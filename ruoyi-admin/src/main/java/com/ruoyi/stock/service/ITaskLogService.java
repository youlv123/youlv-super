package com.ruoyi.stock.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.TaskLog;

/**
 * 任务日志Service接口
 *
 * @author DXR
 * @date 2025-08-27
 */
public interface ITaskLogService extends IService<TaskLog> {
    /**
     * 查询任务日志
     *
     * @param taskLogId 任务日志主键
     * @return 任务日志
     */
    public TaskLog selectTaskLogByTaskLogId(Long taskLogId);

    /**
     * 查询任务日志列表
     *
     * @param taskLog 任务日志
     * @return 任务日志集合
     */
    public List<TaskLog> selectTaskLogList(TaskLog taskLog);

    /**
     * 新增任务日志
     *
     * @param taskLog 任务日志
     * @return 结果
     */
    public int insertTaskLog(TaskLog taskLog);

    /**
     * 修改任务日志
     *
     * @param taskLog 任务日志
     * @return 结果
     */
    public int updateTaskLog(TaskLog taskLog);

    /**
     * 批量删除任务日志
     *
     * @param taskLogIds 需要删除的任务日志主键集合
     * @return 结果
     */
    public int deleteTaskLogByTaskLogIds(Long[] taskLogIds);

    /**
     * 删除任务日志信息
     *
     * @param taskLogId 任务日志主键
     * @return 结果
     */
    public int deleteTaskLogByTaskLogId(Long taskLogId);
}
