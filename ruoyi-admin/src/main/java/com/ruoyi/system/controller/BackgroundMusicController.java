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
import com.ruoyi.system.domain.BackgroundMusicDTO;
import com.ruoyi.system.service.IBackgroundMusicService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 背景音乐Controller
 *
 * @author ruoyi
 * @date 2024-02-06
 */
@RestController
@RequestMapping("/system/music")
public class BackgroundMusicController extends BaseController {
    @Autowired
    private IBackgroundMusicService backgroundMusicService;

    /**
     * 查询背景音乐列表
     */
    @PreAuthorize("@ss.hasPermi('system:music:list')")
    @GetMapping("/list")
    public TableDataInfo list(BackgroundMusicDTO backgroundMusicDTO) {
        startPage();
        List<BackgroundMusicDTO> list = backgroundMusicService.selectBackgroundMusicList(backgroundMusicDTO);
        return getDataTable(list);
    }

    /**
     * 导出背景音乐列表
     */
    @PreAuthorize("@ss.hasPermi('system:music:export')")
    @Log(title = "背景音乐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BackgroundMusicDTO backgroundMusicDTO) {
        List<BackgroundMusicDTO> list = backgroundMusicService.selectBackgroundMusicList(backgroundMusicDTO);
        ExcelUtil<BackgroundMusicDTO> util = new ExcelUtil<BackgroundMusicDTO>(BackgroundMusicDTO.class);
        util.exportExcel(response, list, "背景音乐数据");
    }

    /**
     * 获取背景音乐详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:music:query')")
    @GetMapping(value = "/{musicId}")
    public AjaxResult getInfo(@PathVariable("musicId") Long musicId) {
        return success(backgroundMusicService.selectBackgroundMusicByMusicId(musicId));
    }

    /**
     * 新增背景音乐
     */
    @PreAuthorize("@ss.hasPermi('system:music:add')")
    @Log(title = "背景音乐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BackgroundMusicDTO backgroundMusicDTO) {
        return toAjax(backgroundMusicService.insertBackgroundMusic(backgroundMusicDTO));
    }

    /**
     * 修改背景音乐
     */
    @PreAuthorize("@ss.hasPermi('system:music:edit')")
    @Log(title = "背景音乐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BackgroundMusicDTO backgroundMusicDTO) {
        return toAjax(backgroundMusicService.updateBackgroundMusic(backgroundMusicDTO));
    }

    /**
     * 删除背景音乐
     */
    @PreAuthorize("@ss.hasPermi('system:music:remove')")
    @Log(title = "背景音乐", businessType = BusinessType.DELETE)
    @DeleteMapping("/{musicIds}")
    public AjaxResult remove(@PathVariable Long[] musicIds) {
        return toAjax(backgroundMusicService.deleteBackgroundMusicByMusicIds(musicIds));
    }
}
