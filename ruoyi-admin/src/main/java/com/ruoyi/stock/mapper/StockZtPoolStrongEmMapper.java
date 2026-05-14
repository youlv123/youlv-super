package com.ruoyi.stock.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockZtPoolEm;
import com.ruoyi.stock.domain.StockZtPoolStrongEm;
import org.springframework.stereotype.Repository;

/**
 * 强势股池Mapper接口
 *
 * @author DXR
 * @date 2025-05-20
 */
@Repository
public interface StockZtPoolStrongEmMapper extends BaseMapper<StockZtPoolStrongEm> {
    /**
     * 查询强势股池
     *
     * @param ztsId 强势股池主键
     * @return 强势股池
     */
    public StockZtPoolStrongEm selectStockZtPoolStrongEmByZtsId(Long ztsId);

    /**
     * 查询强势股池列表
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 强势股池集合
     */
    public List<StockZtPoolStrongEm> selectStockZtPoolStrongEmList(StockZtPoolStrongEm stockZtPoolStrongEm);

    /**
     * 新增强势股池
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 结果
     */
    public int insertStockZtPoolStrongEm(StockZtPoolStrongEm stockZtPoolStrongEm);

    /**
     * 修改强势股池
     *
     * @param stockZtPoolStrongEm 强势股池
     * @return 结果
     */
    public int updateStockZtPoolStrongEm(StockZtPoolStrongEm stockZtPoolStrongEm);

    /**
     * 删除强势股池
     *
     * @param ztsId 强势股池主键
     * @return 结果
     */
    public int deleteStockZtPoolStrongEmByZtsId(Long ztsId);

    /**
     * 批量删除强势股池
     *
     * @param ztsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockZtPoolStrongEmByZtsIds(Long[] ztsIds);
}
