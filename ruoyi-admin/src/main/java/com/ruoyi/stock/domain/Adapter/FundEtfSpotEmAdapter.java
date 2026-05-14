package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * ETF基金实时行情-东财对象 转换对象
 *
 * @author DXR
 * @date 2026-01-08
 */
@Data
public class FundEtfSpotEmAdapter {

    /**
     * 代码
     */
    @JsonProperty("代码")
    private String foundCode;

    /**
     * 名称
     */
    @JsonProperty("名称")
    private String foundName;

    /**
     * 最新价
     */
    @JsonProperty("最新价")
    private String latestPrice;

    /**
     * IOPV实时估值
     */
    @JsonProperty("IOPV实时估值")
    private String iopvRealTimeValue;

    /**
     * 基金折价率
     */
    @JsonProperty("基金折价率")
    private String fundDiscountRate;

    /**
     * 涨跌幅
     */
    @JsonProperty("涨跌幅")
    private String changePercent;

    /**
     * 涨跌额
     */
    @JsonProperty("涨跌额")
    private String priceRiseFall;

    /**
     * 成交量
     */
    @JsonProperty("成交量")
    private String volume;

    /**
     * 成交额
     */
    @JsonProperty("成交额")
    private Long turnover;

    /**
     * 开盘价
     */
    @JsonProperty("开盘价")
    private String openPrice;

    /**
     * 最高价
     */
    @JsonProperty("最高价")
    private String highestPrice;

    /**
     * 最低价
     */
    @JsonProperty("最低价")
    private String lowestPrice;

    /**
     * 昨日收盘价
     */
    @JsonProperty("昨收")
    private String yesterdayClosingPrice;

    /**
     * 振幅
     */
    @JsonProperty("振幅")
    private String amplitude;

    /**
     * 换手率
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 量比
     */
    @JsonProperty("量比")
    private String volumeRatio;

    /**
     * 委比
     */
    @JsonProperty("委比")
    private String orderBookRatio;

    /**
     * 外盘
     */
    @JsonProperty("外盘")
    private String externalVolume;

    /**
     * 内盘
     */
    @JsonProperty("内盘")
    private String internalVolume;

    /**
     * 主力净流入-净额（单位:元）
     */
    @JsonProperty("主力净流入-净额")
    private BigDecimal mainstayInflowNetAmount;

    /**
     * 主力净流入-净占比（单位:%）
     */
    @JsonProperty("主力净流入-净占比")
    private BigDecimal mainstayInflowNetPercent;

    /**
     * 超大单净流入-净额（单位:元）
     */
    @JsonProperty("超大单净流入-净额")
    private BigDecimal superInflowNetAmount;

    /**
     * 超大单净流入-净占比（单位:%）
     */
    @JsonProperty("超大单净流入-净占比")
    private BigDecimal superInflowNetPercent;

    /**
     * 大单净流入-净额（单位:元）
     */
    @JsonProperty("大单净流入-净额")
    private BigDecimal bigInflowNetAmount;

    /**
     * 大单净流入-净占比（单位:%）
     */
    @JsonProperty("大单净流入-净占比")
    private BigDecimal bigInflowNetPercent;

    /**
     * 中单净流入-净额（单位:元）
     */
    @JsonProperty("中单净流入-净额")
    private BigDecimal midleInflowNetAmount;

    /**
     * 中单净流入-净占比（单位:%）
     */
    @JsonProperty("中单净流入-净占比")
    private BigDecimal midleInflowNetPercent;

    /**
     * 小单净流入-净额（单位:元）
     */
    @JsonProperty("小单净流入-净额")
    private BigDecimal smallInflowNetAmount;

    /**
     * 小单净流入-净占比（单位:%）
     */
    @JsonProperty("小单净流入-净占比")
    private BigDecimal smallInflowNetPercent;

    /**
     * 现手
     */
    @JsonProperty("现手")
    private String currentTradeLot;

    /**
     * 买一
     */
    @JsonProperty("买一")
    private BigDecimal bidPrice1;

    /**
     * 卖一
     */
    @JsonProperty("卖一")
    private BigDecimal askPrice1;

    /**
     * 最新份额
     */
    @JsonProperty("最新份额")
    private String latestShare;

    /**
     * 流通市值
     */
    @JsonProperty("流通市值")
    private String circulationMarketValue;

    /**
     * 总市值
     */
    @JsonProperty("总市值")
    private String totalMarketValue;

    /**
     * 数据日期
     */
    @JsonProperty("数据日期")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "GMT+8")
    private Date dataDate;

    /**
     * 数据更新时间
     */
    @JsonProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "GMT+8")
    private Date dataUpdateTime;

    /**
     * 交易日
     */
    private String bizDate;



}
