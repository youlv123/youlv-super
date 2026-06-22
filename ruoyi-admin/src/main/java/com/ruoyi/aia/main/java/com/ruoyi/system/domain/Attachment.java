package com.ruoyi.aia.main.java.com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件信息对象 attachment
 *
 * @author DXR
 * @date 2026-06-22
 */
public class Attachment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 会话ID */
    @Excel(name = "会话ID")
    private String conversationId;

    /** 原始文件名 */
    @Excel(name = "原始文件名")
    private String fileName;

    /** 文件大小(字节) */
    @Excel(name = "文件大小(字节)")
    private String fileSize;

    /** 文件MIME类型（如 image/png, application/pdf） */
    @Excel(name = "文件MIME类型", readConverterExp = "如=,i=mage/png,,a=pplication/pdf")
    private String contentType;

    /** 存储系统中的唯一Key/路径 */
    @Excel(name = "存储系统中的唯一Key/路径")
    private String ossKey;

    /** 文件访问URL */
    @Excel(name = "文件访问URL")
    private String ossUrl;

    /** 是否包含敏感信息：0-否，1-是 */
    @Excel(name = "是否包含敏感信息：0-否，1-是")
    private Integer hasSensitiveInfo;

    /** 逻辑删除标识：0-未删除，1-已删除 */
    @Excel(name = "逻辑删除标识：0-未删除，1-已删除")
    private Integer isDeleted;

    /** 创建人ID/用户名 */
    @Excel(name = "创建人ID/用户名")
    private String createdBy;

    /** 更新人ID/用户名 */
    @Excel(name = "更新人ID/用户名")
    private String updatedBy;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setConversationId(String conversationId)
    {
        this.conversationId = conversationId;
    }

    public String getConversationId()
    {
        return conversationId;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFileSize(String fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getFileSize()
    {
        return fileSize;
    }
    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public String getContentType()
    {
        return contentType;
    }
    public void setOssKey(String ossKey)
    {
        this.ossKey = ossKey;
    }

    public String getOssKey()
    {
        return ossKey;
    }
    public void setOssUrl(String ossUrl)
    {
        this.ossUrl = ossUrl;
    }

    public String getOssUrl()
    {
        return ossUrl;
    }
    public void setHasSensitiveInfo(Integer hasSensitiveInfo)
    {
        this.hasSensitiveInfo = hasSensitiveInfo;
    }

    public Integer getHasSensitiveInfo()
    {
        return hasSensitiveInfo;
    }
    public void setIsDeleted(Integer isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted()
    {
        return isDeleted;
    }
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }
    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy()
    {
        return updatedBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("conversationId", getConversationId())
            .append("fileName", getFileName())
            .append("fileSize", getFileSize())
            .append("contentType", getContentType())
            .append("ossKey", getOssKey())
            .append("ossUrl", getOssUrl())
            .append("hasSensitiveInfo", getHasSensitiveInfo())
            .append("isDeleted", getIsDeleted())
            .append("createdBy", getCreatedBy())
            .append("updatedBy", getUpdatedBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
