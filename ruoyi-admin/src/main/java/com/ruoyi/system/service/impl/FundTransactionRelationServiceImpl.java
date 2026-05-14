package com.ruoyi.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.FundPositionDetail;
import com.ruoyi.system.mapper.FundPositionDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FundTransactionRelationMapper;
import com.ruoyi.system.domain.FundTransactionRelation;
import com.ruoyi.system.service.IFundTransactionRelationService;

/**
 * 关联持仓明细Service业务层处理
 *
 * @author DXR
 * @date 2025-10-13
 */
@Service
public class FundTransactionRelationServiceImpl extends ServiceImpl<FundTransactionRelationMapper, FundTransactionRelation> implements IFundTransactionRelationService {
    @Autowired
    private FundTransactionRelationMapper fundTransactionRelationMapper;

    /**
     * 查询关联持仓明细
     *
     * @param fundTransactionRelationId 关联持仓明细主键
     * @return 关联持仓明细
     */
    @Override
    public FundTransactionRelation selectFundTransactionRelationByFundTransactionRelationId(Long fundTransactionRelationId) {
        return fundTransactionRelationMapper.selectFundTransactionRelationByFundTransactionRelationId(fundTransactionRelationId);
    }

    /**
     * 查询关联持仓明细列表
     *
     * @param fundTransactionRelation 关联持仓明细
     * @return 关联持仓明细
     */
    @Override
    public List<FundTransactionRelation> selectFundTransactionRelationList(FundTransactionRelation fundTransactionRelation) {
        return fundTransactionRelationMapper.selectFundTransactionRelationList(fundTransactionRelation);
    }

    /**
     * 新增关联持仓明细
     *
     * @param fundTransactionRelation 关联持仓明细
     * @return 结果
     */
    @Override
    public int insertFundTransactionRelation(FundTransactionRelation fundTransactionRelation) {
        return fundTransactionRelationMapper.insertFundTransactionRelation(fundTransactionRelation);
    }

    /**
     * 修改关联持仓明细
     *
     * @param fundTransactionRelation 关联持仓明细
     * @return 结果
     */
    @Override
    public int updateFundTransactionRelation(FundTransactionRelation fundTransactionRelation) {
        return fundTransactionRelationMapper.updateFundTransactionRelation(fundTransactionRelation);
    }

    /**
     * 批量删除关联持仓明细
     *
     * @param fundTransactionRelationIds 需要删除的关联持仓明细主键
     * @return 结果
     */
    @Override
    public int deleteFundTransactionRelationByFundTransactionRelationIds(Long[] fundTransactionRelationIds) {
        return fundTransactionRelationMapper.deleteFundTransactionRelationByFundTransactionRelationIds(fundTransactionRelationIds);
    }

    /**
     * 删除关联持仓明细信息
     *
     * @param fundTransactionRelationId 关联持仓明细主键
     * @return 结果
     */
    @Override
    public int deleteFundTransactionRelationByFundTransactionRelationId(Long fundTransactionRelationId) {
        return fundTransactionRelationMapper.deleteFundTransactionRelationByFundTransactionRelationId(fundTransactionRelationId);
    }
}
