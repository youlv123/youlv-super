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
import com.ruoyi.stock.domain.ThsBoardFundFlow;
import com.ruoyi.stock.service.IThsBoardFundFlowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 东方财富板块资金流向Controller
 *
 * @author DXR
 * @date 2025-08-23
 */
@RestController
@RequestMapping("/system/thsBoardFundFlow")
public class ThsBoardFundFlowController extends BaseController {
    @Autowired
    private IThsBoardFundFlowService thsBoardFundFlowService;

    /**
     * 查询东方财富板块资金流向列表
     */
    @PreAuthorize("@ss.hasPermi('system:thsBoardFundFlow:list')")
    @GetMapping("/list")
    public TableDataInfo list(ThsBoardFundFlow thsBoardFundFlow) {
        startPage();
        List<ThsBoardFundFlow> list = thsBoardFundFlowService.selectThsBoardFundFlowList(thsBoardFundFlow);
        return getDataTable(list);
    }

    /**
     * 导出东方财富板块资金流向列表
     */
    @PreAuthorize("@ss.hasPermi('system:thsBoardFundFlow:export')")
    @Log(title = "东方财富板块资金流向", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ThsBoardFundFlow thsBoardFundFlow) {
        List<ThsBoardFundFlow> list = thsBoardFundFlowService.selectThsBoardFundFlowList(thsBoardFundFlow);
        ExcelUtil<ThsBoardFundFlow> util = new ExcelUtil<ThsBoardFundFlow>(ThsBoardFundFlow.class);
        util.exportExcel(response, list, "东方财富板块资金流向数据");
    }

    /**
     * 获取东方财富板块资金流向详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:thsBoardFundFlow:query')")
    @GetMapping(value = "/{fundFlowBoardId}")
    public AjaxResult getInfo(@PathVariable("fundFlowBoardId") Long fundFlowBoardId) {
        return success(thsBoardFundFlowService.selectThsBoardFundFlowByFundFlowBoardId(fundFlowBoardId));
    }

    /**
     * 新增东方财富板块资金流向
     */
    @PreAuthorize("@ss.hasPermi('system:thsBoardFundFlow:add')")
    @Log(title = "东方财富板块资金流向", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ThsBoardFundFlow thsBoardFundFlow) {
        return toAjax(thsBoardFundFlowService.insertThsBoardFundFlow(thsBoardFundFlow));
    }

    /**
     * 修改东方财富板块资金流向
     */
    @PreAuthorize("@ss.hasPermi('system:thsBoardFundFlow:edit')")
    @Log(title = "东方财富板块资金流向", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ThsBoardFundFlow thsBoardFundFlow) {
        return toAjax(thsBoardFundFlowService.updateThsBoardFundFlow(thsBoardFundFlow));
    }

    /**
     * 删除东方财富板块资金流向
     */
    @PreAuthorize("@ss.hasPermi('system:thsBoardFundFlow:remove')")
    @Log(title = "东方财富板块资金流向", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fundFlowBoardIds}")
    public AjaxResult remove(@PathVariable Long[] fundFlowBoardIds) {
        return toAjax(thsBoardFundFlowService.deleteThsBoardFundFlowByFundFlowBoardIds(fundFlowBoardIds));
    }
}
