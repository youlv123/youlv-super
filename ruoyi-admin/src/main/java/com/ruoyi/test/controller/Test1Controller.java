package com.ruoyi.test.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

import com.github.shuaidd.callback.AesException;
import com.github.shuaidd.callback.WXBizJsonMsgCrypt;


import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.service.*;
import com.ruoyi.system.domain.BackgroundMusicDTO;
import com.ruoyi.system.domain.SheetInfo;
import com.ruoyi.system.domain.WxSendResponse;
import com.ruoyi.system.service.IBackgroundMusicService;
import com.ruoyi.test.dto.*;
import com.ruoyi.util.EasyExcelExportUtil;
//import com.ruoyi.util.EasyPoiExportUtil;

import com.ruoyi.util.WxHttpUtils;
import com.ruoyi.work.ImportCustomerReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/system/test")
@Slf4j
public class Test1Controller {
    @Autowired
    private IBackgroundMusicService backgroundMusicService;

    @Autowired
    private AKShareService akShareService;
    @Autowired
    private TaskService taskService;


    @Autowired
    private IStockZtPoolStrongEmService iStockZtPoolStrongEmService;

    @Autowired
    private IStockZtPoolEmService iStockZtPoolEmService;

    @Autowired
    private IStockZtPoolZbgcEmService iStockZbgcEmService;

    @Autowired
    private IStockZtPoolDtgcEmService iStockZtPoolDtgcEmService;

    @Autowired
    private IStockZhAHistService iStockZhAHistService;

    @Autowired
    private IStockBoardService stockBoardConceptNameEmService;

    @Autowired
    private WxHttpUtils wxHttpUtils;


    @Autowired
    private IStockBoardConsEmService iStockBoardConsEmService;

    @Autowired
    private IStockTradeDateService iStockTradeDateService;


    @Autowired
    private IThsStockFundFlowService iThsFundFlowService;

    @Autowired
    private WaiZaoWangService waiZaoWangService;

    @Autowired
    private ITaskLogService iTaskLogService;


    /**
     * excel导出带参数测试类
     *
     * @param response
     * @param a
     */
    @PostMapping("/list")
    public void list(HttpServletResponse response, @RequestBody BackgroundMusicDTO a) {

        List<ImportCustomerReviewDto> list = new ArrayList<>();

    }

    /**
     * 多sheet页导出测试类
     *
     * @param response
     * @throws IOException
     * @throws ParseException
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response) throws IOException, ParseException {

        List<ImportCustomerReviewDto> list = new ArrayList<>();
        ImportCustomerReviewDto importCustomerReviewDto = new ImportCustomerReviewDto();
        importCustomerReviewDto.setCustomerId("123");
        importCustomerReviewDto.setCustomerServiceStaffId("222");
        importCustomerReviewDto.setReviewType("22");
        importCustomerReviewDto.setTriggerInfo("22");
        list.add(importCustomerReviewDto);

        List<SheetInfo> sheets = new ArrayList<>();
        SheetInfo sheetInfo = new SheetInfo();
        sheetInfo.setSheetName("1");
        sheetInfo.setDataList(list);
        sheetInfo.setClazz(ImportCustomerReviewDto.class);
        SheetInfo sheetInfo1 = new SheetInfo();
        sheetInfo1.setSheetName("2");
        sheetInfo1.setDataList(list);
        sheetInfo1.setClazz(ImportCustomerReviewDto.class);
        SheetInfo sheetInfo2 = new SheetInfo();
        sheetInfo2.setSheetName("3");
        sheetInfo2.setDataList(list);
        sheetInfo2.setClazz(ImportCustomerReviewDto.class);
        sheets.add(sheetInfo);
        sheets.add(sheetInfo1);
        sheets.add(sheetInfo2);
        EasyExcelExportUtil.exportMultiSheet(response, "测试", sheets);
    }


    /**
     * 多sheet页导出测试类
     *
     * @param
     * @throws IOException
     * @throws ParseException
     */
//    @PostMapping("/export1")
//    public void export1(HttpServletResponse response) throws IOException, ParseException {
//
//        List<AuditFlowExport> list = new ArrayList<>();
//        List<AuditFlowExport> list1 = new ArrayList<>();
//        AuditFlowExport importCustomerReviewDto = new AuditFlowExport();
//        importCustomerReviewDto.setCustomerId("123");
//        importCustomerReviewDto.setCustomerServiceStaffId("222");
//        importCustomerReviewDto.setReviewType("22");
//        importCustomerReviewDto.setTriggerInfo("22");
//        list.add(importCustomerReviewDto);
//        AuditFlowExport importCustomerReviewDto1 = new AuditFlowExport();
//        importCustomerReviewDto1.setCustomerId("3");
//        importCustomerReviewDto1.setCustomerServiceStaffId("3");
//        importCustomerReviewDto1.setReviewType("3");
//        importCustomerReviewDto1.setTriggerInfo("3");
//        list1.add(importCustomerReviewDto1);
//        List<SheetInfo> sheets = new ArrayList<>();
//        SheetInfo sheetInfo = new SheetInfo();
//        sheetInfo.setSheetName("1");
//        sheetInfo.setDataList(list);
//        sheetInfo.setClazz(AuditFlowExport.class);
//        SheetInfo sheetInfo1 = new SheetInfo();
//        sheetInfo1.setSheetName("2");
//        sheetInfo1.setDataList(list1);
//        sheetInfo1.setClazz(AuditFlowExport.class);
//        SheetInfo sheetInfo2 = new SheetInfo();
//        sheetInfo2.setSheetName("3");
//        sheetInfo2.setDataList(list);
//        sheetInfo2.setClazz(AuditFlowExport.class);
//        sheets.add(sheetInfo);
//        sheets.add(sheetInfo1);
//        sheets.add(sheetInfo2);
//        EasyPoiExportUtil.exportMultiSheetWorkbook(response, "测试", sheets);
//    }
    @PostMapping("/singleQuery")
    public SingleQueryResponse singleQuery(@RequestBody SingleQueryVo singleQuery) {

        BasicInfoEntity basicInfoEntity = new BasicInfoEntity();
        basicInfoEntity.setOfferIdNb("213234");
        basicInfoEntity.setReSubCt(1);
        basicInfoEntity.setDealerCode("3");
        basicInfoEntity.setDProduct("3");
        basicInfoEntity.setPrApplcntName("3");
        basicInfoEntity.setCoAoolcntName("3");
        basicInfoEntity.setGuarantorName("3");
        basicInfoEntity.setTier("3");
        basicInfoEntity.setPop(new BigDecimal("2"));

        List<ModelDisplay> modelDisplay = new ArrayList<>();
        ModelDisplay dto = new ModelDisplay();
        dto.setOfferIdNb("223");
        dto.setScore(new BigDecimal("0"));
        dto.setView(0);
        dto.setStrategy("33");
        dto.setTransfm(new BigDecimal("0"));
        dto.setTier("3");
        dto.setFlag("Y");
        dto.setAmount("34.656");
        dto.setProvince("433");
        dto.setCallTime(new Date());
        modelDisplay.add(dto);
        modelDisplay.add(dto);

        SingleQueryResponse singleQueryResponse = new SingleQueryResponse();
        singleQueryResponse.setBasicInfo(basicInfoEntity);
        singleQueryResponse.setModelDisplay(modelDisplay);
        return singleQueryResponse;
    }

