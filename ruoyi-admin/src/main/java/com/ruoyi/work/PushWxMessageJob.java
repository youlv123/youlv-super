/*
package com.ruoyi.work;

import cn.hutool.core.collection.ListUtil;
import cn.nesc.wm.opt.mot.enums.WxPushMessageTypeEnum;
import cn.nesc.wm.opt.mot.infrustracture.oracle.mapper.OracleMapper;
import cn.nesc.wm.opt.mot.infrustracture.oracle.mapper.T99AcalendarHrsMapper;
import cn.nesc.wm.opt.mot.infrustracture.remote.DoingQueryManager;
import cn.nesc.wm.opt.mot.infrustracture.remote.WmNoticeManager;
import cn.nesc.wm.opt.mot.infrustracture.remote.WmTaskManager;
import cn.nesc.wm.opt.mot.infrustracture.remote.WmVisitReviewManager;
import cn.nesc.wm.opt.mot.infrustracture.remote.model.UsersInfoDto;
import cn.nesc.wm.opt.mot.service.model.*;

import cn.nesc.wm.opt.mot.util.DateUtilTool;
import com.tencent.cloud.task.sdk.client.model.ExecutableTaskData;
import com.tencent.cloud.task.sdk.client.model.ProcessResult;
import com.tencent.cloud.task.sdk.client.spi.ExecutableTask;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

*/
/**
 * MOT待做任务提醒，9:15 11:15 14:15 16:15 触发推送，一天四次 ，仅限交易日
 * 对客户经理当日必做任务没做的进行提醒
 * 对客户没有客户经理的数据提醒财富总监分配数据
 * 对管理员进行数据提醒，发送了多少条数据
 *//*

@Component
@Slf4j
public class PushWxMessageJob implements ExecutableTask {

    @Resource
    private WmTaskManager wmTaskManager;
    @Resource
    private WmVisitReviewManager wmVisitReviewManager;
    @Resource
    private T99AcalendarHrsMapper t99AcalendarHrsMapper;
    @Resource
    private WmNoticeManager wmNoticeManager;
    @Resource
    private OracleMapper oracleMapper;

    //管理员数据发送人
    @Value("${layim.weixin.WX_SEND_USER}")
    private String wxSendUser;

    //马聪ID
    @Value("${layim.weixin.WX_STAFF_ID}")
    private String wxStaffId;

    //马聪姓名
    @Value("${layim.weixin.WX_STAFF_NAME}")
    private String wxStaffName;

    //合规专员DOING角色ID
    @Value("${role.COMMISSIONER_ROLE_ID}")
    private String commissionerRoleId;

    //财富总监DOING角色ID
    @Value("${role.CHIEF_ROLE_ID}")
    private String chiefRoleId;

    @Value("${template.WX_STAFF}")
    private Long wxStaff;

    @Value("${template.WX_COMMISSIONER}")
    private Long wxCommissioner;

    @Value("${template.WX_CHIEF}")
    private Long wxChief;

    @Value("${template.WX_MC}")
    private Long wxMc;

    @Value("${template.WX_LJQ}")
    private Long wxLjq;

    @Value("${template.WX_LJQHF}")
    private Long wxLjqhf;

    @Value("${template.WX_FXYWTZ_STAFF}")
    private Long wxFxywtzStaff;

    @Value("${template.WX_FXYWTZ}")
    private Long wxFxywtz;

    @Value("${template.WX_ADMIN}")
    private Long wxAdmin;

    @Resource
    private UserConfigBean userConfigBean;

    @Resource
    private DoingQueryManager doingQueryManager;
    //发送最大条数
    private static final int MAX_SEND_SIZE = 500;

    @Override
    public ProcessResult execute(ExecutableTaskData taskData) {
        //1、判断今天是不是交易日
        String today = DateUtilTool.formatDate(DateUtilTool.BASIC_ISO_DATE, LocalDateTime.now());
        String bizDate = t99AcalendarHrsMapper.selectBizDate(today);
        if (!today.equals(bizDate)) {
            log.info("=== 今天[{}]不是交易日，不进行处理", today);
            // 设置任务执行状态： 执行成功
            return ProcessResult.newSuccessResult();
        }

        // 2、查询需要组装的数据，
        // 2.1 查询客户经理数据
        List<StaffWxMessageDto> staffWxList = wmTaskManager.searchStaffWxMessage();
        // 2.2 查询财富总监数据
        List<ChiefWxMessageDto> chiefWxList = wmTaskManager.selectChiefMessage();
        //2.3 拿出所有客户id
        List<String> allCustomerIds = chiefWxList.stream()
                .map(ChiefWxMessageDto::getCustomerId)
                .distinct()
                .collect(Collectors.toList());
        //2.4 找到有服务关系的去掉
        List<String> removeCustomerList = oracleMapper.selectKHGX(allCustomerIds);
        for (int i = chiefWxList.size() - 1; i >= 0; i--) {
            ChiefWxMessageDto chiefWxMessageDto = chiefWxList.get(i);
            if (removeCustomerList.contains(chiefWxMessageDto.getCustomerId())){
                chiefWxList.remove(i);
            }
        }
        //定义客户经理成功发送条数
        int staffSuccessCount = 0;
        //定义财富总监成功发送条数
        int chiefSuccessCount = 0;
        //定义合规专员成功发送条数
        int commissionerSuccessCount = 0;

        //3、-------------------------------------------------------------------客户经理及合规专员数据组装推送
        //定义推送客户经理数据的list
        List<WxMsgSendDto> staffSendList = new ArrayList<>();
        //定义推送合规专员数据的list
        List<WxMsgSendDto> commissionerSendList = new ArrayList<>();
        //3.1查询所有合规专员数据，大概几百个
        QueryUsersInfoDto queryUsersInfoDto = new QueryUsersInfoDto();
        queryUsersInfoDto.setRoleId(commissionerRoleId);
        List<UsersInfoDto> commissionerList = doingQueryManager.getApproverByCustomerId(queryUsersInfoDto);
        //3.2 构建key是organId，value是对应的UserPo对象列表的Map
        Map<String, List<UsersInfoDto>> commissionerMap = commissionerList.stream()
                .filter(user -> user.getOrgId() != null) // 过滤掉organId为空的数据
                .collect(Collectors.groupingBy(UsersInfoDto::getOrgId));
        for (StaffWxMessageDto staffWxMessageDto : staffWxList) {
            //3.3 消息组装成map
            Map<String, String> messageMap = this.parseStaffDataToMap(staffWxMessageDto);
            //3.4 组装待发送消息实体
            WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
            wxMsgSendDto.setUserId(staffWxMessageDto.getStaffId());
            wxMsgSendDto.setUserName(staffWxMessageDto.getStaffName());
            wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_STAFF.getCode());
            wxMsgSendDto.setData(messageMap);
            wxMsgSendDto.setMessageId(wxStaff);
            staffSendList.add(wxMsgSendDto);
            //3.5 查询该客户经理所在机构的合规专员,组装专员数据
            List<UsersInfoDto> userPoList = commissionerMap.get(staffWxMessageDto.getOrganId());
            if (CollectionUtils.isNotEmpty(userPoList)) {
                for (UsersInfoDto usersInfoDto : userPoList) {
                    WxMsgSendDto dto = new WxMsgSendDto();
                    dto.setUserId(usersInfoDto.getUserId());
                    dto.setUserName(usersInfoDto.getUserName());
                    dto.setMessageType(WxPushMessageTypeEnum.WX_COMMISSIONER.getCode());
                    dto.setData(messageMap);
                    dto.setMessageId(wxCommissioner);
                    commissionerSendList.add(dto);
                }
            }
        }
        //3.6 客户经理数据500一个批次调用notice进行微信消息推送
        List<List<WxMsgSendDto>> partitionStaffList = ListUtil.partition(staffSendList, MAX_SEND_SIZE);
        for (List<WxMsgSendDto> list : partitionStaffList) {
            int a = wmNoticeManager.sendMessageToWx(list);
            staffSuccessCount += a;
        }
        log.info("客户经理总共待推送数据条数：" + staffWxList.size() + "，发送完成条数：" + staffSuccessCount);

        //3.7 合规专员数据500一个批次调用notice进行微信消息推送
        List<List<WxMsgSendDto>> partitionCommissionerList = ListUtil.partition(commissionerSendList, MAX_SEND_SIZE);
        for (List<WxMsgSendDto> list : partitionCommissionerList) {
            int a = wmNoticeManager.sendMessageToWx(list);
            commissionerSuccessCount += a;
        }
        log.info("合规专员总共待推送数据条数：" + commissionerSendList.size() + "，发送完成条数：" + commissionerSuccessCount);

        //4、-------------------------------------------------------------------财富总监数据组装推送
        //4.1按照机构进行合并
        List<ChiefWxPushMessageDto> summaryList = chiefWxList.stream()
                .collect(Collectors.groupingBy(ChiefWxMessageDto::getOrganId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> new ChiefWxPushMessageDto(
                                        list.get(0).getOrganId(),//按照机构合并
                                        list.stream().collect(Collectors.counting()),//计算合并的客户数
                                        list.stream().mapToInt(ChiefWxMessageDto::getCountNum).sum()//计算客户数触发的事件未完成总数
                                )
                        )
                ))
                .values().stream()
                .collect(Collectors.toList());

        //定义最后发送消息list
        List<WxMsgSendDto> chiefSendList = new ArrayList<>();
        for (ChiefWxPushMessageDto chiefWxPushMessageDto : summaryList) {
            //4.2组装财富总监数据map
            Map<String, String> messageMap = this.parseChiefDataToMap(chiefWxPushMessageDto);
            //4.3根据机构去查询财富总监的员工号，根据数据查询，存在一个机构有多个财富总监的情况，不过不是很多，一般就几个
            QueryUsersInfoDto queryDto = new QueryUsersInfoDto();
            queryDto.setOrgId(chiefWxPushMessageDto.getOrganId());
            queryDto.setRoleId(chiefRoleId);
            //查询财富总监
            List<UsersInfoDto> userPoList = doingQueryManager.getApproverByCustomerId(queryUsersInfoDto);
            //4.4 组装财富总监待发送消息实体
            for (UsersInfoDto userPo : userPoList) {
                WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
                wxMsgSendDto.setUserId(userPo.getUserId());
                wxMsgSendDto.setUserName(userPo.getUserName());
                wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_CHIEF.getCode());
                wxMsgSendDto.setData(messageMap);
                wxMsgSendDto.setMessageId(wxChief);
                chiefSendList.add(wxMsgSendDto);
            }
        }
        //定义财富总监数据总量
        int countChief = 0;
        countChief += chiefSendList.size();
        //4.5 调用notice微信放模板进行消息发送，这里不需要分500一个批次，因为数据量很少，一般就几条数据。
        if (CollectionUtils.isNotEmpty(chiefSendList)) {
            int a = wmNoticeManager.sendMessageToWx(chiefSendList);
            chiefSuccessCount += a;
        }
        //5、 -------------------------------------------------------------------财富总监数据-视图数据，不需要组装
        //定义财富总监视图数据最后发送集合
        List<WxMsgSendDto> vChiefSendList = new ArrayList<>();
        //5.1 马聪专有特殊微信消息推送,曾经的视图V_EVENTFLOW_HF
        Boolean flag = wmVisitReviewManager.selectEventFlowTask();
        if (flag) {
            WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
            wxMsgSendDto.setUserId(wxStaffId);
            wxMsgSendDto.setUserName(wxStaffName);
            wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_MC.getCode());
            wxMsgSendDto.setMessageId(wxMc);
            vChiefSendList.add(wxMsgSendDto);
        }
        //5.2查询曾经V_SM_LJQ视图触发的数据  私募基金销售触发信息
        List<PlacementDto> placementDtoList = wmVisitReviewManager.selectOrganNum();
        for (PlacementDto placementDto : placementDtoList) {
            Map<String, String> messageMap = this.parseChiefNumDataToMap(placementDto);
            //查询合规专员
            List<UsersInfoDto> userPoList = commissionerMap.get(placementDto.getOrganId());
            for (UsersInfoDto userPo : userPoList) {
                WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
                wxMsgSendDto.setUserId(userPo.getUserId());
                wxMsgSendDto.setUserName(userPo.getUserName());
                wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_LJQ.getCode());
                wxMsgSendDto.setData(messageMap);
                wxMsgSendDto.setMessageId(wxLjq);
                vChiefSendList.add(wxMsgSendDto);
            }
        }
        //5.3 查询当前业务日期的私募基金产生数量  曾经的视图V_SM_LJQHF
        // 获取当前时间
        LocalTime currentTime = LocalTime.now();
        // 定义时间范围,只在13:30到15:00触发
        LocalTime startTime = LocalTime.of(13, 30);
        LocalTime endTime = LocalTime.of(15, 0);
        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            //查询visit，触发的任务数量
            Integer count = wmVisitReviewManager.selectStaffNum();
            if (0 != count) {
                Map<String, String> messageMap = this.parseStaffNumDataToMap(count);
                //配置的待发送人员
                for (User user : userConfigBean.getUserlist()) {
                    WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
                    wxMsgSendDto.setUserId(user.getUserId());
                    wxMsgSendDto.setUserName(user.getName());
                    wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_LJQHF.getCode());
                    wxMsgSendDto.setData(messageMap);
                    wxMsgSendDto.setMessageId(wxLjqhf);
                    vChiefSendList.add(wxMsgSendDto);
                }
            }
        }
        //5.4 查询曾今视图 V_MOT_FXYWTZ 数据
        //每天10点前才触发
        LocalTime limitTime = LocalTime.of(10, 0);
        if (currentTime.isBefore(limitTime)) {
            List<TaskEntity> taskEntities = wmTaskManager.searchEventWxInfo();
            for (TaskEntity taskEntity : taskEntities) {
                Map<String, String> messageMap = this.parseEventWxInfoDataToMap(taskEntity.getCustomerName());
                WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
                wxMsgSendDto.setUserId(taskEntity.getStaffId());
                wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_FXYWTZ_STAFF.getCode());
                wxMsgSendDto.setData(messageMap);
                wxMsgSendDto.setMessageId(wxFxywtzStaff);
                vChiefSendList.add(wxMsgSendDto);
                //查询合规专员
                List<UsersInfoDto> userPoList = commissionerMap.get(taskEntity.getOrganId());
                for (UsersInfoDto userPo : userPoList) {
                    WxMsgSendDto dto = new WxMsgSendDto();
                    dto.setUserId(userPo.getUserId());
                    dto.setUserName(userPo.getUserName());
                    dto.setMessageType(WxPushMessageTypeEnum.WX_FXYWTZ.getCode());
                    dto.setData(messageMap);
                    dto.setMessageId(wxFxywtz);
                    vChiefSendList.add(dto);
                }
            }
        }

        //5.5查询 剩余暂时不能改造视图数据
        List<VChiefWxPushMessageDto> vChiefWxPushMessageDtoList = oracleMapper.selectChiefWx();
        //循环组装数据
        for (VChiefWxPushMessageDto vChiefWxPushMessageDto : vChiefWxPushMessageDtoList) {
            WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
            wxMsgSendDto.setUserId(vChiefWxPushMessageDto.getUserId());
            wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_V_CHIEF.getCode());
            wxMsgSendDto.setWxContent(vChiefWxPushMessageDto.getWxContent());
            vChiefSendList.add(wxMsgSendDto);
        }
        //5.6调用notice发送消息
        if (CollectionUtils.isNotEmpty(vChiefSendList)) {
            int a = wmNoticeManager.sendMessageToWx(vChiefSendList);
            chiefSuccessCount += a;
        }
        countChief += vChiefSendList.size();
        log.info("财富总监总共待推送数据条数：" + countChief + "，发送完成条数：" + countChief);
        //6、 -------------------------------------------------------------------管理员数据组装推送
        //6.1 组装数据
        Map<String, String> adminStringMap = parseAdminDataToMap(staffWxList.size(), staffSuccessCount, countChief, chiefSuccessCount);
        List<WxMsgSendDto> adminList = new ArrayList<>();
        WxMsgSendDto wxMsgSendDto = new WxMsgSendDto();
        wxMsgSendDto.setUserId(wxSendUser);
        wxMsgSendDto.setMessageType(WxPushMessageTypeEnum.WX_ADMIN.getCode());
        wxMsgSendDto.setData(adminStringMap);
        wxMsgSendDto.setMessageId(wxAdmin);
        adminList.add(wxMsgSendDto);
        //6.2调用notice发送微信消息，一般就几条数据
        wmNoticeManager.sendMessageToWx(adminList);
        return ProcessResult.newSuccessResult();
    }


    */
