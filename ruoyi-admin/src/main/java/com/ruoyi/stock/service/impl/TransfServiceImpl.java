package com.ruoyi.stock.service.impl;

import com.ruoyi.common.enums.BoardTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.domain.Adapter.*;
import com.ruoyi.stock.service.TransfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * 查询股票的对象，转换出参
 */
@Service
@Slf4j
public class TransfServiceImpl implements TransfService {
    // 单位倍数
    private static final BigDecimal UNIT_YI = new BigDecimal("100000000"); // 1亿 = 10^8
    private static final BigDecimal UNIT_WAN = new BigDecimal("10000");    // 1万 = 10^4

    /**
     * 转换涨停板行情
     *
     * @param adapter
     * @return
     */
    @Override
    public StockZtPoolEm transfStockZtPoolEm(StockZtPoolEmAdapter adapter) {
        StockZtPoolEm stockZtPoolEm = new StockZtPoolEm();
        stockZtPoolEm.setSerialNo(adapter.getSerialNo());
        stockZtPoolEm.setStockCode(adapter.getStockCode());
        stockZtPoolEm.setStockName(adapter.getStockName());
        if (StringUtils.isNotBlank(adapter.getChangePercent())) {
            int endIndex = Math.min(adapter.getChangePercent().length(), 5); // 确保不越界
            stockZtPoolEm.setChangePercent(adapter.getChangePercent().substring(0, endIndex));
        }

        stockZtPoolEm.setLatestPrice(adapter.getLatestPrice());
        stockZtPoolEm.setTurnover(adapter.getTurnover());
        stockZtPoolEm.setCirculatingMarketValue(adapter.getCirculatingMarketValue());
        stockZtPoolEm.setTotalMarketValue(adapter.getTotalMarketValue());

        if (StringUtils.isNotBlank(adapter.getTurnoverRate())) {
            int endIndex = Math.min(adapter.getTurnoverRate().length(), 5); // 确保不越界
            stockZtPoolEm.setTurnoverRate(adapter.getTurnoverRate().substring(0, endIndex));
        }

        stockZtPoolEm.setSealedFunds(adapter.getSealedFunds());
        Date firstSealedTime = DateUtils.dateTime(DateUtils.YYYYMMDDHHMMSS, adapter.getBizDate() + adapter.getFirstSealedDate());
        stockZtPoolEm.setFirstSealedTime(firstSealedTime);
        Date lastSealedTime = DateUtils.dateTime(DateUtils.YYYYMMDDHHMMSS, adapter.getBizDate() + adapter.getLastSealedDate());
        stockZtPoolEm.setLastSealedTime(lastSealedTime);
        stockZtPoolEm.setBizDate(adapter.getBizDate());
        stockZtPoolEm.setBreakNum(adapter.getBreakNum());
        stockZtPoolEm.setPriceLimitStatistics(adapter.getPriceLimitStatistics());
        stockZtPoolEm.setContinuousNum(adapter.getContinuousNum());
        stockZtPoolEm.setIndustry(adapter.getIndustry());
        stockZtPoolEm.setDataSource(adapter.getDataSource());
        return stockZtPoolEm;
    }

    /**
     * 转换涨停板高股息
     *
     * @param adapter
     * @return
     */
    @Override
    public StockZtPoolDtgcEm transfStockZtPoolDtgcEm(StockZtPoolDtgcEmAdapter adapter) {
        StockZtPoolDtgcEm stockZtPoolDtgcEm = new StockZtPoolDtgcEm();
        stockZtPoolDtgcEm.setSerialNo(adapter.getSerialNo());
        stockZtPoolDtgcEm.setStockCode(adapter.getStockCode());
        stockZtPoolDtgcEm.setStockName(adapter.getStockName());
        if (StringUtils.isNotBlank(adapter.getChangePercent())) {
            int endIndex = Math.min(adapter.getChangePercent().length(), 5); // 确保不越界
            stockZtPoolDtgcEm.setChangePercent(adapter.getChangePercent().substring(0, endIndex));
        }

        stockZtPoolDtgcEm.setLatestPrice(adapter.getLatestPrice());
        stockZtPoolDtgcEm.setTurnover(adapter.getTurnover());
        stockZtPoolDtgcEm.setCirculatingMarketValue(adapter.getCirculatingMarketValue());
        stockZtPoolDtgcEm.setTotalMarketValue(adapter.getTotalMarketValue());

        if (StringUtils.isNotBlank(adapter.getDynamicPriceRatio())) {
            int endIndex = Math.min(adapter.getDynamicPriceRatio().length(), 8); // 确保不越界
            stockZtPoolDtgcEm.setDynamicPriceRatio(adapter.getDynamicPriceRatio().substring(0, endIndex));
        }

        if (StringUtils.isNotBlank(adapter.getTurnoverRate())) {
            int endIndex = Math.min(adapter.getTurnoverRate().length(), 5); // 确保不越界
            stockZtPoolDtgcEm.setTurnoverRate(adapter.getTurnoverRate().substring(0, endIndex));
        }
        stockZtPoolDtgcEm.setSealedFunds(adapter.getSealedFunds());

        Date lastSealedTime = DateUtils.dateTime(DateUtils.YYYYMMDDHHMMSS, adapter.getBizDate() + adapter.getLastSealedDate());
        stockZtPoolDtgcEm.setLastSealedTime(lastSealedTime);
        stockZtPoolDtgcEm.setTurnoverOnBoard(adapter.getTurnoverOnBoard());
        stockZtPoolDtgcEm.setBizDate(adapter.getBizDate());
        stockZtPoolDtgcEm.setContinuousLimitDown(adapter.getContinuousLimitDown());
        stockZtPoolDtgcEm.setOpenNum(adapter.getOpenNum());
        stockZtPoolDtgcEm.setIndustry(adapter.getIndustry());
        return stockZtPoolDtgcEm;
    }

