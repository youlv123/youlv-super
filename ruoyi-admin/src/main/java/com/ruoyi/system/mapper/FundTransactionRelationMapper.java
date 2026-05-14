package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.FundPositionDetail;
import com.ruoyi.system.domain.FundTransactionRelation;
import org.springframework.stereotype.Repository;

/**
 * 关联持仓明细Mapper接口
 *
 * @author DXR
 * @date 2025-10-13
 */
@Repository
public interface FundTransactionRelationMapper extends BaseMapper<FundTransactionRelation> {
    /**
     * 查询关联持仓明细
     *
     * @param fundTransactionRelationId 关联持仓明细主键
     * @return 关联持仓明细
     */
    public FundTransactionRelation selectFundTransactionRelationByFundTransactionRelationId(Long fundTransactionRelationId);

    /**
     * 查询关联持仓明细列表
     *
     * @param fundTransactionRelation 关联持仓明细
     * @return 关联持仓明细集合
     */
    public List<FundTransactionRelation> selectFundTransactionRelationList(FundTransactionRelation fundTransactionRelation);

    /**
     * 新增关联持仓明细
     *
     * @param fundTransactionRelation 关联持仓明细
     * @return 结果
     */
    public int insertFundTransactionRelation(FundTransactionRelation fundTransactionRelation);

    /**
     * 修改关联持仓明细
     *
     * @param fundTransactionRelation 关联持仓明细
     * @return 结果
     */
    public int updateFundTransactionRelation(FundTransactionRelation fundTransactionRelation);

    /**
     * 删除关联持仓明细
     *
     * @param fundTransactionRelationId 关联持仓明细主键
     * @return 结果
     */
    public int deleteFundTransactionRelationByFundTransactionRelationId(Long fundTransactionRelationId);

    /**
     * 批量删除关联持仓明细
     *
     * @param fundTransactionRelationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFundTransactionRelationByFundTransactionRelationIds(Long[] fundTransactionRelationIds);
}
