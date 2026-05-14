package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 涨停板行情对象转换 stock_zt_pool_em
 *
 * @author DXR
 * @date 2025-05-09
 */
@Data
public class StockZtPoolEmAdapter{

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
     * 换手率（单位：%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 封板资金（单位：元）
     */
    @JsonProperty("封板资金")
    private Long sealedFunds;


    @JsonProperty("首次封板时间")
    @TableField(exist = false)
    private String firstSealedDate;


    @JsonProperty("最后封板时间")
    @TableField(exist = false)
    private String lastSealedDate;

    /**
     * 炸板次数
     */
    @JsonProperty("炸板次数")
    private Long breakNum;

    /**
     * 涨停统计（格式：X/Y）
     */
    @JsonProperty("涨停统计")
    private String priceLimitStatistics;

    /**
     * 连板数
     */
    @JsonProperty("连板数")
    private Long continuousNum;

    /**
     * 所属行业
     */
    @JsonProperty("所属行业")
    private String industry;

    /** 数据来源 */
    private String dataSource;

    /**
     * 业务日期
     */

    private String bizDate;
}
