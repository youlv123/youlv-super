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
 * 理财汇总对象 financial_total
 *
 * @author DXR
 * @date 2025-09-13
 */
@Data
@TableName("financial_total")
public class FinancialTotal {

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
    private Long financialTotalId;

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
     * 理财分组
     */
    @Excel(name = "理财分组")
    private String financialGroup;

    /**
     * 最新净值
     * 程序每日定时拉取
     */
    @Excel(name = "最新净值")
    private BigDecimal latestNetValue;

    /**
     * 持仓成本价
     * 计算规则：当前持有的买入的总金额/当前持有总份额
     */
    @Excel(name = "持仓成本价")
    private BigDecimal currentHoldingCost;

    /**
     * 当前持有份额
     * 当前持有没卖出去的份额总数
     * 计算规则：买入总份额 - 卖出总份额
     */
    @Excel(name = "当前持有份额")
    private BigDecimal currentHoldingShares;

    /**
     * 当前持有金额
     * 当前持有没卖出去的金额总数
     * 计算规则：最新净值 x 当前持有份额
     */
    @Excel(name = "当前持有金额")
    private BigDecimal currentHoldingAmount;


    /**
     * 当前持有盈亏
     * 当前持仓中的份额盈亏数额
     * 计算规则： 当前持仓中的份额 x 最新净值 - 当前持仓中的份额 x 持仓成本价
     */
    @Excel(name = "当前持有盈亏")
    private BigDecimal currentHoldingProfit;

    /**
     * 总盈亏
     * 总共所有的金额盈亏数额
     * 计算规则： 卖出每笔盈亏 + 当前持有盈亏
     */
    @Excel(name = "总盈亏")
    private BigDecimal totalProfit;

    /**
     * 当前持有收益率
     * 目前持有的收益率
     * 持有份额x乘以最新净值除以当前持有金额
     * 计算规则：(当前持仓中的份额 x 最新净值 - 当前持仓中的份额 x 持仓成本价)/当前持仓中的份额 x 持仓成本价
     * 等于  当前持有盈亏/当前持仓中的份额 x 持仓成本价
     * 等于  (最新净值 - 持仓成本价)/持仓成本价
     */
    @Excel(name = "当前持有收益率")
    private BigDecimal currentHoldingYield;

    /**
     * 历史收益率
     * 不包括当前持仓的
     * 计算规则：卖出的所有盈亏/(卖出的所有金额-所有盈亏)
     */
    @Excel(name = "历史收益率")
    private BigDecimal hisTotalYield;

    /**
     * 备注
     */
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

}
