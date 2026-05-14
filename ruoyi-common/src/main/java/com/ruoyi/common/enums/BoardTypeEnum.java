package com.ruoyi.common.enums;

/**
 * 是否枚举
 *
 * @author dengxinrui
 */
public enum BoardTypeEnum {
    CONCEPT_BOARD("0", "概念板块"),
    INDUSTRY_BOARD("1", "行业板块"),
    ;

    private final String code;
    private final String info;

    BoardTypeEnum(String code, String info) {
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
