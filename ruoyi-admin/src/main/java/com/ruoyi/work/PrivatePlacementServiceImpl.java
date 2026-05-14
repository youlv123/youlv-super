/*
package com.ruoyi.work;

import cn.nesc.wm.visit.controller.adapter.PrivatePlacementAdapter;
import cn.nesc.wm.visit.controller.model.AddMsgReceiverContantVo;
import cn.nesc.wm.visit.controller.model.AddMsgReceiverVo;
import cn.nesc.wm.visit.controller.model.CustomerViewVo;
import cn.nesc.wm.visit.controller.model.MessageSendInVo;
import cn.nesc.wm.visit.controller.model.PrivatePlacementStatusVo;
import cn.nesc.wm.visit.controller.model.QueryTcustVo;
import cn.nesc.wm.visit.enums.DaFlagEnum;
import cn.nesc.wm.visit.enums.OpenDayFlagEnum;
import cn.nesc.wm.visit.enums.PrivateReviewChannelEnum;
import cn.nesc.wm.visit.enums.PrivateReviewModeEnum;
import cn.nesc.wm.visit.enums.PrivateReviewStatusEnum;
import cn.nesc.wm.visit.enums.ReviewStatusEnum;
import cn.nesc.wm.visit.infrustracture.db.DataDictManager;
import cn.nesc.wm.visit.infrustracture.db.PrivatePlacementDbManager;
import cn.nesc.wm.visit.infrustracture.db.PrivatePlacementOnlineReviewDbManager;
import cn.nesc.wm.visit.infrustracture.db.mapper.CustomerReviewInfoMapper;
import cn.nesc.wm.visit.infrustracture.db.mapper.PrivatePlacementCustomerReviewMapper;
import cn.nesc.wm.visit.infrustracture.db.mapper.PrivatePlacementMapper;
import cn.nesc.wm.visit.infrustracture.db.mapper.PrivatePlacementOnlineReviewMapper;
import cn.nesc.wm.visit.infrustracture.db.model.CustomerReviewJobPo;
import cn.nesc.wm.visit.infrustracture.db.model.PrivatePlacementOnlineReviewPo;
import cn.nesc.wm.visit.infrustracture.db.model.PrivatePlacementPo;
import cn.nesc.wm.visit.infrustracture.oracle.mapper.TClientreviewInfoMapper;
import cn.nesc.wm.visit.infrustracture.oracle.mapper.TSmLjqhfMapper;
import cn.nesc.wm.visit.infrustracture.oracle.model.TClientreviewInfoPo;
import cn.nesc.wm.visit.infrustracture.remote.CallCenterManager;
import cn.nesc.wm.visit.infrustracture.remote.DcManager;
import cn.nesc.wm.visit.infrustracture.remote.FinancialMallClient;
import cn.nesc.wm.visit.infrustracture.remote.StaffManager;
import cn.nesc.wm.visit.infrustracture.remote.WmNoticeClient;
import cn.nesc.wm.visit.service.model.CallCenterReviewDTO;
import cn.nesc.wm.visit.service.model.CreatePrivateTelReviewDto;
import cn.nesc.wm.visit.service.model.CustomerReviewInfoDto;
import cn.nesc.wm.visit.service.model.IdListConfigBean;
import cn.nesc.wm.visit.service.model.OnlineReviewEntity;
import cn.nesc.wm.visit.service.model.PrivatePlacementDto;
import cn.nesc.wm.visit.service.model.PrivatePlacementEntity;
import cn.nesc.wm.visit.service.model.PrivatePlacementInfoDto;
import cn.nesc.wm.visit.service.model.PrivatePlacementOracleDto;
import cn.nesc.wm.visit.service.model.PrivatePlacementStatusDto;
import cn.nesc.wm.visit.service.model.PrivateReviewChannelChangeDto;
import cn.nesc.wm.visit.service.model.ProdSaleCommissionerDto;
import cn.nesc.wm.visit.service.model.PushFinancialDto;
import cn.nesc.wm.visit.service.model.QueryOnlineReviewDto;
import cn.nesc.wm.visit.service.model.QueryPrivatePlacementDto;
import cn.nesc.wm.visit.service.model.Staff;
import cn.nesc.wm.visit.service.model.UpdatePrivatePlacementComplianceDto;
import cn.nesc.wm.visit.util.DateUtil;
import cn.nesc.wm.visit.util.TaskConstants;
import cn.nesc.wm.visit.util.VisitConstants;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import test.common.exception.ExceptionUtil;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PrivatePlacementServiceImpl implements PrivatePlacementService {

    private static final String COMMA = ",";

    private static final String SEMICOLON = ";";

    //产品不在开放期
    private final static String errorNo = "-2";

    //产品不存在
    private final static String productErrorNo = "-3";

    private final static String errorTitle = "在线问卷异常业务消息提醒";

    */
