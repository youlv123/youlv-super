package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.BasicStock;
import com.ruoyi.stock.domain.BasicStockHis;

/**
 * 基础股票历史Service接口
 *
 * @author ruoyi
 * @date 2025-08-03
 */
public interface IBasicStockHisService extends IService<BasicStockHis> {
    /**
     * 查询基础股票历史
     *
     * @param basicStockId 基础股票历史主键
     * @return 基础股票历史
     */
    public BasicStockHis selectBasicStockHisByBasicStockId(Long basicStockId);

    /**
     * 查询基础股票历史列表
     *
     * @param basicStockHis 基础股票历史
     * @return 基础股票历史集合
     */
    public List<BasicStockHis> selectBasicStockHisList(BasicStockHis basicStockHis);

    /**
     * 新增基础股票历史
     *
     * @param basicStockHis 基础股票历史
     * @return 结果
     */
    public int insertBasicStockHis(BasicStockHis basicStockHis);

    /**
     * 修改基础股票历史
     *
     * @param basicStockHis 基础股票历史
     * @return 结果
     */
    public int updateBasicStockHis(BasicStockHis basicStockHis);

    /**
     * 批量删除基础股票历史
     *
     * @param basicStockIds 需要删除的基础股票历史主键集合
     * @return 结果
     */
    public int deleteBasicStockHisByBasicStockIds(Long[] basicStockIds);

    /**
     * 删除基础股票历史信息
     *
     * @param basicStockId 基础股票历史主键
     * @return 结果
     */
    public int deleteBasicStockHisByBasicStockId(Long basicStockId);
}
