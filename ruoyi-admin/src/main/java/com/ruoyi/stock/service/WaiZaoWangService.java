package com.ruoyi.stock.service;

import com.ruoyi.stock.domain.StockZtPoolDtgcEm;
import com.ruoyi.stock.domain.StockZtPoolEm;
import com.ruoyi.stock.domain.StockZtPoolStrongEm;
import com.ruoyi.stock.domain.StockZtPoolZbgcEm;

import java.util.List;

public interface WaiZaoWangService {
    List<StockZtPoolEm> getStockZtPoolEm(String startDate, String endDate,String fields,int export,String token);

    List<StockZtPoolStrongEm> getStockZtPoolStrongEm(String startDate, String endDate, String fields, int export, String token);

    List<StockZtPoolZbgcEm> getStockZtPoolZbgcEm(String startDate, String endDate, String fields, int export, String token);


    List<StockZtPoolDtgcEm> getStockZtPoolDtgcEm(String startDate, String endDate, String fields, int export, String token);


}
