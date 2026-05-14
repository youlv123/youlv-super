package com.ruoyi.weixin.mapper;

import java.util.List;

import com.ruoyi.weixin.domain.ArticleTaskDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章任务Mapper接口
 *
 * @author DXR
 * @date 2024-04-27
 */
@Mapper
public interface ArticleTaskMapper {
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
     * 删除文章任务
     *
     * @param taskId 文章任务主键
     * @return 结果
     */
    public int deleteArticleTaskByTaskId(Long taskId);

    /**
     * 批量删除文章任务
     *
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleTaskByTaskIds(Long[] taskIds);

    /**
     * 根据业务日期查询任务
     * @param bizdate
     * @return
     */
    public List<ArticleTaskDTO> selectArticleByBizDate(String bizdate);
}
