package com.ruoyi.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.enums.BoardTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.mapper.BasicStockMapper;
import com.ruoyi.stock.mapper.StockBoardMapper;
import com.ruoyi.stock.mapper.StockZhAHistMapper;
import com.ruoyi.stock.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InitStockServiceImpl implements InitStockService {


    @Autowired
    private AKShareService akShareService;

    @Autowired
    private IStockZtPoolEmService iStockZtPoolEmService;

    @Autowired
    private IStockBoardService stockBoardConceptNameEmService;


    @Autowired
    private StockBoardMapper stockBoardMapper;

    @Autowired
    private IStockBoardConsEmService iStockBoardConsEmService;

    @Autowired
    private TransfService trfService;


    @Autowired
    private IStockBoardConsEmHisService iStockBoardConsEmHisService;

    @Autowired
    private IBasicStockService iBasicStockService;

    @Autowired
    private BasicStockMapper basicStockMapper;

    @Autowired
    private IStockZhAHistService iStockZhAHistService;
    @Autowired
    private StockZhAHistMapper stockZhAHistMapper;
    @Autowired
    private IStockBoardDataHistEmService iStockBoardDataHistEmService;

    /**
     * 初始化涨停板行情对象 stock_zt_pool_em
     */
    @Override
    public void initStockZtPoolEm(String startTime, String endTime) {
        //获取指定时间范围的数据，落库
        List<String> list = DateUtils.getDaysBetweenDates(startTime, endTime);
        List<StockZtPoolEm> insertList = new ArrayList<>();
        int i = 0;
        for (String day : list) {
            i++;
            log.info("开始处理数据：{}", day);
            List<StockZtPoolEm> stockZtPoolEmList = akShareService.getStockZtPoolEm(day);
            insertList.addAll(stockZtPoolEmList);
            if (i % 10 == 0) {
                iStockZtPoolEmService.saveBatch(insertList);
                insertList.clear();
            }
        }

        if (CollectionUtils.isNotEmpty(insertList)) {
            iStockZtPoolEmService.saveBatch(insertList);
        }
    }

    /**
     * 全量同步成分股表stock_board_cons_em
     */
    @Override
    public void initStockBoardConsEm() {
        //查询所有的概念板块
        List<StockBoard> conceptList = stockBoardMapper.selectList(new QueryWrapper<>());
        for (StockBoard stockBoard : conceptList) {
            List<StockBoardConsEm> stockBoardConsEmList = akShareService.getStockBoardConceptConsEm(stockBoard.getBoardCode(), stockBoard.getBoardName());
            List<StockBoardConsEmHis> insertList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(stockBoardConsEmList)) {
                //保存到当前表
                iStockBoardConsEmService.saveBatch(stockBoardConsEmList);
                //循环转换
                for (StockBoardConsEm stockBoardConsEm : stockBoardConsEmList) {
                    StockBoardConsEmHis stockBoardConsEmHis = trfService.transfStockBoardConsEmHis(stockBoardConsEm);
                    insertList.add(stockBoardConsEmHis);
                }
                //保存到历史表
                iStockBoardConsEmHisService.saveBatch(insertList);
            }

        }

        log.info("初始化stock_board_cons_em完成");
    }

    /**
     * 获取A股当日数据，手动同步
     */
    @Override
    public void initStockZhAHist(Long start, Long end) {
        LambdaQueryWrapper<BasicStock> queryWrapper = new LambdaQueryWrapper<>();
        // 设置条件：id >= A 并且 id < B
        queryWrapper.ge(BasicStock::getBasicStockId, start)  // ge 表示 greater than or equal（>=）
                .lt(BasicStock::getBasicStockId, end); // lt 表示 less than（<）
        // 执行查询
        List<BasicStock> resultList = basicStockMapper.selectList(queryWrapper);
        for (BasicStock basicStock : resultList) {
            LambdaQueryWrapper<StockZhAHist> queryW = new LambdaQueryWrapper<>();
            queryW.eq(StockZhAHist::getStockCode, basicStock.getStockCode());
            Integer count = stockZhAHistMapper.selectCount(queryW);
            if (count != 0) {
                continue;
            }
            log.info("开始处理数据：{}", basicStock.getStockCode());
            List<StockZhAHist> stockZhAHistList = akShareService.getStockZhAHist(basicStock.getStockCode(), "daily", null, null, null, null, basicStock.getStockName(), basicStock.getStockMarketType());
            iStockZhAHistService.saveBatch(stockZhAHistList);
            try {
                // 暂停1秒钟（1000毫秒）
                long l = System.currentTimeMillis();
                Thread.sleep(3000);
                long l1 = System.currentTimeMillis();
                log.info("休眠时间：{}", l1 - l);
            } catch (InterruptedException e) {
                // 捕获中断异常（线程在休眠时被中断会抛出此异常）
                e.printStackTrace();
                // 可以根据需要处理中断，例如恢复中断状态
                Thread.currentThread().interrupt();
            }
        }
        log.info(start + "----" + end + "初始化stock_zh_a_hist完成");
    }

    /**
     * 初始化股票基础信息表
     */
    @Override
    public void initBasicStock() {

        LambdaQueryWrapper<BasicStock> queryWrapper = new LambdaQueryWrapper<>();
        // 设置条件：id >= A 并且 id < B
        queryWrapper.ge(BasicStock::getBasicStockId, 0)  // ge 表示 greater than or equal（>=）
                .lt(BasicStock::getBasicStockId, 10143); // lt 表示 less than（<）
        // 执行查询
        List<BasicStock> resultList = basicStockMapper.selectList(queryWrapper);
        Map<String, Integer> map = resultList.stream()
                .collect(Collectors.toMap(BasicStock::getStockCode, a -> 0));

        List<StockZhAHist> insertList = new ArrayList<>();
        //同步沪市A股
        List<BasicStock> shBasicStocks = akShareService.getStockShASpotEm();

        //同步深市A股
        List<BasicStock> szBasicStocks = akShareService.getStockSzASpotEm();


        //同步北交所A股
        List<BasicStock> bjBasicStocks = akShareService.getStockBjASpotEm();


        //同步新股
        List<BasicStock> newBasicStocks = akShareService.getStockNewASpotEm();


        //同步创业板
        List<BasicStock> cyBasicStocks = akShareService.getStockCyASpotEm();


        //同步科创板
        List<BasicStock> kcBasicStocks = akShareService.getStockKcASpotEm();

        kcBasicStocks.addAll(shBasicStocks);
        kcBasicStocks.addAll(szBasicStocks);
        kcBasicStocks.addAll(bjBasicStocks);
        kcBasicStocks.addAll(newBasicStocks);
        kcBasicStocks.addAll(cyBasicStocks);

        for (BasicStock kcBasicStock : kcBasicStocks) {
            if (null != map.get(kcBasicStock.getStockCode()) && map.get(kcBasicStock.getStockCode()) == 0) {
                StockZhAHist stockZhAHist = trfService.transfBasicStockToStockZhAHist(kcBasicStock);
                insertList.add(stockZhAHist);
                map.put(kcBasicStock.getStockCode(), 1);
            }
        }
        iStockZhAHistService.saveBatch(insertList);
        insertList.clear();

        log.info("当日全量股票数据同步完成");


    }

    /**
     * 初始化板块历史数据
     */
    @Override
    public void initStockBoardDataHistEm() {
        //查询所有的概念板块数据

        LambdaQueryWrapper<StockBoard> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq( StockBoard::getBoardType, BoardTypeEnum.CONCEPT_BOARD.getInfo());
//        queryWrapper.ge(StockBoard::getBoardId, 285);
//        List<StockBoard> conceptList = stockBoardMapper.selectList(queryWrapper);
//        for (StockBoard stockBoard : conceptList) {
//            log.info("开始处理概念板块数据：{},{}", stockBoard.getBoardName(), "----------"+stockBoard.getBoardId());
//            List<StockBoardDataHistEm> stockBoardDataHistEmList = akShareService.getStockBoardConceptHistEm(stockBoard.getBoardCode(), stockBoard.getBoardName(), "daily", "20000101", "20251231", null);
//            iStockBoardDataHistEmService.saveBatch(stockBoardDataHistEmList);
//            try {
//                // 暂停1秒钟（1000毫秒）
//                long l = System.currentTimeMillis();
//                Thread.sleep(3000);
//                long l1 = System.currentTimeMillis();
//                log.info("休眠时间：{}", l1 - l);
//            } catch (InterruptedException e) {
//                // 捕获中断异常（线程在休眠时被中断会抛出此异常）
//                e.printStackTrace();
//                // 可以根据需要处理中断，例如恢复中断状态
//                Thread.currentThread().interrupt();
//            }
//        }
        //  查询所有的行业板块数据
        queryWrapper.eq( StockBoard::getBoardType, BoardTypeEnum.INDUSTRY_BOARD.getInfo());
        List<StockBoard> industryList = stockBoardMapper.selectList(queryWrapper);
        for (StockBoard stockBoard : industryList) {
            log.info("开始处理行业板块数据：{},{}", stockBoard.getBoardName(), "----------"+stockBoard.getBoardId());
            List<StockBoardDataHistEm> stockBoardDataHistEmList = akShareService.getstockBoardIndustryHistEm(stockBoard.getBoardCode(), stockBoard.getBoardName(), "日k", "20000101", "20251231", null);
            iStockBoardDataHistEmService.saveBatch(stockBoardDataHistEmList);
            try {
                // 暂停1秒钟（1000毫秒）
                long l = System.currentTimeMillis();
                Thread.sleep(3000);
                long l1 = System.currentTimeMillis();
                log.info("休眠时间：{}", l1 - l);
            } catch (InterruptedException e) {
                // 捕获中断异常（线程在休眠时被中断会抛出此异常）
                e.printStackTrace();
                // 可以根据需要处理中断，例如恢复中断状态
                Thread.currentThread().interrupt();
            }
        }
        log.info("当日全量板块数据同步完成");
    }
}