    /**
     * 涨停板强势股转换
     *
     * @param adapter
     * @return
     */
    @Override
    public StockZtPoolStrongEm transfStockZtPoolStrongEm(StockZtPoolStrongEmAdapter adapter) {


        StockZtPoolStrongEm stockZtPoolStrongEm = new StockZtPoolStrongEm();
        stockZtPoolStrongEm.setSerialNo(adapter.getSerialNo());
        stockZtPoolStrongEm.setStockCode(adapter.getStockCode());
        stockZtPoolStrongEm.setStockName(adapter.getStockName());
        if (StringUtils.isNotBlank(adapter.getChangePercent())) {
            int endIndex = Math.min(adapter.getChangePercent().length(), 5); // 确保不越界
            stockZtPoolStrongEm.setChangePercent(adapter.getChangePercent().substring(0, endIndex));
        }


        stockZtPoolStrongEm.setLatestPrice(adapter.getLatestPrice());
        stockZtPoolStrongEm.setPriceLimitUp(adapter.getPriceLimitUp());
        stockZtPoolStrongEm.setTurnover(adapter.getTurnover());
        stockZtPoolStrongEm.setCirculatingMarketValue(adapter.getCirculatingMarketValue());
        stockZtPoolStrongEm.setTotalMarketValue(adapter.getTotalMarketValue());


        if (StringUtils.isNotBlank(adapter.getTurnoverRate())) {
            int endIndex = Math.min(adapter.getTurnoverRate().length(), 5); // 确保不越界
            stockZtPoolStrongEm.setTurnoverRate(adapter.getTurnoverRate().substring(0, endIndex));
        }

        stockZtPoolStrongEm.setGrowthRate(adapter.getGrowthRate());
        stockZtPoolStrongEm.setIsNewHigh(adapter.getIsNewHigh());
        if (StringUtils.isNotBlank(adapter.getVolumeRatio())) {
            int endIndex = Math.min(adapter.getVolumeRatio().length(), 5); // 确保不越界
            stockZtPoolStrongEm.setVolumeRatio(adapter.getVolumeRatio().substring(0, endIndex));
        }

        stockZtPoolStrongEm.setBizDate(adapter.getBizDate());
        stockZtPoolStrongEm.setPriceLimitStatistics(adapter.getPriceLimitStatistics());
        stockZtPoolStrongEm.setSelectionReasons(adapter.getSelectionReasons());
        stockZtPoolStrongEm.setIndustry(adapter.getIndustry());
        stockZtPoolStrongEm.setDataSource(adapter.getDataSource());
        return stockZtPoolStrongEm;
    }

    @Override
    public StockBoard transfStockBoardConcept(StockBoardAdapter adapter) {
        StockBoard stockBoard = new StockBoard();
        stockBoard.setSerialNo(adapter.getSerialNo());
        stockBoard.setBoardCode(adapter.getBoardCode());
        stockBoard.setBoardName(adapter.getBoardName());
        stockBoard.setBoardType(adapter.getBoardType());
        stockBoard.setDataSource(adapter.getDataSource());
        stockBoard.setLatestPrice(adapter.getLatestPrice());
        stockBoard.setPriceRiseFall(adapter.getPriceRiseFall());
        stockBoard.setChangePercent(adapter.getChangePercent());
        stockBoard.setTotalMarketValue(adapter.getTotalMarketValue());
        stockBoard.setTurnoverRate(adapter.getTurnoverRate());
        stockBoard.setRiseNum(adapter.getRiseNum());
        stockBoard.setFallNum(adapter.getFallNum());
        stockBoard.setLeadingStocks(adapter.getLeadingStocks());
        stockBoard.setLeadingStocksChangePercent(adapter.getLeadingStocksChangePercent());
        stockBoard.setBizDate(DateUtils.getBizDate());

        return stockBoard;
    }

    /**
     * 板块概念股转换
     *
     * @param adapter
     * @return
     */
    @Override
    public StockBoardConsEm transfStockBoardConsEm(StockBoardConsAdapter adapter) {
        StockBoardConsEm stockBoardConsEm = new StockBoardConsEm();
        stockBoardConsEm.setSerialNo(adapter.getSerialNo());
        stockBoardConsEm.setStockCode(adapter.getStockCode());
        stockBoardConsEm.setStockName(adapter.getStockName());
        stockBoardConsEm.setBoardCode(adapter.getBoardCode());
        stockBoardConsEm.setBoardName(adapter.getBoardName());
        stockBoardConsEm.setLatestPrice(adapter.getLatestPrice());
        stockBoardConsEm.setChangePercent(adapter.getChangePercent());
        stockBoardConsEm.setPriceRiseFall(adapter.getPriceRiseFall());
        stockBoardConsEm.setVolume(adapter.getVolume());
        stockBoardConsEm.setTurnover(adapter.getTurnover());
        stockBoardConsEm.setAmplitude(adapter.getAmplitude());
        stockBoardConsEm.setHighestPrice(adapter.getHighestPrice());
        stockBoardConsEm.setLowestPrice(adapter.getLowestPrice());
        stockBoardConsEm.setTodayOpenPrice(adapter.getTodayOpenPrice());
        stockBoardConsEm.setYesterdayClosingPrice(adapter.getYesterdayClosingPrice());
        stockBoardConsEm.setTurnoverRate(adapter.getTurnoverRate());
        stockBoardConsEm.setDynamicPriceRatio(adapter.getDynamicPriceRatio());
        stockBoardConsEm.setPriceToBookRatio(adapter.getPriceToBookRatio());
        return stockBoardConsEm;
    }

