package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.stock.domain.BasicStock;
import com.ruoyi.stock.domain.StockBoard;
import com.ruoyi.stock.mapper.StockBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockTradeDateMapper;
import com.ruoyi.stock.domain.StockTradeDate;
import com.ruoyi.stock.service.IStockTradeDateService;

/**
 * 股票交易日历Service业务层处理
 *
 * @author DXR
 * @date 2025-08-04
 */
@Service
public class StockTradeDateServiceImpl extends ServiceImpl<StockTradeDateMapper, StockTradeDate> implements IStockTradeDateService {
    @Autowired
    private StockTradeDateMapper stockTradeDateMapper;

    /**
     * 查询股票交易日历
     *
     * @param tradeDateId 股票交易日历主键
     * @return 股票交易日历
     */
    @Override
    public StockTradeDate selectStockTradeDateByTradeDateId(Long tradeDateId) {
        return stockTradeDateMapper.selectStockTradeDateByTradeDateId(tradeDateId);
    }

    /**
     * 查询股票交易日历列表
     *
     * @param stockTradeDate 股票交易日历
     * @return 股票交易日历
     */
    @Override
    public List<StockTradeDate> selectStockTradeDateList(StockTradeDate stockTradeDate) {
        return stockTradeDateMapper.selectStockTradeDateList(stockTradeDate);
    }

    /**
     * 新增股票交易日历
     *
     * @param stockTradeDate 股票交易日历
     * @return 结果
     */
    @Override
    public int insertStockTradeDate(StockTradeDate stockTradeDate) {
        return stockTradeDateMapper.insertStockTradeDate(stockTradeDate);
    }

    /**
     * 修改股票交易日历
     *
     * @param stockTradeDate 股票交易日历
     * @return 结果
     */
    @Override
    public int updateStockTradeDate(StockTradeDate stockTradeDate) {
        return stockTradeDateMapper.updateStockTradeDate(stockTradeDate);
    }

    /**
     * 批量删除股票交易日历
     *
     * @param tradeDateIds 需要删除的股票交易日历主键
     * @return 结果
     */
    @Override
    public int deleteStockTradeDateByTradeDateIds(Long[] tradeDateIds) {
        return stockTradeDateMapper.deleteStockTradeDateByTradeDateIds(tradeDateIds);
    }

    /**
     * 删除股票交易日历信息
     *
     * @param tradeDateId 股票交易日历主键
     * @return 结果
     */
    @Override
    public int deleteStockTradeDateByTradeDateId(Long tradeDateId) {
        return stockTradeDateMapper.deleteStockTradeDateByTradeDateId(tradeDateId);
    }

    /**
     * 判断是否是交易日
     *
     * @param tradeDate 交易日
     * @return
     */
    @Override
    public boolean isTradeDate(String tradeDate) {
        LambdaQueryWrapper<StockTradeDate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StockTradeDate::getTradeDate, tradeDate);
        StockTradeDate stockTradeDate = stockTradeDateMapper.selectOne(queryWrapper);
        return stockTradeDate != null;
    }

}
