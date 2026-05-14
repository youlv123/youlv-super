package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WorkTimeRecordMapper;
import com.ruoyi.system.domain.WorkTimeRecordDTO;
import com.ruoyi.system.service.IWorkTimeRecordService;

/**
 * 记录工作时长Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-25
 */
@Service
public class WorkTimeRecordServiceImpl implements IWorkTimeRecordService {
    @Autowired
    private WorkTimeRecordMapper workTimeRecordMapper;

    /**
     * 查询记录工作时长
     *
     * @param recordId 记录工作时长主键
     * @return 记录工作时长
     */
    @Override
    public WorkTimeRecordDTO selectWorkTimeRecordByRecordId(Long recordId) {
        String userId = SecurityUtils.getUserId().toString();
        return workTimeRecordMapper.selectWorkTimeRecordByRecordId(recordId,userId);
    }

    /**
     * 查询记录工作时长列表
     *
     * @param workTimeRecord 记录工作时长
     * @return 记录工作时长
     */
    @Override
    public List<WorkTimeRecordDTO> selectWorkTimeRecordList(WorkTimeRecordDTO workTimeRecord) {
        Long userId = SecurityUtils.getUserId();
        workTimeRecord.setCreatedBy(userId.toString());
        return workTimeRecordMapper.selectWorkTimeRecordList(workTimeRecord);
    }

    /**
     * 新增记录工作时长
     *
     * @param workTimeRecord 记录工作时长
     * @return 结果
     */
    @Override
    public int insertWorkTimeRecord(WorkTimeRecordDTO workTimeRecord) {
        Date startTime = workTimeRecord.getStartTime();
        Date endTime = workTimeRecord.getEndTime();

        Map<String,BigDecimal> map=getTime(startTime,endTime);
        BigDecimal bigMinutes = map.get("startTime");
        BigDecimal bigHours = map.get("endTime");
        workTimeRecord.setTotalMinutesDuration(bigMinutes);
        workTimeRecord.setTotalHoursDuration(bigHours);
        // 获取当前用户名
        Long userId = SecurityUtils.getUserId();
        workTimeRecord.setUpdatedBy(userId.toString());
        workTimeRecord.setCreatedBy(userId.toString());
        return workTimeRecordMapper.insertWorkTimeRecord(workTimeRecord);
    }

    /**
     * 计算两个时间的时间差，换算成分钟和小时
     * @param startTime
     * @param endTime
     * @return
     */
    private Map<String, BigDecimal> getTime(Date startTime, Date endTime) {
        // 创建 Calendar 对象并设置时间
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startTime);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endTime);

        // 计算时间差值（单位为毫秒）
        long diffMillis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();

        // 计算分钟数和小时数
        long diffMinutes = diffMillis / (60 * 1000);
        String sMinutes = String.valueOf(diffMinutes);
        double diffHours = (double) diffMillis / (60 * 60 * 1000);
        String sHours = String.valueOf(diffHours);
        BigDecimal bigMinutes = new BigDecimal(sMinutes);
        BigDecimal bigHours = new BigDecimal(sHours);
        Map<String, BigDecimal>  map = new HashMap<>();
        map.put("startTime",bigMinutes);
        map.put("endTime",bigHours);
        return map;
    }

    /**
     * 修改记录工作时长
     *
     * @param workTimeRecord 记录工作时长
     * @return 结果
     */
    @Override
    public int updateWorkTimeRecord(WorkTimeRecordDTO workTimeRecord) {
        Date startTime = workTimeRecord.getStartTime();
        Date endTime = workTimeRecord.getEndTime();

        Map<String,BigDecimal> map=getTime(startTime,endTime);
        BigDecimal bigMinutes = map.get("startTime");
        BigDecimal bigHours = map.get("endTime");
        workTimeRecord.setTotalMinutesDuration(bigMinutes);
        workTimeRecord.setTotalHoursDuration(bigHours);
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        workTimeRecord.setUpdatedBy(username);
        return workTimeRecordMapper.updateWorkTimeRecord(workTimeRecord);
    }

    /**
     * 批量删除记录工作时长
     *
     * @param recordIds 需要删除的记录工作时长主键
     * @return 结果
     */
    @Override
    public int deleteWorkTimeRecordByRecordIds(Long[] recordIds) {
        return workTimeRecordMapper.deleteWorkTimeRecordByRecordIds(recordIds);
    }

    /**
     * 删除记录工作时长信息
     *
     * @param recordId 记录工作时长主键
     * @return 结果
     */
    @Override
    public int deleteWorkTimeRecordByRecordId(Long recordId) {
        return workTimeRecordMapper.deleteWorkTimeRecordByRecordId(recordId);
    }
}
