package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockBoard;

/**
 * 东方财富-概念板块Service接口
 *
 * @author DXR
 * @date 2025-06-08
 */
public interface IStockBoardService extends IService<StockBoard> {
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
     * 批量删除东方财富-概念板块
     *
     * @param boardIds 需要删除的东方财富-概念板块主键集合
     * @return 结果
     */
    public int deleteStockBoardByBoardIds(Long[] boardIds);

    /**
     * 删除东方财富-概念板块信息
     *
     * @param boardId 东方财富-概念板块主键
     * @return 结果
     */
    public int deleteStockBoardByBoardId(Long boardId);
}
