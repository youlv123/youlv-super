package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
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
import com.ruoyi.system.domain.FinancialRecord;
import com.ruoyi.system.service.IFinancialRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 理财记录Controller
 *
 * @author DXR
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/system/financialRecord")
public class FinancialRecordController extends BaseController {
    @Autowired
    private IFinancialRecordService financialRecordService;

    /**
     * 查询理财记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:financialRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialRecord financialRecord) {
        startPage();
        List<FinancialRecord> list = financialRecordService.selectFinancialRecordList(financialRecord);
        return getDataTable(list);
    }

    /**
     * 导出理财记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:financialRecord:export')")
    @Log(title = "理财记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialRecord financialRecord) {
        List<FinancialRecord> list = financialRecordService.selectFinancialRecordList(financialRecord);
        ExcelUtil<FinancialRecord> util = new ExcelUtil<FinancialRecord>(FinancialRecord.class);
        util.exportExcel(response, list, "理财记录数据");
    }

    /**
     * 获取理财记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:financialRecord:query')")
    @GetMapping(value = "/{financialRecordId}")
    public AjaxResult getInfo(@PathVariable("financialRecordId") Long financialRecordId) {
        return success(financialRecordService.selectFinancialRecordByFinancialRecordId(financialRecordId));
    }

    /**
     * 新增理财记录
     */
    @PreAuthorize("@ss.hasPermi('system:financialRecord:add')")
    @Log(title = "理财记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialRecord financialRecord) throws SchedulerException {
        return toAjax(financialRecordService.insertFinancialRecord(financialRecord));
    }

    /**
     * 修改理财记录
     */
    @PreAuthorize("@ss.hasPermi('system:financialRecord:edit')")
    @Log(title = "理财记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialRecord financialRecord) throws SchedulerException{
        return toAjax(financialRecordService.updateFinancialRecord(financialRecord));
    }

    /**
     * 删除理财记录
     */
    @PreAuthorize("@ss.hasPermi('system:financialRecord:remove')")
    @Log(title = "理财记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{financialRecordIds}")
    public AjaxResult remove(@PathVariable Long[] financialRecordIds) {
        return toAjax(financialRecordService.deleteFinancialRecordByFinancialRecordIds(financialRecordIds));
    }
}