    @Override
    public StockZhAHist transfStockZhAHist(StockZhAHistAdapter adapter) {
        StockZhAHist stockZhAHist = new StockZhAHist();
        stockZhAHist.setStockCode(adapter.getStockCode());
        stockZhAHist.setStockName(adapter.getStockName());
        stockZhAHist.setOpeningPrice(adapter.getOpeningPrice());
        stockZhAHist.setClosingPrice(adapter.getClosingPrice());
        stockZhAHist.setHighestPrice(adapter.getHighestPrice());
        stockZhAHist.setLowestPrice(adapter.getLowestPrice());
        stockZhAHist.setVolume(adapter.getVolume());
        stockZhAHist.setTurnover(adapter.getTurnover());
        stockZhAHist.setAmplitude(adapter.getAmplitude());
        stockZhAHist.setChangePercent(adapter.getChangePercent());
        stockZhAHist.setPriceRiseFall(adapter.getPriceRiseFall());
        stockZhAHist.setTurnoverRate(adapter.getTurnoverRate());
        stockZhAHist.setBizDate(adapter.getBizDate());
        stockZhAHist.setStockMarketType(adapter.getStockMarketType());
        return stockZhAHist;
    }

    /**
     * 炸板股转换
     *
     * @param adapter
     * @return
     */
    @Override
    public StockZtPoolZbgcEm transfStockZtPoolZbgcEm(StockZtPoolZbgcEmAdapter adapter) {
        StockZtPoolZbgcEm stockZtPoolZbgcEm = new StockZtPoolZbgcEm();
        stockZtPoolZbgcEm.setSerialNo(adapter.getSerialNo());
        stockZtPoolZbgcEm.setStockCode(adapter.getStockCode());
        stockZtPoolZbgcEm.setStockName(adapter.getStockName());

        if (StringUtils.isNotBlank(adapter.getChangePercent())) {
            int endIndex = Math.min(adapter.getChangePercent().length(), 5); // 确保不越界
            stockZtPoolZbgcEm.setChangePercent(adapter.getChangePercent().substring(0, endIndex));
        }

        stockZtPoolZbgcEm.setLatestPrice(adapter.getLatestPrice());
        stockZtPoolZbgcEm.setPriceLimitUp(adapter.getPriceLimitUp());
        stockZtPoolZbgcEm.setTurnover(adapter.getTurnover());
        stockZtPoolZbgcEm.setCirculatingMarketValue(adapter.getCirculatingMarketValue());
        stockZtPoolZbgcEm.setTotalMarketValue(adapter.getTotalMarketValue());
        if (StringUtils.isNotBlank(adapter.getTurnoverRate())) {
            int endIndex = Math.min(adapter.getTurnoverRate().length(), 5); // 确保不越界
            stockZtPoolZbgcEm.setTurnoverRate(adapter.getTurnoverRate().substring(0, endIndex));
        }


        if (StringUtils.isNotBlank(adapter.getGrowthRate())) {
            int endIndex = Math.min(adapter.getGrowthRate().length(), 5); // 确保不越界
            stockZtPoolZbgcEm.setGrowthRate(adapter.getGrowthRate().substring(0, endIndex));
        }


        Date firstSealedTime = DateUtils.dateTime(DateUtils.YYYYMMDDHHMMSS, adapter.getBizDate() + adapter.getFirstDate());
        stockZtPoolZbgcEm.setFirstCoverDate(firstSealedTime);
        stockZtPoolZbgcEm.setBizDate(adapter.getBizDate());
        stockZtPoolZbgcEm.setExplosionNum(adapter.getExplosionNum());
        stockZtPoolZbgcEm.setPriceLimitStatistics(adapter.getPriceLimitStatistics());


        if (StringUtils.isNotBlank(adapter.getAmplitude())) {
            int endIndex = Math.min(adapter.getAmplitude().length(), 5); // 确保不越界
            stockZtPoolZbgcEm.setAmplitude(adapter.getAmplitude().substring(0, endIndex));
        }
        stockZtPoolZbgcEm.setIndustry(adapter.getIndustry());
        stockZtPoolZbgcEm.setDataSource(adapter.getDataSource());

        return stockZtPoolZbgcEm;
    }

