package com.ruoyi.stock.domain;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 涨停板行情对象 stock_zt_pool_em
 *
 * @author DXR
 * @date 2025-05-09
 */
@Data
@TableName("stock_zt_pool_em")
public class StockZtPoolEm {

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
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long ztId;

    /**
     * 序号
     */
    private Long serialNo;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 涨跌幅（单位：%）
     */
    private String changePercent;

    /**
     * 最新价（单位：元）
     */
    private String latestPrice;

    /**
     * 成交额（单位：元）
     */
    private Long turnover;

    /**
     * 流通市值（单位：元）
     */
    private String circulatingMarketValue;

    /**
     * 总市值（单位：元）
     */
    private String totalMarketValue;

    /**
     * 换手率（单位：%）
     */
    private String turnoverRate;

    /**
     * 封板资金（单位：元）
     */
    private Long sealedFunds;

    /**
     * 首次封板时间
     */
    private Date firstSealedTime;

    /**
     * 最后封板时间
     */
    private Date lastSealedTime;

    /**
     * 业务日期
     */

    private String bizDate;

    /**
     * 炸板次数
     */
    private Long breakNum;

    /**
     * 涨停统计（格式：X/Y）
     */
    private String priceLimitStatistics;

    /**
     * 连板数
     */
    private Long continuousNum;

    /**
     * 所属行业
     */
    private String industry;

    /** 数据来源 */
    private String dataSource;

    /**
     * 创建人
     */

    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

}
