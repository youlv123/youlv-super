package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.domain.StockBoardHis;

/**
 * 板块历史Service接口
 *
 * @author DXR
 * @date 2025-07-21
 */
public interface IStockBoardHisService extends IService<StockBoardHis> {
    /**
     * 查询板块历史
     *
     * @param hisBoardId 板块历史主键
     * @return 板块历史
     */
    public StockBoardHis selectStockBoardHisByHisBoardId(Long hisBoardId);

    /**
     * 查询板块历史列表
     *
     * @param stockBoardHis 板块历史
     * @return 板块历史集合
     */
    public List<StockBoardHis> selectStockBoardHisList(StockBoardHis stockBoardHis);

    /**
     * 新增板块历史
     *
     * @param stockBoardHis 板块历史
     * @return 结果
     */
    public int insertStockBoardHis(StockBoardHis stockBoardHis);

    /**
     * 修改板块历史
     *
     * @param stockBoardHis 板块历史
     * @return 结果
     */
    public int updateStockBoardHis(StockBoardHis stockBoardHis);

    /**
     * 批量删除板块历史
     *
     * @param hisBoardIds 需要删除的板块历史主键集合
     * @return 结果
     */
    public int deleteStockBoardHisByHisBoardIds(Long[] hisBoardIds);

    /**
     * 删除板块历史信息
     *
     * @param hisBoardId 板块历史主键
     * @return 结果
     */
    public int deleteStockBoardHisByHisBoardId(Long hisBoardId);
}
