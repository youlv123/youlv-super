package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.ThsStockFundFlow;
import com.ruoyi.stock.mapper.ThsStockFundFlowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.ThsBoardFundFlowMapper;
import com.ruoyi.stock.domain.ThsBoardFundFlow;
import com.ruoyi.stock.service.IThsBoardFundFlowService;

/**
 * 东方财富板块资金流向Service业务层处理
 *
 * @author DXR
 * @date 2025-08-23
 */
@Service
public class ThsBoardFundFlowServiceImpl extends ServiceImpl<ThsBoardFundFlowMapper, ThsBoardFundFlow> implements IThsBoardFundFlowService {
    @Autowired
    private ThsBoardFundFlowMapper thsBoardFundFlowMapper;

    /**
     * 查询东方财富板块资金流向
     *
     * @param fundFlowBoardId 东方财富板块资金流向主键
     * @return 东方财富板块资金流向
     */
    @Override
    public ThsBoardFundFlow selectThsBoardFundFlowByFundFlowBoardId(Long fundFlowBoardId) {
        return thsBoardFundFlowMapper.selectThsBoardFundFlowByFundFlowBoardId(fundFlowBoardId);
    }

    /**
     * 查询东方财富板块资金流向列表
     *
     * @param thsBoardFundFlow 东方财富板块资金流向
     * @return 东方财富板块资金流向
     */
    @Override
    public List<ThsBoardFundFlow> selectThsBoardFundFlowList(ThsBoardFundFlow thsBoardFundFlow) {
        return thsBoardFundFlowMapper.selectThsBoardFundFlowList(thsBoardFundFlow);
    }

    /**
     * 新增东方财富板块资金流向
     *
     * @param thsBoardFundFlow 东方财富板块资金流向
     * @return 结果
     */
    @Override
    public int insertThsBoardFundFlow(ThsBoardFundFlow thsBoardFundFlow) {
        return thsBoardFundFlowMapper.insertThsBoardFundFlow(thsBoardFundFlow);
    }

    /**
     * 修改东方财富板块资金流向
     *
     * @param thsBoardFundFlow 东方财富板块资金流向
     * @return 结果
     */
    @Override
    public int updateThsBoardFundFlow(ThsBoardFundFlow thsBoardFundFlow) {
        return thsBoardFundFlowMapper.updateThsBoardFundFlow(thsBoardFundFlow);
    }

    /**
     * 批量删除东方财富板块资金流向
     *
     * @param fundFlowBoardIds 需要删除的东方财富板块资金流向主键
     * @return 结果
     */
    @Override
    public int deleteThsBoardFundFlowByFundFlowBoardIds(Long[] fundFlowBoardIds) {
        return thsBoardFundFlowMapper.deleteThsBoardFundFlowByFundFlowBoardIds(fundFlowBoardIds);
    }

    /**
     * 删除东方财富板块资金流向信息
     *
     * @param fundFlowBoardId 东方财富板块资金流向主键
     * @return 结果
     */
    @Override
    public int deleteThsBoardFundFlowByFundFlowBoardId(Long fundFlowBoardId) {
        return thsBoardFundFlowMapper.deleteThsBoardFundFlowByFundFlowBoardId(fundFlowBoardId);
    }
}
