package com.ruoyi.weixin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.weixin.domain.TemplatesDTO;
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

import com.ruoyi.weixin.service.ITemplatesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模板Controller
 *
 * @author DXR
 * @date 2024-04-27
 */
@RestController
@RequestMapping("/weixin/templates")
public class TemplatesController extends BaseController {
    @Autowired
    private ITemplatesService templatesService;

    /**
     * 查询模板列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:templates:list')")
    @GetMapping("/list")
    public TableDataInfo list(TemplatesDTO templates) {
        startPage();
        List<TemplatesDTO> list = templatesService.selectTemplatesList(templates);
        return getDataTable(list);
    }

    /**
     * 导出模板列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:templates:export')")
    @Log(title = "模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TemplatesDTO templates) {
        List<TemplatesDTO> list = templatesService.selectTemplatesList(templates);
        ExcelUtil<TemplatesDTO> util = new ExcelUtil<TemplatesDTO>(TemplatesDTO.class);
        util.exportExcel(response, list, "模板数据");
    }

    /**
     * 获取模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('weixin:templates:query')")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") Long templateId) {
        return success(templatesService.selectTemplatesByTemplateId(templateId));
    }

    /**
     * 新增模板
     */
    @PreAuthorize("@ss.hasPermi('weixin:templates:add')")
    @Log(title = "模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TemplatesDTO templates) {
        return toAjax(templatesService.insertTemplates(templates));
    }

    /**
     * 修改模板
     */
    @PreAuthorize("@ss.hasPermi('weixin:templates:edit')")
    @Log(title = "模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TemplatesDTO templates) {
        return toAjax(templatesService.updateTemplates(templates));
    }

    /**
     * 删除模板
     */
    @PreAuthorize("@ss.hasPermi('weixin:templates:remove')")
    @Log(title = "模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable Long[] templateIds) {
        return toAjax(templatesService.deleteTemplatesByTemplateIds(templateIds));
    }
}
