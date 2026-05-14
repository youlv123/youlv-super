package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.FinancialTotal;
import com.ruoyi.system.service.IFinancialTotalService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 理财汇总Controller
 *
 * @author DXR
 * @date 2025-09-13
 */
@RestController
@RequestMapping("/system/financialTotal")
public class FinancialTotalController extends BaseController {
    @Autowired
    private IFinancialTotalService financialTotalService;

    /**
     * 查询理财汇总列表
     */
    @PreAuthorize("@ss.hasPermi('system:financialTotal:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialTotal financialTotal) {
        startPage();
        List<FinancialTotal> list = financialTotalService.selectFinancialTotalList(financialTotal);
        return getDataTable(list);
    }

    /**
     * 导出理财汇总列表
     */
    @PreAuthorize("@ss.hasPermi('system:financialTotal:export')")
    @Log(title = "理财汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialTotal financialTotal) {
        List<FinancialTotal> list = financialTotalService.selectFinancialTotalList(financialTotal);
        ExcelUtil<FinancialTotal> util = new ExcelUtil<FinancialTotal>(FinancialTotal.class);
        util.exportExcel(response, list, "理财汇总数据");
    }

    /**
     * 获取理财汇总详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:financialTotal:query')")
    @GetMapping(value = "/{financialTotalId}")
    public AjaxResult getInfo(@PathVariable("financialTotalId") Long financialTotalId) {
        return success(financialTotalService.selectFinancialTotalByFinancialTotalId(financialTotalId));
    }

    /**
     * 新增理财汇总
     */
    @PreAuthorize("@ss.hasPermi('system:financialTotal:add')")
    @Log(title = "理财汇总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialTotal financialTotal) {
        return toAjax(financialTotalService.insertFinancialTotal(financialTotal));
    }

    /**
     * 修改理财汇总
     */
    @PreAuthorize("@ss.hasPermi('system:financialTotal:edit')")
    @Log(title = "理财汇总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialTotal financialTotal) {
        return toAjax(financialTotalService.updateFinancialTotal(financialTotal));
    }

    /**
     * 删除理财汇总
     */
    @PreAuthorize("@ss.hasPermi('system:financialTotal:remove')")
    @Log(title = "理财汇总", businessType = BusinessType.DELETE)
    @DeleteMapping("/{financialTotalIds}")
    public AjaxResult remove(@PathVariable Long[] financialTotalIds) {
        return toAjax(financialTotalService.deleteFinancialTotalByFinancialTotalIds(financialTotalIds));
    }
}
