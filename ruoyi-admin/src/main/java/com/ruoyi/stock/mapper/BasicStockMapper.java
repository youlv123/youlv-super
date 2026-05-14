package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.BasicStock;
import com.ruoyi.stock.domain.StockBoardConsEmHis;
import org.springframework.stereotype.Repository;

/**
 * 基础股票Mapper接口
 *
 * @author DXR
 * @date 2025-07-31
 */
@Repository
public interface BasicStockMapper extends BaseMapper<BasicStock> {
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
     * 删除基础股票
     *
     * @param basicStockId 基础股票主键
     * @return 结果
     */
    public int deleteBasicStockByBasicStockId(Long basicStockId);

    /**
     * 批量删除基础股票
     *
     * @param basicStockIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBasicStockByBasicStockIds(Long[] basicStockIds);
}
