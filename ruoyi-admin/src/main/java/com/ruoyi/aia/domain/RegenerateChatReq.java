package com.ruoyi.aia.domain;

import lombok.Data;

/**
 * 重新生成回答请求（按你图片）
 */
@Data
public class RegenerateChatReq {
    /**
     * 会话ID（必填）
     */
    private String session_id;

    /**
     * 用户问题（必填）
     */
    private String message;
}
