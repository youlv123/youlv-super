package com.ruoyi.stock.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 同花顺个股资金流向对象 ths_stock_fund_flow
 *
 * @author DXR
 * @date 2025-08-20
 */
@Data
@TableName("ths_stock_fund_flow")
public class ThsStockFundFlow {
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
    private Long fundFlowIndividualId;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private Long serialNo;

    /**
     * 股票代码
     */
    @Excel(name = "股票代码")
    private String stockCode;

    /**
     * 股票名称
     */
    @Excel(name = "股票名称")
    private String stockName;

    /**
     * 最新价（单位：元）
     */
    @Excel(name = "最新价", readConverterExp = "单=位：元")
    private BigDecimal latestPrice;

    /**
     * 涨跌幅（单位:%）
     */
    @Excel(name = "涨跌幅", readConverterExp = "单=位:%")
    private BigDecimal changePercent;

    /**
     * 换手率（单位:%）
     */
    @Excel(name = "换手率", readConverterExp = "单=位:%")
    private BigDecimal turnoverRate;

    /**
     * 流入资金（单位:元）
     */
    @Excel(name = "流入资金", readConverterExp = "单=位:元")
    private BigDecimal inflowFunds;

    /**
     * 流出资金（单位:元）
     */
    @Excel(name = "流出资金", readConverterExp = "单=位:元")
    private BigDecimal outflowFunds;

    /**
     * 净额（单位:元）
     */
    @Excel(name = "净额", readConverterExp = "单=位:元")
    private BigDecimal netAmount;

    /**
     * 成交额（单位:元）
     */
    @Excel(name = "成交额", readConverterExp = "单=位:元")
    private BigDecimal turnover;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String type;

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
