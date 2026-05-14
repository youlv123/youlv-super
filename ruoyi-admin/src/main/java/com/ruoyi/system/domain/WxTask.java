package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企微消息推送任务对象 wx_task
 *
 * @author DXR
 * @date 2025-06-30
 */
@Data
@TableName("wx_task")
public class WxTask {


    /**
     * 主键ID
     */
    @TableId
    private Long wxTaskId;

    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    private String taskName;

    /**
     * 任务分组
     */
    @Excel(name = "任务分组")
    private String taskGroup;

    /**
     * cron表达式
     */
    @Excel(name = "cron表达式")
    private String cron;

    /**
     * 任务状态,0-正常，1-暂停
     */
    @Excel(name = "任务状态")
    private String taskStatus;

    /**
     * 人员id
     */
    @Excel(name = "人员id")
    private String userId;

    /**
     * 人员姓名
     */
    @Excel(name = "人员姓名")
    private String userName;

    /**
     * 企微人员id
     */
    @Excel(name = "企微人员id")
    private String wxId;

    /**
     * 消息推送应用id
     */
    @Excel(name = "消息推送应用id")
    private String applicationId;

    /**
     * 微信消息内容
     */
    @Excel(name = "微信消息内容")
    private String wxMessageContent;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

}
