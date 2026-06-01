package com.ruoyi.aia.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.aia.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 调用你图片上三个接口的服务
 */
@Service
@RequiredArgsConstructor
public class ChatBotService {

    private final WebClient chatBotWebClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 1. 调用图片接口：流式对话 /chatbot/stream
     */
    public Flux<ServerSentEvent<String>> streamChat(StreamChatReq req) {

        System.out.println("========== Service 进入 ==========");
        System.out.println("参数：" + req);

        return chatBotWebClient.post()
                .uri("/chatbot/stream")
                .bodyValue(req)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                // --------- 强制打印所有原始报文 ---------
                .doOnNext(raw -> {
                    System.out.println("=== 原始报文 ==> " + raw);
                })
//                .filter(line -> line.startsWith("data:"))
//                .map(line -> line.substring(5).trim())
                .map(line -> line.trim())
                .filter(json -> json != null && !json.trim().isEmpty() && !"[DONE]".equals(json))
                .doOnNext(json -> {
                    System.out.println("=== 截取JSON ==> " + json);
                })
                .map(json -> {
                    try {
                        ChatSseResp resp = objectMapper.readValue(json, ChatSseResp.class);
                        String finalJson = objectMapper.writeValueAsString(resp);
                        ServerSentEvent<String> sse = ServerSentEvent.builder(finalJson).build();
                        // ========== 这里 100% 会打印 ==========
                        System.out.println("✅ 最终SSE输出 => " + finalJson);
                        return sse;
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("解析失败");
                    }
                })
                // 最后再打印一次，确保一定输出
                .doOnNext(sse -> {
                    System.out.println("🚀 发送给前端：" + sse.data());
                });
    }

    /**
     * 2. 调用图片接口：重新生成 /chatbot/regenerate
     */
    public Flux<ChatSseResp> regenerate(RegenerateChatReq req) {
        return chatBotWebClient.post()
                .uri("/chatbot/regenerate")
                .bodyValue(req)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .filter(line -> line.startsWith("data:"))
                .map(line -> line.substring(5).trim())
//                .filter(json -> !json.isBlank() && !"[DONE]".equals(json))//jdk11用法
                .filter(json -> json != null && !json.trim().isEmpty() && !"[DONE]".equals(json))//jdk8用法
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, ChatSseResp.class);
                    } catch (Exception e) {
                        throw new RuntimeException("SSE解析失败", e);
                    }
                });
    }

    /**
     * 3. 调用图片接口：停止生成 /chatbot/stop
     */
    public Mono<StopChatResp> stop(StopChatReq req) {
        return chatBotWebClient.post()
                .uri("/chatbot/stop")
                .bodyValue(req)
                .retrieve()
                .bodyToMono(StopChatResp.class);
    }
}