    /**
     * 板块股转换,当前表转历史表
     * <p>
     * 当前表与历史表的转换，不需要对涨幅等进行截取
     *
     * @param stockBoard
     * @return
     */
    @Override
    public StockBoardHis transfStockBoardToStockBoardHis(StockBoard stockBoard) {
        StockBoardHis stockBoardHis = new StockBoardHis();
        stockBoardHis.setSerialNo(stockBoard.getSerialNo());
        stockBoardHis.setBoardCode(stockBoard.getBoardCode());
        stockBoardHis.setBoardName(stockBoard.getBoardName());
        stockBoardHis.setBoardType(stockBoard.getBoardType());
        stockBoardHis.setDataSource(stockBoard.getDataSource());
        stockBoardHis.setLatestPrice(stockBoard.getLatestPrice());
        stockBoardHis.setPriceRiseFall(stockBoard.getPriceRiseFall());
        stockBoardHis.setChangePercent(stockBoard.getChangePercent());
        stockBoardHis.setTotalMarketValue(stockBoard.getTotalMarketValue());
        stockBoardHis.setTurnoverRate(stockBoard.getTurnoverRate());
        stockBoardHis.setRiseNum(stockBoard.getRiseNum());
        stockBoardHis.setFallNum(stockBoard.getFallNum());
        stockBoardHis.setLeadingStocks(stockBoard.getLeadingStocks());
        stockBoardHis.setLeadingStocksChangePercent(stockBoard.getLeadingStocksChangePercent());
        stockBoardHis.setBizDate(stockBoard.getBizDate());
        return stockBoardHis;
    }

    /**
     * 板块成份股转换,当前表转历史表
     *
     * @param em
     * @return
     */
    @Override
    public StockBoardConsEmHis transfStockBoardConsEmHis(StockBoardConsEm em) {
        StockBoardConsEmHis his = new StockBoardConsEmHis();
        his.setSerialNo(em.getSerialNo());
        his.setStockCode(em.getStockCode());
        his.setStockName(em.getStockName());
        his.setBoardCode(em.getBoardCode());
        his.setBoardName(em.getBoardName());
        his.setLatestPrice(em.getLatestPrice());
        his.setChangePercent(em.getChangePercent());
        his.setPriceRiseFall(em.getPriceRiseFall());
        his.setVolume(em.getVolume());
        his.setTurnover(em.getBoardConsId());
        his.setAmplitude(em.getAmplitude());
        his.setHighestPrice(em.getHighestPrice());
        his.setLowestPrice(em.getLowestPrice());
        his.setTodayOpenPrice(em.getTodayOpenPrice());
        his.setYesterdayClosingPrice(em.getYesterdayClosingPrice());
        his.setTurnoverRate(em.getTurnoverRate());
        his.setDynamicPriceRatio(em.getDynamicPriceRatio());
        his.setPriceToBookRatio(em.getPriceToBookRatio());
        return his;
    }

    /**
     * 基础股票转换
     *
     * @param adapter
     * @return
     */
    @Override
    public BasicStock transfBasicStock(BasicStockAdapter adapter) {
        BasicStock basicStock = new BasicStock();
        basicStock.setSerialNo(adapter.getSerialNo());
        basicStock.setStockCode(adapter.getStockCode());
        basicStock.setStockName(adapter.getStockName());
        basicStock.setLatestPrice(adapter.getLatestPrice());
        basicStock.setChangePercent(adapter.getChangePercent());
        basicStock.setPriceRiseFall(adapter.getPriceRiseFall());
        basicStock.setVolume(adapter.getVolume());
        basicStock.setTurnover(adapter.getTurnover());
        basicStock.setAmplitude(adapter.getAmplitude());
        basicStock.setHighestPrice(adapter.getHighestPrice());
        basicStock.setLowestPrice(adapter.getLowestPrice());
        basicStock.setTodayOpenPrice(adapter.getTodayOpenPrice());
        basicStock.setYesterdayClosingPrice(adapter.getYesterdayClosingPrice());
        basicStock.setVolumeRatio(adapter.getVolumeRatio());
        basicStock.setTurnoverRate(adapter.getTurnoverRate());
        basicStock.setDynamicPriceRatio(adapter.getDynamicPriceRatio());
        basicStock.setPriceToBookRatio(adapter.getPriceToBookRatio());
        if (StringUtils.isNotBlank(adapter.getListingTime())) {
            basicStock.setListingTime(DateUtils.formatDate(adapter.getListingTime()));
        }

        basicStock.setTotalMarketValue(adapter.getTotalMarketValue());
        basicStock.setCirculatingMarketValue(adapter.getCirculatingMarketValue());
        basicStock.setGrowthRate(adapter.getGrowthRate());
        basicStock.setMins5ChangePercent(adapter.getMins5ChangePercent());
        basicStock.setDays60ChangePercent(adapter.getDays60ChangePercent());
        basicStock.setYearChangePercent(adapter.getYearChangePercent());
        basicStock.setStockMarketType(adapter.getStockMarketType());
        basicStock.setBizDate(adapter.getBizDate());
        return basicStock;
    }

