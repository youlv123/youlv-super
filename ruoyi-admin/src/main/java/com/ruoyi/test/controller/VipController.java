package com.ruoyi.test.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.github.shuaidd.callback.AesException;
import com.github.shuaidd.callback.WXBizJsonMsgCrypt;
import com.ruoyi.stock.domain.*;
import com.ruoyi.stock.service.*;
import com.ruoyi.system.domain.BackgroundMusicDTO;
import com.ruoyi.system.domain.SheetInfo;
import com.ruoyi.system.domain.WxSendResponse;
import com.ruoyi.system.service.IBackgroundMusicService;
import com.ruoyi.test.dto.*;
import com.ruoyi.util.EasyExcelExportUtil;
import com.ruoyi.util.WxHttpUtils;
import com.ruoyi.work.ImportCustomerReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/system/vip")
@Slf4j
public class VipController {
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
    @PreAuthorize("@ss.hasPermi('system:vip:list')")
    public void importOTAeasy(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException, ParseException {
        // 入参非空校验
        if (Objects.isNull(file)) {
            return;
        }
        String originalFilename = file.getOriginalFilename();
        String filenameWithoutExtension = FilenameUtils.removeExtension(originalFilename);
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
        EasyExcelExportUtil.exportMultiSheet(response, filenameWithoutExtension, sheets);
    }


    @GetMapping("/exportOTAeasyTemplate")
    public void exportOTAeasyTemplate(HttpServletResponse response) throws IOException, ParseException {
        List<SheetInfo> sheets = new ArrayList<>();
        List<SheetOtaEasyIn> outList = new ArrayList<>();
        SheetInfo sheetInfo = new SheetInfo();
        sheetInfo.setSheetName("1");
        sheetInfo.setDataList(outList);
        sheetInfo.setClazz(SheetOtaEasyIn.class);
        sheets.add(sheetInfo);
        EasyExcelExportUtil.exportMultiSheet(response, "20XX年YTD亚朵&竞品数据", sheets);
    }

}
