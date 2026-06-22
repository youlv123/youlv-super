package com.ruoyi.aia.main.java.com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.aia.main.java.com.ruoyi.system.domain.Attachment;
import com.ruoyi.aia.main.java.com.ruoyi.system.service.IAttachmentService;
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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 附件信息Controller
 *
 * @author DXR
 * @date 2026-06-22
 */
@RestController
@RequestMapping("/system/attachment")
public class AttachmentController extends BaseController
{
    @Autowired
    private IAttachmentService attachmentService;

    /**
     * 查询附件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Attachment attachment)
    {
        startPage();
        List<Attachment> list = attachmentService.selectAttachmentList(attachment);
        return getDataTable(list);
    }

    /**
     * 导出附件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:export')")
    @Log(title = "附件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Attachment attachment)
    {
        List<Attachment> list = attachmentService.selectAttachmentList(attachment);
        ExcelUtil<Attachment> util = new ExcelUtil<Attachment>(Attachment.class);
        util.exportExcel(response, list, "附件信息数据");
    }

    /**
     * 获取附件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(attachmentService.selectAttachmentById(id));
    }

    /**
     * 新增附件信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:add')")
    @Log(title = "附件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Attachment attachment)
    {
        return toAjax(attachmentService.insertAttachment(attachment));
    }

    /**
     * 修改附件信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:edit')")
    @Log(title = "附件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Attachment attachment)
    {
        return toAjax(attachmentService.updateAttachment(attachment));
    }

    /**
     * 删除附件信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:remove')")
    @Log(title = "附件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(attachmentService.deleteAttachmentByIds(ids));
    }
}
