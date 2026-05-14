package com.ruoyi.stock.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 东方财富-指数返回对象
 *
 * @author DXR
 * @date 2025-06-11
 */
@Data
public class StockBoardConceptHistEm {


    /**
     * 业务日期
     */
    @JsonProperty("日期")
    private String bizDate;

    /**
     * 开盘价
     */
    @JsonProperty("开盘")
    private String openingPrice;

    /**
     * 收盘价
     */
    @JsonProperty("收盘")
    private String closingPrice;

    /**
     * 最高价
     */
    @JsonProperty("最高")
    private String highestPrice;

    /**
     * 最低价
     */
    @JsonProperty("最低")
    private String lowestPrice;


    /**
     * 涨跌幅（单位：%）
     */
    @JsonProperty("涨跌幅")
    private String changePercent;

    /**
     * 涨跌额（单位:元）
     */
    @JsonProperty("涨跌额")
    private String priceRiseFall;

    /**
     * 成交量（单位:手）
     */
    @JsonProperty("成交量")
    private String volume;


    /**
     * 成交额（单位：元）
     */
    @JsonProperty("成交额")
    private String turnover;

    /**
     * 振幅
     */
    @JsonProperty("振幅")
    private String amplitude;


    /**
     * 换手率（单位：%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

}
