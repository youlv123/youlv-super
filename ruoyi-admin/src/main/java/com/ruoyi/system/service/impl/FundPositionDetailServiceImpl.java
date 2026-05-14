package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.FinancialGroupEnum;
import com.ruoyi.common.enums.TransactionTypeEnum;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.FinancialTotal;
import com.ruoyi.system.domain.FinancialTransaction;
import com.ruoyi.system.domain.FundTransactionRelation;
import com.ruoyi.system.mapper.FinancialTotalMapper;
import com.ruoyi.system.mapper.FinancialTransactionMapper;
import com.ruoyi.system.mapper.FundTransactionRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FundPositionDetailMapper;
import com.ruoyi.system.domain.FundPositionDetail;
import com.ruoyi.system.service.IFundPositionDetailService;

import javax.transaction.Transactional;

/**
 * 理财持仓明细（每笔买入独立记录，FIFO计算核心）Service业务层处理
 *
 * @author DXR
 * @date 2025-10-13
 */
@Service
public class FundPositionDetailServiceImpl extends ServiceImpl<FundPositionDetailMapper, FundPositionDetail> implements IFundPositionDetailService {
    @Autowired
    private FundPositionDetailMapper fundPositionDetailMapper;

    @Autowired
    private FinancialTransactionMapper financialTransactionMapper;
    @Autowired
    private FundTransactionRelationMapper fundTransactionRelationMapper;

    @Autowired
    private FinancialTotalMapper financialTotalMapper;

    /**
     * 查询理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundDetailId 理财持仓明细（每笔买入独立记录，FIFO计算核心）主键
     * @return 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     */
    @Override
    public FundPositionDetail selectFundPositionDetailByFundDetailId(Long fundDetailId) {
        return fundPositionDetailMapper.selectFundPositionDetailByFundDetailId(fundDetailId);
    }

    /**
     * 查询理财持仓明细（每笔买入独立记录，FIFO计算核心）列表
     *
     * @param fundPositionDetail 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     * @return 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     */
    @Override
    public List<FundPositionDetail> selectFundPositionDetailList(FundPositionDetail fundPositionDetail) {
        return fundPositionDetailMapper.selectFundPositionDetailList(fundPositionDetail);
    }

