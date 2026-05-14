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
 * 东方财富板块资金流向对象 ths_board_fund_flow
 *
 * @author DXR
 * @date 2025-08-23
 */
@Data
@TableName("ths_board_fund_flow")
public class ThsBoardFundFlow {

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
    private Long fundFlowBoardId;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private Long serialNo;

    /**
     * 股票代码
     */
    @Excel(name = "股票代码")
    private String boardCode;

    /**
     * 股票名称
     */
    @Excel(name = "股票名称")
    private String boardName;

    /**
     * 涨跌幅（单位:%）
     */
    @Excel(name = "涨跌幅", readConverterExp = "单=位:%")
    private BigDecimal changePercent;

    /**
     * 主力净流入-净额（单位:元）
     */
    @Excel(name = "主力净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal mainstayInflowNetAmount;

    /**
     * 主力净流入-净占比（单位:%）
     */
    @Excel(name = "主力净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal mainstayInflowNetPercent;

    /**
     * 超大单净流入-净额（单位:元）
     */
    @Excel(name = "超大单净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal superInflowNetAmount;

    /**
     * 超大单净流入-净占比（单位:%）
     */
    @Excel(name = "超大单净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal superInflowNetPercent;

    /**
     * 大单净流入-净额（单位:元）
     */
    @Excel(name = "大单净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal bigInflowNetAmount;

    /**
     * 大单净流入-净占比（单位:%）
     */
    @Excel(name = "大单净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal bigInflowNetPercent;

    /**
     * 中单净流入-净额（单位:元）
     */
    @Excel(name = "中单净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal midleInflowNetAmount;

    /**
     * 中单净流入-净占比（单位:%）
     */
    @Excel(name = "中单净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal midleInflowNetPercent;

    /**
     * 小单净流入-净额（单位:元）
     */
    @Excel(name = "小单净流入-净额", readConverterExp = "单=位:元")
    private BigDecimal smallInflowNetAmount;

    /**
     * 小单净流入-净占比（单位:%）
     */
    @Excel(name = "小单净流入-净占比", readConverterExp = "单=位:%")
    private BigDecimal smallInflowNetPercent;

    /**
     * 主力净流入-最大股
     */
    @Excel(name = "主力净流入-最大股")
    private String mainstayInflowMaxStock;

    /**
     * 类型，今日，5日，10日
     */
    @Excel(name = "类型，今日，5日，10日")
    private String type;

    /**
     * 资金类型，行业资金流，概念资金流, 地域资金流
     */
    @Excel(name = "资金类型，行业资金流，概念资金流, 地域资金流")
    private String sectorType;

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
