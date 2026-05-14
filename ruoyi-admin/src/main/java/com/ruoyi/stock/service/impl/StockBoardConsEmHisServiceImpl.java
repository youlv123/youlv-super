package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.mapper.StockBoardConsEmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockBoardConsEmHisMapper;
import com.ruoyi.stock.domain.StockBoardConsEmHis;
import com.ruoyi.stock.service.IStockBoardConsEmHisService;

/**
 * 板块成分股历史Service业务层处理
 *
 * @author DXR
 * @date 2025-07-23
 */
@Service
public class StockBoardConsEmHisServiceImpl extends ServiceImpl<StockBoardConsEmHisMapper, StockBoardConsEmHis> implements IStockBoardConsEmHisService {
    @Autowired
    private StockBoardConsEmHisMapper stockBoardConsEmHisMapper;

    /**
     * 查询板块成分股历史
     *
     * @param hisBoardConsId 板块成分股历史主键
     * @return 板块成分股历史
     */
    @Override
    public StockBoardConsEmHis selectStockBoardConsEmHisByHisBoardConsId(Long hisBoardConsId) {
        return stockBoardConsEmHisMapper.selectStockBoardConsEmHisByHisBoardConsId(hisBoardConsId);
    }

    /**
     * 查询板块成分股历史列表
     *
     * @param stockBoardConsEmHis 板块成分股历史
     * @return 板块成分股历史
     */
    @Override
    public List<StockBoardConsEmHis> selectStockBoardConsEmHisList(StockBoardConsEmHis stockBoardConsEmHis) {
        return stockBoardConsEmHisMapper.selectStockBoardConsEmHisList(stockBoardConsEmHis);
    }

    /**
     * 新增板块成分股历史
     *
     * @param stockBoardConsEmHis 板块成分股历史
     * @return 结果
     */
    @Override
    public int insertStockBoardConsEmHis(StockBoardConsEmHis stockBoardConsEmHis) {
        return stockBoardConsEmHisMapper.insertStockBoardConsEmHis(stockBoardConsEmHis);
    }

    /**
     * 修改板块成分股历史
     *
     * @param stockBoardConsEmHis 板块成分股历史
     * @return 结果
     */
    @Override
    public int updateStockBoardConsEmHis(StockBoardConsEmHis stockBoardConsEmHis) {
        return stockBoardConsEmHisMapper.updateStockBoardConsEmHis(stockBoardConsEmHis);
    }

    /**
     * 批量删除板块成分股历史
     *
     * @param hisBoardConsIds 需要删除的板块成分股历史主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardConsEmHisByHisBoardConsIds(Long[] hisBoardConsIds) {
        return stockBoardConsEmHisMapper.deleteStockBoardConsEmHisByHisBoardConsIds(hisBoardConsIds);
    }

    /**
     * 删除板块成分股历史信息
     *
     * @param hisBoardConsId 板块成分股历史主键
     * @return 结果
     */
    @Override
    public int deleteStockBoardConsEmHisByHisBoardConsId(Long hisBoardConsId) {
        return stockBoardConsEmHisMapper.deleteStockBoardConsEmHisByHisBoardConsId(hisBoardConsId);
    }
}
