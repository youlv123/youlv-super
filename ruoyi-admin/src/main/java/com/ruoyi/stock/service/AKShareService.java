package com.ruoyi.stock.service;

import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.domain.Adapter.FundEtfSpotEmAdapter;
import com.ruoyi.stock.domain.Adapter.FundEtfSpotThsAdapter;
import com.ruoyi.stock.domain.Adapter.FundOpenFundDailyEmAdapter;
import com.ruoyi.stock.domain.Adapter.FundValueEstimationEmAdapter;

import java.util.List;

public interface AKShareService {

    /**
     * 根据传入的业务日期获取涨停板数据
     */
    List<StockZtPoolEm> getStockZtPoolEm(String bizDate);

    /**
     * 根据传入的业务日期获取强势股池数据
     */
    List<StockZtPoolStrongEm> getStockZtPoolStrongEm(String bizDate);


    /**
     * 根据传入的业务日期获取炸板股池数据
     */
    List<StockZtPoolZbgcEm> getStockZtPoolZbgcEm(String bizDate);

    /**
     * 根据传入的业务日期获取跌停股池数据
     */
    List<StockZtPoolDtgcEm> getStockZtPoolDtgcEm(String bizDate);

    /**
     * 单次返回当前时刻所有概念板块的实时行情数据
     *
     * @return
     */
    List<StockBoard> getStockBoardConceptNameEm();

    /**
     * 单次返回指定概念板块的实时行情数据
     *
     * @param symbol 板块名称
     * @return
     */
    StockoardConceptSpotOutDTO getStockoardConceptSpotEm(String symbol);

    /**
     * 根据板块代码查询概念板块所有的成分股
     *
     * @param boardCode 板块代码
     * @param boardName 板块名称
     * @return
     */
    List<StockBoardConsEm> getStockBoardConceptConsEm(String boardCode, String boardName);

    /**
     * 单次返回指定概念板块的实时历史数据
     *
     * @param symbol     板块名称,只能是名称
     * @param period     周期 	period="daily"; choice of {"daily", "weekly", "monthly"}
     * @param start_date 开始日期 start_date="20220101"
     * @param end_date   结束日期 end_date="20221128"
     * @param adjust     复权类型 adjust=""; choice of {'': 不复权, 默认; "qfq": 前复权, "hfq": 后复权}
     * @return
     */

    List<StockBoardDataHistEm> getStockBoardConceptHistEm(String boardCode, String symbol, String period, String start_date, String end_date, String adjust);

    /**
     * 单次返回指定行业板块的实时历史数据
     *
     * @param symbol     板块名称,只能是名称
     * @param period     周期 	period="日k"; 周期; choice of {"日k", "周k", "月k"}
     * @param start_date 开始日期 start_date="20220101"
     * @param end_date   结束日期 end_date="20221128"
     * @param adjust     复权类型 adjust=""; choice of {'': 不复权, 默认; "qfq": 前复权, "hfq": 后复权}
     * @return
     */
    List<StockBoardDataHistEm> getstockBoardIndustryHistEm(String boardCode, String symbol, String period, String start_date, String end_date, String adjust);

    /**
     * 返回指定概念板块按分钟的历史数据，当天的数据
     *
     * @param symbol period="5"; choice of {"1", "5", "15", "30", "60"}  传入1就是每分钟的数据，是当天的，其它的是近45左右的数据
     * @param period 板块名称,只能是名称
     * @return
     */
    List<StockBoardConceptHistMinEm> getStockBoardConceptHistMinEm(String symbol, String period);


//    行业板块

    /**
     * 单次返回当前时刻所有行业板块的实时行情数据
     *
     * @return
     */
    List<StockBoard> getStockBoardIndustryNameEm();

    /**
     * 单次返回指定行业板块的实时行情数据
     *
     * @param symbol
     * @return
     */
    StockoardConceptSpotOutDTO getStockBoardIndustrySpotEm(String symbol);

    /**
     * 根据板块代码查询行业板块所有的成分股，和上面查询概念板块的成分股一样作用，两个接口应该可以互查
     *
     * @param boardCode 板块代码
     * @param boardName 板块名称
     * @return
     */
    List<StockBoardConsEm> getStockBoardIndustryConsEm(String boardCode, String boardName);

    /**
     * 单次返回所有沪深京 A 股上市公司的实时行情数据
     *
     * @return
     */
    List<StockZhAHist> getStockZhASpotEm();

    /**
     * 单次返回所有沪 A 股上市公司的实时行情数据
     * <p>
     * 沪A
     *
     * @return
     */
    List<BasicStock> getStockShASpotEm();


    /**
     * 单次返回所有深 A 股上市公司的实时行情数据
     * <p>
     * 深 A
     *
     * @return
     */
    List<BasicStock> getStockSzASpotEm();


    /**
     * 单次返回所有京 A 股上市公司的实时行情数据
     * <p>
     * 京 A
     *
     * @return
     */
    List<BasicStock> getStockBjASpotEm();

    /**
     * 单次返回所有新股上市公司的实时行情数据
     * <p>
     * 新股
     *
     * @return
     */
    List<BasicStock> getStockNewASpotEm();

    /**
     * 单次返回所有创业板的实时行情数据
     * <p>
     * 创业板
     *
     * @return
     */
    List<BasicStock> getStockCyASpotEm();


    /**
     * 单次返回所有科创板的实时行情数据
     * <p>
     * 科创板
     *
     * @return
     */
    List<BasicStock> getStockKcASpotEm();

    /**
     * 根据传入的股票代码、周期、开始日期、结束日期、复权类型、超时时间、股票名称获取历史数据
     */
    List<StockZhAHist> getStockZhAHist(String symbol, String period, String start_date, String end_date, String adjust, String timeout, String stockName, String stockMarketType);

    /**
     * 单次返回从 1990-12-19 到 2024-12-31 之间的股票交易日历数据, 这里补充 1992-05-04 进入交易日
     *
     * @return
     */
    List<StockTradeDate> getToolTradeDateHistSina();


    /**
     * 同花顺个股资金流
     */
    List<ThsStockFundFlow> getStockFundFlowIndividual(String symbol);

    /**
     * 东方财富板块资金流
     */
    List<ThsBoardFundFlow> getThsBoardFundFlow(String indicator, String sectorType);

    /**
     * ETF基金实时行情-东财
     */
    List<FundEtfSpotEmAdapter> fundEtfSpotEm();

    /**
     * ETF基金实时行情-同花顺
     */
    List<FundEtfSpotThsAdapter> fundEtfSpotThs(String date);

    /**
     * 开放式基金-实时数据
     *
     * @return
     */
    List<FundOpenFundDailyEmAdapter> fundOpenFundDailyEm();

    /**
     * 东方财富基金净值估算
     */
    List<FundValueEstimationEmAdapter> fundValueEstimationEm(String symbol);

    /**
     * 期货-外盘-品种代码表
     */
    List<FuturesFqSubscribeExchangeSymbol> futuresHqSubscribeExchangeSymbol();

    /**
     * 外盘期货-实时数据
     */
    List<FuturesForeignCommodityRealtime>  futuresForeignCommodityRealtime(String symbol);
}
