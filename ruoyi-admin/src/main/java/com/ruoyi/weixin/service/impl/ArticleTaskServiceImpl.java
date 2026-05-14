package com.ruoyi.weixin.service.impl;

import java.util.List;

import com.ruoyi.weixin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weixin.mapper.ArticleTaskMapper;
import com.ruoyi.weixin.domain.ArticleTaskDTO;

/**
 * 文章任务Service业务层处理
 *
 * @author DXR
 * @date 2024-04-27
 */
@Service
public class ArticleTaskServiceImpl implements IArticleTaskService {
    @Autowired
    private ArticleTaskMapper articleTaskMapper;

    @Autowired
    private AutoPushDraft autoPushDraft;

    /**
     * 查询文章任务
     *
     * @param taskId 文章任务主键
     * @return 文章任务
     */
    @Override
    public ArticleTaskDTO selectArticleTaskByTaskId(Long taskId) {
        return articleTaskMapper.selectArticleTaskByTaskId(taskId);
    }

    /**
     * 查询文章任务列表
     *
     * @param articleTaskDTO 文章任务
     * @return 文章任务
     */
    @Override
    public List<ArticleTaskDTO> selectArticleTaskList(ArticleTaskDTO articleTaskDTO) {
//        imageAutoHandle.autoHandle();
//        autoArticleTask.createArticleTask();
//        autoWeiXin.autoMaker();
        autoPushDraft.AutoPushDraft();
        return articleTaskMapper.selectArticleTaskList(articleTaskDTO);
    }

    /**
     * 新增文章任务
     *
     * @param articleTaskDTO 文章任务
     * @return 结果
     */
    @Override
    public int insertArticleTask(ArticleTaskDTO articleTaskDTO) {
        return articleTaskMapper.insertArticleTask(articleTaskDTO);
    }

    /**
     * 修改文章任务
     *
     * @param articleTaskDTO 文章任务
     * @return 结果
     */
    @Override
    public int updateArticleTask(ArticleTaskDTO articleTaskDTO) {
        return articleTaskMapper.updateArticleTask(articleTaskDTO);
    }

    /**
     * 批量删除文章任务
     *
     * @param taskIds 需要删除的文章任务主键
     * @return 结果
     */
    @Override
    public int deleteArticleTaskByTaskIds(Long[] taskIds) {
        return articleTaskMapper.deleteArticleTaskByTaskIds(taskIds);
    }

    /**
     * 删除文章任务信息
     *
     * @param taskId 文章任务主键
     * @return 结果
     */
    @Override
    public int deleteArticleTaskByTaskId(Long taskId) {
        return articleTaskMapper.deleteArticleTaskByTaskId(taskId);
    }
}
