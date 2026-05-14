//package com.ruoyi.test.dto;
//
//import cn.afterturn.easypoi.excel.annotation.Excel;
//import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
//import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
//import com.alibaba.excel.annotation.ExcelProperty;
//import lombok.Data;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Akira
// * @description 导出实体类
// */
//@Data
//@ExcelTarget("auditFlowExport")
//public class AuditFlowExport {
//
//    // 客户编码
//    @Excel(name = "审批单号", orderNum = "1", needMerge = true)
//    private String customerId;
//
//    // 坐席
//    @Excel(name = "流程名称", orderNum = "2", width = 50, needMerge = true)
//    private String customerServiceStaffId;
//    // 回访类型
//    @Excel(name = "发起人", orderNum = "3", width = 18, needMerge = true)
//    private String reviewType;
//
//    // 回访任务摘要
//    @Excel(name = "归属项目", orderNum = "4", width = 25, needMerge = true)
//    private String triggerInfo;
//
//}
