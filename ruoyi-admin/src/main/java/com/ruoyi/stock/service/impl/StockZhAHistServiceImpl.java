package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockZtPoolDtgcEm;
import com.ruoyi.stock.mapper.StockZtPoolDtgcEmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockZhAHistMapper;
import com.ruoyi.stock.domain.StockZhAHist;
import com.ruoyi.stock.service.IStockZhAHistService;

/**
 * 东财历史行情数据Service业务层处理
 *
 * @author DXR
 * @date 2025-06-03
 */
@Service
public class StockZhAHistServiceImpl extends ServiceImpl<StockZhAHistMapper, StockZhAHist> implements IStockZhAHistService {
    @Autowired
    private StockZhAHistMapper stockZhAHistMapper;

    /**
     * 查询东财历史行情数据
     *
     * @param zhHistId 东财历史行情数据主键
     * @return 东财历史行情数据
     */
    @Override
    public StockZhAHist selectStockZhAHistByZhHistId(Long zhHistId) {
        return stockZhAHistMapper.selectStockZhAHistByZhHistId(zhHistId);
    }

    /**
     * 查询东财历史行情数据列表
     *
     * @param stockZhAHist 东财历史行情数据
     * @return 东财历史行情数据
     */
    @Override
    public List<StockZhAHist> selectStockZhAHistList(StockZhAHist stockZhAHist) {
        return stockZhAHistMapper.selectStockZhAHistList(stockZhAHist);
    }

    /**
     * 新增东财历史行情数据
     *
     * @param stockZhAHist 东财历史行情数据
     * @return 结果
     */
    @Override
    public int insertStockZhAHist(StockZhAHist stockZhAHist) {
        return stockZhAHistMapper.insertStockZhAHist(stockZhAHist);
    }

    /**
     * 修改东财历史行情数据
     *
     * @param stockZhAHist 东财历史行情数据
     * @return 结果
     */
    @Override
    public int updateStockZhAHist(StockZhAHist stockZhAHist) {
        return stockZhAHistMapper.updateStockZhAHist(stockZhAHist);
    }

    /**
     * 批量删除东财历史行情数据
     *
     * @param zhHistIds 需要删除的东财历史行情数据主键
     * @return 结果
     */
    @Override
    public int deleteStockZhAHistByZhHistIds(Long[] zhHistIds) {
        return stockZhAHistMapper.deleteStockZhAHistByZhHistIds(zhHistIds);
    }

    /**
     * 删除东财历史行情数据信息
     *
     * @param zhHistId 东财历史行情数据主键
     * @return 结果
     */
    @Override
    public int deleteStockZhAHistByZhHistId(Long zhHistId) {
        return stockZhAHistMapper.deleteStockZhAHistByZhHistId(zhHistId);
    }
}
