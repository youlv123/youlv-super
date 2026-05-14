package com.ruoyi.common.enums;

/**
 * 理财状态类型枚举
 *
 * @author dengxinrui
 */
public enum FinancialStatusEnum {
    IN_PROGRESS("0", "进行中"),
    EXPIRATION_NOT_REDEEMED("1", "已到期未赎回"),
    EXPIRATION_HAS_BEEN_REDEEMED("2", "到期已赎回"),
    ;

    private final String code;
    private final String info;

    FinancialStatusEnum(String code, String info) {
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
