package com.ruoyi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.enums.PushFlagEnum;
import com.ruoyi.config.WxConfig;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.IPushWxMessageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * 企微消息推送工具类
 * 多应用自动适配版本
 * 企微对接平台地址 https://developer.work.weixin.qq.com/document/path/91039
 */

@Slf4j
@Component
public class WxHttpMultiUtils {

    @Value("${layim.weixin.WX_GET_TOKEN_URL}")
    private String wxGetTokenUrl;

    @Value("${layim.weixin.WX_SEND_TEXT_MESSAGE}")
    private String wxSendTextMessage;

    @Resource
    public RedisTemplate<String, String> redisTemplate;

    @Resource
    private HttpClient httpClient;

    private static final String SUCCESS_CODE = "0";

    //token过期标识，还有个40014，不合法的access_token。不知道这两个有什么区别，微信官方建议根据42001判断
    private static final String EXPIRED_CODE = "42001";

    //不合法的access_token
    private static final String INVALID_CODE = "40014";

    //微信token的redis用的key
    private static final String redisWxKey = "redisWxKey";

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private IPushWxMessageRecordService ipushWxMsgRecordService;


/**
 * 发送微信消息并记录发送结果
 *
 * @param pushWxMessageRecord
 */
    public void sendWxMessagesAndRecord(PushWxMessageRecord pushWxMessageRecord) {
        if ( Objects.isNull(pushWxMessageRecord)|| StringUtils.isBlank(pushWxMessageRecord.getWxId()) || StringUtils.isBlank(pushWxMessageRecord.getWxMessageContent()) ||StringUtils.isBlank(pushWxMessageRecord.getApplicationId())) {
            throw new RuntimeException("消息和被发送人ID和应用ID不可为空！");
        }
        try {
            WxSendResponse wxSendResponse = sendWxMessages(pushWxMessageRecord.getWxId(), pushWxMessageRecord.getWxMessageContent(), pushWxMessageRecord.getApplicationId());
            if (SUCCESS_CODE.equals(wxSendResponse.getErrcode().toString())){
                pushWxMessageRecord.setPushFlag(PushFlagEnum.PUSH_SUCCESS.getCode());
//                pushWxMessageRecord.setWxMsgid(wxSendResponse.getMsgid());//暂时用不上
            }else {
                pushWxMessageRecord.setPushFlag(PushFlagEnum.PUSH_FAIL.getCode());
            }
            pushWxMessageRecord.setErrorLog(wxSendResponse.getErrmsg());
        } catch (Exception e) {
            log.error("sendWxMessagesAndRecord error", e);
            pushWxMessageRecord.setPushFlag(PushFlagEnum.PUSH_FAIL.getCode());
            pushWxMessageRecord.setErrorLog(e.getMessage());
        }finally {
            ipushWxMsgRecordService.insertPushWxMessageRecord(pushWxMessageRecord);
        }
    }

    /**
     * 推送企微消息
     * @param userId 企微用户id
     * @param message 消息
     * @param applicationId 应用id
     * @return
     */
    public WxSendResponse sendWxMessages(String userId, String message, String applicationId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(message) || StringUtils.isBlank(applicationId)) {
            throw new RuntimeException("消息和被发送人ID不可为空");
        }
        Map<String, WxConfig.WxConfigDTO> wxConfigMap = wxConfig.getWxConfigMap();
        WxConfig.WxConfigDTO wxConfigDTO = wxConfigMap.get(applicationId);
        if (null == wxConfigDTO) {
            throw new RuntimeException("没有找到对应的应用");
        }
        String corpId = wxConfigDTO.getCorpId();
        String corpSecret = wxConfigDTO.getCorpSecret();
        //组装发送参数
        WxSendMessageRequest wxSendMessageRequest = new WxSendMessageRequest();
        wxSendMessageRequest.setTouser(userId);
        wxSendMessageRequest.setAgentid(Integer.parseInt(applicationId));
        WxContentDto wxContentDto = new WxContentDto();
        wxContentDto.setContent(message);
        wxSendMessageRequest.setText(wxContentDto);
        //获得token
        String key = redisWxKey + applicationId;
        String token = getWxNormalToken(Boolean.TRUE, corpId, corpSecret, key);
        WxSendResponse wxSendResponse = send(token, wxSendMessageRequest);
        //企业微信的机制是两小时重复查询返回同一个token，但是过期时间显示不变，且微信可能提前让token过期，所以这里加判断，过期了重新获取，再放入redis
        //重新获取token，重新发起请求
        if (EXPIRED_CODE.equals(wxSendResponse.getErrcode().toString()) ||
                INVALID_CODE.equals(wxSendResponse.getErrcode().toString())) {
            String wxNormalToken = getWxNormalToken(Boolean.FALSE, corpId, corpSecret, key);
            return send(wxNormalToken, wxSendMessageRequest);
        }
        return wxSendResponse;
    }

    /***
     * 发送企微消息
     * @param token
     * @param wxSendMessageRequest
     * @return
     */
    public WxSendResponse send(String token, WxSendMessageRequest wxSendMessageRequest) {
        //请求url
        String url = String.format(wxSendTextMessage, token);
        //推送企微消息

        String response = httpClient.post(url, wxSendMessageRequest);
        if (null == response) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        WxSendResponse wxSendResponse = new WxSendResponse();
        try {
            wxSendResponse = objectMapper.readValue(response, WxSendResponse.class);

        } catch (JsonProcessingException e) {
            log.error("sendWxMessages error", e);
            throw new RuntimeException(e);
        }
        return wxSendResponse;
    }


    /**
     * 获得应用的Token
     * flag只是控制是否需要查询redis，某些情况redis没有过期，微信官方过期了，这种情况跳过查询，直接获取最新的去更新reids
     *
     * @return
     */
    public String getWxNormalToken(Boolean flag, String corpId, String corpSecret, String key) {
        if (flag) {
            //先查询redis这个token有没有过期，没过期直接拿了返回
            if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
                Object value = redisTemplate.opsForValue().get(key);
                if (null != value) {
                    return value.toString();
                }
            }
        }
        String url = String.format(wxGetTokenUrl, corpId, corpSecret);
        //发起请求
        String wxTokenResponse = httpClient.get(url);
        if (null == wxTokenResponse) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        WxTokenResponse response = new WxTokenResponse();
        try {
            response = objectMapper.readValue(wxTokenResponse, WxTokenResponse.class);
        } catch (JsonProcessingException e) {
            log.error("getWxToken error", e);
            throw new RuntimeException(e);
        }
        //成功
        if (SUCCESS_CODE.equals(response.getErrcode().toString())) {
            String token = response.getAccess_token();
            String expiresIn = response.getExpires_in();
            //大概过期时间是7200秒
            Long expiresTime = Long.valueOf(expiresIn);
            redisTemplate.opsForValue().set(key, token, expiresTime, TimeUnit.SECONDS);
            return token;
        } else {//失败
            log.error("getWxToken error");
            throw new RuntimeException();
        }
    }


}
