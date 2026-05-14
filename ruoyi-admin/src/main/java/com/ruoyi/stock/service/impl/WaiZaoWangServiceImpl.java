package com.ruoyi.stock.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.domain.Adapter.StockZtPoolEmAdapter;
import com.ruoyi.stock.service.TransfService;
import com.ruoyi.stock.service.WaiZaoWangService;
import com.ruoyi.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WaiZaoWangServiceImpl implements WaiZaoWangService {
    @Resource(name = "customHttpClient")
    private HttpClient httpClient;

    @Autowired
    private TransfService trfService;
/**
 * 获取涨停板行情
 *
 * @param startDate 开始日期，yyyy-MM-dd格式，例如：2020-01-01
 * @param endDate 	结束日期，yyyy-MM-dd格式，例如：2050-01-01
 * @param fields 数据字段，多个字段之间使用逗号分隔，若获取所有字段，则取值为all。
 * @param export 	数据导出类型，取值范围：0|Txt字符串；1|Json字符串；2|Txt文件；3|Json文件；4|Csv文件；5|DataFrame格式
 * @param token 	令牌，登录后可获取
 *                  单次返回的数据不能超过2万
 * @return
 */
    @Override
    public List<StockZtPoolEm> getStockZtPoolEm(String startDate, String endDate, String fields, int export, String token) {


        String url = "http://api.waizaowang.com/doc/getPoolZT?startDate=" + startDate+"&endDate="+endDate+"&fields="+fields+"&export="+export+"&token="+token;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<CzStockZt> stockList = new ArrayList<>();

        try {
            CzStockZtRes czStockZtRes = objectMapper.readValue(s, new TypeReference<CzStockZtRes>() {
            });
            if (CollectionUtils.isNotEmpty(czStockZtRes.getData())){
                stockList.addAll(czStockZtRes.getData());
            }
        } catch (JsonProcessingException e) {
            log.error("getStockZtPoolEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZtPoolEm> returnList = new ArrayList<>();
        for (CzStockZt adapter : stockList) {
            StockZtPoolEm stockZtPoolEm = trfService.transfCzStockZt(adapter);
            returnList.add(stockZtPoolEm);
        }

        return returnList;
    }

    @Override
    public List<StockZtPoolStrongEm> getStockZtPoolStrongEm(String startDate, String endDate, String fields, int export, String token) {
        String url = "http://api.waizaowang.com/doc/getPoolQS?startDate=" + startDate+"&endDate="+endDate+"&fields="+fields+"&export="+export+"&token="+token;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<CzStockQs> stockList = new ArrayList<>();

        try {
            CzStockQsRes czStockQsRes = objectMapper.readValue(s, new TypeReference<CzStockQsRes>() {
            });
            if (CollectionUtils.isNotEmpty(czStockQsRes.getData())){
                stockList.addAll(czStockQsRes.getData());
            }
        } catch (JsonProcessingException e) {
            log.error("getStockZtPoolEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZtPoolStrongEm> returnList = new ArrayList<>();
        for (CzStockQs adapter : stockList) {
            StockZtPoolStrongEm stockZtPoolStrongEm = trfService.transfCzStockQs(adapter);
            returnList.add(stockZtPoolStrongEm);
        }

        return returnList;
    }

    @Override
    public List<StockZtPoolZbgcEm> getStockZtPoolZbgcEm(String startDate, String endDate, String fields, int export, String token) {
        String url = "http://api.waizaowang.com/doc/getPoolZB?startDate=" + startDate+"&endDate="+endDate+"&fields="+fields+"&export="+export+"&token="+token;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<CzStockZb> stockList = new ArrayList<>();

        try {
            CzStockZbRes czStockQsRes = objectMapper.readValue(s, new TypeReference<CzStockZbRes>() {
            });
            if (CollectionUtils.isNotEmpty(czStockQsRes.getData())){
                stockList.addAll(czStockQsRes.getData());
            }
        } catch (JsonProcessingException e) {
            log.error("getStockZtPoolEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZtPoolZbgcEm> returnList = new ArrayList<>();
        for (CzStockZb adapter : stockList) {
            StockZtPoolZbgcEm stockZtPoolStrongEm = trfService.transfCzStockZb(adapter);
            returnList.add(stockZtPoolStrongEm);
        }

        return returnList;
    }

    @Override
    public List<StockZtPoolDtgcEm> getStockZtPoolDtgcEm(String startDate, String endDate, String fields, int export, String token) {
        String url = "http://api.waizaowang.com/doc/getPoolDT?startDate=" + startDate+"&endDate="+endDate+"&fields="+fields+"&export="+export+"&token="+token;
        String s = httpClient.get(url);
        if (null == s) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<CzStockDt> stockList = new ArrayList<>();

        try {
            CzStockDtRes czStockQsRes = objectMapper.readValue(s, new TypeReference<CzStockDtRes>() {
            });
            if (CollectionUtils.isNotEmpty(czStockQsRes.getData())){
                stockList.addAll(czStockQsRes.getData());
            }
        } catch (JsonProcessingException e) {
            log.error("getStockZtPoolEm error", e);
            throw new RuntimeException(e);
        }
        if (CollectionUtils.isEmpty(stockList)) {
            return new ArrayList<>();
        }
        List<StockZtPoolDtgcEm> returnList = new ArrayList<>();
        for (CzStockDt adapter : stockList) {
            StockZtPoolDtgcEm stockZtPoolStrongEm = trfService.transfCzStockDt(adapter);
            returnList.add(stockZtPoolStrongEm);
        }

        return returnList;
    }
}
