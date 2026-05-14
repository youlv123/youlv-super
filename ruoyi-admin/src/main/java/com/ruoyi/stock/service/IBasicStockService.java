package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.BasicStock;
import com.ruoyi.stock.domain.StockBoardConsEmHis;

/**
 * 基础股票Service接口
 *
 * @author DXR
 * @date 2025-07-31
 */
public interface IBasicStockService extends IService<BasicStock> {
    /**
     * 查询基础股票
     *
     * @param basicStockId 基础股票主键
     * @return 基础股票
     */
    public BasicStock selectBasicStockByBasicStockId(Long basicStockId);

    /**
     * 查询基础股票列表
     *
     * @param basicStock 基础股票
     * @return 基础股票集合
     */
    public List<BasicStock> selectBasicStockList(BasicStock basicStock);

    /**
     * 新增基础股票
     *
     * @param basicStock 基础股票
     * @return 结果
     */
    public int insertBasicStock(BasicStock basicStock);

    /**
     * 修改基础股票
     *
     * @param basicStock 基础股票
     * @return 结果
     */
    public int updateBasicStock(BasicStock basicStock);

    /**
     * 批量删除基础股票
     *
     * @param basicStockIds 需要删除的基础股票主键集合
     * @return 结果
     */
    public int deleteBasicStockByBasicStockIds(Long[] basicStockIds);

    /**
     * 删除基础股票信息
     *
     * @param basicStockId 基础股票主键
     * @return 结果
     */
    public int deleteBasicStockByBasicStockId(Long basicStockId);
}
