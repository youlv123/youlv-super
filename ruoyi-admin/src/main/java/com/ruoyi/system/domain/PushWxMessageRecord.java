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
 * 企业微信推送消息记录对象 push_wx_message_record
 *
 * @author DXR
 * @date 2025-06-21
 */
@Data
@TableName("push_wx_message_record")
public class PushWxMessageRecord {

    /** 主键ID */
    @TableId
    private Long pushId;

    /** 人员id */
    @Excel(name = "人员id")
    private String userId;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    private String userName;

    /** 企微人员id */
    @Excel(name = "企微人员id")
    private String wxId;

    /** 消息推送应用id */
    @Excel(name = "消息推送应用id")
    private String applicationId;

    /** 企微任务表主键ID */
    @Excel(name = "企微任务表主键ID")
    private Long wxTaskId;

    /** 微信消息内容 */
    @Excel(name = "微信消息内容")
    private String wxMessageContent;

    /** 微信消息类型 */
    @Excel(name = "微信消息类型")
    private String messageType;

    /** 微信消息id */
    @Excel(name = "微信消息id")
    private String wxMsgid;

    /** 推送标识 */
    @Excel(name = "推送标识")
    private String pushFlag;

    /** 推送错误信息 */
    @Excel(name = "推送错误信息")
    private String errorLog;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

}
