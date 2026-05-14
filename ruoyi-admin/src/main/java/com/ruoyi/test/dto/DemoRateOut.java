package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 批量查询入参类
 */
@Data
public class DemoRateOut {

    @ExcelProperty(value = "日期", index = 0)
    private String date;

    @ExcelProperty(value = "平均噪音批评率", index = 1 )
    private BigDecimal noiseRate;

    @ExcelProperty(value = "平均异味批评率", index = 2)
    private BigDecimal smellRate;

    @ExcelProperty(value = "平均热水与排水批评率", index = 3)
    private BigDecimal waterRate;

    @ExcelProperty(value = "平均网络与信号批评率", index = 4)
    private BigDecimal networkRate;

    @ExcelProperty(value = "平均空调批评率", index = 5)
    private BigDecimal airRate;

    @ExcelProperty(value = "平均电视批评率", index = 6)
    private BigDecimal tvRate;
}
