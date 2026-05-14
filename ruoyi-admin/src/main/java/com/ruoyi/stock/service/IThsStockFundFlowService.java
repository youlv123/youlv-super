package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockZtPoolZbgcEm;
import com.ruoyi.stock.domain.ThsStockFundFlow;

/**
 * 同花顺个股资金流向Service接口
 *
 * @author DXR
 * @date 2025-08-20
 */
public interface IThsStockFundFlowService extends IService<ThsStockFundFlow> {
    /**
     * 查询同花顺个股资金流向
     *
     * @param fundFlowIndividualId 同花顺个股资金流向主键
     * @return 同花顺个股资金流向
     */
    public ThsStockFundFlow selectThsStockFundFlowByFundFlowIndividualId(Long fundFlowIndividualId);

    /**
     * 查询同花顺个股资金流向列表
     *
     * @param thsStockFundFlow 同花顺个股资金流向
     * @return 同花顺个股资金流向集合
     */
    public List<ThsStockFundFlow> selectThsStockFundFlowList(ThsStockFundFlow thsStockFundFlow);

    /**
     * 新增同花顺个股资金流向
     *
     * @param thsStockFundFlow 同花顺个股资金流向
     * @return 结果
     */
    public int insertThsStockFundFlow(ThsStockFundFlow thsStockFundFlow);

    /**
     * 修改同花顺个股资金流向
     *
     * @param thsStockFundFlow 同花顺个股资金流向
     * @return 结果
     */
    public int updateThsStockFundFlow(ThsStockFundFlow thsStockFundFlow);

    /**
     * 批量删除同花顺个股资金流向
     *
     * @param fundFlowIndividualIds 需要删除的同花顺个股资金流向主键集合
     * @return 结果
     */
    public int deleteThsStockFundFlowByFundFlowIndividualIds(Long[] fundFlowIndividualIds);

    /**
     * 删除同花顺个股资金流向信息
     *
     * @param fundFlowIndividualId 同花顺个股资金流向主键
     * @return 结果
     */
    public int deleteThsStockFundFlowByFundFlowIndividualId(Long fundFlowIndividualId);
}
