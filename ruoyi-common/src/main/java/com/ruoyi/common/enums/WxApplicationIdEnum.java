package com.ruoyi.common.enums;

/**
 * 微信应用id枚举
 *
 * @author dengxinrui
 */
public enum WxApplicationIdEnum {
    NORMAL_NOTICE("1000002", "日常消息提醒"),
    STOCK_NOTICE("1000003", "股票信息通知"),
    FINANCE_NOTICE("1000004", "理财提醒"),
    SYSTEM_NOTICE("1000005", "系统信息提醒"),

    ;

    private final String code;
    private final String info;

    WxApplicationIdEnum(String code, String info) {
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
