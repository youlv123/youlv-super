/*
package com.ruoyi.work;

import cn.nesc.wm.common.util.DateTimeUtils;
import cn.nesc.wm.notice.enums.MsgReceiverSendStatesEnum;
import cn.nesc.wm.notice.enums.SmsStateEnum;
import cn.nesc.wm.notice.enums.TSmsSendResultEnum;
import cn.nesc.wm.notice.infrustracture.db.mapper.MsgReceiverContantMapper;
import cn.nesc.wm.notice.infrustracture.db.mapper.MsgReceiverMapper;
import cn.nesc.wm.notice.infrustracture.db.model.MsgReceiverPo;
import cn.nesc.wm.notice.infrustracture.es.EsSmsSendResultManager;
import cn.nesc.wm.notice.infrustracture.es.mapper.EsMsgReceiverAndContantMapper;
import cn.nesc.wm.notice.infrustracture.es.mapper.EsSmsSendResultEsMapper;
import cn.nesc.wm.notice.infrustracture.es.model.MsgReceiverAndContantEs;
import cn.nesc.wm.notice.infrustracture.es.model.SmsSendResultEs;
import cn.nesc.wm.notice.infrustracture.oracle.mapper.TSmsSendResultOracleMapper;
import cn.nesc.wm.notice.infrustracture.oracle.mapper.TXxfwFsnrFsdxTodayMapper;
import cn.nesc.wm.notice.infrustracture.oracle.mapper.TXxfwFsnrTodayMapper;
import cn.nesc.wm.notice.infrustracture.oracle.model.TSmsSendResultPo;
import cn.nesc.wm.notice.service.OrganizationService;
import cn.nesc.wm.notice.service.model.MsgResultDto;
import cn.nesc.wm.notice.service.model.SmsSendStausDto;
import cn.nesc.wm.notice.service.model.UpdateSmsResultDto;
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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import test.common.json.JsonUtil;

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
 * 短信查询发送状态任务
 * 每天早上六点十分到晚上十点十分每五分钟执行一次
 *//*

@Component
@Slf4j
public class SmsSearchStatusTask implements ExecutableTask {
    //发送最大条数
    private static final int MAX_SEND_SIZE = 200;
    @Resource
    private MsgReceiverMapper msgReceiverMapper;
    @Resource
    private SmsSendUtils smsSendUtils;
    @Resource
    private TSmsSendResultOracleMapper tSmsSendResultOracleMapper;
    @Resource
    private EsMsgReceiverAndContantMapper esMsgReceiverAndContantMapper;
    @Resource
    private OrganizationService organizationService;

    @Resource
    private EsSmsSendResultEsMapper esSmsSendResultEsMapper;


    @Resource
    private TXxfwFsnrFsdxTodayMapper tXxfwFsnrFsdxTodayMapper;

    @Resource
    private TXxfwFsnrTodayMapper tXxfwFsnrTodayMapper;

    @Resource
    private MsgReceiverContantMapper msgReceiverContantMapper;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplateCluster1 ;

    @Value("${SMS.startTime}")
    private String time;

    @Override
    public ProcessResult execute(ExecutableTaskData executableTaskData) {
        long beginTime = System.currentTimeMillis();
        Integer shardKey = executableTaskData.getShardingArgs().getShardKey();
        log.info("SmsSearchStatusTask分片号：{}", shardKey);
        String batchId = executableTaskData.getTaskMeta().getBatchId();
        log.info("SmsSearchStatusTask批次号：{}", batchId);
        Date date = getDate(3);
        //上线排除之前的数据
        Date startTime = null;
        try {
            startTime = DateTimeUtils.parseStringToDate(time,DateTimeUtils.DATE_PATTERN_TIME);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("时间转换异常，{}",e);
        }
        //1、扫描msg_receiver短信发送对象表，发送状态send_status为2已发送的数据,扫描近3天的数据，因为有部分数据的状态始终是未知，3天依旧是未知，就不用查询了，这数据有问题
        List<MsgReceiverPo> msgReceiverList = msgReceiverMapper.selectByStatus(date, MsgReceiverSendStatesEnum.STATE_ALREADY_SEND.getCode(),startTime);
        log.info("获取结果总数据量：{}", msgReceiverList.size());
        //没数据直接结束任务
        if (CollectionUtils.isEmpty(msgReceiverList)) {
            return ProcessResult.newSuccessResult();
        }
        //将数据分为200一个批次
        List<List<MsgReceiverPo>> partitionSendList = ListUtils.partition(msgReceiverList, MAX_SEND_SIZE);

        //2、循环查询短信状态
        for (List<MsgReceiverPo> sendList : partitionSendList) {

            //2.1 拿到发送对象所有的主键id，也就是msgid
            List<String> msgIdList = sendList.stream()
                    .map(a -> String.valueOf(a.getId()))
                    .collect(Collectors.toList());
            //主键id和对象，用于后面拿到transactionId
            Map<Long, MsgReceiverPo> msgMap = sendList.stream()
                    .collect(Collectors.toMap(MsgReceiverPo::getId, a -> a));
            //2.2 拿主键id查询在es的这一批的数据
            List<MsgReceiverAndContantEs> msgReceiverAndContantEsList = esMsgReceiverAndContantMapper.selectBatchIds(
                    msgIdList);
            //2.3 将es数据抓换成map方便后面拿出es数据的字段赋值
            Map<Long, MsgReceiverAndContantEs> map = msgReceiverAndContantEsList.stream()
                    .collect(Collectors.toMap(a -> a.getId().longValue(), a -> a));
            //2.4 批量查询短信发送结果
            List<SmsSendStausDto> smsSendStausDtoList = smsSendUtils.getsmsSendStutus(msgIdList);
            if (CollectionUtils.isEmpty(smsSendStausDtoList)) {
                continue;
            }
            // 定义新增T_SMS_SEND_RESULT的集合
            List<TSmsSendResultPo> oracleList = new ArrayList<>();
            // 定义更新msg_receiver的集合
            List<UpdateSmsResultDto> mysqlList = new ArrayList<>();
            // 定义更新ES的集合
            List<MsgReceiverAndContantEs> esList = new ArrayList<>();
            //更新ES短信结果的集合
            List<SmsSendResultEs> smsSendResultEslist = new ArrayList<>();
            //2.5 循环分类短信发送成功还是失败
            for (SmsSendStausDto smsSendStausDto : smsSendStausDtoList) {
                TSmsSendResultPo tSmsSendResultPo = new TSmsSendResultPo();
                tSmsSendResultPo.setSeqNo(tSmsSendResultOracleMapper.selectTSmsSendSesultNextVal());
                if (StringUtils.isNotBlank(smsSendStausDto.getMobileTel()) && StringUtils.isNumeric(smsSendStausDto.getMobileTel())) {
                    int length = smsSendStausDto.getMobileTel().length();
                    if (length < 19) {
                        tSmsSendResultPo.setTelNum(Long.valueOf(smsSendStausDto.getMobileTel()));
                    }
                }
//                tSmsSendResultPo.setFailedReason();

                UpdateSmsResultDto updateSmsResultDto = new UpdateSmsResultDto();
                updateSmsResultDto.setId(smsSendStausDto.getMsgId());
                MsgReceiverPo po = msgMap.get(smsSendStausDto.getMsgId());
                updateSmsResultDto.setSendFlow(po.getSendFlow());
                if (SmsStateEnum.SMS_STATE_SUCCESS.getCode().equals(smsSendStausDto.getStateCode())) {//成功
                    tSmsSendResultPo.setSendResult(TSmsSendResultEnum.SEND_SUCCESS.getCode());
                    updateSmsResultDto.setSendStatus(MsgReceiverSendStatesEnum.STATE_SUCCESS.getCode());
                } else if (SmsStateEnum.SMS_STATE_ERROR.getCode().equals(smsSendStausDto.getStateCode())) {//失败
                    tSmsSendResultPo.setSendResult(TSmsSendResultEnum.SEND_ERROR.getCode());
                    updateSmsResultDto.setSendStatus(MsgReceiverSendStatesEnum.STATE_ERROR.getCode());
                }
                //设置短信平台传回来的送达时间
                Date sendDate = null;
                try {
                    sendDate = smsSendStausDto.getStateTime() == null ? null : DateTimeUtils.parseStringToDate(
                            smsSendStausDto.getStateTime(), DateTimeUtils.DATE_PATTERN_TIME);
                    tSmsSendResultPo.setRecTime(new Date());
                    updateSmsResultDto.setSendDate(sendDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    log.info("解析短信平台传回的发送时间失败{}",e);
                }
                //组装kafka对象发送消息给分发平台
                MsgResultDto msgResultDto = new MsgResultDto();
                if (StringUtils.isNotBlank((po.getTransactionId()))) {
                    msgResultDto.setTransactionId(po.getTransactionId());
                    msgResultDto.setSendStatus(updateSmsResultDto.getSendStatus());
                    msgResultDto.setSendDate(updateSmsResultDto.getSendDate());
                    sendMessage(msgResultDto);
                }

                //拿到es
                MsgReceiverAndContantEs es = map.get(smsSendStausDto.getMsgId());
                SmsSendResultEs smsSendResultEs = new SmsSendResultEs();
                if (null != es) {
                    smsSendResultEs.setOrganId(es.getOrganId());
                    if (StringUtils.isNotBlank(es.getOrganId()) && StringUtils.isNumeric(es.getOrganId())) {
                        tSmsSendResultPo.setBranchId(Integer.parseInt(organizationService.codeToBranchNo(es.getOrganId())));
                    }
                    if (StringUtils.isNotBlank(es.getContent())) {
                        tSmsSendResultPo.setFsnr(replacePatterns(es.getContent()));
                    }
                    tSmsSendResultPo.setSendTime(es.getSendTime());
                    es.setSendDate(sendDate);
                    es.setSendStatus(updateSmsResultDto.getSendStatus());
                    esList.add(es);
                }
                mysqlList.add(updateSmsResultDto);
                tSmsSendResultPo.setOcDate(Integer.parseInt(
                        DateTimeUtils.parseDateToString(new Date(), DateTimeUtils.SIMPLE_DATE_FORMAT_BASE_2)));

                //组装新增ES短信结果表的数据
                sssemblyEs(smsSendResultEs, tSmsSendResultPo);
                smsSendResultEslist.add(smsSendResultEs);
                oracleList.add(tSmsSendResultPo);
                //4、更新oracle的T_XXFW_FSNR_FSDX_TODAY短信发送对象表的FSZT
                tXxfwFsnrFsdxTodayMapper.updateBatchBySendFlow(updateSmsResultDto);
                //5、更新oracle的T_XXFW_FSNR_TODAY短信发送内容表的FSZT
                tXxfwFsnrTodayMapper.updateBatchBySendFlow(updateSmsResultDto);
            }
            //3、MYSQL批量更新msg_receiver短信发送对象表发送状态和发送日期
            msgReceiverMapper.updateBatch(mysqlList);

            //6、发送完将这一批的数据在msg_receiver_contant短信发送内容表状态更新,更新发送时间
            msgReceiverContantMapper.updateBatchBySendFlow(mysqlList);
            //7、ES批量更新wm_sms_send_content索引的发送状态和发送日期
            esMsgReceiverAndContantMapper.updateBatchByIds(esList);
            //8、ORACLE对老系统的T_SMS_SEND_RESULT（该表为昨日短信结果表）短信发送结果表进行批量新增
            tSmsSendResultOracleMapper.insertBatch(oracleList);
            //批量新增T_SMS_SEND_RESULT_JJMZ（该表为当日短信结果表）
            tSmsSendResultOracleMapper.insertBatchResultJjmz(oracleList);
            //批量新增T_SMS_SEND_RESULT_TODAY
            tSmsSendResultOracleMapper.insertTodayBatch(oracleList);
            //9、对ES短信结果表wm_sms_send_result批量新增数据
            esSmsSendResultEsMapper.insertBatch(smsSendResultEslist);
        }
        long endTime = System.currentTimeMillis();
        log.info("SmsSearchStatusTask分片号{}批次号{}，执行时长{}", shardKey, batchId, endTime - beginTime);
        return ProcessResult.newSuccessResult();
    }

    */
