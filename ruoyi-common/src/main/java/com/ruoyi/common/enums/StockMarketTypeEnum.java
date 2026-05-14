package com.ruoyi.common.enums;

/**
 * 股票类型
 *
 * @author dengxinrui
 */
public enum StockMarketTypeEnum {
    SH_A("1", "上海A股"),
   SZ_A("2", "深圳A股"),
    BJ_A("3", "北京A股"),
    NEW_A("4", "新三板A股"),
    CY_A("5", "创业板A股"),
    KC_A("6", "科创板A股"),
    ;

    private final String code;
    private final String info;

    StockMarketTypeEnum(String code, String info) {
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
