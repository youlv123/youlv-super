//package com.ruoyi.work;
//
//
//import cn.nesc.wm.opt.mot.enums.WxPushMessageTypeEnum;
//import cn.nesc.wm.opt.mot.infrustracture.oracle.mapper.T99AcalendarHrsMapper;
//import cn.nesc.wm.opt.mot.infrustracture.remote.DoingQueryManager;
//import cn.nesc.wm.opt.mot.infrustracture.remote.DcManager;
//import cn.nesc.wm.opt.mot.infrustracture.remote.WmNoticeManager;
//import cn.nesc.wm.opt.mot.infrustracture.remote.model.UsersInfoDto;
//import cn.nesc.wm.opt.mot.security.mapper.CrhCancelAccountMapper;
//import cn.nesc.wm.opt.mot.security.mapper.UserMapper;
//import cn.nesc.wm.opt.mot.security.po.UserPo;
//import cn.nesc.wm.opt.mot.service.model.*;
//import cn.nesc.wm.opt.mot.util.DateUtilTool;
//import cn.nesc.wm.opt.mot.web.model.CustomerViewVo;
//
//import com.tencent.cloud.task.sdk.client.model.ExecutableTaskData;
//import com.tencent.cloud.task.sdk.client.model.ProcessResult;
//import com.tencent.cloud.task.sdk.client.spi.ExecutableTask;
//import lombok.extern.slf4j.Slf4j;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//
//@Component
//@Slf4j
//public class CancelAccountJob implements ExecutableTask {
//
//
//    @Resource
//    private CrhCancelAccountMapper crhCancelAccountMapper;
//
//    @Resource
//    private T99AcalendarHrsMapper t99AcalendarHrsMapper;
//
//    @Value("${template.FLAG1}")
//    private Long FLAG1;
//    @Value("${template.FLAG2}")
//    private Long FLAG2;
//    @Value("${template.FLAG3}")
//    private Long FLAG3;
//    @Value("${template.FLAG22}")
//    private Long FLAG22;
//
//    @Value("${role.CHIEF_ROLE_ID}")
//    private String chiefRoleId;
//
//    @Value("${role.GENERAL_MANAGER_ROLE_ID}")
//    private String generalManagerRoleId;
//
//    //大额销户配置
//    @Value("${workflow.closeAccount.bigWorkFlow.bigWorkFlowCode}")
//    private String bigWorkFlowCode;
//    @Value("${workflow.closeAccount.bigWorkFlow.bigSheetCode}")
//    private String bigSheetCode;
//    @Value("${workflow.closeAccount.bigWorkFlow.bigSchemaCode}")
//    private String bigSchemaCode;
//
//    //一般销户配置
//    @Value("${workflow.closeAccount.normalWorkFlow.normalWorkFlowCode}")
//    private String normalWorkFlowCode;
//    @Value("${workflow.closeAccount.normalWorkFlow.normalSheetCode}")
//    private String normalSheetCode;
//    @Value("${workflow.closeAccount.normalWorkFlow.normalSchemaCode}")
//    private String normalSchemaCode;
//
//    @Resource
//    private WmNoticeManager wmNoticeManager;
//    @Resource
//    private DcManager dcManager;
//    @Resource
//    private UserMapper userMapper;
//    @Resource
//    private DoingQueryManager doingQueryManager;
//    //额度
//    private static final BigDecimal MaxAsset = new BigDecimal("300000.00");
//    private static final String weixinSendFlag = "0";
//
//    @Override
//    public ProcessResult execute(ExecutableTaskData taskData) {
//
//        //1、判断今天是不是交易日
//        String today = DateUtilTool.formatDate(DateUtilTool.BASIC_ISO_DATE, LocalDateTime.now());
//        String bizDate = t99AcalendarHrsMapper.selectBizDate(today);
//        if (!today.equals(bizDate)) {
//            log.info("=== 今天[{}]不是交易，不进行处理", today);
//            // 设置任务执行状态： 执行成功
//            return ProcessResult.newSuccessResult();
//        }
//        //2、查询T_CRH_CANCEL2_ACCOUNT，微信发送标识为0的数据
//        List<CrhCancelAccountDto> crhCloseAccountDtoList = crhCancelAccountMapper.selectInfo(weixinSendFlag);
//        //3、循环发起流程
//        if (CollectionUtils.isNotEmpty(crhCloseAccountDtoList)) {
//            // 3.1收集所有的客户号
//            List<String> customerIdList = crhCloseAccountDtoList.stream()
//                    .map(CrhCancelAccountDto::getCustomerId)
//                    .collect(Collectors.toList());
//            // 3.2查询中台拿到客户的机构和客户经理信息
//            List<CustomerViewVo> customerViewVoList = dcManager.quertCustomerList2(customerIdList);
//            //3.3组装成map，方便后面查询微信推送人
//            Map<String, CustomerViewVo> customerMap = customerViewVoList.stream()
//                    .collect(Collectors.toMap(CustomerViewVo::getCustomerId, Function.identity()));
//            //待发送微信消息集合
//            List<WxMsgSendDto> adminList = new ArrayList<>();
//            for (CrhCancelAccountDto crhCancelAccountDto : crhCloseAccountDtoList) {
//
//                if ("2".equals(crhCancelAccountDto.getFlag())) {
//                    //判断要走哪个销户流程
//                    String workFlowDefId = null;
//                    //开户至今最高资产不为空，并且产大于等于30万，走大额销户流程
//                    CloseAccountDto closeAccountDto = new CloseAccountDto();
//                    SubmitWorkflowDto submitWorkflowDto = new SubmitWorkflowDto();
//                    Bizobject bizobject = new Bizobject();
//                    if (StringUtils.isNotBlank(crhCancelAccountDto.getMaxAsset()) && MaxAsset.compareTo(new BigDecimal(crhCancelAccountDto.getMaxAsset())) < 0) {
//                        submitWorkflowDto.setWorkflowCode(bigWorkFlowCode);
//                        bizobject.setSheetCode(bigSheetCode);
//                        bizobject.setSchemaCode(bigSchemaCode);
//                        crhCancelAccountDto.setWorkFlowCode(bigWorkFlowCode);
//                    } else {//走一般流程
//                        submitWorkflowDto.setWorkflowCode(normalWorkFlowCode);
//                        bizobject.setSheetCode(normalSheetCode);
//                        bizobject.setSchemaCode(normalSchemaCode);
//                        crhCancelAccountDto.setWorkFlowCode(normalWorkFlowCode);
//                    }
//
//                    closeAccountDto.setRequestNo(crhCancelAccountDto.getRequestNo());
//                    closeAccountDto.setFundAccount(crhCancelAccountDto.getFundAccount());
//                    closeAccountDto.setMaxAsset(crhCancelAccountDto.getMaxAsset());
//                    closeAccountDto.setCustomerId(crhCancelAccountDto.getCustomerId());
//                    closeAccountDto.setCustomerName(crhCancelAccountDto.getCustomerName());
//                    CustomerViewVo customerViewVo = customerMap.get(crhCancelAccountDto.getCustomerId());
//                    if (null != customerViewVo) {
//                        closeAccountDto.setOrganId(customerViewVo.getOrganId());
//                    }
//
//                    closeAccountDto.setRoleId(null);
//                    closeAccountDto.setOpenDate(crhCancelAccountDto.getOpenDate());
//                    closeAccountDto.setDealEndDate(crhCancelAccountDto.getDealEndDate());
//                    closeAccountDto.setCancelType(crhCancelAccountDto.getCancelType());
//                    closeAccountDto.setCancelAccount(crhCancelAccountDto.getCancelAccount());
//                    closeAccountDto.setCancelReason(crhCancelAccountDto.getCancelReason());
//                    closeAccountDto.setStatusCode(null);
//                    closeAccountDto.setId(crhCancelAccountDto.getId().toString());
//                    closeAccountDto.setRemake(null);
//
//
//                    bizobject.setData(closeAccountDto);
//                    submitWorkflowDto.setBizObject(bizobject);
////                submitWorkflowDto.setReplayToken(replyToken);
////                String workflowInstanceId = SubmitService.submitWorkflow(submitWorkflowDto, accessToken);
//
////                crhCloseAccountDto.setWorkFlowInstanceId(workflowInstanceId);
//                    //更新流程信息
//                    crhCancelAccountMapper.updateCrhCancelAccount(crhCancelAccountDto);
//                }
//                    WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
//                    Map<String, String> sendMap = new HashMap<>();
//
//                //获取微信推送人
//                String approverPeople = selectApproverPeople(crhCancelAccountDto, customerMap);
//                if ("1".equals(crhCancelAccountDto.getFlag())) {
//                    sendMap = parseStaffDataToMap(crhCancelAccountDto);
//                    wxMsgSendDto.setMessageId(FLAG1);
//                    wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_FLAG1.getCode());
//                } else if ("2".equals(crhCancelAccountDto.getFlag())) {
//                    //flag为2的，增加总经理推送
//                    String manager = selectManager(crhCancelAccountDto, customerMap);
//                    wxMsgSendDto.setUserId(manager);
//                    wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_FLAG22.getCode());
//                    wxMsgSendDto.setMessageId(FLAG22);
//                    sendMap = parseChiefDataToMap(crhCancelAccountDto);
//                    wxMsgSendDto.setData(sendMap);
//                    adminList.add(wxMsgSendDto);
//                    //普通人员推送
//                    wxMsgSendDto.setMessageId(FLAG2);
//                    wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_FLAG2.getCode());
//                } else if ("3".equals(crhCancelAccountDto.getFlag())) {
//                    sendMap = parseRetailDataToMap(crhCancelAccountDto);
//                    wxMsgSendDto.setMessageId(FLAG3);
//                    wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_FLAG3.getCode());
//                }
//                wxMsgSendDto.setUserId(approverPeople);
//                wxMsgSendDto.setData(sendMap);
//                adminList.add(wxMsgSendDto);
//            }
//            //6.2调用notice发送微信消息，五分钟跑一次，一般就几条数据
//            wmNoticeManager.sendMessageToWx(adminList);
//        }
//        return ProcessResult.newSuccessResult();
//    }
//
//
//*
//     * 查询客户经理/财富总监/零售业务部门
//     * 查询审批人
//     *
//     * @return
//
//
//    private String selectApproverPeople(CrhCancelAccountDto dto, Map<String, CustomerViewVo> customerMap) {
//        //默认是95360
//        String approverPeople = "95360";
//        //先看中台是否查询有值
//        if (null != customerMap.get(dto.getCustomerId())) {
//            CustomerViewVo customerViewVo = customerMap.get(dto.getCustomerId());
//            //如果客户经理不为空，就返回客户经理
//            if (StringUtils.isNotBlank(customerViewVo.getManagerId())) {
//                approverPeople = customerViewVo.getManagerId();
//            } else {
//                //客户经理没有，客户有机构，就去查机构的财富总监id
//                if (StringUtils.isNotBlank(customerViewVo.getOrganId())) {
//                    QueryUsersInfoDto queryUsersInfoDto = new QueryUsersInfoDto();
//                    queryUsersInfoDto.setOrgId(customerViewVo.getOrganId());
//                    queryUsersInfoDto.setRoleId(chiefRoleId);
//                    //查询财富总监
//                    List<UsersInfoDto> list = doingQueryManager.getApproverByCustomerId(queryUsersInfoDto);
//                    //财富总监存在就返回财富总监
//                    if (CollectionUtils.isNotEmpty(list) && StringUtils.isNotBlank(list.get(0).getUserId())) {
//                        approverPeople = list.get(0).getUserId();
//                    }
//                }
//            }
//        }
//        return approverPeople;
//    }
//
//*
//     * 查询客户所在机构的总经理
//     *
//     * @param dto
//     * @param customerMap
//     * @return
//
//
//    private String selectManager(CrhCancelAccountDto dto, Map<String, CustomerViewVo> customerMap) {
//        //默认是95360
//        String approverPeople = "95360";
//        //中台查询该客户有值
//        if (null != customerMap.get(dto.getCustomerId())) {
//            CustomerViewVo customerViewVo = customerMap.get(dto.getCustomerId());
//            //看客户机构是否有值
//            if (StringUtils.isNotBlank(customerViewVo.getOrganId())) {
//                //查询机构总经理
//                List<UserPo> userPoList = userMapper.selectInfoByOrganId(generalManagerRoleId, customerViewVo.getOrganId());
//                //总经理存在就返回总经理id
//                if (CollectionUtils.isNotEmpty(userPoList) && StringUtils.isNotBlank(userPoList.get(0).getUid())) {
//                    approverPeople = userPoList.get(0).getUid();
//                }
//            }
//        }
//        return approverPeople;
//    }
//
//*
//     * 分割拼接类型数据，组装成map
//     * Flag=1
//     * 您好！您有一个客户要销户，资金账号：XXX，客户姓名：XXX，开户日期：XXX，开户至今最高资产：XXX，
//     * 经纪关系: XXX，投资顾问: XXX，
//     * 有销户申请，请及时关注。
//     *
//     * @param dto
//     * @return
//
//
//    public Map<String, String> parseStaffDataToMap(CrhCancelAccountDto dto) {
//        if (null == dto) {
//            return null;
//        }
//        Map<String, String> map = new HashMap<>();
//        map.put("资金账号", dto.getFundAccount());
//        map.put("客户姓名", dto.getCustomerName());
//        map.put("开户日期", dto.getOpenDate());
//        map.put("开户至今最高资产", dto.getMaxAsset());
//        String relation = "";
//        if (StringUtils.isNotBlank(dto.getAgentName())) {
//            relation = relation + "经纪关系" + dto.getAgentName() + "，";
//        }
//        if (StringUtils.isNotBlank(dto.getInvestmentAdviserName())) {
//            relation = relation + "投资顾问" + dto.getInvestmentAdviserName() + "，";
//        }
//        map.put("经纪关系相关数据", relation);
//        return map;
//    }
//
//*
//     * 分割拼接类型数据，组装成map
//     * 组装总经理数据
//     * Flag=2  总经理
//     * 您好！您有一个客户要销户，资金账号：XXX，客户姓名：XXX，开户日期：XXX，数据处理截止时间:XXX，开户至今最高资产：XXX，账户销户类型：XXX，销户账户：XXX，销户原因：XXX,
//     * 经纪关系: XXX，投资顾问: XXX，
//     * 有销户流程，请及时到MOT系统处理。
//     *
//     * @param dto
//     * @return
//
//
//    public Map<String, String> parseChiefDataToMap(CrhCancelAccountDto dto) {
//        if (null == dto) {
//            return null;
//        }
//        Map<String, String> map = new HashMap<>();
//        map.put("资金账号", dto.getFundAccount());
//        map.put("客户姓名", dto.getCustomerName());
//        map.put("开户日期", dto.getOpenDate());
//        map.put("数据处理截止时间", dto.getOpenDate());
//        map.put("开户至今最高资产", dto.getMaxAsset());
//        map.put("账户销户类型", dto.getCancelType());
//        map.put("销户账户", dto.getCancelAccount());
//        map.put("销户原因", dto.getCancelReason());
//        String relation = "";
//        if (StringUtils.isNotBlank(dto.getAgentName())) {
//            relation = relation + "经纪关系" + dto.getAgentName() + "，";
//        }
//        if (StringUtils.isNotBlank(dto.getInvestmentAdviserName())) {
//            relation = relation + "投资顾问" + dto.getInvestmentAdviserName() + "，";
//        }
//        map.put("经纪关系相关数据", relation);
//        return map;
//    }
//
//*
//     * 分割拼接类型数据，组装成map
//     * Flag=3
//     * 您好！您有一个客户要销户，资金账号：XXX，客户姓名：XXX，开户日期：XXX，开户至今最高资产：XXX，执行预销户时间：XXX，
//     * 经纪关系: XXX，投资顾问: XXX，
//     * 请及时关注和跟进，做好客户挽留工作。
//     *
//     * @param dto
//     * @return
//
//
//    public Map<String, String> parseRetailDataToMap(CrhCancelAccountDto dto) {
//        if (null == dto) {
//            return null;
//        }
//        Map<String, String> map = new HashMap<>();
//        map.put("资金账号", dto.getFundAccount());
//        map.put("客户姓名", dto.getCustomerName());
//        map.put("开户日期", dto.getOpenDate());
//        map.put("数据处理截止时间", dto.getOpenDate());
//        map.put("开户至今最高资产", dto.getMaxAsset());
//        map.put("执行预销户时间", dto.getPreCancelDate().toString());
//        String relation = "";
//        if (StringUtils.isNotBlank(dto.getAgentName())) {
//            relation = relation + "经纪关系" + dto.getAgentName() + "，";
//        }
//        if (StringUtils.isNotBlank(dto.getInvestmentAdviserName())) {
//            relation = relation + "投资顾问" + dto.getInvestmentAdviserName() + "，";
//        }
//        map.put("经纪关系相关数据", relation);
//        return map;
//    }
//}
