package com.ruoyi.common.enums;

/**
 * 交易类型
 *
 * @author dengxinrui
 */
public enum TransactionTypeEnum {
    BUY("0", "买入"),
    SELL("1", "卖出"),
    ;

    private final String code;
    private final String info;

    TransactionTypeEnum(String code, String info) {
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