/**
     * 标识是否需要调用外呼接口直接创建回访
     * true：需要
     * false：不需要
     *//*

    @Value("${app.useOutboundInterface}")
    private boolean useOutboundInterface;

    @Resource
    private DcManager dcManager;

    @Resource
    private PrivatePlacementOnlineReviewDbManager privatePlacementOnlineReviewDbManager;

    @Resource
    private PrivatePlacementOnlineReviewMapper onlineReviewMapper;

    @Resource
    private PrivatePlacementDbManager privatePlacementDbManager;

    @Resource
    private DataDictManager dataDictManager;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private PrivatePlacementMapper privatePlacementMapper;

    @Resource
    private TSmLjqhfMapper tSmLjqhfMapper;

    @Resource
    private CallCenterManager callCenterManager;

    @Resource
    private PrivatePlacementCustomerReviewMapper privatePlacementCustomerReviewMapper;

    @Resource
    private CustomerReviewInfoMapper customerReviewInfoMapper;

    @Resource
    private FinancialMallClient financialMallClient;

    @Resource
    private StaffManager staffManager;

    @Resource
    private WmNoticeClient wmNoticeClient;

    @Resource
    private IdListConfigBean idListConfigBean;

    @Value("${privatePlacement.createBizCode}")
    private String createBizCode;

    @Value("${privatePlacement.errorSmsMsgTemplateId}")
    private Long errorSmsMsgTemplateId;

    @Resource
    private TClientreviewInfoMapper tClientreviewInfoMapper;

    */
/**
     * 线上回访转人工外呼
     *//*

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class,
        transactionManager = "mysqlTrans")
    public String setManualService(PrivateReviewChannelChangeDto dto) {
        // 参数校验
        dto.validate();
        // 1、查询私募回访
        Long id = dto.getId();
        PrivatePlacementPo privatePlacementPo = privatePlacementDbManager.selectById(id);
        if (privatePlacementPo == null) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_REVIEW_ABSENT);
        }

        String reviewStatus = privatePlacementPo.getReviewStatus();
        if (!PrivateReviewStatusEnum.NO_BEGIN.getKey().equals(reviewStatus) &&
            !PrivateReviewStatusEnum.DOING.getKey().equals(reviewStatus)) {
            // 当前冷静期回访的回访状态不是 未回访 和 回访中，报错
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_REVIEW_CHANNEL_CHANGE);
        }

        String reviewChannel = privatePlacementPo.getReviewChannel();
        String reviewMode = privatePlacementPo.getReviewMode();
        if (!PrivateReviewChannelEnum.APP.getKey()
            .equals(reviewChannel) || !PrivateReviewModeEnum.QUESTIONNAIRE.getKey().equals(reviewMode)) {
            // 当前冷静期回访不是线上问券回访，直接报错
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_REVIEW_CHANNEL_CHANGE);
        }

        if (PrivateReviewStatusEnum.NO_BEGIN.getKey().equals(reviewStatus)) {
            // 未回访,直接修改冷静期回访即可
            privatePlacementPo.setReviewChannel(PrivateReviewChannelEnum.MANUAL_OUTBOUND_CALL.getKey());
            privatePlacementPo.setReviewMode(PrivateReviewModeEnum.TELEPHONE.getKey());
            privatePlacementPo.updateDefault(dto.getOperator());
            privatePlacementMapper.updateById(privatePlacementPo);
            return id.toString();
        }

        // 3、回访中
        // 先查下私募在线回访表
        // 理论上不应该为空，但是由于允许多次变更回访渠道，所以可能查到多条
        List<PrivatePlacementOnlineReviewPo> onlineReviewPos =
            privatePlacementOnlineReviewDbManager.selectByPrivatePlacementId(id);
        if (CollectionUtils.isEmpty(onlineReviewPos)) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_PLACEMENT_ONLINE_REVIEW_ABSENT);
        }

        List<PrivatePlacementOnlineReviewPo> toCancelPos = new ArrayList<>();
        List<Long> onlineReviewIds = new ArrayList<>();
        for (PrivatePlacementOnlineReviewPo po : onlineReviewPos) {
            if (PrivateReviewStatusEnum.DOING.getKey().equals(po.getReviewStatus())) {
                // 按照“已完成”登记回访状态，按照“其他”登记回访结果
                // 更新私募在线回访的回访状态为已回访，回访结果为其他
                po.setReviewResult("其他");
                po.setReviewTime(new Date());
                po.setUpdateBy(dto.getOperator());
                po.setReviewStatus(PrivateReviewStatusEnum.FINISHED.getKey());
                toCancelPos.add(po);

                onlineReviewIds.add(po.getId());
            }
        }
        if (CollectionUtils.isEmpty(toCancelPos)) {
            // 理论上不应该为空
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_PLACEMENT_ONLINE_REVIEW_ABSENT);
        }
        privatePlacementOnlineReviewDbManager.updateOnlineReviews(toCancelPos);

        // 4、调用理财商城接口，设置问卷失效
        // 调用理财商城取消问卷
        List<String> errorList = financialMallClient.cancleReview(onlineReviewIds);
        // 返回错误消息代表取消失败
        if (CollectionUtils.isNotEmpty(errorList)) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_CANCLE_REVIEW);
        }

        // 5、创建私募冷静期外呼回访并更新回访状态和回访渠道
        createPrivatePlacementTelReview(Collections.singletonList(privatePlacementPo));

        return id.toString();
    }

    */
