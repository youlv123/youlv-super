/*
package com.ruoyi.work;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 有个很重要的点 CallCenterCustomerReviewDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去

*/
/**
 * i won‘t be back
 * but my code will sleep there alaways
 * DXR 2024/08/19
 * good luck！
 * 于道各努力，千里自同风
 *//*

@Slf4j
@Data
public class CallCenterCustomerReviewDataListener implements ReadListener<ImportCustomerReviewDto> {

    */
/**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     *//*

    private static final int BATCH_COUNT = 2000;

    private StringBuffer errorMsg = new StringBuffer();
    private int rowNum = 0;
//    private List<ImportCustomerReviewDto> errorDataList = new ArrayList<>();
    */
/**
     * 缓存的数据
     *//*

    private List<ImportCustomerReviewDto> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private List<ReviewImportTimpPo> poList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    */
/**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     *//*

    private ReviewImportTimpMapper reviewImportTimpMapper;
    private String type;
    private Long batchNo;
    private DcManager dcManager;
    private Map<String, String> reviewTypeMap;

*/
/*    public CallCenterCustomerReviewDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        demoDAO = new ReviewImportTimpMapper();
    }*//*


    */
/**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param reviewImportTimpMapper
     *//*

    public CallCenterCustomerReviewDataListener(ReviewImportTimpMapper reviewImportTimpMapper, DcManager dcManager, String type, Long batchNo, Map<String, String> reviewTypeMap) {
        this.reviewImportTimpMapper = reviewImportTimpMapper;
        this.type = type;
        this.batchNo = batchNo;
        this.dcManager = dcManager;
        this.reviewTypeMap = reviewTypeMap;
    }

    */
/**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     *//*

    @Override
    public void invoke(ImportCustomerReviewDto data, AnalysisContext context) {
//        log.info("解析到一条数据:{}", JSON.toJSONString(data));

        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            this.handle();
            if (StringUtils.isBlank(errorMsg)) {
                saveData();
            }
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
            poList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

        }
    }

    */
/**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     *//*

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        if (StringUtils.isNotBlank(errorMsg)) {
            reviewImportTimpMapper.deleteByPrimaryKey(batchNo);
        } else {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
            handle();
            saveData();
        }

        log.info("所有数据解析完成！");
    }

    */
/**
     * 加上存储数据库
     *//*

    private void saveData() {
        log.info("{}条数据，开始存储数据库！", poList.size());
        if (CollectionUtils.isNotEmpty(poList)) {
            reviewImportTimpMapper.batchInsert(poList);
        }
        log.info("存储数据库成功！");
    }

    private void validate(ImportCustomerReviewDto data, Map<String, String> customerMap) {
//        if (StringUtils.isBlank(data.getCustomerName())) {
//            errorMsg.append("第" + (rowNum+1) + "行,客户姓名为空！\t");
//        }

        if (StringUtils.isBlank(data.getReviewType()) | StringUtils.isBlank(reviewTypeMap.get(data.getReviewType()))) {
            errorMsg.append("第" + (rowNum+1) + "行,回访类型为空或不符合！\t");
        }

        if (StringUtils.isBlank(data.getCustomerId()) || !StringUtils.isNumeric(data.getCustomerId()) ||
                StringUtils.isBlank(customerMap.get(data.getCustomerId()))) {
            errorMsg.append("第" + (rowNum+1) + "行,客户编码为空或不正确！\t");
        }

        if (StringUtils.isBlank(data.getTriggerInfo())) {
            errorMsg.append("第" + (rowNum+1) + "行,回访任务摘要为空！\t");
        }

        if (StringUtils.isBlank(data.getCustomerServiceStaffId()) || !StringUtils.isNumeric(data.getCustomerServiceStaffId())) {
            errorMsg.append("第" + (rowNum+1) + "行,坐席为空！或填写不正确！\t");
        }
     */
