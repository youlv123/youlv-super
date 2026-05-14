package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.domain.StockBoardConsEmHis;

/**
 * 板块成分股历史Service接口
 *
 * @author DXR
 * @date 2025-07-23
 */
public interface IStockBoardConsEmHisService extends IService<StockBoardConsEmHis> {
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
     * 批量删除板块成分股历史
     *
     * @param hisBoardConsIds 需要删除的板块成分股历史主键集合
     * @return 结果
     */
    public int deleteStockBoardConsEmHisByHisBoardConsIds(Long[] hisBoardConsIds);

    /**
     * 删除板块成分股历史信息
     *
     * @param hisBoardConsId 板块成分股历史主键
     * @return 结果
     */
    public int deleteStockBoardConsEmHisByHisBoardConsId(Long hisBoardConsId);
}
