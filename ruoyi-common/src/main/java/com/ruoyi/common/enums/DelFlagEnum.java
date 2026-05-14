package com.ruoyi.common.enums;

/**
 * 逻辑删除枚举
 *
 * @author dengxinrui
 */
public enum DelFlagEnum {
    BOOLEAN_0("0", "正常"),
    BOOLEAN_1("1", "删除"),
    ;

    private final String code;
    private final String info;

    DelFlagEnum(String code, String info) {
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
