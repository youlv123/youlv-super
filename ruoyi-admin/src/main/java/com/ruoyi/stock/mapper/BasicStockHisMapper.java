package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.BasicStock;
import com.ruoyi.stock.domain.BasicStockHis;
import org.springframework.stereotype.Repository;

/**
 * 基础股票历史Mapper接口
 *
 * @author ruoyi
 * @date 2025-08-03
 */
@Repository
public interface BasicStockHisMapper extends BaseMapper<BasicStockHis> {
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
     * 删除基础股票历史
     *
     * @param basicStockId 基础股票历史主键
     * @return 结果
     */
    public int deleteBasicStockHisByBasicStockId(Long basicStockId);

    /**
     * 批量删除基础股票历史
     *
     * @param basicStockIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBasicStockHisByBasicStockIds(Long[] basicStockIds);
}
