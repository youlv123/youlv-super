package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 批量查询入参类
 */
@Data
public class SheetOtaIn {

    @ExcelProperty(value = "触点日期维度", index = 0)
    private String date;
    @ExcelProperty(value = "维度", index = 1)
    private String dimension;

    @ExcelProperty(value = "亚朵批评观点数", index = 2)
    private String criticalViewNum;
    @ExcelProperty(value = "亚朵表扬观点数", index = 3)
    private String praisingViewsNum;
    @ExcelProperty(value = "亚朵表扬率", index = 4)
    private String praisingRate;
    @ExcelProperty(value = "亚朵点评数", index = 5)
    private String reviewsNum;
    @ExcelProperty(value = "亚朵百点评批评数", index = 6)
    private String comments;


    @ExcelProperty(value = "城际批评观点数", index = 7)
    private String criticalViewNum1;

    @ExcelProperty(value = "城际表扬观点数", index = 8)
    private String praisingViewsNum1;

    @ExcelProperty(value = "城际表扬率", index = 9)
    private String praisingRate1;

    @ExcelProperty(value = "城际点评数", index = 10)
    private String reviewsNum1;

    @ExcelProperty(value = "城际百点评批评数", index = 11)
    private String comments1;


    @ExcelProperty(value = "希尔顿欢朋批评观点数", index = 12)
    private String criticalViewNum2;

    @ExcelProperty(value = "希尔顿欢朋表扬观点数", index = 13)
    private String praisingViewsNum2;

    @ExcelProperty(value = "希尔顿欢朋表扬率", index = 14)
    private String praisingRate2;

    @ExcelProperty(value = "希尔顿欢朋点评数", index = 15)
    private String reviewsNum2;

    @ExcelProperty(value = "希尔顿欢朋百点评批评数", index = 16)
    private String comments2;


    @ExcelProperty(value = "桔子水晶批评观点数", index = 17)
    private String criticalViewNum3;

    @ExcelProperty(value = "桔子水晶表扬观点数", index = 18)
    private String praisingViewsNum3;

    @ExcelProperty(value = "桔子水晶表扬率", index = 19)
    private String praisingRate3;

    @ExcelProperty(value = "桔子水晶点评数", index = 20)
    private String reviewsNum3;

    @ExcelProperty(value = "桔子水晶百点评批评数", index = 21)
    private String comments3;


    @ExcelProperty(value = "美居批评观点数", index = 22)
    private String criticalViewNum4;

    @ExcelProperty(value = "美居表扬观点数", index = 23)
    private String praisingViewsNum4;

    @ExcelProperty(value = "美居表扬率", index = 24)
    private String praisingRate4;

    @ExcelProperty(value = "美居点评数", index = 25)
    private String reviewsNum4;

    @ExcelProperty(value = "美居百点评批评数", index = 26)
    private String comments4;

}
