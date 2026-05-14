package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 批量查询入参类
 */
@Data
public class Demo {
    @ExcelProperty(value = "日期", index = 0)
    private String date;
    @ExcelProperty(value = "间夜数", index = 1)
    private BigDecimal room;
    @ExcelProperty(value = "噪音批评数", index = 2)
    private BigDecimal noise;
    @ExcelProperty(value = "异味批评数", index = 3)
    private BigDecimal smell;
    @ExcelProperty(value = "热水与排水批评数", index = 4)
    private BigDecimal water;
    @ExcelProperty(value = "网络与信号批评数", index = 5)
    private BigDecimal network;
    @ExcelProperty(value = "空调批评数", index = 6)
    private BigDecimal air;
    @ExcelProperty(value = "电视批评数", index = 7)
    private BigDecimal tv;

}
