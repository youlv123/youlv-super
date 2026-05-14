package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.TransactionTypeEnum;
import com.ruoyi.common.enums.WxApplicationIdEnum;
import com.ruoyi.common.enums.WxMessageTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.domain.StockZhAHist;
import com.ruoyi.stock.mapper.StockZhAHistMapper;
import com.ruoyi.system.domain.FinancialRecord;
import com.ruoyi.system.domain.FinancialTotal;
import com.ruoyi.system.domain.WxTask;
import com.ruoyi.system.mapper.WxTaskMapper;
import com.ruoyi.system.service.IFinancialTotalService;
import com.ruoyi.system.service.IWxTaskService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FinancialTransactionMapper;
import com.ruoyi.system.domain.FinancialTransaction;
import com.ruoyi.system.service.IFinancialTransactionService;

/**
 * 理财交易记录（记录所有买入/卖出操作，关联持仓明细）Service业务层处理
 *
 * @author DXR
 * @date 2025-10-13
 */
@Service
public class FinancialTransactionServiceImpl extends ServiceImpl<FinancialTransactionMapper, FinancialTransaction> implements IFinancialTransactionService {
    @Autowired
    private FinancialTransactionMapper financialTransactionMapper;


    @Autowired
    private IFinancialTotalService iFinancialTotalService;

    @Autowired
    private IWxTaskService iWxTaskService;

    @Autowired
    private WxTaskMapper wxTaskMapper;


    /**
     * 查询理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param transactionId 理财交易记录（记录所有买入/卖出操作，关联持仓明细）主键
     * @return 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     */
    @Override
    public FinancialTransaction selectFinancialTransactionByTransactionId(Long transactionId) {
        return financialTransactionMapper.selectFinancialTransactionByTransactionId(transactionId);
    }

    /**
     * 查询理财交易记录（记录所有买入/卖出操作，关联持仓明细）列表
     *
     * @param financialTransaction 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     * @return 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     */
    @Override
    public List<FinancialTransaction> selectFinancialTransactionList(FinancialTransaction financialTransaction) {
        return financialTransactionMapper.selectFinancialTransactionList(financialTransaction);
    }

    /**
     * 新增理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param financialTransaction 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     * @return 结果
     */
    @Override
    public int insertFinancialTransaction(FinancialTransaction financialTransaction) {
        return financialTransactionMapper.insertFinancialTransaction(financialTransaction);
    }

    /**
     * 修改理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param financialTransaction 理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     * @return 结果
     */
    @Override
    public int updateFinancialTransaction(FinancialTransaction financialTransaction) {
        return financialTransactionMapper.updateFinancialTransaction(financialTransaction);
    }

    /**
     * 批量删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     *
     * @param transactionIds 需要删除的理财交易记录（记录所有买入/卖出操作，关联持仓明细）主键
     * @return 结果
     */
    @Override
    public int deleteFinancialTransactionByTransactionIds(Long[] transactionIds) {
        return financialTransactionMapper.deleteFinancialTransactionByTransactionIds(transactionIds);
    }

    /**
     * 删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）信息
     *
     * @param transactionId 理财交易记录（记录所有买入/卖出操作，关联持仓明细）主键
     * @return 结果
     */
    @Override
    public int deleteFinancialTransactionByTransactionId(Long transactionId) {
        return financialTransactionMapper.deleteFinancialTransactionByTransactionId(transactionId);
    }


