package com.ruoyi.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.FinancialRecord;
import com.ruoyi.system.domain.WxTask;
import org.quartz.SchedulerException;

/**
 * 理财记录Service接口
 *
 * @author DXR
 * @date 2025-07-07
 */
public interface IFinancialRecordService extends IService<FinancialRecord> {
    /**
     * 查询理财记录
     *
     * @param financialRecordId 理财记录主键
     * @return 理财记录
     */
    public FinancialRecord selectFinancialRecordByFinancialRecordId(Long financialRecordId);

    /**
     * 查询理财记录列表
     *
     * @param financialRecord 理财记录
     * @return 理财记录集合
     */
    public List<FinancialRecord> selectFinancialRecordList(FinancialRecord financialRecord);

    /**
     * 新增理财记录
     *
     * @param financialRecord 理财记录
     * @return 结果
     */
    public int insertFinancialRecord(FinancialRecord financialRecord)throws SchedulerException;

    /**
     * 修改理财记录
     *
     * @param financialRecord 理财记录
     * @return 结果
     */
    public int updateFinancialRecord(FinancialRecord financialRecord)throws SchedulerException;

    /**
     * 批量删除理财记录
     *
     * @param financialRecordIds 需要删除的理财记录主键集合
     * @return 结果
     */
    public int deleteFinancialRecordByFinancialRecordIds(Long[] financialRecordIds);

    /**
     * 删除理财记录信息
     *
     * @param financialRecordId 理财记录主键
     * @return 结果
     */
    public int deleteFinancialRecordByFinancialRecordId(Long financialRecordId);
}
