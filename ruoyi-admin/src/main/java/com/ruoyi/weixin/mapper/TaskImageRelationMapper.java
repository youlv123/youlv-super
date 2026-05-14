package com.ruoyi.weixin.mapper;

import java.util.List;


import com.ruoyi.weixin.domain.TaskImageRelationDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 任务素材顺序关联Mapper接口
 *
 * @author www.joolun.com
 * @date 2023-12-17
 */
@Repository
public interface TaskImageRelationMapper {
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
     * 删除任务素材顺序关联
     *
     * @param relationId 任务素材顺序关联ID
     * @return 结果
     */
    public int deleteTaskImageRelationById(Long relationId);

    /**
     * 批量删除任务素材顺序关联
     *
     * @param relationIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskImageRelationByIds(Long[] relationIds);

    /**
     * 通过任务id查询任务下面所有关联的素材
     *
     * @param taskId
     * @return
     */
    List<TaskImageRelationDTO> selectTaskImageRelationByTaskId(Long taskId);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsertTaskImageRelation(@Param("list") List<TaskImageRelationDTO> list);
}
