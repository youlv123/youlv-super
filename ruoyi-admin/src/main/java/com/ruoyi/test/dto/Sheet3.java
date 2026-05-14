package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 批量查询入参类
 */
@Data
public class Sheet3 {
    @ExcelProperty(value = "客户门店编码", index = 0)
    private String code;
    @ExcelProperty(value = "客户门店名称", index = 1)
    private String shopName;
    @ExcelProperty(value = "美的门店编码", index = 2)
    private String meiShopCode;
    @ExcelProperty(value = "美的门店名称", index = 3)
    private String meiShopName;

}
