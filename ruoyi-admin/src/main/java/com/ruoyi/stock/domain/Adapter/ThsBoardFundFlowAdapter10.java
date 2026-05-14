package com.ruoyi.stock.domain.Adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 东方财富板块资金流向对象转换对象 ths_board_fund_flow
 *
 * @author DXR
 * @date 2025-08-23
 */
@Data
public class ThsBoardFundFlowAdapter10 {

    /**
     * 序号
     */
    @JsonProperty("序号")
    private Long serialNo;

    /**
     * 板块代码
     */
    private String boardCode;

    /**
     * 板块名称
     */
    @JsonProperty("名称")
    private String boardName;

    /**
     * 涨跌幅（单位:%）
     */
    @JsonProperty("10日涨跌幅")
    private BigDecimal changePercent;

    /**
     * 主力净流入-净额（单位:元）
     */
    @JsonProperty("10日主力净流入-净额")
    private BigDecimal mainstayInflowNetAmount;

    /**
     * 主力净流入-净占比（单位:%）
     */
    @JsonProperty("10日主力净流入-净占比")
    private BigDecimal mainstayInflowNetPercent;

    /**
     * 超大单净流入-净额（单位:元）
     */
    @JsonProperty("10日超大单净流入-净额")
    private BigDecimal superInflowNetAmount;

    /**
     * 超大单净流入-净占比（单位:%）
     */
    @JsonProperty("10日超大单净流入-净占比")
    private BigDecimal superInflowNetPercent;

    /**
     * 大单净流入-净额（单位:元）
     */
    @JsonProperty("10日大单净流入-净额")
    private BigDecimal bigInflowNetAmount;

    /**
     * 大单净流入-净占比（单位:%）
     */
    @JsonProperty("10日大单净流入-净占比")
    private BigDecimal bigInflowNetPercent;

    /**
     * 中单净流入-净额（单位:元）
     */
    @JsonProperty("10日中单净流入-净额")
    private BigDecimal midleInflowNetAmount;

    /**
     * 中单净流入-净占比（单位:%）
     */
    @JsonProperty("10日中单净流入-净占比")
    private BigDecimal midleInflowNetPercent;

    /**
     * 小单净流入-净额（单位:元）
     */
    @JsonProperty("10日小单净流入-净额")
    private BigDecimal smallInflowNetAmount;

    /**
     * 小单净流入-净占比（单位:%）
     */
    @JsonProperty("10日小单净流入-净占比")
    private BigDecimal smallInflowNetPercent;

    /**
     * 主力净流入-最大股
     */
    @JsonProperty("10日主力净流入最大股")
    private String mainstayInflowMaxStock;

    /**
     * 类型，今日，5日，10日
     */
    private String type;

    /**
     * 资金类型，行业资金流，概念资金流, 地域资金流
     */
    private String sectorType;
}
