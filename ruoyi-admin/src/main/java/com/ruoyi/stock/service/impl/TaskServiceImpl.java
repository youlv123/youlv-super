package com.ruoyi.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.*;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.mapper.*;
import com.ruoyi.stock.service.*;
import com.ruoyi.system.domain.FinancialRecord;
import com.ruoyi.system.domain.PushWxMessageRecord;
import com.ruoyi.system.mapper.FinancialRecordMapper;
import com.ruoyi.util.WxHttpMultiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private AKShareService akShareService;

    @Autowired
    private IStockZtPoolStrongEmService iStockZtPoolStrongEmService;

    @Autowired
    private IStockZtPoolEmService iStockZtPoolEmService;

    @Autowired
    private IStockZtPoolZbgcEmService iStockZbgcEmService;

    @Autowired
    private IStockZtPoolDtgcEmService iStockZtPoolDtgcEmService;


    @Autowired
    private FinancialRecordMapper financialRecordMapper;

    @Autowired
    private IStockBoardService iStockBoardService;


    @Autowired
    private StockBoardMapper stockBoardMapper;

    @Autowired
    private StockBoardHisMapper stockBoardHisMapper;

    @Autowired
    private TransfService trfService;

    @Autowired
    private IStockBoardHisService iStockBoardHisService;


    @Autowired
    private IStockBoardConsEmService iStockBoardConsEmService;


    @Autowired
    private IStockBoardConsEmHisService iStockBoardConsEmHisService;

    @Autowired
    private StockBoardConsEmMapper stockBoardConsEmMapper;

    @Autowired
    private StockBoardConsEmHisMapper stockBoardConsEmHisMapper;

    @Autowired
    private WxHttpMultiUtils wxHttpMultiUtils;

    @Autowired
    private IBasicStockService iBasicStockService;


    @Autowired
    private IBasicStockHisService iBasicStockHisService;


    @Autowired
    private BasicStockMapper basicStockMapper;

    @Autowired
    private BasicStockHisMapper basicStockHisMapper;

    @Autowired
    private StockTradeDateMapper stockTradeDateMapper;


    @Autowired
    private IStockTradeDateService iStockTradeDateService;

    @Autowired
    private IStockZhAHistService iStockZhAHistService;

    @Autowired
    private IThsStockFundFlowService iThsFundFlowService;

    @Autowired
    private ThsStockFundFlowMapper thsStockFundFlowMapper;

    @Autowired
    private ThsBoardFundFlowMapper thsBoardFundFlowMapper;

    @Autowired
    private IThsBoardFundFlowService iThsBoardFundFlowService;

    @Autowired
    private ITaskLogService iTaskLogService;

    @Autowired

    private TaskLogMapper taskLogMapper;

    /**
     * 获取当日涨停股票数据
     */
    @Async
    public void getStockZtPoolEm() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            //1、得到当日的日期
            List<StockZtPoolEm> list = akShareService.getStockZtPoolEm(bizDate);
            iStockZtPoolEmService.saveBatch(list);
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("获取当日涨停股票数据", Constants.SUCCESS, null, list.size(), DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("getStockZtPoolEm error", e);
            TaskLog taskLog = new TaskLog("获取当日涨停股票数据", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 获取当日强势股池数据
     */
    @Async
    public void getStockZtPoolStrongEm() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            List<StockZtPoolStrongEm> list = akShareService.getStockZtPoolStrongEm(bizDate);
            iStockZtPoolStrongEmService.saveBatch(list);
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("获取当日强势股池数据", Constants.SUCCESS, null, list.size(), DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("getStockZtPoolStrongEm error", e);
            TaskLog taskLog = new TaskLog("获取当日强势股池数据", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 获取当日炸板股池数据
     */
    @Async
    public void getStockZtPoolZbgcEm() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            List<StockZtPoolZbgcEm> list = akShareService.getStockZtPoolZbgcEm(bizDate);
            iStockZbgcEmService.saveBatch(list);
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("获取当日炸板股池数据", Constants.SUCCESS, null, list.size(), DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("getStockZtPoolZbgcEm error", e);
            TaskLog taskLog = new TaskLog("获取当日炸板股池数据", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 获取当日跌停股池数据
     */
    @Async
    public void getStockZtPoolDtgcEm() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            List<StockZtPoolDtgcEm> list = akShareService.getStockZtPoolDtgcEm(bizDate);
            iStockZtPoolDtgcEmService.saveBatch(list);
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("获取当日跌停股池数据", Constants.SUCCESS, null, list.size(), DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("getStockZtPoolDtgcEm error", e);
            TaskLog taskLog = new TaskLog("获取当日跌停股池数据", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }


    /**
     * 定时同步理财的持有时间和状态
     */
    @Async
    public void syncFinancialTime() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        int count = 0;
        try {
            Long maxId = null;
            List<FinancialRecord> list = this.findFinancialNextBatch(maxId);
            log.info("found list [{}]", list.size());

            maxId = Long.MIN_VALUE;
            // 获取当前用户ID
            while (CollectionUtils.isNotEmpty(list)) {
                count = count + list.size();
                for (FinancialRecord dto : list) {
                    Date purchaseTime = dto.getPurchaseTime();
                    long l = DateUtils.calculateDaysBetween(purchaseTime);
                    dto.setHoldingTime(String.valueOf(l));
                    //如果设置了过期时间
                    if (null != dto.getExpirationDate()) {
                        //比较当前时间是否大于过期时间
                        if (dto.getExpirationDate().getTime() < System.currentTimeMillis()) {
                            //到期还没赎回改变状态
                            dto.setFinancialStatus(FinancialStatusEnum.EXPIRATION_NOT_REDEEMED.getCode());
                        }
                    }
                    financialRecordMapper.updateFinancialRecord(dto);
                    Long id = dto.getFinancialRecordId();
                    if (id > maxId) {
                        // find max id
                        maxId = id;
                    }
                }
                list = this.findFinancialNextBatch(maxId);
                log.info("found list [{}]", list.size());

            }
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("定时同步理财的持有时间和状态", Constants.SUCCESS, null, count, DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("syncFinancialTime error", e);
            TaskLog taskLog = new TaskLog("定时同步理财的持有时间和状态", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }

    }

    /**
     * 每周一9点同步板块信息
     */
    @Async
    public void syncStockBoard() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            //查询历史数据.保存历史表
            List<StockBoard> hisList = stockBoardMapper.selectList(new QueryWrapper<>());
            List<StockBoardHis> insertList = new ArrayList<>();
            for (StockBoard stockBoard : hisList) {
                StockBoardHis stockBoardHis = trfService.transfStockBoardToStockBoardHis(stockBoard);
                insertList.add(stockBoardHis);
            }
            //存入历史数据表
            if (CollectionUtils.isNotEmpty(insertList)) {
                iStockBoardHisService.saveBatch(insertList);
            }

            //清空该表
            stockBoardMapper.truncateStockBoard();
            //查询概念板块
            List<StockBoard> list = akShareService.getStockBoardConceptNameEm();
            iStockBoardService.saveBatch(list);
            //查询行业板块
            List<StockBoard> stockBoardList = akShareService.getStockBoardIndustryNameEm();
            iStockBoardService.saveBatch(stockBoardList);
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("每周一9点同步板块信息", Constants.SUCCESS, null, hisList.size(), DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("syncStockBoard error", e);
            TaskLog taskLog = new TaskLog("每周一9点同步板块信息", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 每周一10点同步板块内股票信息
     */

    @Async
    public void syncStockBoardConsEm() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            //查询所有的概念板块
            LambdaQueryWrapper<StockBoard> queryWrapper1 = new LambdaQueryWrapper<>();
//        queryWrapper1.ge(StockBoard::getBoardId, 285);
            List<StockBoard> conceptList = stockBoardMapper.selectList(queryWrapper1);
            for (StockBoard stockBoard : conceptList) {
                //查询该板块下有哪些股票
                List<StockBoardConsEm> stockBoardConsEmList = akShareService.getStockBoardConceptConsEm(stockBoard.getBoardCode(), stockBoard.getBoardName());
                if (CollectionUtils.isNotEmpty(stockBoardConsEmList)) {
                    //查询当前表该板块下保存了哪些股票
                    LambdaQueryWrapper<StockBoardConsEm> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(StockBoardConsEm::getBoardCode, stockBoard.getBoardCode());
                    Integer count = stockBoardConsEmMapper.selectCount(queryWrapper);
                    //如果数量一致，则跳过
                    if (count == stockBoardConsEmList.size()) {
                        continue;
                    }
                    //数量不一致，则删除，再新增
                    //删除这些数据，再保存
                    stockBoardConsEmMapper.delete(queryWrapper);
                    //保存到当前表
                    iStockBoardConsEmService.saveBatch(stockBoardConsEmList);
                    //循环转换
                    List<StockBoardConsEmHis> insertList = new ArrayList<>();
                    for (StockBoardConsEm stockBoardConsEm : stockBoardConsEmList) {
                        StockBoardConsEmHis stockBoardConsEmHis = trfService.transfStockBoardConsEmHis(stockBoardConsEm);
                        insertList.add(stockBoardConsEmHis);
                    }
                    //删除历史表
                    LambdaQueryWrapper<StockBoardConsEmHis> hisQueryWrapper = new LambdaQueryWrapper<>();
                    hisQueryWrapper.eq(StockBoardConsEmHis::getBoardCode, stockBoard.getBoardCode());
                    stockBoardConsEmHisMapper.delete(hisQueryWrapper);
                    //保存历史表
                    iStockBoardConsEmHisService.saveBatch(insertList);
                }
                stop(3000);
            }
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("每周一10点同步板块内股票信息", Constants.SUCCESS, null, conceptList.size(), DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("syncStockBoardConsEm error", e);
            TaskLog taskLog = new TaskLog("每周一10点同步板块内股票信息", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 过滤每天涨势不错的板块和股票发送到手机
     * 每日9点26分同步当天前十板块数据
     */
    @Async
    public void sendStrongStock() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            //查询概念板块
            List<StockBoard> conceptList = akShareService.getStockBoardConceptNameEm();
            //查询行业板块
            List<StockBoard> industryList = akShareService.getStockBoardIndustryNameEm();

            //取前十个
            List<StockBoard> sortedConceptList = conceptList.stream()
                    // 显式指定s为StockBoard类型
                    .filter((StockBoard s) -> s.getChangePercent() != null)
                    .sorted(Comparator.comparingDouble(
                            (StockBoard s) -> Double.parseDouble(s.getChangePercent())
                    ).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
//        List<StockBoard> sortedConceptList = conceptList.stream()
//                .sorted((s1, s2) -> {
//                    double percent1 = Double.parseDouble(s1.getChangePercent());
//                    double percent2 = Double.parseDouble(s2.getChangePercent());
//                    return Double.compare(percent2, percent1); // 降序
//                })
//                .limit(10)
//                .collect(Collectors.toList());
            //取五个

            List<StockBoard> sortedIndustryList = industryList.stream()
                    // 显式指定s为StockBoard类型
                    .filter((StockBoard s) -> s.getChangePercent() != null)
                    .sorted(Comparator.comparingDouble(
                            (StockBoard s) -> Double.parseDouble(s.getChangePercent())
                    ).reversed())
                    .limit(5)
                    .collect(Collectors.toList());
//        List<StockBoard> sortedIndustryList = industryList.stream()
//                .sorted((s1, s2) -> {
//                    double percent1 = Double.parseDouble(s1.getChangePercent());
//                    double percent2 = Double.parseDouble(s2.getChangePercent());
//                    return Double.compare(percent2, percent1); // 降序
//                })
//                .limit(5)
//                .collect(Collectors.toList());
            StringBuilder conceptStringBuilder = new StringBuilder();
            conceptStringBuilder.append("【概念板块】\n");
            for (StockBoard stockBoard : sortedConceptList) {
                conceptStringBuilder.append(stockBoard.getBoardName()).append(":").append("涨跌幅").append(stockBoard.getChangePercent()).append("，");
                //上涨家数
                conceptStringBuilder.append("上涨家数:").append(stockBoard.getRiseNum()).append("，");
                //下跌家数
                conceptStringBuilder.append("下跌家数:").append(stockBoard.getFallNum()).append("\n");
                //查询该板块下有哪些股票
                List<StockBoardConsEm> stockBoardConsEmList = akShareService.getStockBoardConceptConsEm(stockBoard.getBoardCode(), stockBoard.getBoardName());
//            List<StockBoardConsEm> conceptstockBoardConsEmList = stockBoardConsEmList.stream()
//                    .sorted((s1, s2) -> {
//                        double percent1 = Double.parseDouble(s1.getChangePercent());
//                        double percent2 = Double.parseDouble(s2.getChangePercent());
//                        return Double.compare(percent2, percent1); // 降序
//                    })
//                    .limit(10)
//                    .collect(Collectors.toList());

                List<StockBoardConsEm> conceptstockBoardConsEmList = stockBoardConsEmList.stream()
                        // 显式指定s为StockBoard类型
                        .filter((StockBoardConsEm s) -> s.getChangePercent() != null)
                        .sorted(Comparator.comparingDouble(
                                (StockBoardConsEm s) -> Double.parseDouble(s.getChangePercent())
                        ).reversed())
                        .limit(10)
                        .collect(Collectors.toList());

                for (StockBoardConsEm stockBoardConsEm : conceptstockBoardConsEmList) {
                    conceptStringBuilder.append(stockBoardConsEm.getStockName()).append(stockBoardConsEm.getStockCode()).append(":").append("涨跌幅").append(stockBoardConsEm.getChangePercent()).append("\n");
                }
            }
            sendWxMessagesAndRecord(conceptStringBuilder.toString());

            StringBuilder industryStringBuilder = new StringBuilder();
            industryStringBuilder.append("【行业板块】\n");
            for (StockBoard stockBoard : sortedIndustryList) {
                industryStringBuilder.append(stockBoard.getBoardName()).append(":").append("涨跌幅").append(stockBoard.getChangePercent()).append("，");
                //上涨家数
                industryStringBuilder.append("上涨家数:").append(stockBoard.getRiseNum()).append("，");
                //下跌家数
                industryStringBuilder.append("下跌家数:").append(stockBoard.getFallNum()).append("\n");
                //查询该板块下有哪些股票
                List<StockBoardConsEm> stockBoardConsEmList = akShareService.getStockBoardIndustryConsEm(stockBoard.getBoardCode(), stockBoard.getBoardName());

                List<StockBoardConsEm> conceptstockBoardConsEmList = stockBoardConsEmList.stream()
                        // 显式指定s为StockBoard类型
                        .filter((StockBoardConsEm s) -> s.getChangePercent() != null)
                        .sorted(Comparator.comparingDouble(
                                (StockBoardConsEm s) -> Double.parseDouble(s.getChangePercent())
                        ).reversed())
                        .limit(10)
                        .collect(Collectors.toList());
//            List<StockBoardConsEm> conceptstockBoardConsEmList = stockBoardConsEmList.stream()
//                    .sorted((s1, s2) -> {
//                        double percent1 = Double.parseDouble(s1.getChangePercent());
//                        double percent2 = Double.parseDouble(s2.getChangePercent());
//                        return Double.compare(percent2, percent1); // 降序
//                    })
//                    .limit(10)
//                    .collect(Collectors.toList());

                for (StockBoardConsEm stockBoardConsEm : conceptstockBoardConsEmList) {
                    industryStringBuilder.append(stockBoardConsEm.getStockName()).append(stockBoardConsEm.getStockCode()).append(":").append("涨跌幅").append(stockBoardConsEm.getChangePercent()).append("\n");
                }

            }
            sendWxMessagesAndRecord(industryStringBuilder.toString());
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("每日9点26分同步当天前十板块数据", Constants.SUCCESS, null, conceptList.size() + industryList.size(), DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("sendStrongStock error", e);
            TaskLog taskLog = new TaskLog("每日9点26分同步当天前十板块数据", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 定时同步股票基础数据16点
     */
    @Async
    public void syncBasicStock() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            int size = 0;
            //初始化当前表和历史表
            Integer count = basicStockMapper.selectCount(null);
            Integer hisCount = basicStockHisMapper.selectCount(null);
            List<BasicStock> insertList = new ArrayList<>();
            List<BasicStockHis> insertHisList = new ArrayList<>();
            if (count == 0 && hisCount == 0) {
                List<BasicStock> shBasicStocks = akShareService.getStockShASpotEm();
                List<BasicStock> szBasicStocks = akShareService.getStockSzASpotEm();
                List<BasicStock> bjBasicStocks = akShareService.getStockBjASpotEm();
                List<BasicStock> newBasicStocks = akShareService.getStockNewASpotEm();
                List<BasicStock> cyBasicStocks = akShareService.getStockCyASpotEm();
                List<BasicStock> kcBasicStocks = akShareService.getStockKcASpotEm();
                insertList.addAll(shBasicStocks);
                insertList.addAll(szBasicStocks);
                insertList.addAll(bjBasicStocks);
                insertList.addAll(newBasicStocks);
                insertList.addAll(cyBasicStocks);
                insertList.addAll(kcBasicStocks);
                iBasicStockService.saveBatch(insertList);
                size += insertList.size();
                for (BasicStock basicStock : insertList) {
                    BasicStockHis basicStockHis = trfService.transfBasicStockHis(basicStock);
                    insertHisList.add(basicStockHis);
                }
                iBasicStockHisService.saveBatch(insertHisList);
                return;
            }

            List<BasicStock> shBasicStocks = akShareService.getStockShASpotEm();
            syncBasicStock(shBasicStocks, StockMarketTypeEnum.SH_A.getInfo());

            List<BasicStock> szBasicStocks = akShareService.getStockSzASpotEm();
            syncBasicStock(szBasicStocks, StockMarketTypeEnum.SZ_A.getInfo());

            List<BasicStock> bjBasicStocks = akShareService.getStockBjASpotEm();
            syncBasicStock(bjBasicStocks, StockMarketTypeEnum.BJ_A.getInfo());

            List<BasicStock> newBasicStocks = akShareService.getStockNewASpotEm();
            syncBasicStock(newBasicStocks, StockMarketTypeEnum.NEW_A.getInfo());

            List<BasicStock> cyBasicStocks = akShareService.getStockCyASpotEm();
            syncBasicStock(cyBasicStocks, StockMarketTypeEnum.CY_A.getInfo());

            List<BasicStock> kcBasicStocks = akShareService.getStockKcASpotEm();
            syncBasicStock(kcBasicStocks, StockMarketTypeEnum.KC_A.getInfo());
            size = shBasicStocks.size() + szBasicStocks.size() + bjBasicStocks.size() + newBasicStocks.size() + cyBasicStocks.size() + kcBasicStocks.size();
            log.info("同步股票基础数据完成");
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("定时同步股票基础数据16点", Constants.SUCCESS, null, size, DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("syncBasicStock error", e);
            TaskLog taskLog = new TaskLog("定时同步股票基础数据16点", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 每年1月1日同步股票交易日日期
     */

    @Async
    public void syncStockTradeDate() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            List<StockTradeDate> toolTradeDateHistSina = akShareService.getToolTradeDateHistSina();
            stockTradeDateMapper.delete(null);
            iStockTradeDateService.saveBatch(toolTradeDateHistSina);
            log.info("同步股票交易日历完成");
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("每年1月1日同步股票交易日日期", Constants.SUCCESS, null, toolTradeDateHistSina.size(), DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("syncStockTradeDate error", e);
            TaskLog taskLog = new TaskLog("每年1月1日同步股票交易日日期", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 每日17点同步股票每日数据
     */
    @Async
    public void syncStockZhAHist() {
        long startTime = System.currentTimeMillis();
        String bizDate = DateUtils.getBizDate();
        try {
            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
            if (!tradeDate) {
                return;
            }
            int size = 0;
            List<StockZhAHist> insertList = new ArrayList<>();
            //同步沪市A股
            List<BasicStock> shBasicStocks = akShareService.getStockShASpotEm();
            for (BasicStock shBasicStock : shBasicStocks) {
                StockZhAHist stockZhAHist = trfService.transfBasicStockToStockZhAHist(shBasicStock);
                insertList.add(stockZhAHist);
            }
            iStockZhAHistService.saveBatch(insertList);
            size = size + shBasicStocks.size();
            insertList.clear();

            //同步深市A股
            List<BasicStock> szBasicStocks = akShareService.getStockSzASpotEm();
            for (BasicStock szBasicStock : szBasicStocks) {
                StockZhAHist stockZhAHist = trfService.transfBasicStockToStockZhAHist(szBasicStock);
                insertList.add(stockZhAHist);
            }
            iStockZhAHistService.saveBatch(insertList);
            size = size + szBasicStocks.size();
            insertList.clear();

            //同步北交所A股
            List<BasicStock> bjBasicStocks = akShareService.getStockBjASpotEm();
            for (BasicStock bjBasicStock : bjBasicStocks) {
                StockZhAHist stockZhAHist = trfService.transfBasicStockToStockZhAHist(bjBasicStock);
                insertList.add(stockZhAHist);
            }
            iStockZhAHistService.saveBatch(insertList);
            size = size + bjBasicStocks.size();
            insertList.clear();

            //同步新股
            List<BasicStock> newBasicStocks = akShareService.getStockNewASpotEm();
            for (BasicStock newBasicStock : newBasicStocks) {
                StockZhAHist stockZhAHist = trfService.transfBasicStockToStockZhAHist(newBasicStock);
                insertList.add(stockZhAHist);
            }
            iStockZhAHistService.saveBatch(insertList);
            size = size + newBasicStocks.size();
            insertList.clear();

            //同步创业板
            List<BasicStock> cyBasicStocks = akShareService.getStockCyASpotEm();
            for (BasicStock cyBasicStock : cyBasicStocks) {
                StockZhAHist stockZhAHist = trfService.transfBasicStockToStockZhAHist(cyBasicStock);
                insertList.add(stockZhAHist);
            }
            iStockZhAHistService.saveBatch(insertList);
            size = size + cyBasicStocks.size();
            insertList.clear();

            //同步科创板
            List<BasicStock> kcBasicStocks = akShareService.getStockKcASpotEm();
            for (BasicStock kcBasicStock : kcBasicStocks) {
                StockZhAHist stockZhAHist = trfService.transfBasicStockToStockZhAHist(kcBasicStock);
                insertList.add(stockZhAHist);
            }
            iStockZhAHistService.saveBatch(insertList);
            size = size + kcBasicStocks.size();
            insertList.clear();

            log.info("当日全量股票数据同步完成");
            long elapsedTime = System.currentTimeMillis();
            TaskLog taskLog = new TaskLog("每日17点同步股票每日数据", Constants.SUCCESS, null, size, DateUtils.formatMillisWithPadding(elapsedTime - startTime), bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        } catch (Exception e) {
            log.error("syncStockZhAHist error", e);
            TaskLog taskLog = new TaskLog("每日17点同步股票每日数据", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
            iTaskLogService.insertTaskLog(taskLog);
        }
    }

    /**
     * 同步同花顺个股资金流入
     */
    @Async
    public void syncThsStockFundFlow() {
        //清空表
        thsStockFundFlowMapper.truncateThsStockFundFlow();
        List<ThsStockFundFlow> basicStocks = akShareService.getStockFundFlowIndividual("即时");
        iThsFundFlowService.saveBatch(basicStocks);
        List<ThsStockFundFlow> basicStocks3 = akShareService.getStockFundFlowIndividual("3日排行");
        iThsFundFlowService.saveBatch(basicStocks3);
        List<ThsStockFundFlow> basicStocks5 = akShareService.getStockFundFlowIndividual("5日排行");
        iThsFundFlowService.saveBatch(basicStocks5);
        List<ThsStockFundFlow> basicStocks10 = akShareService.getStockFundFlowIndividual("10日排行");
        iThsFundFlowService.saveBatch(basicStocks10);
        List<ThsStockFundFlow> basicStocks20 = akShareService.getStockFundFlowIndividual("20日排行");
        iThsFundFlowService.saveBatch(basicStocks20);
        log.info("同步同花顺个股资金流入完成");
    }

    /**
     * 同步东方财富顺板块资金流入
     */
    @Async
    public void syncThsBoardFundFlow() {
        thsBoardFundFlowMapper.truncateThsBoardFundFlow();
        List<ThsBoardFundFlow> listIndustry = akShareService.getThsBoardFundFlow("今日", "行业资金流");
        iThsBoardFundFlowService.saveBatch(listIndustry);
        stop(10000);

        List<ThsBoardFundFlow> listIndustry5 = akShareService.getThsBoardFundFlow("5日", "行业资金流");
        iThsBoardFundFlowService.saveBatch(listIndustry5);
        stop(10000);

        List<ThsBoardFundFlow> listIndustry10 = akShareService.getThsBoardFundFlow("10日", "行业资金流");
        iThsBoardFundFlowService.saveBatch(listIndustry10);
        stop(10000);

        List<ThsBoardFundFlow> listConcept = akShareService.getThsBoardFundFlow("今日", "概念资金流");
        iThsBoardFundFlowService.saveBatch(listConcept);
        stop(10000);

        List<ThsBoardFundFlow> listConcept5 = akShareService.getThsBoardFundFlow("5日", "概念资金流");
        iThsBoardFundFlowService.saveBatch(listConcept5);
        stop(10000);

        List<ThsBoardFundFlow> listConcept10 = akShareService.getThsBoardFundFlow("10日", "概念资金流");
        iThsBoardFundFlowService.saveBatch(listConcept10);
        stop(10000);

        List<ThsBoardFundFlow> listArea = akShareService.getThsBoardFundFlow("今日", "地域资金流");
        iThsBoardFundFlowService.saveBatch(listArea);
        stop(10000);

        List<ThsBoardFundFlow> listArea5 = akShareService.getThsBoardFundFlow("5日", "地域资金流");
        iThsBoardFundFlowService.saveBatch(listArea5);
        stop(10000);

        List<ThsBoardFundFlow> listArea10 = akShareService.getThsBoardFundFlow("10日", "地域资金流");
        iThsBoardFundFlowService.saveBatch(listArea10);
        log.info("同步东方财富板块资金流入完成");
    }

    /**
     * 每日任务数据通知
     */
    @Async
    public void syncTaskLog() {
        String bizDate = DateUtils.getBizDate();
        LambdaQueryWrapper<TaskLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TaskLog::getBizDate, bizDate);
        List<TaskLog> taskLogs = taskLogMapper.selectList(queryWrapper);
        StringBuilder sb = new StringBuilder();
        sb.append(bizDate + "任务数据通知：\n");
        if (CollectionUtils.isNotEmpty(taskLogs)) {
            for (TaskLog taskLog : taskLogs) {
                if (taskLog.getTaskStatus().equals(Constants.SUCCESS)) {
                    String msg = "任务【" + taskLog.getTaskName() + "】执行成功，耗时：" + taskLog.getDuration() + "，任务条数" + taskLog.getDataNum();
                    sb.append(msg).append("\n");
                }
                if (taskLog.getTaskStatus().equals(Constants.FAIL)) {
                    String msg = "任务【" + taskLog.getTaskName() + "】执行失败";
                    sb.append(msg).append("\n");
                }
            }
        } else {
            sb.append("无任务数据");
        }
        PushWxMessageRecord pushWxMessageRecord = new PushWxMessageRecord();
        pushWxMessageRecord.setWxId("qy01b3e5ccd3ccc10128f37211ed");
        pushWxMessageRecord.setApplicationId(WxApplicationIdEnum.SYSTEM_NOTICE.getCode());
        pushWxMessageRecord.setWxMessageContent(sb.toString());
        pushWxMessageRecord.setMessageType(WxMessageTypeEnum.SYSTEM_NOTICE.getCode());

        //发送消息
        wxHttpMultiUtils.sendWxMessagesAndRecord(pushWxMessageRecord);
    }

    /**
     * 第一天阳，第二天阴，然后第二天成交大于第一天一半，市值大于100亿筛选
     */
    @Override
    public void stockMethod() {

        //非交易日跳过
        String bizDate = DateUtils.getBizDate();
        boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
        if (!tradeDate) {
            return;
        }
        //获取前一天数据
        StockZhAHist stockZhAHist = new StockZhAHist();
        stockZhAHist.setBizDate(DateUtils.getBizDate(-1));
        List<StockZhAHist> stockZhAHists = iStockZhAHistService.selectStockZhAHistList(stockZhAHist);
        // 新增第三个参数，指定冲突时用新值（newValue）覆盖旧值（oldValue）
        Map<String, StockZhAHist> map = stockZhAHists.stream()
                .collect(Collectors.toMap(
                        StockZhAHist::getStockCode,  // Key：股票代码（stockCode）
                        a -> a,                      // Value：StockZhAHist 对象本身
                        (oldValue, newValue) -> newValue  // 核心：Key重复时，用新值覆盖旧值
                ));

        //同步沪市A股
        List<BasicStock> shBasicStocks = akShareService.getStockShASpotEm();
        StringBuilder sb = new StringBuilder();
        sb.append(bizDate + "符合条件股票通知：\n");
        sb.append("沪市A股：---------------\n");
        filterData(map, sb, shBasicStocks);

        //同步深市A股
        List<BasicStock> szBasicStocks = akShareService.getStockSzASpotEm();
        sb.append("深市A股：---------------\n");
        filterData(map, sb, szBasicStocks);

        //同步北交所A股
        List<BasicStock> bjBasicStocks = akShareService.getStockBjASpotEm();
        sb.append("北交所A股：---------------\n");
        filterData(map, sb, bjBasicStocks);

        //同步新股
        List<BasicStock> newBasicStocks = akShareService.getStockNewASpotEm();
        sb.append("新股：---------------\n");
        filterData(map, sb, newBasicStocks);

        //同步创业板
        List<BasicStock> cyBasicStocks = akShareService.getStockCyASpotEm();
        sb.append("创业板：---------------\n");
        filterData(map, sb, cyBasicStocks);

        //同步科创板
        List<BasicStock> kcBasicStocks = akShareService.getStockKcASpotEm();
        sb.append("科创板：---------------\n");
        filterData(map, sb, kcBasicStocks);
        PushWxMessageRecord pushWxMessageRecord = new PushWxMessageRecord();
        pushWxMessageRecord.setWxId("qy01b3e5ccd3ccc10128f37211ed");
        pushWxMessageRecord.setApplicationId(WxApplicationIdEnum.STOCK_NOTICE.getCode());
        pushWxMessageRecord.setWxMessageContent(sb.toString());
        pushWxMessageRecord.setMessageType(WxMessageTypeEnum.STOCK_NOTICE.getCode());
        //发送消息
        wxHttpMultiUtils.sendWxMessagesAndRecord(pushWxMessageRecord);
    }
    //批量处理,校验数据
    private void filterData(Map<String, StockZhAHist> map,StringBuilder sb, List<BasicStock> list){
        for (BasicStock shBasicStock : list) {
            StockZhAHist stockZhAHistOld = map.get(shBasicStock.getStockCode());
            if (stockZhAHistOld == null) {
                continue;
            }
            String volumeOld = stockZhAHistOld.getVolume();
            String volume = shBasicStock.getVolume();
            //昨日成交量为空，跳过
            if (StringUtils.isEmpty(volumeOld) || StringUtils.isEmpty(volume)) {
                continue;
            }
            //今日涨跌额为正数，跳过
            if (StringUtils.isEmpty(shBasicStock.getPriceRiseFall()) || new BigDecimal(shBasicStock.getPriceRiseFall()).compareTo(BigDecimal.ZERO) < 0) {
                continue;
            }
            // 注意：volume 和 volumeOld 是字符串类型（与你原有代码一致）
            BigDecimal volumeBig = new BigDecimal(volume);
            BigDecimal volumeOldBig = new BigDecimal(volumeOld);
            // 核心：先计算 volumeOldBig / 2，再比较 volumeBig 是否小于该结果
            //volume大于volumeOld一半以上
            if (volumeBig.compareTo(volumeOldBig.divide(new BigDecimal(2))) < 0) {
                continue;
            }
            if (new BigDecimal(shBasicStock.getTotalMarketValue()).compareTo(new BigDecimal("10000000000")) < 0) {
                continue;
            }
            sb.append(shBasicStock.getStockName() + "  " + shBasicStock.getStockCode() + "：\n");
        }
    }

    private void syncBasicStock(List<BasicStock> list, String type) {

        LambdaQueryWrapper<BasicStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BasicStock::getStockMarketType, type);
        Integer count = basicStockMapper.selectCount(queryWrapper);
        List<BasicStockHis> insertHisList = new ArrayList<>();
        if (count != list.size() && count > 0) {
            basicStockMapper.delete(queryWrapper);
            iBasicStockService.saveBatch(list);
            LambdaQueryWrapper<BasicStockHis> queryHisWrapper = new LambdaQueryWrapper<>();
            queryHisWrapper.eq(BasicStockHis::getStockMarketType, type);
            basicStockHisMapper.delete(queryHisWrapper);
            for (BasicStock basicStock : list) {
                BasicStockHis basicStockHis = trfService.transfBasicStockHis(basicStock);
                insertHisList.add(basicStockHis);
            }
            iBasicStockHisService.saveBatch(insertHisList);
        }
    }


    private List<FinancialRecord> findFinancialNextBatch(Long maxId) {
        PageHelper.startPage(1, 200);
        List<FinancialRecord> financialRecords = financialRecordMapper.selectListById(maxId);
        PageInfo<FinancialRecord> pageInfo = new PageInfo<>(financialRecords);
        return pageInfo.getList();
    }


    private void sendWxMessagesAndRecord(String wxMessageContent) {

        PushWxMessageRecord pushWxMessageRecord = new PushWxMessageRecord();

        pushWxMessageRecord.setWxId("qy01b3e5ccd3ccc10128f37211ed");
        pushWxMessageRecord.setApplicationId(WxApplicationIdEnum.STOCK_NOTICE.getCode());
        pushWxMessageRecord.setWxMessageContent(wxMessageContent);
        pushWxMessageRecord.setMessageType(WxMessageTypeEnum.STOCK_NOTICE.getCode());

        //发送消息
        wxHttpMultiUtils.sendWxMessagesAndRecord(pushWxMessageRecord);
    }

    /**
     * 休眠时间
     */
    private void stop(int sleepTime) {
        try {
            // 暂停1秒钟（1000毫秒）
            long l = System.currentTimeMillis();
            Thread.sleep(sleepTime);
            long l1 = System.currentTimeMillis();
            log.info("休眠时间：{}", l1 - l);
        } catch (InterruptedException e) {
            // 捕获中断异常（线程在休眠时被中断会抛出此异常）
            e.printStackTrace();
            // 可以根据需要处理中断，例如恢复中断状态
            Thread.currentThread().interrupt();
        }
    }
}
