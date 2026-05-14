package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.ThsBoardFundFlow;
import com.ruoyi.stock.domain.ThsStockFundFlow;

/**
 * 东方财富板块资金流向Service接口
 *
 * @author DXR
 * @date 2025-08-23
 */
public interface IThsBoardFundFlowService extends IService<ThsBoardFundFlow> {
    /**
     * 查询东方财富板块资金流向
     *
     * @param fundFlowBoardId 东方财富板块资金流向主键
     * @return 东方财富板块资金流向
     */
    public ThsBoardFundFlow selectThsBoardFundFlowByFundFlowBoardId(Long fundFlowBoardId);

    /**
     * 查询东方财富板块资金流向列表
     *
     * @param thsBoardFundFlow 东方财富板块资金流向
     * @return 东方财富板块资金流向集合
     */
    public List<ThsBoardFundFlow> selectThsBoardFundFlowList(ThsBoardFundFlow thsBoardFundFlow);

    /**
     * 新增东方财富板块资金流向
     *
     * @param thsBoardFundFlow 东方财富板块资金流向
     * @return 结果
     */
    public int insertThsBoardFundFlow(ThsBoardFundFlow thsBoardFundFlow);

    /**
     * 修改东方财富板块资金流向
     *
     * @param thsBoardFundFlow 东方财富板块资金流向
     * @return 结果
     */
    public int updateThsBoardFundFlow(ThsBoardFundFlow thsBoardFundFlow);

    /**
     * 批量删除东方财富板块资金流向
     *
     * @param fundFlowBoardIds 需要删除的东方财富板块资金流向主键集合
     * @return 结果
     */
    public int deleteThsBoardFundFlowByFundFlowBoardIds(Long[] fundFlowBoardIds);

    /**
     * 删除东方财富板块资金流向信息
     *
     * @param fundFlowBoardId 东方财富板块资金流向主键
     * @return 结果
     */
    public int deleteThsBoardFundFlowByFundFlowBoardId(Long fundFlowBoardId);
}
