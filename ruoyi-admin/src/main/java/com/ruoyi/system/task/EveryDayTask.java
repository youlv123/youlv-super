package com.ruoyi.system.task;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.PushWxMessageRecord;
import com.ruoyi.system.domain.WxSendResponse;
import com.ruoyi.util.WxHttpMultiUtils;
import com.ruoyi.util.WxHttpUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: robot
 * @description: 每日任务
 * @author: TBKJ
 * @create: 2021-09-21
 **/
@Data
@Component
@Slf4j
public class EveryDayTask implements Job {

    private String taskGroup;
    private String userId;
    private String userName;
    private String wxId;
    private String applicationId;
    private String wxMessageContent;
    private String wxTaskId;

    @Autowired
    private WxHttpMultiUtils wxHttpMultiUtils;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("------------------消息发送------------------");
        log.info("发送人员:" + wxId);
        log.info("发送内容:" + wxMessageContent);
        log.info("应用id:" + applicationId);
        PushWxMessageRecord pushWxMessageRecord = new PushWxMessageRecord();
        pushWxMessageRecord.setUserId(userId);
        pushWxMessageRecord.setUserName(userName);
        pushWxMessageRecord.setWxId(wxId);
        pushWxMessageRecord.setApplicationId(applicationId);
        pushWxMessageRecord.setWxTaskId(Long.valueOf(wxTaskId));
        pushWxMessageRecord.setWxMessageContent(wxMessageContent);
        pushWxMessageRecord.setMessageType(taskGroup);

        //发送消息
        wxHttpMultiUtils.sendWxMessagesAndRecord(pushWxMessageRecord);

    }
}
