package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockZtPoolDtgcEm;
import com.ruoyi.stock.domain.StockZtPoolEm;

/**
 * 跌停股池Service接口
 *
 * @author DXR
 * @date 2025-05-25
 */
public interface IStockZtPoolDtgcEmService extends IService<StockZtPoolDtgcEm> {
    /**
     * 查询跌停股池
     *
     * @param dtgcId 跌停股池主键
     * @return 跌停股池
     */
    public StockZtPoolDtgcEm selectStockZtPoolDtgcEmByDtgcId(Long dtgcId);

    /**
     * 查询跌停股池列表
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 跌停股池集合
     */
    public List<StockZtPoolDtgcEm> selectStockZtPoolDtgcEmList(StockZtPoolDtgcEm stockZtPoolDtgcEm);

    /**
     * 新增跌停股池
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 结果
     */
    public int insertStockZtPoolDtgcEm(StockZtPoolDtgcEm stockZtPoolDtgcEm);

    /**
     * 修改跌停股池
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 结果
     */
    public int updateStockZtPoolDtgcEm(StockZtPoolDtgcEm stockZtPoolDtgcEm);

    /**
     * 批量删除跌停股池
     *
     * @param dtgcIds 需要删除的跌停股池主键集合
     * @return 结果
     */
    public int deleteStockZtPoolDtgcEmByDtgcIds(Long[] dtgcIds);

    /**
     * 删除跌停股池信息
     *
     * @param dtgcId 跌停股池主键
     * @return 结果
     */
    public int deleteStockZtPoolDtgcEmByDtgcId(Long dtgcId);
}
