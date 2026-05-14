package com.ruoyi.weixin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.weixin.service.IArticleTaskService;
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
import com.ruoyi.weixin.domain.ArticleTaskDTO;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章任务Controller
 *
 * @author DXR
 * @date 2024-04-27
 */
@RestController
@RequestMapping("/weixin/task")
public class ArticleTaskController extends BaseController {
    @Autowired
    private IArticleTaskService articleTaskService;

    /**
     * 查询文章任务列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArticleTaskDTO articleTaskDTO) {
//        startPage();
        List<ArticleTaskDTO> list = articleTaskService.selectArticleTaskList(articleTaskDTO);
        return getDataTable(list);
    }

    /**
     * 导出文章任务列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:task:export')")
    @Log(title = "文章任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ArticleTaskDTO articleTaskDTO) {
        List<ArticleTaskDTO> list = articleTaskService.selectArticleTaskList(articleTaskDTO);
        ExcelUtil<ArticleTaskDTO> util = new ExcelUtil<ArticleTaskDTO>(ArticleTaskDTO.class);
        util.exportExcel(response, list, "文章任务数据");
    }

    /**
     * 获取文章任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('weixin:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId) {
        return success(articleTaskService.selectArticleTaskByTaskId(taskId));
    }

    /**
     * 新增文章任务
     */
    @PreAuthorize("@ss.hasPermi('weixin:task:add')")
    @Log(title = "文章任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArticleTaskDTO articleTaskDTO) {
        return toAjax(articleTaskService.insertArticleTask(articleTaskDTO));
    }

    /**
     * 修改文章任务
     */
    @PreAuthorize("@ss.hasPermi('weixin:task:edit')")
    @Log(title = "文章任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArticleTaskDTO articleTaskDTO) {
        return toAjax(articleTaskService.updateArticleTask(articleTaskDTO));
    }

    /**
     * 删除文章任务
     */
    @PreAuthorize("@ss.hasPermi('weixin:task:remove')")
    @Log(title = "文章任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds) {
        return toAjax(articleTaskService.deleteArticleTaskByTaskIds(taskIds));
    }
}
