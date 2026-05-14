/*
package com.ruoyi.work;

import cn.easyes.core.conditions.LambdaEsUpdateWrapper;
import cn.nesc.wm.common.util.DateTimeUtils;
import cn.nesc.wm.notice.enums.MsgReceiverSendStatesEnum;
import cn.nesc.wm.notice.enums.TSmsSendResultEnum;
import cn.nesc.wm.notice.infrustracture.db.mapper.MsgReceiverContantMapper;
import cn.nesc.wm.notice.infrustracture.db.mapper.MsgReceiverMapper;
import cn.nesc.wm.notice.infrustracture.db.model.MsgReceiverContantPo;
import cn.nesc.wm.notice.infrustracture.db.model.MsgReceiverPo;
import cn.nesc.wm.notice.infrustracture.es.mapper.EsMsgReceiverAndContantMapper;
import cn.nesc.wm.notice.infrustracture.es.mapper.EsSmsSendResultEsMapper;
import cn.nesc.wm.notice.infrustracture.es.model.MsgReceiverAndContantEs;
import cn.nesc.wm.notice.infrustracture.es.model.SmsSendResultEs;
import cn.nesc.wm.notice.infrustracture.oracle.mapper.TSmsSendResultOracleMapper;
import cn.nesc.wm.notice.infrustracture.oracle.mapper.TXxfwFsnrFsdxTodayMapper;
import cn.nesc.wm.notice.infrustracture.oracle.mapper.TXxfwFsnrTodayMapper;
import cn.nesc.wm.notice.infrustracture.oracle.model.TSmsSendResultPo;
import cn.nesc.wm.notice.service.OrganizationService;
import cn.nesc.wm.notice.service.model.SmsSendDto;
import cn.nesc.wm.notice.util.NoticeConstants;
import cn.nesc.wm.notice.util.SmsSendUtils;
import com.tencent.cloud.task.sdk.client.model.ExecutableTaskData;
import com.tencent.cloud.task.sdk.client.model.ProcessResult;
import com.tencent.cloud.task.sdk.client.spi.ExecutableTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

*/
/**
 * 短信发送定时任务
 * 每天早上六点到晚上十点每五分钟执行一次
 *//*

@Component
@Slf4j
public class SmsSendMessageTask implements ExecutableTask {
    //发送最大条数
    private static final int MAX_SEND_SIZE = 200;
    @Resource
    private MsgReceiverMapper msgReceiverMapper;
    @Resource
    private MsgReceiverContantMapper msgReceiverContantMapper;
    @Resource
    private SmsSendUtils smsSendUtils;
    @Resource
    private EsMsgReceiverAndContantMapper esMsgReceiverAndContantMapper;

    @Resource
    private TXxfwFsnrFsdxTodayMapper tXxfwFsnrFsdxTodayMapper;

    @Resource
    private TXxfwFsnrTodayMapper tXxfwFsnrTodayMapper;
    @Resource
    private TSmsSendResultOracleMapper tSmsSendResultOracleMapper;

    @Resource
    private EsSmsSendResultEsMapper esSmsSendResultEsMapper;

    @Resource
    private OrganizationService organizationService;
    @Value("${SMS.startTime}")
    private String time;

    @Override
    public ProcessResult execute(ExecutableTaskData executableTaskData) {
        long beginTime = System.currentTimeMillis();
        Integer shardKey = executableTaskData.getShardingArgs().getShardKey();
        log.info("SmsSearchStatusTask分片号：{}", shardKey);
        String batchId = executableTaskData.getTaskMeta().getBatchId();
        log.info("SmsSearchStatusTask批次号：{}", batchId);
        //1、扫描msg_receiver短信发送对象表，发送状态send_status为1未发送的数据
        Date date = getDate(1);
        //上线排除之前的数据
        Date startTime = null;
        try {
            startTime = DateTimeUtils.parseStringToDate(time,DateTimeUtils.DATE_PATTERN_TIME);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("时间转换异常，{}",e);
        }
        List<MsgReceiverPo> msgReceiverList = msgReceiverMapper.selectByStatus(date,
                MsgReceiverSendStatesEnum.STATE_UNSEND.getCode(),startTime);
        log.info("发送总数据量：{}", msgReceiverList.size());

        //没数据直接结束任务
        if (CollectionUtils.isEmpty(msgReceiverList)) {
            return ProcessResult.newSuccessResult();
        }
        //将数据分为200一个批次
        List<List<MsgReceiverPo>> partitionSendList = ListUtils.partition(msgReceiverList, MAX_SEND_SIZE);
        // 定义新增T_SMS_SEND_RESULT的集合
        List<TSmsSendResultPo> oracleList = new ArrayList<>();
        //更新ES短信结果的集合
        List<SmsSendResultEs> smsSendResultEslist = new ArrayList<>();
        //2、拿到数据进行循环，拿发送流水号去查询短信内容表msg_receiver_contant，拿到短信内容等信息
        for (List<MsgReceiverPo> sendList : partitionSendList) {
            //2.1 定义短信发送对象类
            List<SmsSendDto> smsSendlist = new ArrayList<>();
            //2.2 拿到发送对象所有的流水号id
            List<Long> sendFlowIdList = sendList.stream()
                    .map(MsgReceiverPo::getSendFlow)
                    .collect(Collectors.toList());

            //2.3 批量查询流水号对应的短信内容
            List<MsgReceiverContantPo> msgReceiverContantPoList = msgReceiverContantMapper.listByFlowId(sendFlowIdList);
            //key是流水号，对象是短信内容实体
            Map<Long, MsgReceiverContantPo> map = msgReceiverContantPoList.stream()
                    .collect(Collectors.toMap(MsgReceiverContantPo::getFlowId, a -> a));
            //2.4 循环组装发送对象
            List<Long> updateIdList = new ArrayList<>();
            List<Long> errorIdList = new ArrayList<>();

            List<Long> updateSendFlowList = new ArrayList<>();
            List<Long> errorSendFlowList = new ArrayList<>();
            for (MsgReceiverPo msgReceiverPo : sendList) {
                MsgReceiverContantPo msgReceiverContantPo = map.get(msgReceiverPo.getSendFlow());
                //清空
                smsSendlist.clear();
                if (null != msgReceiverContantPo) {
                    SmsSendDto smsSendDto = new SmsSendDto();
                    smsSendDto.setContent(msgReceiverContantPo.getContent());
                    smsSendDto.setMsgId(msgReceiverPo.getId());

                    if (StringUtils.isBlank(msgReceiverPo.getPhone())) {//生产上还有非数字的手机号
                        errorIdList.add(msgReceiverPo.getId());
                        errorSendFlowList.add(msgReceiverPo.getSendFlow());
                        createtSmsSendResultPo(null, msgReceiverContantPo.getContent(),
                                oracleList, smsSendResultEslist, msgReceiverContantPo.getOrganId());
                        log.info("短信发送失败手机号为空，短信发送对象表主键{}", msgReceiverPo.getSendFlow());
                        continue;
                    }
                    //去除所有空格
                    String phone = msgReceiverPo.getPhone();
                    phone = phone.replaceAll("\\s+", "");
                    if (!StringUtils.isNumeric(phone)) {
                        errorIdList.add(msgReceiverPo.getId());
                        errorSendFlowList.add(msgReceiverPo.getSendFlow());
                        createtSmsSendResultPo(null, msgReceiverContantPo.getContent(),
                                oracleList, smsSendResultEslist, msgReceiverContantPo.getOrganId());
                        log.info("短信发送失败手机号非数字有误，短信发送对象表主键{}", msgReceiverPo.getSendFlow());
                        continue;
                    }
                    if (phone.length()>11) {
                        errorIdList.add(msgReceiverPo.getId());
                        errorSendFlowList.add(msgReceiverPo.getSendFlow());
                        createtSmsSendResultPo(null, msgReceiverContantPo.getContent(),
                                oracleList, smsSendResultEslist, msgReceiverContantPo.getOrganId());
                        log.info("短信发送失败手机号长度大于11，短信发送对象表主键{}", msgReceiverPo.getSendFlow());
                        continue;
                    }

                    smsSendDto.setPhoneNumber(phone);
                    smsSendlist.add(smsSendDto);
                    try {
                        //改成单个发送
                        smsSendUtils.smsSend(smsSendlist);
                        updateIdList.add(msgReceiverPo.getId());
                        updateSendFlowList.add(msgReceiverPo.getSendFlow());
                    } catch (Exception e) {
                        log.error("短信发送失败，短信发送对象表主键{}", msgReceiverPo.getSendFlow());
                        errorIdList.add(msgReceiverPo.getId());
                        errorSendFlowList.add(msgReceiverPo.getSendFlow());
                        createtSmsSendResultPo(phone, msgReceiverContantPo.getContent(),
                                oracleList, smsSendResultEslist, msgReceiverContantPo.getOrganId());
                    }

                }
            }
            //3、200一个批次去发送短信
//            smsSendUtils.smsSend(smsSendlist);
            //4、发送完将这一批的数据在短信发送对象表批量改成2，已发送,更新发送时间
            if (CollectionUtils.isNotEmpty(updateIdList)) {
                msgReceiverMapper.updateSendStatusBySendFlow(new Date(),
                        MsgReceiverSendStatesEnum.STATE_ALREADY_SEND.getCode(), updateIdList, null);

                //5、批量更新对象发送表的ES    wm_sms_send_content
                LambdaEsUpdateWrapper<MsgReceiverAndContantEs> wrapper = new LambdaEsUpdateWrapper<>();
                wrapper.in(MsgReceiverAndContantEs::getId, updateIdList)
                        .set(MsgReceiverAndContantEs::getSendStatus, MsgReceiverSendStatesEnum.STATE_ALREADY_SEND.getCode())
                        .set(MsgReceiverAndContantEs::getSendTime, new Date());

                esMsgReceiverAndContantMapper.update(null, wrapper);
            }

            //6、发送完将这一批的数据在短信发送对象表批量改成4，发送失败,更新发送时间
            if (CollectionUtils.isNotEmpty(errorIdList)) {
                msgReceiverMapper.updateSendStatusBySendFlow(new Date(),
                        MsgReceiverSendStatesEnum.STATE_ERROR.getCode(), errorIdList, "手机号格式错误");

                //7、批量更新对象发送表的ES    wm_sms_send_content
                LambdaEsUpdateWrapper<MsgReceiverAndContantEs> wrapper = new LambdaEsUpdateWrapper<>();
                wrapper.in(MsgReceiverAndContantEs::getId, errorIdList)
                        .set(MsgReceiverAndContantEs::getSendStatus, MsgReceiverSendStatesEnum.STATE_ERROR.getCode())
                        .set(MsgReceiverAndContantEs::getRemark, "手机号格式错误");

                esMsgReceiverAndContantMapper.update(null, wrapper);
            }
            if (CollectionUtils.isNotEmpty(updateSendFlowList)) {
                //8、发送完将这一批的数据在msg_receiver_contant短信发送内容表状态批量改成2，已发送,更新发送时间
                msgReceiverContantMapper.updateSendStatusByFlowId(
                        MsgReceiverSendStatesEnum.STATE_ALREADY_SEND.getCode(), updateSendFlowList);
                //9、批量更新oracle的T_XXFW_FSNR_FSDX_TODAY短信发送对象表的FSZT为2
                tXxfwFsnrFsdxTodayMapper.updateByLsh(new Date(),
                        MsgReceiverSendStatesEnum.STATE_ALREADY_SEND.getCode(), updateSendFlowList, null);
                //10、批量更新oracle的T_XXFW_FSNR_TODAY短信发送内容表的FSZT为2
                tXxfwFsnrTodayMapper.updateByLsh(new Date(),
                        MsgReceiverSendStatesEnum.STATE_ALREADY_SEND.getCode(), updateSendFlowList);
            }

            if (CollectionUtils.isNotEmpty(errorSendFlowList)) {
                //11、发送完将这一批的数据在msg_receiver_contant短信发送内容表状态批量改成4，发送失败,更新发送时间
                msgReceiverContantMapper.updateSendStatusByFlowId(
                        MsgReceiverSendStatesEnum.STATE_ERROR.getCode(), errorSendFlowList);
                //12、批量更新oracle的T_XXFW_FSNR_FSDX_TODAY短信发送对象表的FSZT为4
                tXxfwFsnrFsdxTodayMapper.updateByLsh(new Date(),
                        MsgReceiverSendStatesEnum.STATE_ERROR.getCode(), errorSendFlowList, "手机号格式错误");
                //13、批量更新oracle的T_XXFW_FSNR_TODAY短信发送内容表的FSZT为4
                tXxfwFsnrTodayMapper.updateByLsh(new Date(),
                        MsgReceiverSendStatesEnum.STATE_ERROR.getCode(), errorSendFlowList);
            }
            if (CollectionUtils.isNotEmpty(oracleList)) {
                //14、ORACLE对老系统的T_SMS_SEND_RESULT短信发送结果表进行批量新增
                tSmsSendResultOracleMapper.insertBatch(oracleList);
                //批量新增T_SMS_SEND_RESULT_JJMZ
                tSmsSendResultOracleMapper.insertBatchResultJjmz(oracleList);
                //批量新增T_SMS_SEND_RESULT_TODAY
                tSmsSendResultOracleMapper.insertTodayBatch(oracleList);
            }
            if (CollectionUtils.isNotEmpty(smsSendResultEslist)) {
                //15、对ES短信结果表wm_sms_send_result批量新增数据
                esSmsSendResultEsMapper.insertBatch(smsSendResultEslist);
            }

        }
        long endTime = System.currentTimeMillis();
        log.info("SmsSearchStatusTask分片号{}批次号{}，执行时长{}", shardKey, batchId, endTime - beginTime);
        return ProcessResult.newSuccessResult();
    }

    private Date getDate(int num) {
        // 获取当前时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 获取两天前的时间
        LocalDateTime twoDaysAgoDateTime = currentDateTime.minusDays(num);
        // 将LocalDateTime转换为Date
        Date twoDaysAgoDate = Date.from(twoDaysAgoDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return twoDaysAgoDate;
    }


    private void createtSmsSendResultPo(String phone, String content, List<TSmsSendResultPo> oracleList,
                                        List<SmsSendResultEs> smsSendResultEslist, String organId) {
        TSmsSendResultPo tSmsSendResultPo = new TSmsSendResultPo();
        tSmsSendResultPo.setSeqNo(tSmsSendResultOracleMapper.selectTSmsSendSesultNextVal());
        if (StringUtils.isNotBlank(phone)&& StringUtils.isNumeric(phone)&&phone.length()<19) {
            tSmsSendResultPo.setTelNum(Long.valueOf(phone));
        }
        tSmsSendResultPo.setFailedReason("手机号格式错误");
        tSmsSendResultPo.setSendResult(TSmsSendResultEnum.SEND_ERROR.getCode());
        if (StringUtils.isNotBlank(organId) && StringUtils.isNumeric(organId)) {
            tSmsSendResultPo.setBranchId(Integer.parseInt(organizationService.codeToBranchNo(organId)));
        }
        tSmsSendResultPo.setFsnr(replacePatterns(content));
        tSmsSendResultPo.setOcDate(Integer.parseInt(
                DateTimeUtils.parseDateToString(new Date(), DateTimeUtils.SIMPLE_DATE_FORMAT_BASE_2)));
        tSmsSendResultPo.setRecTime(new Date());
        SmsSendResultEs smsSendResultEs = new SmsSendResultEs();
        smsSendResultEs.setOrganId(organId);
        sssemblyEs(smsSendResultEs, tSmsSendResultPo);
        oracleList.add(tSmsSendResultPo);
        smsSendResultEslist.add(smsSendResultEs);
    }

    */
