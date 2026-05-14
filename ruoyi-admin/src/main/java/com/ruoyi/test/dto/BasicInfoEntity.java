package com.ruoyi.test.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasicInfoEntity {
    private String offerIdNb;
    private int reSubCt;
    private String dealerCode;
    private String dProduct;
    private String prApplcntName;
    private String coAoolcntName;
    private String guarantorName;
    private String tier;


    private BigDecimal pop;



}
