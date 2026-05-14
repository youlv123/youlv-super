package com.ruoyi.common.enums;

/**
 * 是否枚举
 *
 * @author dengxinrui
 */
public enum BooleanEnum {
    BOOLEAN_Y("Y", "是"),
    BOOLEAN_N("N", "否"),
    ;

    private final String code;
    private final String info;

    BooleanEnum(String code, String info) {
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
