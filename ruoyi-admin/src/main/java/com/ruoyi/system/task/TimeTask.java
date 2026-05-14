package com.ruoyi.system.task;


import com.ruoyi.system.service.EveryDayRemindService;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: robot
 * @description: 定时任务
 * @author: TBKJ
 * @create: 2021-09-21
 **/
@Component
public class TimeTask {

    @Resource
    public EveryDayRemindService everyDayRemindService;

    //项目启动时，检索数据库中全部录入定时任务并执行
    public  void scanTimeTask() throws SchedulerException {
        //执行全部每日任务
        everyDayRemindService.executeEveryDayRemind();
    }


}