    /**
     * 基础股票转换,当前表转历史表
     *
     * @param basicStock
     * @return
     */
    @Override
    public BasicStockHis transfBasicStockHis(BasicStock basicStock) {
        BasicStockHis basicStockHis = new BasicStockHis();
        basicStockHis.setSerialNo(basicStock.getSerialNo());
        basicStockHis.setStockCode(basicStock.getStockCode());
        basicStockHis.setStockName(basicStock.getStockName());
        basicStockHis.setLatestPrice(basicStock.getLatestPrice());
        basicStockHis.setChangePercent(basicStock.getChangePercent());
        basicStockHis.setPriceRiseFall(basicStock.getPriceRiseFall());
        basicStockHis.setVolume(basicStock.getVolume());
        basicStockHis.setTurnover(basicStock.getTurnover());
        basicStockHis.setAmplitude(basicStock.getAmplitude());
        basicStockHis.setHighestPrice(basicStock.getHighestPrice());
        basicStockHis.setLowestPrice(basicStock.getLowestPrice());
        basicStockHis.setTodayOpenPrice(basicStock.getTodayOpenPrice());
        basicStockHis.setYesterdayClosingPrice(basicStock.getYesterdayClosingPrice());
        basicStockHis.setVolumeRatio(basicStock.getVolumeRatio());
        basicStockHis.setTurnoverRate(basicStock.getTurnoverRate());
        basicStockHis.setDynamicPriceRatio(basicStock.getDynamicPriceRatio());
        basicStockHis.setPriceToBookRatio(basicStock.getPriceToBookRatio());
        basicStockHis.setListingTime(basicStock.getListingTime());
        basicStockHis.setTotalMarketValue(basicStock.getTotalMarketValue());
        basicStockHis.setCirculatingMarketValue(basicStock.getCirculatingMarketValue());
        basicStockHis.setGrowthRate(basicStock.getGrowthRate());
        basicStockHis.setMins5ChangePercent(basicStock.getMins5ChangePercent());
        basicStockHis.setDays60ChangePercent(basicStock.getDays60ChangePercent());
        basicStockHis.setYearChangePercent(basicStock.getYearChangePercent());
        basicStockHis.setStockMarketType(basicStock.getStockMarketType());
        basicStockHis.setBizDate(basicStock.getBizDate());
        return basicStockHis;
    }

    /**
     * 交易日历转换
     *
     * @param adapter
     * @return
     */
    @Override
    public StockTradeDate transfStockTradeDate(StockTradeDateAdapter adapter) {
        StockTradeDate stockTradeDate = new StockTradeDate();
        stockTradeDate.setTradeDate(DateUtils.formatDate(adapter.getTradeDate()));
        return stockTradeDate;
    }

    /**
     * 基础股票转换,当前表转历史表
     *
     * @param adapter
     * @return
     */
    @Override
    public StockZhAHist transfBasicStockAdapterToStockZhAHist(BasicStockAdapter adapter) {
        StockZhAHist stockZhAHist = new StockZhAHist();
        stockZhAHist.setStockCode(adapter.getStockCode());
        stockZhAHist.setStockName(adapter.getStockName());
        stockZhAHist.setStockMarketType(adapter.getStockMarketType());
        stockZhAHist.setOpeningPrice(adapter.getTodayOpenPrice());
        stockZhAHist.setClosingPrice(adapter.getLatestPrice());
        stockZhAHist.setHighestPrice(adapter.getHighestPrice());
        stockZhAHist.setLowestPrice(adapter.getLowestPrice());
        stockZhAHist.setVolume(adapter.getVolume());
        stockZhAHist.setTurnover(adapter.getTurnover());
        stockZhAHist.setAmplitude(adapter.getAmplitude());
        stockZhAHist.setChangePercent(adapter.getChangePercent());
        stockZhAHist.setPriceRiseFall(adapter.getPriceRiseFall());
        stockZhAHist.setTurnoverRate(adapter.getTurnoverRate());
        stockZhAHist.setBizDate(adapter.getBizDate());
        stockZhAHist.setCirculatingMarketValue(adapter.getCirculatingMarketValue());
        stockZhAHist.setTotalMarketValue(adapter.getTotalMarketValue());
        return stockZhAHist;
    }

    @Override
    public StockZhAHist transfBasicStockToStockZhAHist(BasicStock adapter) {
        StockZhAHist stockZhAHist = new StockZhAHist();
        stockZhAHist.setStockCode(adapter.getStockCode());
        stockZhAHist.setStockName(adapter.getStockName());
        stockZhAHist.setStockMarketType(adapter.getStockMarketType());
        stockZhAHist.setOpeningPrice(adapter.getTodayOpenPrice());
        stockZhAHist.setClosingPrice(adapter.getLatestPrice());
        stockZhAHist.setHighestPrice(adapter.getHighestPrice());
        stockZhAHist.setLowestPrice(adapter.getLowestPrice());
        stockZhAHist.setVolume(adapter.getVolume());
        stockZhAHist.setTurnover(adapter.getTurnover());
        stockZhAHist.setAmplitude(adapter.getAmplitude());
        stockZhAHist.setChangePercent(adapter.getChangePercent());
        stockZhAHist.setPriceRiseFall(adapter.getPriceRiseFall());
        stockZhAHist.setTurnoverRate(adapter.getTurnoverRate());
        stockZhAHist.setBizDate(adapter.getBizDate());
        stockZhAHist.setCirculatingMarketValue(adapter.getCirculatingMarketValue());
        stockZhAHist.setTotalMarketValue(adapter.getTotalMarketValue());
        return stockZhAHist;
    }

