package com.ruoyi.system.domain;

import lombok.Data;

/**
 * 企业微信获取token返回值
 */
@Data
public class WxTokenResponse {
    /**
     * 出错返回码，为0表示成功，非0表示调用失败
     */
    private  Integer errcode;

    /**
     * 	返回码提示语
     */
    private  String errmsg;

    /**
     * 	获取到的凭证，最长为512字节
     */
    private  String access_token;

    /**
     * 	凭证的有效时间（秒）
     */
    private  String expires_in;

}