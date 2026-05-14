package com.ruoyi.weixin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

/**
 * 微信公众号图片素材对象 weixin_image
 *
 * @author www.joolun.com
 * @date 2023-11-27
 */
@Data
public class WeiXinImageDTO  {


    /**
     * 主键ID
     */
    private Long imageId;

    /**
     * 图片名称
     */
    @Excel(name = "图片名称")
    private String imageName;

    /**
     * 图片大小（KB）
     */
    @Excel(name = "图片大小", readConverterExp = "K=B")
    private Long imageSize;

    /**
     * 图片URL
     */
    @Excel(name = "图片URL")
    private String imageUrl;

    /**
     * 对象键
     */
    @Excel(name = "对象键")
    private String objectKey;

    /**
     * 图片类型
     */
    @Excel(name = "图片类型")
    private String imageType;

    /**
     * 图片描述
     */
    @Excel(name = "图片描述")
    private String imageDescription;

    /**
     * 上传平台
     */
    @Excel(name = "上传平台")
    private String uploadPlatform;

    /**
     * 图片来源地
     */
    @Excel(name = "图片来源地")
    private String imageSource;

    /**
     * 是否已被使用，0未使用过，1已使用过
     */
    @Excel(name = "是否已被使用，0未使用过，1已使用过")
    private String useFlag;

    /**
     * 自动审核标识，0初始状态，1审核通过，2审核不通过提交人工审核
     */
    @Excel(name = "自动审核标识，0初始状态，1审核通过，2审核不通过提交人工审核")
    private String automaticAuditFlag;

    /**
     * 自动审核意见
     */
    @Excel(name = "自动审核意见")
    private String automaticAuditOpinion;

    /**
     * 人工审核标识，0初始状态，1审核通过，2审核不通过
     */
    @Excel(name = "人工审核标识，0初始状态，1审核通过，2审核不通过")
    private String manualAuditFlag;

    /**
     * 人工审核意见
     */
    @Excel(name = "人工审核意见")
    private String manualAuditOpinion;

    /**
     * 是否已上传至微信素材库，0未上传，1已上传，3上传完后已经删除
     */
    @Excel(name = "是否已上传至微信素材库，0未上传，1已上传，3上传完后已经删除")
    private String weixinFlag;

    /**
     * 上传微信素材库后的获取标志，长度不固定，但不会超过 128 字符
     */
    @Excel(name = "上传微信素材库后的获取标志，长度不固定，但不会超过 128 字符")
    private String mediaId;

    /**
     * 新增微信公众平台的图片素材的图片URL（仅新增图片素材时会返回该字段）
     */
    @Excel(name = "新增微信公众平台的图片素材的图片URL", readConverterExp = "仅=新增图片素材时会返回该字段")
    private String weixinUrl;

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
