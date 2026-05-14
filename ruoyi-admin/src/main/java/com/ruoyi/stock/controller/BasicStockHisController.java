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
import com.ruoyi.stock.domain.BasicStockHis;
import com.ruoyi.stock.service.IBasicStockHisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础股票历史Controller
 *
 * @author ruoyi
 * @date 2025-08-03
 */
@RestController
@RequestMapping("/system/basicStockHis")
public class BasicStockHisController extends BaseController {
    @Autowired
    private IBasicStockHisService basicStockHisService;

    /**
     * 查询基础股票历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:basicStockHis:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicStockHis basicStockHis) {
        startPage();
        List<BasicStockHis> list = basicStockHisService.selectBasicStockHisList(basicStockHis);
        return getDataTable(list);
    }

    /**
     * 导出基础股票历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:basicStockHis:export')")
    @Log(title = "基础股票历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BasicStockHis basicStockHis) {
        List<BasicStockHis> list = basicStockHisService.selectBasicStockHisList(basicStockHis);
        ExcelUtil<BasicStockHis> util = new ExcelUtil<BasicStockHis>(BasicStockHis.class);
        util.exportExcel(response, list, "基础股票历史数据");
    }

    /**
     * 获取基础股票历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:basicStockHis:query')")
    @GetMapping(value = "/{basicStockId}")
    public AjaxResult getInfo(@PathVariable("basicStockId") Long basicStockId) {
        return success(basicStockHisService.selectBasicStockHisByBasicStockId(basicStockId));
    }

    /**
     * 新增基础股票历史
     */
    @PreAuthorize("@ss.hasPermi('system:basicStockHis:add')")
    @Log(title = "基础股票历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicStockHis basicStockHis) {
        return toAjax(basicStockHisService.insertBasicStockHis(basicStockHis));
    }

    /**
     * 修改基础股票历史
     */
    @PreAuthorize("@ss.hasPermi('system:basicStockHis:edit')")
    @Log(title = "基础股票历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicStockHis basicStockHis) {
        return toAjax(basicStockHisService.updateBasicStockHis(basicStockHis));
    }

    /**
     * 删除基础股票历史
     */
    @PreAuthorize("@ss.hasPermi('system:basicStockHis:remove')")
    @Log(title = "基础股票历史", businessType = BusinessType.DELETE)
    @DeleteMapping("/{basicStockIds}")
    public AjaxResult remove(@PathVariable Long[] basicStockIds) {
        return toAjax(basicStockHisService.deleteBasicStockHisByBasicStockIds(basicStockIds));
    }
}
