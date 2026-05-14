package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 天天基金-开放式基金-实时数据对象 fund_open_fund_daily_em
 *
 * @author DXR
 * @date 2026-01-09
 */
@Data
public class FundOpenFundDailyEmAdapter {


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
     * 当前-单位净值
     */
    @Excel(name = "当前-单位净值")
    private BigDecimal currentNav;

    /**
     * 当前-累计净值
     */
    @Excel(name = "当前-累计净值")
    private BigDecimal currentCumulativeNav;

    /**
     * 前一日-单位净值
     */
    @Excel(name = "前一日-单位净值")
    private BigDecimal previousNav;

    /**
     * 前一日-累计净值
     */
    @Excel(name = "前一日-累计净值")
    private BigDecimal previousCumulativeNav;

    /**
     * 增长值
     */
    @Excel(name = "增长值")
    private String growthValue;

    /**
     * 增长率
     */
    @Excel(name = "增长率")
    private String growthRate;

    /**
     * 赎回状态
     */
    @Excel(name = "赎回状态")
    private String redemptionStatus;

    /**
     * 申购状态
     */
    @Excel(name = "申购状态")
    private String subscriptionStatus;

    /**
     * 手续费
     */
    @Excel(name = "手续费")
    private String commission;

    /**
     * 交易日
     */
    @Excel(name = "交易日")
    private String bizDate;


}
