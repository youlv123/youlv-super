package com.ruoyi.stock.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.stock.domain.TaskLog;
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
import com.ruoyi.stock.service.ITaskLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务日志Controller
 *
 * @author DXR
 * @date 2025-08-27
 */
@RestController
@RequestMapping("/system/taskLog")
public class TaskLogController extends BaseController {
    @Autowired
    private ITaskLogService taskLogService;

    /**
     * 查询任务日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskLog taskLog) {
        startPage();
        List<TaskLog> list = taskLogService.selectTaskLogList(taskLog);
        return getDataTable(list);
    }

    /**
     * 导出任务日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskLog:export')")
    @Log(title = "任务日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskLog taskLog) {
        List<TaskLog> list = taskLogService.selectTaskLogList(taskLog);
        ExcelUtil<TaskLog> util = new ExcelUtil<TaskLog>(TaskLog.class);
        util.exportExcel(response, list, "任务日志数据");
    }

    /**
     * 获取任务日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskLog:query')")
    @GetMapping(value = "/{taskLogId}")
    public AjaxResult getInfo(@PathVariable("taskLogId") Long taskLogId) {
        return success(taskLogService.selectTaskLogByTaskLogId(taskLogId));
    }

    /**
     * 新增任务日志
     */
    @PreAuthorize("@ss.hasPermi('system:taskLog:add')")
    @Log(title = "任务日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskLog taskLog) {
        return toAjax(taskLogService.insertTaskLog(taskLog));
    }

    /**
     * 修改任务日志
     */
    @PreAuthorize("@ss.hasPermi('system:taskLog:edit')")
    @Log(title = "任务日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskLog taskLog) {
        return toAjax(taskLogService.updateTaskLog(taskLog));
    }

    /**
     * 删除任务日志
     */
    @PreAuthorize("@ss.hasPermi('system:taskLog:remove')")
    @Log(title = "任务日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskLogIds}")
    public AjaxResult remove(@PathVariable Long[] taskLogIds) {
        return toAjax(taskLogService.deleteTaskLogByTaskLogIds(taskLogIds));
    }
}
