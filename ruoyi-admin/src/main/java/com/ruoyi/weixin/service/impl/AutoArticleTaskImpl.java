package com.ruoyi.weixin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.weixin.domain.ArticleTaskDTO;
import com.ruoyi.weixin.domain.TaskImageRelationDTO;
import com.ruoyi.weixin.domain.WeiXinImageDTO;
import com.ruoyi.weixin.mapper.TaskImageRelationMapper;
import com.ruoyi.weixin.mapper.WeiXinImageMapper;
import com.ruoyi.weixin.service.AutoArticleTask;
import com.ruoyi.weixin.service.IArticleTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AutoArticleTaskImpl implements AutoArticleTask {
    @Autowired
    private IArticleTaskService iArticleTaskService;
    @Autowired
    private WeiXinImageMapper weixinImageMapper;
    @Autowired
    private TaskImageRelationMapper taskImageRelationMapper;

    private static final int DEFAULT_BATCH_SIZE = 1000;

    /**
     * 2、生成文章任务
     * （1）扫描微信图片表，拿到最新的图片数据，是否要根据图片热度来获取数据呢？
     * （2）根据图片的顺序来确定文章用图片的多少，每次查询1000张照片
     * （3）根据图片生成多个任务
     * （4）
     */
    @Override
    public void createArticleTask() {
        //1、article_task文章任务表创建一条新数据，拿到主键id
        //定义任务表第几条任务的计数
        int count = 1;
        //第一次先插入一条任务
        String bizDate = DateUtils.getBizDate();
/*        ArticleTaskDTO articleTaskDTO = new ArticleTaskDTO();
        articleTaskDTO.setBizDate(bizDate);
        articleTaskDTO.setArticleTaskName(bizDate + "自动任务" + count);
        int taskId = iArticleTaskService.insertArticleTask(articleTaskDTO);*/
        //2、扫描weixin_image图片素材表，拿到最新的图片数据  todo 是否要根据图片的热度来获取数据
        //2.1 拿到当天时间
        Date date = DateUtils.dateTime(DateUtils.YYYY_MM_DD, bizDate);
        //2.2 查询weixin_image图片素材表，拿到当天最新的数据，分批1000一次查询
        Long maxId = null;
        List<WeiXinImageDTO> WeiXinImageList = this.findNextBatch(date, maxId);
        log.info("found WeiXinImageList [{}]", WeiXinImageList.size());
        maxId = Long.MIN_VALUE;
        //定义任务素材顺序关联表素材组装顺序
        Long order = 1L;
//        articleTaskName = articleTaskDTO.getArticleTaskName();
        //3、循环查询，直到为空结束今天的任务
        while (CollectionUtils.isNotEmpty(WeiXinImageList)) {
            ArrayList<TaskImageRelationDTO> insertList = new ArrayList<>();
            //4、一个任务20个素材，循环生成任务绑定素材
            ArticleTaskDTO dto = new ArticleTaskDTO();
            dto.setBizDate(bizDate);
            dto.setArticleTaskName(bizDate + "自动任务" + count);
            //article_task
            iArticleTaskService.insertArticleTask(dto);
            Long taskId = dto.getTaskId();
            String articleTaskName = dto.getArticleTaskName();
            for (WeiXinImageDTO weiXinImageDTO : WeiXinImageList) {
                Long id = weiXinImageDTO.getImageId();
                if (id > maxId) {
                    maxId = id;
                }
                TaskImageRelationDTO taskImageRelationDTO = new TaskImageRelationDTO();
                if (order >= 21L) {
                    count++;
                    ArticleTaskDTO articleTaskDTO = new ArticleTaskDTO();
                    articleTaskDTO.setBizDate(bizDate);
                    articleTaskDTO.setArticleTaskName(bizDate + "自动任务" + count);
                    //article_task
                    iArticleTaskService.insertArticleTask(articleTaskDTO);
                    taskId = articleTaskDTO.getTaskId();
                    articleTaskName = dto.getArticleTaskName();
                    order = 1L;
                }
                taskImageRelationDTO.setArticleTaskName(articleTaskName);
                taskImageRelationDTO.setTaskId(taskId);
                taskImageRelationDTO.setImageId(weiXinImageDTO.getImageId());
                //这里需要模板id，该怎么确认呢？？？？？？？
                taskImageRelationDTO.setTemplateId(3L);
                taskImageRelationDTO.setOrderNum(order);
                //task_image_relation
                insertList.add(taskImageRelationDTO);
//                taskImageRelationMapper.insertTaskImageRelation(taskImageRelationDTO);
                order++;
            }
            //批量插入
            taskImageRelationMapper.batchInsertTaskImageRelation(insertList);
            //查询剩下的
            WeiXinImageList = this.findNextBatch(date, maxId);
            log.info("found oraclePos [{}]", WeiXinImageList.size());
        }

    }

    /**
     * 根据时间和最大id，批量1000查询一批数据
     *
     * @param lastSuccessDate
     * @param id
     * @return
     */
    private List<WeiXinImageDTO> findNextBatch(Date lastSuccessDate, Long id) {
        PageHelper.startPage(1, DEFAULT_BATCH_SIZE);
        List<WeiXinImageDTO> WeiXinImageList = weixinImageMapper.selectByUpdateTime(lastSuccessDate, id);
        PageInfo<WeiXinImageDTO> pageInfo = new PageInfo<>(WeiXinImageList);
        return pageInfo.getList();
    }

}