    /**
     * 板块数据历史表转换
     *
     * @param adapter
     * @return
     */
    @Override
    public StockBoardDataHistEm transfStockBoardDataHistEm(StockBoardDataHistEmAdapter adapter) {
        StockBoardDataHistEm em = new StockBoardDataHistEm();
        em.setBoardCode(adapter.getBoardCode());
        em.setBoardName(adapter.getBoardName());
        em.setBoardType(adapter.getBoardType());
        em.setOpenPrice(adapter.getOpenPrice());
        em.setClosingPrice(adapter.getClosingPrice());
        em.setHighestPrice(adapter.getHighestPrice());
        em.setLowestPrice(adapter.getLowestPrice());
        em.setChangePercent(adapter.getChangePercent());
        em.setPriceRiseFall(adapter.getPriceRiseFall());
        em.setVolume(adapter.getVolume());
        em.setTurnover(adapter.getTurnover());
        em.setAmplitude(adapter.getAmplitude());
        em.setTurnoverRate(adapter.getTurnoverRate());
        em.setPeriod(adapter.getPeriod());
        em.setBizDate(DateUtils.formatDateYYYY_MM_DDToyyyyMMdd(adapter.getBizDate()));
        return em;
    }

    /**
     * ths股票资金流向转换
     *
     * @param adapter
     * @return
     */
    @Override
    public ThsStockFundFlow transfThsStockFundFlow(ThsStockFundFlowAdapter adapter) {
        ThsStockFundFlow thsStockFundFlow = new ThsStockFundFlow();
        thsStockFundFlow.setSerialNo(adapter.getSerialNo());
        thsStockFundFlow.setStockCode(adapter.getStockCode());
        thsStockFundFlow.setStockName(adapter.getStockName());
        thsStockFundFlow.setLatestPrice(StringUtils.isNotBlank(adapter.getLatestPrice()) ? new BigDecimal(adapter.getLatestPrice()) : null);
        if (StringUtils.isNotBlank(adapter.getChangePercent())) {
            thsStockFundFlow.setChangePercent(new BigDecimal(adapter.getChangePercent().replace("%", "")));
        }
        if (StringUtils.isNotBlank(adapter.getTurnoverRate())) {
            thsStockFundFlow.setTurnoverRate(new BigDecimal(adapter.getTurnoverRate().replace("%", "")));
        }

        if (StringUtils.isNotBlank(adapter.getInflowFunds())) {
            thsStockFundFlow.setInflowFunds(getNumberFromString(adapter.getInflowFunds()));
        }
        if (StringUtils.isNotBlank(adapter.getOutflowFunds())) {
            thsStockFundFlow.setOutflowFunds(getNumberFromString(adapter.getOutflowFunds()));
        }
        if (StringUtils.isNotBlank(adapter.getNetAmount())) {
            thsStockFundFlow.setNetAmount(getNumberFromString(adapter.getNetAmount()));
        }
        if (StringUtils.isNotBlank(adapter.getTurnover())) {
            thsStockFundFlow.setTurnover(getNumberFromString(adapter.getTurnover()));
        }
        thsStockFundFlow.setType(adapter.getType());

        if (StringUtils.isNotBlank(adapter.getTurnoverRateTemp())) {
            thsStockFundFlow.setTurnoverRate(new BigDecimal(adapter.getTurnoverRateTemp().replace("%", "")));
        }
        if (StringUtils.isNotEmpty(adapter.getNetAmountTemp())) {
            thsStockFundFlow.setNetAmount(getNumberFromString(adapter.getNetAmountTemp()));
        }
        if (StringUtils.isNotBlank(adapter.getChangePercenTemp())) {
            thsStockFundFlow.setChangePercent(new BigDecimal(adapter.getChangePercenTemp().replace("%", "")));
        }
        return thsStockFundFlow;
    }

    @Override
    public ThsBoardFundFlow transfThsBoardFundFlow(ThsBoardFundFlowAdapter adapter) {
        ThsBoardFundFlow thsBoardFundFlow = new ThsBoardFundFlow();
        thsBoardFundFlow.setSerialNo(adapter.getSerialNo());
        thsBoardFundFlow.setBoardCode(adapter.getBoardCode());
        thsBoardFundFlow.setBoardName(adapter.getBoardName());
        thsBoardFundFlow.setChangePercent(adapter.getChangePercent());
        thsBoardFundFlow.setMainstayInflowNetAmount(adapter.getMainstayInflowNetAmount());
        thsBoardFundFlow.setMainstayInflowNetPercent(adapter.getMainstayInflowNetPercent());
        thsBoardFundFlow.setSuperInflowNetAmount(adapter.getSuperInflowNetAmount());
        thsBoardFundFlow.setSuperInflowNetPercent(adapter.getSuperInflowNetPercent());
        thsBoardFundFlow.setBigInflowNetAmount(adapter.getBigInflowNetAmount());
        thsBoardFundFlow.setBigInflowNetPercent(adapter.getBigInflowNetPercent());
        thsBoardFundFlow.setMidleInflowNetAmount(adapter.getMidleInflowNetAmount());
        thsBoardFundFlow.setMidleInflowNetPercent(adapter.getMidleInflowNetPercent());
        thsBoardFundFlow.setSmallInflowNetAmount(adapter.getSmallInflowNetAmount());
        thsBoardFundFlow.setSmallInflowNetPercent(adapter.getSmallInflowNetPercent());
        thsBoardFundFlow.setMainstayInflowMaxStock(adapter.getMainstayInflowMaxStock());
        thsBoardFundFlow.setType(adapter.getType());
        thsBoardFundFlow.setSectorType(adapter.getSectorType());
        return thsBoardFundFlow;
    }