/**
     * 查询私募回访明细
     *//*

    @Override
    public List<PrivatePlacementEntity> queryPrivateReviewDetail(Long id) {
        if (Objects.isNull(id)) {
            throw ExceptionUtil.createException(TaskConstants.ERROR_PARAM_OBJECT_NOT_NULL);
        }
        return privatePlacementDbManager.queryPrivateReviewDetail(id);
    }

    @Override
    public PageInfo<OnlineReviewEntity> queryPrivatePlacementReviewByOnline(QueryOnlineReviewDto dto) {
        if (Objects.isNull(dto)) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_PARAM_OBJECT_NOT_NULL);
        }
        //执行参数校验
        dto.validate();
        return privatePlacementDbManager.selectPrivatePlacementReviewInfoByOnline(dto);
    }

    private void onlineTransferChecks(List<PrivatePlacementPo> pos) {
        pos.forEach(po -> onlineTransferCheck(po));
    }

    private void onlineTransferCheck(PrivatePlacementPo po) {
        String reviewStatus = po.getReviewStatus();
        if (!PrivateReviewStatusEnum.NO_BEGIN.getKey().equals(reviewStatus) &&
            !PrivateReviewStatusEnum.DOING.getKey().equals(reviewStatus)) {
            // 当前冷静期回访的回访状态不是 未回访 和 回访中，报错
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_REVIEW_CHANNEL_CHANGE);
        }

        String reviewChannel = po.getReviewChannel();
        String reviewMode = po.getReviewMode();
        if (!PrivateReviewChannelEnum.MANUAL_OUTBOUND_CALL.getKey()
            .equals(reviewChannel) || !PrivateReviewModeEnum.TELEPHONE.getKey().equals(reviewMode)) {
            // 当前冷静期回访不是线上问券回访，直接报错
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_REVIEW_CHANNEL_CHANGE);
        }
    }

    private List<PrivatePlacementOnlineReviewPo> createOnlineReviews(List<PrivatePlacementPo> pos, String operator) {
        List<PrivatePlacementOnlineReviewPo> onlineReviewPos = pos.stream().map(po -> createOnlineReview(po, operator))
            .collect(Collectors.toList());
        onlineReviewMapper.insertBatch(onlineReviewPos);
        return onlineReviewPos;
    }

    private PrivatePlacementOnlineReviewPo createOnlineReview(PrivatePlacementPo po, String operator) {
        PrivatePlacementOnlineReviewPo onlineReviewPo = PrivatePlacementAdapter.toPrivatePlacementOnlineReviewPo(po);
        onlineReviewPo.setPrivatePlacementId(po.getId());
        onlineReviewPo.setReviewChannel(PrivateReviewChannelEnum.APP.getKey());
        onlineReviewPo.setReviewMode(PrivateReviewModeEnum.QUESTIONNAIRE.getKey());
        onlineReviewPo.setReviewStatus(PrivateReviewStatusEnum.DOING.getKey());
        onlineReviewPo.createDefault(operator);
        String triggerInfo = getTriggerInfo(Collections.singletonList(po));
        onlineReviewPo.setTriggerInfo(triggerInfo);
        return onlineReviewPo;
    }

    */
