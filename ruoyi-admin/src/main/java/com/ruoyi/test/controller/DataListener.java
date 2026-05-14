
package com.ruoyi.test.controller;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.ruoyi.test.dto.Demo;
import com.ruoyi.test.dto.DemoOut;
import com.ruoyi.work.ImportCustomerReviewDto;
import com.ruoyi.work.ReviewImportTimpPo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

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
public class DataListener implements ReadListener<Demo> {
    private int rowNum = 0;
    private List<List<DemoOut>> allSheetData = new ArrayList<>();
    private List<DemoOut> cachedDataList = new ArrayList<>();
    Map<Integer, List<DemoOut>> map=new HashMap<>();

    public DataListener(List<List<DemoOut>> allSheetData, Map<Integer, List<DemoOut>> map) {
        this.allSheetData = allSheetData;
        this.map = map;
    }

    @Override
    public void invoke(Demo data, AnalysisContext context) {
        BigDecimal num = new BigDecimal("10000");
        DemoOut demoOut=new DemoOut();
        demoOut.setDate(data.getDate());
        demoOut.setRoom(data.getRoom());
        demoOut.setNoise(data.getNoise());
        demoOut.setSmell(data.getSmell());
        demoOut.setWater(data.getWater());
        demoOut.setNetwork(data.getNetwork());
        demoOut.setAir(data.getAir());
        demoOut.setTv(data.getTv());
        demoOut.setNoiseRate(data.getNoise().multiply(num).divide(data.getRoom(), 2, RoundingMode.HALF_UP));
        demoOut.setSmellRate(data.getSmell().multiply(num).divide(data.getRoom(), 2, RoundingMode.HALF_UP));
        demoOut.setWaterRate(data.getWater().multiply(num).divide(data.getRoom(), 2, RoundingMode.HALF_UP));
        demoOut.setNetworkRate(data.getNetwork().multiply(num).divide(data.getRoom(), 2, RoundingMode.HALF_UP));
        demoOut.setAirRate(data.getAir().multiply(num).divide(data.getRoom(), 2, RoundingMode.HALF_UP));
        demoOut.setTvRate(data.getTv().multiply(num).divide(data.getRoom(), 2, RoundingMode.HALF_UP));

        cachedDataList.add(demoOut);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        rowNum++;
        List<DemoOut> newDataList = new ArrayList<>(cachedDataList);
        Collections.reverse(newDataList);
        allSheetData.add(newDataList);
        map.put(rowNum,newDataList);
        cachedDataList.clear();
        log.info("所有数据解析完成！");
    }

      public List<List<DemoOut>> getAllSheetData() {
        return allSheetData;
    }
}
