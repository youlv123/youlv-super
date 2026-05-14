package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockBoard;
import com.ruoyi.stock.mapper.StockBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockBoardHisMapper;
import com.ruoyi.stock.domain.StockBoardHis;
import com.ruoyi.stock.service.IStockBoardHisService;

/**
 * 板块历史Service业务层处理
 *
 * @author DXR
 * @date 2025-07-21
 */
@Service
public class StockBoardHisServiceImpl extends ServiceImpl<StockBoardHisMapper, StockBoardHis> implements IStockBoardHisService {
    @Autowired
    private StockBoardHisMapper stockBoardHisMapper;

    /**
     * 查询板块历史
     *
     * @param hisBoardId 板块历史主键
     * @return 板块历史
     */
    @Override
    public StockBoardHis selectStockBoardHisByHisBoardId(Long hisBoardId) {
        return stockBoardHisMapper.selectStockBoardHisByHisBoardId(hisBoardId);
    }

    /**
     * 查询板块历史列表
     *
     * @param stockBoardHis 板块历史
     * @return 板块历史
     */
    @Override
    public List<StockBoardHis> selectStockBoardHisList(StockBoardHis stockBoardHis) {
        return stockBoardHisMapper.selectStockBoardHisList(stockBoardHis);
    }

    /**
     * 新增板块历史
     *
     * @param stockBoardHis 板块历史
     * @return 结果
     */
    @Override
    public int insertStockBoardHis(StockBoardHis stockBoardHis) {
        return stockBoardHisMapper.insertStockBoardHis(stockBoardHis);
    }

    /**
     * 修改板块历史
     *
     * @param stockBoardHis 板块历史
     * @return 结果
     */
    @Override
    public int updateStockBoardHis(StockBoardHis stockBoardHis) {
        return stockBoardHisMapper.updateStockBoardHis(stockBoardHis);
    }

    /**
     * 批量删除板块历史
     *
     * @param hisBoardIds 需要删除的板块历史主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardHisByHisBoardIds(Long[] hisBoardIds) {
        return stockBoardHisMapper.deleteStockBoardHisByHisBoardIds(hisBoardIds);
    }

    /**
     * 删除板块历史信息
     *
     * @param hisBoardId 板块历史主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardHisByHisBoardId(Long hisBoardId) {
        return stockBoardHisMapper.deleteStockBoardHisByHisBoardId(hisBoardId);
    }
}
