package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockBoardHis;
import org.springframework.stereotype.Repository;

/**
 * 板块历史Mapper接口
 *
 * @author DXR
 * @date 2025-07-21
 */
@Repository
public interface StockBoardHisMapper extends BaseMapper<StockBoardHis> {
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
     * 删除板块历史
     *
     * @param hisBoardId 板块历史主键
     * @return 结果
     */
    public int deleteStockBoardHisByHisBoardId(Long hisBoardId);

    /**
     * 批量删除板块历史
     *
     * @param hisBoardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockBoardHisByHisBoardIds(Long[] hisBoardIds);

    /**
     * 查询板块历史最大ID
     *
     * @return 板块历史最大ID
     */
    Long selectMaxId();

    /**
     * 删除板块历史根据最大id
     *
     * @param hisBoardId 板块历史主键
     * @return 结果
     */
    public int deleteStockBoardHisByMaxId(Long hisBoardId);

}
