package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物品信息对象 item_information
 *
 * @author ruoyi
 * @date 2023-06-07
 */
@Data
public class ItemInformationDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long itemId;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String itemName;

    /**
     * 物品的描述信息
     */
    @Excel(name = "物品的描述信息")
    private String description;

    /**
     * 是否上传物品图片
     */
    @Excel(name = "是否上传物品图片")
    private String hasImage;

    /**
     * 购买价格
     */
    @Excel(name = "购买价格")
    private BigDecimal purchasePrice;

    /**
     * 购买日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /**
     * 购买的数量
     */
    @Excel(name = "购买的数量")
    private Long purchaseQuantity;

    /**
     * 购买平台
     */
    @Excel(name = "购买平台")
    private String purchasePlatform;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private Long orderId;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productionDate;

    /**
     * 保质期（天数）
     */
    @Excel(name = "保质期", readConverterExp = "天=数")
    private Long shelfLife;

    /**
     * 有效期至
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期至", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expirationDate;

    /**
     * 物品存放的位置
     */
    @Excel(name = "物品存放的位置")
    private String storageLocation;

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
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

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

    /**
     * 是否生成二维码
     */
    @Excel(name = "是否生成二维码")
    private String qrCodeGenerated;

    /**
     * 物品状态
     */
    @Excel(name = "物品状态")
    private Long itemStatus;

    /**
     * 使用百分率
     */
    @Excel(name = "使用百分率")
    private Integer usageRate;

    /**
     * 图片信息
     */
    private List<ItemImageVO> itemImageVOList;

    /**
     * 分类ID
     */
    private Long categoryId;

}