/*   else if (!StringUtils.isNumeric(data.getCustomerServiceStaffId())) {
            //非法坐席
            errorMsg.append("第" + rowNum + "行,必须正确填写“坐席”！\t");
        }*//*

        // 清空字符串
//        errorMsg.setLength(ConstantUtils.ZERO);
    }

    */
/**
     * 创建ReviewImportTimpPo临时数据实体
     *
     * @param data
     * @param customerOrganMap
     *//*

    private void createReviewImportTimpPo(ImportCustomerReviewDto data, Map<String, String> customerOrganMap,Map<String, CustomerViewVo> customerViewVoMap) {
        ReviewImportTimpPo reviewImportTimpPo = new ReviewImportTimpPo();
        reviewImportTimpPo.setCustomerId(data.getCustomerId());
        reviewImportTimpPo.setCustomerServiceId(data.getCustomerServiceStaffId());
        reviewImportTimpPo.setOrganId(customerOrganMap.get(data.getCustomerId()));
        reviewImportTimpPo.setTriggerInfo(data.getTriggerInfo());
        reviewImportTimpPo.setRowNo(Long.valueOf(rowNum));
        reviewImportTimpPo.setType(type);
        reviewImportTimpPo.setBatchNo(batchNo);
        reviewImportTimpPo.setReviewType(data.getReviewType());
        reviewImportTimpPo.createDefault(SecurityUtils.getLoginUserId());
        CustomerViewVo customerViewVo = customerViewVoMap.get(data.getCustomerId());
        if (null!=customerViewVo) {
            String openAccountDate = customerViewVo.getOpenAccountDate();
            if (StringUtils.isNotBlank(openAccountDate)) {
                Date openDate = null;
                try {
                    openDate = DateTimeUtils.parseStringToDate(openAccountDate, DateTimeUtils.SIMPLE_DATE_FORMAT_BASE_2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                reviewImportTimpPo.setOpenDate(openDate);
            }
            reviewImportTimpPo.setStaffId(customerViewVo.getManagerId());
            reviewImportTimpPo.setStaffName(customerViewVo.getManagerName());
            reviewImportTimpPo.setPhone(customerViewVo.getMobile());
            reviewImportTimpPo.setCustomerAsset(customerViewVo.getTotleAsset()== null ? null : new BigDecimal(customerViewVo.getTotleAsset()));
            reviewImportTimpPo.setCustomerName(customerViewVo.getCustomerName());
        }
        poList.add(reviewImportTimpPo);

    }


    private void handle() {
        List<String> customerIds = cachedDataList.stream()
                .map(ImportCustomerReviewDto::getCustomerId)
                .map(id -> String.format("'%s'", id))// 使用map函数将对象A映射为其id
                .distinct()
                .collect(Collectors.toList()); // 将id收集到一个新的List中
//            //去重客户id
//            List<String> custNew = customerIds.stream().distinct().collect(Collectors.toList());

        //去中台查询客户
        List<CustomerViewVo> customerList = dcManager.quertCustomerList2(customerIds);
        Map<String, String> customerMap = customerList.stream().collect(Collectors
                .toMap(CustomerViewVo::getCustomerId, CustomerViewVo::getCustomerName, (k1, k2) -> k1));
            Map<String, CustomerViewVo> customerViewVoMap = customerList.stream()
                    .collect(Collectors.toMap(CustomerViewVo::getCustomerId, a -> a));

        Map<String, String> customerOrganMap = customerList.stream().collect(Collectors
                .toMap(CustomerViewVo::getCustomerId, CustomerViewVo::getOrganId, (k1, k2) -> k1));


        for (ImportCustomerReviewDto importCustomerReviewDto : cachedDataList) {
            rowNum++;
            validate(importCustomerReviewDto, customerMap);
            if (StringUtils.isBlank(errorMsg)) {
                createReviewImportTimpPo(importCustomerReviewDto, customerOrganMap,customerViewVoMap);
            }
        }
    }


}*/
