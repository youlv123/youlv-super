package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.domain.StockBoardConsEmHis;
import org.springframework.stereotype.Repository;

/**
 * 板块成分股历史Mapper接口
 *
 * @author DXR
 * @date 2025-07-23
 */
@Repository
public interface StockBoardConsEmHisMapper extends BaseMapper<StockBoardConsEmHis> {
    /**
     * 查询板块成分股历史
     *
     * @param hisBoardConsId 板块成分股历史主键
     * @return 板块成分股历史
     */
    public StockBoardConsEmHis selectStockBoardConsEmHisByHisBoardConsId(Long hisBoardConsId);

    /**
     * 查询板块成分股历史列表
     *
     * @param stockBoardConsEmHis 板块成分股历史
     * @return 板块成分股历史集合
     */
    public List<StockBoardConsEmHis> selectStockBoardConsEmHisList(StockBoardConsEmHis stockBoardConsEmHis);

    /**
     * 新增板块成分股历史
     *
     * @param stockBoardConsEmHis 板块成分股历史
     * @return 结果
     */
    public int insertStockBoardConsEmHis(StockBoardConsEmHis stockBoardConsEmHis);

    /**
     * 修改板块成分股历史
     *
     * @param stockBoardConsEmHis 板块成分股历史
     * @return 结果
     */
    public int updateStockBoardConsEmHis(StockBoardConsEmHis stockBoardConsEmHis);

    /**
     * 删除板块成分股历史
     *
     * @param hisBoardConsId 板块成分股历史主键
     * @return 结果
     */
    public int deleteStockBoardConsEmHisByHisBoardConsId(Long hisBoardConsId);

    /**
     * 批量删除板块成分股历史
     *
     * @param hisBoardConsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockBoardConsEmHisByHisBoardConsIds(Long[] hisBoardConsIds);
}
