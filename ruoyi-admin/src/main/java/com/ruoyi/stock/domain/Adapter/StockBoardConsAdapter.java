package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 板块成分股转换对象 stock_board_cons_em
 *
 * @author DXR
 * @date 2025-06-15
 */
@Data
public class StockBoardConsAdapter {


    /**
     * 序号
     */
    @JsonProperty("序号")
    private Long serialNo;

    /**
     * 股票代码
     */
    @JsonProperty("代码")
    private String stockCode;

    /**
     * 股票名称
     */
    @JsonProperty("名称")
    private String stockName;

    /** 所属板块代码 */
    private String boardCode;

    /** 所属板块名称 */
    private String boardName;

    /**
     * 最新价（单位：元）
     */
    @JsonProperty("最新价")
    private String latestPrice;

    /**
     * 涨跌幅（单位：%）
     */
    @JsonProperty("涨跌幅")
    private String changePercent;

    /**
     * 涨跌额（单位:元）
     */
    @JsonProperty("涨跌额")
    private String priceRiseFall;

    /**
     * 成交量（单位:手）
     */
    @JsonProperty("成交量")
    private String volume;

    /**
     * 成交额（单位：元）
     */
    @JsonProperty("成交额")
    private String turnover;

    /**
     * 振幅
     */
    @JsonProperty("振幅")
    private String amplitude;

    /**
     * 最高价
     */
    @JsonProperty("最高")
    private String highestPrice;

    /**
     * 最低价
     */
    @JsonProperty("最低")
    private String lowestPrice;

    @JsonProperty("今开")
    private String todayOpenPrice;

    @JsonProperty("昨收")
    private String yesterdayClosingPrice;

    /**
     * 换手率（单位：%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 动态市盈率
     */
    @JsonProperty("市盈率-动态")
    private String dynamicPriceRatio;

    /**
     * 市净率
     */
    @JsonProperty("市净率")
    private String priceToBookRatio;


}
