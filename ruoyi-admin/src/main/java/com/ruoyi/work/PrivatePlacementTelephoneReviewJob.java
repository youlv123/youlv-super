/*
package com.ruoyi.work;

import cn.nesc.wm.visit.enums.OpenDayFlagEnum;
import cn.nesc.wm.visit.enums.PrivateReviewStatusEnum;
import cn.nesc.wm.visit.enums.ReviewChannelEnum;
import cn.nesc.wm.visit.infrustracture.db.mapper.PrivatePlacementMapper;
import cn.nesc.wm.visit.infrustracture.db.model.PrivatePlacementPo;
import cn.nesc.wm.visit.infrustracture.oracle.mapper.T99AcalendarHrsMapper;
import cn.nesc.wm.visit.infrustracture.remote.DcManager;
import cn.nesc.wm.visit.service.PrivatePlacementService;
import cn.nesc.wm.visit.util.DateUtil;
import cn.nesc.wm.visit.util.VisitConstants;
import com.alibaba.fastjson.JSONObject;
import com.tencent.cloud.task.sdk.client.model.ExecutableTaskData;
import com.tencent.cloud.task.sdk.client.model.ProcessResult;
import com.tencent.cloud.task.sdk.client.spi.ExecutableTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

*/
/**
 * 触发私募基金人工外呼回访
 * 非交易日不处理，交易日8:32-17:00扫描private_placement表，获取已过冷静期的未回访状态的专业投资者客户数据，生成人工回访任务，更新回访状态为回访中
 * 每天每隔15分钟执行一次
 *//*

@Component
@Slf4j
public class PrivatePlacementTelephoneReviewJob implements ExecutableTask {

    private static final String JOB_NAME = PrivatePlacementTelephoneReviewJob.class.getSimpleName();

    */
/**
     * 触发回访时是否需要考虑当前时间是否在指定时间范围，以及当天是否为交易日
     * 默认为true需要考虑，但是为了配合测试故新增此开关
     *//*

    @Value("${app.privatePlacement.bizDateFlag}")
    private boolean bizDateFlag;

    @Value("${privatePlacement.startTime}")
    private String startTime;

    @Resource
    private T99AcalendarHrsMapper t99AcalendarHrsMapper;

    @Resource
    private PrivatePlacementMapper privatePlacementMapper;

    @Resource
    private PrivatePlacementService privatePlacementService;

    @Resource
    private DcManager dcManager;

    public ProcessResult doProcess(ExecutableTaskData executableTaskData) {
        LocalDateTime now = LocalDateTime.now();
        if (bizDateFlag) {
            // 判断当前时间是否在8:32-17:00
            LocalDateTime start = now.withHour(8).withMinute(32).withSecond(0);
            LocalDateTime end = now.withHour(17).withMinute(0).withSecond(0);
            if (now.isAfter(end) || now.isBefore(start)) {
                log.info("=== 当前时间[{}]不在指定时间区间，不进行处理", now);
                return ProcessResult.newSuccessResult("当前时间不在指定时间区间，不进行处理");
            }
            // 判断今天是不是交易日
            String today = DateUtil.formatDate(DateUtil.BASIC_ISO_DATE, now);
            String bizDate = dcManager.selectBizDate(today);
            if (!today.equals(bizDate)) {
                log.info("=== 今天[{}]不是交易日，不进行处理", today);
                return ProcessResult.newSuccessResult("今天不是交易日，不进行处理");
            }
        }
        // 今天是交易日
        // 查询需要生成回访的数据
        Long nowTime = Long.valueOf(DateUtil.formatDate(DateUtil.DATE_TIME, now));
        List<String> entrustStatusList = new ArrayList<>();
        entrustStatusList.add(VisitConstants.ENTRUST_STATUS_REVOKE);
        entrustStatusList.add(VisitConstants.ENTRUST_STATUS_CANCEL);
        Date beginTime = DateUtil.parseDateByPattern(startTime, DateUtil.HYPHEN_DATE_TIME);
        List<PrivatePlacementPo> list = privatePlacementMapper.selectInfo(
            ReviewChannelEnum.MANUAL_OUTBOUND_CALL.getCode(),
            PrivateReviewStatusEnum.NO_BEGIN.getKey(), entrustStatusList, nowTime, beginTime,
            OpenDayFlagEnum.IN_OPEN_DAY.getCode());

        if (CollectionUtils.isEmpty(list)) {
            log.info("=== 没有查到需要创建回访的专业投资者");
            return ProcessResult.newSuccessResult("没有查到需要创建回访的专业投资者");
        }
        log.info("=== 生成外呼回访");
        log.info("=== 待创建私募外呼回访的数据：{}", JSONObject.toJSON(list));
        // 根据客户进行分组
        Map<String, List<PrivatePlacementPo>> map = list.stream()
            .collect(Collectors.groupingBy(PrivatePlacementPo::getCustomerId));
        for (String customerId : map.keySet()) {
            List<PrivatePlacementPo> privatePlacementPos = map.get(customerId);
            log.info("=== 客户[{}]开始创建私募外呼回访", customerId);
            try {
                // 创建私募冷静期外呼回访并更新回访状态和回访渠道
                privatePlacementService.createPrivatePlacementTelReview(privatePlacementPos);
                log.info("=== 客户[{}]创建私募外呼回访成功", customerId);
            } catch (Exception ex) {
                log.error("客户[" + customerId + "]创建私募外呼回访失败：" + ex.getMessage(), ex);
            }

        }
        return ProcessResult.newSuccessResult();
    }

    @Override
    public ProcessResult execute(ExecutableTaskData executableTaskData) {
        log.info("=== job[{}] start!", JOB_NAME);
        try {
            return doProcess(executableTaskData);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ProcessResult.newFailResult(ex);
        } finally {
            log.info("=== job[{}] end!", JOB_NAME);
        }
    }

}
*/
