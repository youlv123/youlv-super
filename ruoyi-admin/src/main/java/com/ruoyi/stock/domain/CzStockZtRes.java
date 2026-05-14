package com.ruoyi.stock.domain;

import lombok.Data;

import java.util.List;

/**
 * 财经数据接口-涨停股池
 */
@Data
public class CzStockZtRes {
    // 交易时间
    private int code;
    private String message;
    private List<CzStockZt> data;

}
