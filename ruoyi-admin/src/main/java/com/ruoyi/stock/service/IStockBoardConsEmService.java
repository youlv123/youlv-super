package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.domain.StockZhAHist;

/**
 * 板块成分股Service接口
 *
 * @author DXR
 * @date 2025-06-15
 */
public interface IStockBoardConsEmService extends IService<StockBoardConsEm> {
    /**
     * 查询板块成分股
     *
     * @param boardConsId 板块成分股主键
     * @return 板块成分股
     */
    public StockBoardConsEm selectStockBoardConsEmByBoardConsId(Long boardConsId);

    /**
     * 查询板块成分股列表
     *
     * @param stockBoardConsEm 板块成分股
     * @return 板块成分股集合
     */
    public List<StockBoardConsEm> selectStockBoardConsEmList(StockBoardConsEm stockBoardConsEm);

    /**
     * 新增板块成分股
     *
     * @param stockBoardConsEm 板块成分股
     * @return 结果
     */
    public int insertStockBoardConsEm(StockBoardConsEm stockBoardConsEm);

    /**
     * 修改板块成分股
     *
     * @param stockBoardConsEm 板块成分股
     * @return 结果
     */
    public int updateStockBoardConsEm(StockBoardConsEm stockBoardConsEm);

    /**
     * 批量删除板块成分股
     *
     * @param boardConsIds 需要删除的板块成分股主键集合
     * @return 结果
     */
    public int deleteStockBoardConsEmByBoardConsIds(Long[] boardConsIds);

    /**
     * 删除板块成分股信息
     *
     * @param boardConsId 板块成分股主键
     * @return 结果
     */
    public int deleteStockBoardConsEmByBoardConsId(Long boardConsId);
}
