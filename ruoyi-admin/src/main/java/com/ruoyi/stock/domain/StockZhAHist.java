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
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 东财历史行情数据对象 stock_zh_a_hist
 *
 * @author DXR
 * @date 2025-06-03
 */
@Data
@TableName("stock_zh_a_hist")
public class StockZhAHist {


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
    private Long zhHistId;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /** 股票市场类型 */
    private String stockMarketType;

    /**
     * 开盘价
     */
    private String openingPrice;

    /**
     * 收盘价
     */
    private String closingPrice;

    /**
     * 最高价
     */
    private String highestPrice;

    /**
     * 最低价
     */
    private String lowestPrice;

    /**
     * 成交量（单位:手）
     */
    private String volume;

    /**
     * 成交额（单位:元）
     */
    private Long turnover;

    /**
     * 振幅（单位:%）
     */
    private String amplitude;

    /**
     * 涨跌幅（单位:%）
     */
    private String changePercent;

    /**
     * 涨跌额（单位:元）
     */
    private String priceRiseFall;

    /**
     * 换手率（单位:%）
     */
    private String turnoverRate;

    /**
     * 交易日
     */
    private String bizDate;

    /**
     * 总市值（单位：元）
     */
    private String totalMarketValue;

    /**
     * 流通市值（单位：元）
     */
    private String circulatingMarketValue;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
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