/**
     * 人工外呼转线上回访
     *//*

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class,
        transactionManager = "mysqlTrans")
    public String setQuestionnaire(PrivateReviewChannelChangeDto dto) {
        // 参数校验
        dto.validate();
        // 1、查询私募回访
        Long id = dto.getId();
        String operator = dto.getOperator();
        PrivatePlacementPo privatePlacementPo = privatePlacementMapper.selectById(id);
        if (privatePlacementPo == null) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_REVIEW_ABSENT);
        }
        // 2、检查当前的冷静期回访状态
        this.onlineTransferCheck(privatePlacementPo);

        if (PrivateReviewStatusEnum.NO_BEGIN.getKey().equals(privatePlacementPo.getReviewStatus())) {
            // 未回访状态
            privatePlacementPo.setReviewChannel(PrivateReviewChannelEnum.APP.getKey());
            privatePlacementPo.setReviewMode(PrivateReviewModeEnum.QUESTIONNAIRE.getKey());
            privatePlacementPo.updateDefault(dto.getOperator());
            privatePlacementMapper.updateById(privatePlacementPo);
            return id.toString();
        }

        // 回访中状态
        // 3、根据私募id去查询外呼id,查询私募外呼关联表
        Long customerReviewInfoId = privatePlacementCustomerReviewMapper.selectCustomerReviewInfoId(id);
        if (null == customerReviewInfoId) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_SEARCHER_ID);
        }
        // 4、通过主键去查回访编号，目前做的回访编号是老的，采用的主键还是oracle的主键
        Long customerReviewId = customerReviewInfoMapper.selectCustomerReviewIdById(customerReviewInfoId);
        if (null == customerReviewId) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_SEARCHER_ID);
        }
        // 查询外呼关联的多个冷静期回访
        List<Long> privatePlacementIds = privatePlacementCustomerReviewMapper.selectPrivatePlacementIds(
            customerReviewInfoId);
        List<PrivatePlacementPo> privatePlacementPos = privatePlacementMapper.selectByIds(privatePlacementIds);
        // 检查外呼关联的多个冷静期回访状态
        this.onlineTransferChecks(privatePlacementPos);

        // 5、数据库创建线上问卷
        List<PrivatePlacementOnlineReviewPo> onlineReviews = this.createOnlineReviews(privatePlacementPos, operator);

        //6、调用理财商城接口新增问卷回访
        List<PushFinancialDto> errorList = financialMallClient.createReview(onlineReviews);
        log.info("errorList,{}", errorList);
        //定义推送理财商城失败，删除线上回访数据的集合
        List<Long> deleteList = new ArrayList<>();
        //定义推送理财商城失败，更新私募冷静期数据不在开放日
        List<Long> openDayFlagList = new ArrayList<>();
        //批量发送短信集合
        List<MessageSendInVo> errorSmsList = new ArrayList<>();
        //6.3 判断理财商城有没有报错
        if (CollectionUtils.isNotEmpty(errorList)) {
            //报错数据的私募主键id拿出来匹配，停止发送短信和融e通消息
            Set<Long> errorIds = new HashSet<>();
            for (PushFinancialDto pushFinancialDto : errorList) {
                errorIds.add(Long.valueOf(pushFinancialDto.getPrivatePlacementId()));
                deleteList.add(Long.valueOf(pushFinancialDto.getPrivatePlacementOnlineReviewId()));
                //6.4 产品不在开放期或产品不存在
                if (errorNo.equals(String.valueOf(pushFinancialDto.getError())) || productErrorNo.equals(
                    String.valueOf(pushFinancialDto.getError()))) {
                    openDayFlagList.add(Long.valueOf(pushFinancialDto.getPrivatePlacementId()));
                }
                Map<String, String> pushErrorSmsToMap = new HashMap<>();
                pushErrorSmsToMap.put("客户名称", pushFinancialDto.getUserName());
                pushErrorSmsToMap.put("客户编号", pushFinancialDto.getCustomerId());
                pushErrorSmsToMap.put("产品代码", pushFinancialDto.getProductCode());
                pushErrorSmsToMap.put("产品名称", pushFinancialDto.getProductName());
                List<String> specialStaffIdList = idListConfigBean.getStaffIdList();
                List<Staff> staffInfoList = staffManager.getStaffInfoList(specialStaffIdList);
                Map<String, Staff> staffMap = staffInfoList.stream().collect(Collectors.toMap(Staff::getEmpNO, a -> a));
                //6.5 错误的收集给指定的人员发送短信
                for (String staffId : specialStaffIdList) {
                    Staff staff = staffMap.get(staffId);
                    CustomerViewVo customerViewVo = new CustomerViewVo();
                    customerViewVo.setManagerId(staff.getEmpNO());
                    customerViewVo.setCustomerName(staff.getEmpName());
                    customerViewVo.setOrganId("0");//总部机构
                    customerViewVo.setMobile(staff.getPhone());
                    customerViewVo.setCustomerId(staff.getEmpNO());
                    sendSms(errorSmsList, pushErrorSmsToMap, customerViewVo, errorTitle, errorSmsMsgTemplateId, null);
                }
            }

        }
        //7、批量发送异常短信
        if (CollectionUtils.isNotEmpty(errorSmsList)) {
            wmNoticeClient.newMessageSend(errorSmsList);
        }
        //8、更新不在开放日的数据
        if (CollectionUtils.isNotEmpty(openDayFlagList)) {
            privatePlacementMapper.updateBatch(openDayFlagList, PrivateReviewStatusEnum.DOING.getKey(),
                OpenDayFlagEnum.NOT_OPEN_DAY.getCode(), PrivateReviewChannelEnum.MANUAL_OUTBOUND_CALL.getKey(),
                PrivateReviewModeEnum.TELEPHONE.getKey());
        }
        //9、将失败的线上回访记录删除
        if (CollectionUtils.isNotEmpty(deleteList)) {
            privatePlacementOnlineReviewDbManager.deleteBatchById(deleteList);
        }
        //10、调用理财商城正常的再关闭外呼并更新渠道等数据
        if (CollectionUtils.isEmpty(errorSmsList)) {
            //组装关闭外呼的信息
            List<CallCenterReviewDTO> list = new ArrayList<>();
            CallCenterReviewDTO callCenterReviewDTO = new CallCenterReviewDTO();
            callCenterReviewDTO.setSeq_no(customerReviewId);
            callCenterReviewDTO.setDa_flag(DaFlagEnum.CANCEL_REVIEW.getCode());
            list.add(callCenterReviewDTO);
            //10.1 调用外呼，关闭回访，外呼失败，回访以及成功？？？，调用的时候用新的还是老的编号
            List<CallCenterReviewDTO> callCenterReviewList = callCenterManager.callCenterReview(list);
            if (CollectionUtils.isNotEmpty(callCenterReviewList)) {
                throw ExceptionUtil.createException(VisitConstants.ERROR_CLOSE_REVIEW);
            }
            //10.2 更新customer_review_info回访信息表的回访状态为已回访，回访结果为其他
            CustomerReviewInfoDto customerReviewInfoDto = new CustomerReviewInfoDto();
            customerReviewInfoDto.setCustomerReviewId(customerReviewId);
            customerReviewInfoDto.setReviewStatus(ReviewStatusEnum.HAVE_REVIEW.getCode());
            customerReviewInfoDto.setReviewResult("其他");
            customerReviewInfoDto.setReviewResult2("4");
            customerReviewInfoDto.setReviewTime(new Date());
            customerReviewInfoMapper.updateCustomerReviewIdById(customerReviewInfoDto);
            //10.3 查询oracle，同步更新
            TClientreviewInfoPo tClientreviewInfoPo = tClientreviewInfoMapper.selectByCustomerReviewId(
                customerReviewId);
            if (null != tClientreviewInfoPo) {
                tClientreviewInfoPo.setReviewResult("其他");
                tClientreviewInfoPo.setReviewStatus(ReviewStatusEnum.HAVE_REVIEW.getCode());
                tClientreviewInfoPo.setReviewTime(new Date());
            }
            tClientreviewInfoMapper.updateById(tClientreviewInfoPo);
            //10.4 更新私募回访渠道
            PrivatePlacementDto updateDto = new PrivatePlacementDto();
            updateDto.setId(id);
            updateDto.setReviewStatus(PrivateReviewStatusEnum.DOING.getKey());// 回访中
            updateDto.setReviewMode(PrivateReviewModeEnum.QUESTIONNAIRE.getKey());
            updateDto.setReviewChannel(PrivateReviewChannelEnum.APP.getKey());
            privatePlacementDbManager.batchUpdateById(updateDto);
        }

        return id.toString();
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

    public void sendSms(List<MessageSendInVo> voList, Map<String, String> paramMap, CustomerViewVo customerViewVo,
        String title, Long smsMsgTemplateId, Long privatePlacementId) {
        List<AddMsgReceiverVo> list = new ArrayList<>();
        AddMsgReceiverVo addMsgReceiverVo = new AddMsgReceiverVo();
        addMsgReceiverVo.setCustomerId(customerViewVo.getCustomerId());

        addMsgReceiverVo.setSendStatus("1");//未发送
//        addMsgReceiverVo.setRemark();
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

    */
