package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockZhAHist;
import com.ruoyi.stock.mapper.StockZhAHistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockBoardConsEmMapper;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.service.IStockBoardConsEmService;

/**
 * 板块成分股Service业务层处理
 *
 * @author DXR
 * @date 2025-06-15
 */
@Service
public class StockBoardConsEmServiceImpl extends ServiceImpl<StockBoardConsEmMapper, StockBoardConsEm> implements IStockBoardConsEmService {
    @Autowired
    private StockBoardConsEmMapper stockBoardConsEmMapper;

    /**
     * 查询板块成分股
     *
     * @param boardConsId 板块成分股主键
     * @return 板块成分股
     */
    @Override
    public StockBoardConsEm selectStockBoardConsEmByBoardConsId(Long boardConsId) {
        return stockBoardConsEmMapper.selectStockBoardConsEmByBoardConsId(boardConsId);
    }

    /**
     * 查询板块成分股列表
     *
     * @param stockBoardConsEm 板块成分股
     * @return 板块成分股
     */
    @Override
    public List<StockBoardConsEm> selectStockBoardConsEmList(StockBoardConsEm stockBoardConsEm) {
        return stockBoardConsEmMapper.selectStockBoardConsEmList(stockBoardConsEm);
    }

    /**
     * 新增板块成分股
     *
     * @param stockBoardConsEm 板块成分股
     * @return 结果
     */
    @Override
    public int insertStockBoardConsEm(StockBoardConsEm stockBoardConsEm) {
        return stockBoardConsEmMapper.insertStockBoardConsEm(stockBoardConsEm);
    }

    /**
     * 修改板块成分股
     *
     * @param stockBoardConsEm 板块成分股
     * @return 结果
     */
    @Override
    public int updateStockBoardConsEm(StockBoardConsEm stockBoardConsEm) {
        return stockBoardConsEmMapper.updateStockBoardConsEm(stockBoardConsEm);
    }

    /**
     * 批量删除板块成分股
     *
     * @param boardConsIds 需要删除的板块成分股主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardConsEmByBoardConsIds(Long[] boardConsIds) {
        return stockBoardConsEmMapper.deleteStockBoardConsEmByBoardConsIds(boardConsIds);
    }

    /**
     * 删除板块成分股信息
     *
     * @param boardConsId 板块成分股主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardConsEmByBoardConsId(Long boardConsId) {
        return stockBoardConsEmMapper.deleteStockBoardConsEmByBoardConsId(boardConsId);
    }
}