    @Override
    public ThsBoardFundFlow transfThsBoardFundFlow5(ThsBoardFundFlowAdapter5 adapter) {
        ThsBoardFundFlow thsBoardFundFlow = new ThsBoardFundFlow();
        thsBoardFundFlow.setSerialNo(adapter.getSerialNo());
        thsBoardFundFlow.setBoardCode(adapter.getBoardCode());
        thsBoardFundFlow.setBoardName(adapter.getBoardName());
        thsBoardFundFlow.setChangePercent(adapter.getChangePercent());
        thsBoardFundFlow.setMainstayInflowNetAmount(adapter.getMainstayInflowNetAmount());
        thsBoardFundFlow.setMainstayInflowNetPercent(adapter.getMainstayInflowNetPercent());
        thsBoardFundFlow.setSuperInflowNetAmount(adapter.getSuperInflowNetAmount());
        thsBoardFundFlow.setSuperInflowNetPercent(adapter.getSuperInflowNetPercent());
        thsBoardFundFlow.setBigInflowNetAmount(adapter.getBigInflowNetAmount());
        thsBoardFundFlow.setBigInflowNetPercent(adapter.getBigInflowNetPercent());
        thsBoardFundFlow.setMidleInflowNetAmount(adapter.getMidleInflowNetAmount());
        thsBoardFundFlow.setMidleInflowNetPercent(adapter.getMidleInflowNetPercent());
        thsBoardFundFlow.setSmallInflowNetAmount(adapter.getSmallInflowNetAmount());
        thsBoardFundFlow.setSmallInflowNetPercent(adapter.getSmallInflowNetPercent());
        thsBoardFundFlow.setMainstayInflowMaxStock(adapter.getMainstayInflowMaxStock());
        thsBoardFundFlow.setType(adapter.getType());
        thsBoardFundFlow.setSectorType(adapter.getSectorType());
        return thsBoardFundFlow;
    }

    @Override
    public ThsBoardFundFlow transfThsBoardFundFlow10(ThsBoardFundFlowAdapter10 adapter) {
        ThsBoardFundFlow thsBoardFundFlow = new ThsBoardFundFlow();
        thsBoardFundFlow.setSerialNo(adapter.getSerialNo());
        thsBoardFundFlow.setBoardCode(adapter.getBoardCode());
        thsBoardFundFlow.setBoardName(adapter.getBoardName());
        thsBoardFundFlow.setChangePercent(adapter.getChangePercent());
        thsBoardFundFlow.setMainstayInflowNetAmount(adapter.getMainstayInflowNetAmount());
        thsBoardFundFlow.setMainstayInflowNetPercent(adapter.getMainstayInflowNetPercent());
        thsBoardFundFlow.setSuperInflowNetAmount(adapter.getSuperInflowNetAmount());
        thsBoardFundFlow.setSuperInflowNetPercent(adapter.getSuperInflowNetPercent());
        thsBoardFundFlow.setBigInflowNetAmount(adapter.getBigInflowNetAmount());
        thsBoardFundFlow.setBigInflowNetPercent(adapter.getBigInflowNetPercent());
        thsBoardFundFlow.setMidleInflowNetAmount(adapter.getMidleInflowNetAmount());
        thsBoardFundFlow.setMidleInflowNetPercent(adapter.getMidleInflowNetPercent());
        thsBoardFundFlow.setSmallInflowNetAmount(adapter.getSmallInflowNetAmount());
        thsBoardFundFlow.setSmallInflowNetPercent(adapter.getSmallInflowNetPercent());
        thsBoardFundFlow.setMainstayInflowMaxStock(adapter.getMainstayInflowMaxStock());
        thsBoardFundFlow.setType(adapter.getType());
        thsBoardFundFlow.setSectorType(adapter.getSectorType());
        return thsBoardFundFlow;
    }

    @Override
    public StockZtPoolEm transfCzStockZt(CzStockZt adapter) {
        StockZtPoolEm em = new StockZtPoolEm();
        em.setStockCode(adapter.getCode());
        em.setStockName(adapter.getN());
        em.setChangePercent(String.valueOf(adapter.getZdp()));
        em.setLatestPrice(String.valueOf(adapter.getP()));
        em.setTurnover((long) adapter.getAmount());
        em.setCirculatingMarketValue(String.valueOf(adapter.getLtsz()));
        em.setTotalMarketValue(String.valueOf(adapter.getTshare()));
        em.setTurnoverRate(String.valueOf(adapter.getHs()));
        em.setSealedFunds((long) adapter.getFund());
        em.setBizDate(DateUtils.formatDateYYYY_MM_DDToyyyyMMdd(adapter.getTdate()));
        Date firstSealedTime = DateUtils.dateTime(DateUtils.YYYYMMDDHHMMSS, em.getBizDate() + adapter.getFbt().replace(":", ""));
        em.setFirstSealedTime(firstSealedTime);
        Date lastSealedTime = DateUtils.dateTime(DateUtils.YYYYMMDDHHMMSS, em.getBizDate() + adapter.getLbt().replace(":", ""));
        em.setLastSealedTime(lastSealedTime);
        em.setBreakNum((long) adapter.getZbc());
        em.setPriceLimitStatistics(adapter.getZttj());
        em.setContinuousNum((long) adapter.getLbc());
        em.setIndustry(adapter.getHybk());
        em.setDataSource("歪枣网");
        return em;
    }

