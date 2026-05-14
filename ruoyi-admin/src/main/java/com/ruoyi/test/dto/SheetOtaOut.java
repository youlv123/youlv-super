package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 批量查询出参类
 */
@Data
public class SheetOtaOut {
    @ExcelProperty(value = "双条件匹配辅助列", index = 0)
    private String auxiliaryList;

    @ExcelProperty(value = "rank", index = 1)
    private String rank;

    @ExcelProperty(value = "触点日期维度", index = 2)
    private String date;
    @ExcelProperty(value = "维度", index = 3)
    private String dimension;

    @ExcelProperty(value = "品牌维度", index = 4)
    private String brandDimension;

    @ExcelProperty(value = "批评观点数", index = 5)
    private String criticalViewNum;
    @ExcelProperty(value = "表扬观点数", index = 6)
    private String praisingViewsNum;
    @ExcelProperty(value = "表扬率", index = 7)
    private String praisingRate;
    @ExcelProperty(value = "点评数", index = 8)
    private String reviewsNum;
    @ExcelProperty(value = "百点评批评数", index = 9)
    private String comments;

    @ExcelProperty(value = "触点编号", index = 10)
    private String touchpointNumber;

}