/**
     * 获取几天前的时间
     *
     * @param num 传入几就距离几天
     * @return
     *//*

    private Date getDate(int num) {
        // 获取当前时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 获取两天前的时间
        LocalDateTime twoDaysAgoDateTime = currentDateTime.minusDays(num);
        // 将LocalDateTime转换为Date
        Date twoDaysAgoDate = Date.from(twoDaysAgoDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return twoDaysAgoDate;
    }


    */
/**
     * 发送消息
     *//*

    public void sendMessage(MsgResultDto msgResultDto) {
*/
/*        try {
            String message = JsonUtil.objToJson(msgResultDto);
            //第一个参数是topic，
            //第二个是key，用来进行分区的，具有相同键的消息被发送到同一个分区。这对于具有相同键的消息的顺序性和有序性非常重要，因为它们将被发送到同一个分区并按照顺序处理。
            //第三个参数是指定的消息，是泛型，这里只用一个任务id即可。
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplateCluster1.send("SMS_RESULT",msgResultDto.getTransactionId(),message);
            listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("sendMessage success");
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("sendMessage error", ex);
                }
            });
        } catch (Exception e) {
            log.error("sendMessage exception", e);
        }*//*


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
        if (null != tSmsSendResultPo.getOcDate()) {
            smsSendResultEs.setOcDate(Long.valueOf(tSmsSendResultPo.getOcDate()));
        }
        smsSendResultEs.setRecTime(tSmsSendResultPo.getRecTime());
        smsSendResultEs.setCreateTime(new Date());
        smsSendResultEs.setCreateBy(NoticeConstants.DEFAULT_OPERATOR);
        smsSendResultEs.setUpdateTime(new Date());
        smsSendResultEs.setUpdateBy(NoticeConstants.DEFAULT_OPERATOR);

    }


}
*/
