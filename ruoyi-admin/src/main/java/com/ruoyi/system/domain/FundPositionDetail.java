package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 理财持仓明细（每笔买入独立记录，FIFO计算核心）对象 fund_position_detail
 *
 * @author DXR
 * @date 2025-10-13
 */
@Data
@TableName("fund_position_detail")
public class FundPositionDetail {

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 搜索值
     */
    @JsonIgnore
    @TableField(exist = false)
    private String searchValue;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long fundDetailId;

    /**
     * 推送任务表主键ID
     */
    private Long wxTaskId;

    /**
     * 理财名称
     */
    @Excel(name = "理财名称")
    private String financialProductName;

    /**
     * 代码
     */
    @Excel(name = "代码")
    private String financialProductCode;

    /**
     * 买入本金
     */
    @Excel(name = "买入本金")
    private BigDecimal buyPrincipal;

    /**
     * 当前价值
     */
    @Excel(name = "当前价值")
    private BigDecimal amount;

    /**
     * 买入手续费
     */
    @Excel(name = "买入手续费")
    private BigDecimal buyFee;

    /**
     * 买入时基金净值
     */
    @Excel(name = "买入时基金净值")
    private BigDecimal buyNetValue;

    /**
     * 该笔买入总份额
     */
    @Excel(name = "该笔买入总份额")
    private BigDecimal totalShares;

    /**
     * 该笔买入剩余份额
     */
    @Excel(name = "该笔买入剩余份额")
    private BigDecimal remainingShares;

    /**
     * 持仓状态
     */
    @Excel(name = "持仓状态")
    private Long positionStatus;

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
     * 理财状态
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

    /**
     * 确认时间
     */
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expirationDate;

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
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedDate;

    /**
     * 交易类型
     */
    @TableField(exist = false)
    private String transType;

}