/**
     * 创建私募冷静期外呼回访并更新回访状态和回访渠道
     * 集合privatePlacementPos中的customerId字段值需相同
     * 同一客户购买了多个产品，只生成一条外呼回访
     *//*

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class, transactionManager =
        "mysqlTrans")
    public void createPrivatePlacementTelReview(List<PrivatePlacementPo> privatePlacementPos) {
        if (CollectionUtils.isEmpty(privatePlacementPos)) {
            log.error("privatePlacementPos为空");
            return;
        }
        PrivatePlacementPo privatePlacementPo = privatePlacementPos.get(0);
        // 拼接触发信息
        String triggerInfo = getTriggerInfo(privatePlacementPos);
        // 获取客户姓名
        String customerName = getCustomerName(privatePlacementPo.getCustomerId());
        CreatePrivateTelReviewDto createPrivateTelReviewDto = new CreatePrivateTelReviewDto();
        createPrivateTelReviewDto.setTriggerInfo(triggerInfo);
        createPrivateTelReviewDto.setOrganId(privatePlacementPo.getOrganId());
        createPrivateTelReviewDto.setCustomerName(customerName);
        createPrivateTelReviewDto.setCustomerId(privatePlacementPo.getCustomerId());
        createPrivateTelReviewDto.setCreateBy(VisitConstants.DEFAULT_OPERATOR);
        createPrivateTelReviewDto.setReviewType(VisitConstants.REVIEW_TYPE_PRIVATE_PLACEMENT);
        createPrivateTelReviewDto.setChannel("MOT");
        createPrivateTelReviewDto.setEventId(privatePlacementPo.getEventId());
        createPrivateTelReviewDto.setPrivatePlacementIds(
            privatePlacementPos.stream().map(PrivatePlacementPo::getId).collect(Collectors.toList()));
        createPrivateTelReviewDto.setStaffId(privatePlacementPo.getStaffId());
        createPrivateTelReviewDto.setStaffName(privatePlacementPo.getStaffName());
        // 创建回访
        CustomerReviewJobPo jobPo = privatePlacementDbManager.createCustomerTelReview(createPrivateTelReviewDto);
        // 是否需要调用外呼接口直接创建回访
        if (useOutboundInterface) {
            privatePlacementDbManager.createTelReviewByHttp(jobPo);
        }
        log.info("创建外呼回访成功，customerReviewId: {}", jobPo.getCustomerReviewId());

        // 更新状态为回访中
        PrivatePlacementDto updateDto = new PrivatePlacementDto();
        updateDto.setIds(privatePlacementPos.stream().map(PrivatePlacementPo::getId).collect(Collectors.toList()));
        updateDto.setReviewStatus(PrivateReviewStatusEnum.DOING.getKey());// 回访中
        updateDto.setReviewMode(PrivateReviewModeEnum.TELEPHONE.getKey());
        updateDto.setReviewChannel(PrivateReviewChannelEnum.MANUAL_OUTBOUND_CALL.getKey());
        privatePlacementDbManager.batchUpdateById(updateDto);//todo 修改sql
    }

    */
/**
     * 根据客户id查询客户姓名
     *//*

    private String getCustomerName(String customerId) {
        QueryTcustVo vo = new QueryTcustVo();
        vo.setCustNo(customerId);
        vo.setPageSize(100);
        vo.setPageNo(1);
        List<CustomerViewVo> customerViewVos = dcManager.queryCustomerByPhone(vo);
        if (CollectionUtils.isNotEmpty(customerViewVos)) {
            return customerViewVos.get(0).getCustomerName();
        }
        return null;
    }

    */
