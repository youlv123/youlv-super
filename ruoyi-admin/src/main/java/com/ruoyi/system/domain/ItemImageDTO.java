package com.ruoyi.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物品图片对象 item_image
 *
 * @author ruoyi
 * @date 2023-04-20
 */
@Data
public class ItemImageDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long imageId;

    /**
     * 物品ID
     */
    private Long itemId;

    /**
     * 图片名称
     */
    private String imageName;

    /**
     * 图片大小（KB）
     */
    private Long imageSize;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 对象键
     */
    private String objectKey;

    /**
     * 上传平台
     */
    private String uploadPlatform;

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

    /**
     * 逻辑删除
     */
    private String delFlag;

}
