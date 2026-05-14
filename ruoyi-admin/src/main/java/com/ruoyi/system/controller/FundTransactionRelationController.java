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
import com.ruoyi.system.domain.FundTransactionRelation;
import com.ruoyi.system.service.IFundTransactionRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 关联持仓明细Controller
 *
 * @author DXR
 * @date 2025-10-13
 */
@RestController
@RequestMapping("/system/fundTransactionRelation")
public class FundTransactionRelationController extends BaseController {
    @Autowired
    private IFundTransactionRelationService fundTransactionRelationService;

    /**
     * 查询关联持仓明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:fundTransactionRelation:list')")
    @GetMapping("/list")
    public TableDataInfo list(FundTransactionRelation fundTransactionRelation) {
        startPage();
        List<FundTransactionRelation> list = fundTransactionRelationService.selectFundTransactionRelationList(fundTransactionRelation);
        return getDataTable(list);
    }

    /**
     * 导出关联持仓明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:fundTransactionRelation:export')")
    @Log(title = "关联持仓明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FundTransactionRelation fundTransactionRelation) {
        List<FundTransactionRelation> list = fundTransactionRelationService.selectFundTransactionRelationList(fundTransactionRelation);
        ExcelUtil<FundTransactionRelation> util = new ExcelUtil<FundTransactionRelation>(FundTransactionRelation.class);
        util.exportExcel(response, list, "关联持仓明细数据");
    }

    /**
     * 获取关联持仓明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:fundTransactionRelation:query')")
    @GetMapping(value = "/{fundTransactionRelationId}")
    public AjaxResult getInfo(@PathVariable("fundTransactionRelationId") Long fundTransactionRelationId) {
        return success(fundTransactionRelationService.selectFundTransactionRelationByFundTransactionRelationId(fundTransactionRelationId));
    }

    /**
     * 新增关联持仓明细
     */
    @PreAuthorize("@ss.hasPermi('system:fundTransactionRelation:add')")
    @Log(title = "关联持仓明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FundTransactionRelation fundTransactionRelation) {
        return toAjax(fundTransactionRelationService.insertFundTransactionRelation(fundTransactionRelation));
    }

    /**
     * 修改关联持仓明细
     */
    @PreAuthorize("@ss.hasPermi('system:fundTransactionRelation:edit')")
    @Log(title = "关联持仓明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FundTransactionRelation fundTransactionRelation) {
        return toAjax(fundTransactionRelationService.updateFundTransactionRelation(fundTransactionRelation));
    }

    /**
     * 删除关联持仓明细
     */
    @PreAuthorize("@ss.hasPermi('system:fundTransactionRelation:remove')")
    @Log(title = "关联持仓明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fundTransactionRelationIds}")
    public AjaxResult remove(@PathVariable Long[] fundTransactionRelationIds) {
        return toAjax(fundTransactionRelationService.deleteFundTransactionRelationByFundTransactionRelationIds(fundTransactionRelationIds));
    }
}
