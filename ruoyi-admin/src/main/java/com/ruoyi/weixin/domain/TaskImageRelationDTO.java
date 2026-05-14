package com.ruoyi.weixin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

/**
 * 任务素材顺序关联对象 task_image_relation
 *
 * @author www.joolun.com
 * @date 2023-12-17
 */
@Data
public class TaskImageRelationDTO  {


    /**
     * 主键ID
     */
    private Long relationId;

    /**
     * 文章任务名称
     */
    @Excel(name = "文章任务名称")
    private String articleTaskName;

    /**
     * 文章任务表主键ID
     */
    @Excel(name = "文章任务表主键ID")
    private Long taskId;

    /**
     * 图片素材表主键ID
     */
    @Excel(name = "图片素材表主键ID")
    private Long imageId;

    /**
     * 模板表主键ID
     */
    @Excel(name = "模板表主键ID")
    private Long templateId;

    /**
     * 顺序
     */
    @Excel(name = "顺序")
    private Long orderNum;

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


}
