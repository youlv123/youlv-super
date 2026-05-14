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
import com.ruoyi.system.domain.FundPositionDetail;
import com.ruoyi.system.service.IFundPositionDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 理财持仓明细（每笔买入独立记录，FIFO计算核心）Controller
 *
 * @author DXR
 * @date 2025-10-13
 */
@RestController
@RequestMapping("/system/fundPositionDetail")
public class FundPositionDetailController extends BaseController {
    @Autowired
    private IFundPositionDetailService fundPositionDetailService;

    /**
     * 查询理财持仓明细（每笔买入独立记录，FIFO计算核心）列表
     */
    @PreAuthorize("@ss.hasPermi('system:fundPositionDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(FundPositionDetail fundPositionDetail) {
        startPage();
        List<FundPositionDetail> list = fundPositionDetailService.selectFundPositionDetailList(fundPositionDetail);
        return getDataTable(list);
    }

    /**
     * 导出理财持仓明细（每笔买入独立记录，FIFO计算核心）列表
     */
    @PreAuthorize("@ss.hasPermi('system:fundPositionDetail:export')")
    @Log(title = "理财持仓明细（每笔买入独立记录，FIFO计算核心）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FundPositionDetail fundPositionDetail) {
        List<FundPositionDetail> list = fundPositionDetailService.selectFundPositionDetailList(fundPositionDetail);
        ExcelUtil<FundPositionDetail> util = new ExcelUtil<FundPositionDetail>(FundPositionDetail.class);
        util.exportExcel(response, list, "理财持仓明细（每笔买入独立记录，FIFO计算核心）数据");
    }

    /**
     * 获取理财持仓明细（每笔买入独立记录，FIFO计算核心）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:fundPositionDetail:query')")
    @GetMapping(value = "/{fundDetailId}")
    public AjaxResult getInfo(@PathVariable("fundDetailId") Long fundDetailId) {
        return success(fundPositionDetailService.selectFundPositionDetailByFundDetailId(fundDetailId));
    }

    /**
     * 新增理财持仓明细（每笔买入独立记录，FIFO计算核心）
     */
    @PreAuthorize("@ss.hasPermi('system:fundPositionDetail:add')")
    @Log(title = "理财持仓明细（每笔买入独立记录，FIFO计算核心）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FundPositionDetail fundPositionDetail) {
        return toAjax(fundPositionDetailService.insertFundPositionDetail(fundPositionDetail));
    }

    /**
     * 修改理财持仓明细（每笔买入独立记录，FIFO计算核心）
     */
    @PreAuthorize("@ss.hasPermi('system:fundPositionDetail:edit')")
    @Log(title = "理财持仓明细（每笔买入独立记录，FIFO计算核心）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FundPositionDetail fundPositionDetail) {
        return toAjax(fundPositionDetailService.updateFundPositionDetail(fundPositionDetail));
    }

    /**
     * 删除理财持仓明细（每笔买入独立记录，FIFO计算核心）
     */
    @PreAuthorize("@ss.hasPermi('system:fundPositionDetail:remove')")
    @Log(title = "理财持仓明细（每笔买入独立记录，FIFO计算核心）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fundDetailIds}")
    public AjaxResult remove(@PathVariable Long[] fundDetailIds) {
        return toAjax(fundPositionDetailService.deleteFundPositionDetailByFundDetailIds(fundDetailIds));
    }
}
