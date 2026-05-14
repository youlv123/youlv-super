package com.ruoyi.stock.controller;


import com.ruoyi.stock.service.InitStockService;
import com.ruoyi.system.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 各种数据初始化专用
 */
@RestController
@RequestMapping("/stock/init")
public class InitStockController {


    @Autowired
    private InitStockService initStockService;

    /**
     * 初始化涨停板行情对象 stock_zt_pool_em
     * 时间格式20230101
     */
    @GetMapping("/initStockZtPoolEm")
    public int compressPicture(String startTime, String endTime) {
        initStockService.initStockZtPoolEm(startTime, endTime);
        return 1;
    }


    @GetMapping("/initStockBoardConsEm")
    public int initStockBoardConsEm() {
        initStockService.initStockBoardConsEm();
        return 1;
    }

    /**
     * 获取A股当日数据，手动同步
     */
    @GetMapping("/initStockZhAHist")
    public int initStockZhAHist(Long start, Long end) {
        initStockService.initStockZhAHist(start, end);
        return 1;
    }

    /**
     * 初始化股票基础信息表
     */
    @GetMapping("/initBasicStock")
    public int initBasicStock() {
        initStockService.initBasicStock();
        return 1;
    }

    /**
     * 初始化板块历史数据
     */
    @GetMapping("/initStockBoardDataHistEm")
    public int initStockBoardDataHistEm() {
        initStockService.initStockBoardDataHistEm();
        return 1;
    }
}
