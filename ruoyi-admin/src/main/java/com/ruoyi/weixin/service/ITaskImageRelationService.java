package com.ruoyi.weixin.service;



import com.ruoyi.weixin.domain.TaskImageRelationDTO;

import java.util.List;


/**
 * 任务素材顺序关联Service接口
 *
 * @author www.joolun.com
 * @date 2023-12-17
 */
public interface ITaskImageRelationService {
    /**
     * 查询任务素材顺序关联
     *
     * @param relationId 任务素材顺序关联ID
     * @return 任务素材顺序关联
     */
    public TaskImageRelationDTO selectTaskImageRelationById(Long relationId);

    /**
     * 查询任务素材顺序关联列表
     *
     * @param taskImageRelationDTO 任务素材顺序关联
     * @return 任务素材顺序关联集合
     */
    public List<TaskImageRelationDTO> selectTaskImageRelationList(TaskImageRelationDTO taskImageRelationDTO);

    /**
     * 新增任务素材顺序关联
     *
     * @param taskImageRelationDTO 任务素材顺序关联
     * @return 结果
     */
    public int insertTaskImageRelation(TaskImageRelationDTO taskImageRelationDTO);

    /**
     * 修改任务素材顺序关联
     *
     * @param taskImageRelationDTO 任务素材顺序关联
     * @return 结果
     */
    public int updateTaskImageRelation(TaskImageRelationDTO taskImageRelationDTO);

    /**
     * 批量删除任务素材顺序关联
     *
     * @param relationIds 需要删除的任务素材顺序关联ID
     * @return 结果
     */
    public int deleteTaskImageRelationByIds(Long[] relationIds);

    /**
     * 删除任务素材顺序关联信息
     *
     * @param relationId 任务素材顺序关联ID
     * @return 结果
     */
    public int deleteTaskImageRelationById(Long relationId);
}
