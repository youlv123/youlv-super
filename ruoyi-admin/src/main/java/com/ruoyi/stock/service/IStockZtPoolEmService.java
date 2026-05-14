package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockZtPoolEm;

/**
 * 涨停板行情Service接口
 *
 * @author DXR
 * @date 2025-05-09
 */
public interface IStockZtPoolEmService extends IService<StockZtPoolEm> {
    /**
     * 查询涨停板行情
     *
     * @param ztId 涨停板行情主键
     * @return 涨停板行情
     */
    public StockZtPoolEm selectStockZtPoolEmByZtId(Long ztId);

    /**
     * 查询涨停板行情列表
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 涨停板行情集合
     */
    public List<StockZtPoolEm> selectStockZtPoolEmList(StockZtPoolEm stockZtPoolEm);

    /**
     * 新增涨停板行情
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 结果
     */
    public int insertStockZtPoolEm(StockZtPoolEm stockZtPoolEm);

    /**
     * 修改涨停板行情
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 结果
     */
    public int updateStockZtPoolEm(StockZtPoolEm stockZtPoolEm);

    /**
     * 批量删除涨停板行情
     *
     * @param ztIds 需要删除的涨停板行情主键集合
     * @return 结果
     */
    public int deleteStockZtPoolEmByZtIds(Long[] ztIds);

    /**
     * 删除涨停板行情信息
     *
     * @param ztId 涨停板行情主键
     * @return 结果
     */
    public int deleteStockZtPoolEmByZtId(Long ztId);
}