    @Override
    public StockZtPoolStrongEm transfCzStockQs(CzStockQs adapter) {
        StockZtPoolStrongEm em = new StockZtPoolStrongEm();
        em.setPriceLimitUp(String.valueOf(adapter.getZtp()));
        em.setGrowthRate(String.valueOf(adapter.getZs()));
        if (0==adapter.getNh()){
            em.setIsNewHigh("否");
        }else {
            em.setIsNewHigh("是");
        }
        em.setVolumeRatio(String.valueOf(adapter.getLb()));
        if (1==adapter.getCc()){
            em.setSelectionReasons("60日新高");
        }  else if (2==adapter.getCc()){
            em.setSelectionReasons("近期多次涨停");
        }else if (3==adapter.getCc()){
            em.setSelectionReasons("60日新高且近期多次涨停");
        }

        em.setStockCode(adapter.getCode());
        em.setStockName(adapter.getN());
        em.setChangePercent(String.valueOf(adapter.getZdp()));
        em.setLatestPrice(String.valueOf(adapter.getP()));
        em.setTurnover((long) adapter.getAmount());
        em.setCirculatingMarketValue(String.valueOf(adapter.getLtsz()));
        em.setTotalMarketValue(String.valueOf(adapter.getTshare()));
        em.setTurnoverRate(String.valueOf(adapter.getHs()));
        em.setBizDate(DateUtils.formatDateYYYY_MM_DDToyyyyMMdd(adapter.getTdate()));
        em.setPriceLimitStatistics(adapter.getZttj());
        em.setIndustry(adapter.getHybk());
        em.setDataSource("歪枣网");
        return em;
    }

    @Override
    public StockZtPoolZbgcEm transfCzStockZb(CzStockZb adapter) {
        StockZtPoolZbgcEm em = new StockZtPoolZbgcEm();
        em.setPriceLimitUp(String.valueOf(adapter.getZtp()));
        em.setGrowthRate(String.valueOf(adapter.getZs()));
        em.setBizDate(DateUtils.formatDateYYYY_MM_DDToyyyyMMdd(adapter.getTdate()));
        Date firstSealedTime = DateUtils.dateTime(DateUtils.YYYYMMDDHHMMSS, em.getBizDate() + adapter.getFbt().replace(":", ""));
        em.setFirstCoverDate(firstSealedTime);
        em.setExplosionNum(String.valueOf(adapter.getZbc()));
        em.setAmplitude(String.valueOf(adapter.getZf()));
        em.setStockCode(adapter.getCode());
        em.setStockName(adapter.getN());
        em.setChangePercent(String.valueOf(adapter.getZdp()));
        em.setLatestPrice(String.valueOf(adapter.getP()));
        em.setTurnover((long) adapter.getAmount());
        em.setCirculatingMarketValue(String.valueOf(adapter.getLtsz()));
        em.setTotalMarketValue(String.valueOf(adapter.getTshare()));
        em.setTurnoverRate(String.valueOf(adapter.getHs()));
        em.setPriceLimitStatistics(adapter.getZttj());
        em.setIndustry(adapter.getHybk());
        em.setDataSource("歪枣网");
        return em;
    }

    @Override
    public StockZtPoolDtgcEm transfCzStockDt(CzStockDt adapter) {
        StockZtPoolDtgcEm em = new StockZtPoolDtgcEm();
        em.setDynamicPriceRatio(String.valueOf(adapter.getPe()));
        em.setSealedFunds((long)adapter.getFund());
        em.setTurnoverOnBoard((long)adapter.getFba());
        em.setContinuousLimitDown(String.valueOf(adapter.getDays()));
        em.setOpenNum(String.valueOf(adapter.getOc()));
        em.setBizDate(DateUtils.formatDateYYYY_MM_DDToyyyyMMdd(adapter.getTdate()));
        Date firstSealedTime = DateUtils.dateTime(DateUtils.YYYYMMDDHHMMSS, em.getBizDate() + adapter.getLbt().replace(":", ""));
        em.setLastSealedTime(firstSealedTime);
        em.setStockCode(adapter.getCode());
        em.setStockName(adapter.getN());
        em.setChangePercent(String.valueOf(adapter.getZdp()));
        em.setLatestPrice(String.valueOf(adapter.getP()));
        em.setTurnover((long) adapter.getAmount());
        em.setCirculatingMarketValue(String.valueOf(adapter.getLtsz()));
        em.setTotalMarketValue(String.valueOf(adapter.getTshare()));
        em.setTurnoverRate(String.valueOf(adapter.getHs()));
        em.setIndustry(adapter.getHybk());
        em.setDataSource("歪枣网");
        return em;
    }

    private BigDecimal getNumberFromString(String str) {

        String numberStr;
        BigDecimal multiplier;

        // 解析单位和数值部分
        if (str.endsWith("亿")) {
            multiplier = UNIT_YI;
            numberStr = str.substring(0, str.length() - 1);
        } else if (str.endsWith("万")) {
            multiplier = UNIT_WAN;
            numberStr = str.substring(0, str.length() - 1);
        } else {
            multiplier = new BigDecimal("1");
            numberStr = str;
        }
        BigDecimal number;
        try {
            number = new BigDecimal(numberStr)
                    .multiply(multiplier)
                    .setScale(2, RoundingMode.HALF_UP); // 保留2位小数（四舍五入）
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("数字格式错误：" + numberStr, e);
        }
        return number;
    }
}
