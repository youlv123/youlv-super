package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.FinancialTransaction;
import com.ruoyi.system.domain.FundPositionDetail;
import org.springframework.stereotype.Repository;

/**
 * 理财交易记录（记录所有买入/卖出操作，关联持仓明细）Mapper接口
 *
 * @author DXR
 * @date 2025-10-13
 */
@Repository
public interface FinancialTransactionMapper extends BaseMapper<FinancialTransaction> {
    /**
     * 查询理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param transactionId 理财交易记录（记录所有买入/卖出操作，关联持仓明细）主键
     * @return 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     */
    public FinancialTransaction selectFinancialTransactionByTransactionId(Long transactionId);

    /**
     * 查询理财交易记录（记录所有买入/卖出操作，关联持仓明细）列表
     *
     * @param financialTransaction 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     * @return 理财交易记录（记录所有买入/卖出操作，关联持仓明细）集合
     */
    public List<FinancialTransaction> selectFinancialTransactionList(FinancialTransaction financialTransaction);

    /**
     * 新增理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param financialTransaction 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     * @return 结果
     */
    public int insertFinancialTransaction(FinancialTransaction financialTransaction);

    /**
     * 修改理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param financialTransaction 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     * @return 结果
     */
    public int updateFinancialTransaction(FinancialTransaction financialTransaction);

    /**
     * 删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param transactionId 理财交易记录（记录所有买入/卖出操作，关联持仓明细）主键
     * @return 结果
     */
    public int deleteFinancialTransactionByTransactionId(Long transactionId);

    /**
     * 批量删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param transactionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialTransactionByTransactionIds(Long[] transactionIds);
}
