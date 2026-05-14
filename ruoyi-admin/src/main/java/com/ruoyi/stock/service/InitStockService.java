package com.ruoyi.stock.service;

public interface InitStockService {

    /**
     * 初始化涨停板行情对象 stock_zt_pool_em
     */
    public void initStockZtPoolEm(String startTime, String endTime);

    /**
     * 全量同步成分股表stock_board_cons_em
     */
    public void initStockBoardConsEm();

    /**
     * 获取A股当日数据，手动同步
     */
    void initStockZhAHist(Long start, Long end);

    /**
     * 初始化股票基础信息表
     */
    void initBasicStock();

    /**
     * 初始化板块历史数据
     */
    void initStockBoardDataHistEm();

}
