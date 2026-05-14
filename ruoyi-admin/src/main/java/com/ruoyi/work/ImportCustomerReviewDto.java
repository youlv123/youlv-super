package com.ruoyi.work;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 导入excel数据映射实体
 */
@Data
public class ImportCustomerReviewDto {

    // 客户编码
    @ExcelProperty(value = "客户编码", index = 0)
    private String customerId;

    // 坐席
    @ExcelProperty(value = "坐席工号", index = 1)
    private String customerServiceStaffId;
    // 回访类型
    @ExcelProperty(value = "回访类型编码", index = 2)
    private String reviewType;

    // 回访任务摘要
    @ExcelProperty(value = "回访任务摘要", index = 3)
    private String triggerInfo;

    // 客户姓名
/*    @ExcelProperty(value = "客户姓名", index = 1)
    private String customerName;*/








}