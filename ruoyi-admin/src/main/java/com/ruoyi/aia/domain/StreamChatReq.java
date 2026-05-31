package com.ruoyi.aia.domain;

import lombok.Data;

/**
 * 流式对话请求（完全按照你图片字段定义）
 */
@Data
public class StreamChatReq {
    /**
     * 会话ID（必填）
     */
    private String session_id;

    /**
     * 用户输入内容（必填）
     */
    private String message;

    /**
     * 确认按钮 1-确认 0-不确认（非必填）
     */
    private Integer confirmed;

    /**
     * OCR识别结果（非必填）
     */
    private String ocr_result;
}
