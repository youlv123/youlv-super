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
import com.ruoyi.stock.domain.StockTradeDate;
import com.ruoyi.stock.service.IStockTradeDateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 股票交易日历Controller
 *
 * @author DXR
 * @date 2025-08-04
 */
@RestController
@RequestMapping("/system/stockTradeDate")
public class StockTradeDateController extends BaseController {
    @Autowired
    private IStockTradeDateService stockTradeDateService;

    /**
     * 查询股票交易日历列表
     */
    @PreAuthorize("@ss.hasPermi('system:stockTradeDate:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockTradeDate stockTradeDate) {
        startPage();
        List<StockTradeDate> list = stockTradeDateService.selectStockTradeDateList(stockTradeDate);
        return getDataTable(list);
    }

    /**
     * 导出股票交易日历列表
     */
    @PreAuthorize("@ss.hasPermi('system:stockTradeDate:export')")
    @Log(title = "股票交易日历", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockTradeDate stockTradeDate) {
        List<StockTradeDate> list = stockTradeDateService.selectStockTradeDateList(stockTradeDate);
        ExcelUtil<StockTradeDate> util = new ExcelUtil<StockTradeDate>(StockTradeDate.class);
        util.exportExcel(response, list, "股票交易日历数据");
    }

    /**
     * 获取股票交易日历详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stockTradeDate:query')")
    @GetMapping(value = "/{tradeDateId}")
    public AjaxResult getInfo(@PathVariable("tradeDateId") Long tradeDateId) {
        return success(stockTradeDateService.selectStockTradeDateByTradeDateId(tradeDateId));
    }

    /**
     * 新增股票交易日历
     */
    @PreAuthorize("@ss.hasPermi('system:stockTradeDate:add')")
    @Log(title = "股票交易日历", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockTradeDate stockTradeDate) {
        return toAjax(stockTradeDateService.insertStockTradeDate(stockTradeDate));
    }

    /**
     * 修改股票交易日历
     */
    @PreAuthorize("@ss.hasPermi('system:stockTradeDate:edit')")
    @Log(title = "股票交易日历", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockTradeDate stockTradeDate) {
        return toAjax(stockTradeDateService.updateStockTradeDate(stockTradeDate));
    }

    /**
     * 删除股票交易日历
     */
    @PreAuthorize("@ss.hasPermi('system:stockTradeDate:remove')")
    @Log(title = "股票交易日历", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tradeDateIds}")
    public AjaxResult remove(@PathVariable Long[] tradeDateIds) {
        return toAjax(stockTradeDateService.deleteStockTradeDateByTradeDateIds(tradeDateIds));
    }
}
