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
import com.ruoyi.stock.domain.StockZtPoolStrongEm;
import com.ruoyi.stock.service.IStockZtPoolStrongEmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 强势股池Controller
 *
 * @author DXR
 * @date 2025-05-20
 */
@RestController
@RequestMapping("/stock/strongem")
public class StockZtPoolStrongEmController extends BaseController {
    @Autowired
    private IStockZtPoolStrongEmService stockZtPoolStrongEmService;

    /**
     * 查询强势股池列表
     */
    @PreAuthorize("@ss.hasPermi('system:em:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockZtPoolStrongEm stockZtPoolStrongEm) {
        startPage();
        List<StockZtPoolStrongEm> list = stockZtPoolStrongEmService.selectStockZtPoolStrongEmList(stockZtPoolStrongEm);
        return getDataTable(list);
    }

    /**
     * 导出强势股池列表
     */
    @PreAuthorize("@ss.hasPermi('system:em:export')")
    @Log(title = "强势股池", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockZtPoolStrongEm stockZtPoolStrongEm) {
        List<StockZtPoolStrongEm> list = stockZtPoolStrongEmService.selectStockZtPoolStrongEmList(stockZtPoolStrongEm);
        ExcelUtil<StockZtPoolStrongEm> util = new ExcelUtil<StockZtPoolStrongEm>(StockZtPoolStrongEm.class);
        util.exportExcel(response, list, "强势股池数据");
    }

    /**
     * 获取强势股池详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:em:query')")
    @GetMapping(value = "/{ztsId}")
    public AjaxResult getInfo(@PathVariable("ztsId") Long ztsId) {
        return success(stockZtPoolStrongEmService.selectStockZtPoolStrongEmByZtsId(ztsId));
    }

    /**
     * 新增强势股池
     */
    @PreAuthorize("@ss.hasPermi('system:em:add')")
    @Log(title = "强势股池", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockZtPoolStrongEm stockZtPoolStrongEm) {
        return toAjax(stockZtPoolStrongEmService.insertStockZtPoolStrongEm(stockZtPoolStrongEm));
    }

    /**
     * 修改强势股池
     */
    @PreAuthorize("@ss.hasPermi('system:em:edit')")
    @Log(title = "强势股池", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockZtPoolStrongEm stockZtPoolStrongEm) {
        return toAjax(stockZtPoolStrongEmService.updateStockZtPoolStrongEm(stockZtPoolStrongEm));
    }

    /**
     * 删除强势股池
     */
    @PreAuthorize("@ss.hasPermi('system:em:remove')")
    @Log(title = "强势股池", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ztsIds}")
    public AjaxResult remove(@PathVariable Long[] ztsIds) {
        return toAjax(stockZtPoolStrongEmService.deleteStockZtPoolStrongEmByZtsIds(ztsIds));
    }
}
