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
import com.ruoyi.stock.domain.StockZtPoolEm;
import com.ruoyi.stock.service.IStockZtPoolEmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 涨停板行情Controller
 *
 * @author DXR
 * @date 2025-07-15
 */
@RestController
@RequestMapping("/stock/stockZtPoolEm")
public class StockZtPoolEmController extends BaseController {
    @Autowired
    private IStockZtPoolEmService stockZtPoolEmService;

    /**
     * 查询涨停板行情列表
     */
    @PreAuthorize("@ss.hasPermi('stock:stockZtPoolEm:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockZtPoolEm stockZtPoolEm) {
        startPage();
        List<StockZtPoolEm> list = stockZtPoolEmService.selectStockZtPoolEmList(stockZtPoolEm);
        return getDataTable(list);
    }

    /**
     * 导出涨停板行情列表
     */
    @PreAuthorize("@ss.hasPermi('stock:stockZtPoolEm:export')")
    @Log(title = "涨停板行情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockZtPoolEm stockZtPoolEm) {
        List<StockZtPoolEm> list = stockZtPoolEmService.selectStockZtPoolEmList(stockZtPoolEm);
        ExcelUtil<StockZtPoolEm> util = new ExcelUtil<StockZtPoolEm>(StockZtPoolEm.class);
        util.exportExcel(response, list, "涨停板行情数据");
    }

    /**
     * 获取涨停板行情详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:stockZtPoolEm:query')")
    @GetMapping(value = "/{ztId}")
    public AjaxResult getInfo(@PathVariable("ztId") Long ztId) {
        return success(stockZtPoolEmService.selectStockZtPoolEmByZtId(ztId));
    }

    /**
     * 新增涨停板行情
     */
    @PreAuthorize("@ss.hasPermi('stock:stockZtPoolEm:add')")
    @Log(title = "涨停板行情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockZtPoolEm stockZtPoolEm) {
        return toAjax(stockZtPoolEmService.insertStockZtPoolEm(stockZtPoolEm));
    }

    /**
     * 修改涨停板行情
     */
    @PreAuthorize("@ss.hasPermi('stock:stockZtPoolEm:edit')")
    @Log(title = "涨停板行情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockZtPoolEm stockZtPoolEm) {
        return toAjax(stockZtPoolEmService.updateStockZtPoolEm(stockZtPoolEm));
    }

    /**
     * 删除涨停板行情
     */
    @PreAuthorize("@ss.hasPermi('stock:stockZtPoolEm:remove')")
    @Log(title = "涨停板行情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ztIds}")
    public AjaxResult remove(@PathVariable Long[] ztIds) {
        return toAjax(stockZtPoolEmService.deleteStockZtPoolEmByZtIds(ztIds));
    }
}
