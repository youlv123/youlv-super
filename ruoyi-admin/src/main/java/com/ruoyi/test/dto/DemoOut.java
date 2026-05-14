package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 批量查询入参类
 */
@Data
public class DemoOut {
//    @ExcelProperty(value = "日期", index = 0)
//    private String date;
//    @ExcelProperty(value = "间夜数", index = 1)
//    private BigDecimal room;
//    @ExcelProperty(value = "噪音批评数", index = 2)
//    private BigDecimal noise;
//    @ExcelProperty(value = "异味批评数", index = 3)
//    private BigDecimal smell;
//    @ExcelProperty(value = "热水与排水批评数", index = 4)
//    private BigDecimal water;
//    @ExcelProperty(value = "网络与信号批评数", index = 5)
//    private BigDecimal network;
//    @ExcelProperty(value = "空调批评数", index = 6)
//    private BigDecimal air;
//    @ExcelProperty(value = "电视批评数", index = 7)
//    private BigDecimal tv;
//
//
//    @ExcelProperty(value = "噪音批评率", index = 8 )
//    private BigDecimal noiseRate;
//
//    @ExcelProperty(value = "异味批评率", index = 9)
//    private BigDecimal smellRate;
//
//    @ExcelProperty(value = "热水与排水批评率", index = 10)
//    private BigDecimal waterRate;
//
//    @ExcelProperty(value = "网络与信号批评率", index = 11)
//    private BigDecimal networkRate;
//
//    @ExcelProperty(value = "空调批评率", index = 12)
//    private BigDecimal airRate;
//
//    @ExcelProperty(value = "电视批评率", index = 13)
//    private BigDecimal tvRate;


    @ExcelProperty(value = "日期", index = 0)
    private String date;
    @ExcelProperty(value = "间夜数", index = 1)
    private BigDecimal room;
    @ExcelProperty(value = "噪音批评数", index = 2)
    private BigDecimal noise;

    @ExcelProperty(value = "噪音批评率", index = 3 )
    private BigDecimal noiseRate;

    @ExcelProperty(value = "异味批评数", index = 4)
    private BigDecimal smell;

    @ExcelProperty(value = "异味批评率", index = 5)
    private BigDecimal smellRate;

    @ExcelProperty(value = "热水与排水批评数", index = 6)
    private BigDecimal water;
    @ExcelProperty(value = "热水与排水批评率", index = 7)
    private BigDecimal waterRate;

    @ExcelProperty(value = "网络与信号批评数", index = 8)
    private BigDecimal network;
    @ExcelProperty(value = "网络与信号批评率", index = 9)
    private BigDecimal networkRate;

    @ExcelProperty(value = "空调批评数", index = 10)
    private BigDecimal air;
    @ExcelProperty(value = "空调批评率", index = 11)
    private BigDecimal airRate;

    @ExcelProperty(value = "电视批评数", index = 12)
    private BigDecimal tv;
    @ExcelProperty(value = "电视批评率", index = 13)
    private BigDecimal tvRate;
}
