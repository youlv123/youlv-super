package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 记录工作时长对象 work_time_record
 *
 * @author ruoyi
 * @date 2023-06-25
 */
public class WorkTimeRecordDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long recordId;

    /**
     * 所属项目
     */
    @Excel(name = "所属项目")
    private String belongsProjects;

    /**
     * 当天日期
     */
    @Excel(name = "当天日期")
    private String todayDate;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 总共分钟时长
     */
    @Excel(name = "总共分钟时长")
    private BigDecimal totalMinutesDuration;

    /**
     * 总共小时时长
     */
    @Excel(name = "总共小时时长")
    private BigDecimal totalHoursDuration;

    /**
     * 工作内容描述
     */
    @Excel(name = "工作内容描述")
    private String workContent;

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

    /**
     * 逻辑删除
     */
    private String delFlag;

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setBelongsProjects(String belongsProjects) {
        this.belongsProjects = belongsProjects;
    }

    public String getBelongsProjects() {
        return belongsProjects;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setTotalMinutesDuration(BigDecimal totalMinutesDuration) {
        this.totalMinutesDuration = totalMinutesDuration;
    }

    public BigDecimal getTotalMinutesDuration() {
        return totalMinutesDuration;
    }

    public void setTotalHoursDuration(BigDecimal totalHoursDuration) {
        this.totalHoursDuration = totalHoursDuration;
    }

    public BigDecimal getTotalHoursDuration() {
        return totalHoursDuration;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("recordId", getRecordId())
                .append("belongsProjects", getBelongsProjects())
                .append("todayDate", getTodayDate())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("totalMinutesDuration", getTotalMinutesDuration())
                .append("totalHoursDuration", getTotalHoursDuration())
                .append("workContent", getWorkContent())
                .append("createdBy", getCreatedBy())
                .append("createdDate", getCreatedDate())
                .append("updatedBy", getUpdatedBy())
                .append("updatedDate", getUpdatedDate())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
