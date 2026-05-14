package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 批量查询入参类
 */
@Data
public class Sheet1 {
    @ExcelProperty(value = "客户门店编码", index = 0)
    private String code;
    @ExcelProperty(value = "客户门店名称", index = 1)
    private String shopName;
    @ExcelProperty(value = "美的门店名称", index = 2)
    private String meiShopName;
    @ExcelProperty(value = "商品名称", index = 3)
    private String productName;
    @ExcelProperty(value = "商品编码", index = 4)
    private String productCode;
    @ExcelProperty(value = "销售数量", index = 5)
    private String number;


}
