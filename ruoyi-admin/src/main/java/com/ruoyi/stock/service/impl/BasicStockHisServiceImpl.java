package com.ruoyi.stock.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.BasicStock;
import com.ruoyi.stock.mapper.BasicStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.BasicStockHisMapper;
import com.ruoyi.stock.domain.BasicStockHis;
import com.ruoyi.stock.service.IBasicStockHisService;

/**
 * 基础股票历史Service业务层处理
 *
 * @author ruoyi
 * @date 2025-08-03
 */
@Service
public class BasicStockHisServiceImpl extends ServiceImpl<BasicStockHisMapper, BasicStockHis> implements IBasicStockHisService {
    @Autowired
    private BasicStockHisMapper basicStockHisMapper;

    /**
     * 查询基础股票历史
     *
     * @param basicStockId 基础股票历史主键
     * @return 基础股票历史
     */
    @Override
    public BasicStockHis selectBasicStockHisByBasicStockId(Long basicStockId) {
        return basicStockHisMapper.selectBasicStockHisByBasicStockId(basicStockId);
    }

    /**
     * 查询基础股票历史列表
     *
     * @param basicStockHis 基础股票历史
     * @return 基础股票历史
     */
    @Override
    public List<BasicStockHis> selectBasicStockHisList(BasicStockHis basicStockHis) {
        return basicStockHisMapper.selectBasicStockHisList(basicStockHis);
    }

    /**
     * 新增基础股票历史
     *
     * @param basicStockHis 基础股票历史
     * @return 结果
     */
    @Override
    public int insertBasicStockHis(BasicStockHis basicStockHis) {
        return basicStockHisMapper.insertBasicStockHis(basicStockHis);
    }

    /**
     * 修改基础股票历史
     *
     * @param basicStockHis 基础股票历史
     * @return 结果
     */
    @Override
    public int updateBasicStockHis(BasicStockHis basicStockHis) {
        return basicStockHisMapper.updateBasicStockHis(basicStockHis);
    }

    /**
     * 批量删除基础股票历史
     *
     * @param basicStockIds 需要删除的基础股票历史主键
     * @return 结果
     */
    @Override
    public int deleteBasicStockHisByBasicStockIds(Long[] basicStockIds) {
        return basicStockHisMapper.deleteBasicStockHisByBasicStockIds(basicStockIds);
    }

    /**
     * 删除基础股票历史信息
     *
     * @param basicStockId 基础股票历史主键
     * @return 结果
     */
    @Override
    public int deleteBasicStockHisByBasicStockId(Long basicStockId) {
        return basicStockHisMapper.deleteBasicStockHisByBasicStockId(basicStockId);
    }
}
