package com.ruoyi.stock.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.stock.domain.StockDeliveryNoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.StockDeliveryNoteMapper;
import com.ruoyi.stock.service.IStockDeliveryNoteService;

/**
 * 股票交割单Service业务层处理
 *
 * @author DXR
 * @date 2025-03-13
 */
@Service
public class StockDeliveryNoteServiceImpl implements IStockDeliveryNoteService {
    @Autowired
    private StockDeliveryNoteMapper stockDeliveryNoteMapper;

    /**
     * 查询股票交割单
     *
     * @param stockId 股票交割单主键
     * @return 股票交割单
     */
    @Override
    public StockDeliveryNoteDTO selectStockDeliveryNoteByStockId(Long stockId) {
        return stockDeliveryNoteMapper.selectStockDeliveryNoteByStockId(stockId);
    }

    /**
     * 查询股票交割单列表
     *
     * @param stockDeliveryNoteDTO 股票交割单
     * @return 股票交割单
     */
    @Override
    public List<StockDeliveryNoteDTO> selectStockDeliveryNoteList(StockDeliveryNoteDTO stockDeliveryNoteDTO) {
        return stockDeliveryNoteMapper.selectStockDeliveryNoteList(stockDeliveryNoteDTO);
    }

    /**
     * 新增股票交割单
     *
     * @param stockDeliveryNoteDTO 股票交割单
     * @return 结果
     */
    @Override
    public int insertStockDeliveryNote(StockDeliveryNoteDTO stockDeliveryNoteDTO) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        stockDeliveryNoteDTO.setCreatedBy(username);
        stockDeliveryNoteDTO.setUpdatedBy(username);
        return stockDeliveryNoteMapper.insertStockDeliveryNote(stockDeliveryNoteDTO);
    }

    /**
     * 修改股票交割单
     *
     * @param stockDeliveryNoteDTO 股票交割单
     * @return 结果
     */
    @Override
    public int updateStockDeliveryNote(StockDeliveryNoteDTO stockDeliveryNoteDTO) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        stockDeliveryNoteDTO.setUpdatedBy(username);
        return stockDeliveryNoteMapper.updateStockDeliveryNote(stockDeliveryNoteDTO);
    }

    /**
     * 批量删除股票交割单
     *
     * @param stockIds 需要删除的股票交割单主键
     * @return 结果
     */
    @Override
    public int deleteStockDeliveryNoteByStockIds(Long[] stockIds) {
        return stockDeliveryNoteMapper.deleteStockDeliveryNoteByStockIds(stockIds);
    }

    /**
     * 删除股票交割单信息
     *
     * @param stockId 股票交割单主键
     * @return 结果
     */
    @Override
    public int deleteStockDeliveryNoteByStockId(Long stockId) {
        return stockDeliveryNoteMapper.deleteStockDeliveryNoteByStockId(stockId);
    }
}
