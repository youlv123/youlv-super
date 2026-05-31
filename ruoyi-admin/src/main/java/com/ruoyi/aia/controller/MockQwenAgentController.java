package com.ruoyi.aia.controller;//package com.ruoyi.aia.controller;
//
//import com.ruoyi.aia.domain.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.UUID;
//
///**
// * 千问Agent2.0 模拟服务端
// * 提供 3 个接口：stream / regenerate / stop
// * 给你的业务项目调用，本地测试流式输出
// */
//@Slf4j
//@RestController
//@RequestMapping("/chatbot")
//public class MockQwenAgentController {
//
//    /**
//     * 1. 流式对话接口
//     */
//    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<ChatSseResp> stream(@RequestBody StreamChatReq req) {
//        log.info("收到流式请求：{}", req);
//
//        // 模拟AI逐字输出：你好呀，我是千问Agent2.0，很高兴为你服务
//        String[] texts = new String[]{
//            "你", "好", "呀", "，", "我", "是", "千", "问", "Agent2.0",
//            "，", "很", "高", "兴", "为", "你", "服", "务"
//        };
//
//        return Flux.fromArray(texts)
//            .delayElements(Duration.ofMillis(300))  // 每300ms输出一个字，模拟流式
//            .index()
//            .map(idx -> {
//                long i = idx.getT1();
//                String content = idx.getT2();
//                String event = i == texts.length - 1 ? "end" : "thinking";
//
//                return new ChatSseResp(
//                    req.getSession_id(),
//                    new SseMessage(UUID.randomUUID().toString(), content, "assistant"),
//                    event,
//                    null
//                );
//            });
//    }
//
//    /**
//     * 2. 重新生成接口
//     */
//    @PostMapping(value = "/regenerate", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<ChatSseResp> regenerate(@RequestBody RegenerateChatReq req) {
//        log.info("重新生成：{}", req);
//
//        String[] texts = new String[]{
//            "我", "重", "新", "回", "答", "你", "的", "问", "题", "！"
//        };
//
//        return Flux.fromArray(texts)
//            .delayElements(Duration.ofMillis(300))
//            .index()
//            .map(idx -> {
//                long i = idx.getT1();
//                String content = idx.getT2();
//                String event = i == texts.length - 1 ? "end" : "thinking";
//
//                return new ChatSseResp(
//                    req.getSession_id(),
//                    new SseMessage(UUID.randomUUID().toString(), content, "assistant"),
//                    event,
//                    null
//                );
//            });
//    }
//
//    /**
//     * 3. 停止生成接口
//     */
//    @PostMapping("/stop")
//    public Mono<StopChatResp> stop(@RequestBody StopChatReq req) {
//        log.info("停止会话：{}", req.getSession_id());
//        return Mono.just(new StopChatResp(req.getSession_id(), true, "用户主动停止"));
//    }
//}
