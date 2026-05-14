
package com.ruoyi.test.controller;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.ruoyi.test.dto.Sheet1;
import com.ruoyi.test.dto.Sheet2;
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
public class DataListener2 implements ReadListener<Sheet2> {

    List<Sheet2> sheet1DataList = new ArrayList<>();
    public DataListener2(List<Sheet2> sheet1DataList) {
        this.sheet1DataList = sheet1DataList;

    }
//这个每一条数据解析都会来调用

    @Override
    public void invoke(Sheet2 data, AnalysisContext context) {



        sheet1DataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

      public List<Sheet2> getAllSheetData() {
        return sheet1DataList;
    }
}
