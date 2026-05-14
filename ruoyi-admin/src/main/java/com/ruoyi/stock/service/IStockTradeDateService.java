package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.BasicStockHis;
import com.ruoyi.stock.domain.StockTradeDate;

/**
 * 股票交易日历Service接口
 *
 * @author DXR
 * @date 2025-08-04
 */
public interface IStockTradeDateService extends IService<StockTradeDate> {
    /**
     * 查询股票交易日历
     *
     * @param tradeDateId 股票交易日历主键
     * @return 股票交易日历
     */
    public StockTradeDate selectStockTradeDateByTradeDateId(Long tradeDateId);

    /**
     * 查询股票交易日历列表
     *
     * @param stockTradeDate 股票交易日历
     * @return 股票交易日历集合
     */
    public List<StockTradeDate> selectStockTradeDateList(StockTradeDate stockTradeDate);

    /**
     * 新增股票交易日历
     *
     * @param stockTradeDate 股票交易日历
     * @return 结果
     */
    public int insertStockTradeDate(StockTradeDate stockTradeDate);

    /**
     * 修改股票交易日历
     *
     * @param stockTradeDate 股票交易日历
     * @return 结果
     */
    public int updateStockTradeDate(StockTradeDate stockTradeDate);

    /**
     * 批量删除股票交易日历
     *
     * @param tradeDateIds 需要删除的股票交易日历主键集合
     * @return 结果
     */
    public int deleteStockTradeDateByTradeDateIds(Long[] tradeDateIds);

    /**
     * 删除股票交易日历信息
     *
     * @param tradeDateId 股票交易日历主键
     * @return 结果
     */
    public int deleteStockTradeDateByTradeDateId(Long tradeDateId);


    /**
     * 查询是否是交易日
     */
    public boolean isTradeDate(String tradeDate);

}
