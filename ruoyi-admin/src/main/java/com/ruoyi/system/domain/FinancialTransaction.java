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
 * 理财交易记录（记录所有买入/卖出操作，关联持仓明细）对象 financial_transaction
 *
 * @author DXR
 * @date 2025-10-13
 */
@Data
@TableName("financial_transaction")
public class FinancialTransaction {

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
    private Long transactionId;

    /**
     * 代码
     */
    @Excel(name = "代码")
    private String financialProductCode;

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
     * 交易类型
     */
    @Excel(name = "交易类型")
    private String transType;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date transTime;

    /**
     * 交易份额
     */
    @Excel(name = "交易份额")
    private BigDecimal transShares;

    /**
     * 交易金额
     */
    @Excel(name = "交易金额")
    private BigDecimal transPrincipal;

    /**
     * 交易时基金净值
     */
    @Excel(name = "交易时基金净值")
    private BigDecimal transNetValue;

    /**
     * 交易手续费
     */
    @Excel(name = "交易手续费")
    private BigDecimal transFee;

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

    private String remark;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedDate;

}
