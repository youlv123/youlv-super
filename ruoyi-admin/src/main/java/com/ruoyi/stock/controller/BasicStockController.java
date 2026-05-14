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
import com.ruoyi.stock.domain.BasicStock;
import com.ruoyi.stock.service.IBasicStockService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础股票Controller
 *
 * @author DXR
 * @date 2025-07-31
 */
@RestController
@RequestMapping("/system/basicStock")
public class BasicStockController extends BaseController {
    @Autowired
    private IBasicStockService basicStockService;

    /**
     * 查询基础股票列表
     */
    @PreAuthorize("@ss.hasPermi('system:basicStock:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicStock basicStock) {
        startPage();
        List<BasicStock> list = basicStockService.selectBasicStockList(basicStock);
        return getDataTable(list);
    }

    /**
     * 导出基础股票列表
     */
    @PreAuthorize("@ss.hasPermi('system:basicStock:export')")
    @Log(title = "基础股票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BasicStock basicStock) {
        List<BasicStock> list = basicStockService.selectBasicStockList(basicStock);
        ExcelUtil<BasicStock> util = new ExcelUtil<BasicStock>(BasicStock.class);
        util.exportExcel(response, list, "基础股票数据");
    }

    /**
     * 获取基础股票详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:basicStock:query')")
    @GetMapping(value = "/{basicStockId}")
    public AjaxResult getInfo(@PathVariable("basicStockId") Long basicStockId) {
        return success(basicStockService.selectBasicStockByBasicStockId(basicStockId));
    }

    /**
     * 新增基础股票
     */
    @PreAuthorize("@ss.hasPermi('system:basicStock:add')")
    @Log(title = "基础股票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicStock basicStock) {
        return toAjax(basicStockService.insertBasicStock(basicStock));
    }

    /**
     * 修改基础股票
     */
    @PreAuthorize("@ss.hasPermi('system:basicStock:edit')")
    @Log(title = "基础股票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicStock basicStock) {
        return toAjax(basicStockService.updateBasicStock(basicStock));
    }

    /**
     * 删除基础股票
     */
    @PreAuthorize("@ss.hasPermi('system:basicStock:remove')")
    @Log(title = "基础股票", businessType = BusinessType.DELETE)
    @DeleteMapping("/{basicStockIds}")
    public AjaxResult remove(@PathVariable Long[] basicStockIds) {
        return toAjax(basicStockService.deleteBasicStockByBasicStockIds(basicStockIds));
    }
}