    @PostMapping("/batchQuery")
    public void batchQuery(HttpServletResponse response, BatchQueryDTO a) throws IOException, ParseException {
//    public void batchQuery(HttpServletResponse response) throws IOException, ParseException {

        List<ImportCustomerReviewDto> list = new ArrayList<>();
        ImportCustomerReviewDto importCustomerReviewDto = new ImportCustomerReviewDto();
        importCustomerReviewDto.setCustomerId("123");
        importCustomerReviewDto.setCustomerServiceStaffId("222");
        importCustomerReviewDto.setReviewType("22");
        importCustomerReviewDto.setTriggerInfo("22");
        list.add(importCustomerReviewDto);

        List<SheetInfo> sheets = new ArrayList<>();
        SheetInfo sheetInfo = new SheetInfo();
        sheetInfo.setSheetName("1");
        sheetInfo.setDataList(list);
        sheetInfo.setClazz(ImportCustomerReviewDto.class);
        SheetInfo sheetInfo1 = new SheetInfo();
        sheetInfo1.setSheetName("2");
        sheetInfo1.setDataList(list);
        sheetInfo1.setClazz(ImportCustomerReviewDto.class);
        SheetInfo sheetInfo2 = new SheetInfo();
        sheetInfo2.setSheetName("3");
        sheetInfo2.setDataList(list);
        sheetInfo2.setClazz(ImportCustomerReviewDto.class);
        sheets.add(sheetInfo);
        sheets.add(sheetInfo1);
        sheets.add(sheetInfo2);
        EasyExcelExportUtil.exportMultiSheet(response, "测试", sheets);

    }

    /**
     * 读多个或者全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link }
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link }
     * <p>
     * 3. 直接读即可
     */