    /**
     * 新增理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundPositionDetail 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     * @return 结果
     */
    @Override
    @Transactional
    public int insertFundPositionDetail(FundPositionDetail fundPositionDetail) {
        //拿到交易类型
        String transType = fundPositionDetail.getTransType();
        BigDecimal totalShares = fundPositionDetail.getTotalShares();
        //拿到理财分组
        String financialGroup = fundPositionDetail.getFinancialGroup();
        if (FinancialGroupEnum.FUND.getCode().equals(financialGroup) | FinancialGroupEnum.ETF.getCode().equals(financialGroup)) {

            //查询财务总表，没有就新增一条
            LambdaQueryWrapper<FinancialTotal> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(FinancialTotal::getFinancialProductCode, fundPositionDetail.getFinancialProductCode());
            FinancialTotal financialTotal = financialTotalMapper.selectOne(queryWrapper);
            //新增理财交易记录表
            FinancialTransaction financialTransaction = new FinancialTransaction();
            financialTransaction.setFinancialProductCode(fundPositionDetail.getFinancialProductCode());
            financialTransaction.setFinancialProductName(fundPositionDetail.getFinancialProductName());
            financialTransaction.setFinancialGroup(fundPositionDetail.getFinancialGroup());
            financialTransaction.setTransType(fundPositionDetail.getTransType());
            financialTransaction.setTransTime(fundPositionDetail.getPurchaseTime());
            financialTransaction.setTransShares(totalShares);
            financialTransaction.setTransPrincipal(fundPositionDetail.getBuyPrincipal());
            financialTransaction.setTransNetValue(fundPositionDetail.getBuyNetValue());
            financialTransaction.setTransFee(fundPositionDetail.getBuyFee());
            financialTransactionMapper.insertFinancialTransaction(financialTransaction);
            //如果是买入
            if (TransactionTypeEnum.BUY.getCode().equals(transType)) {
                //新增理财持仓明细
                fundPositionDetail.setCreatedBy(SecurityUtils.getUserId().toString());
                fundPositionDetail.setRemainingShares(totalShares);
                fundPositionDetailMapper.insertFundPositionDetail(fundPositionDetail);

                //新增关联持仓明细对象
                FundTransactionRelation fundTransactionRelation = new FundTransactionRelation();
                fundTransactionRelation.setTransactionId(financialTransaction.getTransactionId());
                fundTransactionRelation.setFundDetailId(fundPositionDetail.getFundDetailId());
                fundTransactionRelation.setTransShares(totalShares);
                fundTransactionRelation.setTransType(transType);
                fundTransactionRelationMapper.insertFundTransactionRelation(fundTransactionRelation);

                if (financialTotal == null) {//财务总表，没有就新增一条
                    FinancialTotal dto = new FinancialTotal();
                    dto.setFinancialProductName(fundPositionDetail.getFinancialProductName());
                    dto.setFinancialProductCode(fundPositionDetail.getFinancialProductCode());
                    dto.setFinancialGroup(financialGroup);
                    dto.setLatestNetValue(fundPositionDetail.getBuyNetValue());
                    dto.setCurrentHoldingCost(fundPositionDetail.getBuyPrincipal().divide(totalShares, 4, RoundingMode.HALF_UP));
                    dto.setCurrentHoldingShares(totalShares);
                    dto.setCurrentHoldingAmount(fundPositionDetail.getBuyPrincipal());
                    dto.setCurrentHoldingProfit(BigDecimal.ZERO);
                    dto.setTotalProfit(BigDecimal.ZERO);
                    dto.setCurrentHoldingYield(BigDecimal.ZERO);
                    dto.setHisTotalYield(BigDecimal.ZERO);
                    financialTotalMapper.insertFinancialTotal(dto);
                } else {//如果有就进行更新
                    //* 当前持有总份额
                    financialTotal.setCurrentHoldingShares(financialTotal.getCurrentHoldingShares().add(totalShares));
                    //* 当前持有总金额
                    financialTotal.setCurrentHoldingAmount(financialTotal.getCurrentHoldingAmount().add(fundPositionDetail.getBuyPrincipal()));
                    //* 持仓成本价
                    //* 计算规则：当前持有的买入的总金额/当前持有总份额
                    financialTotal.setCurrentHoldingCost(financialTotal.getCurrentHoldingAmount().divide(financialTotal.getCurrentHoldingShares(), 4, RoundingMode.HALF_UP));
                    //* 当前持有收益率  (最新净值 - 持仓成本价)/持仓成本价
                    financialTotal.setCurrentHoldingYield(financialTotal.getLatestNetValue().subtract(financialTotal.getCurrentHoldingCost()).divide(financialTotal.getCurrentHoldingCost(), 4, RoundingMode.HALF_UP));
                    //更新统计表
                    financialTotalMapper.updateFinancialTotal(financialTotal);
                }

                return 1;
            } else {
                //如果是卖出
                if (financialTotal == null) {//财务总表为空，则报错，没有的数据不可以卖出
                    throw new RuntimeException("没有数据，无法卖出");
                }
                if (financialTotal.getCurrentHoldingShares().compareTo(totalShares) < 0){//卖出份额大于当前总持有份额
                    throw new RuntimeException("卖出份额大于当前总持有份额！");
                }
                //财务总表，数据进行更新
                //* 当前持有总份额
                financialTotal.setCurrentHoldingShares(financialTotal.getCurrentHoldingShares().subtract(fundPositionDetail.getTotalShares()));
                //* 当前持有总金额
                financialTotal.setCurrentHoldingAmount(financialTotal.getCurrentHoldingAmount().subtract(fundPositionDetail.getBuyPrincipal()));
                //* 持仓成本价
                //* 计算规则：当前持有的买入的总金额/当前持有总份额
                financialTotal.setCurrentHoldingCost(financialTotal.getCurrentHoldingAmount().divide(financialTotal.getCurrentHoldingShares(), 4, RoundingMode.HALF_UP));
                //* 当前持有收益率  (最新净值 - 持仓成本价)/持仓成本价
                financialTotal.setCurrentHoldingYield(financialTotal.getLatestNetValue().subtract(financialTotal.getCurrentHoldingCost()).divide(financialTotal.getCurrentHoldingCost(), 4, RoundingMode.HALF_UP));
                //更新统计表
                financialTotalMapper.updateFinancialTotal(financialTotal);


                //查询剩余份额是否大于0，如果小于0，则返回错误，如果当前扣减份额大于剩余份额，则返回错误
                LambdaQueryWrapper<FundPositionDetail> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(FundPositionDetail::getFinancialProductCode, fundPositionDetail.getFinancialProductCode());
                // 获取当前用户ID
                wrapper.eq(FundPositionDetail::getCreatedBy, SecurityUtils.getUserId().toString());
                //根据买入时间升序排序（先进先出）
                wrapper.orderByAsc(FundPositionDetail::getPurchaseTime);
                List<FundPositionDetail> detailList = fundPositionDetailMapper.selectList(wrapper);

                for (FundPositionDetail detail : detailList) {
                    //如果剩余份额等于卖出份额，则直接更新这条数据
                    if (detail.getRemainingShares().compareTo(totalShares) == 0) {
                        detail.setRemainingShares(BigDecimal.ZERO);
                        //更新这条数据
                        fundPositionDetailMapper.updateFundPositionDetail(detail);
                    } else if (detail.getRemainingShares().compareTo(totalShares) < 0) {//如果剩余份额小于卖出份额，则减去剩余份额
                        detail.setRemainingShares(BigDecimal.ZERO);
                        totalShares = totalShares.subtract(detail.getRemainingShares());
                        //更新这条数据
                        fundPositionDetailMapper.updateFundPositionDetail(detail);
                    } else {//如果剩余份额大于卖出份额，则减去卖出份额
                        detail.setRemainingShares(detail.getRemainingShares().subtract(totalShares));
                        fundPositionDetailMapper.updateFundPositionDetail(detail);
                    }
                    //新增关联持仓明细对象
                    FundTransactionRelation fundTransactionRelation = new FundTransactionRelation();
                    fundTransactionRelation.setTransactionId(financialTransaction.getTransactionId());
                    fundTransactionRelation.setFundDetailId(fundPositionDetail.getFundDetailId());
                    fundTransactionRelation.setTransShares(fundPositionDetail.getTotalShares());
                    fundTransactionRelation.setTransType(transType);
                    fundTransactionRelationMapper.insertFundTransactionRelation(fundTransactionRelation);
                }
                if (fundPositionDetail.getRemainingShares().compareTo(BigDecimal.ZERO) < 0) {

                }
            }


        }

        return 1;
    }

    /**
     * 修改理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundPositionDetail 理财持仓明细（每笔买入独立记录，FIFO计算核心）
     * @return 结果
     */
    @Override
    public int updateFundPositionDetail(FundPositionDetail fundPositionDetail) {
        return fundPositionDetailMapper.updateFundPositionDetail(fundPositionDetail);
    }

    /**
     * 批量删除理财持仓明细（每笔买入独立记录，FIFO计算核心）
     *
     * @param fundDetailIds 需要删除的理财持仓明细（每笔买入独立记录，FIFO计算核心）主键
     * @return 结果
     */
    @Override
    public int deleteFundPositionDetailByFundDetailIds(Long[] fundDetailIds) {
        return fundPositionDetailMapper.deleteFundPositionDetailByFundDetailIds(fundDetailIds);
    }

    /**
     * 删除理财持仓明细（每笔买入独立记录，FIFO计算核心）信息
     *
     * @param fundDetailId 理财持仓明细（每笔买入独立记录，FIFO计算核心）主键
     * @return 结果
     */
    @Override
    public int deleteFundPositionDetailByFundDetailId(Long fundDetailId) {
        return fundPositionDetailMapper.deleteFundPositionDetailByFundDetailId(fundDetailId);
    }
}
