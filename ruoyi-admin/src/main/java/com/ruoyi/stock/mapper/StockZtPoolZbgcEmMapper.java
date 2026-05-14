package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockZtPoolStrongEm;
import com.ruoyi.stock.domain.StockZtPoolZbgcEm;
import org.springframework.stereotype.Repository;

/**
 * 炸板股池Mapper接口
 *
 * @author DXR
 * @date 2025-05-21
 */
@Repository
public interface StockZtPoolZbgcEmMapper extends BaseMapper<StockZtPoolZbgcEm> {
    /**
     * 查询炸板股池
     *
     * @param zbgcId 炸板股池主键
     * @return 炸板股池
     */
    public StockZtPoolZbgcEm selectStockZtPoolZbgcEmByZbgcId(Long zbgcId);

    /**
     * 查询炸板股池列表
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 炸板股池集合
     */
    public List<StockZtPoolZbgcEm> selectStockZtPoolZbgcEmList(StockZtPoolZbgcEm stockZtPoolZbgcEm);

    /**
     * 新增炸板股池
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 结果
     */
    public int insertStockZtPoolZbgcEm(StockZtPoolZbgcEm stockZtPoolZbgcEm);

    /**
     * 修改炸板股池
     *
     * @param stockZtPoolZbgcEm 炸板股池
     * @return 结果
     */
    public int updateStockZtPoolZbgcEm(StockZtPoolZbgcEm stockZtPoolZbgcEm);

    /**
     * 删除炸板股池
     *
     * @param zbgcId 炸板股池主键
     * @return 结果
     */
    public int deleteStockZtPoolZbgcEmByZbgcId(Long zbgcId);

    /**
     * 批量删除炸板股池
     *
     * @param zbgcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockZtPoolZbgcEmByZbgcIds(Long[] zbgcIds);
}
