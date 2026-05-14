package com.ruoyi.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockZhAHist;
import com.ruoyi.stock.mapper.StockZhAHistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FinancialTotalMapper;
import com.ruoyi.system.domain.FinancialTotal;
import com.ruoyi.system.service.IFinancialTotalService;

/**
 * 理财汇总Service业务层处理
 *
 * @author DXR
 * @date 2025-09-13
 */
@Service
public class FinancialTotalServiceImpl extends ServiceImpl<FinancialTotalMapper, FinancialTotal> implements IFinancialTotalService {
    @Autowired
    private FinancialTotalMapper financialTotalMapper;

    /**
     * 查询理财汇总
     *
     * @param financialTotalId 理财汇总主键
     * @return 理财汇总
     */
    @Override
    public FinancialTotal selectFinancialTotalByFinancialTotalId(Long financialTotalId) {
        return financialTotalMapper.selectFinancialTotalByFinancialTotalId(financialTotalId);
    }

    /**
     * 查询理财汇总列表
     *
     * @param financialTotal 理财汇总
     * @return 理财汇总
     */
    @Override
    public List<FinancialTotal> selectFinancialTotalList(FinancialTotal financialTotal) {
        return financialTotalMapper.selectFinancialTotalList(financialTotal);
    }

    /**
     * 新增理财汇总
     *
     * @param financialTotal 理财汇总
     * @return 结果
     */
    @Override
    public int insertFinancialTotal(FinancialTotal financialTotal) {
        return financialTotalMapper.insertFinancialTotal(financialTotal);
    }

    /**
     * 修改理财汇总
     *
     * @param financialTotal 理财汇总
     * @return 结果
     */
    @Override
    public int updateFinancialTotal(FinancialTotal financialTotal) {
        return financialTotalMapper.updateFinancialTotal(financialTotal);
    }

    /**
     * 批量删除理财汇总
     *
     * @param financialTotalIds 需要删除的理财汇总主键
     * @return 结果
     */
    @Override
    public int deleteFinancialTotalByFinancialTotalIds(Long[] financialTotalIds) {
        return financialTotalMapper.deleteFinancialTotalByFinancialTotalIds(financialTotalIds);
    }

    /**
     * 删除理财汇总信息
     *
     * @param financialTotalId 理财汇总主键
     * @return 结果
     */
    @Override
    public int deleteFinancialTotalByFinancialTotalId(Long financialTotalId) {
        return financialTotalMapper.deleteFinancialTotalByFinancialTotalId(financialTotalId);
    }
}
