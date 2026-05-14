package com.ruoyi.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockZtPoolDtgcEm;
import com.ruoyi.system.domain.WxTask;
import org.quartz.SchedulerException;

/**
 * 企微消息推送任务Service接口
 *
 * @author DXR
 * @date 2025-06-30
 */
public interface IWxTaskService extends IService<WxTask> {
    /**
     * 查询企微消息推送任务
     *
     * @param wxTaskId 企微消息推送任务主键
     * @return 企微消息推送任务
     */
    public WxTask selectWxTaskByWxTaskId(Long wxTaskId);

    /**
     * 查询企微消息推送任务列表
     *
     * @param wxTask 企微消息推送任务
     * @return 企微消息推送任务集合
     */
    public List<WxTask> selectWxTaskList(WxTask wxTask);

    /**
     * 新增企微消息推送任务
     *
     * @param wxTask 企微消息推送任务
     * @return 结果
     */
    public int insertWxTask(WxTask wxTask) throws SchedulerException;

    /**
     * 修改企微消息推送任务
     *
     * @param wxTask 企微消息推送任务
     * @return 结果
     */
    public int updateWxTask(WxTask wxTask) throws SchedulerException;

    /**
     * 批量删除企微消息推送任务
     *
     * @param wxTaskIds 需要删除的企微消息推送任务主键集合
     * @return 结果
     */
    public int deleteWxTaskByWxTaskIds(Long[] wxTaskIds);

    /**
     * 删除企微消息推送任务信息
     *
     * @param wxTaskId 企微消息推送任务主键
     * @return 结果
     */
    public int deleteWxTaskByWxTaskId(Long wxTaskId);


    public void createWxTask(WxTask wxTask) throws SchedulerException;

    public boolean isValid(String cronExpression);
}
