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
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 炸板股池对象 stock_zt_pool_zbgc_em
 *
 * @author DXR
 * @date 2025-05-21
 */
@Data
@TableName("stock_zt_pool_zbgc_em")
public class StockZtPoolZbgcEm {


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
    private Long zbgcId;

    /**
     * 序号
     */
    private Long serialNo;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 涨跌幅（单位：%）
     */
    private String changePercent;

    /**
     * 最新价（单位：元）
     */
    private String latestPrice;

    /**
     * 涨停价（单位：元）
     */
    private String priceLimitUp;

    /**
     * 成交额（单位：元）
     */
    private Long turnover;

    /**
     * 流通市值（单位：元）
     */
    private String circulatingMarketValue;

    /**
     * 总市值（单位：元）
     */
    private String totalMarketValue;

    /**
     * 换手率（单位：%）
     */
    private String turnoverRate;

    /**
     * 涨速（单位：%）
     */
    private String growthRate;

    /**
     * 首次封板时间
     */
    private Date firstCoverDate;


    /**
     * 业务日期
     */
    private String bizDate;

    /**
     * 炸板次数
     */
    private String explosionNum;

    /**
     * 涨停统计（格式：X/Y）
     */
    private String priceLimitStatistics;

    /**
     * 振幅
     */
    private String amplitude;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 数据来源
     */
    private String dataSource;

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
