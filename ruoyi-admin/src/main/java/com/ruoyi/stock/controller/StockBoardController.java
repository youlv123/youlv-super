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
import com.ruoyi.stock.domain.StockBoard;
import com.ruoyi.stock.service.IStockBoardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 东方财富-概念板块Controller
 *
 * @author DXR
 * @date 2025-06-08
 */
@RestController
@RequestMapping("/system/em")
public class StockBoardController extends BaseController {
    @Autowired
    private IStockBoardService stockBoardConceptNameEmService;

    /**
     * 查询东方财富-概念板块列表
     */
    @PreAuthorize("@ss.hasPermi('system:em:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockBoard stockBoard) {
        startPage();
        List<StockBoard> list = stockBoardConceptNameEmService.selectStockBoardList(stockBoard);
        return getDataTable(list);
    }

    /**
     * 导出东方财富-概念板块列表
     */
    @PreAuthorize("@ss.hasPermi('system:em:export')")
    @Log(title = "东方财富-概念板块", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockBoard stockBoard) {
        List<StockBoard> list = stockBoardConceptNameEmService.selectStockBoardList(stockBoard);
        ExcelUtil<StockBoard> util = new ExcelUtil<StockBoard>(StockBoard.class);
        util.exportExcel(response, list, "东方财富-概念板块数据");
    }

    /**
     * 获取东方财富-概念板块详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:em:query')")
    @GetMapping(value = "/{boardId}")
    public AjaxResult getInfo(@PathVariable("boardId") Long boardId) {
        return success(stockBoardConceptNameEmService.selectStockBoardByBoardId(boardId));
    }

    /**
     * 新增东方财富-概念板块
     */
    @PreAuthorize("@ss.hasPermi('system:em:add')")
    @Log(title = "东方财富-概念板块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockBoard stockBoard) {
        return toAjax(stockBoardConceptNameEmService.insertStockBoard(stockBoard));
    }

    /**
     * 修改东方财富-概念板块
     */
    @PreAuthorize("@ss.hasPermi('system:em:edit')")
    @Log(title = "东方财富-概念板块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockBoard stockBoard) {
        return toAjax(stockBoardConceptNameEmService.updateStockBoard(stockBoard));
    }

    /**
     * 删除东方财富-概念板块
     */
    @PreAuthorize("@ss.hasPermi('system:em:remove')")
    @Log(title = "东方财富-概念板块", businessType = BusinessType.DELETE)
    @DeleteMapping("/{boardIds}")
    public AjaxResult remove(@PathVariable Long[] boardIds) {
        return toAjax(stockBoardConceptNameEmService.deleteStockBoardByBoardIds(boardIds));
    }
}
