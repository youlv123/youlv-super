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
import com.ruoyi.stock.domain.ThsStockFundFlow;
import com.ruoyi.stock.service.IThsStockFundFlowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 同花顺个股资金流向Controller
 *
 * @author DXR
 * @date 2025-08-20
 */
@RestController
@RequestMapping("/system/thsStockFundFlow")
public class ThsStockFundFlowController extends BaseController {
    @Autowired
    private IThsStockFundFlowService thsStockFundFlowService;

    /**
     * 查询同花顺个股资金流向列表
     */
    @PreAuthorize("@ss.hasPermi('system:thsStockFundFlow:list')")
    @GetMapping("/list")
    public TableDataInfo list(ThsStockFundFlow thsStockFundFlow) {
        startPage();
        List<ThsStockFundFlow> list = thsStockFundFlowService.selectThsStockFundFlowList(thsStockFundFlow);
        return getDataTable(list);
    }

    /**
     * 导出同花顺个股资金流向列表
     */
    @PreAuthorize("@ss.hasPermi('system:thsStockFundFlow:export')")
    @Log(title = "同花顺个股资金流向", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ThsStockFundFlow thsStockFundFlow) {
        List<ThsStockFundFlow> list = thsStockFundFlowService.selectThsStockFundFlowList(thsStockFundFlow);
        ExcelUtil<ThsStockFundFlow> util = new ExcelUtil<ThsStockFundFlow>(ThsStockFundFlow.class);
        util.exportExcel(response, list, "同花顺个股资金流向数据");
    }

    /**
     * 获取同花顺个股资金流向详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:thsStockFundFlow:query')")
    @GetMapping(value = "/{fundFlowIndividualId}")
    public AjaxResult getInfo(@PathVariable("fundFlowIndividualId") Long fundFlowIndividualId) {
        return success(thsStockFundFlowService.selectThsStockFundFlowByFundFlowIndividualId(fundFlowIndividualId));
    }

    /**
     * 新增同花顺个股资金流向
     */
    @PreAuthorize("@ss.hasPermi('system:thsStockFundFlow:add')")
    @Log(title = "同花顺个股资金流向", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ThsStockFundFlow thsStockFundFlow) {
        return toAjax(thsStockFundFlowService.insertThsStockFundFlow(thsStockFundFlow));
    }

    /**
     * 修改同花顺个股资金流向
     */
    @PreAuthorize("@ss.hasPermi('system:thsStockFundFlow:edit')")
    @Log(title = "同花顺个股资金流向", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ThsStockFundFlow thsStockFundFlow) {
        return toAjax(thsStockFundFlowService.updateThsStockFundFlow(thsStockFundFlow));
    }

    /**
     * 删除同花顺个股资金流向
     */
    @PreAuthorize("@ss.hasPermi('system:thsStockFundFlow:remove')")
    @Log(title = "同花顺个股资金流向", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fundFlowIndividualIds}")
    public AjaxResult remove(@PathVariable Long[] fundFlowIndividualIds) {
        return toAjax(thsStockFundFlowService.deleteThsStockFundFlowByFundFlowIndividualIds(fundFlowIndividualIds));
    }
}
