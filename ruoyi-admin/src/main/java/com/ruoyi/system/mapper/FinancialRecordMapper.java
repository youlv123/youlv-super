package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.FinancialRecord;
import com.ruoyi.system.domain.WxTask;
import org.springframework.stereotype.Repository;

/**
 * 理财记录Mapper接口
 *
 * @author DXR
 * @date 2025-07-07
 */
@Repository
public interface FinancialRecordMapper extends BaseMapper<FinancialRecord> {
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
    public int insertFinancialRecord(FinancialRecord financialRecord);

    /**
     * 修改理财记录
     *
     * @param financialRecord 理财记录
     * @return 结果
     */
    public int updateFinancialRecord(FinancialRecord financialRecord);

    /**
     * 删除理财记录
     *
     * @param financialRecordId 理财记录主键
     * @return 结果
     */
    public int deleteFinancialRecordByFinancialRecordId(Long financialRecordId);

    /**
     * 批量删除理财记录
     *
     * @param financialRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialRecordByFinancialRecordIds(Long[] financialRecordIds);

    /**
     * 全量查询理财记录列表
     *
     * @param maxId 理财记录主键
     * @return 理财记录集合
     */
    public List<FinancialRecord> selectListById(Long maxId);
}
