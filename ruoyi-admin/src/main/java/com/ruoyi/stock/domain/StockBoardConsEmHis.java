package com.ruoyi.stock.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 板块成分股历史对象 stock_board_cons_em_his
 *
 * @author DXR
 * @date 2025-07-23
 */
@Data
@TableName("stock_board_cons_em_his")
public class StockBoardConsEmHis {


    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long hisBoardConsId;

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
     * 所属板块代码
     */
    @Excel(name = "所属板块代码")
    private String boardCode;

    /**
     * 所属板块名称
     */
    @Excel(name = "所属板块名称")
    private String boardName;

    /**
     * 最新价（单位：元）
     */
    @Excel(name = "最新价", readConverterExp = "单=位：元")
    private String latestPrice;

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
     * 今日开盘价
     */
    @Excel(name = "今日开盘价")
    private String todayOpenPrice;

    /**
     * 昨日收盘价
     */
    @Excel(name = "昨日收盘价")
    private String yesterdayClosingPrice;

    /**
     * 换手率（单位:%）
     */
    @Excel(name = "换手率", readConverterExp = "单=位:%")
    private String turnoverRate;

    /**
     * 市盈率-动态
     */
    @Excel(name = "市盈率-动态")
    private String dynamicPriceRatio;

    /**
     * 市净率
     */
    @Excel(name = "市净率")
    private String priceToBookRatio;

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
