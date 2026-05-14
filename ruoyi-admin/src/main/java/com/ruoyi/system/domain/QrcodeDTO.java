package com.ruoyi.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二维码对象 qrcode
 *
 * @author ruoyi
 * @date 2023-06-06
 */
public class QrcodeDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long qrcodeId;

    /**
     * 物品ID
     */
    @Excel(name = "物品ID")
    private Long itemId;

    /**
     * 分类ID
     */
    @Excel(name = "分类ID")
    private Long categoryId;

    /**
     * 是否批量绑定
     */
    @Excel(name = "是否批量绑定")
    private String isBatchBinding;

    /**
     * 二维码内容
     */
    @Excel(name = "二维码内容")
    private String content;

    /**
     * 二维码状态
     */
    @Excel(name = "二维码状态")
    private String status;

    /**
     * 二维码分享范围
     */
    @Excel(name = "二维码分享范围")
    private String sharingScope;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedDate;

    /**
     * 逻辑删除
     */
    private String delFlag;

    public void setQrcodeId(Long qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public Long getQrcodeId() {
        return qrcodeId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setIsBatchBinding(String isBatchBinding) {
        this.isBatchBinding = isBatchBinding;
    }

    public String getIsBatchBinding() {
        return isBatchBinding;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setSharingScope(String sharingScope) {
        this.sharingScope = sharingScope;
    }

    public String getSharingScope() {
        return sharingScope;
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
                .append("qrcodeId", getQrcodeId())
                .append("itemId", getItemId())
                .append("categoryId", getCategoryId())
                .append("isBatchBinding", getIsBatchBinding())
                .append("content", getContent())
                .append("status", getStatus())
                .append("sharingScope", getSharingScope())
                .append("createdBy", getCreatedBy())
                .append("createdDate", getCreatedDate())
                .append("updatedBy", getUpdatedBy())
                .append("updatedDate", getUpdatedDate())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
