package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockZtPoolDtgcEm;
import com.ruoyi.stock.domain.StockZtPoolEm;
import org.springframework.stereotype.Repository;

/**
 * 跌停股池Mapper接口
 *
 * @author DXR
 * @date 2025-05-25
 */
@Repository
public interface StockZtPoolDtgcEmMapper extends BaseMapper<StockZtPoolDtgcEm> {
    /**
     * 查询跌停股池
     *
     * @param dtgcId 跌停股池主键
     * @return 跌停股池
     */
    public StockZtPoolDtgcEm selectStockZtPoolDtgcEmByDtgcId(Long dtgcId);

    /**
     * 查询跌停股池列表
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 跌停股池集合
     */
    public List<StockZtPoolDtgcEm> selectStockZtPoolDtgcEmList(StockZtPoolDtgcEm stockZtPoolDtgcEm);

    /**
     * 新增跌停股池
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 结果
     */
    public int insertStockZtPoolDtgcEm(StockZtPoolDtgcEm stockZtPoolDtgcEm);

    /**
     * 修改跌停股池
     *
     * @param stockZtPoolDtgcEm 跌停股池
     * @return 结果
     */
    public int updateStockZtPoolDtgcEm(StockZtPoolDtgcEm stockZtPoolDtgcEm);

    /**
     * 删除跌停股池
     *
     * @param dtgcId 跌停股池主键
     * @return 结果
     */
    public int deleteStockZtPoolDtgcEmByDtgcId(Long dtgcId);

    /**
     * 批量删除跌停股池
     *
     * @param dtgcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockZtPoolDtgcEmByDtgcIds(Long[] dtgcIds);
}
