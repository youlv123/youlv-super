package com.ruoyi.system.domain;

import lombok.Data;

import java.util.List;

/**
 * 多sheet导出时所需要用到的类
 */
@Data
public class SheetInfo {
    /**
     * sheet页的名字
     */
    private String sheetName;

    /**
     * 需要导出的数据集合
     */
    private List<?> dataList;

    /**
     * 导出数据对应的实体类，使用时上面的？和这里的是同一个类即可
     */
    private Class<?> clazz;
}
