package com.ruoyi.weixin.service;

import java.util.List;

import com.ruoyi.weixin.domain.ArticleTaskDTO;

/**
 * 文章任务Service接口
 *
 * @author DXR
 * @date 2024-04-27
 */
public interface IArticleTaskService {
    /**
     * 查询文章任务
     *
     * @param taskId 文章任务主键
     * @return 文章任务
     */
    public ArticleTaskDTO selectArticleTaskByTaskId(Long taskId);

    /**
     * 查询文章任务列表
     *
     * @param articleTaskDTO 文章任务
     * @return 文章任务集合
     */
    public List<ArticleTaskDTO> selectArticleTaskList(ArticleTaskDTO articleTaskDTO);

    /**
     * 新增文章任务
     *
     * @param articleTaskDTO 文章任务
     * @return 结果
     */
    public int insertArticleTask(ArticleTaskDTO articleTaskDTO);

    /**
     * 修改文章任务
     *
     * @param articleTaskDTO 文章任务
     * @return 结果
     */
    public int updateArticleTask(ArticleTaskDTO articleTaskDTO);

    /**
     * 批量删除文章任务
     *
     * @param taskIds 需要删除的文章任务主键集合
     * @return 结果
     */
    public int deleteArticleTaskByTaskIds(Long[] taskIds);

    /**
     * 删除文章任务信息
     *
     * @param taskId 文章任务主键
     * @return 结果
     */
    public int deleteArticleTaskByTaskId(Long taskId);
}
