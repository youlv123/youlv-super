package com.ruoyi.stock.domain;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础股票对象 basic_stock
 *
 * @author DXR
 * @date 2025-07-31
 */
@Data
@TableName("basic_stock")
public class BasicStock  {

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 搜索值
     */
    @JsonIgnore
    @TableField(exist = false)
    private String searchValue;

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long basicStockId;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private Long serialNo;

    /**
     * 股票代码
     */
    @Excel(name = "股票代码")
    private String stockCode;

    /**
     * 股票名称
     */
    @Excel(name = "股票名称")
    private String stockName;

    /**
     * 最新价（单位：元）
     */
    @Excel(name = "最新价", readConverterExp = "单=位：元")
    private String latestPrice;

    /**
     * 涨跌幅（单位:%）
     */
    @Excel(name = "涨跌幅", readConverterExp = "单=位:%")
    private String changePercent;

    /**
     * 涨跌额（单位:元）
     */
    @Excel(name = "涨跌额", readConverterExp = "单=位:元")
    private String priceRiseFall;

    /**
     * 成交量（单位:手）
     */
    @Excel(name = "成交量", readConverterExp = "单=位:手")
    private String volume;

    /**
     * 成交额（单位:元）
     */
    @Excel(name = "成交额", readConverterExp = "单=位:元")
    private Long turnover;

    /**
     * 振幅（单位:%）
     */
    @Excel(name = "振幅", readConverterExp = "单=位:%")
    private String amplitude;

    /**
     * 最高价
     */
    @Excel(name = "最高价")
    private String highestPrice;

    /**
     * 最低价
     */
    @Excel(name = "最低价")
    private String lowestPrice;

    /**
     * 今日开盘价
     */
    @Excel(name = "今日开盘价")
    private String todayOpenPrice;

    /**
     * 昨日收盘价
     */
    @Excel(name = "昨日收盘价")
    private String yesterdayClosingPrice;

    /** 量比 */
    @Excel(name = "量比")
    private String volumeRatio;

    /**
     * 换手率（单位:%）
     */
    @Excel(name = "换手率", readConverterExp = "单=位:%")
    private String turnoverRate;

    /**
     * 市盈率-动态
     */
    @Excel(name = "市盈率-动态")
    private String dynamicPriceRatio;

    /**
     * 市净率
     */
    @Excel(name = "市净率")
    private String priceToBookRatio;

    /** 上市时间 */
    @Excel(name = "上市时间")
    private String listingTime;

    /**
     * 总市值（单位：元）
     */
    @Excel(name = "总市值", readConverterExp = "单=位：元")
    private String totalMarketValue;

    /**
     * 流通市值（单位：元）
     */
    @Excel(name = "流通市值", readConverterExp = "单=位：元")
    private String circulatingMarketValue;

    /**
     * 涨速（单位：%）
     */
    @Excel(name = "涨速", readConverterExp = "单=位：%")
    private String growthRate;

    /** 5分钟涨跌幅（单位:%） */
    @Excel(name = "5分钟涨跌幅", readConverterExp = "单=位:%")
    private String mins5ChangePercent;

    /** 60日涨跌幅（单位:%） */
    @Excel(name = "60日涨跌幅", readConverterExp = "单=位:%")
    private String days60ChangePercent;

    /**
     * 年初至今涨跌幅（单位:%）
     */
    @Excel(name = "年初至今涨跌幅", readConverterExp = "单=位:%")
    private String yearChangePercent;

    /** 股票市场类型 */
    @Excel(name = "股票市场类型")
    private String stockMarketType;

    /** 交易日 */
    @Excel(name = "交易日")
    private String bizDate;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedDate;

}
