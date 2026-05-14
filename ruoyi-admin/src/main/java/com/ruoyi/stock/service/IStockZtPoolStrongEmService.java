package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockZtPoolEm;
import com.ruoyi.stock.domain.StockZtPoolStrongEm;

/**
 * 强势股池Service接口
 *
 * @author DXR
 * @date 2025-05-20
 */
public interface IStockZtPoolStrongEmService extends IService<StockZtPoolStrongEm> {
    /**
     * 查询强势股池
     *
     * @param ztsId 强势股池主键
     * @return 强势股池
     */
    public StockZtPoolStrongEm selectStockZtPoolStrongEmByZtsId(Long ztsId);

    /**
     * 查询强势股池列表
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 强势股池集合
     */
    public List<StockZtPoolStrongEm> selectStockZtPoolStrongEmList(StockZtPoolStrongEm stockZtPoolStrongEm);

    /**
     * 新增强势股池
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 结果
     */
    public int insertStockZtPoolStrongEm(StockZtPoolStrongEm stockZtPoolStrongEm);

    /**
     * 修改强势股池
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 结果
     */
    public int updateStockZtPoolStrongEm(StockZtPoolStrongEm stockZtPoolStrongEm);

    /**
     * 批量删除强势股池
     *
     * @param ztsIds 需要删除的强势股池主键集合
     * @return 结果
     */
    public int deleteStockZtPoolStrongEmByZtsIds(Long[] ztsIds);

    /**
     * 删除强势股池信息
     *
     * @param ztsId 强势股池主键
     * @return 结果
     */
    public int deleteStockZtPoolStrongEmByZtsId(Long ztsId);
}
