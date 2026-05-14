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
import com.ruoyi.system.domain.WorkTimeRecordDTO;
import com.ruoyi.system.service.IWorkTimeRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 记录工作时长Controller
 *
 * @author ruoyi
 * @date 2023-06-25
 */
@RestController
@RequestMapping("/system/record")
public class WorkTimeRecordController extends BaseController {
    @Autowired
    private IWorkTimeRecordService workTimeRecordService;

    /**
     * 查询记录工作时长列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkTimeRecordDTO workTimeRecord) {
        startPage();
        List<WorkTimeRecordDTO> list = workTimeRecordService.selectWorkTimeRecordList(workTimeRecord);
        return getDataTable(list);
    }

    /**
     * 导出记录工作时长列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "记录工作时长", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkTimeRecordDTO workTimeRecord) {
        List<WorkTimeRecordDTO> list = workTimeRecordService.selectWorkTimeRecordList(workTimeRecord);
        ExcelUtil<WorkTimeRecordDTO> util = new ExcelUtil<WorkTimeRecordDTO>(WorkTimeRecordDTO.class);
        util.exportExcel(response, list, "记录工作时长数据");
    }

    /**
     * 获取记录工作时长详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId) {
        return success(workTimeRecordService.selectWorkTimeRecordByRecordId(recordId));
    }

    /**
     * 新增记录工作时长
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "记录工作时长", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkTimeRecordDTO workTimeRecord) {
        return toAjax(workTimeRecordService.insertWorkTimeRecord(workTimeRecord));
    }

    /**
     * 修改记录工作时长
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "记录工作时长", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkTimeRecordDTO workTimeRecord) {
        return toAjax(workTimeRecordService.updateWorkTimeRecord(workTimeRecord));
    }

    /**
     * 删除记录工作时长
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "记录工作时长", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds) {
        return toAjax(workTimeRecordService.deleteWorkTimeRecordByRecordIds(recordIds));
    }
}
