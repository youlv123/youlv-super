package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 理财记录对象 financial_record
 *
 * @author DXR
 * @date 2025-07-07
 */
@Data
@TableName("financial_record")
public class FinancialRecord  extends BaseEntity{


    /**
     * 主键ID
     */
    @TableId
    private Long financialRecordId;

    /** 推送任务表主键ID */
    @Excel(name = "推送任务表主键ID")
    private Long wxTaskId;

    /**
     * 理财名称
     */
    @Excel(name = "理财名称")
    private String financialProductName;

    /**
     * 理财分组
     */
    @Excel(name = "理财分组")
    private String financialGroup;

    /**
     * 到期提醒cron表达式
     */
    @Excel(name = "到期提醒cron表达式")
    private String cron;

    /**
     * 默认到期提醒
     */
    @Excel(name = "默认到期提醒")
    private String defaultCron;

    /**
     * 理财状态
     * 0:未开始 1:进行中 2:已结束
     */
    @Excel(name = "理财状态")
    private String financialStatus;

    /**
     * 购买平台
     */
    @Excel(name = "购买平台")
    private String purchasePlatform;

    /**
     * 购买时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "购买时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date purchaseTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "确认时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date confirmTime;

    /**
     * 持有时间
     */
    @Excel(name = "持有时间")
    private String holdingTime;

    /**
     * 到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;

    /** 赎回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "赎回时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date redemptionTime;

    /**
     * 金额
     */
    @Excel(name = "金额")
    private String amount;

    /**
     * 收益率
     */
    @Excel(name = "收益率")
    private String yield;

    /**
     * 赎回路径
     */
    @Excel(name = "赎回路径")
    private String redemptionPath;

    /**
     * 盈利
     */
    @Excel(name = "盈利")
    private String profit;

    /** 备注 */
    private String remark;

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

}
