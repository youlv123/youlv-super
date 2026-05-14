package com.ruoyi.system.domain;


import lombok.Data;

/**
 * 微信发送请求实体类
 */
@Data
public class WxSendMessageRequest {
    /**
     * 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
     * 特殊情况：指定为"@all"，则向该企业应用的全部成员发送
     * 非必填
     */
    private String touser;

    /**
     * 	指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
     * 当touser为"@all"时忽略本参数
     * 非必填
     */
    private String toparty;

    /**
     * 指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
     * 当touser为"@all"时忽略本参数
     * 非必填
     */
    private String totag;

    /**
     * 消息类型，固定为text
     * 必填
     */
    private String msgtype="text";

    /**
     * 企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
     * 必填
     */
    private int agentid;

    /**
     * 消息内容，最长不超过2048个字节，超过将截断（支持id转译）
     * 必填
     */
    private WxContentDto text;

    /**
     * 	表示是否是保密消息，0表示可对外分享，1表示不能分享且内容显示水印，默认为0
     * 	非必填
     */
    private int safe=0;

    /**
     * 	表示是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
     * 	非必填
     */
    private int enable_id_trans;

    /**
     * 	表示是否开启重复消息检查，0表示否，1表示是，默认0
     * 	非必填
     */
    private int enable_duplicate_check;

    /**
     * 	表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
     * 	非必填
     */
    private int duplicate_check_interval;


}