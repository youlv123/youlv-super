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
import com.ruoyi.stock.domain.StockBoardDataHistEm;
import com.ruoyi.stock.service.IStockBoardDataHistEmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 板块数据历史Controller
 *
 * @author DXR
 * @date 2025-08-17
 */
@RestController
@RequestMapping("/system/stockBoardDataHistEm")
public class StockBoardDataHistEmController extends BaseController {
    @Autowired
    private IStockBoardDataHistEmService stockBoardDataHistEmService;

    /**
     * 查询板块数据历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardDataHistEm:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockBoardDataHistEm stockBoardDataHistEm) {
        startPage();
        List<StockBoardDataHistEm> list = stockBoardDataHistEmService.selectStockBoardDataHistEmList(stockBoardDataHistEm);
        return getDataTable(list);
    }

    /**
     * 导出板块数据历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardDataHistEm:export')")
    @Log(title = "板块数据历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockBoardDataHistEm stockBoardDataHistEm) {
        List<StockBoardDataHistEm> list = stockBoardDataHistEmService.selectStockBoardDataHistEmList(stockBoardDataHistEm);
        ExcelUtil<StockBoardDataHistEm> util = new ExcelUtil<StockBoardDataHistEm>(StockBoardDataHistEm.class);
        util.exportExcel(response, list, "板块数据历史数据");
    }

    /**
     * 获取板块数据历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardDataHistEm:query')")
    @GetMapping(value = "/{boardDataHistId}")
    public AjaxResult getInfo(@PathVariable("boardDataHistId") Long boardDataHistId) {
        return success(stockBoardDataHistEmService.selectStockBoardDataHistEmByBoardDataHistId(boardDataHistId));
    }

    /**
     * 新增板块数据历史
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardDataHistEm:add')")
    @Log(title = "板块数据历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockBoardDataHistEm stockBoardDataHistEm) {
        return toAjax(stockBoardDataHistEmService.insertStockBoardDataHistEm(stockBoardDataHistEm));
    }

    /**
     * 修改板块数据历史
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardDataHistEm:edit')")
    @Log(title = "板块数据历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockBoardDataHistEm stockBoardDataHistEm) {
        return toAjax(stockBoardDataHistEmService.updateStockBoardDataHistEm(stockBoardDataHistEm));
    }

    /**
     * 删除板块数据历史
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardDataHistEm:remove')")
    @Log(title = "板块数据历史", businessType = BusinessType.DELETE)
    @DeleteMapping("/{boardDataHistIds}")
    public AjaxResult remove(@PathVariable Long[] boardDataHistIds) {
        return toAjax(stockBoardDataHistEmService.deleteStockBoardDataHistEmByBoardDataHistIds(boardDataHistIds));
    }
}
