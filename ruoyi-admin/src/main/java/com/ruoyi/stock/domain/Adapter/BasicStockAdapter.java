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
 * 基础股票转换对象 basic_stock
 *
 * @author DXR
 * @date 2025-07-31
 */
@Data
public class BasicStockAdapter {


    /**
     * 序号
     */
    @JsonProperty("序号")
    private Long serialNo;

    /**
     * 股票代码
     */

    @JsonProperty("代码")
    private String stockCode;

    /**
     * 股票名称
     */
    @JsonProperty("名称")
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
     * 今日开盘价
     */
    @JsonProperty("今开")
    private String todayOpenPrice;

    /**
     * 昨日收盘价
     */
    @JsonProperty("昨收")
    private String yesterdayClosingPrice;

    /** 量比 */
    @JsonProperty("量比")
    private String volumeRatio;

    /**
     * 换手率（单位:%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 市盈率-动态
     */
    @JsonProperty("市盈率-动态")
    private String dynamicPriceRatio;

    /**
     * 市净率
     */
    @JsonProperty("市净率")
    private String priceToBookRatio;
    /**
     * 上市时间
     */
    @JsonProperty("上市日期")
    private String listingTime;

    /**
     * 总市值（单位：元）
     */
    @JsonProperty("总市值")
    private String totalMarketValue;

    /**
     * 流通市值（单位：元）
     */
    @JsonProperty("流通市值")
    private String circulatingMarketValue;

    /**
     * 涨速（单位：%）
     */
    @JsonProperty("涨速")
    private String growthRate;

    /** 5分钟涨跌幅（单位:%） */
    @JsonProperty("5分钟涨跌")
    private String mins5ChangePercent;

    /** 60日涨跌幅（单位:%） */
    @JsonProperty("60日涨跌幅")
    private String days60ChangePercent;

    /**
     * 年初至今涨跌幅（单位:%）
     */
    @JsonProperty("年初至今涨跌幅")
    private String yearChangePercent;

    private String stockMarketType;

    private String bizDate;
}
