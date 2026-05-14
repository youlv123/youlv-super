package com.ruoyi.stock.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 板块成分股对象 stock_board_cons_em
 *
 * @author DXR
 * @date 2025-06-15
 */
@Data
@TableName("stock_board_cons_em")
public class StockBoardConsEm {


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
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long boardConsId;

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
     * 所属板块代码
     */
    private String boardCode;

    /**
     * 所属板块名称
     */
    private String boardName;

    /**
     * 最新价（单位：元）
     */
    private String latestPrice;


    /**
     * 涨跌幅（单位：%）
     */
    private String changePercent;

    /**
     * 涨跌额（单位:元）
     */
    private String priceRiseFall;

    /**
     * 成交量（单位:手）
     */
    private String volume;


    /**
     * 成交额（单位：元）
     */
    private String turnover;

    /**
     * 振幅
     */
    private String amplitude;

    /**
     * 最高价
     */
    private String highestPrice;

    /**
     * 最低价
     */
    private String lowestPrice;

    /**
     * 今日开盘价
     */
    private String todayOpenPrice;

    /**
     * 昨日收盘价
     */
    private String yesterdayClosingPrice;

    /**
     * 换手率（单位：%）
     */
    private String turnoverRate;

    /**
     * 动态市盈率
     */
    private String dynamicPriceRatio;

    /**
     * 市净率
     */
    private String priceToBookRatio;


    private String createdBy;

    private Date createdDate;


    private String updatedBy;


    private Date updatedDate;

}
