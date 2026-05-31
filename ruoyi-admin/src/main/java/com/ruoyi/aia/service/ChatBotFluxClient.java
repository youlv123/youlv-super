package com.ruoyi.aia.service;//package com.ruoyi.aia.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ruoyi.aia.domain.*;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//public class ChatBotFluxClient {
//    private final WebClient webClient;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private static final String BASE_URL = "http://127.0.0.1:8000";
//
//    public ChatBotFluxClient() {
//        this.webClient = WebClient.builder()
//                .baseUrl(BASE_URL)
//                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                .build();
//    }
//
//    /**
//     * 1. 流式对话接口，返回Flux，响应式接收SSE
//     */
//    public Flux<SseEventDTO> streamChat(StreamChatReq req) {
//        return webClient.post()
//                .uri("/chatbot/stream")
//                .bodyValue(req)
//                .accept(MediaType.TEXT_EVENT_STREAM)
//                .retrieve()
//                .bodyToFlux(String.class)
//                // 原始SSE每一行是data: xxx，过滤、截取data内容
//                .filter(line -> line.startsWith("data:"))
//                .map(line -> line.substring(5).trim())
//                .filter(json -> !json.isEmpty())
//                .map(json -> {
//                    try {
//                        return objectMapper.readValue(json, SseEventDTO.class);
//                    } catch (Exception e) {
//                        throw new RuntimeException("SSE JSON解析失败", e);
//                    }
//                });
//    }
//
//    /**
//     * 2. 重新生成接口
//     */
//    public Flux<SseEventDTO> regenerateChat(RegenerateChatReq req) {
//        return webClient.post()
//                .uri("/chatbot/regenerate")
//                .bodyValue(req)
//                .accept(MediaType.TEXT_EVENT_STREAM)
//                .retrieve()
//                .bodyToFlux(String.class)
//                .filter(line -> line.startsWith("data:"))
//                .map(line -> line.substring(5).trim())
//                .filter(json -> !json.isEmpty())
//                .map(json -> {
//                    try {
//                        return objectMapper.readValue(json, SseEventDTO.class);
//                    } catch (Exception e) {
//                        throw new RuntimeException("SSE JSON解析失败", e);
//                    }
//                });
//    }
//
//    /**
//     * 3. 停止接口（普通POST，Mono）
//     */
//    public Mono<StopChatResp> stopChat(StopChatReq req) {
//        return webClient.post()
//                .uri("/chatbot/stop")
//                .bodyValue(req)
//                .retrieve()
//                .bodyToMono(StopChatResp.class);
//    }
//}
