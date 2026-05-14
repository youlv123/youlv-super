package com.ruoyi.weixin.service.impl;
import java.util.Date;
import com.google.common.collect.Maps;


import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.FreemarkerUtil;
import com.ruoyi.weixin.domain.*;
import com.ruoyi.weixin.mapper.*;
import com.ruoyi.weixin.service.AutoWeiXin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AutoWeiXinImpl implements AutoWeiXin {

    @Autowired
    private WeiXinImageMapper weixinImageMapper;

    @Autowired
    private TemplatesMapper templatesMapper;

    @Autowired
    private TaskImageRelationMapper taskImageRelationMapper;
    @Autowired
    private ArticleTaskMapper articleTaskMapper;

    @Autowired
    private WeiXinArticleMapper weixinArticleMapper;

    /**
     * 3、自动组装数据
     *
     * @return
     */
    @Override
    public void autoMaker() {
        //1、获得当前业务日期
        String bizDate = DateUtils.getBizDate();
        //2、拿业务日期去article_task任务表查询，查询不到就结束
        List<ArticleTaskDTO> articleTaskDTOS = articleTaskMapper.selectArticleByBizDate(bizDate);
        if (CollectionUtils.isEmpty(articleTaskDTOS)){
            return;
        }
        for (ArticleTaskDTO articleTaskDTO : articleTaskDTOS) {
            //3、拿任务id查询这个任务下面所有的素材  task_image_relation
            List<TaskImageRelationDTO> taskImageRelationDTOList = taskImageRelationMapper.selectTaskImageRelationByTaskId(articleTaskDTO.getTaskId());
            StringBuilder stringBuilder = new StringBuilder();
            //4、循环该任务下所有的素材拿到素材和模板进行组装
            String thumbMediaId="";
            Long imageId=null;
            for (TaskImageRelationDTO taskImageRelationDTO : taskImageRelationDTOList) {
                //2.1拿到图片素材表的id
                 imageId = taskImageRelationDTO.getImageId();
                //2.2 查询weixin_image对应的图片素材
                WeiXinImageDTO weixinImageDTO = weixinImageMapper.selectWeixinImageById(imageId);
                //2.3 拿到上传后的微信素材库对应的url
                String weixinUrl = weixinImageDTO.getWeixinUrl();
                //2.4 拿到模板id
                Long templateId = taskImageRelationDTO.getTemplateId();
                //2.5 拿到templates模板表对应的模板
                TemplatesDTO templatesDTO = templatesMapper.selectTemplatesByTemplateId(templateId);
                //2.6 拿到模板
                String templateContent = templatesDTO.getTemplateContent();
                //2.7 模板名称
                String templateName = templatesDTO.getTemplateName();
                //2.8 拼接字符串
                Map<String, String> data=new HashMap<>();
                data.put("微信图片地址",weixinUrl);
                data.put("图片名称",weixinImageDTO.getImageName());
                data.put("图片描述",weixinImageDTO.getImageDescription());
                try {
                    String message = FreemarkerUtil.generateMessage(templateName, templateContent, data);
                    stringBuilder.append(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("模板拼接出错");
                    log.error(e.getMessage());
                }
                thumbMediaId=weixinImageDTO.getMediaId();

            }
            String content = stringBuilder.toString();
            //5、插入weixin_article微信公众号文章表
            WeiXinArticleDTO weixinArticleDTO=new WeiXinArticleDTO();
            weixinArticleDTO.setArticleId(0L);
            weixinArticleDTO.setTitle("这是一个测试");
            weixinArticleDTO.setAuthor("DXR");
            weixinArticleDTO.setDigest("哈哈");
            weixinArticleDTO.setDescription("描述");
            weixinArticleDTO.setContentSourceUrl("原文地址");
            weixinArticleDTO.setThumbMediaId(thumbMediaId);
            weixinArticleDTO.setCoverImage(imageId);
            weixinArticleDTO.setNeedOpenComment("1");
            weixinArticleDTO.setOnlyFansCanComment("0");
            weixinArticleDTO.setArticleType("1");
            weixinArticleDTO.setArticleWordCount(0L);
            weixinArticleDTO.setPublishTime(new Date());
            weixinArticleDTO.setContent(content);
            //weixin_article
            weixinArticleMapper.insertWeixinArticle(weixinArticleDTO);

        }

        //如何拼装
        //拿到所需要的素材和模板
        //图片每使用一次就记录一次
//文章表，拿到主键，查询素材顺序表，每个素材有对应的模板id，拿到对应的模板，查询素材，拿到素材，进行组装，素材被删问题
        //组装完整版存起来







    }
}
