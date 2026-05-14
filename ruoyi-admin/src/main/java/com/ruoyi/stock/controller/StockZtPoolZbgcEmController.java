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
import com.ruoyi.stock.domain.StockZtPoolZbgcEm;
import com.ruoyi.stock.service.IStockZtPoolZbgcEmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 炸板股池Controller
 *
 * @author DXR
 * @date 2025-05-21
 */
@RestController
@RequestMapping("/stock/zbgcem")
public class StockZtPoolZbgcEmController extends BaseController {
    @Autowired
    private IStockZtPoolZbgcEmService stockZtPoolZbgcEmService;

    /**
     * 查询炸板股池列表
     */
    @PreAuthorize("@ss.hasPermi('system:em:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockZtPoolZbgcEm stockZtPoolZbgcEm) {
        startPage();
        List<StockZtPoolZbgcEm> list = stockZtPoolZbgcEmService.selectStockZtPoolZbgcEmList(stockZtPoolZbgcEm);
        return getDataTable(list);
    }

    /**
     * 导出炸板股池列表
     */
    @PreAuthorize("@ss.hasPermi('system:em:export')")
    @Log(title = "炸板股池", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockZtPoolZbgcEm stockZtPoolZbgcEm) {
        List<StockZtPoolZbgcEm> list = stockZtPoolZbgcEmService.selectStockZtPoolZbgcEmList(stockZtPoolZbgcEm);
        ExcelUtil<StockZtPoolZbgcEm> util = new ExcelUtil<StockZtPoolZbgcEm>(StockZtPoolZbgcEm.class);
        util.exportExcel(response, list, "炸板股池数据");
    }

    /**
     * 获取炸板股池详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:em:query')")
    @GetMapping(value = "/{zbgcId}")
    public AjaxResult getInfo(@PathVariable("zbgcId") Long zbgcId) {
        return success(stockZtPoolZbgcEmService.selectStockZtPoolZbgcEmByZbgcId(zbgcId));
    }

    /**
     * 新增炸板股池
     */
    @PreAuthorize("@ss.hasPermi('system:em:add')")
    @Log(title = "炸板股池", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockZtPoolZbgcEm stockZtPoolZbgcEm) {
        return toAjax(stockZtPoolZbgcEmService.insertStockZtPoolZbgcEm(stockZtPoolZbgcEm));
    }

    /**
     * 修改炸板股池
     */
    @PreAuthorize("@ss.hasPermi('system:em:edit')")
    @Log(title = "炸板股池", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockZtPoolZbgcEm stockZtPoolZbgcEm) {
        return toAjax(stockZtPoolZbgcEmService.updateStockZtPoolZbgcEm(stockZtPoolZbgcEm));
    }

    /**
     * 删除炸板股池
     */
    @PreAuthorize("@ss.hasPermi('system:em:remove')")
    @Log(title = "炸板股池", businessType = BusinessType.DELETE)
    @DeleteMapping("/{zbgcIds}")
    public AjaxResult remove(@PathVariable Long[] zbgcIds) {
        return toAjax(stockZtPoolZbgcEmService.deleteStockZtPoolZbgcEmByZbgcIds(zbgcIds));
    }
}
