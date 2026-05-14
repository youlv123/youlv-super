package com.ruoyi.weixin.service.impl;


import com.ruoyi.weixin.domain.WeiXinArticleDTO;
import com.ruoyi.weixin.domain.WeiXinSendDTO;
import com.ruoyi.weixin.mapper.WeiXinArticleMapper;
import com.ruoyi.weixin.service.AutoPushDraft;
import com.ruoyi.weixin.service.IWeiXinSendService;
import com.ruoyi.weixin.service.IWeiXinArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpDraftService;
import me.chanjar.weixin.mp.api.WxMpService;

import me.chanjar.weixin.mp.bean.draft.WxMpAddDraft;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftArticles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 4、自动上传文章到微信草稿箱
 */
@Slf4j
@Service
@AllArgsConstructor
public class AutoPushDraftImpl implements AutoPushDraft {

    @Autowired
    private IWeiXinArticleService iWeixinArticleService;

    @Autowired
    private WeiXinArticleMapper weixinArticleMapper;
    @Autowired
    private IWeiXinSendService iWeixinSendService;


    private final WxMpService wxService;

    @Override
    public void AutoPushDraft() {
        //1、扫描微信公众号文章表 weixin_article，自动审核状态为1和人工审核状态为1的数据
        List<WeiXinArticleDTO> list = weixinArticleMapper.selectWeixinArticleAll();
        //2、拿到数据后调用微信草稿上传
        for (WeiXinArticleDTO weixinArticleDTO : list) {
            try {
                List<WxMpDraftArticles> articles = new ArrayList<>();
                WxMpDraftArticles wxMpDraftArticles = new WxMpDraftArticles();
                wxMpDraftArticles.setTitle(weixinArticleDTO.getTitle());//标题
                wxMpDraftArticles.setAuthor(weixinArticleDTO.getAuthor());//作者
                wxMpDraftArticles.setDigest(weixinArticleDTO.getDigest());//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前54个字。
                wxMpDraftArticles.setContent(weixinArticleDTO.getContent());//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
                wxMpDraftArticles.setContentSourceUrl(weixinArticleDTO.getContentSourceUrl());//图文消息的原文地址，即点击“阅读原文”后的URL
                wxMpDraftArticles.setThumbMediaId(weixinArticleDTO.getThumbMediaId());//图文消息的封面图片素材id（必须是永久MediaID）
                wxMpDraftArticles.setNeedOpenComment(1);//Uint32 是否打开评论，0不打开(默认)，1打开
                wxMpDraftArticles.setOnlyFansCanComment(0);//Uint32 是否粉丝才可评论，0所有人可评论(默认)，1粉丝才可评论

                articles.add(wxMpDraftArticles);
                WxMpAddDraft wxMpAddDraft = new WxMpAddDraft();
                wxMpAddDraft.setArticles(articles);
//                WxMpService wxMpService = new WxMpServiceImpl();
                WxMpDraftService wxMpDraftService = wxService.getDraftService();
                String mediaId = wxMpDraftService.addDraft(wxMpAddDraft);//上传后的获取标志，长度不固定，但不会超过 128 字符
                WeiXinSendDTO weiXinSendDTO = new WeiXinSendDTO();
                String s = "2024-01-20 19:00:00";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date time = null;
                try {
                    time = simpleDateFormat.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                weiXinSendDTO.setTimingPublishTime(time);
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String bizDate = currentDate.format(formatter);
                weiXinSendDTO.setBizDate(bizDate);
                weiXinSendDTO.setBelongAccount("装逼精");
                weiXinSendDTO.setMediaId(mediaId);
                weiXinSendDTO.setArticleId(weixinArticleDTO.getArticleId());
                weiXinSendDTO.setSendFlag("0");//发送，0待发送，1发送成功，2发送失败
                //3、上传完成存入数据库weixin_send
                iWeixinSendService.insertWeixinSend(weiXinSendDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 测试转换
//        String filePath = "joolun-wx/joolun-admin/src/main/resources/html/1.html";
//        String htmlContent = convertToString(filePath);


    }


    public String convertToString(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
