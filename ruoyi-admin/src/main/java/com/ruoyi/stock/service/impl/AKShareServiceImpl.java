package com.ruoyi.stock.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.enums.BoardTypeEnum;
import com.ruoyi.common.enums.StockMarketTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.domain.Adapter.*;
import com.ruoyi.stock.service.AKShareService;
import com.ruoyi.stock.service.TransfService;
import com.ruoyi.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class AKShareServiceImpl implements AKShareService {

    private static final String BasicUrl = "http://127.0.0.1:8084/api/public/";

    @Resource(name = "customHttpClient")
    private HttpClient httpClient;


    @Autowired
    private TransfService trfService;

    /**
     * 根据传入的业务日期获取涨停板数据
     * <p>
     * 接口: stock_zt_pool_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/ztb/detail#type=ztgc
     * <p>
     * 描述: 东方财富网-行情中心-涨停板行情-涨停股池
     * <p>
     * 限量: 单次返回指定 date 的涨停股池数据; 该接口只能获取近期的数据
     */
    @Override
    public List<StockZtPoolEm> getStockZtPoolEm(String bizDate) {
        if (StringUtils.isBlank(bizDate)) {
            return new ArrayList<>();
        }

        String url = BasicUrl + "stock_zt_pool_em?date=" + bizDate;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockZtPoolEmAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockZtPoolEmAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockZtPoolEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZtPoolEm> returnList = new ArrayList<>();
        for (StockZtPoolEmAdapter adapter : stockList) {
            adapter.setBizDate(bizDate);
            adapter.setDataSource("akshare");
            StockZtPoolEm stockZtPoolEm = trfService.transfStockZtPoolEm(adapter);
            returnList.add(stockZtPoolEm);
        }

        return returnList;
    }

    /**
     * 根据传入的业务日期获取强势股池数据
     * <p>
     * 接口: stock_zt_pool_strong_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/ztb/detail#type=qsgc
     * <p>
     * 描述: 东方财富网-行情中心-涨停板行情-强势股池
     * <p>
     * 限量: 单次返回指定 date 的强势股池数据；该接口只能获取近期的数据
     */
    @Override
    public List<StockZtPoolStrongEm> getStockZtPoolStrongEm(String bizDate) {
        if (StringUtils.isBlank(bizDate)) {
            return new ArrayList<>();
        }

        String url = BasicUrl + "stock_zt_pool_strong_em?date=" + bizDate;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockZtPoolStrongEmAdapter> stockList = new ArrayList<>();


        List<StockZtPoolStrongEm> returnList = new ArrayList<>();
        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockZtPoolStrongEmAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockZtPoolStrongEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        for (StockZtPoolStrongEmAdapter adapter : stockList) {
            adapter.setBizDate(bizDate);
            adapter.setDataSource("akshare");
            returnList.add(trfService.transfStockZtPoolStrongEm(adapter));
        }
        return returnList;
    }

    /**
     * 根据传入的业务日期获取炸板股池数据
     * <p>
     * 接口: stock_zt_pool_zbgc_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/ztb/detail#type=zbgc
     * <p>
     * 描述: 东方财富网-行情中心-涨停板行情-炸板股池
     * <p>
     * 限量: 单次返回指定 date 的炸板股池数据；该接口只能获取近期的数据
     */
    @Override
    public List<StockZtPoolZbgcEm> getStockZtPoolZbgcEm(String bizDate) {
        if (StringUtils.isBlank(bizDate)) {
            return new ArrayList<>();
        }

        String url = BasicUrl + "stock_zt_pool_zbgc_em?date=" + bizDate;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockZtPoolZbgcEmAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockZtPoolZbgcEmAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockZtPoolZbgcEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZtPoolZbgcEm> returnList = new ArrayList<>();
        for (StockZtPoolZbgcEmAdapter adapter : stockList) {
            adapter.setBizDate(bizDate);
            adapter.setDataSource("akshare");
            StockZtPoolZbgcEm stockZtPoolZbgcEm = trfService.transfStockZtPoolZbgcEm(adapter);
            returnList.add(stockZtPoolZbgcEm);
        }
        return returnList;
    }

    /**
     * 根据传入的业务日期获取跌停股池数据
     * <p>
     * 接口: stock_zt_pool_dtgc_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/ztb/detail#type=zbgc
     * <p>
     * 描述: 东方财富网-行情中心-涨停板行情-跌停股池
     * <p>
     * 限量: 单次返回指定 date 的跌停股池数据；该接口只能获取近期的数据
     */
    @Override
    public List<StockZtPoolDtgcEm> getStockZtPoolDtgcEm(String bizDate) {
        if (StringUtils.isBlank(bizDate)) {
            return new ArrayList<>();
        }

        String url = BasicUrl + "stock_zt_pool_dtgc_em?date=" + bizDate;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockZtPoolDtgcEmAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockZtPoolDtgcEmAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockZtPoolDtgcEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZtPoolDtgcEm> returnList = new ArrayList<>();
        for (StockZtPoolDtgcEmAdapter adapter : stockList) {
            adapter.setBizDate(bizDate);
            adapter.setDataSource("akshare");
            StockZtPoolDtgcEm stockZtPoolDtgcEm = trfService.transfStockZtPoolDtgcEm(adapter);
            returnList.add(stockZtPoolDtgcEm);
        }
        return returnList;
    }

    /**
     * 历史行情数据-东财
     * 接口: stock_zh_a_hist
     * <p>
     * 目标地址: https://quote.eastmoney.com/concept/sh603777.html?from=classic(示例)
     * <p>
     * 描述: 东方财富-沪深京 A 股日频率数据; 历史数据按日频率更新, 当日收盘价请在收盘后获取
     * <p>
     * 限量: 单次返回指定沪深京 A 股上市公司、指定周期和指定日期间的历史行情日频率数据
     *
     * @param symbol          symbol='603777'; 股票代码可以在 ak.stock_zh_a_spot_em() 中获取
     * @param period          period='daily'; choice of {'daily', 'weekly', 'monthly'}
     * @param start_date      start_date='20210301'; 开始查询的日期
     * @param end_date        end_date='20210616'; 结束查询的日期
     * @param adjust          默认返回不复权的数据; qfq: 返回前复权后的数据; hfq: 返回后复权后的数据
     * @param timeout         timeout=None; 默认不设置超时参数
     * @param stockName       股票名称
     * @param stockMarketType 股票市场类型
     * @return
     */
    @Override
    public List<StockZhAHist> getStockZhAHist(String symbol, String period, String start_date, String end_date, String adjust, String timeout, String stockName, String stockMarketType) {
        if (StringUtils.isBlank(symbol)) {
            return new ArrayList<>();
        }
        if (StringUtils.isBlank(period)) {
            period = "daily";
        }
        String url = BasicUrl + "stock_zh_a_hist?symbol=" + symbol + "&period=" + period;
        if (StringUtils.isNotBlank(start_date)) {
            url = url + "&start_date=" + start_date;
        }
        if (StringUtils.isNotBlank(end_date)) {
            url = url + "&end_date=" + end_date;
        }
        if (StringUtils.isNotBlank(adjust)) {
            url = url + "&adjust=" + adjust;
        }
        if (StringUtils.isNotBlank(timeout)) {
            url = url + "&timeout=" + timeout;
        }

        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockZhAHistAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockZhAHistAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockZhAHist error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZhAHist> returnList = new ArrayList<>();
        for (StockZhAHistAdapter adapter : stockList) {
            adapter.setBizDate(DateUtils.formatDate(adapter.getBizDate()));
            adapter.setStockName(stockName);
            adapter.setStockMarketType(stockMarketType);
            StockZhAHist stockBoard = trfService.transfStockZhAHist(adapter);
            returnList.add(stockBoard);
        }
        return returnList;
    }

    /**
     * 获取东方财富-概念板块所有数据
     *
     * @return 接口: stock_board_concept_name_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/center/boardlist.html#concept_board
     * <p>
     * 描述: 东方财富网-行情中心-沪深京板块-概念板块
     * <p>
     * 限量: 单次返回当前时刻所有概念板块的实时行情数据
     */
    @Override
    public List<StockBoard> getStockBoardConceptNameEm() {

        String url = BasicUrl + "stock_board_concept_name_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockBoardAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockBoardAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardConceptNameEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }

        List<StockBoard> returnList = new ArrayList<>();
        for (StockBoardAdapter adapter : stockList) {
            adapter.setBoardType(BoardTypeEnum.CONCEPT_BOARD.getInfo());
            adapter.setDataSource("东方财富");
            StockBoard stockBoard = trfService.transfStockBoardConcept(adapter);
            returnList.add(stockBoard);
        }
        return returnList;
    }

    /**
     * 单次返回指定概念板块的实时行情数据
     *
     * @param symbol 板块名称
     * @return 东方财富-概念板块-实时行情
     * 接口: stock_board_concept_spot_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/bk/90.BK0818.html
     * <p>
     * 描述: 东方财富网-行情中心-沪深京板块-概念板块-实时行情
     * <p>
     * 限量: 单次返回指定概念板块的实时行情数据
     */
    @Override
    public StockoardConceptSpotOutDTO getStockoardConceptSpotEm(String symbol) {
        StockoardConceptSpotOutDTO outDTO = new StockoardConceptSpotOutDTO();
        if (StringUtils.isBlank(symbol)) {
            return null;
        }

        String url = BasicUrl + "stock_board_concept_spot_em?symbol=" + symbol;
        String s = httpClient.get(url);
        if (null == s) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockoardConceptSpotDTO> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockoardConceptSpotDTO>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockoardConceptSpotEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return null;
        }
        for (StockoardConceptSpotDTO dto : stockList) {
            String item = dto.getItem();
            String value = dto.getValue();
            switch (item) {
                case "最新":
                    outDTO.setLatestPrice(value);
                    break;
                case "最高":
                    outDTO.setHighestPrice(value);
                    break;
                case "最低":
                    outDTO.setLowestPrice(value);
                    break;
                case "开盘":
                    outDTO.setOpeningPrice(value);
                    break;
                case "成交量":
                    outDTO.setVolume(value);
                    break;
                case "成交额":
                    outDTO.setTurnover(value);
                    break;
                case "换手率":
                    outDTO.setTurnoverRate(value);
                    break;
                case "涨跌额":
                    outDTO.setPriceRiseFall(value);
                    break;
                case "涨跌幅":
                    outDTO.setChangePercent(value);
                    break;
                case "振幅":
                    outDTO.setAmplitude(value);
                    break;
            }
        }
        return outDTO;
    }

    /**
     * 获取东方财富-概念板块-成份股
     *
     * @param boardCode 板块代码
     * @param boardName 板块名称
     * @return 接口: stock_board_concept_cons_em
     * <p>
     * 目标地址: http://quote.eastmoney.com/center/boardlist.html#boards-BK06551
     * <p>
     * 描述: 东方财富-沪深板块-概念板块-板块成份
     * <p>
     * 限量: 单次返回当前时刻所有成份股
     */
    @Override
    public List<StockBoardConsEm> getStockBoardConceptConsEm(String boardCode, String boardName) {
        if (StringUtils.isBlank(boardCode)) {
            return null;
        }

        String url = BasicUrl + "stock_board_concept_cons_em?symbol=" + boardCode;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockBoardConsAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockBoardConsAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardConceptConsEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }

        List<StockBoardConsEm> returnList = new ArrayList<>();

        for (StockBoardConsAdapter adapter : stockList) {
            adapter.setBoardCode(boardCode);
            adapter.setBoardName(boardName);
            StockBoardConsEm stockBoardConsEm = trfService.transfStockBoardConsEm(adapter);
            returnList.add(stockBoardConsEm);
        }
        return returnList;
    }

    /**
     * 单次返回指定概念板块的实时历史数据
     *
     * @param symbol     板块名称,只能是名称
     * @param period     周期 	period="daily"; choice of {"daily", "weekly", "monthly"}
     * @param start_date 开始日期 start_date="20220101"
     * @param end_date   结束日期 end_date="20221128"
     * @param adjust     复权类型 adjust=""; choice of {'': 不复权, 默认; "qfq": 前复权, "hfq": 后复权}
     *                   <p>
     *                   接口: stock_board_concept_hist_em
     *                   目标地址: http://quote.eastmoney.com/bk/90.BK0715.html
     *                   描述: 东方财富-沪深板块-概念板块-历史行情数据
     *                   限量: 单次返回指定 symbol 和 adjust 的历史数据
     *                   <p>
     *                   日期        开盘      收盘      最高  ...     成交量       成交额    振幅   换手率
     *                   0    2022-01-04  1151.48  1143.13  1155.59  ...  57503811  4.502800e+10  1.22  1.42
     *                   1    2022-01-05  1141.91  1106.74  1141.91  ...  54859005  4.212589e+10  3.74  1.35
     *                   2    2022-01-06  1100.52  1115.16  1122.27  ...  42174619  3.282551e+10  2.25  1.04
     *                   3    2022-01-07  1115.49  1089.20  1118.08  ...  44604672  3.478526e+10  2.61  1.10
     *                   4    2022-01-10  1083.43  1082.51  1088.21  ...  36095282  2.706919e+10  1.23  0.89
     *                   ..          ...      ...      ...      ...  ...       ...           ...   ...   ...
     *                   756  2025-02-21   985.80   986.71   987.88  ...  42772223  2.825437e+10  0.91  1.06
     *                   757  2025-02-24   986.75   996.56   997.62  ...  43658453  3.027056e+10  1.13  1.08
     *                   758  2025-02-25   990.79   990.51   997.28  ...  38009252  2.627609e+10  1.00  0.94
     *                   759  2025-02-26   991.87  1002.54  1002.57  ...  39788170  2.634823e+10  1.08  0.98
     *                   760  2025-02-27  1002.54   995.28  1003.97  ...  38629884  2.581886e+10  1.89  0.95
     * @return
     */
    @Override
    public List<StockBoardDataHistEm> getStockBoardConceptHistEm(String boardCode, String symbol, String period, String start_date, String end_date, String adjust) {
        if (StringUtils.isBlank(symbol) || StringUtils.isEmpty(start_date)) {
            return new ArrayList<>();
        }
        if (StringUtils.isBlank(period)) {
            period = "daily";
        }

        String url = BasicUrl + "stock_board_concept_hist_em?symbol=" + symbol + "&period=" + period + "&start_date=" + start_date + "&end_date=" + end_date;
        if (StringUtils.isNotBlank(adjust)) {
            url = url + "&adjust=" + adjust;
        }

        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockBoardDataHistEmAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockBoardDataHistEmAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardConceptHistEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }

        List<StockBoardDataHistEm> returnList = new ArrayList<>();

        for (StockBoardDataHistEmAdapter adapter : stockList) {
            adapter.setBoardCode(boardCode);
            adapter.setBoardName(symbol);
            adapter.setBoardType("概念板块");
            adapter.setPeriod(period);
            StockBoardDataHistEm stockBoardDataHistEm = trfService.transfStockBoardDataHistEm(adapter);
            returnList.add(stockBoardDataHistEm);
        }

        return returnList;
    }

    /**
     * 单次返回指定行业板块的实时历史数据
     *
     * @param symbol     板块名称,只能是名称
     * @param period     周期 	period="日k"; 周期; choice of {"日k", "周k", "月k"}
     * @param start_date 开始日期 start_date="20220101"
     * @param end_date   结束日期 end_date="20221128"
     * @param adjust     复权类型 adjust=""; choice of {'': 不复权, 默认; "qfq": 前复权, "hfq": 后复权}
     * @return 东方财富-指数-日频
     * 接口: stock_board_industry_hist_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/bk/90.BK1027.html
     * <p>
     * 描述: 东方财富-沪深板块-行业板块-历史行情数据
     * <p>
     * 限量: 单次返回指定 symbol 和 adjust 的所有历史数据
     */
    @Override
    public List<StockBoardDataHistEm> getstockBoardIndustryHistEm(String boardCode, String symbol, String period, String start_date, String end_date, String adjust) {
        if (StringUtils.isBlank(symbol) || StringUtils.isEmpty(start_date)) {
            return new ArrayList<>();
        }
        if (StringUtils.isBlank(period)) {
            period = "日k";
        }

        String url = BasicUrl + "stock_board_industry_hist_em?symbol=" + symbol + "&period=" + period + "&start_date=" + start_date + "&end_date=" + end_date;
        if (StringUtils.isNotBlank(adjust)) {
            url = url + "&adjust=" + adjust;
        }

        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockBoardDataHistEmAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockBoardDataHistEmAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardConceptHistEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }

        List<StockBoardDataHistEm> returnList = new ArrayList<>();

        for (StockBoardDataHistEmAdapter adapter : stockList) {
            adapter.setBoardCode(boardCode);
            adapter.setBoardName(symbol);
            adapter.setBoardType("行业板块");
            adapter.setPeriod(period);
            StockBoardDataHistEm stockBoardDataHistEm = trfService.transfStockBoardDataHistEm(adapter);
            returnList.add(stockBoardDataHistEm);
        }

        return returnList;
    }


    /**
     * 返回指定概念板块按分钟的历史数据，当天的数据
     *
     * @param symbol period="5"; choice of {"1", "5", "15", "30", "60"}  传入1就是每分钟的数据，是当天的，其它的是近45左右的数据
     * @param period 板块名称,只能是名称
     *               <p>
     *               接口: stock_board_concept_hist_min_em
     *               目标地址: http://quote.eastmoney.com/bk/90.BK0715.html
     *               描述: 东方财富-沪深板块-概念板块-分时历史行情数据
     *               限量: 单次返回指定 symbol 和 period 的历史数据
     *               <p>
     *               日期时间      开盘      收盘  ...    成交量     成交额    最新价
     *               0    2023-06-29 09:30  982.68  982.68  ...   4108   3937927.0  982.676
     *               1    2023-06-29 09:31  982.68  983.77  ...  17078  17015428.0  982.802
     *               2    2023-06-29 09:32  983.74  982.80  ...   7764   8582773.0  982.864
     *               3    2023-06-29 09:33  982.70  984.44  ...  14440  12411281.0  983.023
     *               4    2023-06-29 09:34  984.15  986.00  ...  11301  10606470.0  983.438
     *               ..                ...     ...     ...  ...    ...         ...      ...
     *               236  2023-06-29 14:56  989.46  988.81  ...  10894  10521292.0  989.834
     *               237  2023-06-29 14:57  988.91  988.82  ...  11955  13259401.0  989.828
     *               238  2023-06-29 14:58  988.93  989.12  ...    615    927170.0  989.828
     *               239  2023-06-29 14:59  989.12  989.12  ...      0         0.0  989.828
     *               240  2023-06-29 15:00  989.12  989.03  ...  13803  14810685.0  989.819
     */
    @Override
    public List<StockBoardConceptHistMinEm> getStockBoardConceptHistMinEm(String symbol, String period) {
        if (StringUtils.isBlank(symbol) || StringUtils.isEmpty(period)) {
            return new ArrayList<>();
        }

        String url = BasicUrl + "stock_board_concept_hist_min_em?symbol=" + symbol + "&period=" + period;

        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockBoardConceptHistMinEm> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockBoardConceptHistMinEm>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardConceptHistMinEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        return stockList;
    }

    /**
     * 东方财富-行业板块
     * 接口: stock_board_industry_name_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/center/boardlist.html#industry_board
     * <p>
     * 描述: 东方财富-沪深京板块-行业板块
     * <p>
     * 限量: 单次返回当前时刻所有行业板的实时行情数据
     * <p>
     * 排名   板块名称 板块代码   最新价    涨跌额  ...  换手率  上涨家数  下跌家数  领涨股票  领涨股票-涨跌幅
     * 0      1      煤炭行业   BK0437  11332.18  518.57  ...  1.34    35     0  云煤能源     10.12
     * 1      2     互联网服务   BK0447  16332.38  673.12  ...  3.38   144     3   信雅达      9.97
     * 2      3     计算机设备   BK0735    867.59   34.86  ...  3.00    50     1  中威电子     20.08
     * 3      4      通信服务   BK0736    793.26   28.90  ...  1.56    40     1   二六三      9.95
     * 4      5      软件开发   BK0737    691.48   24.85  ...  3.02   179     8  国投智能     20.00
     *
     * @return
     */
    @Override
    public List<StockBoard> getStockBoardIndustryNameEm() {
        String url = BasicUrl + "stock_board_industry_name_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockBoardAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockBoardAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardIndustryNameEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }

        List<StockBoard> returnList = new ArrayList<>();
        for (StockBoardAdapter adapter : stockList) {

            adapter.setBoardType(BoardTypeEnum.INDUSTRY_BOARD.getInfo());
            adapter.setDataSource("东方财富");
            StockBoard stockBoard = trfService.transfStockBoardConcept(adapter);
            returnList.add(stockBoard);
        }
        return returnList;

    }

    /**
     * 东方财富-行业板块-实时行情
     * 接口: stock_board_industry_spot_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/bk/90.BK1027.html
     * <p>
     * 描述: 东方财富网-沪深板块-行业板块-实时行情
     * <p>
     * 限量: 单次返回指定板块的实时行情数据
     * <p>
     * item         value
     * 0   最新  1.957370e+03
     * 1   最高  1.990130e+03
     * 2   最低  1.953700e+03
     * 3   开盘  1.964160e+03
     * 4  成交量  1.386981e+07
     * 5  成交额  2.165428e+10
     * 6  换手率  2.970000e+00
     * 7  涨跌额 -1.791000e+01
     * 8  涨跌幅 -9.100000e-01
     * 9   振幅  1.840000e+00
     *
     * @param symbol
     * @return
     */
    @Override
    public StockoardConceptSpotOutDTO getStockBoardIndustrySpotEm(String symbol) {

        StockoardConceptSpotOutDTO outDTO = new StockoardConceptSpotOutDTO();
        if (StringUtils.isBlank(symbol)) {
            return null;
        }

        String url = BasicUrl + "stock_board_industry_spot_em?symbol=" + symbol;
        String s = httpClient.get(url);
        if (null == s) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockoardConceptSpotDTO> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockoardConceptSpotDTO>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardIndustrySpotEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return null;
        }
        for (StockoardConceptSpotDTO dto : stockList) {
            String item = dto.getItem();
            String value = dto.getValue();
            switch (item) {
                case "最新":
                    outDTO.setLatestPrice(value);
                    break;
                case "最高":
                    outDTO.setHighestPrice(value);
                    break;
                case "最低":
                    outDTO.setLowestPrice(value);
                    break;
                case "开盘":
                    outDTO.setOpeningPrice(value);
                    break;
                case "成交量":
                    outDTO.setVolume(value);
                    break;
                case "成交额":
                    outDTO.setTurnover(value);
                    break;
                case "换手率":
                    outDTO.setTurnoverRate(value);
                    break;
                case "涨跌额":
                    outDTO.setPriceRiseFall(value);
                    break;
                case "涨跌幅":
                    outDTO.setChangePercent(value);
                    break;
                case "振幅":
                    outDTO.setAmplitude(value);
                    break;
            }
        }
        return outDTO;
    }

    /**
     * 东方财富-成份股
     * 接口: stock_board_industry_cons_em
     * <p>
     * 目标地址: https://data.eastmoney.com/bkzj/BK1027.html
     * <p>
     * 描述: 东方财富-沪深板块-行业板块-板块成份
     * <p>
     * 限量: 单次返回指定 symbol 的所有成份股
     *
     * @param boardCode 板块代码
     * @param boardName 板块名称
     * @return
     */
    @Override
    public List<StockBoardConsEm> getStockBoardIndustryConsEm(String boardCode, String boardName) {
        if (StringUtils.isBlank(boardCode)) {
            return null;
        }

        String url = BasicUrl + "stock_board_industry_cons_em?symbol=" + boardCode;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockBoardConsAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockBoardConsAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardIndustryConsEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockBoardConsEm> returnList = new ArrayList<>();

        for (StockBoardConsAdapter adapter : stockList) {
            adapter.setBoardCode(boardCode);
            adapter.setBoardName(boardName);
            StockBoardConsEm stockBoardConsEm = trfService.transfStockBoardConsEm(adapter);
            returnList.add(stockBoardConsEm);
        }

        return returnList;
    }

    /**
     * 沪深京 A 股
     * 接口: stock_zh_a_spot_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/center/gridlist.html#hs_a_board
     * <p>
     * 描述: 东方财富网-沪深京 A 股-实时行情数据
     * <p>
     * 限量: 单次返回所有沪深京 A 股上市公司的实时行情数据
     *
     * @return
     */
    @Override
    public List<StockZhAHist> getStockZhASpotEm() {
        String url = BasicUrl + "stock_zh_a_spot_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BasicStockAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<BasicStockAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getstockShASpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZhAHist> returnList = new ArrayList<>();

        for (BasicStockAdapter adapter : stockList) {
            adapter.setBizDate(DateUtils.getBizDate());
            StockZhAHist basicStock = trfService.transfBasicStockAdapterToStockZhAHist(adapter);
            returnList.add(basicStock);
        }

        return returnList;
    }


    /**
     * 沪 A 股
     * 接口: stock_sh_a_spot_em
     * <p>
     * 目标地址: http://quote.eastmoney.com/center/gridlist.html#sh_a_board
     * <p>
     * 描述: 东方财富网-沪 A 股-实时行情数据
     * <p>
     * 限量: 单次返回所有沪 A 股上市公司的实时行情数据
     *
     * @return
     */
    @Override
    public List<BasicStock> getStockShASpotEm() {

        String url = BasicUrl + "stock_sh_a_spot_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BasicStockAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<BasicStockAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getstockShASpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<BasicStock> returnList = new ArrayList<>();

        for (BasicStockAdapter adapter : stockList) {
            adapter.setBizDate(DateUtils.getBizDate());
            adapter.setStockMarketType(StockMarketTypeEnum.SH_A.getInfo());
            BasicStock basicStock = trfService.transfBasicStock(adapter);
            returnList.add(basicStock);
        }

        return returnList;
    }

    /**
     * 深 A 股
     * 接口: stock_sz_a_spot_em
     * <p>
     * 目标地址: http://quote.eastmoney.com/center/gridlist.html#sz_a_board
     * <p>
     * 描述: 东方财富网-深 A 股-实时行情数据
     * <p>
     * 限量: 单次返回所有深 A 股上市公司的实时行情数据
     *
     * @return
     */
    @Override
    public List<BasicStock> getStockSzASpotEm() {
        String url = BasicUrl + "stock_sz_a_spot_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BasicStockAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<BasicStockAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getstockSzASpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<BasicStock> returnList = new ArrayList<>();

        for (BasicStockAdapter adapter : stockList) {
            adapter.setBizDate(DateUtils.getBizDate());
            adapter.setStockMarketType(StockMarketTypeEnum.SZ_A.getInfo());
            BasicStock basicStock = trfService.transfBasicStock(adapter);
            returnList.add(basicStock);
        }

        return returnList;
    }

    /**
     * 京 A 股
     * 接口: stock_bj_a_spot_em
     * <p>
     * 目标地址: http://quote.eastmoney.com/center/gridlist.html#bj_a_board
     * <p>
     * 描述: 东方财富网-京 A 股-实时行情数据
     * <p>
     * 限量: 单次返回所有京 A 股上市公司的实时行情数据
     *
     * @return
     */
    @Override
    public List<BasicStock> getStockBjASpotEm() {
        String url = BasicUrl + "stock_bj_a_spot_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BasicStockAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<BasicStockAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getstockBjASpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<BasicStock> returnList = new ArrayList<>();

        for (BasicStockAdapter adapter : stockList) {
            adapter.setBizDate(DateUtils.getBizDate());
            adapter.setStockMarketType(StockMarketTypeEnum.BJ_A.getInfo());
            BasicStock basicStock = trfService.transfBasicStock(adapter);
            returnList.add(basicStock);
        }

        return returnList;
    }

    /**
     * 新股
     * 接口: stock_new_a_spot_em
     * <p>
     * 目标地址: http://quote.eastmoney.com/center/gridlist.html#newshares
     * <p>
     * 描述: 东方财富网-新股-实时行情数据
     * <p>
     * 限量: 单次返回所有新股上市公司的实时行情数据
     *
     * @return
     */
    @Override
    public List<BasicStock> getStockNewASpotEm() {
        String url = BasicUrl + "stock_new_a_spot_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BasicStockAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<BasicStockAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getstockNewASpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<BasicStock> returnList = new ArrayList<>();

        for (BasicStockAdapter adapter : stockList) {
            adapter.setBizDate(DateUtils.getBizDate());
            adapter.setStockMarketType(StockMarketTypeEnum.NEW_A.getInfo());
            BasicStock basicStock = trfService.transfBasicStock(adapter);
            returnList.add(basicStock);
        }

        return returnList;
    }

    /**
     * 创业板
     * 接口: stock_cy_a_spot_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/center/gridlist.html#gem_board
     * <p>
     * 描述: 东方财富网-创业板-实时行情
     * <p>
     * 限量: 单次返回所有创业板的实时行情数据
     *
     * @return
     */
    @Override
    public List<BasicStock> getStockCyASpotEm() {
        String url = BasicUrl + "stock_cy_a_spot_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BasicStockAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<BasicStockAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getstockCyASpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<BasicStock> returnList = new ArrayList<>();

        for (BasicStockAdapter adapter : stockList) {
            adapter.setBizDate(DateUtils.getBizDate());
            adapter.setStockMarketType(StockMarketTypeEnum.CY_A.getInfo());
            BasicStock basicStock = trfService.transfBasicStock(adapter);
            returnList.add(basicStock);
        }

        return returnList;
    }

    /**
     * 科创板
     * 接口: stock_kc_a_spot_em
     * <p>
     * 目标地址: http://quote.eastmoney.com/center/gridlist.html#kcb_board
     * <p>
     * 描述: 东方财富网-科创板-实时行情
     * <p>
     * 限量: 单次返回所有科创板的实时行情数据
     *
     * @return
     */
    @Override
    public List<BasicStock> getStockKcASpotEm() {
        String url = BasicUrl + "stock_kc_a_spot_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BasicStockAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<BasicStockAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getstockKcASpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<BasicStock> returnList = new ArrayList<>();

        for (BasicStockAdapter adapter : stockList) {
            adapter.setBizDate(DateUtils.getBizDate());
            adapter.setStockMarketType(StockMarketTypeEnum.KC_A.getInfo());
            BasicStock basicStock = trfService.transfBasicStock(adapter);
            returnList.add(basicStock);
        }

        return returnList;
    }

    /**
     * 交易日历
     * 接口: tool_trade_date_hist_sina
     * <p>
     * 目标地址: https://finance.sina.com.cn
     * <p>
     * 描述: 新浪财经-股票交易日历数据
     * <p>
     * 限量: 单次返回从 1990-12-19 到 2024-12-31 之间的股票交易日历数据, 这里补充 1992-05-04 进入交易日
     *
     * @return
     */
    @Override
    public List<StockTradeDate> getToolTradeDateHistSina() {
        String url = BasicUrl + "tool_trade_date_hist_sina";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockTradeDateAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<StockTradeDateAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getstockKcASpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockTradeDate> returnList = new ArrayList<>();

        for (StockTradeDateAdapter adapter : stockList) {
            StockTradeDate stockTradeDate = trfService.transfStockTradeDate(adapter);
            returnList.add(stockTradeDate);
        }

        return returnList;

    }

    /**
     * 资金流向
     * 同花顺
     * 个股资金流
     * 接口: stock_fund_flow_individual
     * <p>
     * 目标地址: https://data.10jqka.com.cn/funds/ggzjl/#refCountId=data_55f13c2c_254
     * <p>
     * 描述: 同花顺-数据中心-资金流向-个股资金流
     * <p>
     * 限量: 单次获取指定 symbol 的概念资金流数据
     * symbol="即时"; choice of {“即时”, "3日排行", "5日排行", "10日排行", "20日排行"}
     */
    @Override
    public List<ThsStockFundFlow> getStockFundFlowIndividual(String symbol) {
        if (StringUtils.isBlank(symbol)) {
            symbol = "即时";
        }

        String url = BasicUrl + "stock_fund_flow_individual?symbol=" + symbol;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<ThsStockFundFlowAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<ThsStockFundFlowAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getStockBoardIndustryConsEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<ThsStockFundFlow> returnList = new ArrayList<>();

        for (ThsStockFundFlowAdapter adapter : stockList) {
            adapter.setType(symbol);
            ThsStockFundFlow thsStockFundFlow = trfService.transfThsStockFundFlow(adapter);
            returnList.add(thsStockFundFlow);
        }

        return returnList;
    }

    /**
     * 板块资金流排名
     * 接口: stock_sector_fund_flow_rank
     * <p>
     * 目标地址: https://data.eastmoney.com/bkzj/hy.html
     * <p>
     * 描述: 东方财富网-数据中心-资金流向-板块资金流-排名
     * <p>
     * 限量: 单次获取指定板块的指定期限的资金流排名数据
     * indicator="今日"; choice of {"今日", "5日", "10日"}
     * sector_type="行业资金流"; choice of {"行业资金流", "概念资金流", "地域资金流"}
     */
    @Override
    public List<ThsBoardFundFlow> getThsBoardFundFlow(String indicator, String sectorType) {
        if (StringUtils.isBlank(indicator)) {
            indicator = "今日";
        }
        if (StringUtils.isBlank(sectorType)) {
            sectorType = "行业资金流";
        }
        String url = BasicUrl + "stock_sector_fund_flow_rank?indicator=" + indicator + "&sector_type=" + sectorType;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<ThsBoardFundFlow> returnList = new ArrayList<>();
        switch (indicator) {
            case "今日": {
                List<ThsBoardFundFlowAdapter> stockList = new ArrayList<>();

                try {
                    stockList = objectMapper.readValue(s, new TypeReference<List<ThsBoardFundFlowAdapter>>() {
                    });
                } catch (JsonProcessingException e) {
                    log.error("getStockBoardIndustryConsEm error", e);
                    throw new RuntimeException(e);
                }

                if (CollectionUtils.isEmpty(stockList)) {
                    return new ArrayList<>();
                }
                for (ThsBoardFundFlowAdapter adapter : stockList) {
                    adapter.setType(indicator);
                    adapter.setSectorType(sectorType);
                    ThsBoardFundFlow thsStockFundFlow = trfService.transfThsBoardFundFlow(adapter);
                    returnList.add(thsStockFundFlow);
                }
                break;
            }
            case "5日": {
                List<ThsBoardFundFlowAdapter5> stockList = new ArrayList<>();

                try {
                    stockList = objectMapper.readValue(s, new TypeReference<List<ThsBoardFundFlowAdapter5>>() {
                    });
                } catch (JsonProcessingException e) {
                    log.error("getStockBoardIndustryConsEm error", e);
                    throw new RuntimeException(e);
                }

                if (CollectionUtils.isEmpty(stockList)) {
                    return new ArrayList<>();
                }

                for (ThsBoardFundFlowAdapter5 adapter : stockList) {
                    adapter.setType(indicator);
                    adapter.setSectorType(sectorType);
                    ThsBoardFundFlow thsStockFundFlow = trfService.transfThsBoardFundFlow5(adapter);
                    returnList.add(thsStockFundFlow);
                }
                break;
            }
            case "10日": {
                List<ThsBoardFundFlowAdapter10> stockList = new ArrayList<>();

                try {
                    stockList = objectMapper.readValue(s, new TypeReference<List<ThsBoardFundFlowAdapter10>>() {
                    });
                } catch (JsonProcessingException e) {
                    log.error("getStockBoardIndustryConsEm error", e);
                    throw new RuntimeException(e);
                }

                if (CollectionUtils.isEmpty(stockList)) {
                    return new ArrayList<>();
                }


                for (ThsBoardFundFlowAdapter10 adapter : stockList) {
                    adapter.setType(indicator);
                    adapter.setSectorType(sectorType);
                    ThsBoardFundFlow thsStockFundFlow = trfService.transfThsBoardFundFlow10(adapter);
                    returnList.add(thsStockFundFlow);
                }
                break;
            }
        }
        return returnList;
    }

    /**
     * 基金行情
     * ETF基金实时行情-东财
     * 接口: fund_etf_spot_em
     * <p>
     * 目标地址: https://quote.eastmoney.com/center/gridlist.html#fund_etf
     * <p>
     * 描述: 东方财富-ETF 实时行情
     * <p>
     * 限量: 单次返回所有数据
     *
     * @return
     */
    @Override
    public List<FundEtfSpotEmAdapter> fundEtfSpotEm() {
        String url = BasicUrl + "fund_etf_spot_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<FundEtfSpotEmAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<FundEtfSpotEmAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("fundEtfSpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
//        List<ThsStockFundFlow> returnList = new ArrayList<>();
//
//        for (ThsStockFundFlowAdapter adapter : stockList) {
//            adapter.setType(symbol);
//            ThsStockFundFlow thsStockFundFlow = trfService.transfThsStockFundFlow(adapter);
//            returnList.add(thsStockFundFlow);
//        }

        return stockList;
    }

    /**
     * ETF基金实时行情-同花顺   貌似不实时
     * 接口: fund_etf_spot_ths
     * <p>
     * 目标地址: https://fund.10jqka.com.cn/datacenter/jz/kfs/etf/
     * <p>
     * 描述: 同花顺理财-基金数据-每日净值-ETF-实时行情
     * <p>
     * 限量: 单次返回指定 date 的所有数据
     *
     * @return
     */
    @Override
    public List<FundEtfSpotThsAdapter> fundEtfSpotThs(String date) {
        String url = BasicUrl + "fund_etf_spot_ths";
        if (StringUtils.isNotBlank(date)) {
            url = url + "?date=" + date;
        }

        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<FundEtfSpotThsAdapter> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<FundEtfSpotThsAdapter>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("fundEtfSpotThs error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
//        List<ThsStockFundFlow> returnList = new ArrayList<>();

//        for (ThsStockFundFlowAdapter adapter : stockList) {
//            adapter.setType(symbol);
//            ThsStockFundFlow thsStockFundFlow = trfService.transfThsStockFundFlow(adapter);
//            returnList.add(thsStockFundFlow);
//        }

        return stockList;
    }

    /**
     * 开放式基金-实时数据
     * 接口: fund_open_fund_daily_em
     * <p>
     * 目标地址: http://fund.eastmoney.com/fund.html#os_0;isall_0;ft_;pt_1
     * <p>
     * 描述: 东方财富网-天天基金网-基金数据, 此接口在每个交易日 16:00-23:00 更新当日的最新开放式基金净值数据
     * <p>
     * 限量: 单次返回当前时刻所有历史数据
     *
     * @return
     */
    @Override
    public List<FundOpenFundDailyEmAdapter> fundOpenFundDailyEm() {
        String url = BasicUrl + "fund_open_fund_daily_em";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<FundOpenFundDailyEmAdapter> stockList = new ArrayList<>();

        try {
            // 关键修改：将JSON字符串转为List<Map>（适配数组格式）
            List<Map<String, Object>> fundMapList = objectMapper.readValue(
                    s, new TypeReference<List<Map<String, Object>>>() {
                    }
            );

            // 遍历每个基金对象
            for (Map<String, Object> fundMap : fundMapList) {
                FundOpenFundDailyEmAdapter fundData = new FundOpenFundDailyEmAdapter();

                // 1. 获取固定字段（包含新增的赎回状态、手续费）
                fundData.setFoundCode((String) fundMap.get("基金代码"));
                fundData.setFoundName((String) fundMap.get("基金简称"));
                fundData.setGrowthValue((String) fundMap.get("日增长值"));
                fundData.setGrowthRate((String) fundMap.get("日增长率"));
                fundData.setSubscriptionStatus((String) fundMap.get("申购状态"));
                fundData.setRedemptionStatus((String) fundMap.get("赎回状态")); // 新增
                fundData.setCommission((String) fundMap.get("手续费"));       // 新增
                int a = 0;
                // 2. 遍历Map，筛选含动态日期的净值字段（核心逻辑不变）
                for (Map.Entry<String, Object> entry : fundMap.entrySet()) {
                    String fieldName = entry.getKey();
                    Object fieldValue = entry.getValue();


                    // 空值判断：避免空指针异常
                    if (fieldValue == null) {
                        continue;
                    }

                    // 匹配规则：字段名包含"-单位净值" 或 "-累计净值"
                    if (fieldName.contains("-单位净值")) {
                        // 解析日期：从字段名中截取日期部分（如"2026-01-08"）
                        String dateStr = fieldName.split("-单位净值")[0];
                        // 解析净值：转为BigDecimal保证精度
                        BigDecimal nav = new BigDecimal((String) fieldValue);
                        if (a == 0) {
                            fundData.setCurrentNav(nav);
                        } else {
                            fundData.setPreviousNav(nav);
                        }

                    } else if (fieldName.contains("-累计净值")) {
                        String dateStr = fieldName.split("-累计净值")[0];
                        BigDecimal cumulativeNav = new BigDecimal((String) fieldValue);
                        if (a == 0) {
                            fundData.setCurrentCumulativeNav(cumulativeNav);
                            a = 1;
                        } else {
                            fundData.setPreviousCumulativeNav(cumulativeNav);
                        }


                    }
                }

                // 将解析后的单个基金数据加入列表
                stockList.add(fundData);
            }

        } catch (JsonProcessingException e) {
            log.error("fundEtfSpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
//        List<ThsStockFundFlow> returnList = new ArrayList<>();
//
//        for (ThsStockFundFlowAdapter adapter : stockList) {
//            adapter.setType(symbol);
//            ThsStockFundFlow thsStockFundFlow = trfService.transfThsStockFundFlow(adapter);
//            returnList.add(thsStockFundFlow);
//        }

        return stockList;
    }

    /**
     * 净值估算
     * 接口: fund_value_estimation_em
     * <p>
     * 目标地址: http://fund.eastmoney.com/fundguzhi.html
     * <p>
     * 描述: 东方财富网-数据中心-净值估算
     * <p>
     * 限量: 单次返回当前交易日指定 symbol 的所有数据
     * symbol='全部'; 默认返回所有数据; choice of {'全部', '股票型', '混合型', '债券型', '指数型', 'QDII', 'ETF联接', 'LOF', '场内交易基金'}
     */
    @Override
    public List<FundValueEstimationEmAdapter> fundValueEstimationEm(String symbol) {
        if (StringUtils.isBlank(symbol)) {
            symbol = "全部";
        }
        String url = BasicUrl + "fund_value_estimation_em?symbol=" + symbol;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<FundValueEstimationEmAdapter> stockList = new ArrayList<>();

        try {
            // 关键修改：将JSON字符串转为List<Map>（适配数组格式）
            List<Map<String, Object>> fundMapList = objectMapper.readValue(
                    s, new TypeReference<List<Map<String, Object>>>() {
                    }
            );

            // 遍历每个基金对象
            for (Map<String, Object> fundMap : fundMapList) {
                FundValueEstimationEmAdapter fundData = new FundValueEstimationEmAdapter();
                // 1. 获取固定字段（包含新增的赎回状态、手续费）
                fundData.setFoundCode((String) fundMap.get("基金代码"));
                fundData.setFoundName((String) fundMap.get("基金简称"));
                fundData.setEstimatedDeviation((String) fundMap.get("估算偏差"));
                // 2. 遍历Map，筛选含动态日期的净值字段（核心逻辑不变）
                for (Map.Entry<String, Object> entry : fundMap.entrySet()) {
                    String fieldName = entry.getKey();
                    String fieldValue = (String) entry.getValue();
                    // 空值判断：避免空指针异常
                    if (StringUtils.isBlank(fieldValue)) {
                        continue;
                    }
                    BigDecimal nav = new BigDecimal(fieldValue);

                    if (fieldName.contains("-估算数据-估算值")) {
                        // 解析净值：转为BigDecimal保证精度
                        fundData.setEstimatedValue(nav);
                    } else if (fieldName.contains("-估算数据-估算增长率")) {
                        fundData.setEstimatedGrowthRate(fieldValue);
                    } else if (fieldName.contains("-公布数据-单位净值")) {
                        fundData.setNetAssetValue(nav);
                    } else if (fieldName.contains("-公布数据-日增长率")) {
                        fundData.setDailyGrowthRate(fieldValue);
                    }else if (fieldName.contains("-单位净值")) {
                        fundData.setCurrentNav(nav);
                    }
                }

                // 将解析后的单个基金数据加入列表
                stockList.add(fundData);
            }

        } catch (JsonProcessingException e) {
            log.error("fundEtfSpotEm error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
//        List<ThsStockFundFlow> returnList = new ArrayList<>();
//
//        for (ThsStockFundFlowAdapter adapter : stockList) {
//            adapter.setType(symbol);
//            ThsStockFundFlow thsStockFundFlow = trfService.transfThsStockFundFlow(adapter);
//            returnList.add(thsStockFundFlow);
//        }

        return stockList;
    }

    /**
     * 外盘-品种代码表
     * 接口: futures_hq_subscribe_exchange_symbol
     *
     * 目标地址: https://finance.sina.com.cn/money/future/hf.html
     *
     * 描述: 新浪财经-外盘商品期货品种代码表数据
     *
     * 限量: 单次返回当前交易日的订阅的所有期货品种的品种代码表数据
     * @return
     */
    @Override
    public List<FuturesFqSubscribeExchangeSymbol> futuresHqSubscribeExchangeSymbol() {
        String url = BasicUrl + "futures_hq_subscribe_exchange_symbol";
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<FuturesFqSubscribeExchangeSymbol> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<FuturesFqSubscribeExchangeSymbol>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("futuresHqSubscribeExchangeSymbol error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        return stockList;
    }

    /**
     * 外盘-实时行情数据
     * 接口: futures_foreign_commodity_realtime
     *
     * 目标地址: https://finance.sina.com.cn/money/future/hf.html
     *
     * 描述: 新浪财经-外盘商品期货数据
     *
     * 限量: 单次返回当前交易日的订阅的所有期货品种的数据
     * @param symbol 从上面一个接口获得，可以单个，也可以逗号拼接，像 XAU,XAG
     * @return
     */
    @Override
    public List<FuturesForeignCommodityRealtime> futuresForeignCommodityRealtime(String symbol) {
        if (StringUtils.isBlank(symbol)){
            return new ArrayList<>();
        }
        String url = BasicUrl + "futures_foreign_commodity_realtime?symbol=" +symbol;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<FuturesForeignCommodityRealtime> stockList = new ArrayList<>();

        try {
            stockList = objectMapper.readValue(s, new TypeReference<List<FuturesForeignCommodityRealtime>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("futuresForeignCommodityRealtime error", e);
            throw new RuntimeException(e);
        }

        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        return stockList;
    }

}
