package com.ruoyi.aia.controller;

import com.ruoyi.aia.domain.*;
import com.ruoyi.aia.service.ChatBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 前端调用接口
 * 内部调用你图片上的三个接口，并把流式数据转给前端
 */
@RestController
@RequestMapping("/system/test")
@RequiredArgsConstructor
public class ChatBotController {

    private final ChatBotService chatBotService;

    /**
     * 1. 流式对话
     * 返回 Flux + SSE，前端实时接收
     */
    @PostMapping(value = "/api/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> stream(@RequestBody StreamChatReq req) {
        System.out.println("========== Controller 进入 ==========");
        return chatBotService.streamChat(req);
    }

    /**
     * 2. 重新生成回答
     */
    @PostMapping(value = "/api/chat/regenerate", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatSseResp> regenerate(@RequestBody RegenerateChatReq req) {
        return chatBotService.regenerate(req);
    }

    /**
     * 3. 停止生成
     */
    @PostMapping("/api/chat/stop")
    public Mono<StopChatResp> stop(@RequestBody StopChatReq req) {
        return chatBotService.stop(req);
    }
}
