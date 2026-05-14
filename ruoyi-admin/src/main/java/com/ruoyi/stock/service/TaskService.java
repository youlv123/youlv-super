package com.ruoyi.stock.service;

import com.ruoyi.stock.domain.StockZtPoolEm;

import java.util.List;

public interface TaskService {
    /**
     * 获取当日涨停股票数据
     *
     */
    void getStockZtPoolEm();

    /**
     * 获取当日强势股池数据
     *
     */
    void getStockZtPoolStrongEm();

    /**
     * 获取当日炸板股池数据
     *
     */
    void getStockZtPoolZbgcEm();

    /**
     * 获取当日跌停股池数据
     */
    void getStockZtPoolDtgcEm();


    /**
     * 定时同步理财的持有时间和状态
     */
    public void syncFinancialTime();

    /**
     * 每周一9点同步板块信息
     */
    void syncStockBoard();

    /**
     * 每周一10点同步板块内股票信息
     */
    void syncStockBoardConsEm();

    /**
     * 每日9点26分同步当天前十板块数据
     */
    void sendStrongStock();

    /**
     * 定时同步股票基础数据16点
     */
    void syncBasicStock();

    /**
     * 每年1月1日同步股票交易日日期
     */
    void syncStockTradeDate();

    /**
     * 每日17点同步股票每日数据
     */
    void syncStockZhAHist();

    /**
     * 同步同花顺个股资金流入
     */
    void syncThsStockFundFlow();

    /**
     * 同步东方财富顺板块资金流入
     */
    void syncThsBoardFundFlow();

    /**
     * 每日任务数据通知
     */
    void syncTaskLog();

    /**
     * 第一天阳，第二天阴，然后第二天成交大于第一天一半，市值大于100亿筛选
     */
    void stockMethod();

}
