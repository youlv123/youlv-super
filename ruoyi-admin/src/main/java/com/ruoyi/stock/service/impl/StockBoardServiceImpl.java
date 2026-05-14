package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockBoardMapper;
import com.ruoyi.stock.domain.StockBoard;
import com.ruoyi.stock.service.IStockBoardService;

/**
 * 东方财富-概念板块Service业务层处理
 *
 * @author DXR
 * @date 2025-06-08
 */
@Service
public class StockBoardServiceImpl extends ServiceImpl<StockBoardMapper, StockBoard> implements IStockBoardService {
    @Autowired
    private StockBoardMapper stockBoardMapper;

    /**
     * 查询东方财富-概念板块
     *
     * @param boardId 东方财富-概念板块主键
     * @return 东方财富-概念板块
     */
    @Override
    public StockBoard selectStockBoardByBoardId(Long boardId) {
        return stockBoardMapper.selectStockBoardByBoardId(boardId);
    }

    /**
     * 查询东方财富-概念板块列表
     *
     * @param stockBoard 东方财富-概念板块
     * @return 东方财富-概念板块
     */
    @Override
    public List<StockBoard> selectStockBoardList(StockBoard stockBoard) {
        return stockBoardMapper.selectStockBoardList(stockBoard);
    }

    /**
     * 新增东方财富-概念板块
     *
     * @param stockBoard 东方财富-概念板块
     * @return 结果
     */
    @Override
    public int insertStockBoard(StockBoard stockBoard) {
        return stockBoardMapper.insertStockBoard(stockBoard);
    }

    /**
     * 修改东方财富-概念板块
     *
     * @param stockBoard 东方财富-概念板块
     * @return 结果
     */
    @Override
    public int updateStockBoard(StockBoard stockBoard) {
        return stockBoardMapper.updateStockBoard(stockBoard);
    }

    /**
     * 批量删除东方财富-概念板块
     *
     * @param boardIds 需要删除的东方财富-概念板块主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardByBoardIds(Long[] boardIds) {
        return stockBoardMapper.deleteStockBoardByBoardIds(boardIds);
    }

    /**
     * 删除东方财富-概念板块信息
     *
     * @param boardId 东方财富-概念板块主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardByBoardId(Long boardId) {
        return stockBoardMapper.deleteStockBoardByBoardId(boardId);
    }
}
