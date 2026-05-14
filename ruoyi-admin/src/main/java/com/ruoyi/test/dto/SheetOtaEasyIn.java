package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 批量查询入参类
 */
@Data
public class SheetOtaEasyIn {

    @ExcelProperty(value = "触点日期维度", index = 0)
    private String date;
    @ExcelProperty(value = "维度", index = 1)
    private String dimension;

    @ExcelProperty(value = "品牌维度", index = 2)
    private String brand;

    @ExcelProperty(value = "批评观点数", index = 3)
    private String criticalViewNum;
    @ExcelProperty(value = "表扬观点数", index = 4)
    private String praisingViewsNum;
    @ExcelProperty(value = "表扬率", index = 5)
    private String praisingRate;
    @ExcelProperty(value = "点评数", index = 6)
    private String reviewsNum;
    @ExcelProperty(value = "百点评批评数", index = 7)
    private String comments;

}