    /*
     * 新增理财交易明细
     *
     * @param financialTransaction 理财交易明细
     * @return 结果
     */
//
//    @Override
//    public int insertFinancialTransaction(FinancialTransaction financialTransaction) throws SchedulerException {
//        // 获取当前用户ID
//        financialTransaction.setCreatedBy(SecurityUtils.getUserId().toString());
//        //计算持有时间
//        Date purchaseTime = financialTransaction.getPurchaseTime();
//        if (null != financialTransaction.getRedemptionTime()) {
//            long l = DateUtils.calculateDaysBetweenAB(purchaseTime, financialTransaction.getRedemptionTime());
//            financialTransaction.setHoldingTime(String.valueOf(l));
//        } else {
//            long l = DateUtils.calculateDaysBetween(purchaseTime);
//            financialTransaction.setHoldingTime(String.valueOf(l));
//        }
//
//        if (TransactionTypeEnum.SELL.getCode().equals(financialTransaction.getTransactionType())) {
//            //判断交易金额是否大于0，不大于0，则取负数
//            if (financialTransaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
//                financialTransaction.setAmount(financialTransaction.getAmount().negate());
//            }
//            //判断交易份额是否大于0，不大于0，则取负数
//            if (financialTransaction.getTransactionShares().compareTo(BigDecimal.ZERO) > 0) {
//                financialTransaction.setTransactionShares(financialTransaction.getTransactionShares().negate());
//            }
//        }
//        //cron表达式不为空，生成提醒任务
//        if (StringUtils.isNotBlank(financialTransaction.getCron())) {
//            //cron表达式不为空，生成提醒任务
//            WxTask wxTask = new WxTask();
//            createWxTask(wxTask, financialTransaction);
//            int i = wxTaskMapper.insertWxTask(wxTask);
//            iWxTaskService.createWxTask(wxTask);
//            financialTransaction.setWxTaskId(wxTask.getWxTaskId());
//        }
//
//        //判断是否有理财汇总表主键ID,没有就创建，说明是第一次新增的
//        if (financialTransaction.getFinancialTotalId() == null) {
//            //创建理财汇总表
//            FinancialTotal financialTotal = new FinancialTotal();
//            financialTotal.setFinancialProductName(financialTransaction.getFinancialProductName());
//            financialTotal.setFinancialProductCode(financialTransaction.getFinancialProductCode());
//            financialTotal.setFinancialGroup(financialTransaction.getFinancialGroup());
//            financialTotal.setLatestNetValue(financialTransaction.getNetValue());
//            financialTotal.setCurrentHoldingShares(financialTransaction.getTransactionShares());
//            financialTotal.setCurrentHoldingAmount(financialTransaction.getAmount());
//            financialTotal.setTotalAmount(financialTransaction.getAmount());
//            financialTotal.setCurrentHoldingProfit(financialTransaction.getProfit());
//            financialTotal.setTotalProfit(financialTransaction.getProfit());
//            financialTotal.setTotalHoldingAmount(financialTransaction.getAmount());
//            if (null != financialTransaction.getProfit()) {
//                //用盈利除以持有天数乘以365再除以本金，计算年化收益率，保留两位小数
//                BigDecimal profit = financialTransaction.getProfit();
//                BigDecimal amount = financialTransaction.getAmount();
//                // 将持有天数（String类型）转换为BigDecimal
//                BigDecimal holdingTime = new BigDecimal(financialTransaction.getHoldingTime());
//
//                // 计算年化收益率：(盈利 / 持有天数 * 365 / 本金) * 100（转为百分比）
//                BigDecimal annualizedYield = profit
//                        // 第一步：盈利 ÷ 持有天数（保留10位小数，四舍五入）
//                        .divide(holdingTime, 10, RoundingMode.HALF_UP)
//                        // 第二步：乘以365
//                        .multiply(new BigDecimal("365"))
//                        // 第三步：除以本金（保留10位小数，四舍五入）
//                        .divide(amount, 10, RoundingMode.HALF_UP)
//                        // 第四步：乘以100转为百分比
//                        .multiply(new BigDecimal("100"))
//                        // 最后保留两位小数（四舍五入）
//                        .setScale(2, RoundingMode.HALF_UP);
//                financialTotal.setCurrentHoldingYield(annualizedYield);
//                financialTotal.setTotalYield(annualizedYield);
//            }
//            iFinancialTotalService.insertFinancialTotal(financialTotal);
//
//        } else {
//            //查询出理财汇总表数据,更新数据
//            FinancialTotal financialTotal = iFinancialTotalService.selectFinancialTotalByFinancialTotalId(financialTransaction.getFinancialTotalId());
//            financialTotal.setCurrentHoldingShares(financialTotal.getCurrentHoldingShares().add(financialTransaction.getTransactionShares()));
//            financialTotal.setCurrentHoldingAmount(financialTotal.getCurrentHoldingAmount().add(financialTransaction.getAmount()));
//            financialTotal.setTotalAmount(financialTotal.getTotalAmount().add(financialTransaction.getAmount()));
//            financialTotal.setTotalHoldingAmount(financialTotal.getTotalHoldingAmount().add(financialTransaction.getAmount()));
//            financialTotal.setCurrentHoldingProfit(financialTotal.getCurrentHoldingProfit().add(financialTransaction.getProfit()));
//            financialTotal.setTotalProfit(financialTotal.getTotalProfit().add(financialTransaction.getProfit()));
//            financialTotal.setCurrentHoldingYield(financialTotal.getCurrentHoldingProfit().divide(financialTotal.getCurrentHoldingAmount(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
//            financialTotal.setTotalYield(financialTotal.getTotalProfit().divide(financialTotal.getTotalAmount(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
//            iFinancialTotalService.updateFinancialTotal(financialTotal);
//        }
//
//        return financialTransactionMapper.insertFinancialTransaction(financialTransaction);
//    }
//
//    /*
//     * 修改理财交易明细
//     *
//     * @param financialTransaction 理财交易明细
//     * @return 结果
//     */
//
//    @Override
//    public int updateFinancialTransaction(FinancialTransaction financialTransaction) throws SchedulerException {
//        Date purchaseTime = financialTransaction.getPurchaseTime();
//        if (null != financialTransaction.getRedemptionTime()) {
//            long l = DateUtils.calculateDaysBetweenAB(purchaseTime, financialTransaction.getRedemptionTime());
//            financialTransaction.setHoldingTime(String.valueOf(l));
//        } else {
//            long l = DateUtils.calculateDaysBetween(purchaseTime);
//            financialTransaction.setHoldingTime(String.valueOf(l));
//        }
//        //如果是卖出
//        if (TransactionTypeEnum.SELL.getCode().equals(financialTransaction.getTransactionType())) {
//            //判断是否大于0，不大于0，则取负数
//            if (financialTransaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
//                financialTransaction.setAmount(financialTransaction.getAmount().negate());
//            }
//            //判断交易份额是否大于0，不大于0，则取负数
//            if (financialTransaction.getTransactionShares().compareTo(BigDecimal.ZERO) > 0) {
//                financialTransaction.setTransactionShares(financialTransaction.getTransactionShares().negate());
//            }
//        }
//        //计算收益率
//        if (null != financialTransaction.getProfit()) {
//            //用盈利除以持有天数乘以365再除以本金，计算年化收益率，保留两位小数
//            BigDecimal profit = financialTransaction.getProfit();
//            BigDecimal amount = financialTransaction.getAmount();
//            // 将持有天数（String类型）转换为BigDecimal
//            BigDecimal holdingTime = new BigDecimal(financialTransaction.getHoldingTime());
//
//            // 计算年化收益率：(盈利 / 持有天数 * 365 / 本金) * 100（转为百分比）
//            BigDecimal annualizedYield = profit
//                    // 第一步：盈利 ÷ 持有天数（保留10位小数，四舍五入）
//                    .divide(holdingTime, 10, RoundingMode.HALF_UP)
//                    // 第二步：乘以365
//                    .multiply(new BigDecimal("365"))
//                    // 第三步：除以本金（保留10位小数，四舍五入）
//                    .divide(amount, 10, RoundingMode.HALF_UP)
//                    // 第四步：乘以100转为百分比
//                    .multiply(new BigDecimal("100"))
//                    // 最后保留两位小数（四舍五入）
//                    .setScale(2, RoundingMode.HALF_UP);
//            financialTransaction.setYield(annualizedYield);
//        }
//        //修改推送任务
//        if (null != financialTransaction.getWxTaskId()) {
//            WxTask wxTask = iWxTaskService.selectWxTaskByWxTaskId(financialTransaction.getWxTaskId());
//            if (Objects.isNull(wxTask)) {
//                wxTask = new WxTask();
//            }
//            createWxTask(wxTask, financialTransaction);
//            iWxTaskService.updateWxTask(wxTask);
//        } else if (StringUtils.isNotBlank(financialTransaction.getCron())) {
//            WxTask wxTask = new WxTask();
//            createWxTask(wxTask, financialTransaction);
//            int i = wxTaskMapper.insertWxTask(wxTask);
//            iWxTaskService.createWxTask(wxTask);
//            financialTransaction.setWxTaskId(wxTask.getWxTaskId());
//        }
//        //先更新这条数据
//        financialTransactionMapper.updateFinancialTransaction(financialTransaction);
//        //再把这个汇总id下面的所有数据拿出来
//        LambdaQueryWrapper<FinancialTransaction> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(FinancialTransaction::getFinancialTotalId, financialTransaction.getFinancialTotalId());
//        List<FinancialTransaction> list = financialTransactionMapper.selectList(queryWrapper);
//
//        //持仓成本价
//        BigDecimal currentHoldingCost = BigDecimal.ZERO;
//        //当前持有份额
//        BigDecimal currentHoldingShares = BigDecimal.ZERO;
//        //当前持有金额
//        BigDecimal currentHoldingAmount = BigDecimal.ZERO;
//        //当前持有盈亏
//        BigDecimal currentHoldingProfit = BigDecimal.ZERO;
//        //总盈亏
//        BigDecimal totalProfit = BigDecimal.ZERO;
//        //当前持有收益率
//        BigDecimal currentHoldingYield = BigDecimal.ZERO;
//        //历史收益率
//        BigDecimal hisTotalYield = BigDecimal.ZERO;
//
//        for (FinancialTransaction transaction : list) {
//            //买入
//            if (TransactionTypeEnum.BUY.getCode().equals(transaction.getTransactionType())) {
//
//            } else if (TransactionTypeEnum.SELL.getCode().equals(transaction.getTransactionType())) {//卖出
//
//            }
//            //计算持有的所有份额，卖出的是负数，所以这里一直加就行
//            currentHoldingShares.add(transaction.getTransactionShares());
//        }
//        //计算规则：最新净值 x 当前持有份额
//        currentHoldingAmount = currentHoldingShares.multiply(financialTransaction.getNetValue());
//
//        currentHoldingCost
//        //查询出理财汇总表数据,更新数据
//        FinancialTotal financialTotal = iFinancialTotalService.selectFinancialTotalByFinancialTotalId(financialTransaction.getFinancialTotalId());
//        financialTotal.setCurrentHoldingCost(currentHoldingCost);
//        financialTotal.setCurrentHoldingShares(currentHoldingShares);
//        financialTotal.setCurrentHoldingAmount(currentHoldingAmount);
//        financialTotal.setCurrentHoldingProfit(financialTotal.getCurrentHoldingProfit().subtract(financialTransaction.getProfit()));
//        financialTotal.setTotalProfit(financialTotal.getTotalProfit().subtract(financialTransaction.getProfit()));
//        financialTotal.setCurrentHoldingYield(financialTotal.getCurrentHoldingProfit().divide(financialTotal.getCurrentHoldingAmount(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
//        financialTotal.setTotalYield(financialTotal.getTotalProfit().divide(financialTotal.getTotalAmount(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
//
//
//        return iFinancialTotalService.updateFinancialTotal(financialTotal);
//    }
//
//
//
//    /*
//     * 创建微信任务实体
//     *
//     * @param wxTask
//     * @param financialRecord
//     * @return
//     */
//
//    public WxTask createWxTask(WxTask wxTask, FinancialTransaction financialRecord) throws SchedulerException {
//        if (!iWxTaskService.isValid(financialRecord.getCron())) {
//            throw new SchedulerException("cron表达式有误");
//        }
//        String username = SecurityUtils.getUsername();
//        String wxMessageContent = "您在" + financialRecord.getPurchasePlatform() + "平台购买的[" + financialRecord.getFinancialProductName()
//                + "]理财产品即将到期，到期时间为" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, financialRecord.getExpirationDate())
//                + "，到期金额为" + financialRecord.getAmount() + "，赎回路径为：" + financialRecord.getRedemptionPath() + "，请及时处理。";
//
//        wxTask.setTaskName(financialRecord.getFinancialProductName());
//        wxTask.setTaskGroup(WxMessageTypeEnum.FINANCE_NOTICE.getCode());
//        wxTask.setCron(financialRecord.getCron());
//        wxTask.setTaskStatus("0");
//        wxTask.setUserId(SecurityUtils.getUserId().toString());
//        wxTask.setUserName(username);
//        wxTask.setWxId("qy01b3e5ccd3ccc10128f37211ed");
//        wxTask.setApplicationId(WxApplicationIdEnum.FINANCE_NOTICE.getCode());
//        wxTask.setWxMessageContent(wxMessageContent);
//        wxTask.setCreatedBy(username);
//        wxTask.setUpdatedBy(username);
//        return wxTask;
//    }
//

}
