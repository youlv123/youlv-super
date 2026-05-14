package com.ruoyi.stock.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ETF基金实时行情-东财对象 fund_etf_spot_em
 *
 * @author DXR
 * @date 2026-01-08
 */
@Data
@TableName("fund_etf_spot_em")
public class FundEtfSpotEm {
    /**
     * 搜索值
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;


    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long fundEtfSpotId;

    /**
     * 代码
     */
    @Excel(name = "代码")
    private String foundCode;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String foundName;

    /**
     * 最新价
     */
    @Excel(name = "最新价")
    private String latestPrice;

    /**
     * IOPV实时估值
     */
    @Excel(name = "IOPV实时估值")
    private String iopvRealTimeValue;

    /**
     * 基金折价率
     */
    @Excel(name = "基金折价率")
    private String fundDiscountRate;

    /**
     * 涨跌幅
     */
    @Excel(name = "涨跌幅")
    private String changePercent;

    /**
     * 涨跌额
     */
    @Excel(name = "涨跌额")
    private String priceRiseFall;

    /**
     * 成交量
     */
    @Excel(name = "成交量")
    private String volume;

    /**
     * 成交额
     */
    @Excel(name = "成交额")
    private Long turnover;

    /**
     * 开盘价
     */
    @Excel(name = "开盘价")
    private String openPrice;

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
     * 昨日收盘价
     */
    @Excel(name = "昨日收盘价")
    private String yesterdayClosingPrice;

    /**
     * 振幅
     */
    private String amplitude;

    /**
     * 换手率
     */
    @Excel(name = "换手率")
    private String turnoverRate;

    /**
     * 量比
     */
    @Excel(name = "量比")
    private String volumeRatio;

    /**
     * 委比
     */
    @Excel(name = "委比")
    private String orderBookRatio;

    /**
     * 外盘
     */
    @Excel(name = "外盘")
    private String externalVolume;

    /**
     * 内盘
     */
    @Excel(name = "内盘")
    private String internalVolume;

    /**
     * 主力净流入-净额（单位:元）
     */
    @Excel(name = "主力净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal mainstayInflowNetAmount;

    /**
     * 主力净流入-净占比（单位:%）
     */
    @Excel(name = "主力净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal mainstayInflowNetPercent;

    /**
     * 超大单净流入-净额（单位:元）
     */
    @Excel(name = "超大单净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal superInflowNetAmount;

    /**
     * 超大单净流入-净占比（单位:%）
     */
    @Excel(name = "超大单净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal superInflowNetPercent;

    /**
     * 大单净流入-净额（单位:元）
     */
    @Excel(name = "大单净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal bigInflowNetAmount;

    /**
     * 大单净流入-净占比（单位:%）
     */
    @Excel(name = "大单净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal bigInflowNetPercent;

    /**
     * 中单净流入-净额（单位:元）
     */
    @Excel(name = "中单净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal midleInflowNetAmount;

    /**
     * 中单净流入-净占比（单位:%）
     */
    @Excel(name = "中单净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal midleInflowNetPercent;

    /**
     * 小单净流入-净额（单位:元）
     */
    @Excel(name = "小单净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal smallInflowNetAmount;

    /**
     * 小单净流入-净占比（单位:%）
     */
    @Excel(name = "小单净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal smallInflowNetPercent;

    /**
     * 现手
     */
    @Excel(name = "现手")
    private String currentTradeLot;

    /**
     * 买一
     */
    @Excel(name = "买一")
    private BigDecimal bidPrice1;

    /**
     * 卖一
     */
    @Excel(name = "卖一")
    private BigDecimal askPrice1;

    /**
     * 最新份额
     */
    @Excel(name = "最新份额")
    private String latestShare;

    /**
     * 流通市值
     */
    @Excel(name = "流通市值")
    private String circulationMarketValue;

    /**
     * 总市值
     */
    @Excel(name = "总市值")
    private String totalMarketValue;

    /**
     * 数据日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "数据日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dataDate;

    /**
     * 数据更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "数据更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dataUpdateTime;

    /**
     * 交易日
     */
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
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedDate;

}
