package com.ruoyi.stock.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 外盘-实时行情数据
 *
 * @author DXR
 * @date 2026-01-30
 */
@Data
public class FuturesForeignCommodityRealtime {


    /**
     * 名称
     */
    @JsonProperty("名称")
    private String name;

    /**
     * 最新价（单位：元）
     */
    @JsonProperty("最新价")
    private String latestPrice;

    /**
     * 人民币报价（单位：RMB）
     */
    @JsonProperty("人民币报价")
    private String rmbQuotation;

    /**
     * 涨跌额（单位:元）
     */
    @JsonProperty("涨跌额")
    private String priceRiseFall;

    /**
     * 涨跌幅（单位：%）
     */
    @JsonProperty("涨跌幅")
    private String changePercent;

    /**
     * 开盘价
     */
    @JsonProperty("开盘价")
    private String openingPrice;

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
     * 昨日结算价
     */
    @JsonProperty("昨日结算价")
    private String yesterdayClosingPrice;

    /**
     * 持仓量
     */
    @JsonProperty("持仓量")
    private String holdings;

    /**
     * 买价
     */
    @JsonProperty("买价")
    private String bidPrice1;

    /**
     * 卖价
     */
    @JsonProperty("卖价")
    private String askPrice1;

    /**
     * 行情时间
     */
    @JsonProperty("行情时间")
    private String time;

    /**
     * 日期
     */
    @JsonProperty("日期")
    private String date;


}
