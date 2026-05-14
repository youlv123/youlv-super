package com.ruoyi.weixin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信公众号文章对象 weixin_article
 *
 * @author DXR
 * @date 2024-04-28
 */
@Data
public class WeiXinArticleDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Excel(name = "主键ID")
    private Long articleId;

    /**
     * 文章标题
     */
    @Excel(name = "文章标题")
    private String title;

    /**
     * 作者
     */
    @Excel(name = "作者")
    private String author;

    /**
     * 摘要
     */
    @Excel(name = "摘要")
    private String digest;

    /**
     * 文章描述
     */
    @Excel(name = "文章描述")
    private String description;

    /**
     * 图文具体内容
     */
    @Excel(name = "图文具体内容")
    private String content;

    /**
     * 原文地址
     */
    @Excel(name = "原文地址")
    private String contentSourceUrl;

    /**
     * 封面图片素材id
     */
    @Excel(name = "封面图片素材id")
    private String thumbMediaId;

    /**
     * 文章封面主键id
     */
    @Excel(name = "文章封面主键id")
    private Long coverImage;

    /**
     * 是否打开评论
     */
    @Excel(name = " 是否打开评论")
    private String needOpenComment;

    /**
     * 评论范围
     */
    @Excel(name = "评论范围")
    private String onlyFansCanComment;

    /**
     * 文章类型
     */
    @Excel(name = "文章类型")
    private String articleType;

    /**
     * 文章字数
     */
    @Excel(name = "文章字数")
    private Long articleWordCount;

    /**
     * 文章发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "文章发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /**
     * 所使用模板id
     */
    @Excel(name = "所使用模板id")
    private Long templateId;

    /**
     * 阅读量
     */
    @Excel(name = "阅读量")
    private Long readCount;

    /**
     * 点赞量
     */
    @Excel(name = "点赞量")
    private Long likeCount;

    /**
     * 评论量
     */
    @Excel(name = "评论量")
    private Long commentCount;

    /**
     * 分享量
     */
    @Excel(name = "分享量")
    private Long shareCount;

    /**
     * 自动审核标识
     */
    @Excel(name = "自动审核标识")
    private String automaticAuditFlag;

    /**
     * 自动审核意见
     */
    @Excel(name = "自动审核意见")
    private String automaticAuditOpinion;

    /**
     * 人工审核标识
     */
    @Excel(name = "人工审核标识")
    private String manualAuditFlag;

    /**
     * 人工审核意见
     */
    @Excel(name = "人工审核意见")
    private String manualAuditOpinion;

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
