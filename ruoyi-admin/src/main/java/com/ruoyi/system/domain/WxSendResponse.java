package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 企微发送消息返回实体
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxSendResponse {
    /**
     * 出错返回码，为0表示成功，非0表示调用失败
     */
    private  Integer errcode;

    /**
     * 	返回码提示语
     */
    private  String errmsg;

    /**
     * 消息id，用于撤回应用消息
     */
    private  String msgid;
}