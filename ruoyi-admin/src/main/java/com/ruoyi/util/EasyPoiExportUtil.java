//package com.ruoyi.util;
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
//
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.ruoyi.system.domain.SheetInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@Slf4j
//public class EasyPoiExportUtil {
//
//    public static void exportMultiSheetWorkbook(HttpServletResponse response,String excelName,List<SheetInfo> sheets ) {
//
//        // 多个sheet配置参数
//         List<Map<String, Object>> sheetsList = new ArrayList<>();
//        for (SheetInfo sheet : sheets) {
//            Map<String, Object> exportMap = new HashMap<>();
//             ExportParams exportParams = new ExportParams(null, sheet.getSheetName(), ExcelType.XSSF);
//            // 以下3个参数为API中写死的参数名 分别是sheet配置/导出类（注解定义）/数据集
//            exportMap.put("title", exportParams);
//            exportMap.put("entity", sheet.getClazz());
//            exportMap.put("data", sheet.getDataList());
//            // 加入多sheet配置列表
//            sheetsList.add(exportMap);
//        }
//
//
//
//        // 导出文件
//        try {
//            // 核心方法：导出含多个sheet的excel文件 【注意，该方法第二个参数必须与上述的ExportParams对象指定的导出类型一致，默认ExcelType.HSSF格式，否则执行此方法时会报错!!!】
//             Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.XSSF);
//
////            String fileName = new String("文件名.xls".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8.toString());
////            String fileName = URLEncoder.encode(excelName + ".xls", StandardCharsets.UTF_8.toString());
////            response.setContentType("application/octet-stream");
////            response.setCharacterEncoding("utf-8");
////            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
////            OutputStream outputStream = response.getOutputStream();
////            response.flushBuffer();
////            workbook.write(outputStream);
////            // 写完数据关闭流
////            outputStream.close();
////            log.warn("导出成功");
//
//
//            // 告诉浏览器用什么软件可以打开此文件
//            response.setHeader("content-Type", "application/vnd.ms-excel");
//            //设置浏览器响应头对应的Content-disposition
//            response.setHeader("Content-Disposition",
//                    "attachment;filename=" + URLEncoder.encode("测试统计.xlsx", "UTF-8"));
//            //编码
//            response.setCharacterEncoding("UTF-8");
//            workbook.write(response.getOutputStream());
//
//        } catch (IOException e) {
//            log.error("导出异常 e:{}", e);
//        }
//    }
//
//
//
//
//}
