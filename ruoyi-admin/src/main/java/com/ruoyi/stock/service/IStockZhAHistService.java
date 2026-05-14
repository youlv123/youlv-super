package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockZhAHist;
import com.ruoyi.stock.domain.StockZtPoolDtgcEm;

/**
 * 东财历史行情数据Service接口
 *
 * @author DXR
 * @date 2025-06-03
 */
public interface IStockZhAHistService extends IService<StockZhAHist> {
    /**
     * 查询东财历史行情数据
     *
     * @param zhHistId 东财历史行情数据主键
     * @return 东财历史行情数据
     */
    public StockZhAHist selectStockZhAHistByZhHistId(Long zhHistId);

    /**
     * 查询东财历史行情数据列表
     *
     * @param stockZhAHist 东财历史行情数据
     * @return 东财历史行情数据集合
     */
    public List<StockZhAHist> selectStockZhAHistList(StockZhAHist stockZhAHist);

    /**
     * 新增东财历史行情数据
     *
     * @param stockZhAHist 东财历史行情数据
     * @return 结果
     */
    public int insertStockZhAHist(StockZhAHist stockZhAHist);

    /**
     * 修改东财历史行情数据
     *
     * @param stockZhAHist 东财历史行情数据
     * @return 结果
     */
    public int updateStockZhAHist(StockZhAHist stockZhAHist);

    /**
     * 批量删除东财历史行情数据
     *
     * @param zhHistIds 需要删除的东财历史行情数据主键集合
     * @return 结果
     */
    public int deleteStockZhAHistByZhHistIds(Long[] zhHistIds);

    /**
     * 删除东财历史行情数据信息
     *
     * @param zhHistId 东财历史行情数据主键
     * @return 结果
     */
    public int deleteStockZhAHistByZhHistId(Long zhHistId);
}
