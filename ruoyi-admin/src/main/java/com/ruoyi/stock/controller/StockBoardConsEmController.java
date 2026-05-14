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
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.service.IStockBoardConsEmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 板块成分股Controller
 *
 * @author DXR
 * @date 2025-06-15
 */
@RestController
@RequestMapping("/system/cons")
public class StockBoardConsEmController extends BaseController {
    @Autowired
    private IStockBoardConsEmService stockBoardConsEmService;

    /**
     * 查询板块成分股列表
     */
    @PreAuthorize("@ss.hasPermi('system:em:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockBoardConsEm stockBoardConsEm) {
        startPage();
        List<StockBoardConsEm> list = stockBoardConsEmService.selectStockBoardConsEmList(stockBoardConsEm);
        return getDataTable(list);
    }

    /**
     * 导出板块成分股列表
     */
    @PreAuthorize("@ss.hasPermi('system:em:export')")
    @Log(title = "板块成分股", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockBoardConsEm stockBoardConsEm) {
        List<StockBoardConsEm> list = stockBoardConsEmService.selectStockBoardConsEmList(stockBoardConsEm);
        ExcelUtil<StockBoardConsEm> util = new ExcelUtil<StockBoardConsEm>(StockBoardConsEm.class);
        util.exportExcel(response, list, "板块成分股数据");
    }

    /**
     * 获取板块成分股详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:em:query')")
    @GetMapping(value = "/{boardConsId}")
    public AjaxResult getInfo(@PathVariable("boardConsId") Long boardConsId) {
        return success(stockBoardConsEmService.selectStockBoardConsEmByBoardConsId(boardConsId));
    }

    /**
     * 新增板块成分股
     */
    @PreAuthorize("@ss.hasPermi('system:em:add')")
    @Log(title = "板块成分股", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockBoardConsEm stockBoardConsEm) {
        return toAjax(stockBoardConsEmService.insertStockBoardConsEm(stockBoardConsEm));
    }

    /**
     * 修改板块成分股
     */
    @PreAuthorize("@ss.hasPermi('system:em:edit')")
    @Log(title = "板块成分股", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockBoardConsEm stockBoardConsEm) {
        return toAjax(stockBoardConsEmService.updateStockBoardConsEm(stockBoardConsEm));
    }

    /**
     * 删除板块成分股
     */
    @PreAuthorize("@ss.hasPermi('system:em:remove')")
    @Log(title = "板块成分股", businessType = BusinessType.DELETE)
    @DeleteMapping("/{boardConsIds}")
    public AjaxResult remove(@PathVariable Long[] boardConsIds) {
        return toAjax(stockBoardConsEmService.deleteStockBoardConsEmByBoardConsIds(boardConsIds));
    }
}
