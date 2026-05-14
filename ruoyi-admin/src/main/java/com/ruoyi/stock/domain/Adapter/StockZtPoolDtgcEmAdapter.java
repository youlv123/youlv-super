package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 跌停股池对象转换 stock_zt_pool_dtgc_em
 *
 * @author DXR
 * @date 2025-05-25
 */
@Data
public class StockZtPoolDtgcEmAdapter {

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
     * 动态市盈率
     */
    @JsonProperty("动态市盈率")
    private String dynamicPriceRatio;

    /**
     * 换手率（单位：%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 封单资金（单位：元）
     */
    @JsonProperty("封单资金")
    private Long sealedFunds;


    @JsonProperty("最后封板时间")
    private String lastSealedDate;

    /**
     * 板上成交额（单位：元）
     */
    @JsonProperty("板上成交额")
    private Long turnoverOnBoard;

    /**
     * 业务日期
     */
    private String bizDate;

    /**
     * 连续跌停
     */
    @JsonProperty("连续跌停")
    private String continuousLimitDown;

    /**
     * 开板次数
     */
    @JsonProperty("开板次数")
    private String openNum;

    /**
     * 所属行业
     */
    @JsonProperty("所属行业")
    private String industry;

    /**
     * 数据来源
     */
    private String dataSource;
}
