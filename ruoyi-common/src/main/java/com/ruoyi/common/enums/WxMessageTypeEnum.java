package com.ruoyi.common.enums;

/**
 * 微信消息类型枚举
 *
 * @author dengxinrui
 */
public enum WxMessageTypeEnum {
    NORMAL_NOTICE("0", "日常消息提醒"),
    STOCK_NOTICE("1", "股票信息通知"),
    FINANCE_NOTICE("2", "理财提醒"),
    SYSTEM_NOTICE("3", "系统信息提醒"),
    ;

    private final String code;
    private final String info;

    WxMessageTypeEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
