package com.ruoyi.stock.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 东方财富-概念板块-实时行情
 *
 * @author DXR
 * @date 2025-06-09
 */
@Data
public class StockoardConceptSpotDTO {


    private String item;

    private String value;

}
