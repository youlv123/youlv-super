package com.ruoyi.common.enums;

/**
 * 返回码及返回信息枚举
 *
 * @author dengxinrui
 */
public enum ResponseStatusEnum {
    RESPONSE_STATUS_200("200", "成功"),
    RESPONSE_STATUS_201("201", "分类或者物品信息不存在！"),
    ;

    private final String code;
    private final String info;

    ResponseStatusEnum(String code, String info) {
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
