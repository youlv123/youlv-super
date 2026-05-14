package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.WxApplicationIdEnum;
import com.ruoyi.common.enums.WxMessageTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.WxTask;
import com.ruoyi.system.mapper.WxTaskMapper;
import com.ruoyi.system.service.IWxTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FinancialRecordMapper;
import com.ruoyi.system.domain.FinancialRecord;
import com.ruoyi.system.service.IFinancialRecordService;

/**
 * 理财记录Service业务层处理
 *
 * @author DXR
 * @date 2025-07-07
 */
@Service
@Slf4j
public class FinancialRecordServiceImpl extends ServiceImpl<FinancialRecordMapper, FinancialRecord> implements IFinancialRecordService {
    @Autowired
    private FinancialRecordMapper financialRecordMapper;

    @Autowired
    private IWxTaskService iWxTaskService;

    @Autowired
    private WxTaskMapper wxTaskMapper;

    /**
     * 查询理财记录
     *
     * @param financialRecordId 理财记录主键
     * @return 理财记录
     */
    @Override
    public FinancialRecord selectFinancialRecordByFinancialRecordId(Long financialRecordId) {
        return financialRecordMapper.selectFinancialRecordByFinancialRecordId(financialRecordId);
    }

    /**
     * 查询理财记录列表
     *
     * @param financialRecord 理财记录
     * @return 理财记录
     */
    @Override
    public List<FinancialRecord> selectFinancialRecordList(FinancialRecord financialRecord) {
        return financialRecordMapper.selectFinancialRecordList(financialRecord);
    }

    /**
     * 新增理财记录
     *
     * @param financialRecord 理财记录
     * @return 结果
     */
    @Override
    public int insertFinancialRecord(FinancialRecord financialRecord) throws SchedulerException {
        //计算持有时间
        Date purchaseTime = financialRecord.getPurchaseTime();
        if (null != purchaseTime) {
            long l = DateUtils.calculateDaysBetween(purchaseTime);
            financialRecord.setHoldingTime(String.valueOf(l));
        }

        if (StringUtils.isNotBlank(financialRecord.getCron())) {
            //cron表达式不为空，生成提醒任务
            WxTask wxTask = new WxTask();
            createWxTask(wxTask, financialRecord);
            int i = wxTaskMapper.insertWxTask(wxTask);
            iWxTaskService.createWxTask(wxTask);
            financialRecord.setWxTaskId(wxTask.getWxTaskId());
        }

        return financialRecordMapper.insertFinancialRecord(financialRecord);
    }

    /**
     * 修改理财记录
     *
     * @param financialRecord 理财记录
     * @return 结果
     */
    @Override
    public int updateFinancialRecord(FinancialRecord financialRecord) throws SchedulerException {
        Date purchaseTime = financialRecord.getPurchaseTime();
        if (null != financialRecord.getRedemptionTime()) {
            long l = DateUtils.calculateDaysBetweenAB(purchaseTime, financialRecord.getRedemptionTime());
            financialRecord.setHoldingTime(String.valueOf(l));
        } else{
            long l = DateUtils.calculateDaysBetween(purchaseTime);
            financialRecord.setHoldingTime(String.valueOf(l));
        }
        if (StringUtils.isNotBlank(financialRecord.getProfit())) {
            //用盈利除以持有天数乘以365再除以本金，计算年化收益率，保留两位小数
            String yield = String.format("%.2f", Double.parseDouble(financialRecord.getProfit()) * 100 / Double.parseDouble(financialRecord.getHoldingTime()) * 365 / Double.parseDouble(financialRecord.getAmount()));
            financialRecord.setYield(yield + "%");
        }

        if (null != financialRecord.getWxTaskId()) {
            WxTask wxTask = iWxTaskService.selectWxTaskByWxTaskId(financialRecord.getWxTaskId());
            if (Objects.isNull(wxTask)) {
                wxTask = new WxTask();
            }
            createWxTask(wxTask, financialRecord);
            iWxTaskService.updateWxTask(wxTask);
        } else if (StringUtils.isNotBlank(financialRecord.getCron())) {
            WxTask wxTask = new WxTask();
            createWxTask(wxTask, financialRecord);
            int i = wxTaskMapper.insertWxTask(wxTask);
            iWxTaskService.createWxTask(wxTask);
            financialRecord.setWxTaskId(wxTask.getWxTaskId());
        }
        return financialRecordMapper.updateFinancialRecord(financialRecord);
    }

    /**
     * 批量删除理财记录
     *
     * @param financialRecordIds 需要删除的理财记录主键
     * @return 结果
     */
    @Override
    public int deleteFinancialRecordByFinancialRecordIds(Long[] financialRecordIds) {
        for (Long financialRecordId : financialRecordIds) {
            FinancialRecord financialRecord = financialRecordMapper.selectFinancialRecordByFinancialRecordId(financialRecordId);
            if (null != financialRecord.getWxTaskId()) {
                iWxTaskService.deleteWxTaskByWxTaskId(financialRecord.getWxTaskId());
            }
        }
        return financialRecordMapper.deleteFinancialRecordByFinancialRecordIds(financialRecordIds);
    }

    /**
     * 删除理财记录信息
     *
     * @param financialRecordId 理财记录主键
     * @return 结果
     */
    @Override
    public int deleteFinancialRecordByFinancialRecordId(Long financialRecordId) {
        FinancialRecord financialRecord = financialRecordMapper.selectFinancialRecordByFinancialRecordId(financialRecordId);
        if (null != financialRecord.getWxTaskId()) {
            iWxTaskService.deleteWxTaskByWxTaskId(financialRecord.getWxTaskId());
        }

        return financialRecordMapper.deleteFinancialRecordByFinancialRecordId(financialRecordId);
    }

    /**
     * 创建微信任务实体
     *
     * @param wxTask
     * @param financialRecord
     * @return
     */
    public WxTask createWxTask(WxTask wxTask, FinancialRecord financialRecord) throws SchedulerException {
        if (!iWxTaskService.isValid(financialRecord.getCron())) {
            throw new SchedulerException("cron表达式有误");
        }
        String username = SecurityUtils.getUsername();
        String wxMessageContent = "您在" + financialRecord.getPurchasePlatform() + "平台购买的[" + financialRecord.getFinancialProductName()
                + "]理财产品即将到期，到期时间为" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, financialRecord.getExpirationDate())
                + "，到期金额为" + financialRecord.getAmount() + "，赎回路径为：" + financialRecord.getRedemptionPath() + "，请及时处理。";

        wxTask.setTaskName(financialRecord.getFinancialProductName());
        wxTask.setTaskGroup(WxMessageTypeEnum.FINANCE_NOTICE.getCode());
        wxTask.setCron(financialRecord.getCron());
        wxTask.setTaskStatus("0");
        wxTask.setUserId(SecurityUtils.getUserId().toString());
        wxTask.setUserName(username);
        wxTask.setWxId("qy01b3e5ccd3ccc10128f37211ed");
        wxTask.setApplicationId(WxApplicationIdEnum.FINANCE_NOTICE.getCode());
        wxTask.setWxMessageContent(wxMessageContent);
        wxTask.setCreatedBy(username);
        wxTask.setUpdatedBy(username);
        return wxTask;
    }
}
