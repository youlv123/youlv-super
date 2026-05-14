package com.ruoyi.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.FinancialTransaction;
import com.ruoyi.system.domain.FundPositionDetail;

/**
 * 理财交易记录（记录所有买入/卖出操作，关联持仓明细）Service接口
 *
 * @author DXR
 * @date 2025-10-13
 */
public interface IFinancialTransactionService extends IService<FinancialTransaction> {
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
     * 批量删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param transactionIds 需要删除的理财交易记录（记录所有买入/卖出操作，关联持仓明细）主键集合
     * @return 结果
     */
    public int deleteFinancialTransactionByTransactionIds(Long[] transactionIds);

    /**
     * 删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）信息
     *
     * @param transactionId 理财交易记录（记录所有买入/卖出操作，关联持仓明细）主键
     * @return 结果
     */
    public int deleteFinancialTransactionByTransactionId(Long transactionId);
}
