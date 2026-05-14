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
import com.ruoyi.stock.domain.StockBoardHis;
import com.ruoyi.stock.service.IStockBoardHisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 板块历史Controller
 *
 * @author DXR
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/system/boardHis")
public class StockBoardHisController extends BaseController {
    @Autowired
    private IStockBoardHisService stockBoardHisService;

    /**
     * 查询板块历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:boardHis :list')")
    @GetMapping("/list")
    public TableDataInfo list(StockBoardHis stockBoardHis) {
        startPage();
        List<StockBoardHis> list = stockBoardHisService.selectStockBoardHisList(stockBoardHis);
        return getDataTable(list);
    }

    /**
     * 导出板块历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:boardHis :export')")
    @Log(title = "板块历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockBoardHis stockBoardHis) {
        List<StockBoardHis> list = stockBoardHisService.selectStockBoardHisList(stockBoardHis);
        ExcelUtil<StockBoardHis> util = new ExcelUtil<StockBoardHis>(StockBoardHis.class);
        util.exportExcel(response, list, "板块历史数据");
    }

    /**
     * 获取板块历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:boardHis :query')")
    @GetMapping(value = "/{hisBoardId}")
    public AjaxResult getInfo(@PathVariable("hisBoardId") Long hisBoardId) {
        return success(stockBoardHisService.selectStockBoardHisByHisBoardId(hisBoardId));
    }

    /**
     * 新增板块历史
     */
    @PreAuthorize("@ss.hasPermi('system:boardHis :add')")
    @Log(title = "板块历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockBoardHis stockBoardHis) {
        return toAjax(stockBoardHisService.insertStockBoardHis(stockBoardHis));
    }

    /**
     * 修改板块历史
     */
    @PreAuthorize("@ss.hasPermi('system:boardHis :edit')")
    @Log(title = "板块历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockBoardHis stockBoardHis) {
        return toAjax(stockBoardHisService.updateStockBoardHis(stockBoardHis));
    }

    /**
     * 删除板块历史
     */
    @PreAuthorize("@ss.hasPermi('system:boardHis :remove')")
    @Log(title = "板块历史", businessType = BusinessType.DELETE)
    @DeleteMapping("/{hisBoardIds}")
    public AjaxResult remove(@PathVariable Long[] hisBoardIds) {
        return toAjax(stockBoardHisService.deleteStockBoardHisByHisBoardIds(hisBoardIds));
    }
}
