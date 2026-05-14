/*
package com.ruoyi.work;

import cn.nesc.wm.notice.enums.SmsSendResultEnum;
import cn.nesc.wm.notice.service.model.SmsSendDto;
import cn.nesc.wm.notice.service.model.SmsSendStausDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.common.exception.ExceptionUtil;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * 短信发送工具类
 *//*

@Component
@Slf4j
public class SmsSendUtils {

    private static final String SUCCESS_CODE = "0";
    //发送最大条数
    private static final int MAX_SEND_SIZE = 200;
    @Value("${SMS.SEND_URL}")
    private String sendUrl;
    @Value("${SMS.SEARCH_URL}")
    private String searchUrl;
    @Value("${SMS.DLZH}")
    private String dlzh;
    @Value("${SMS.DLMM}")
    private String dlmm;
    @Resource
    private SmsClient smsClient;

    */
/**
     * 查询结果转换
     *
     * @param xmlString
     * @return
     *//*

    public static List<SmsSendStausDto> parseXmlResponse(String xmlString) {
        List<SmsSendStausDto> list = new ArrayList<>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new java.io.StringReader(xmlString));
            Element smsStateResponse = document.getRootElement();
            //返回结果不是成功，直接抛异常，错误信息
            String resultCode = smsStateResponse.elementText("ResultCode");

            if (SmsSendResultEnum.SMS_NO_STATUS.getCode().equals(resultCode)){
                return new ArrayList<>();
            }
            if (!SUCCESS_CODE.equals(resultCode)) {
                String resultMsg = smsStateResponse.elementText("ResultMsg");
                if (StringUtils.isNotBlank(resultMsg)) {
                    throw ExceptionUtil.createException(resultMsg);
                }
            }
            //组装结果数据
            Element smsStateList = smsStateResponse.element("SmsStateList");
            for (Element smsState : smsStateList.elements("SmsState")) {
                SmsSendStausDto a = new SmsSendStausDto();
                a.setMsgId(Long.valueOf(smsState.elementText("MsgId")));
                a.setMobileTel(smsState.elementText("MobileTel"));
                a.setStateCode(smsState.elementText("StateCode"));
                a.setStateTime(smsState.elementText("StateTime"));
                list.add(a);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return list;
    }

    */
/**
     * 短信发送，单次发送200条
     *
     * @return
     *//*


    public String smsSend(List<SmsSendDto> sendList) {
        if (CollectionUtils.isEmpty(sendList)) {
            return null;
        }
        List<List<SmsSendDto>> partitionSendList = ListUtils.partition(sendList, MAX_SEND_SIZE);
        for (List<SmsSendDto> list : partitionSendList) {
            // 创建Document对象
            Document document1 = DocumentHelper.createDocument();
            // 添加根元素ShortMessages
            Element shortMessages = document1.addElement("ShortMessages");
            // 添加子元素Version
            shortMessages.addElement("Version").addText("1.0");
            // 添加子元素DLZH
            shortMessages.addElement("DLZH").addText(dlzh);
            // 添加子元素DLMM
            shortMessages.addElement("DLMM").addText(dlmm);
            // 添加子元素YYBDM（此处为空）
            shortMessages.addElement("YYBDM").addText("");
            for (SmsSendDto entity : list) {
                // 添加Messages元素
                Element messages = shortMessages.addElement("Messages");
                try {
                    // 添加第一个Message元素
                    Element message1 = messages.addElement("Message");
                    message1.addElement("DXNR").addText(URLEncoder.encode(entity.getContent(), "GBK"));
                    message1.addElement("SJHM").addText(entity.getPhoneNumber());
                    message1.addElement("XXID").addText(entity.getMsgId().toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            // 设置XML编码为gbk（请注意，这不会改变文档内容的编码，只是声明）
            document1.setXMLEncoding("gbk");
            // 输出XML字符串（这里只是输出到控制台，实际使用时可能用于发起HTTP请求）
            String xmlString = document1.asXML();
            String encodedXmlData = "";
            try {
                //这边会把空格转换成+    %3C%3Fxml+version   正常的+应该转换成%20
                encodedXmlData = URLEncoder.encode(xmlString, "GBK").replace("+", "%20");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String LX = "1";//处理类型（功能号）
            String url = String.format(sendUrl, LX, encodedXmlData);
            //发起请求
            String xmlContent = smsClient.get(url);
            if (null == xmlContent) {
                return null;
            }
            try {
                SAXReader reader = new SAXReader();
                Document document = reader.read(new java.io.StringReader(xmlContent));
                Element smsSendResponse = document.getRootElement();
                String resultCode = smsSendResponse.elementText("ResultCode");
                if (!SUCCESS_CODE.equals(resultCode)) {
                    String resultMsg = smsSendResponse.elementText("ResultMsg");
                    if (StringUtils.isNotBlank(resultMsg)) {
                        throw ExceptionUtil.createException(resultMsg);
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    */
/**
     * 根据短信消息id获取短信发送状态,只能查询三天内的数据状态
     *
     * @param msgIdList
     * @return
     *//*

    public List<SmsSendStausDto> getsmsSendStutus(List<String> msgIdList) {
        if (CollectionUtils.isEmpty(msgIdList)) {
            return null;
        }
        //拼接手机号
        String smsIdStr = String.join(";", msgIdList);
        System.out.println(smsIdStr);
        String LX = "2";
        String surl = String.format(searchUrl, LX, dlzh, dlmm);
        String url = surl + smsIdStr;

        //发起请求
        String xmlContent = smsClient.get(url);
        if (null == xmlContent) {
            return null;
        }
        //转换结果
        List<SmsSendStausDto> smsSendStausDtoList = parseXmlResponse(xmlContent);
        return smsSendStausDtoList;
    }
}





*/
