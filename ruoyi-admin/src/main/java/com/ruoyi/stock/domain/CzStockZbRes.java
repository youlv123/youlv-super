package com.ruoyi.stock.domain;

import lombok.Data;

import java.util.List;

/**
 * 财经数据接口-强势股池
 */
@Data
public class CzStockZbRes {
    // 交易时间
    private int code;
    private String message;
    private List<CzStockZb> data;

}
