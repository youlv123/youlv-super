package com.ruoyi.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二维码查看权限对象 qrcode_permission
 *
 * @author ruoyi
 * @date 2023-06-07
 */
public class QrcodePermissionDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long permissionId;

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
     * 二维码id
     */
    @Excel(name = "二维码id")
    private Long qrcodeId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 二维码修改权限
     */
    @Excel(name = "二维码修改权限")
    private String editFlag;

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
    @Excel(name = "逻辑删除")
    private String delFlag;

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getPermissionId() {
        return permissionId;
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

    public void setQrcodeId(Long qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public Long getQrcodeId() {
        return qrcodeId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getEditFlag() {
        return editFlag;
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
                .append("permissionId", getPermissionId())
                .append("itemId", getItemId())
                .append("categoryId", getCategoryId())
                .append("qrcodeId", getQrcodeId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("editFlag", getEditFlag())
                .append("createdBy", getCreatedBy())
                .append("createdDate", getCreatedDate())
                .append("updatedBy", getUpdatedBy())
                .append("updatedDate", getUpdatedDate())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
