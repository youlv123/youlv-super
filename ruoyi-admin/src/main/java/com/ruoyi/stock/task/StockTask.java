package com.ruoyi.stock.task;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.stock.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("stockTask")
public class StockTask {

    @Autowired
    private TaskService taskService;


    /**
     * 获取当日涨停股票数据
     *
     */
    public void getStockZtPoolEm() {
        taskService.getStockZtPoolEm();
    }
    /**
     * 获取当日强势股池数据
     *
     */
    public void getStockZtPoolStrongEm() {
        taskService.getStockZtPoolStrongEm();
    }

    /**
     * 获取当日炸板股池数据
     *
     */
    public void getStockZtPoolZbgcEm() {
        taskService.getStockZtPoolZbgcEm();
    }

    /**
     * 获取当日跌停股池数据
     */
    public void getStockZtPoolDtgcEm() {
        taskService.getStockZtPoolDtgcEm();
    }

    /**
     * 定时同步理财的持有时间和状态
     */
    public void syncFinancialTime() {
        taskService.syncFinancialTime();
    }

    /**
     * 每周一9点同步板块信息
     * 每周一9点进行同步
     */
    public void syncStockBoard() {
        taskService.syncStockBoard();
    }


    /**
     * 每周一10点同步板块内股票信息
     * 每周一10点进行同步
     */
    public void syncStockBoardConsEm() {
        taskService.syncStockBoardConsEm();
    }

    /**
     * 每日9点26分同步当天前十板块数据
     * 交易日9点26分执行
     */
    public void sendStrongStock() {
        taskService.sendStrongStock();
    }
    /**
     * 定时同步股票基础数据16点
     *
     * 每天16点执行
     */
    public void syncBasicStock() {
        taskService.syncBasicStock();
    }

    /**
     * 每年1月1日同步股票交易日日期
     * 每年1月1日同步
     */
    public void syncStockTradeDate() {
        taskService.syncStockTradeDate();
    }

    /**
     * 每日17点同步股票每日数据
     */
    public void syncStockZhAHist() {
        taskService.syncStockZhAHist();
    }

    /**
     * 同步同花顺个股资金流入
     * 手动同步
     */
    public void syncThsStockFundFlow() {
        taskService.syncThsStockFundFlow();
    }

    /**
     * 同步东方财富个股资金流入
     * 手动同步
     */
    public void syncThsBoardFundFlow() {
        taskService.syncThsBoardFundFlow();
    }

    /**
     * 每日任务数据通知
     * 每晚8点 执行
     */
    public void syncTaskLog() {
        taskService.syncTaskLog();
    }

    /**
     *第一天阳，第二天阴，然后第二天成交大于第一天一半，市值大于100亿筛选
     */
    public void stockMethod() {
        taskService.stockMethod();
    }
}