/**
     * 分割拼接类型数据，组装成map
     * 组装客户经理数据
     *
     * @param staffWxMessageDto
     * @return
     *//*

    public Map<String, String> parseStaffDataToMap(StaffWxMessageDto staffWxMessageDto) {
        if (null == staffWxMessageDto) {
            return null;
        }
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String month = String.valueOf(currentDate.getMonthValue());
        String day = String.valueOf(currentDate.getDayOfMonth());
        LocalTime currentTime = LocalTime.now();
        String hour = String.valueOf(currentTime.getHour());
        Map<String, String> map = new HashMap<>();
        map.put("年", year);
        map.put("月", month);
        map.put("日", day);
        map.put("时", hour);
        map.put("数量", staffWxMessageDto.getCountNum());
        map.put("类别", staffWxMessageDto.getWxMessageContent());
        return map;
    }

    */
/**
     * 分割拼接类型数据，组装成map
     * 组装财富总监数据
     * 2023年12月14日时，您有1个客户已触发必做任务（共1条），尚未分配服务关系，请及时登录MOT服务关系 管理页面进行分配。（备注：如已处理请忽略）
     *
     * @param chiefWxPushMessageDto
     * @return
     *//*

    public Map<String, String> parseChiefDataToMap(ChiefWxPushMessageDto chiefWxPushMessageDto) {
        if (null == chiefWxPushMessageDto) {
            return null;
        }
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String month = String.valueOf(currentDate.getMonthValue());
        String day = String.valueOf(currentDate.getDayOfMonth());
        LocalTime currentTime = LocalTime.now();
        String hour = String.valueOf(currentTime.getHour());
        Map<String, String> map = new HashMap<>();
        map.put("年", year);
        map.put("月", month);
        map.put("日", day);
        map.put("时", hour);
        map.put("客户数量", chiefWxPushMessageDto.getCustomerCount().toString());
        map.put("任务数量", String.valueOf(chiefWxPushMessageDto.getCountNum()));
        return map;
    }

    */
