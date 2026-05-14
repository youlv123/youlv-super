package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockZtPoolStrongEm;
import com.ruoyi.stock.domain.StockZtPoolZbgcEm;

/**
 * 炸板股池Service接口
 *
 * @author DXR
 * @date 2025-05-21
 */
public interface IStockZtPoolZbgcEmService extends IService<StockZtPoolZbgcEm> {
    /**
     * 查询炸板股池
     *
     * @param zbgcId 炸板股池主键
     * @return 炸板股池
     */
    public StockZtPoolZbgcEm selectStockZtPoolZbgcEmByZbgcId(Long zbgcId);

    /**
     * 查询炸板股池列表
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 炸板股池集合
     */
    public List<StockZtPoolZbgcEm> selectStockZtPoolZbgcEmList(StockZtPoolZbgcEm stockZtPoolZbgcEm);

    /**
     * 新增炸板股池
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 结果
     */
    public int insertStockZtPoolZbgcEm(StockZtPoolZbgcEm stockZtPoolZbgcEm);

    /**
     * 修改炸板股池
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 结果
     */
    public int updateStockZtPoolZbgcEm(StockZtPoolZbgcEm stockZtPoolZbgcEm);

    /**
     * 批量删除炸板股池
     *
     * @param zbgcIds 需要删除的炸板股池主键集合
     * @return 结果
     */
    public int deleteStockZtPoolZbgcEmByZbgcIds(Long[] zbgcIds);

    /**
     * 删除炸板股池信息
     *
     * @param zbgcId 炸板股池主键
     * @return 结果
     */
    public int deleteStockZtPoolZbgcEmByZbgcId(Long zbgcId);
}
