package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.ThsBoardFundFlow;
import com.ruoyi.stock.domain.ThsStockFundFlow;
import org.springframework.stereotype.Repository;

/**
 * 东方财富板块资金流向Mapper接口
 *
 * @author DXR
 * @date 2025-08-23
 */
@Repository
public interface ThsBoardFundFlowMapper extends BaseMapper<ThsBoardFundFlow> {
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
     * 删除东方财富板块资金流向
     *
     * @param fundFlowBoardId 东方财富板块资金流向主键
     * @return 结果
     */
    public int deleteThsBoardFundFlowByFundFlowBoardId(Long fundFlowBoardId);

    /**
     * 批量删除东方财富板块资金流向
     *
     * @param fundFlowBoardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteThsBoardFundFlowByFundFlowBoardIds(Long[] fundFlowBoardIds);

    /**
     * 清空表
     * @return
     */
    int truncateThsBoardFundFlow();
}
