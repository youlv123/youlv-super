package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockBoardHis;
import com.ruoyi.system.domain.FinancialTotal;
import org.springframework.stereotype.Repository;

/**
 * 理财汇总Mapper接口
 *
 * @author DXR
 * @date 2025-09-13
 */
@Repository
public interface FinancialTotalMapper extends BaseMapper<FinancialTotal> {
    /**
     * 查询理财汇总
     *
     * @param financialTotalId 理财汇总主键
     * @return 理财汇总
     */
    public FinancialTotal selectFinancialTotalByFinancialTotalId(Long financialTotalId);

    /**
     * 查询理财汇总列表
     *
     * @param financialTotal 理财汇总
     * @return 理财汇总集合
     */
    public List<FinancialTotal> selectFinancialTotalList(FinancialTotal financialTotal);

    /**
     * 新增理财汇总
     *
     * @param financialTotal 理财汇总
     * @return 结果
     */
    public int insertFinancialTotal(FinancialTotal financialTotal);

    /**
     * 修改理财汇总
     *
     * @param financialTotal 理财汇总
     * @return 结果
     */
    public int updateFinancialTotal(FinancialTotal financialTotal);

    /**
     * 删除理财汇总
     *
     * @param financialTotalId 理财汇总主键
     * @return 结果
     */
    public int deleteFinancialTotalByFinancialTotalId(Long financialTotalId);

    /**
     * 批量删除理财汇总
     *
     * @param financialTotalIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialTotalByFinancialTotalIds(Long[] financialTotalIds);
}
