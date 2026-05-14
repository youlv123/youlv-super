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
import com.ruoyi.system.domain.FinancialTransaction;
import com.ruoyi.system.service.IFinancialTransactionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 理财交易记录（记录所有买入/卖出操作，关联持仓明细）Controller
 *
 * @author DXR
 * @date 2025-10-13
 */
@RestController
@RequestMapping("/system/financialTransaction")
public class FinancialTransactionController extends BaseController {
    @Autowired
    private IFinancialTransactionService financialTransactionService;

    /**
     * 查询理财交易记录（记录所有买入/卖出操作，关联持仓明细）列表
     */
    @PreAuthorize("@ss.hasPermi('system:financialTransaction:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialTransaction financialTransaction) {
        startPage();
        List<FinancialTransaction> list = financialTransactionService.selectFinancialTransactionList(financialTransaction);
        return getDataTable(list);
    }

    /**
     * 导出理财交易记录（记录所有买入/卖出操作，关联持仓明细）列表
     */
    @PreAuthorize("@ss.hasPermi('system:financialTransaction:export')")
    @Log(title = "理财交易记录（记录所有买入/卖出操作，关联持仓明细）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialTransaction financialTransaction) {
        List<FinancialTransaction> list = financialTransactionService.selectFinancialTransactionList(financialTransaction);
        ExcelUtil<FinancialTransaction> util = new ExcelUtil<FinancialTransaction>(FinancialTransaction.class);
        util.exportExcel(response, list, "理财交易记录（记录所有买入/卖出操作，关联持仓明细）数据");
    }

    /**
     * 获取理财交易记录（记录所有买入/卖出操作，关联持仓明细）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:financialTransaction:query')")
    @GetMapping(value = "/{transactionId}")
    public AjaxResult getInfo(@PathVariable("transactionId") Long transactionId) {
        return success(financialTransactionService.selectFinancialTransactionByTransactionId(transactionId));
    }

    /**
     * 新增理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     */
    @PreAuthorize("@ss.hasPermi('system:financialTransaction:add')")
    @Log(title = "理财交易记录（记录所有买入/卖出操作，关联持仓明细）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialTransaction financialTransaction) {
        return toAjax(financialTransactionService.insertFinancialTransaction(financialTransaction));
    }

    /**
     * 修改理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     */
    @PreAuthorize("@ss.hasPermi('system:financialTransaction:edit')")
    @Log(title = "理财交易记录（记录所有买入/卖出操作，关联持仓明细）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialTransaction financialTransaction) {
        return toAjax(financialTransactionService.updateFinancialTransaction(financialTransaction));
    }

    /**
     * 删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）
     */
    @PreAuthorize("@ss.hasPermi('system:financialTransaction:remove')")
    @Log(title = "理财交易记录（记录所有买入/卖出操作，关联持仓明细）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{transactionIds}")
    public AjaxResult remove(@PathVariable Long[] transactionIds) {
        return toAjax(financialTransactionService.deleteFinancialTransactionByTransactionIds(transactionIds));
    }
}
