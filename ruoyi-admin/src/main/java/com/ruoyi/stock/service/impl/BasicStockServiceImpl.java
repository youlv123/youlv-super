package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockBoardConsEmHis;
import com.ruoyi.stock.mapper.StockBoardConsEmHisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.BasicStockMapper;
import com.ruoyi.stock.domain.BasicStock;
import com.ruoyi.stock.service.IBasicStockService;

/**
 * 基础股票Service业务层处理
 *
 * @author DXR
 * @date 2025-07-31
 */
@Service
public class BasicStockServiceImpl extends ServiceImpl<BasicStockMapper, BasicStock> implements IBasicStockService {
    @Autowired
    private BasicStockMapper basicStockMapper;

    /**
     * 查询基础股票
     *
     * @param basicStockId 基础股票主键
     * @return 基础股票
     */
    @Override
    public BasicStock selectBasicStockByBasicStockId(Long basicStockId) {
        return basicStockMapper.selectBasicStockByBasicStockId(basicStockId);
    }

    /**
     * 查询基础股票列表
     *
     * @param basicStock 基础股票
     * @return 基础股票
     */
    @Override
    public List<BasicStock> selectBasicStockList(BasicStock basicStock) {
        return basicStockMapper.selectBasicStockList(basicStock);
    }

    /**
     * 新增基础股票
     *
     * @param basicStock 基础股票
     * @return 结果
     */
    @Override
    public int insertBasicStock(BasicStock basicStock) {
        return basicStockMapper.insertBasicStock(basicStock);
    }

    /**
     * 修改基础股票
     *
     * @param basicStock 基础股票
     * @return 结果
     */
    @Override
    public int updateBasicStock(BasicStock basicStock) {
        return basicStockMapper.updateBasicStock(basicStock);
    }

    /**
     * 批量删除基础股票
     *
     * @param basicStockIds 需要删除的基础股票主键
     * @return 结果
     */
    @Override
    public int deleteBasicStockByBasicStockIds(Long[] basicStockIds) {
        return basicStockMapper.deleteBasicStockByBasicStockIds(basicStockIds);
    }

    /**
     * 删除基础股票信息
     *
     * @param basicStockId 基础股票主键
     * @return 结果
     */
    @Override
    public int deleteBasicStockByBasicStockId(Long basicStockId) {
        return basicStockMapper.deleteBasicStockByBasicStockId(basicStockId);
    }
}