/**
     * 分割拼接类型数据，组装成map
     * 组装管理员数据
     * 2023年12月14日09:19，MOT任务提醒详情如下：共推送客户经理消息404条，成功348条；财富总监消息 236条，成功196条。
     *
     * @param
     * @return
     *//*

    public Map<String, String> parseAdminDataToMap(int qyhTotal, int qyhSuccess, int wfpTotal, int wfpSuccess) {

        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String month = String.valueOf(currentDate.getMonthValue());
        String day = String.valueOf(currentDate.getDayOfMonth());
        LocalTime currentTime = LocalTime.now();
        String hour = String.valueOf(currentTime.getHour());
        Map<String, String> map = new HashMap<>();
        map.put("年", year);
        map.put("月", month);
        map.put("日", day);
        map.put("时", hour);
        map.put("客户经理总条数", String.valueOf(qyhTotal));
        map.put("客户经理成功条数", String.valueOf(qyhSuccess));
        map.put("财富总监总条数", String.valueOf(wfpTotal));
        map.put("财富总监成功条数", String.valueOf(wfpSuccess));
        return map;
    }


    */
/**
     * 分割拼接类型数据，组装成map
     * 组装私募基金销售触发
     * 您有xxx个私募基金销售触发，请在15：00前通过MOT系统进行处理。
     *
     * @param placementDto
     * @return
     *//*

    public Map<String, String> parseChiefNumDataToMap(PlacementDto placementDto) {
        if (null == placementDto) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        map.put("数量", placementDto.getOrganIdCount().toString());
        return map;
    }

    */
/**
     * 分割拼接类型数据，组装成map
     * 组装私募基金销售触发
     * <p>
     * '您有xxxx个私募基金销售触发，请及时处理。'
     *
     * @param count
     * @return
     *//*

    public Map<String, String> parseStaffNumDataToMap(int count) {

        Map<String, String> map = new HashMap<>();
        map.put("数量", String.valueOf(count));
        return map;
    }

    */
/**
     * 分割拼接类型数据，组装成map
     * <p>
     * 您有客户' || to_char(wm_concat(T.CUSTOMER_NAME)) ||'MOT触发必做任务，请及时登录MOT使用录音电话开展通知服务。
     *
     * @param customerName
     * @return
     *//*

    public Map<String, String> parseEventWxInfoDataToMap(String customerName) {

        Map<String, String> map = new HashMap<>();
        map.put("客户姓名", customerName);
        return map;
    }


}
*/
