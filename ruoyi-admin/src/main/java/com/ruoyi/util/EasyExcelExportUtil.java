package com.ruoyi.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ruoyi.system.domain.SheetInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

/**
 * DXR
 * 2024-07-12
 */
@Component
@Slf4j
public class EasyExcelExportUtil {


    /**
     * 导出excel文件，单sheet
     *
     * @param response  输出流
     * @param excelName 文件名称
     * @param sheetName sheet页名称
     * @param list      要导出的数据集合
     * @param clazz     导出的数据对应的导出实体
     * @throws ParseException
     * @throws IOException
     */
    public static void export(HttpServletResponse response, String excelName, String sheetName,
                              List<?> list, Class<?> clazz) throws ParseException, IOException {
        //这里文件名如果涉及中文一定要使用URL编码，否则会乱码
        String fileName = URLEncoder.encode(excelName + ".xlsx", StandardCharsets.UTF_8.toString());
        response.setContentType("application/force-download");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), clazz).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
        excelWriter.write(list, writeSheet);
        // 刷新数据到浏览器
        response.getOutputStream().flush();
        excelWriter.finish();
    }

    /**
     * 导出excel文件，多sheet
     *
     * @param response  输出流
     * @param excelName 文件名称
     * @param sheets    每个导出数据的信息，包括工作表名称、数据集合和对应的实体类
     * @throws ParseException
     * @throws IOException
     */
    public static void exportMultiSheet(HttpServletResponse response, String excelName, List<SheetInfo> sheets) throws ParseException, IOException {
        // 这里文件名如果涉及中文一定要使用 URL 编码，否则会乱码
        String fileName = URLEncoder.encode(excelName + ".xlsx", StandardCharsets.UTF_8.toString());
        response.setContentType("application/force-download");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        // 创建 ExcelWriter 对象
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        // 遍历每个工作表信息并写入数据
        for (SheetInfo sheetInfo : sheets) {
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetInfo.getSheetName()).head(sheetInfo.getClazz()).build();
            excelWriter.write(sheetInfo.getDataList(), writeSheet);
        }

        // 刷新数据到浏览器
        response.getOutputStream().flush();
        excelWriter.finish();
    }



}
