package com.ruoyi.work;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:review_import_timp表的实体类
 * @version
 * @author:  CS000147
 * @创建时间: 2022-12-06
 */
@Data
@TableName(value = "review_import_timp")
public class ReviewImportTimpPo {
    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户id
     */
    private String customerId;

    private String customerName;

    /**
     * 回访类型
     */
    private String reviewType;

    /**
     * 触发信息
     */
    private String triggerInfo;

    /**
     * 行数
     */
    private Long rowNo;

    /**
     * 类型
     */
    private String type;

    /**
     * 批次号
     */
    private Long batchNo;

    /**
     * 回访编号
     */
    private Long customerReviewId;

    /**
     * 机构id
     */
    private String organId;

    /**
     * 坐席
     */
    private String customerServiceId;


    /**
     * oracle创建时间
     */
    private Date recGenTime;

    /**
     * oracle最后更新时间
     */
    private Date recUpdTime;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 客户经理id
     */
    private String staffId;

    /**
     * 客户经理
     */
    private String staffName;

    /**
     * 账户资产
     */
    private BigDecimal customerAsset;
    /**
     * 开户日期
     */
    private Date openDate;

}
