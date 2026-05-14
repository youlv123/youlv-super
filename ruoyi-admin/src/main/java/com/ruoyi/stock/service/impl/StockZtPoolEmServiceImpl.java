package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockZtPoolEmMapper;
import com.ruoyi.stock.domain.StockZtPoolEm;
import com.ruoyi.stock.service.IStockZtPoolEmService;

/**
 * 涨停板行情Service业务层处理
 *
 * @author DXR
 * @date 2025-05-09
 */
@Service
public class StockZtPoolEmServiceImpl extends ServiceImpl<StockZtPoolEmMapper,StockZtPoolEm> implements IStockZtPoolEmService {
    @Autowired
    private StockZtPoolEmMapper stockZtPoolEmMapper;

    /**
     * 查询涨停板行情
     *
     * @param ztId 涨停板行情主键
     * @return 涨停板行情
     */
    @Override
    public StockZtPoolEm selectStockZtPoolEmByZtId(Long ztId) {
        return stockZtPoolEmMapper.selectStockZtPoolEmByZtId(ztId);
    }

    /**
     * 查询涨停板行情列表
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 涨停板行情
     */
    @Override
    public List<StockZtPoolEm> selectStockZtPoolEmList(StockZtPoolEm stockZtPoolEm) {
        return stockZtPoolEmMapper.selectStockZtPoolEmList(stockZtPoolEm);
    }

    /**
     * 新增涨停板行情
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 结果
     */
    @Override
    public int insertStockZtPoolEm(StockZtPoolEm stockZtPoolEm) {
        return stockZtPoolEmMapper.insertStockZtPoolEm(stockZtPoolEm);
    }

    /**
     * 修改涨停板行情
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 结果
     */
    @Override
    public int updateStockZtPoolEm(StockZtPoolEm stockZtPoolEm) {
        return stockZtPoolEmMapper.updateStockZtPoolEm(stockZtPoolEm);
    }

    /**
     * 批量删除涨停板行情
     *
     * @param ztIds 需要删除的涨停板行情主键
     * @return 结果
     */
    @Override
    public int deleteStockZtPoolEmByZtIds(Long[] ztIds) {
        return stockZtPoolEmMapper.deleteStockZtPoolEmByZtIds(ztIds);
    }

    /**
     * 删除涨停板行情信息
     *
     * @param ztId 涨停板行情主键
     * @return 结果
     */
    @Override
    public int deleteStockZtPoolEmByZtId(Long ztId) {
        return stockZtPoolEmMapper.deleteStockZtPoolEmByZtId(ztId);
    }
}
