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
import com.ruoyi.stock.domain.StockZhAHist;
import com.ruoyi.stock.service.IStockZhAHistService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 东财历史行情数据Controller
 *
 * @author DXR
 * @date 2025-06-03
 */
@RestController
@RequestMapping("/system/hist")
public class StockZhAHistController extends BaseController {
    @Autowired
    private IStockZhAHistService stockZhAHistService;

    /**
     * 查询东财历史行情数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:hist:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockZhAHist stockZhAHist) {
        startPage();
        List<StockZhAHist> list = stockZhAHistService.selectStockZhAHistList(stockZhAHist);
        return getDataTable(list);
    }

    /**
     * 导出东财历史行情数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:hist:export')")
    @Log(title = "东财历史行情数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockZhAHist stockZhAHist) {
        List<StockZhAHist> list = stockZhAHistService.selectStockZhAHistList(stockZhAHist);
        ExcelUtil<StockZhAHist> util = new ExcelUtil<StockZhAHist>(StockZhAHist.class);
        util.exportExcel(response, list, "东财历史行情数据数据");
    }

    /**
     * 获取东财历史行情数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:hist:query')")
    @GetMapping(value = "/{zhHistId}")
    public AjaxResult getInfo(@PathVariable("zhHistId") Long zhHistId) {
        return success(stockZhAHistService.selectStockZhAHistByZhHistId(zhHistId));
    }

    /**
     * 新增东财历史行情数据
     */
    @PreAuthorize("@ss.hasPermi('system:hist:add')")
    @Log(title = "东财历史行情数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockZhAHist stockZhAHist) {
        return toAjax(stockZhAHistService.insertStockZhAHist(stockZhAHist));
    }

    /**
     * 修改东财历史行情数据
     */
    @PreAuthorize("@ss.hasPermi('system:hist:edit')")
    @Log(title = "东财历史行情数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockZhAHist stockZhAHist) {
        return toAjax(stockZhAHistService.updateStockZhAHist(stockZhAHist));
    }

    /**
     * 删除东财历史行情数据
     */
    @PreAuthorize("@ss.hasPermi('system:hist:remove')")
    @Log(title = "东财历史行情数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{zhHistIds}")
    public AjaxResult remove(@PathVariable Long[] zhHistIds) {
        return toAjax(stockZhAHistService.deleteStockZhAHistByZhHistIds(zhHistIds));
    }
}
