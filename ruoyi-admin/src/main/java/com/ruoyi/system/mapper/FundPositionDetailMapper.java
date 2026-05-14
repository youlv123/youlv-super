package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.FinancialTransaction;
import com.ruoyi.system.domain.FundPositionDetail;
import org.springframework.stereotype.Repository;

/**
 * 理财持仓明细（每笔买入独立记录，FIFO计算核心）Mapper接口
 *
 * @author DXR
 * @date 2025-10-13
 */
@Repository
public interface FundPositionDetailMapper extends BaseMapper<FundPositionDetail> {
    /**
     * 查询理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundDetailId 理财持仓明细（每笔买入独立记录，FIFO计算核心）主键
     * @return 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     */
    public FundPositionDetail selectFundPositionDetailByFundDetailId(Long fundDetailId);

    /**
     * 查询理财持仓明细（每笔买入独立记录，FIFO计算核心）列表
     *
     * @param fundPositionDetail 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     * @return 理财持仓明细（每笔买入独立记录，FIFO计算核心）集合
     */
    public List<FundPositionDetail> selectFundPositionDetailList(FundPositionDetail fundPositionDetail);

    /**
     * 新增理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundPositionDetail 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     * @return 结果
     */
    public int insertFundPositionDetail(FundPositionDetail fundPositionDetail);

    /**
     * 修改理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundPositionDetail 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     * @return 结果
     */
    public int updateFundPositionDetail(FundPositionDetail fundPositionDetail);

    /**
     * 删除理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundDetailId 理财持仓明细（每笔买入独立记录，FIFO计算核心）主键
     * @return 结果
     */
    public int deleteFundPositionDetailByFundDetailId(Long fundDetailId);

    /**
     * 批量删除理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundDetailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFundPositionDetailByFundDetailIds(Long[] fundDetailIds);
}
