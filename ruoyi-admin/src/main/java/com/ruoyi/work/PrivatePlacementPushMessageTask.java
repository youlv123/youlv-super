/*
package com.ruoyi.work;

import cn.nesc.wm.visit.controller.adapter.CustomerReviewScanInfoAdapter;
import cn.nesc.wm.visit.controller.adapter.PrivatePlacementAdapter;
import cn.nesc.wm.visit.controller.model.AddMsgReceiverContantVo;
import cn.nesc.wm.visit.controller.model.AddMsgReceiverVo;
import cn.nesc.wm.visit.controller.model.CustomerViewVo;
import cn.nesc.wm.visit.controller.model.MessageBoxPushMessageVo;
import cn.nesc.wm.visit.controller.model.MessageSendInVo;
import cn.nesc.wm.visit.enums.OpenDayFlagEnum;
import cn.nesc.wm.visit.enums.PrivateReviewChannelEnum;
import cn.nesc.wm.visit.enums.PrivateReviewModeEnum;
import cn.nesc.wm.visit.enums.PrivateReviewStatusEnum;
import cn.nesc.wm.visit.enums.ReviewChannelEnum;
import cn.nesc.wm.visit.infrustracture.db.PrivatePlacementOnlineReviewDbManager;
import cn.nesc.wm.visit.infrustracture.db.mapper.PrivatePlacementMapper;
import cn.nesc.wm.visit.infrustracture.db.model.PrivatePlacementOnlineReviewPo;
import cn.nesc.wm.visit.infrustracture.db.model.PrivatePlacementPo;
import cn.nesc.wm.visit.infrustracture.oracle.mapper.T99AcalendarHrsMapper;
import cn.nesc.wm.visit.infrustracture.remote.DcManager;
import cn.nesc.wm.visit.infrustracture.remote.FinancialMallClient;
import cn.nesc.wm.visit.infrustracture.remote.StaffManager;
import cn.nesc.wm.visit.infrustracture.remote.WmNoticeClient;
import cn.nesc.wm.visit.service.OrganizationService;
import cn.nesc.wm.visit.service.PrivatePlacementServiceImpl;
import cn.nesc.wm.visit.service.model.IdListConfigBean;
import cn.nesc.wm.visit.service.model.PushFinancialDto;
import cn.nesc.wm.visit.service.model.Staff;
import cn.nesc.wm.visit.util.DateUtil;
import cn.nesc.wm.visit.util.VisitConstants;
import com.tencent.cloud.task.sdk.client.model.ExecutableTaskData;
import com.tencent.cloud.task.sdk.client.model.ProcessResult;
import com.tencent.cloud.task.sdk.client.spi.ExecutableTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.common.json.JsonUtil;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

*/
/**
 * 私募冷静期非专业投资者，发送短信，推送数据至融E通和理财商城，每天9:35启动，业务要求生成的回访任务需要在9:35之后
 *//*

@Component
@Slf4j
public class PrivatePlacementPushMessageTask implements ExecutableTask {

    //产品不在开放期
    private final static String errorNo = "-2";
    //产品不存在
    private final static String productErrorNo = "-3";

    private final static String customerTitle = "高端理财客户短信";

    private final static String staffTitle = "高端理财客户经理短信";

    private final static String errorTitle = "在线问卷异常业务消息提醒";

    @Resource
    private PrivatePlacementMapper privatePlacementMapper;

    @Resource
    private WmNoticeClient wmNoticeClient;

    @Resource
    private DcManager dcManager;

    @Resource
    private FinancialMallClient financialMallClient;

    @Resource
    private PrivatePlacementOnlineReviewDbManager privatePlacementOnlineReviewDbManager;

    @Resource
    private PrivatePlacementServiceImpl privatePlacementService;

    @Resource
    private StaffManager staffManager;

    @Resource
    private IdListConfigBean idListConfigBean;

    @Resource
    private OrganizationService organizationService;

    @Value("${privatePlacement.smsUrl}")
    private String smsUrl;

    @Value("${privatePlacement.eUrl}")
    private String eUrl;

    @Value("${privatePlacement.createBizCode}")
    private String createBizCode;

    @Value("${privatePlacement.customerSmsMsgTemplateId}")
    private Long customerSmsMsgTemplateId;

    @Value("${privatePlacement.staffSmsMsgTemplateId}")
    private Long staffSmsMsgTemplateId;

    @Value("${privatePlacement.errorSmsMsgTemplateId}")
    private Long errorSmsMsgTemplateId;

    @Value("${privatePlacement.eMsgTemplateId}")
    private Long eMsgTemplateId;

    @Value("${app.privatePlacement.bizDateFlag}")
    private Boolean bizDateFlag;

    @Value("${privatePlacement.startTime}")
    private String startTime;

    @Resource
    private T99AcalendarHrsMapper t99AcalendarHrsMapper;

    @Override
    public ProcessResult execute(ExecutableTaskData executableTaskData) {
        if (bizDateFlag) {
            LocalDateTime now = LocalDateTime.now();
            // 1、判断当前时间是否在9:35-17:00
            LocalDateTime start = now.withHour(9).withMinute(35).withSecond(0);
            LocalDateTime end = now.withHour(17).withMinute(0).withSecond(0);
            if (now.isAfter(end) || now.isBefore(start)) {
                log.info("=== 当前时间[{}]不在指定时间区间，不进行处理", now);
                return ProcessResult.newSuccessResult("当前时间不在指定时间区间，不进行处理");
            }
            // 2、判断今天是不是交易日
            Integer today = Integer.valueOf(DateUtil.formatDate(DateUtil.BASIC_ISO_DATE, now));
            Integer bizDate = t99AcalendarHrsMapper.selectBizDate(today);
            if (!today.equals(bizDate)) {
                log.info("=== 今天[{}]不是交易日，不进行处理", today);
                return ProcessResult.newSuccessResult("今天不是交易日，不进行处理！");
            }
        }

        //3、新建消息发送任务，每五分钟扫描一次私募冷静期表，拿到回访状态为未回访   的且   专业投资者标识为否的数据，
        // entrust_status委托状态不为撤单，废单，即状态不为6.9
        //在开放日的数据，冷静到期时间小于当前时间的，即过了冷静期的数据
        List<String> entrustStatusList = new ArrayList<>();
        entrustStatusList.add(VisitConstants.ENTRUST_STATUS_REVOKE);
        entrustStatusList.add(VisitConstants.ENTRUST_STATUS_CANCEL);
        Long nowTime = Long.valueOf(DateUtil.formatDate(DateUtil.DATE_TIME, LocalDateTime.now()));
        Date beginTime = DateUtil.parseDateByPattern(startTime, DateUtil.HYPHEN_DATE_TIME);
        List<PrivatePlacementPo> privatePlacementPoList = privatePlacementMapper.selectInfo(
            ReviewChannelEnum.APP.getCode(), PrivateReviewStatusEnum.NO_BEGIN.getKey(), entrustStatusList, nowTime,
            beginTime, OpenDayFlagEnum.IN_OPEN_DAY.getCode());

        if (CollectionUtils.isEmpty(privatePlacementPoList)) {
            return ProcessResult.newSuccessResult("没有数据需要发起线上回访，程序结束！");
        }
        //4、组装各种数据
        //批量更新冷静期表集合
        ArrayList<Long> updatelist = new ArrayList<>();
        //客户批量发送短信集合
        List<MessageSendInVo> customerSmsList = new ArrayList<>();
        //客户经理批量发送短信集合
        List<MessageSendInVo> staffSmsList = new ArrayList<>();
        //批量发送融e通集合
        List<MessageBoxPushMessageVo> pushList = new ArrayList<>();
        //批量推送理财商城集合
        List<PrivatePlacementOnlineReviewPo> pushFinancialList = new ArrayList<>();

        //4.1 拿到所有的客户id
        List<String> customerIdList = privatePlacementPoList.stream()
            .map(PrivatePlacementPo::getCustomerId)
            .distinct()
            .collect(Collectors.toList());
        //获得所有的销售人员id
        List<String> staffIds = privatePlacementPoList.stream()
            .map(PrivatePlacementPo::getSalesmanId)
            .distinct()
            .filter(id -> id != null) // 过滤掉null的id
            .collect(Collectors.toList());
        //4.2 根据客户id查询客户信息,姓名和手机号
        List<CustomerViewVo> customerViewVoList = dcManager.quertCustomerList2(customerIdList);
        List<String> specialStaffIdList = idListConfigBean.getStaffIdList();
        staffIds.addAll(specialStaffIdList);
        List<String> staffIdList = staffIds.stream().distinct().collect(Collectors.toList());

        List<Staff> staffInfoList = staffManager.getStaffInfoList(staffIdList);
        //4.3 查询出来的客户信息组装成MAP
        Map<String, CustomerViewVo> customerMap = customerViewVoList.stream()
            .collect(Collectors.toMap(CustomerViewVo::getCustomerId, a -> a));
        Map<String, Staff> staffMap = staffInfoList.stream()
            .collect(Collectors.toMap(Staff::getEmpNO, a -> a));

        //5、循环私募数据
        for (PrivatePlacementPo privatePlacementPo : privatePlacementPoList) {
            String customerId = privatePlacementPo.getCustomerId();
            //5.1 拿到客户信息，拿不到报错
            CustomerViewVo customerViewVo = customerMap.get(customerId);
            if (null == customerViewVo) {
                throw new RuntimeException("该客户在中台查询不到相关信息！");
            }
            customerViewVo.setManagerId(privatePlacementPo.getStaffId());//该接口查询不到客户经理id
            customerViewVo.setOrganId(privatePlacementPo.getOrganId());
            //5.2 创建线上回访记录
            PrivatePlacementOnlineReviewPo onlineReviewPo = create(privatePlacementPo);

            //5.3 组装理财商城的数据
            pushFinancialList.add(onlineReviewPo);

            //5.4 添加要更新私募冷静期表状态的id
            updatelist.add(privatePlacementPo.getId());
            Map<String, String> pushFinancialToMap = new HashMap<>();
            pushFinancialToMap.put("客户姓名", customerViewVo.getCustomerName());
            pushFinancialToMap.put("产品名称", privatePlacementPo.getProdName());
            pushFinancialToMap.put("产品代码", privatePlacementPo.getProdCode());
            pushFinancialToMap.put("跳转链接", smsUrl);

            //5.5 组装客户短信消息
            sendSms(customerSmsList, pushFinancialToMap, customerViewVo, customerTitle, customerSmsMsgTemplateId,
                privatePlacementPo.getId());

            //5.6 组装客户经理的短信消息
            Map<String, String> staffToMap = new HashMap<>();
            staffToMap.put("客户姓名", customerViewVo.getCustomerName());
            staffToMap.put("产品名称", privatePlacementPo.getProdName());
            staffToMap.put("产品代码", privatePlacementPo.getProdCode());
            staffToMap.put("员工姓名", privatePlacementPo.getSalesman());
            staffToMap.put("回访任务的时间", DateUtil.formatDate(DateUtil.P_DATE_TIME, LocalDateTime.now()));
            CustomerViewVo vo = new CustomerViewVo();
            vo.setManagerId(privatePlacementPo.getSalesmanId());
            vo.setCustomerName(privatePlacementPo.getSalesman());
            vo.setOrganId("0");//总部
            vo.setMobile(privatePlacementPo.getSalesmanTel());
            vo.setCustomerId(privatePlacementPo.getSalesmanId());
            sendSms(staffSmsList, staffToMap, vo, staffTitle, staffSmsMsgTemplateId, privatePlacementPo.getId());
            //5.7 组装融e通消息实体
            MessageBoxPushMessageVo messageVo = CustomerReviewScanInfoAdapter.timedTaskScanPushMessage(customerId,
                privatePlacementPo.getFundAccount());
            messageVo.setAccountType("1");//1-资金账号
            messageVo.setMsgType("1");//消息类型：1-url
            messageVo.setPushType("6");
            messageVo.setParamMap(pushFinancialToMap);
            String jumpUrl = eUrl + onlineReviewPo.getId();//融e通跳转url
            messageVo.setMsgContent(jumpUrl);
            messageVo.setMsgTemplateId(eMsgTemplateId);
            messageVo.setPrivatePlacementId(privatePlacementPo.getId());
            pushList.add(messageVo);
        }
        customerSmsList.addAll(staffSmsList);
        //6、推送理财商城
        //6.1 推送理财商城
        List<PushFinancialDto> errorList = financialMallClient.createReview(pushFinancialList);
        log.info("errorList,{}", errorList);
        //定义推送理财商城失败，删除线上回访数据的集合
        List<Long> deleteList = new ArrayList<>();
        //定义推送理财商城失败，更新私募冷静期数据不在开放日
        List<Long> openDayFlagList = new ArrayList<>();
        //批量发送短信集合
        List<MessageSendInVo> errorSmsList = new ArrayList<>();
        //6.2 判断理财商城有没有报错
        if (CollectionUtils.isNotEmpty(errorList)) {
            //报错数据的私募主键id拿出来匹配，停止发送短信和融e通消息
            Set<Long> errorIds = new HashSet<>();
            for (PushFinancialDto pushFinancialDto : errorList) {
                errorIds.add(Long.valueOf(pushFinancialDto.getPrivatePlacementId()));
                updatelist.remove(Long.valueOf(pushFinancialDto.getPrivatePlacementId()));
                deleteList.add(Long.valueOf(pushFinancialDto.getPrivatePlacementOnlineReviewId()));
                //6.3 产品不在开放期或产品不存在
                if (errorNo.equals(String.valueOf(pushFinancialDto.getError())) || productErrorNo.equals(
                    String.valueOf(pushFinancialDto.getError()))) {
                    openDayFlagList.add(Long.valueOf(pushFinancialDto.getPrivatePlacementId()));
                }
                Map<String, String> pushErrorSmsToMap = new HashMap<>();
                pushErrorSmsToMap.put("客户名称", pushFinancialDto.getUserName());
                pushErrorSmsToMap.put("客户编号", pushFinancialDto.getCustomerId());
                pushErrorSmsToMap.put("产品代码", pushFinancialDto.getProductCode());
                pushErrorSmsToMap.put("产品名称", pushFinancialDto.getProductName());
                //6.4 错误的收集给指定的人员发送短信
                for (String staffId : specialStaffIdList) {
                    Staff staff = staffMap.get(staffId);
                    CustomerViewVo customerViewVo = new CustomerViewVo();
                    customerViewVo.setManagerId(staff.getEmpNO());
                    customerViewVo.setCustomerName(staff.getEmpName());
                    customerViewVo.setOrganId("0");//总部机构
                    customerViewVo.setMobile(staff.getPhone());
                    customerViewVo.setCustomerId(staff.getEmpNO());
                    sendSms(errorSmsList, pushErrorSmsToMap, customerViewVo,
                        errorTitle, errorSmsMsgTemplateId, null);
                }
            }
            //6.5 错误数据不发送融e通消息
            pushList.removeIf(item -> errorIds.contains(item.getPrivatePlacementId()));
            //6.6 错误数据不发送短信
            customerSmsList.removeIf(item -> errorIds.contains(item.getPrivatePlacementId()));
        }
        customerSmsList.addAll(errorSmsList);


        //7、更改私募冷静期表数据的状态为2回访中
        if (CollectionUtils.isNotEmpty(updatelist)) {
            privatePlacementMapper.updateBatch(updatelist, PrivateReviewStatusEnum.DOING.getKey(),
                null, PrivateReviewChannelEnum.APP.getKey(), PrivateReviewModeEnum.QUESTIONNAIRE.getKey());
        }
        //8、更新不在开放日的数据
        if (CollectionUtils.isNotEmpty(openDayFlagList)) {
            privatePlacementMapper.updateBatch(openDayFlagList, PrivateReviewStatusEnum.NO_BEGIN.getKey(),
                OpenDayFlagEnum.NOT_OPEN_DAY.getCode(), PrivateReviewChannelEnum.APP.getKey(),
                PrivateReviewModeEnum.QUESTIONNAIRE.getKey());
        }
        //9、将失败的线上回访记录删除
        if (CollectionUtils.isNotEmpty(deleteList)) {
            privatePlacementOnlineReviewDbManager.deleteBatchById(deleteList);
        }

        //10、批量发送短信
        if (CollectionUtils.isNotEmpty(customerSmsList)) {
            wmNoticeClient.newMessageSend(customerSmsList);
        }

        //11、批量发送融e通消息
        if (CollectionUtils.isNotEmpty(pushList)) {
            wmNoticeClient.privatePlacementPushMessage(pushList);
        }

        return ProcessResult.newSuccessResult("运行成功！");
    }

    */
