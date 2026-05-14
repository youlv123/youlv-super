package com.ruoyi.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 批量查询入参类
 */
@Data
public class Sheet2 {
    @ExcelProperty(value = "美的运中", index = 0)
    private String run;
    @ExcelProperty(value = "美的事业部", index = 1)
    private String shopName;
    @ExcelProperty(value = "美的门店编码", index = 2)
    private String meiShopCode;
    @ExcelProperty(value = "美的门店名称", index = 3)
    private String meiShopName;
    @ExcelProperty(value = "商品品牌", index = 4)
    private String brand;
    @ExcelProperty(value = "商品编码", index = 5)
    private String productCode;
    @ExcelProperty(value = "美的商品", index = 6)
    private String product;
    @ExcelProperty(value = "销售/台", index = 7)
    private String saleNumber;

}
