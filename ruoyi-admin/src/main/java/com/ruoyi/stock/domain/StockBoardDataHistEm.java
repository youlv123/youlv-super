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
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 板块数据历史对象 stock_board_data_hist_em
 *
 * @author DXR
 * @date 2025-08-17
 */
@Data
@TableName("stock_board_data_hist_em")
public class StockBoardDataHistEm  {


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
    private Long boardDataHistId;

    /**
     * 板块代码
     */
    @Excel(name = "板块代码")
    private String boardCode;

    /**
     * 板块名称
     */
    @Excel(name = "板块名称")
    private String boardName;

    /**
     * 板块类型，概念板块-行业板块
     */
    @Excel(name = "板块类型，概念板块-行业板块")
    private String boardType;

    /**
     * 开盘价
     */
    @Excel(name = "开盘价")
    private String openPrice;

    /**
     * 收盘价
     */
    @Excel(name = "收盘价")
    private String closingPrice;

    /**
     * 最高价
     */
    @Excel(name = "最高价")
    private String highestPrice;

    /**
     * 最低价
     */
    @Excel(name = "最低价")
    private String lowestPrice;

    /**
     * 涨跌幅（单位:%）
     */
    @Excel(name = "涨跌幅", readConverterExp = "单=位:%")
    private String changePercent;

    /**
     * 涨跌额（单位:元）
     */
    @Excel(name = "涨跌额", readConverterExp = "单=位:元")
    private String priceRiseFall;

    /**
     * 成交量（单位:手）
     */
    @Excel(name = "成交量", readConverterExp = "单=位:手")
    private String volume;

    /**
     * 成交额（单位:元）
     */
    @Excel(name = "成交额", readConverterExp = "单=位:元")
    private Long turnover;

    /**
     * 振幅（单位:%）
     */
    @Excel(name = "振幅", readConverterExp = "单=位:%")
    private String amplitude;

    /**
     * 换手率（单位:%）
     */
    @Excel(name = "换手率", readConverterExp = "单=位:%")
    private String turnoverRate;

    /**
     * 周期-日-周-月
     */
    @Excel(name = "周期-日-周-月")
    private String period;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;


    @TableField(exist = false)
    private String bizDateStart;
    @TableField(exist = false)
    private String bizDateEnd;
    @TableField(exist = false)
    private String type;

}
