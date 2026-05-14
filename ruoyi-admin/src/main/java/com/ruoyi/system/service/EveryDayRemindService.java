package com.ruoyi.system.service;

import org.quartz.SchedulerException;

public interface EveryDayRemindService {

    public void executeEveryDayRemind() throws SchedulerException;
}
