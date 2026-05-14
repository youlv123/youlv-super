package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.WorkTimeRecordDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 记录工作时长Mapper接口
 *
 * @author ruoyi
 * @date 2023-06-25
 */
@Repository
public interface WorkTimeRecordMapper {
    /**
     * 查询记录工作时长
     *
     * @param recordId 记录工作时长主键
     * @return 记录工作时长
     */
    public WorkTimeRecordDTO selectWorkTimeRecordByRecordId(@Param("recordId") Long recordId, @Param("createdBy") String createdBy);

    /**
     * 查询记录工作时长列表
     *
     * @param workTimeRecord 记录工作时长
     * @return 记录工作时长集合
     */
    public List<WorkTimeRecordDTO> selectWorkTimeRecordList(WorkTimeRecordDTO workTimeRecord);

    /**
     * 新增记录工作时长
     *
     * @param workTimeRecord 记录工作时长
     * @return 结果
     */
    public int insertWorkTimeRecord(WorkTimeRecordDTO workTimeRecord);

    /**
     * 修改记录工作时长
     *
     * @param workTimeRecord 记录工作时长
     * @return 结果
     */
    public int updateWorkTimeRecord(WorkTimeRecordDTO workTimeRecord);

    /**
     * 删除记录工作时长
     *
     * @param recordId 记录工作时长主键
     * @return 结果
     */
    public int deleteWorkTimeRecordByRecordId(Long recordId);

    /**
     * 批量删除记录工作时长
     *
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkTimeRecordByRecordIds(Long[] recordIds);
}
