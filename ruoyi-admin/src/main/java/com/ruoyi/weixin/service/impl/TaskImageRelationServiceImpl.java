package com.ruoyi.weixin.service.impl;

import java.util.List;


import com.ruoyi.weixin.domain.TaskImageRelationDTO;
import com.ruoyi.weixin.mapper.TaskImageRelationMapper;
import com.ruoyi.weixin.service.ITaskImageRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 任务素材顺序关联Service业务层处理
 *
 * @author www.joolun.com
 * @date 2023-12-17
 */
@Service
public class TaskImageRelationServiceImpl implements ITaskImageRelationService {
    @Autowired
    private TaskImageRelationMapper taskImageRelationMapper;

    /**
     * 查询任务素材顺序关联
     *
     * @param relationId 任务素材顺序关联ID
     * @return 任务素材顺序关联
     */
    @Override
    public TaskImageRelationDTO selectTaskImageRelationById(Long relationId) {
        return taskImageRelationMapper.selectTaskImageRelationById(relationId);
    }

    /**
     * 查询任务素材顺序关联列表
     *
     * @param taskImageRelationDTO 任务素材顺序关联
     * @return 任务素材顺序关联
     */
    @Override
    public List<TaskImageRelationDTO> selectTaskImageRelationList(TaskImageRelationDTO taskImageRelationDTO) {
        return taskImageRelationMapper.selectTaskImageRelationList(taskImageRelationDTO);
    }

    /**
     * 新增任务素材顺序关联
     *
     * @param taskImageRelationDTO 任务素材顺序关联
     * @return 结果
     */
    @Override
    public int insertTaskImageRelation(TaskImageRelationDTO taskImageRelationDTO) {
        return taskImageRelationMapper.insertTaskImageRelation(taskImageRelationDTO);
    }

    /**
     * 修改任务素材顺序关联
     *
     * @param taskImageRelationDTO 任务素材顺序关联
     * @return 结果
     */
    @Override
    public int updateTaskImageRelation(TaskImageRelationDTO taskImageRelationDTO) {
        return taskImageRelationMapper.updateTaskImageRelation(taskImageRelationDTO);
    }

    /**
     * 批量删除任务素材顺序关联
     *
     * @param relationIds 需要删除的任务素材顺序关联ID
     * @return 结果
     */
    @Override
    public int deleteTaskImageRelationByIds(Long[] relationIds) {
        return taskImageRelationMapper.deleteTaskImageRelationByIds(relationIds);
    }

    /**
     * 删除任务素材顺序关联信息
     *
     * @param relationId 任务素材顺序关联ID
     * @return 结果
     */
    @Override
    public int deleteTaskImageRelationById(Long relationId) {
        return taskImageRelationMapper.deleteTaskImageRelationById(relationId);
    }
}
