package com.ruoyi.weixin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信公众号文章发送对象 weixin_send
 *
 * @author DXR
 * @date 2024-04-27
 */
@Data
public class WeiXinSendDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Excel(name = "主键ID")
    private Long sendId;

    /**
     * 文章发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "文章发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /**
     * 文章定时发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "文章定时发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timingPublishTime;

    /**
     * 文章表id
     */
    @Excel(name = "文章表id")
    private Long articleId;

    /**
     * 发送状态
     */
    @Excel(name = "发送状态")
    private String sendFlag;

    /**
     * 发送失败原因
     */
    @Excel(name = "发送失败原因")
    private String sendFailMeassage;

    /**
     * 业务日期
     */
    @Excel(name = "业务日期")
    private String bizDate;

    /**
     * 所属公众号
     */
    @Excel(name = "所属公众号")
    private String belongAccount;

    /**
     * 微信素材库获取标志
     */
    @Excel(name = "微信素材库获取标志")
    private String mediaId;

    /**
     * 微信发布任务id
     */
    @Excel(name = "微信发布任务id")
    private String publishId;

    /**
     * 微信侧发布状态
     */
    @Excel(name = "微信侧发布状态")
    private String publishStatus;

    /**
     * 图文的 article_id
     */
    @Excel(name = "图文的 article_id")
    private String wxArticleId;

    /**
     * 文章数量
     */
    @Excel(name = "文章数量")
    private Long count;

    /**
     * 不通过文章编号
     */
    @Excel(name = "不通过文章编号")
    private String failIdx;

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
