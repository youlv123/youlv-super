package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockZtPoolEm;
import com.ruoyi.stock.mapper.StockZtPoolEmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockZtPoolDtgcEmMapper;
import com.ruoyi.stock.domain.StockZtPoolDtgcEm;
import com.ruoyi.stock.service.IStockZtPoolDtgcEmService;

/**
 * 跌停股池Service业务层处理
 *
 * @author DXR
 * @date 2025-05-25
 */
@Service
public class StockZtPoolDtgcEmServiceImpl extends ServiceImpl<StockZtPoolDtgcEmMapper, StockZtPoolDtgcEm>  implements IStockZtPoolDtgcEmService {
    @Autowired
    private StockZtPoolDtgcEmMapper stockZtPoolDtgcEmMapper;

    /**
     * 查询跌停股池
     *
     * @param dtgcId 跌停股池主键
     * @return 跌停股池
     */
    @Override
    public StockZtPoolDtgcEm selectStockZtPoolDtgcEmByDtgcId(Long dtgcId) {
        return stockZtPoolDtgcEmMapper.selectStockZtPoolDtgcEmByDtgcId(dtgcId);
    }

    /**
     * 查询跌停股池列表
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 跌停股池
     */
    @Override
    public List<StockZtPoolDtgcEm> selectStockZtPoolDtgcEmList(StockZtPoolDtgcEm stockZtPoolDtgcEm) {
        return stockZtPoolDtgcEmMapper.selectStockZtPoolDtgcEmList(stockZtPoolDtgcEm);
    }

    /**
     * 新增跌停股池
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 结果
     */
    @Override
    public int insertStockZtPoolDtgcEm(StockZtPoolDtgcEm stockZtPoolDtgcEm) {
        return stockZtPoolDtgcEmMapper.insertStockZtPoolDtgcEm(stockZtPoolDtgcEm);
    }

    /**
     * 修改跌停股池
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 结果
     */
    @Override
    public int updateStockZtPoolDtgcEm(StockZtPoolDtgcEm stockZtPoolDtgcEm) {
        return stockZtPoolDtgcEmMapper.updateStockZtPoolDtgcEm(stockZtPoolDtgcEm);
    }

    /**
     * 批量删除跌停股池
     *
     * @param dtgcIds 需要删除的跌停股池主键
     * @return 结果
     */
    @Override
    public int deleteStockZtPoolDtgcEmByDtgcIds(Long[] dtgcIds) {
        return stockZtPoolDtgcEmMapper.deleteStockZtPoolDtgcEmByDtgcIds(dtgcIds);
    }

    /**
     * 删除跌停股池信息
     *
     * @param dtgcId 跌停股池主键
     * @return 结果
     */
    @Override
    public int deleteStockZtPoolDtgcEmByDtgcId(Long dtgcId) {
        return stockZtPoolDtgcEmMapper.deleteStockZtPoolDtgcEmByDtgcId(dtgcId);
    }
}
