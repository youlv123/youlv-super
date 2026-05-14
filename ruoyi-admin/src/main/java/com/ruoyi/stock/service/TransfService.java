package com.ruoyi.stock.service;

import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.domain.Adapter.*;

public interface TransfService {


    /**
     * 转换涨停股
     *
     * @param adapter
     * @return
     */
    public StockZtPoolEm transfStockZtPoolEm(StockZtPoolEmAdapter adapter);

    /**
     * 转换跌停股
     *
     * @param adapter
     * @return
     */
    StockZtPoolDtgcEm transfStockZtPoolDtgcEm(StockZtPoolDtgcEmAdapter adapter);

    /**
     * 转换强股
     *
     * @param adapter
     * @return
     */
    StockZtPoolStrongEm transfStockZtPoolStrongEm(StockZtPoolStrongEmAdapter adapter);

    /**
     * 转换成概念
     * @param adapter
     * @return
     */
    StockBoard transfStockBoardConcept(StockBoardAdapter adapter);

    /**
     * 转换概念股详情
     * @param adapter
     * @return
     */
    StockBoardConsEm transfStockBoardConsEm(StockBoardConsAdapter adapter);

    /**
     * 转换A股历史数据
     * @param adapter
     * @return
     */
    StockZhAHist transfStockZhAHist(StockZhAHistAdapter adapter);

    /**
     * 转换炸板股
     * @param adapter
     * @return
     */
    StockZtPoolZbgcEm transfStockZtPoolZbgcEm(StockZtPoolZbgcEmAdapter adapter);

    /**
     * 板块当前表转历史表
     * @param stockBoard
     * @return
     */
    StockBoardHis transfStockBoardToStockBoardHis(StockBoard stockBoard);

    /**
     * 板块对应股转历史数据
     * @param stockBoardConsEm
     * @return
     */
    StockBoardConsEmHis transfStockBoardConsEmHis(StockBoardConsEm stockBoardConsEm);

    /**
     * 基础股票转换
     * @param adapter
     * @return
     */
    BasicStock transfBasicStock(BasicStockAdapter adapter);

    /**
     * 基础股票历史数据转换
     * @param basicStock
     * @return
     */
    BasicStockHis transfBasicStockHis(BasicStock basicStock);

/**
     * 交易日历转换
     * @param adapter
     * @return
     */
    StockTradeDate transfStockTradeDate(StockTradeDateAdapter adapter);

    /**
     * 基础股票适配器转A股历史数据
     * @param adapter
     * @return
     */
    StockZhAHist transfBasicStockAdapterToStockZhAHist(BasicStockAdapter adapter);


    /**
     * 基础股票转A股历史数据
     * @param adapter
     * @return
     */
    StockZhAHist transfBasicStockToStockZhAHist(BasicStock adapter);

    /**
     * 板块数据历史表转换
     * @param adapter
     * @return
     */
    StockBoardDataHistEm transfStockBoardDataHistEm(StockBoardDataHistEmAdapter adapter);

    /**
     * ths板块资金流转换
     * @param adapter
     * @return
     */
    ThsStockFundFlow transfThsStockFundFlow(ThsStockFundFlowAdapter adapter);

    /**
     * ths板块资金流转换
     * @param adapter
     * @return
     */
    ThsBoardFundFlow transfThsBoardFundFlow(ThsBoardFundFlowAdapter adapter);

    ThsBoardFundFlow transfThsBoardFundFlow5(ThsBoardFundFlowAdapter5 adapter);

    ThsBoardFundFlow transfThsBoardFundFlow10(ThsBoardFundFlowAdapter10 adapter);

    StockZtPoolEm transfCzStockZt(CzStockZt adapter);

    StockZtPoolStrongEm transfCzStockQs(CzStockQs adapter);

    StockZtPoolZbgcEm transfCzStockZb(CzStockZb adapter);

    StockZtPoolDtgcEm transfCzStockDt(CzStockDt adapter);
}