/**
     * 拼接触发信息
     *//*

    public String getTriggerInfo(List<PrivatePlacementPo> list) {
//        年龄=35;产品代码=SLT083,STJ832;委托方式=手机委托;风险等级=激进型;风险匹配标志=匹配;产品销售专员=陈颖;产品销售专员联系方式=029-89582147;
//        产品名称=龙旗红旭三号私募证券投资基金,龙旗1000指增6号私募证券投资基金A;产品类型=私募基金产品;销售人员姓名=张三;销售人员联系方式=17884584545;产品风险等级=高风险等级;分类ID=3
        StringBuilder triggerInfo = new StringBuilder();
        // 年龄
        int age = 0;
        Long birth = list.get(0).getBirthDate();
        if (birth != null) {
            LocalDate birthDate = DateUtil.parseStrToLocalDate(DateUtil.BASIC_ISO_DATE, birth.toString());
            Period agePeriod = Period.between(birthDate, LocalDate.now());
            age = agePeriod.getYears();
        }
        triggerInfo.append("年龄=").append(age).append(SEMICOLON);
        // 产品代码，多个产品使用逗号拼接
        String prodCode = list.stream().map(PrivatePlacementPo::getProdCode).distinct()
            .collect(Collectors.joining(COMMA));
        triggerInfo.append("产品代码=").append(prodCode).append(SEMICOLON);
        // 委托方式
        String opEntrustWay = list.stream().map(PrivatePlacementPo::getOpEntrustWay).filter(StringUtils::isNotEmpty)
            .distinct()
            .map(it -> dataDictManager.queryDataDict(VisitConstants.DIM_ENTRUST_WAY_DATA_DIC, it).getDdValue())
            .collect(Collectors.joining(COMMA));
        triggerInfo.append("委托方式=").append(opEntrustWay).append(SEMICOLON);
        // 风险等级
        String customerRiskLevel = list.stream().map(PrivatePlacementPo::getCustomerRiskLevel)
            .filter(StringUtils::isNotEmpty).distinct()
            .map(it -> dataDictManager.queryDataDict(VisitConstants.CUSTOMER_RISK_LEVEL_DATA_DIC, it).getDdValue())
            .collect(Collectors.joining(COMMA));
        if (StringUtils.isNotEmpty(customerRiskLevel)) {
            triggerInfo.append("风险等级=").append(customerRiskLevel).append(SEMICOLON);
        }
        // 风险匹配标志
        String riskMatchFlag = list.stream().map(PrivatePlacementPo::getRiskMatchFlag).filter(StringUtils::isNotEmpty)
            .distinct().map(it -> "0".equals(it) ? "不匹配" : "1".equals(it) ? "匹配" : "")
            .collect(Collectors.joining(COMMA));
        if (StringUtils.isNotEmpty(riskMatchFlag)) {
            triggerInfo.append("风险匹配标志=").append(riskMatchFlag).append(SEMICOLON);
        }
        // 产品销售专员
        // 产品销售专员联系方式
        // 查oracle
        String branchNo = organizationService.codeToBranchNo(list.get(0).getOrganId());
        List<ProdSaleCommissionerDto> prodSaleCommissionerList = privatePlacementDbManager.selectProdSaleCommissioner(
            branchNo);
        if (CollectionUtils.isNotEmpty(prodSaleCommissionerList)) {
            String memberName = prodSaleCommissionerList.stream().map(ProdSaleCommissionerDto::getMemberName)
                .filter(StringUtils::isNotEmpty).distinct().collect(Collectors.joining(COMMA));
            if (StringUtils.isNotEmpty(memberName)) {
                triggerInfo.append("产品销售专员=").append(memberName).append(SEMICOLON);
            }
            String tel = prodSaleCommissionerList.stream()
                .map(it -> StringUtils.isEmpty(it.getGzdh()) ? it.getSj() : it.getGzdh())
                .filter(StringUtils::isNotEmpty).distinct().collect(Collectors.joining(COMMA));
            if (StringUtils.isNotEmpty(tel)) {
                triggerInfo.append("产品销售专员联系方式=").append(tel).append(SEMICOLON);
            }
        }
        // 产品名称
        String prodName = list.stream().map(PrivatePlacementPo::getProdName).distinct()
            .collect(Collectors.joining(COMMA));
        triggerInfo.append("产品名称=").append(prodName).append(SEMICOLON);
        // 产品类型
        String prodType = list.stream().map(PrivatePlacementPo::getProdType).filter(StringUtils::isNotEmpty).distinct()
            .map(it -> dataDictManager.queryDataDict(VisitConstants.PROD_TYPE_DATA_DIC, it).getDdValue())
            .collect(Collectors.joining(COMMA));
        if (StringUtils.isNotEmpty(prodType)) {
            triggerInfo.append("产品类型=").append(prodType).append(SEMICOLON);
        }
        // 销售人员姓名
        String salesman = list.stream().map(PrivatePlacementPo::getSalesman).filter(StringUtils::isNotEmpty).distinct()
            .collect(Collectors.joining(COMMA));
        if (StringUtils.isNotEmpty(salesman)) {
            triggerInfo.append("销售人员姓名=").append(salesman).append(SEMICOLON);
        }
        // 销售人员联系方式
        String salesmanTel = list.stream().map(PrivatePlacementPo::getSalesmanTel).filter(StringUtils::isNotEmpty)
            .distinct().collect(Collectors.joining(COMMA));
        if (StringUtils.isNotEmpty(salesmanTel)) {
            triggerInfo.append("销售人员联系方式=").append(salesmanTel).append(SEMICOLON);
        }
        // 产品风险等级
        String prodRiskLevel = list.stream().map(PrivatePlacementPo::getProdRiskLevel).filter(StringUtils::isNotEmpty)
            .distinct().map(it -> "5".equals(it) ? "高风险等级" : "").collect(Collectors.joining(COMMA));
        if (StringUtils.isNotEmpty(prodRiskLevel)) {
            triggerInfo.append("产品风险等级=").append(prodRiskLevel).append(SEMICOLON);
        }
        // 分类ID
        int classificationId = 3;
        triggerInfo.append("分类ID=").append(classificationId);
        return triggerInfo.toString();
    }

    */
