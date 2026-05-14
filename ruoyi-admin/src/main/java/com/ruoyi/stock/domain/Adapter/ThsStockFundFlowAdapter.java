package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 同花顺个股资金流向转换对象 ths_stock_fund_flow
 *
 * @author DXR
 * @date 2025-08-20
 */
@Data
public class ThsStockFundFlowAdapter {

    /**
     * 序号
     */
    @JsonProperty("序号")
    private Long serialNo;

    /**
     * 股票代码
     */
    @JsonProperty("股票代码")
    private String stockCode;

    /**
     * 股票名称
     */
    @JsonProperty("股票简称")
    private String stockName;

    /**
     * 最新价（单位：元）
     */
    @JsonProperty("最新价")
    private String latestPrice;

    /**
     * 涨跌幅（单位:%）
     */
    @JsonProperty("涨跌幅")
    private String changePercent;

    /**
     * 换手率（单位:%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 流入资金（单位:元）
     */
    @JsonProperty("流入资金")
    private String inflowFunds;

    /**
     * 流出资金（单位:元）
     */
    @JsonProperty("流出资金")
    private String outflowFunds;

    /**
     * 净额（单位:元）
     */
    @JsonProperty("净额")
    private String netAmount;

    /**
     * 成交额（单位:元）
     */
    @JsonProperty("成交额")
    private String turnover;


    private String type;


    /**
     * 涨跌幅（单位:%）
     */
    @JsonProperty("阶段涨跌幅")
    private String changePercenTemp;

    /**
     * 换手率（单位:%）
     */
    @JsonProperty("连续换手率")
    private String turnoverRateTemp;

    /**
     * 净额（单位:元）
     */
    @JsonProperty("资金流入净额")
    private String netAmountTemp;


}
