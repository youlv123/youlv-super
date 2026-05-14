package com.ruoyi.common.enums;

/**
 * 是否枚举
 *
 * @author dengxinrui
 */
public enum PushFlagEnum {
    UN_PUSH("0", "未推送"),
    PUSH_SUCCESS("1", "推送成功"),
    PUSH_FAIL("2", "推送失败"),
    ;

    private final String code;
    private final String info;

    PushFlagEnum(String code, String info) {
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
