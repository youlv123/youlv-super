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
import com.ruoyi.system.domain.WxTask;
import com.ruoyi.system.service.IWxTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企微消息推送任务Controller
 *
 * @author DXR
 * @date 2025-06-30
 */
@RestController
@RequestMapping("/system/wxTask")
public class WxTaskController extends BaseController {
    @Autowired
    private IWxTaskService wxTaskService;

    /**
     * 查询企微消息推送任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:wxTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxTask wxTask) {
        startPage();
        List<WxTask> list = wxTaskService.selectWxTaskList(wxTask);
        return getDataTable(list);
    }

    /**
     * 导出企微消息推送任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:wxTask:export')")
    @Log(title = "企微消息推送任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxTask wxTask) {
        List<WxTask> list = wxTaskService.selectWxTaskList(wxTask);
        ExcelUtil<WxTask> util = new ExcelUtil<WxTask>(WxTask.class);
        util.exportExcel(response, list, "企微消息推送任务数据");
    }

    /**
     * 获取企微消息推送任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wxTask:query')")
    @GetMapping(value = "/{wxTaskId}")
    public AjaxResult getInfo(@PathVariable("wxTaskId") Long wxTaskId) {
        return success(wxTaskService.selectWxTaskByWxTaskId(wxTaskId));
    }

    /**
     * 新增企微消息推送任务
     */
    @PreAuthorize("@ss.hasPermi('system:wxTask:add')")
    @Log(title = "企微消息推送任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxTask wxTask) throws SchedulerException {
        return toAjax(wxTaskService.insertWxTask(wxTask));
    }

    /**
     * 修改企微消息推送任务
     */
    @PreAuthorize("@ss.hasPermi('system:wxTask:edit')")
    @Log(title = "企微消息推送任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxTask wxTask) throws SchedulerException{
        return toAjax(wxTaskService.updateWxTask(wxTask));
    }

    /**
     * 删除企微消息推送任务
     */
    @PreAuthorize("@ss.hasPermi('system:wxTask:remove')")
    @Log(title = "企微消息推送任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{wxTaskIds}")
    public AjaxResult remove(@PathVariable Long[] wxTaskIds) {
        return toAjax(wxTaskService.deleteWxTaskByWxTaskIds(wxTaskIds));
    }
}
