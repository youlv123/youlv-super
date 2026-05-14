package com.ruoyi.stock.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 股票交割单对象 stock_delivery_note
 *
 * @author DXR
 * @date 2025-03-15
 */
@Data
public class StockDeliveryNoteDTO {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long stockId;

    /**
     * 交易日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transactionDate;

    /**
     * 交易类型
     */
    @Excel(name = "交易类型")
    private String transactionType;

    /**
     * 盈利标识
     */
    @Excel(name = "盈利标识")
    private String profitFlag;

    /**
     * 证券代码
     */
    @Excel(name = "证券代码")
    private String stockCode;

    /**
     * 证券名称
     */
    @Excel(name = "证券名称")
    private String stockName;

    /**
     * 证券数量
     */
    @Excel(name = "证券数量")
    private String stockQuantity;

    /**
     * 可卖数量
     */
    @Excel(name = "可卖数量")
    private String sellableQuantity;

    /**
     * 成本价
     */
    @Excel(name = "成本价")
    private String costPrice;

    /**
     * 当前价
     */
    @Excel(name = "当前价")
    private String currentPrice;

    /**
     * 盈亏比例（%）
     */
    @Excel(name = "盈亏比例", readConverterExp = "%=")
    private String profitLossRatio;

    /**
     * 参考盈亏
     */
    @Excel(name = "参考盈亏")
    private String referenceProfitLoss;

    /**
     * 参考市值
     */
    @Excel(name = "参考市值")
    private String referenceMarketValue;

    /**
     * 交易市场
     */
    @Excel(name = "交易市场")
    private String tradingMarket;

    /**
     * 游资名称
     */
    @Excel(name = "游资名称")
    private String floatingCapitalName;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 备注
     */
    private String remark;

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
     * 可用金额
     */
    @Excel(name = "可用金额")
    private String availableAmount;

    /**
     * 股票市值
     */
    @Excel(name = "股票市值")
    private String stockValue;

    /**
     * 总资产
     */
    @Excel(name = "总资产")
    private String totalAssets;

}
