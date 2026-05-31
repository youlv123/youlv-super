package com.ruoyi.aia.domain;

import lombok.Data;

/**
 * SSE 响应体（完全按照你图片返回结构）
 */
@Data
public class ChatSseResp {
    private String session_id;
    private SseMessage message;
    private String event_type;
    private Object meta;
}

@Data
class SseMessage {
    private String msg_id;
    private String content;
    private String role;
}
