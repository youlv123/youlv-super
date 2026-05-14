package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockZtPoolZbgcEm;
import com.ruoyi.stock.domain.ThsStockFundFlow;
import org.springframework.stereotype.Repository;

/**
 * 同花顺个股资金流向Mapper接口
 *
 * @author DXR
 * @date 2025-08-20
 */
@Repository
public interface ThsStockFundFlowMapper extends BaseMapper<ThsStockFundFlow> {
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
     * 删除同花顺个股资金流向
     *
     * @param fundFlowIndividualId 同花顺个股资金流向主键
     * @return 结果
     */
    public int deleteThsStockFundFlowByFundFlowIndividualId(Long fundFlowIndividualId);

    /**
     * 批量删除同花顺个股资金流向
     *
     * @param fundFlowIndividualIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteThsStockFundFlowByFundFlowIndividualIds(Long[] fundFlowIndividualIds);

    /**
     * 清空表
     * @return
     */
     int truncateThsStockFundFlow();
}
