
package com.ruoyi.test.controller;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.ruoyi.test.dto.Sheet3;
import com.ruoyi.test.dto.SheetOtaIn;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 CallCenterCustomerReviewDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去


/**
 * i won‘t be back
 * but my code will sleep there alaways
 * DXR 2024/08/19
 * good luck！
 * 于道各努力，千里自同风
 */

@Slf4j
@Data
public class DataListenerOAT implements ReadListener<SheetOtaIn> {

    List<SheetOtaIn> list = new ArrayList<>();
    public DataListenerOAT(List<SheetOtaIn> list) {
        this.list = list;

    }
//这个每一条数据解析都会来调用

    @Override
    public void invoke(SheetOtaIn data, AnalysisContext context) {



        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

      public List<SheetOtaIn> getAllSheetData() {
        return list;
    }
}
