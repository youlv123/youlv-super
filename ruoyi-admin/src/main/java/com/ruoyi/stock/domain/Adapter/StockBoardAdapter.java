package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 板块对象转换 stock_board
 *
 * @author DXR
 * @date 2025-06-08
 */
@Data
public class StockBoardAdapter {


    /**
     * 排名
     */
    @JsonProperty("排名")
    private int serialNo;

    /**
     * 板块代码
     */
    @JsonProperty("板块代码")
    private String boardCode;

    /**
     * 板块名称
     */
    @JsonProperty("板块名称")
    private String boardName;

    /**
     * 最新价（单位：元）
     */
    @JsonProperty("最新价")
    private String latestPrice;

    /**
     * 涨跌额（单位:元）
     */
    @JsonProperty("涨跌额")
    private String priceRiseFall;

    /** 板块类型 */
    private String boardType;

    /** 数据来源 */
    private String dataSource;

    /**
     * 涨跌幅（单位:%）
     */
    @JsonProperty("涨跌幅")
    private String changePercent;

    /**
     * 总市值（单位：元）
     */
    @JsonProperty("总市值")
    private String totalMarketValue;

    /**
     * 换手率（单位:%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 上涨家数
     */
    @JsonProperty("上涨家数")
    private Long riseNum;

    /**
     * 下跌家数
     */
    @JsonProperty("下跌家数")
    private Long fallNum;

    /**
     * 领涨股票
     */
    @JsonProperty("领涨股票")
    private String leadingStocks;

    /**
     * 领涨股票-涨跌幅（单位:%）
     */
    @JsonProperty("领涨股票-涨跌幅")
    private String leadingStocksChangePercent;

    /**
     * 交易日
     */
    private String bizDate;


}
