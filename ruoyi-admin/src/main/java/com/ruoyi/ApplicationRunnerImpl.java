package com.ruoyi;


import com.ruoyi.system.task.TimeTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: robot
 * @description: 启动类实现
 * @author: TBKJ
 * @create: 2021-09-21
 **/
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Resource
    TimeTask timeTask;
    @Value("${timeTask.flag}")
    private Boolean flag;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (flag){
            timeTask.scanTimeTask();
        }else {
            System.out.println("本地定时任务不启动！");
        }
    }
}
