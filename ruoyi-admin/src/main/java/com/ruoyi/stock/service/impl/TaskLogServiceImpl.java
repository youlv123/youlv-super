package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.TaskLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.TaskLogMapper;
import com.ruoyi.stock.service.ITaskLogService;

/**
 * 任务日志Service业务层处理
 *
 * @author DXR
 * @date 2025-08-27
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog>  implements ITaskLogService {
    @Autowired
    private TaskLogMapper taskLogMapper;

    /**
     * 查询任务日志
     *
     * @param taskLogId 任务日志主键
     * @return 任务日志
     */
    @Override
    public TaskLog selectTaskLogByTaskLogId(Long taskLogId) {
        return taskLogMapper.selectTaskLogByTaskLogId(taskLogId);
    }

    /**
     * 查询任务日志列表
     *
     * @param taskLog 任务日志
     * @return 任务日志
     */
    @Override
    public List<TaskLog> selectTaskLogList(TaskLog taskLog) {
        return taskLogMapper.selectTaskLogList(taskLog);
    }

    /**
     * 新增任务日志
     *
     * @param taskLog 任务日志
     * @return 结果
     */
    @Override
    public int insertTaskLog(TaskLog taskLog) {
        return taskLogMapper.insertTaskLog(taskLog);
    }

    /**
     * 修改任务日志
     *
     * @param taskLog 任务日志
     * @return 结果
     */
    @Override
    public int updateTaskLog(TaskLog taskLog) {
        return taskLogMapper.updateTaskLog(taskLog);
    }

    /**
     * 批量删除任务日志
     *
     * @param taskLogIds 需要删除的任务日志主键
     * @return 结果
     */
    @Override
    public int deleteTaskLogByTaskLogIds(Long[] taskLogIds) {
        return taskLogMapper.deleteTaskLogByTaskLogIds(taskLogIds);
    }

    /**
     * 删除任务日志信息
     *
     * @param taskLogId 任务日志主键
     * @return 结果
     */
    @Override
    public int deleteTaskLogByTaskLogId(Long taskLogId) {
        return taskLogMapper.deleteTaskLogByTaskLogId(taskLogId);
    }
}
