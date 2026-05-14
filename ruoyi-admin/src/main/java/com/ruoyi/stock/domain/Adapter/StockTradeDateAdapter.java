package com.ruoyi.stock.domain.Adapter;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 股票交易日历对象 stock_trade_date
 *
 * @author DXR
 * @date 2025-08-04
 */
@Data
@TableName("stock_trade_date")
public class StockTradeDateAdapter {


    /**
     * 交易日
     */
    @JsonProperty("trade_date")
    private String tradeDate;


}