/**
     * 更新客户合同等材料，双录是否合规
     *//*

    @Override
    public String complianceAudit(UpdatePrivatePlacementComplianceDto dto) {
        if (Objects.isNull(dto)) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_PARAM_OBJECT_NOT_NULL);
        }
        // 执行参数校验
        dto.validate();
        // 先查下
        PrivatePlacementPo privatePlacementPo = privatePlacementDbManager.selectById(dto.getId());
        if (privatePlacementPo == null) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_MESSAGE_PRIVATE_REVIEW_ABSENT);
        }
        if (StringUtils.isNotEmpty(dto.getOrganId()) && !privatePlacementPo.getOrganId().equals(dto.getOrganId())) {
            // 不为空表示想要控制机构权限，不相等表示没权限
            throw ExceptionUtil.createException(VisitConstants.ERROR_NO_PERMISSION);
        }
        if (CollectionUtils.isNotEmpty(dto.getControlOrganIds()) && !dto.getControlOrganIds()
            .contains(privatePlacementPo.getOrganId())) {
            // 不为空表示想要控制机构权限，不包含表示没权限
            throw ExceptionUtil.createException(VisitConstants.ERROR_NO_PERMISSION);
        }
        if (!privatePlacementPo.getIsCompliance().equals(VisitConstants.IS_COMPLIANCE_NOT_REPORT)) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_ORDER_CANNOT_BE_UPDATED);
        }
        PrivatePlacementDto privatePlacementDto = new PrivatePlacementDto();
        privatePlacementDto.setId(dto.getId());
        privatePlacementDto.setIsCompliance(dto.getComplianceFlag());
        privatePlacementDto.setComplianceConfirmId(dto.getComplianceConfirmId());
        privatePlacementDbManager.batchUpdateById(privatePlacementDto);
        // 双写oracle
        if (privatePlacementPo.getPrivatePlacementId() != null) {
            PrivatePlacementOracleDto privatePlacementOracleDto = new PrivatePlacementOracleDto();
            privatePlacementOracleDto.setId(privatePlacementPo.getPrivatePlacementId());
            privatePlacementOracleDto.setHgqr(dto.getComplianceFlag());
            privatePlacementOracleDto.setHgczr(dto.getComplianceConfirmId());
            tSmLjqhfMapper.batchUpdateById(privatePlacementOracleDto);
        }
        return privatePlacementPo.getId().toString();
    }

    @Override
    public void dealPrivatePlacementHistory() {
        // 获取没有客户名称的客户信息
        List<String> customerIdList = privatePlacementMapper.selectWithoutCustomerName();
        List<CustomerViewVo> customerViewVos = dcManager.quertCustomerList2(customerIdList);
        int result = privatePlacementMapper.batchUpdateCustomerName(customerViewVos);
        log.info("PrivatePlacementService dealPrivatePlacementHistory size:{} result:{}", customerViewVos.size(),
            result);
    }

    */