/**
     * 组装新增ES短信结果表的数据
     *
     * @param smsSendResultEs
     * @param tSmsSendResultPo
     *//*

    public void sssemblyEs(SmsSendResultEs smsSendResultEs, TSmsSendResultPo tSmsSendResultPo) {
        smsSendResultEs.setSeqNo(BigDecimal.valueOf(tSmsSendResultPo.getSeqNo()));
        smsSendResultEs.setId(BigDecimal.valueOf(tSmsSendResultPo.getSeqNo()));
        if (null != tSmsSendResultPo.getTelNum()) {
            smsSendResultEs.setTelNum(BigDecimal.valueOf(tSmsSendResultPo.getTelNum()));
        }
        smsSendResultEs.setSendContent(tSmsSendResultPo.getFsnr());
        smsSendResultEs.setSendTime(tSmsSendResultPo.getSendTime());
        smsSendResultEs.setSendResult(tSmsSendResultPo.getSendResult());
        smsSendResultEs.setFailedReason(tSmsSendResultPo.getFailedReason());
        smsSendResultEs.setContentPhone(tSmsSendResultPo.getFsnr() + tSmsSendResultPo.getTelNum());
        smsSendResultEs.setOcDate(Long.valueOf(tSmsSendResultPo.getOcDate()));
        smsSendResultEs.setRecTime(tSmsSendResultPo.getRecTime());
        smsSendResultEs.setCreateTime(new Date());
        smsSendResultEs.setCreateBy(NoticeConstants.DEFAULT_OPERATOR);
        smsSendResultEs.setUpdateTime(new Date());
        smsSendResultEs.setUpdateBy(NoticeConstants.DEFAULT_OPERATOR);

    }

    // 封装所有的replace逻辑
    public String replacePatterns(String fsnr) {
        fsnr = fsnr.replace("-回复TD退订", "")
                .replace("【东北证券】回复TD退订", "")
                .replace("回0000拒收【东北证券】", "")
                .replace("回0拒收【东北证券】", "")
                .replace("取消回复0【东北证券】", "")
                .replace("【东北证券】", "")
                .replace("退订回复0", "")
                .replace("取消回复0", "")
                .replace("回复TD退订", "")
                .replace("回0000拒收", "")
                .replace("-拒收请回复R", "");
        return fsnr;
    }

}
*/
