package com.ruoyi.aia.domain;

import lombok.Data;

/**
 * 停止接口返回结构（按你图片）
 */
@Data
public class StopChatResp {
    private String session_id;
    private Boolean stopped;
    private String reason;
}
