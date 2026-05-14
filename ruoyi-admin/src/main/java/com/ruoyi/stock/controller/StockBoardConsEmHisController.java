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
import com.ruoyi.stock.domain.StockBoardConsEmHis;
import com.ruoyi.stock.service.IStockBoardConsEmHisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 板块成分股历史Controller
 *
 * @author DXR
 * @date 2025-07-23
 */
@RestController
@RequestMapping("/system/stockBoardConsEmHis")
public class StockBoardConsEmHisController extends BaseController {
    @Autowired
    private IStockBoardConsEmHisService stockBoardConsEmHisService;

    /**
     * 查询板块成分股历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardConsEmHis:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockBoardConsEmHis stockBoardConsEmHis) {
        startPage();
        List<StockBoardConsEmHis> list = stockBoardConsEmHisService.selectStockBoardConsEmHisList(stockBoardConsEmHis);
        return getDataTable(list);
    }

    /**
     * 导出板块成分股历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardConsEmHis:export')")
    @Log(title = "板块成分股历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockBoardConsEmHis stockBoardConsEmHis) {
        List<StockBoardConsEmHis> list = stockBoardConsEmHisService.selectStockBoardConsEmHisList(stockBoardConsEmHis);
        ExcelUtil<StockBoardConsEmHis> util = new ExcelUtil<StockBoardConsEmHis>(StockBoardConsEmHis.class);
        util.exportExcel(response, list, "板块成分股历史数据");
    }

    /**
     * 获取板块成分股历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardConsEmHis:query')")
    @GetMapping(value = "/{hisBoardConsId}")
    public AjaxResult getInfo(@PathVariable("hisBoardConsId") Long hisBoardConsId) {
        return success(stockBoardConsEmHisService.selectStockBoardConsEmHisByHisBoardConsId(hisBoardConsId));
    }

    /**
     * 新增板块成分股历史
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardConsEmHis:add')")
    @Log(title = "板块成分股历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockBoardConsEmHis stockBoardConsEmHis) {
        return toAjax(stockBoardConsEmHisService.insertStockBoardConsEmHis(stockBoardConsEmHis));
    }

    /**
     * 修改板块成分股历史
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardConsEmHis:edit')")
    @Log(title = "板块成分股历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockBoardConsEmHis stockBoardConsEmHis) {
        return toAjax(stockBoardConsEmHisService.updateStockBoardConsEmHis(stockBoardConsEmHis));
    }

    /**
     * 删除板块成分股历史
     */
    @PreAuthorize("@ss.hasPermi('system:stockBoardConsEmHis:remove')")
    @Log(title = "板块成分股历史", businessType = BusinessType.DELETE)
    @DeleteMapping("/{hisBoardConsIds}")
    public AjaxResult remove(@PathVariable Long[] hisBoardConsIds) {
        return toAjax(stockBoardConsEmHisService.deleteStockBoardConsEmHisByHisBoardConsIds(hisBoardConsIds));
    }
}
