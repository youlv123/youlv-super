package com.ruoyi.stock.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 东方财富网-基金净值估算对象 fund_value_estimation_em
 *
 * @author DXR
 * @date 2026-01-09
 */
@Data
@TableName("fund_value_estimation_em")
public class FundValueEstimationEm {


    /**
     * 自增主键
     */
    private Long fundValueEstimationId;

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
     * 估算值
     */
    @Excel(name = "估算值")
    private BigDecimal estimatedValue;

    /**
     * 估算增长率
     */
    @Excel(name = "估算增长率")
    private String estimatedGrowthRate;

    /**
     * 公布单位净值
     */
    @Excel(name = "公布单位净值")
    private BigDecimal netAssetValue;

    /**
     * 公布日增长率
     */
    @Excel(name = "公布日增长率")
    private String dailyGrowthRate;

    /**
     * 估算偏差
     */
    @Excel(name = "估算偏差")
    private String estimatedDeviation;

    /**
     * 单位净值
     */
    @Excel(name = "单位净值")
    private BigDecimal currentNav;

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
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedDate;

}
