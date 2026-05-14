package com.ruoyi.util;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.system.domain.WxContentDto;
import com.ruoyi.system.domain.WxSendMessageRequest;
import com.ruoyi.system.domain.WxSendResponse;
import com.ruoyi.system.domain.WxTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;




/**
 * 企微消息推送工具类
 * 单应用版本
 * 企微对接平台地址 https://developer.work.weixin.qq.com/document/path/91039
 */

@Slf4j
@Component
public class WxHttpUtils {

    @Value("${layim.weixin.WX_GET_TOKEN_URL}")
    private String wxGetTokenUrl;

    @Value("${layim.weixin.CORPID}")
    private String corpId;

    @Value("${layim.weixin.CORP_SECRET}")
    private String corpsecret;

    @Value("${layim.weixin.WX_SEND_TEXT_MESSAGE}")
    private String wxSendTextMessage;

    @Value("${layim.weixin.AGENT_ID}")
    private int agentId;
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


    /**
     * 推送企微消息
     *
     * @param userId
     * @param message
     * @return
     */
    public WxSendResponse sendWxMessages(String userId, String message) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(message)) {
            throw new RuntimeException("消息和被发送人ID不可为空");
        }
        //组装发送参数
        WxSendMessageRequest wxSendMessageRequest = new WxSendMessageRequest();
        wxSendMessageRequest.setTouser(userId);
        wxSendMessageRequest.setAgentid(agentId);
        WxContentDto wxContentDto = new WxContentDto();
        wxContentDto.setContent(message);
        wxSendMessageRequest.setText(wxContentDto);
        //获得token
        String token = getWxNormalToken(Boolean.TRUE);
        WxSendResponse wxSendResponse = send(token, wxSendMessageRequest);
        //企业微信的机制是两小时重复查询返回同一个token，但是过期时间显示不变，且微信可能提前让token过期，所以这里加判断，过期了重新获取，再放入redis
        //重新获取token，重新发起请求
        if (EXPIRED_CODE.equals(wxSendResponse.getErrcode().toString()) ||
                INVALID_CODE.equals(wxSendResponse.getErrcode().toString())) {
            String wxNormalToken = getWxNormalToken(Boolean.FALSE);
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
    public String getWxNormalToken(Boolean flag) {
        if (flag) {
            //先查询redis这个token有没有过期，没过期直接拿了返回
            if (Boolean.TRUE.equals(redisTemplate.hasKey(redisWxKey))) {
                Object value = redisTemplate.opsForValue().get(redisWxKey);
                if (null != value) {
                    return value.toString();
                }
            }
        }
        String url = String.format(wxGetTokenUrl, corpId, corpsecret);
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
            redisTemplate.opsForValue().set(redisWxKey, token, expiresTime, TimeUnit.SECONDS);
            return token;
        } else {//失败
            log.error("getWxToken error");
            throw new RuntimeException();
        }
    }


}
