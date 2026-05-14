package com.ruoyi.system.service.impl;


import com.ruoyi.system.domain.PushWxMessageRecord;
import com.ruoyi.system.domain.WxTask;
import com.ruoyi.system.mapper.PushWxMessageRecordMapper;
import com.ruoyi.system.mapper.WxTaskMapper;
import com.ruoyi.system.service.EveryDayRemindService;
import com.ruoyi.system.service.IWxTaskService;
import com.ruoyi.system.task.EveryDayTask;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: robot
 * @description:
 * @author: TBKJ
 * @create: 2021-09-21
 **/
@Service
public class EveryDayRemindServiceImpl implements EveryDayRemindService {

    @Resource
    private WxTaskMapper wxTaskMapper;

    @Autowired
    private Scheduler sched;

    @Autowired
    private IWxTaskService iWxTaskService;

    @Override
    public void executeEveryDayRemind() throws SchedulerException {

        //数据库结果集
        List<WxTask> list = wxTaskMapper.selectAll();
        //遍历设定每日任务
        for (WxTask wxTask : list) {
            iWxTaskService.createWxTask(wxTask);
        }

    }
}
