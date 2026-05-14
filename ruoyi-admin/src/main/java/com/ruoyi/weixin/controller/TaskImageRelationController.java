package com.ruoyi.weixin.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.weixin.domain.TaskImageRelationDTO;
import com.ruoyi.weixin.service.ITaskImageRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务素材顺序关联Controller
 *
 * @author www.joolun.com
 * @date 2023-12-17
 */
@RestController
@RequestMapping("/weixin/relation")
public class TaskImageRelationController extends BaseController {
    @Autowired
    private ITaskImageRelationService taskImageRelationService;

    /**
     * 查询任务素材顺序关联列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskImageRelationDTO taskImageRelationDTO) {
        startPage();
        List<TaskImageRelationDTO> list = taskImageRelationService.selectTaskImageRelationList(taskImageRelationDTO);
        return getDataTable(list);
    }

    /**
     * 导出任务素材顺序关联列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:relation:export')")
    @Log(title = "任务素材顺序关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TaskImageRelationDTO taskImageRelationDTO) {
        List<TaskImageRelationDTO> list = taskImageRelationService.selectTaskImageRelationList(taskImageRelationDTO);
        ExcelUtil<TaskImageRelationDTO> util = new ExcelUtil<TaskImageRelationDTO>(TaskImageRelationDTO.class);
        return util.exportExcel(list, "relation");
    }

    /**
     * 获取任务素材顺序关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('weixin:relation:query')")
    @GetMapping(value = "/{relationId}")
    public AjaxResult getInfo(@PathVariable("relationId") Long relationId) {
        return AjaxResult.success(taskImageRelationService.selectTaskImageRelationById(relationId));
    }

    /**
     * 新增任务素材顺序关联
     */
    @PreAuthorize("@ss.hasPermi('weixin:relation:add')")
    @Log(title = "任务素材顺序关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskImageRelationDTO taskImageRelationDTO) {
        return toAjax(taskImageRelationService.insertTaskImageRelation(taskImageRelationDTO));
    }

    /**
     * 修改任务素材顺序关联
     */
    @PreAuthorize("@ss.hasPermi('weixin:relation:edit')")
    @Log(title = "任务素材顺序关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskImageRelationDTO taskImageRelationDTO) {
        return toAjax(taskImageRelationService.updateTaskImageRelation(taskImageRelationDTO));
    }

    /**
     * 删除任务素材顺序关联
     */
    @PreAuthorize("@ss.hasPermi('weixin:relation:remove')")
    @Log(title = "任务素材顺序关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{relationIds}")
    public AjaxResult remove(@PathVariable Long[] relationIds) {
        return toAjax(taskImageRelationService.deleteTaskImageRelationByIds(relationIds));
    }
}
