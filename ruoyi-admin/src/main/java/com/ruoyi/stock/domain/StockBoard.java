package com.ruoyi.stock.domain;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 板块对象 stock_board
 *
 * @author DXR
 * @date 2025-06-08
 */
@Data
@TableName("stock_board")
public class StockBoard {

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /** 搜索值 */
    @JsonIgnore
    @TableField(exist = false)
    private String searchValue;

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long boardId;

    /**
     * 排名
     */
    private int serialNo;

    /**
     * 板块代码
     */
    private String boardCode;

    /**
     * 板块名称
     */
    private String boardName;

    /** 板块类型 */
    private String boardType;

    /** 数据来源 */
    private String dataSource;

    /**
     * 最新价（单位：元）
     */
    private String latestPrice;

    /**
     * 涨跌额（单位:元）
     */
    private String priceRiseFall;

    /**
     * 涨跌幅（单位:%）
     */
    private String changePercent;

    /**
     * 总市值（单位：元）
     */
    private String totalMarketValue;

    /**
     * 换手率（单位:%）
     */
    private String turnoverRate;

    /**
     * 上涨家数
     */
    private Long riseNum;

    /**
     * 下跌家数
     */
    private Long fallNum;

    /**
     * 领涨股票
     */
    private String leadingStocks;

    /**
     * 领涨股票-涨跌幅（单位:%）
     */
    private String leadingStocksChangePercent;

    /**
     * 交易日
     */
    private String bizDate;

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

}
