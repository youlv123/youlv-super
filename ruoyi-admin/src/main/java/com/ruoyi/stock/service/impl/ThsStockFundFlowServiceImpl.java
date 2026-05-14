package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockZtPoolZbgcEm;
import com.ruoyi.stock.mapper.StockZtPoolZbgcEmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.ThsStockFundFlowMapper;
import com.ruoyi.stock.domain.ThsStockFundFlow;
import com.ruoyi.stock.service.IThsStockFundFlowService;

/**
 * 同花顺个股资金流向Service业务层处理
 *
 * @author DXR
 * @date 2025-08-20
 */
@Service
public class ThsStockFundFlowServiceImpl extends ServiceImpl<ThsStockFundFlowMapper, ThsStockFundFlow> implements IThsStockFundFlowService {
    @Autowired
    private ThsStockFundFlowMapper thsStockFundFlowMapper;

    /**
     * 查询同花顺个股资金流向
     *
     * @param fundFlowIndividualId 同花顺个股资金流向主键
     * @return 同花顺个股资金流向
     */
    @Override
    public ThsStockFundFlow selectThsStockFundFlowByFundFlowIndividualId(Long fundFlowIndividualId) {
        return thsStockFundFlowMapper.selectThsStockFundFlowByFundFlowIndividualId(fundFlowIndividualId);
    }

    /**
     * 查询同花顺个股资金流向列表
     *
     * @param thsStockFundFlow 同花顺个股资金流向
     * @return 同花顺个股资金流向
     */
    @Override
    public List<ThsStockFundFlow> selectThsStockFundFlowList(ThsStockFundFlow thsStockFundFlow) {
        return thsStockFundFlowMapper.selectThsStockFundFlowList(thsStockFundFlow);
    }

    /**
     * 新增同花顺个股资金流向
     *
     * @param thsStockFundFlow 同花顺个股资金流向
     * @return 结果
     */
    @Override
    public int insertThsStockFundFlow(ThsStockFundFlow thsStockFundFlow) {
        return thsStockFundFlowMapper.insertThsStockFundFlow(thsStockFundFlow);
    }

    /**
     * 修改同花顺个股资金流向
     *
     * @param thsStockFundFlow 同花顺个股资金流向
     * @return 结果
     */
    @Override
    public int updateThsStockFundFlow(ThsStockFundFlow thsStockFundFlow) {
        return thsStockFundFlowMapper.updateThsStockFundFlow(thsStockFundFlow);
    }

    /**
     * 批量删除同花顺个股资金流向
     *
     * @param fundFlowIndividualIds 需要删除的同花顺个股资金流向主键
     * @return 结果
     */
    @Override
    public int deleteThsStockFundFlowByFundFlowIndividualIds(Long[] fundFlowIndividualIds) {
        return thsStockFundFlowMapper.deleteThsStockFundFlowByFundFlowIndividualIds(fundFlowIndividualIds);
    }

    /**
     * 删除同花顺个股资金流向信息
     *
     * @param fundFlowIndividualId 同花顺个股资金流向主键
     * @return 结果
     */
    @Override
    public int deleteThsStockFundFlowByFundFlowIndividualId(Long fundFlowIndividualId) {
        return thsStockFundFlowMapper.deleteThsStockFundFlowByFundFlowIndividualId(fundFlowIndividualId);
    }
}
