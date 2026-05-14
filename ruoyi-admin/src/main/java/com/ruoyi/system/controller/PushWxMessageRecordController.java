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
import com.ruoyi.system.domain.PushWxMessageRecord;
import com.ruoyi.system.service.IPushWxMessageRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业微信推送消息记录Controller
 *
 * @author DXR
 * @date 2025-06-29
 */
@RestController
@RequestMapping("/system/pushWxMessageRecord")
public class PushWxMessageRecordController extends BaseController {
    @Autowired
    private IPushWxMessageRecordService pushWxMessageRecordService;

    /**
     * 查询企业微信推送消息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:pushWxMessageRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(PushWxMessageRecord pushWxMessageRecord) {
        startPage();
        List<PushWxMessageRecord> list = pushWxMessageRecordService.selectPushWxMessageRecordList(pushWxMessageRecord);
        return getDataTable(list);
    }

    /**
     * 导出企业微信推送消息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:pushWxMessageRecord:export')")
    @Log(title = "企业微信推送消息记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PushWxMessageRecord pushWxMessageRecord) {
        List<PushWxMessageRecord> list = pushWxMessageRecordService.selectPushWxMessageRecordList(pushWxMessageRecord);
        ExcelUtil<PushWxMessageRecord> util = new ExcelUtil<PushWxMessageRecord>(PushWxMessageRecord.class);
        util.exportExcel(response, list, "企业微信推送消息记录数据");
    }

    /**
     * 获取企业微信推送消息记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:pushWxMessageRecord:query')")
    @GetMapping(value = "/{pushId}")
    public AjaxResult getInfo(@PathVariable("pushId") Long pushId) {
        return success(pushWxMessageRecordService.selectPushWxMessageRecordByPushId(pushId));
    }

    /**
     * 新增企业微信推送消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:pushWxMessageRecord:add')")
    @Log(title = "企业微信推送消息记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PushWxMessageRecord pushWxMessageRecord) {
        return toAjax(pushWxMessageRecordService.insertPushWxMessageRecord(pushWxMessageRecord));
    }

    /**
     * 修改企业微信推送消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:pushWxMessageRecord:edit')")
    @Log(title = "企业微信推送消息记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PushWxMessageRecord pushWxMessageRecord) {
        return toAjax(pushWxMessageRecordService.updatePushWxMessageRecord(pushWxMessageRecord));
    }

    /**
     * 删除企业微信推送消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:pushWxMessageRecord:remove')")
    @Log(title = "企业微信推送消息记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pushIds}")
    public AjaxResult remove(@PathVariable Long[] pushIds) {
        return toAjax(pushWxMessageRecordService.deletePushWxMessageRecordByPushIds(pushIds));
    }
}
