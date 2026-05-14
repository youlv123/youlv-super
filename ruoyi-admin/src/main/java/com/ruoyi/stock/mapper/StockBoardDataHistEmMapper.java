package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockBoardDataHistEm;
import com.ruoyi.stock.domain.StockBoardHis;
import org.springframework.stereotype.Repository;

/**
 * 板块数据历史Mapper接口
 *
 * @author DXR
 * @date 2025-08-17
 */
@Repository
public interface StockBoardDataHistEmMapper extends BaseMapper<StockBoardDataHistEm> {
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
     * 删除板块数据历史
     *
     * @param boardDataHistId 板块数据历史主键
     * @return 结果
     */
    public int deleteStockBoardDataHistEmByBoardDataHistId(Long boardDataHistId);

    /**
     * 批量删除板块数据历史
     *
     * @param boardDataHistIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockBoardDataHistEmByBoardDataHistIds(Long[] boardDataHistIds);
}
