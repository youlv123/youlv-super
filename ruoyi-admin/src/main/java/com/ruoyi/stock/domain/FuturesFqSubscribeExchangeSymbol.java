package com.ruoyi.stock.domain;

import lombok.Data;

/**
 * 外盘-品种代码表
 */
@Data
public class FuturesFqSubscribeExchangeSymbol {

    // 品类名称
    private String symbol;

    // 代码
    private String code;
}
