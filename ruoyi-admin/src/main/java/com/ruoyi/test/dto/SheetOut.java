package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 批量查询入参类
 */
@Data
public class SheetOut {
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

    @ExcelProperty(value = "美的运中", index = 6)
    private String run;
    @ExcelProperty(value = "美的事业部", index = 7)
    private String shopName1;
    @ExcelProperty(value = "美的门店编码", index = 8)
    private String meiShopCode;
    @ExcelProperty(value = "美的门店名称", index = 9)
    private String meiShopName1;
    @ExcelProperty(value = "商品品牌", index = 10)
    private String brand;
    @ExcelProperty(value = "美的商品编码", index = 11)
    private String productCode1;
    @ExcelProperty(value = "美的商品", index = 12)
    private String product;
    @ExcelProperty(value = "销售/台", index = 13)
    private String saleNumber;
}
