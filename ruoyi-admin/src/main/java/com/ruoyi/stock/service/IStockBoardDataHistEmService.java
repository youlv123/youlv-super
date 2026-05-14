package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockBoardDataHistEm;
import com.ruoyi.stock.domain.StockBoardHis;

/**
 * 板块数据历史Service接口
 *
 * @author DXR
 * @date 2025-08-17
 */
public interface IStockBoardDataHistEmService extends IService<StockBoardDataHistEm> {
    /**
     * 查询板块数据历史
     *
     * @param boardDataHistId 板块数据历史主键
     * @return 板块数据历史
     */
    public StockBoardDataHistEm selectStockBoardDataHistEmByBoardDataHistId(Long boardDataHistId);

    /**
     * 查询板块数据历史列表
     *
     * @param stockBoardDataHistEm 板块数据历史
     * @return 板块数据历史集合
     */
    public List<StockBoardDataHistEm> selectStockBoardDataHistEmList(StockBoardDataHistEm stockBoardDataHistEm);

    /**
     * 新增板块数据历史
     *
     * @param stockBoardDataHistEm 板块数据历史
     * @return 结果
     */
    public int insertStockBoardDataHistEm(StockBoardDataHistEm stockBoardDataHistEm);

    /**
     * 修改板块数据历史
     *
     * @param stockBoardDataHistEm 板块数据历史
     * @return 结果
     */
    public int updateStockBoardDataHistEm(StockBoardDataHistEm stockBoardDataHistEm);

    /**
     * 批量删除板块数据历史
     *
     * @param boardDataHistIds 需要删除的板块数据历史主键集合
     * @return 结果
     */
    public int deleteStockBoardDataHistEmByBoardDataHistIds(Long[] boardDataHistIds);

    /**
     * 删除板块数据历史信息
     *
     * @param boardDataHistId 板块数据历史主键
     * @return 结果
     */
    public int deleteStockBoardDataHistEmByBoardDataHistId(Long boardDataHistId);
}
