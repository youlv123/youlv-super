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

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * ETF基金实时行情-同花顺对象 fund_etf_spot_ths
 *
 * @author DXR
 * @date 2026-01-08
 */
@Data
@TableName("fund_etf_spot_em")
public class FundEtfSpotThsAdapter {
    /**
     * 序号
     */
    @JsonProperty("序号")
    private Long serialNo;

    /**
     * 代码
     */
    @JsonProperty("基金代码")
    private String foundCode;

    /**
     * 名称
     */
    @JsonProperty("基金名称")
    private String foundName;

    /**
     * 当前-单位净值
     */
    @JsonProperty("当前-单位净值")
    private BigDecimal currentNav;

    /**
     * 当前-累计净值
     */
    @JsonProperty("当前-累计净值")
    private BigDecimal currentCumulativeNav;

    /**
     * 前一日-单位净值
     */
    @JsonProperty("前一日-单位净值")
    private BigDecimal previousNav;

    /**
     * 前一日-累计净值
     */
    @JsonProperty("前一日-累计净值")
    private BigDecimal previousCumulativeNav;

    /**
     * 增长值
     */
    @JsonProperty("增长值")
    private String growthValue;

    /**
     * 增长率
     */
    @JsonProperty("增长率")
    private String growthRate;

    /**
     * 赎回状态
     */
    @JsonProperty("赎回状态")
    private String redemptionStatus;

    /**
     * 申购状态
     */
    @JsonProperty("申购状态")
    private String subscriptionStatus;

    /**
     * 最新-交易日
     */
    @JsonProperty("最新-交易日")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "GMT+8")
    private String latestTradingDate;

    /**
     * 最新-单位净值
     */
    @JsonProperty("最新-单位净值")
    private BigDecimal latestNav;

    /**
     * 最新-累计净值
     */
    @JsonProperty("最新-累计净值")
    private BigDecimal latestCumulativeNav;

    /**
     * 基金类型
     */
    @JsonProperty("基金类型")
    private String fundType;

    /**
     * 数据更新时间
     */
    @JsonProperty("查询日期")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "GMT+8")
    private Date dataUpdateTime;

    /**
     * 交易日
     */
    private String bizDate;

}
