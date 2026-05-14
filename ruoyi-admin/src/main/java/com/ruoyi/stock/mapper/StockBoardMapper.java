package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockBoard;
import org.springframework.stereotype.Repository;

/**
 * 东方财富-概念板块Mapper接口
 *
 * @author DXR
 * @date 2025-06-08
 */
@Repository
public interface StockBoardMapper extends BaseMapper<StockBoard> {
    /**
     * 查询东方财富-概念板块
     *
     * @param boardId 东方财富-概念板块主键
     * @return 东方财富-概念板块
     */
    public StockBoard selectStockBoardByBoardId(Long boardId);

    /**
     * 查询东方财富-概念板块列表
     *
     * @param stockBoard 东方财富-概念板块
     * @return 东方财富-概念板块集合
     */
    public List<StockBoard> selectStockBoardList(StockBoard stockBoard);

    /**
     * 新增东方财富-概念板块
     *
     * @param stockBoard 东方财富-概念板块
     * @return 结果
     */
    public int insertStockBoard(StockBoard stockBoard);

    /**
     * 修改东方财富-概念板块
     *
     * @param stockBoard 东方财富-概念板块
     * @return 结果
     */
    public int updateStockBoard(StockBoard stockBoard);

    /**
     * 删除东方财富-概念板块
     *
     * @param boardId 东方财富-概念板块主键
     * @return 结果
     */
    public int deleteStockBoardByBoardId(Long boardId);

    /**
     * 批量删除东方财富-概念板块
     *
     * @param boardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockBoardByBoardIds(Long[] boardIds);

    /**
     * 清空东方财富-概念板块
     *
     * @return 结果
     */
    public int truncateStockBoard();
}
