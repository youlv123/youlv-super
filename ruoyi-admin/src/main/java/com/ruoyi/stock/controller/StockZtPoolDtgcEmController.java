package com.ruoyi.stock.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.stock.domain.StockZtPoolDtgcEm;
import com.ruoyi.stock.service.IStockZtPoolDtgcEmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 跌停股池Controller
 *
 * @author DXR
 * @date 2025-05-25
 */
@RestController
@RequestMapping("/stock/dtgcem")
public class StockZtPoolDtgcEmController extends BaseController {
    @Autowired
    private IStockZtPoolDtgcEmService stockZtPoolDtgcEmService;

    /**
     * 查询跌停股池列表
     */
    @PreAuthorize("@ss.hasPermi('stock:em:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockZtPoolDtgcEm stockZtPoolDtgcEm) {
        startPage();
        List<StockZtPoolDtgcEm> list = stockZtPoolDtgcEmService.selectStockZtPoolDtgcEmList(stockZtPoolDtgcEm);
        return getDataTable(list);
    }

    /**
     * 导出跌停股池列表
     */
    @PreAuthorize("@ss.hasPermi('stock:em:export')")
    @Log(title = "跌停股池", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockZtPoolDtgcEm stockZtPoolDtgcEm) {
        List<StockZtPoolDtgcEm> list = stockZtPoolDtgcEmService.selectStockZtPoolDtgcEmList(stockZtPoolDtgcEm);
        ExcelUtil<StockZtPoolDtgcEm> util = new ExcelUtil<StockZtPoolDtgcEm>(StockZtPoolDtgcEm.class);
        util.exportExcel(response, list, "跌停股池数据");
    }

    /**
     * 获取跌停股池详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:em:query')")
    @GetMapping(value = "/{dtgcId}")
    public AjaxResult getInfo(@PathVariable("dtgcId") Long dtgcId) {
        return success(stockZtPoolDtgcEmService.selectStockZtPoolDtgcEmByDtgcId(dtgcId));
    }

    /**
     * 新增跌停股池
     */
    @PreAuthorize("@ss.hasPermi('stock:em:add')")
    @Log(title = "跌停股池", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockZtPoolDtgcEm stockZtPoolDtgcEm) {
        return toAjax(stockZtPoolDtgcEmService.insertStockZtPoolDtgcEm(stockZtPoolDtgcEm));
    }

    /**
     * 修改跌停股池
     */
    @PreAuthorize("@ss.hasPermi('stock:em:edit')")
    @Log(title = "跌停股池", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockZtPoolDtgcEm stockZtPoolDtgcEm) {
        return toAjax(stockZtPoolDtgcEmService.updateStockZtPoolDtgcEm(stockZtPoolDtgcEm));
    }

    /**
     * 删除跌停股池
     */
    @PreAuthorize("@ss.hasPermi('stock:em:remove')")
    @Log(title = "跌停股池", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dtgcIds}")
    public AjaxResult remove(@PathVariable Long[] dtgcIds) {
        return toAjax(stockZtPoolDtgcEmService.deleteStockZtPoolDtgcEmByDtgcIds(dtgcIds));
    }
}
