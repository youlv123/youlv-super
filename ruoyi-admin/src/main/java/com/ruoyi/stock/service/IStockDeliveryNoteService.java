package com.ruoyi.stock.service;

import com.ruoyi.stock.domain.StockDeliveryNoteDTO;

import java.util.List;

/**
 * 股票交割单Service接口
 *
 * @author DXR
 * @date 2025-03-13
 */
public interface IStockDeliveryNoteService {
    /**
     * 查询股票交割单
     *
     * @param stockId 股票交割单主键
     * @return 股票交割单
     */
    public StockDeliveryNoteDTO selectStockDeliveryNoteByStockId(Long stockId);

    /**
     * 查询股票交割单列表
     *
     * @param stockDeliveryNoteDTO 股票交割单
     * @return 股票交割单集合
     */
    public List<StockDeliveryNoteDTO> selectStockDeliveryNoteList(StockDeliveryNoteDTO stockDeliveryNoteDTO);

    /**
     * 新增股票交割单
     *
     * @param stockDeliveryNoteDTO 股票交割单
     * @return 结果
     */
    public int insertStockDeliveryNote(StockDeliveryNoteDTO stockDeliveryNoteDTO);

    /**
     * 修改股票交割单
     *
     * @param stockDeliveryNoteDTO 股票交割单
     * @return 结果
     */
    public int updateStockDeliveryNote(StockDeliveryNoteDTO stockDeliveryNoteDTO);

    /**
     * 批量删除股票交割单
     *
     * @param stockIds 需要删除的股票交割单主键集合
     * @return 结果
     */
    public int deleteStockDeliveryNoteByStockIds(Long[] stockIds);

    /**
     * 删除股票交割单信息
     *
     * @param stockId 股票交割单主键
     * @return 结果
     */
    public int deleteStockDeliveryNoteByStockId(Long stockId);
}
