package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 东财历史行情数据转换对象 stock_zh_a_hist
 *
 * @author DXR
 * @date 2025-06-03
 */
@Data
public class StockZhAHistAdapter {

    /**
     * 股票代码
     */
    @JsonProperty("股票代码")
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /** 股票市场类型 */
    private String stockMarketType;

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
     * 成交额（单位:元）
     */
    @JsonProperty("成交额")
    private Long turnover;

    /**
     * 振幅（单位:%）
     */
    @JsonProperty("振幅")
    private String amplitude;

    /**
     * 涨跌幅（单位:%）
     */
    @JsonProperty("涨跌幅")
    private String changePercent;

    /**
     * 涨跌额（单位:元）
     */
    @JsonProperty("涨跌额")
    private String priceRiseFall;

    /**
     * 换手率（单位:%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 交易日
     */
    @JsonProperty("日期")
    private String bizDate;


}
