package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.config.WxConfig;
import com.ruoyi.quartz.util.CronUtils;
import com.ruoyi.stock.domain.StockZhAHist;
import com.ruoyi.stock.mapper.StockZhAHistMapper;
import com.ruoyi.system.task.EveryDayTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxTaskMapper;
import com.ruoyi.system.domain.WxTask;
import com.ruoyi.system.service.IWxTaskService;

import javax.annotation.Resource;

import org.springframework.scheduling.support.CronExpression;

/**
 * 企微消息推送任务Service业务层处理
 *
 * @author DXR
 * @date 2025-06-30
 */
@Service
@Slf4j
public class WxTaskServiceImpl extends ServiceImpl<WxTaskMapper, WxTask> implements IWxTaskService {
    @Autowired
    private WxTaskMapper wxTaskMapper;

    @Autowired
    private Scheduler sched;


    /**
     * 查询企微消息推送任务
     *
     * @param wxTaskId 企微消息推送任务主键
     * @return 企微消息推送任务
     */
    @Override
    public WxTask selectWxTaskByWxTaskId(Long wxTaskId) {
        return wxTaskMapper.selectWxTaskByWxTaskId(wxTaskId);
    }

    /**
     * 查询企微消息推送任务列表
     *
     * @param wxTask 企微消息推送任务
     * @return 企微消息推送任务
     */
    @Override
    public List<WxTask> selectWxTaskList(WxTask wxTask) {
        return wxTaskMapper.selectWxTaskList(wxTask);
    }

    /**
     * 新增企微消息推送任务
     *
     * @param wxTask 企微消息推送任务
     * @return 结果
     */
    @Override
    public int insertWxTask(WxTask wxTask) throws SchedulerException {
        //校验cron表达式
        if (!isValid(wxTask.getCron())) {
            throw new SchedulerException("cron表达式有误");
        }
        String username = SecurityUtils.getUsername();
        wxTask.setCreatedBy(username);
        wxTask.setUpdatedBy(username);
        int i = wxTaskMapper.insertWxTask(wxTask);
        createWxTask(wxTask);
        return i;
    }

    /**
     * 修改企微消息推送任务
     *
     * @param wxTask 企微消息推送任务
     * @return 结果
     */
    @Override
    public int updateWxTask(WxTask wxTask) throws SchedulerException {
        //校验cron表达式
        if (!isValid(wxTask.getCron())) {
            throw new SchedulerException("cron表达式有误");
        }

        deleteWxTask(wxTask.getWxTaskId());
        if ("0".equals(wxTask.getTaskStatus())) {
            createWxTask(wxTask);
        }
        String username = SecurityUtils.getUsername();
        wxTask.setUpdatedBy(username);
        return wxTaskMapper.updateWxTask(wxTask);
    }

    /**
     * 批量删除企微消息推送任务
     *
     * @param wxTaskIds 需要删除的企微消息推送任务主键
     * @return 结果
     */
    @Override
    public int deleteWxTaskByWxTaskIds(Long[] wxTaskIds) {
        for (Long wxTaskId : wxTaskIds) {
            deleteWxTask(wxTaskId);
        }
        return wxTaskMapper.deleteWxTaskByWxTaskIds(wxTaskIds);
    }

    /**
     * 删除企微消息推送任务信息
     *
     * @param wxTaskId 企微消息推送任务主键
     * @return 结果
     */
    @Override
    public int deleteWxTaskByWxTaskId(Long wxTaskId) {
        deleteWxTask(wxTaskId);
        return wxTaskMapper.deleteWxTaskByWxTaskId(wxTaskId);
    }

    /**
     * 删除任务
     *
     * @param wxTaskId
     */
    public void deleteWxTask(Long wxTaskId) {
        try {
            JobKey jobKey = new JobKey("everyDayJob", wxTaskId.toString());
            if (sched.checkExists(jobKey)) {
                boolean success = sched.deleteJob(jobKey);
                log.info(wxTaskId + "--任务删除 " + (success ? "成功" : "失败"));
            } else {
                log.info(wxTaskId + "--任务不存在，无需删除");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 新增任务
     *
     * @param wxTask
     */
    public void createWxTask(WxTask wxTask) throws SchedulerException {
        //校验cron表达式
        if (!isValid(wxTask.getCron())) {
            throw new SchedulerException("cron表达式有误");
        }

        //组名
        JobDetail job = JobBuilder.newJob(EveryDayTask.class).
                withIdentity("everyDayJob", wxTask.getWxTaskId().toString()).build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("everyDayTrigger", wxTask.getWxTaskId().toString())//触发器唯一
                .usingJobData("userId", wxTask.getUserId())//传递参数
                .usingJobData("userName", wxTask.getUserName())
                .usingJobData("wxId", wxTask.getWxId())
                .usingJobData("applicationId", wxTask.getApplicationId())
                .usingJobData("wxMessageContent", wxTask.getWxMessageContent())
                .usingJobData("wxTaskId", wxTask.getWxTaskId().toString())
                .usingJobData("taskGroup", wxTask.getTaskGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(wxTask.getCron()))
                .build();
        // 2. 启动调度器（只需一次）
        if (!sched.isStarted()) {
            sched.start();
        }
        sched.scheduleJob(job, trigger); // 调度任务
        log.info(wxTask.getWxTaskId().toString() + "--任务创建 " + "成功");
    }

    /**
     * 校验cron表达式
     *
     * @param cronExpression
     * @return
     */
    public boolean isValid(String cronExpression) {
//        return CronExpression.isValidExpression(cronExpression);
        return CronUtils.isValid(cronExpression);
    }
}
