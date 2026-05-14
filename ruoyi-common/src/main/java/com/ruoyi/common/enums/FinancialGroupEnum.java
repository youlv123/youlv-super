package com.ruoyi.common.enums;

/**
 * 理财分组
 *
 * @author dengxinrui
 */
public enum FinancialGroupEnum {
    DEMAND_DEPOSIT("0", "活期"),
    ETF("1", "ETF"),
    TIME_DEPOSIT("2", "定期存款"),
    GOLD("3", "黄金"),
    FUND("4", "基金"),

    ;

    private final String code;
    private final String info;

    FinancialGroupEnum(String code, String info) {
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