/**
     * 发送短信
     *
     * @param voList           短信集合
     * @param paramMap         短信参数
     * @param customerViewVo   客户信息
     * @param title            信息标题
     * @param smsMsgTemplateId 信息模板id
     *//*

    public void sendSms(List<MessageSendInVo> voList, Map<String, String> paramMap,
        CustomerViewVo customerViewVo, String title, Long smsMsgTemplateId, Long privatePlacementId) {
        List<AddMsgReceiverVo> list = new ArrayList<>();

        AddMsgReceiverVo addMsgReceiverVo = new AddMsgReceiverVo();
        addMsgReceiverVo.setCustomerId(customerViewVo.getCustomerId());
        addMsgReceiverVo.setSendStatus("1");//未发送
        addMsgReceiverVo.setPhone(customerViewVo.getMobile());
        addMsgReceiverVo.setStaffId(customerViewVo.getManagerId());
        addMsgReceiverVo.setSendType("0");//0：客户 2：用户
        addMsgReceiverVo.setOrganId(customerViewVo.getOrganId());
        addMsgReceiverVo.setCustType("02");//01：潜在客户 02：普通客户
        addMsgReceiverVo.setCreateBy(VisitConstants.DEFAULT_OPERATOR);
        list.add(addMsgReceiverVo);

        AddMsgReceiverContantVo msgReceiverContantVo = new AddMsgReceiverContantVo();
        msgReceiverContantVo.setContentType("0");//0：文本
        msgReceiverContantVo.setTitle(title);
        msgReceiverContantVo.setVerifyType("3");//3：合规专员审核通过
        msgReceiverContantVo.setSendStatus("1");//发送状态 1:未发送
        msgReceiverContantVo.setMessageCreateTime(new Date());
        msgReceiverContantVo.setReviewId("1");
        msgReceiverContantVo.setSendNum(1);
        msgReceiverContantVo.setStaffId(customerViewVo.getManagerId());
        msgReceiverContantVo.setSendType("0");//0：客户 2：用户
        msgReceiverContantVo.setOrganId(customerViewVo.getOrganId());
        msgReceiverContantVo.setMsgType("0");//短信类型 0：普通短信 1：投资建议短信 2：邮件
        msgReceiverContantVo.setCreateBy(VisitConstants.DEFAULT_OPERATOR);

        MessageSendInVo vo = new MessageSendInVo();
        vo.setMsgReceiverContantVo(msgReceiverContantVo);
        vo.setMsgReceiverVos(list);
        vo.setMsgTemplateId(smsMsgTemplateId);
        vo.setParamMap(paramMap);
        vo.setPrivatePlacementId(privatePlacementId);
        voList.add(vo);
    }



    public String getDate(PrivatePlacementPo po) {
        Long tradeDate = po.getTradeDate();// 年月日
        Long currTime = po.getCurrTime();// 时分秒

        // 解析年月日
        int year = (int) (tradeDate / 10000);
        int month = (int) ((tradeDate % 10000) / 100);
        int day = (int) (tradeDate % 100);
        LocalDate date = LocalDate.of(year, month, day);

        // 解析时分秒（注意：分钟是MM，但在这里我们使用mm以避免与月份混淆）
        int hour = (int) (currTime / 10000);
        int minute = (int) ((currTime % 10000) / 100);
        int second = (int) (currTime % 100);
        LocalTime time = LocalTime.of(hour, minute, second);

        // 合并日期和时间
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // 格式化日期时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    //插入私募冷静期线上回访表
    public PrivatePlacementOnlineReviewPo create(PrivatePlacementPo po) {
        PrivatePlacementOnlineReviewPo onlineReviewPo = PrivatePlacementAdapter.toPrivatePlacementOnlineReviewPo(po);
        onlineReviewPo.setPrivatePlacementId(po.getId());
        onlineReviewPo.setReviewChannel(PrivateReviewChannelEnum.APP.getKey());
        onlineReviewPo.setReviewMode(PrivateReviewModeEnum.QUESTIONNAIRE.getKey());
        onlineReviewPo.setReviewStatus(PrivateReviewStatusEnum.DOING.getKey());
        ArrayList<PrivatePlacementPo> list = new ArrayList<>();
        list.add(po);
        onlineReviewPo.setTriggerInfo(privatePlacementService.getTriggerInfo(list));
        privatePlacementOnlineReviewDbManager.insertOnlineReviewPo(onlineReviewPo);
        return onlineReviewPo;
    }

}*/
