package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockBoardHis;
import com.ruoyi.stock.mapper.StockBoardHisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockBoardDataHistEmMapper;
import com.ruoyi.stock.domain.StockBoardDataHistEm;
import com.ruoyi.stock.service.IStockBoardDataHistEmService;

/**
 * 板块数据历史Service业务层处理
 *
 * @author DXR
 * @date 2025-08-17
 */
@Service
public class StockBoardDataHistEmServiceImpl extends ServiceImpl<StockBoardDataHistEmMapper, StockBoardDataHistEm> implements IStockBoardDataHistEmService {
    @Autowired
    private StockBoardDataHistEmMapper stockBoardDataHistEmMapper;

    /**
     * 查询板块数据历史
     *
     * @param boardDataHistId 板块数据历史主键
     * @return 板块数据历史
     */
    @Override
    public StockBoardDataHistEm selectStockBoardDataHistEmByBoardDataHistId(Long boardDataHistId) {
        return stockBoardDataHistEmMapper.selectStockBoardDataHistEmByBoardDataHistId(boardDataHistId);
    }

    /**
     * 查询板块数据历史列表
     *
     * @param stockBoardDataHistEm 板块数据历史
     * @return 板块数据历史
     */
    @Override
    public List<StockBoardDataHistEm> selectStockBoardDataHistEmList(StockBoardDataHistEm stockBoardDataHistEm) {
        return stockBoardDataHistEmMapper.selectStockBoardDataHistEmList(stockBoardDataHistEm);
    }

    /**
     * 新增板块数据历史
     *
     * @param stockBoardDataHistEm 板块数据历史
     * @return 结果
     */
    @Override
    public int insertStockBoardDataHistEm(StockBoardDataHistEm stockBoardDataHistEm) {
        return stockBoardDataHistEmMapper.insertStockBoardDataHistEm(stockBoardDataHistEm);
    }

    /**
     * 修改板块数据历史
     *
     * @param stockBoardDataHistEm 板块数据历史
     * @return 结果
     */
    @Override
    public int updateStockBoardDataHistEm(StockBoardDataHistEm stockBoardDataHistEm) {
        return stockBoardDataHistEmMapper.updateStockBoardDataHistEm(stockBoardDataHistEm);
    }

    /**
     * 批量删除板块数据历史
     *
     * @param boardDataHistIds 需要删除的板块数据历史主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardDataHistEmByBoardDataHistIds(Long[] boardDataHistIds) {
        return stockBoardDataHistEmMapper.deleteStockBoardDataHistEmByBoardDataHistIds(boardDataHistIds);
    }

    /**
     * 删除板块数据历史信息
     *
     * @param boardDataHistId 板块数据历史主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardDataHistEmByBoardDataHistId(Long boardDataHistId) {
        return stockBoardDataHistEmMapper.deleteStockBoardDataHistEmByBoardDataHistId(boardDataHistId);
    }
}
