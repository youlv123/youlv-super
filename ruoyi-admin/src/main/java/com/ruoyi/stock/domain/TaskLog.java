package com.ruoyi.stock.domain;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务日志对象 task_log
 *
 * @author DXR
 * @date 2025-08-28
 */
@Data
@TableName("task_log")
public class TaskLog  {
    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 搜索值
     */
    @JsonIgnore
    @TableField(exist = false)
    private String searchValue;

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long taskLogId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务执行状态（0正常 1失败）
     */
    private String taskStatus;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 数据量
     */
    private Integer dataNum;

    /**
     * 执行时间
     */
    private String duration;

    /**
     * 交易日
     */
    private String bizDate;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedDate;

    public TaskLog(String taskName, String taskStatus, String errorMessage, Integer dataNum, String duration, String bizDate) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.errorMessage = errorMessage;
        this.dataNum = dataNum;
        this.duration = duration;
        this.bizDate = bizDate;
    }
    public TaskLog() {
    }
}
