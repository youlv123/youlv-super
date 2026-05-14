package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 物品图片对象 item_image
 *
 * @author ruoyi
 * @date 2023-04-20
 */
@Data
public class ItemImageVO  extends BaseEntity{
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
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdDate;


}