/**
     * 根据回访编号 批量查询私募冷静期回访状态，最大200条
     *
     * @param list
     * @return
     *//*

    @Override
    public List<PrivatePlacementStatusDto> queryBatchPrivatePlacement(List<PrivatePlacementStatusVo> list) {
        //1、收集冷静期表ID
        ArrayList<Long> privatePlacementIdList = new ArrayList<>();
        //2、收集用fundAccount+orderId与privatePlacementId查询方式的数据
        ArrayList<PrivatePlacementStatusVo> searchList = new ArrayList<>();
        //3、循环收集
        for (PrivatePlacementStatusVo privatePlacementStatusVo : list) {
            if (null != privatePlacementStatusVo.getPrivatePlacementId()) {
                privatePlacementIdList.add(privatePlacementStatusVo.getPrivatePlacementId());
                continue;
            }
            searchList.add(privatePlacementStatusVo);
        }
        List<PrivatePlacementStatusDto> privatePlacementInfoDtoList = new ArrayList<>();
        List<PrivatePlacementStatusDto> dtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(privatePlacementIdList)) {
            //根据私募冷静期表主键批量查询
            privatePlacementInfoDtoList = privatePlacementMapper.queryBatchById(privatePlacementIdList);
        }
        if (CollectionUtils.isNotEmpty(searchList)) {
            //根据资金账号和申请编号（订单号）批量查询回访状态
            dtoList = privatePlacementMapper.queryBatchPrivatePlacement(searchList);
        }

        privatePlacementInfoDtoList.addAll(dtoList);

        //将结果做成map，方便查询结果
        Map<String, PrivatePlacementStatusDto> map = privatePlacementInfoDtoList.stream()
            .collect(Collectors.toMap(item -> item.getFundAccount() + "_" + item.getOrderId(), a -> a));

        Map<Long, PrivatePlacementStatusDto> privatePlacementIdMap = privatePlacementInfoDtoList.stream()
            .collect(Collectors.toMap(PrivatePlacementStatusDto::getPrivatePlacementId, a -> a));
        //查询线上回访最新id
        Map<Long, Long> privatePlacementOnlineReviewPoMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(privatePlacementInfoDtoList)) {
            List<Long> ids = privatePlacementInfoDtoList.stream().map(PrivatePlacementStatusDto::getPrivatePlacementId)
                .distinct().collect(Collectors.toList());
            List<PrivatePlacementOnlineReviewPo> privatePlacementOnlineReviewPoList =
                privatePlacementOnlineReviewDbManager.selectIdByPrivatePlacementId(
                    ids);
            if (CollectionUtils.isNotEmpty(privatePlacementOnlineReviewPoList)) {
                privatePlacementOnlineReviewPoMap = privatePlacementOnlineReviewPoList.stream().collect(
                    Collectors.toMap(PrivatePlacementOnlineReviewPo::getPrivatePlacementId,
                        PrivatePlacementOnlineReviewPo::getId));
            }
        }

        //返回集合
        List<PrivatePlacementStatusDto> returnList = new ArrayList<>();
        //循环映射结果
        for (PrivatePlacementStatusVo privatePlacementStatusVo : list) {
            //冷静期表主键不为空
            if (null != privatePlacementStatusVo.getPrivatePlacementId() && null != privatePlacementIdMap.get(
                privatePlacementStatusVo.getPrivatePlacementId())) {
                PrivatePlacementStatusDto dto = privatePlacementIdMap.get(
                    privatePlacementStatusVo.getPrivatePlacementId());
                dto.setPrivatePlacementOnlineReviewId(
                    privatePlacementOnlineReviewPoMap.get(dto.getPrivatePlacementId()));
                returnList.add(dto);
            } else if (null != map.get(
                privatePlacementStatusVo.getFundAccount() + "_" + privatePlacementStatusVo.getOrderId())) {
                PrivatePlacementStatusDto dto = map.get(
                    privatePlacementStatusVo.getFundAccount() + "_" + privatePlacementStatusVo.getOrderId());
                dto.setPrivatePlacementOnlineReviewId(
                    privatePlacementOnlineReviewPoMap.get(dto.getPrivatePlacementId()));
                returnList.add(dto);
            } else {
                PrivatePlacementStatusDto dto = new PrivatePlacementStatusDto();
                dto.setPrivatePlacementOnlineReviewId(
                    privatePlacementOnlineReviewPoMap.get(dto.getPrivatePlacementId()));
                dto.setFundAccount(privatePlacementStatusVo.getFundAccount());
                dto.setOrderId(privatePlacementStatusVo.getOrderId());
                dto.setPrivatePlacementId(privatePlacementStatusVo.getPrivatePlacementId());
                returnList.add(dto);
            }
        }

        return returnList;
    }

    public List<PrivatePlacementStatusDto> queryBatchPrivatePlacement1(List<PrivatePlacementStatusVo> list) {
        ArrayList<Long> privatePlacementIdList = new ArrayList<>();
        List<PrivatePlacementStatusDto> privatePlacementInfoDtoList = privatePlacementMapper.queryBatchPrivatePlacement(
            list);
        //将结果做成map，方便查询结果
        Map<String, PrivatePlacementStatusDto> map = privatePlacementInfoDtoList.stream()
            .collect(Collectors.toMap(item -> item.getFundAccount() + "_" + item.getOrderId(), a -> a));
        //返回集合
        List<PrivatePlacementStatusDto> returnList = new ArrayList<>();
        //循环映射结果
        for (PrivatePlacementStatusVo privatePlacementStatusVo : list) {

            if (null != map.get(
                privatePlacementStatusVo.getFundAccount() + "_" + privatePlacementStatusVo.getOrderId())) {
                returnList.add(
                    map.get(privatePlacementStatusVo.getFundAccount() + "_" + privatePlacementStatusVo.getOrderId()));
            } else {
                PrivatePlacementStatusDto dto = new PrivatePlacementStatusDto();
                dto.setFundAccount(privatePlacementStatusVo.getFundAccount());
                dto.setOrderId(privatePlacementStatusVo.getOrderId());
                dto.setPrivatePlacementId(privatePlacementStatusVo.getPrivatePlacementId());
                returnList.add(dto);
            }
        }

        return returnList;
    }

    */
/**
     * 根据回访编号 批量更新私募冷静期回访状态，最大200条
     *
     * @param list
     * @return
     *//*

    @Override
    public void updateBatchPrivatePlacement(List<PrivatePlacementInfoDto> list) {
        privatePlacementMapper.updateBatchById(list, PrivateReviewStatusEnum.FINISHED.getKey(),
            VisitConstants.REVIEW_VERIFY_RESULT_SUCCESS,"成功回访");

        //拿到所有的id
        List<Long> privatePlacementIdList = list.stream()
            .map(PrivatePlacementInfoDto::getPrivatePlacementId)
            .filter(id -> id != null) // 过滤掉null的id
            .distinct()
            .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(privatePlacementIdList)) {
            //批量查询对应的数据
            List<PrivatePlacementStatusDto> privatePlacementStatusDtoList = privatePlacementMapper.queryBatchById(
                privatePlacementIdList);
            //拿到所有的oracle的T_SM_LJQHF表主键
            List<Long> idList = privatePlacementStatusDtoList.stream()
                .map(PrivatePlacementStatusDto::getOldPrivatePlacementId)
                .filter(id -> id != null) // 过滤掉null的id
                .distinct()
                .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(idList)) {
                //批量更新老的T_SM_LJQHF的回访审核结果为合规已完成
                tSmLjqhfMapper.updateBatchById(idList, VisitConstants.REVIEW_VERIFY_RESULT_SUCCESS);
            }
        }

        privatePlacementOnlineReviewDbManager.updateBatchById(list, PrivateReviewStatusEnum.FINISHED.getKey(), "成功回访");

    }

    @Override
    public PageInfo<PrivatePlacementEntity> queryPrivatePlacementReview(QueryPrivatePlacementDto dto) {

        if (Objects.isNull(dto)) {
            throw ExceptionUtil.createException(VisitConstants.ERROR_PARAM_OBJECT_NOT_NULL);
        }
        //执行参数校验
        dto.validate();
        return privatePlacementDbManager.selectPrivatePlacement(dto);
    }

    @Override
    public void dealPrivatePlacementOrganName() {
        privatePlacementMapper.batchUpdateOrganName(organizationService.getAll());
    }

}
*/
