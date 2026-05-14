package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 东方财富网-基金净值估算对象 fund_value_estimation_em
 *
 * @author DXR
 * @date 2026-01-09
 */
@Data
public class FundValueEstimationEmAdapter {


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

}
