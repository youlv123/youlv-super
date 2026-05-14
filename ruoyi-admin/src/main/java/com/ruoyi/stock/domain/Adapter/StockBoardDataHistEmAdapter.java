package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 板块数据历史转换对象 stock_board_data_hist_em
 *
 * @author DXR
 * @date 2025-08-17
 */
@Data
public class StockBoardDataHistEmAdapter {

    /**
     * 板块代码
     */
    private String boardCode;

    /**
     * 板块名称
     */
    private String boardName;

    /**
     * 板块类型，概念板块-行业板块
     */
    private String boardType;

    /**
     * 开盘价
     */
    @JsonProperty("开盘")
    private String openPrice;

    /**
     * 收盘价
     */
    @JsonProperty("收盘")
    private String closingPrice;

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

    /**
     * 涨跌幅（单位:%）
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
     * 成交额（单位:元）
     */
    @JsonProperty("成交额")
    private Long turnover;

    /**
     * 振幅（单位:%）
     */
    @JsonProperty("振幅")
    private String amplitude;

    /**
     * 换手率（单位:%）
     */
    @JsonProperty("换手率")
    private String turnoverRate;

    /**
     * 周期-日-周-月
     */
    private String period;

    /**
     * 交易日
     */
    @JsonProperty("日期")
    private String bizDate;


}
