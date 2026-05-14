package com.ruoyi.stock.domain;

import lombok.Data;

import java.util.List;

/**
 * 财经数据接口-跌停股池
 */
@Data
public class CzStockDtRes {
    // 交易时间
    private int code;
    private String message;
    private List<CzStockDt> data;

}