    @PostMapping("/importReviewTicket")
    public void importReviewTicket(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException, ParseException {
        // 入参非空校验
        if (Objects.isNull(file)) {
            return;
        }
/*        DataListener dataListener = new DataListener();
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream(), Demo.class, dataListener).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }*/
        List<List<DemoOut>> allSheetData = new ArrayList<>();
        Map<Integer, List<DemoOut>> mapN = new HashMap<>();
        Map<Integer, String> sheetNameMap = new HashMap<>();
        DataListener dataListener = new DataListener(allSheetData, mapN);
        int rowNum = 0;
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream(), Demo.class, dataListener).build()) {
            // 获取所有Sheet
            List<ReadSheet> readSheets = excelReader.excelExecutor().sheetList();

            // 遍历每个Sheet并读取
            for (ReadSheet readSheet : readSheets) {
                rowNum++;
                System.out.println("准备读取的 Sheet: " + readSheet.getSheetName());
                excelReader.read(readSheet);
                sheetNameMap.put(rowNum, readSheet.getSheetName());

            }
            // 在这里获取数据，确保所有的 Sheet 都已解析

            Map<Integer, List<DemoOut>> map = dataListener.getMap();
            List<SheetInfo> sheets = new ArrayList<>();
            for (int i = 1; i <= rowNum; i++) {
                SheetInfo sheetInfo = new SheetInfo();
                sheetInfo.setSheetName(sheetNameMap.get(i));
                sheetInfo.setDataList(map.get(i));
                sheetInfo.setClazz(DemoOut.class);
                sheets.add(sheetInfo);
            }
            EasyExcelExportUtil.exportMultiSheet(response, "六大维度差评率1", sheets);
        }
    }


    @PostMapping("/importReviewTicket1")
    public void importReviewTicket1(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException, ParseException {
        // 入参非空校验
        if (Objects.isNull(file)) {
            return;
        }

        // 读取 Sheet1
        List<Sheet1> sheet1DataList = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), Sheet1.class, new DataListener1(sheet1DataList))
                .sheet("客户销售数据") // 指定 Sheet 页名称或索引
                .doRead();

        // 读取 Sheet2
        List<Sheet2> sheet2DataList = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), Sheet2.class, new DataListener2(sheet2DataList))
                .sheet("美的商品数据") // 指定 Sheet 页名称或索引
                .doRead();

        // 读取 Sheet3
        List<Sheet3> sheet3DataList = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), Sheet3.class, new DataListener3(sheet3DataList))
                .sheet("匹配表") // 指定 Sheet 页名称或索引
                .doRead();
        Map<String, String> map = new HashMap<>();
        for (Sheet3 sheet3 : sheet3DataList) {
            map.put(sheet3.getCode(), sheet3.getMeiShopCode());
        }

        Map<String, List<Sheet2>> sheet2Map = sheet2DataList.stream()
                .collect(Collectors.groupingBy(Sheet2::getMeiShopCode));
        List<SheetOut> returnList = new ArrayList<>();
        List<Sheet1> sheet1DataList1 = new ArrayList<>();
        String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则
        for (Sheet1 sheet1 : sheet1DataList) {
            SheetOut sheetOut = new SheetOut();
            sheetOut.setCode(sheet1.getCode());
            sheetOut.setShopName(sheet1.getShopName());
            sheetOut.setMeiShopName(sheet1.getMeiShopName());
            sheetOut.setProductName(sheet1.getProductName());
            sheetOut.setProductCode(sheet1.getProductCode());
            sheetOut.setNumber(sheet1.getNumber());
            returnList.add(sheetOut);

            if (StringUtils.isBlank(sheet1.getCode())) {
                continue;
            }
            String code = map.get(sheet1.getCode());
            String result1 = sheet1.getProductName().replaceAll("[\\s]", "").replaceAll(REGEX_CHINESE, "");
            List<Sheet2> sheet2s = sheet2Map.get(code);
            if (CollectionUtils.isNotEmpty(sheet2s)) {
                for (int i = sheet2s.size() - 1; i >= 0; i--) {
                    Sheet2 sheet2 = sheet2s.get(i);
                    String result2 = sheet2.getProduct().replaceAll("[\\s]", "").replaceAll(REGEX_CHINESE, "");
                    if (result1.equals(result2)) {
                        sheetOut.setRun(sheet2.getRun());
                        sheetOut.setShopName1(sheet2.getShopName());
                        sheetOut.setMeiShopCode(sheet2.getMeiShopCode());
                        sheetOut.setMeiShopName1(sheet2.getMeiShopName());
                        sheetOut.setBrand(sheet2.getBrand());
                        sheetOut.setProductCode1(sheet2.getProductCode());
                        sheetOut.setProduct(sheet2.getProduct());
                        sheetOut.setSaleNumber(sheet2.getSaleNumber());
                        sheet2s.remove(i);
                        break;
                    }

                }
                sheet1DataList1.add(sheet1);
            }
        }
        for (Sheet1 sheet1 : sheet1DataList1) {
            SheetOut sheetOut = new SheetOut();
            sheetOut.setCode(sheet1.getCode());
            sheetOut.setShopName(sheet1.getShopName());
            sheetOut.setMeiShopName(sheet1.getMeiShopName());
            sheetOut.setProductName(sheet1.getProductName());
            sheetOut.setProductCode(sheet1.getProductCode());
            sheetOut.setNumber(sheet1.getNumber());
            returnList.add(sheetOut);
        }
        for (Map.Entry<String, List<Sheet2>> stringListEntry : sheet2Map.entrySet()) {
            if (CollectionUtils.isNotEmpty(stringListEntry.getValue())) {
                for (Sheet2 sheet2 : stringListEntry.getValue()) {
                    SheetOut sheetOut = new SheetOut();
                    sheetOut.setRun(sheet2.getRun());
                    sheetOut.setShopName1(sheet2.getShopName());
                    sheetOut.setMeiShopCode(sheet2.getMeiShopCode());
                    sheetOut.setMeiShopName1(sheet2.getMeiShopName());
                    sheetOut.setBrand(sheet2.getBrand());
                    sheetOut.setProductCode1(sheet2.getProductCode());
                    sheetOut.setProduct(sheet2.getProduct());
                    sheetOut.setSaleNumber(sheet2.getSaleNumber());
                    returnList.add(sheetOut);
                }
            }
        }

        EasyExcelExportUtil.export(response, "六大维度差评率1", "11", returnList, SheetOut.class);

    }


    @PostMapping("/importRate")
    public void importRate(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException, ParseException {
        // 入参非空校验
        if (Objects.isNull(file)) {
            return;
        }
/*        DataListener dataListener = new DataListener();
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream(), Demo.class, dataListener).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }*/
        List<List<DemoOut>> allSheetData = new ArrayList<>();
        Map<Integer, List<DemoOut>> mapN = new HashMap<>();
        Map<Integer, String> sheetNameMap = new HashMap<>();
        DataListener dataListener = new DataListener(allSheetData, mapN);
        int rowNum = 0;
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream(), Demo.class, dataListener).build()) {
            // 获取所有Sheet
            List<ReadSheet> readSheets = excelReader.excelExecutor().sheetList();

            // 遍历每个Sheet并读取
            for (ReadSheet readSheet : readSheets) {
                rowNum++;
                System.out.println("准备读取的 Sheet: " + readSheet.getSheetName());
                excelReader.read(readSheet);
                sheetNameMap.put(rowNum, readSheet.getSheetName());

            }
            // 在这里获取数据，确保所有的 Sheet 都已解析

            Map<Integer, List<DemoOut>> map = dataListener.getMap();
            List<SheetInfo> sheets = new ArrayList<>();
            List<DemoOut> firstSheetData = map.get(1);
            int size = firstSheetData.size();

            // 创建一个 Map
            Map<Integer, List<DemoOut>> cacuMap = new HashMap<>();

            // 循环从 1 到 i
            for (int j = 1; j <= size; j++) {
                // 创建一个新的 List<String>
                List<DemoOut> cacuList = new ArrayList<>();
                // 可以在这里添加一些初始值到 list 中
                // list.add("example");

                // 将 list 添加到 map 中，key 为 j
                cacuMap.put(j, cacuList);
            }
            //对总数量循环
            for (int i = 1; i <= rowNum; i++) {
                //拿到每个sheet页数据
                List<DemoOut> demoOuts = map.get(i);
                //循环每一页，拿到每一条数据
                for (int a = 0; a < demoOuts.size(); a++) {
                    DemoOut demoOut = demoOuts.get(a);
                    cacuMap.get(a + 1).add(demoOut);
                }

            }
            List<DemoRateOut> demoRateOutList = new ArrayList<>();
            for (Map.Entry<Integer, List<DemoOut>> entry : cacuMap.entrySet()) {
                Integer key = entry.getKey();
                List<DemoOut> list = entry.getValue();
                BigDecimal noiseRate = new BigDecimal("0");
                BigDecimal smellRate = new BigDecimal("0");
                BigDecimal waterRate = new BigDecimal("0");
                BigDecimal networkRate = new BigDecimal("0");
                BigDecimal airRate = new BigDecimal("0");
                BigDecimal tvRate = new BigDecimal("0");
                for (DemoOut demoOut : list) {

                    noiseRate = noiseRate.add(demoOut.getNoiseRate());
                    smellRate = smellRate.add(demoOut.getSmellRate());
                    waterRate = waterRate.add(demoOut.getWaterRate());
                    networkRate = networkRate.add(demoOut.getNetworkRate());
                    airRate = airRate.add(demoOut.getAirRate());
                    tvRate = tvRate.add(demoOut.getTvRate());
                }
                DemoRateOut demoRateOut = new DemoRateOut();
                demoRateOut.setDate("开业第" + key + "月平均数据");
                demoRateOut.setNoiseRate(noiseRate.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP));

                demoRateOut.setSmellRate(smellRate.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP));
                demoRateOut.setWaterRate(waterRate.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP));
                demoRateOut.setNetworkRate(networkRate.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP));
                demoRateOut.setAirRate(airRate.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP));
                demoRateOut.setTvRate(tvRate.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP));
                demoRateOutList.add(demoRateOut);
            }
            SheetInfo sheetInfo = new SheetInfo();
            sheetInfo.setSheetName("1");
            sheetInfo.setDataList(demoRateOutList);
            sheetInfo.setClazz(DemoRateOut.class);
            sheets.add(sheetInfo);
            EasyExcelExportUtil.exportMultiSheet(response, "按月平均", sheets);
        }
    }


    /**
     * 导入OTA数据
     * 计算五个酒店之间的各种维度的各种表扬率排行榜
     *
     * @param response
     * @param file
     * @throws IOException
     * @throws ParseException
     */
    @PostMapping("/importOTA")
    public void importOTA(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException, ParseException {
        // 入参非空校验
        if (Objects.isNull(file)) {
            return;
        }

        // 读取 Sheet1
        List<SheetOtaIn> list = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), SheetOtaIn.class, new DataListenerOAT(list))
                .sheet("1") // 指定 Sheet 页名称或索引
                .doRead();
        List<SheetOtaOut> outList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int count = i + 1;
            SheetOtaIn sheetOtaIn = list.get(i);
            List<SheetOtaOut> temList = new ArrayList<>();
            SheetOtaOut sheetOtaOut = new SheetOtaOut();
            BigDecimal dividend = new BigDecimal(sheetOtaIn.getCriticalViewNum());
            BigDecimal divisor = new BigDecimal(sheetOtaIn.getPraisingViewsNum());
            BigDecimal total = dividend.add(divisor);
            BigDecimal result = divisor.divide(total, 6, RoundingMode.HALF_UP);
            sheetOtaOut.setDate(sheetOtaIn.getDate());
            sheetOtaOut.setDimension(sheetOtaIn.getDimension());
            sheetOtaOut.setBrandDimension("亚朵");
            sheetOtaOut.setCriticalViewNum(sheetOtaIn.getCriticalViewNum());
            sheetOtaOut.setPraisingViewsNum(sheetOtaIn.getPraisingViewsNum());
            sheetOtaOut.setPraisingRate(result.toString());
            sheetOtaOut.setReviewsNum(sheetOtaIn.getReviewsNum());
            sheetOtaOut.setAuxiliaryList(sheetOtaIn.getDimension() + result);
            BigDecimal CriticalViewNum = new BigDecimal(sheetOtaIn.getCriticalViewNum());
            BigDecimal reviewsNum = new BigDecimal(sheetOtaIn.getReviewsNum());
            BigDecimal comments = CriticalViewNum.multiply(new BigDecimal("100")).divide(reviewsNum, 6, RoundingMode.HALF_UP);
            sheetOtaOut.setComments(comments.toString());
            if (i < 9) {
                sheetOtaOut.setTouchpointNumber("C0" + count);
            } else {
                sheetOtaOut.setTouchpointNumber("C" + count);
            }
            if (i != 17) {
                SheetOtaOut sheetOtaOut1 = new SheetOtaOut();
                BigDecimal dividend1 = new BigDecimal(sheetOtaIn.getCriticalViewNum1());
                BigDecimal divisor1 = new BigDecimal(sheetOtaIn.getPraisingViewsNum1());
                BigDecimal total1 = dividend1.add(divisor1);
                BigDecimal result1 = divisor1.divide(total1, 6, RoundingMode.HALF_UP);
                sheetOtaOut1.setDate(sheetOtaIn.getDate());
                sheetOtaOut1.setDimension(sheetOtaIn.getDimension());
                sheetOtaOut1.setBrandDimension("城际");
                sheetOtaOut1.setCriticalViewNum(sheetOtaIn.getCriticalViewNum1());
                sheetOtaOut1.setPraisingViewsNum(sheetOtaIn.getPraisingViewsNum1());
                sheetOtaOut1.setPraisingRate(result1.toString());
                sheetOtaOut1.setReviewsNum(sheetOtaIn.getReviewsNum1());
                sheetOtaOut1.setAuxiliaryList(sheetOtaIn.getDimension() + result1);
                BigDecimal CriticalViewNum1 = new BigDecimal(sheetOtaIn.getCriticalViewNum1());
                System.out.println(i);
                BigDecimal reviewsNum1 = new BigDecimal(sheetOtaIn.getReviewsNum1());
                BigDecimal comments1 = CriticalViewNum1.multiply(new BigDecimal("100")).divide(reviewsNum1, 6, RoundingMode.HALF_UP);
                sheetOtaOut1.setComments(comments1.toString());
                if (i < 9) {
                    sheetOtaOut1.setTouchpointNumber("C0" + count);
                } else {
                    sheetOtaOut1.setTouchpointNumber("C" + count);
                }

                SheetOtaOut sheetOtaOut2 = new SheetOtaOut();
                BigDecimal dividend2 = new BigDecimal(sheetOtaIn.getCriticalViewNum2());
                BigDecimal divisor2 = new BigDecimal(sheetOtaIn.getPraisingViewsNum2());
                BigDecimal total2 = dividend2.add(divisor2);
                BigDecimal result2 = divisor2.divide(total2, 6, RoundingMode.HALF_UP);
                sheetOtaOut2.setDate(sheetOtaIn.getDate());
                sheetOtaOut2.setDimension(sheetOtaIn.getDimension());
                sheetOtaOut2.setBrandDimension("希尔顿欢朋");
                sheetOtaOut2.setCriticalViewNum(sheetOtaIn.getCriticalViewNum2());
                sheetOtaOut2.setPraisingViewsNum(sheetOtaIn.getPraisingViewsNum2());
                sheetOtaOut2.setPraisingRate(result2.toString());
                sheetOtaOut2.setReviewsNum(sheetOtaIn.getReviewsNum2());
                sheetOtaOut2.setAuxiliaryList(sheetOtaIn.getDimension() + result2);
                BigDecimal CriticalViewNum2 = new BigDecimal(sheetOtaIn.getCriticalViewNum2());
                BigDecimal reviewsNum2 = new BigDecimal(sheetOtaIn.getReviewsNum2());
                BigDecimal comments2 = CriticalViewNum2.multiply(new BigDecimal("100")).divide(reviewsNum2, 6, RoundingMode.HALF_UP);
                sheetOtaOut2.setComments(comments2.toString());
                if (i < 9) {
                    sheetOtaOut2.setTouchpointNumber("C0" + count);
                } else {
                    sheetOtaOut2.setTouchpointNumber("C" + count);
                }

                SheetOtaOut sheetOtaOut3 = new SheetOtaOut();
                BigDecimal dividend3 = new BigDecimal(sheetOtaIn.getCriticalViewNum3());
                BigDecimal divisor3 = new BigDecimal(sheetOtaIn.getPraisingViewsNum3());
                BigDecimal total3 = dividend3.add(divisor3);
                BigDecimal result3 = divisor3.divide(total3, 6, RoundingMode.HALF_UP);
                sheetOtaOut3.setDate(sheetOtaIn.getDate());
                sheetOtaOut3.setDimension(sheetOtaIn.getDimension());
                sheetOtaOut3.setBrandDimension("桔子水晶");
                sheetOtaOut3.setCriticalViewNum(sheetOtaIn.getCriticalViewNum3());
                sheetOtaOut3.setPraisingViewsNum(sheetOtaIn.getPraisingViewsNum3());
                sheetOtaOut3.setPraisingRate(result3.toString());
                sheetOtaOut3.setReviewsNum(sheetOtaIn.getReviewsNum3());
                sheetOtaOut3.setAuxiliaryList(sheetOtaIn.getDimension() + result3);
                BigDecimal CriticalViewNum3 = new BigDecimal(sheetOtaIn.getCriticalViewNum3());
                BigDecimal reviewsNum3 = new BigDecimal(sheetOtaIn.getReviewsNum3());
                BigDecimal comments3 = CriticalViewNum3.multiply(new BigDecimal("100")).divide(reviewsNum3, 6, RoundingMode.HALF_UP);
                sheetOtaOut3.setComments(comments3.toString());
                if (i < 9) {
                    sheetOtaOut3.setTouchpointNumber("C0" + count);
                } else {
                    sheetOtaOut3.setTouchpointNumber("C" + count);
                }

                SheetOtaOut sheetOtaOut4 = new SheetOtaOut();
                BigDecimal dividend4 = new BigDecimal(sheetOtaIn.getCriticalViewNum4());
                BigDecimal divisor4 = new BigDecimal(sheetOtaIn.getPraisingViewsNum4());
                BigDecimal total4 = dividend4.add(divisor4);
                BigDecimal result4 = divisor4.divide(total4, 6, RoundingMode.HALF_UP);
                sheetOtaOut4.setDate(sheetOtaIn.getDate());
                sheetOtaOut4.setDimension(sheetOtaIn.getDimension());
                sheetOtaOut4.setBrandDimension("美居");
                sheetOtaOut4.setCriticalViewNum(sheetOtaIn.getCriticalViewNum4());
                sheetOtaOut4.setPraisingViewsNum(sheetOtaIn.getPraisingViewsNum4());
                sheetOtaOut4.setPraisingRate(result4.toString());
                sheetOtaOut4.setReviewsNum(sheetOtaIn.getReviewsNum4());
                sheetOtaOut4.setAuxiliaryList(sheetOtaIn.getDimension() + result4);
                BigDecimal CriticalViewNum4 = new BigDecimal(sheetOtaIn.getCriticalViewNum4());
                BigDecimal reviewsNum4 = new BigDecimal(sheetOtaIn.getReviewsNum4());
                BigDecimal comments4 = CriticalViewNum4.multiply(new BigDecimal("100")).divide(reviewsNum4, 6, RoundingMode.HALF_UP);
                sheetOtaOut4.setComments(comments4.toString());
                if (i < 9) {
                    sheetOtaOut4.setTouchpointNumber("C0" + count);
                } else {
                    sheetOtaOut4.setTouchpointNumber("C" + count);
                }
                temList.add(sheetOtaOut1);
                temList.add(sheetOtaOut2);
                temList.add(sheetOtaOut3);
                temList.add(sheetOtaOut4);
            }
            temList.add(sheetOtaOut);

            Collections.sort(temList, new Comparator<SheetOtaOut>() {
                @Override
                public int compare(SheetOtaOut e1, SheetOtaOut e2) {
                    // 将String类型的praiseRate转换为double进行比较
                    double praiseRate1 = Double.parseDouble(e1.getPraisingRate());
                    double praiseRate2 = Double.parseDouble(e2.getPraisingRate());
                    // 降序排序，使得最大的表扬率排在最前面
                    return Double.compare(praiseRate2, praiseRate1);
                }
            });

            // 设置rank字段
            for (int b = 0; b < temList.size(); b++) {
                int aa = b + 1;
                temList.get(b).setRank(String.valueOf(aa));
            }
            outList.addAll(temList);
            temList.clear();


        }


        List<SheetInfo> sheets = new ArrayList<>();

        SheetInfo sheetInfo = new SheetInfo();
        sheetInfo.setSheetName("1");
        sheetInfo.setDataList(outList);
        sheetInfo.setClazz(SheetOtaOut.class);
        sheets.add(sheetInfo);
        EasyExcelExportUtil.exportMultiSheet(response, "11", sheets);
    }

    /**
     * 导入OTA数据 简易版
     * 计算五个酒店之间的各种维度的各种表扬率排行榜
     *
     * @param response
     * @param file
     * @throws IOException
     * @throws ParseException
     */
    @PostMapping("/importOTAeasy")
    public void importOTAeasy(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException, ParseException {
        // 入参非空校验
        if (Objects.isNull(file)) {
            return;
        }

        // 读取 Sheet1
        List<SheetOtaEasyIn> list = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), SheetOtaEasyIn.class, new DataListenerOATeasy(list))
                .sheet("1") // 指定 Sheet 页名称或索引
                .doRead();
        List<SheetOtaOut> outList = new ArrayList<>();
        //list按照维度进行分组
        Map<String, List<SheetOtaEasyIn>> map = list.stream().collect(Collectors.groupingBy(SheetOtaEasyIn::getDimension));
        int a = 0;
        String touchpointNumber = "";
        List<SheetOtaOut> temList = new ArrayList<>();
        for (Map.Entry<String, List<SheetOtaEasyIn>> entry : map.entrySet()) {
            a++;
            if (a < 9) {
                touchpointNumber = "C0" + a;
            } else {
                touchpointNumber = "C" + a;
            }
            List<SheetOtaEasyIn> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                SheetOtaEasyIn sheetOtaIn = value.get(i);
                SheetOtaOut sheetOtaOut = new SheetOtaOut();
                BigDecimal dividend = new BigDecimal(sheetOtaIn.getCriticalViewNum());
                BigDecimal divisor = new BigDecimal(sheetOtaIn.getPraisingViewsNum());
                BigDecimal total = dividend.add(divisor);
                BigDecimal result = divisor.divide(total, 6, RoundingMode.HALF_UP);
                sheetOtaOut.setDate(sheetOtaIn.getDate());
                sheetOtaOut.setDimension(sheetOtaIn.getDimension());
                sheetOtaOut.setBrandDimension(sheetOtaIn.getBrand());
                sheetOtaOut.setCriticalViewNum(sheetOtaIn.getCriticalViewNum());
                sheetOtaOut.setPraisingViewsNum(sheetOtaIn.getPraisingViewsNum());
                sheetOtaOut.setPraisingRate(result.toString());
                sheetOtaOut.setReviewsNum(sheetOtaIn.getReviewsNum());
                sheetOtaOut.setAuxiliaryList(sheetOtaIn.getDimension() + result);
                BigDecimal CriticalViewNum = new BigDecimal(sheetOtaIn.getCriticalViewNum());
                BigDecimal reviewsNum = new BigDecimal(sheetOtaIn.getReviewsNum());
                BigDecimal comments = CriticalViewNum.multiply(new BigDecimal("100")).divide(reviewsNum, 6, RoundingMode.HALF_UP);
                sheetOtaOut.setComments(comments.toString());
                sheetOtaOut.setTouchpointNumber(touchpointNumber);
                temList.add(sheetOtaOut);

            }
            Collections.sort(temList, new Comparator<SheetOtaOut>() {
                @Override
                public int compare(SheetOtaOut e1, SheetOtaOut e2) {
                    // 将String类型的praiseRate转换为double进行比较
                    double praiseRate1 = Double.parseDouble(e1.getPraisingRate());
                    double praiseRate2 = Double.parseDouble(e2.getPraisingRate());
                    // 降序排序，使得最大的表扬率排在最前面
                    return Double.compare(praiseRate2, praiseRate1);
                }
            });

            // 设置rank字段
            for (int b = 0; b < temList.size(); b++) {
                int aa = b + 1;
                temList.get(b).setRank(String.valueOf(aa));
            }
            outList.addAll(temList);
            temList.clear();
        }

        List<SheetInfo> sheets = new ArrayList<>();

        SheetInfo sheetInfo = new SheetInfo();
        sheetInfo.setSheetName("1");
        sheetInfo.setDataList(outList);
        sheetInfo.setClazz(SheetOtaOut.class);
        sheets.add(sheetInfo);
        EasyExcelExportUtil.exportMultiSheet(response, "11", sheets);
    }

    @GetMapping("/getStockZtPoolEm")
    public void getStockZtPoolEm(String bizDate) {
        List<StockZtPoolEm> list = akShareService.getStockZtPoolEm(bizDate);
        iStockZtPoolEmService.saveBatch(list);
//        akShareService.getStockZtPoolEm(bizDate);

    }

    @GetMapping("/syncStockBoard")
    public int syncStockBoard() {
        taskService.syncStockBoard();
        return 1;

    }

    @GetMapping("/getStockZtPoolStrongEm")
    public void getStockZtPoolStrongEm(String bizDate) {

        List<StockZtPoolStrongEm> list = akShareService.getStockZtPoolStrongEm(bizDate);
        iStockZtPoolStrongEmService.saveBatch(list);
    }

    @GetMapping("/getStockZtPoolZbgcEm")
    public void getStockZtPoolZbgcEm(String bizDate) {
        List<StockZtPoolZbgcEm> list = akShareService.getStockZtPoolZbgcEm(bizDate);
        iStockZbgcEmService.saveBatch(list);
//        akShareService.getStockZtPoolEm(bizDate);

    }

    @GetMapping("/getStockZtPoolDtgcEm")
    public void getStockZtPoolDtgcEm(String bizDate) {
        List<StockZtPoolDtgcEm> list = akShareService.getStockZtPoolDtgcEm(bizDate);
        iStockZtPoolDtgcEmService.saveBatch(list);

    }

    @GetMapping("/getStockZhAHist")
    public void getStockZhAHist(String bizDate) {
        List<StockZhAHist> list = akShareService.getStockZhAHist("603686", "daily", "20250521", "20250603", "", "", "上证", "");
        iStockZhAHistService.saveBatch(list);
    }

    @GetMapping("/getStockBoardConceptNameEm")
    public void getStockBoardConceptNameEm() {
        List<StockBoard> list = akShareService.getStockBoardConceptNameEm();
        stockBoardConceptNameEmService.saveBatch(list);
    }

    @GetMapping("/getStockoardConceptSpotEm")
    public void getStockoardConceptSpotEm() {
        StockoardConceptSpotOutDTO list = akShareService.getStockoardConceptSpotEm("昨日涨停_含一字");
        String amplitude = list.getAmplitude();
    }


    @GetMapping("/getStockBoardConceptConsEm")
    public void getStockBoardConceptConsEm() {
        List<StockBoardConsEm> list = akShareService.getStockBoardConceptConsEm("BK0983", "核污染防治");
        iStockBoardConsEmService.saveBatch(list);
    }

    @GetMapping("/getStockBoardConceptHistEm")
    public void getStockBoardConceptHistEm() {
        List<StockBoardDataHistEm> list = akShareService.getStockBoardConceptHistEm("BK1051", "昨日涨停_含一字", "daily", "20250521", "20250603", "");
        int size = list.size();
    }

    @GetMapping("/getstockBoardIndustryHistEm")
    public void getstockBoardIndustryHistEm() {
        List<StockBoardDataHistEm> list = akShareService.getstockBoardIndustryHistEm("BK1051", "工程机械", "日k", "20250521", "20250603", "");
        int size = list.size();
    }


    @GetMapping("/getStockBoardConceptHistMinEm")
    public void getStockBoardConceptHistMinEm() {
        List<StockBoardConceptHistMinEm> list = akShareService.getStockBoardConceptHistMinEm("昨日涨停_含一字", "1");
        int size = list.size();
    }

    @GetMapping("/getStockBoardIndustryNameEm")
    public void getStockBoardIndustryNameEm() {
        List<StockBoard> list = akShareService.getStockBoardIndustryNameEm();
        stockBoardConceptNameEmService.saveBatch(list);
    }

    @GetMapping("/getStockBoardIndustrySpotEm")
    public void getStockBoardIndustrySpotEm() {
        StockoardConceptSpotOutDTO list = akShareService.getStockBoardIndustrySpotEm("贵金属");
        String amplitude = list.getAmplitude();
    }

    @GetMapping("/getStockBoardIndustryConsEm")
    public void getStockBoardIndustryConsEm() {
        WxSendResponse wxSendResponse = wxHttpUtils.sendWxMessages("qy01b3e5ccd3ccc10128f37211ed", "哈哈");
        Integer errcode = wxSendResponse.getErrcode();
    }

    @GetMapping("/syncStockZhAHist")
    public void syncStockZhAHist() {
        taskService.syncStockZhAHist();
    }

    @GetMapping("/stockMethod")
    public void stockMethod() {
        taskService.stockMethod();
    }

    @GetMapping("/fundEtfSpotEm")
    public void fundEtfSpotEm() {
        akShareService.fundEtfSpotEm();
    }

    @GetMapping("/fundEtfSpotThs")
    public void fundEtfSpotThs() {
        akShareService.fundEtfSpotThs("20260107");
    }

    @GetMapping("/fundOpenFundDailyEm")
    public void fundOpenFundDailyEm() {
        akShareService.fundOpenFundDailyEm();
    }

    @GetMapping("/futuresHqSubscribeExchangeSymbol")
    public List<FuturesFqSubscribeExchangeSymbol> futuresHqSubscribeExchangeSymbol() {
        return akShareService.futuresHqSubscribeExchangeSymbol();
    }
    @GetMapping("/futuresForeignCommodityRealtime")
    public List<FuturesForeignCommodityRealtime> futuresForeignCommodityRealtime(String  symbol) {
        return akShareService.futuresForeignCommodityRealtime(symbol);
    }

    @GetMapping("/test1")
    public int initStockZhAHist(String startDate, String endDate) {
//        List<StockZtPoolEm> list = waiZaoWangService.getStockZtPoolEm(startDate, endDate, "all", 1, "e48f21ade36b07313bf5117c0f35635c");
//        iStockZtPoolEmService.saveBatch(list);
//        return 1;
//
//        List<StockZtPoolStrongEm> list = waiZaoWangService.getStockZtPoolStrongEm(startDate, endDate, "all", 1, "e48f21ade36b07313bf5117c0f35635c");
//        iStockZtPoolStrongEmService.saveBatch(list);

//        List<StockZtPoolZbgcEm> list = waiZaoWangService.getStockZtPoolZbgcEm(startDate, endDate, "all", 1, "e48f21ade36b07313bf5117c0f35635c");
//        iStockZbgcEmService.saveBatch(list);

//
//        List<StockZtPoolDtgcEm> list = waiZaoWangService.getStockZtPoolDtgcEm(startDate, endDate, "all", 1, "e48f21ade36b07313bf5117c0f35635c");
//        iStockZtPoolDtgcEmService.saveBatch(list);

//        taskService.syncStockBoardConsEm();
        taskService.syncTaskLog();
//        String bizDate = DateUtils.getBizDate();
//        try {
//            long startTime = System.currentTimeMillis();
//            boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
//            if (!tradeDate) {
//                return 1;
//            }
//            //1、得到当日的日期
//            long elapsedTime = System.currentTimeMillis();
//            TaskLog taskLog = new TaskLog("定时获取每日涨停股票", Constants.SUCCESS, null, 0, DateUtils.formatMillisWithPadding(elapsedTime-startTime), bizDate);
//            iTaskLogService.insertTaskLog(taskLog);
//            int A=1/0;
//        } catch (Exception e) {
//            log.error("getStockZtPoolEm error", e);
//            TaskLog taskLog = new TaskLog("定时获取每日涨停股票", Constants.SUCCESS, e.getMessage(), null, null, bizDate);
//            iTaskLogService.insertTaskLog(taskLog);
//        }
        return 1;
    }

    @GetMapping("/test")
    public int test() {
        Thread thread = new Thread();
        thread.start();
//        taskService.syncThsBoardFundFlow();
//        taskService.sendStrongStock();
//        taskService.syncStockBoardConsEm();
//        List<ThsStockFundFlow> basicStocks = akShareService.getStockFundFlowIndividual("即时");
//        iThsFundFlowService.saveBatch(basicStocks);
//        List<ThsStockFundFlow> basicStocks3 = akShareService.getStockFundFlowIndividual("3日排行");
//        iThsFundFlowService.saveBatch(basicStocks3);
//        List<ThsStockFundFlow> basicStocks5 = akShareService.getStockFundFlowIndividual("5日排行");
//        iThsFundFlowService.saveBatch(basicStocks5);
//        List<ThsStockFundFlow> basicStocks10 = akShareService.getStockFundFlowIndividual("10日排行");
//        iThsFundFlowService.saveBatch(basicStocks10);
//        List<ThsStockFundFlow> basicStocks20 = akShareService.getStockFundFlowIndividual("20日排行");
//        iThsFundFlowService.saveBatch(basicStocks20);
//        taskService.syncStockZhAHist();
//        List<StockTradeDate> toolTradeDateHistSina = akShareService.getToolTradeDateHistSina();
//        int size = toolTradeDateHistSina.size();
//
//        iStockTradeDateService.saveBatch(toolTradeDateHistSina);

//        String bizDate = DateUtils.getBizDate();
//        boolean tradeDate = iStockTradeDateService.isTradeDate(bizDate);
//        System.out.println(tradeDate);
//        boolean tradeDate1 = iStockTradeDateService.isTradeDate("20250803");
//        System.out.println(tradeDate1);

//        List<BasicStock> basicStocks = akShareService.getstockShASpotEm();
//        List<BasicStock> lidt = akShareService.getstockSzASpotEm();
//        List<BasicStock> lidt1 = akShareService.getstockBjASpotEm();
//        List<BasicStock> lidt2 = akShareService.getstockNewASpotEm();
//        List<BasicStock> lidt3 = akShareService.getstockCyASpotEm();
//        List<BasicStock> lidt4 = akShareService.getstockKcASpotEm();
//        int size1 = lidt.size();
//        int size2 = lidt1.size();
//        int size = basicStocks.size();
        return 1;

    }


    /***
     * 配置企业微信回调，进入白名单，建立企业微信应用，让本地可以进行测试
     * @param msgSignature
     * @param nonce
     * @param echoStr
     * @param timestamp
     * receiveid是企业id
     * @param json
     * http://101.132.191.177:8083/system/test/json/callback
     * @return
     * @throws AesException
     */
    @RequestMapping("/json/callback")
    public String callbackJson(@RequestParam(value = "msg_signature", required = false) String msgSignature,
                               @RequestParam(value = "nonce", required = false) String nonce,
                               @RequestParam(value = "echostr", required = false) String echoStr,
                               @RequestParam(value = "timestamp", required = false) String timestamp,
                               @RequestBody(required = false) String json
    ) throws AesException {
        log.info("密文--{}--timestamp--{}--nonce---{},echostr--{}", msgSignature, timestamp, nonce, echoStr);
//        WXBizJsonMsgCrypt crypt = new WXBizJsonMsgCrypt("", "", "");//100002
//        WXBizJsonMsgCrypt crypt = new WXBizJsonMsgCrypt("", "", "");//100003
//        WXBizJsonMsgCrypt crypt = new WXBizJsonMsgCrypt("", "", "");//100004
        WXBizJsonMsgCrypt crypt = new WXBizJsonMsgCrypt("", "", "");//100005

        if (StringUtils.isNotEmpty(echoStr)) {
            String msg = crypt.VerifyURL(msgSignature, timestamp, nonce, echoStr);
            log.info(msg);
            return msg;
        }
        if (StringUtils.isNotEmpty(json)) {
            log.info("接收到的postData--{}", json);
            String data = crypt.DecryptMsg(msgSignature, timestamp, nonce, json);
            log.info("拿到的明文信息---{}", data);
            return data;
        }

        return null;
    }
}
