package com.ruoyi.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 东方财富-指数-分时返回对象
 *
 * @author DXR
 * @date 2025-06-12
 */
@Data
public class StockBoardConceptHistMinEm {


    /**
     * 业务日期
     */
    @JsonProperty("日期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date bizDate;

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
     * 最新价（单位：元）
     */
    @JsonProperty("最新价")
    private String latestPrice;

}
