package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockZtPoolEm;
import com.ruoyi.stock.mapper.StockZtPoolEmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockZtPoolStrongEmMapper;
import com.ruoyi.stock.domain.StockZtPoolStrongEm;
import com.ruoyi.stock.service.IStockZtPoolStrongEmService;

/**
 * 强势股池Service业务层处理
 *
 * @author DXR
 * @date 2025-05-20
 */
@Service
public class StockZtPoolStrongEmServiceImpl extends ServiceImpl<StockZtPoolStrongEmMapper, StockZtPoolStrongEm>  implements IStockZtPoolStrongEmService {
    @Autowired
    private StockZtPoolStrongEmMapper stockZtPoolStrongEmMapper;

    /**
     * 查询强势股池
     *
     * @param ztsId 强势股池主键
     * @return 强势股池
     */
    @Override
    public StockZtPoolStrongEm selectStockZtPoolStrongEmByZtsId(Long ztsId) {
        return stockZtPoolStrongEmMapper.selectStockZtPoolStrongEmByZtsId(ztsId);
    }

    /**
     * 查询强势股池列表
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 强势股池
     */
    @Override
    public List<StockZtPoolStrongEm> selectStockZtPoolStrongEmList(StockZtPoolStrongEm stockZtPoolStrongEm) {
        return stockZtPoolStrongEmMapper.selectStockZtPoolStrongEmList(stockZtPoolStrongEm);
    }

    /**
     * 新增强势股池
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 结果
     */
    @Override
    public int insertStockZtPoolStrongEm(StockZtPoolStrongEm stockZtPoolStrongEm) {
        return stockZtPoolStrongEmMapper.insertStockZtPoolStrongEm(stockZtPoolStrongEm);
    }

    /**
     * 修改强势股池
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 结果
     */
    @Override
    public int updateStockZtPoolStrongEm(StockZtPoolStrongEm stockZtPoolStrongEm) {
        return stockZtPoolStrongEmMapper.updateStockZtPoolStrongEm(stockZtPoolStrongEm);
    }

    /**
     * 批量删除强势股池
     *
     * @param ztsIds 需要删除的强势股池主键
     * @return 结果
     */
    @Override
    public int deleteStockZtPoolStrongEmByZtsIds(Long[] ztsIds) {
        return stockZtPoolStrongEmMapper.deleteStockZtPoolStrongEmByZtsIds(ztsIds);
    }

    /**
     * 删除强势股池信息
     *
     * @param ztsId 强势股池主键
     * @return 结果
     */
    @Override
    public int deleteStockZtPoolStrongEmByZtsId(Long ztsId) {
        return stockZtPoolStrongEmMapper.deleteStockZtPoolStrongEmByZtsId(ztsId);
    }
}
