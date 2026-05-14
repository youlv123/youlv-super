package com.ruoyi.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ModelDisplay {

    private String offerIdNb;
    private BigDecimal score;
    private int view;
    private String strategy;
    private BigDecimal  transfm;
    private String tier;
    private String flag;
    private String Amount;
    private String province;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date callTime;


}
