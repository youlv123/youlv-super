package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 强势股池对象转换 stock_zt_pool_strong_em
 *
 * @author DXR
 * @date 2025-05-20
 */
@Data

public class StockZtPoolStrongEmAdapter {


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
     * 涨跌幅（单位：%）
     */
    @JsonProperty("涨跌幅")
    private String changePercent;

    /**
     * 最新价（单位：元）
     */
    @JsonProperty("最新价")
    private String latestPrice;

    /**
     * 涨停价（单位：元）
     */
    @JsonProperty("涨停价")
    private String priceLimitUp;

    /**
     * 成交额（单位：元）
     */
    @JsonProperty("成交额")
    private Long turnover;

    /**
     * 流通市值（单位：元）
     */
    @JsonProperty("流通市值")
    private String circulatingMarketValue;

    /**
     * 总市值（单位：元）
     */
    @JsonProperty("总市值")
    private String totalMarketValue;

    /**
     * 换手率（单位：%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 涨速（单位：%）
     */
    @JsonProperty("涨速")
    private String growthRate;

    /**
     * 是否新高
     */
    @JsonProperty("是否新高")
    private String isNewHigh;

    /**
     * 量比
     */
    @JsonProperty("量比")
    private String volumeRatio;

    /**
     * 业务日期
     */
    @Excel(name = "业务日期")
    private String bizDate;

    /**
     * 涨停统计（格式：X/Y）
     */
    @JsonProperty("涨停统计")
    private String priceLimitStatistics;

    /**
     * 入选理由
     */
    @JsonProperty("入选理由")
    private String selectionReasons;

    /**
     * 所属行业
     */
    @JsonProperty("所属行业")
    private String industry;

    /** 数据来源 */
    private String dataSource;


}
