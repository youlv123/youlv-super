package com.ruoyi.stock.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.stock.domain.StockDeliveryNoteDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.stock.service.IStockDeliveryNoteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 股票交割单Controller
 *
 * @author DXR
 * @date 2025-03-13
 */
@RestController
@RequestMapping("/stock/stock")
public class StockDeliveryNoteController extends BaseController {
    @Autowired
    private IStockDeliveryNoteService stockDeliveryNoteService;

    /**
     * 查询股票交割单列表
     */
    @PreAuthorize("@ss.hasPermi('stock:stock:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockDeliveryNoteDTO stockDeliveryNoteDTO) {
        startPage();
        List<StockDeliveryNoteDTO> list = stockDeliveryNoteService.selectStockDeliveryNoteList(stockDeliveryNoteDTO);
        return getDataTable(list);
    }

    /**
     * 导出股票交割单列表
     */
    @PreAuthorize("@ss.hasPermi('stock:stock:export')")
    @Log(title = "股票交割单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockDeliveryNoteDTO stockDeliveryNoteDTO) {
        List<StockDeliveryNoteDTO> list = stockDeliveryNoteService.selectStockDeliveryNoteList(stockDeliveryNoteDTO);
        ExcelUtil<StockDeliveryNoteDTO> util = new ExcelUtil<StockDeliveryNoteDTO>(StockDeliveryNoteDTO.class);
        util.exportExcel(response, list, "股票交割单数据");
    }

    /**
     * 获取股票交割单详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:stock:query')")
    @GetMapping(value = "/{stockId}")
    public AjaxResult getInfo(@PathVariable("stockId") Long stockId) {
        return success(stockDeliveryNoteService.selectStockDeliveryNoteByStockId(stockId));
    }

    /**
     * 新增股票交割单
     */
    @PreAuthorize("@ss.hasPermi('stock:stock:add')")
    @Log(title = "股票交割单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockDeliveryNoteDTO stockDeliveryNoteDTO) {
        return toAjax(stockDeliveryNoteService.insertStockDeliveryNote(stockDeliveryNoteDTO));
    }

    /**
     * 修改股票交割单
     */
    @PreAuthorize("@ss.hasPermi('stock:stock:edit')")
    @Log(title = "股票交割单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockDeliveryNoteDTO stockDeliveryNoteDTO) {
        return toAjax(stockDeliveryNoteService.updateStockDeliveryNote(stockDeliveryNoteDTO));
    }

    /**
     * 删除股票交割单
     */
    @PreAuthorize("@ss.hasPermi('stock:stock:remove')")
    @Log(title = "股票交割单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stockIds}")
    public AjaxResult remove(@PathVariable Long[] stockIds) {
        return toAjax(stockDeliveryNoteService.deleteStockDeliveryNoteByStockIds(stockIds));
    }
}
