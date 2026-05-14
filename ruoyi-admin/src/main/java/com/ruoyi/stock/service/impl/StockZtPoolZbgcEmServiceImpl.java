package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockZtPoolStrongEm;
import com.ruoyi.stock.mapper.StockZtPoolStrongEmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockZtPoolZbgcEmMapper;
import com.ruoyi.stock.domain.StockZtPoolZbgcEm;
import com.ruoyi.stock.service.IStockZtPoolZbgcEmService;

/**
 * 炸板股池Service业务层处理
 *
 * @author DXR
 * @date 2025-05-21
 */
@Service
public class StockZtPoolZbgcEmServiceImpl extends ServiceImpl<StockZtPoolZbgcEmMapper, StockZtPoolZbgcEm> implements IStockZtPoolZbgcEmService {
    @Autowired
    private StockZtPoolZbgcEmMapper stockZtPoolZbgcEmMapper;

    /**
     * 查询炸板股池
     *
     * @param zbgcId 炸板股池主键
     * @return 炸板股池
     */
    @Override
    public StockZtPoolZbgcEm selectStockZtPoolZbgcEmByZbgcId(Long zbgcId) {
        return stockZtPoolZbgcEmMapper.selectStockZtPoolZbgcEmByZbgcId(zbgcId);
    }

    /**
     * 查询炸板股池列表
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 炸板股池
     */
    @Override
    public List<StockZtPoolZbgcEm> selectStockZtPoolZbgcEmList(StockZtPoolZbgcEm stockZtPoolZbgcEm) {
        return stockZtPoolZbgcEmMapper.selectStockZtPoolZbgcEmList(stockZtPoolZbgcEm);
    }

    /**
     * 新增炸板股池
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 结果
     */
    @Override
    public int insertStockZtPoolZbgcEm(StockZtPoolZbgcEm stockZtPoolZbgcEm) {
        return stockZtPoolZbgcEmMapper.insertStockZtPoolZbgcEm(stockZtPoolZbgcEm);
    }

    /**
     * 修改炸板股池
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 结果
     */
    @Override
    public int updateStockZtPoolZbgcEm(StockZtPoolZbgcEm stockZtPoolZbgcEm) {
        return stockZtPoolZbgcEmMapper.updateStockZtPoolZbgcEm(stockZtPoolZbgcEm);
    }

    /**
     * 批量删除炸板股池
     *
     * @param zbgcIds 需要删除的炸板股池主键
     * @return 结果
     */
    @Override
    public int deleteStockZtPoolZbgcEmByZbgcIds(Long[] zbgcIds) {
        return stockZtPoolZbgcEmMapper.deleteStockZtPoolZbgcEmByZbgcIds(zbgcIds);
    }

    /**
     * 删除炸板股池信息
     *
     * @param zbgcId 炸板股池主键
     * @return 结果
     */
    @Override
    public int deleteStockZtPoolZbgcEmByZbgcId(Long zbgcId) {
        return stockZtPoolZbgcEmMapper.deleteStockZtPoolZbgcEmByZbgcId(zbgcId);
    }
}
