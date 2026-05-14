package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 关联持仓明细对象 fund_transaction_relation
 *
 * @author DXR
 * @date 2025-10-13
 */
@Data
@TableName("fund_transaction_relation")
public class FundTransactionRelation {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long fundTransactionRelationId;

    /**
     * 理财交易记录表id
     */
    @Excel(name = "理财交易记录表id")
    private Long transactionId;

    /**
     * 理财持仓明细表id
     */
    @Excel(name = "理财持仓明细表id")
    private Long fundDetailId;

    /**
     * 交易类型
     */
    @Excel(name = "交易类型")
    private String transType;

    /**
     * 交易份额
     */
    @Excel(name = "交易份额")
    private BigDecimal transShares;

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

}
