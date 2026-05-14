package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockZtPoolEm;
import org.springframework.stereotype.Repository;

/**
 * 涨停板行情Mapper接口
 *
 * @author DXR
 * @date 2025-05-09
 */
@Repository
public interface StockZtPoolEmMapper extends BaseMapper<StockZtPoolEm> {
    /**
     * 查询涨停板行情
     *
     * @param ztId 涨停板行情主键
     * @return 涨停板行情
     */
    public StockZtPoolEm selectStockZtPoolEmByZtId(Long ztId);

    /**
     * 查询涨停板行情列表
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 涨停板行情集合
     */
    public List<StockZtPoolEm> selectStockZtPoolEmList(StockZtPoolEm stockZtPoolEm);

    /**
     * 新增涨停板行情
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 结果
     */
    public int insertStockZtPoolEm(StockZtPoolEm stockZtPoolEm);

    /**
     * 修改涨停板行情
     *
     * @param stockZtPoolEm 涨停板行情
     * @return 结果
     */
    public int updateStockZtPoolEm(StockZtPoolEm stockZtPoolEm);

    /**
     * 删除涨停板行情
     *
     * @param ztId 涨停板行情主键
     * @return 结果
     */
    public int deleteStockZtPoolEmByZtId(Long ztId);

    /**
     * 批量删除涨停板行情
     *
     * @param ztIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockZtPoolEmByZtIds(Long[] ztIds);
}
