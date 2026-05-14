package com.ruoyi.weixin.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.weixin.domain.WeiXinImageDTO;
import com.ruoyi.weixin.service.IWeiXinImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 微信公众号图片素材Controller
 *
 * @author www.joolun.com
 * @date 2023-11-27
 */
@RestController
@RequestMapping("/weixin/image")
public class WeiXinImageController extends BaseController {
    @Autowired
    private IWeiXinImageService weixinImageService;

    /**
     * 查询微信公众号图片素材列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:image:list')")
    @GetMapping("/list")
    public TableDataInfo list(WeiXinImageDTO weixinImageDTO) {
        startPage();
        List<WeiXinImageDTO> list = weixinImageService.selectWeixinImageList(weixinImageDTO);
        return getDataTable(list);
    }

    /**
     * 导出微信公众号图片素材列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:image:export')")
    @Log(title = "微信公众号图片素材", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WeiXinImageDTO weixinImageDTO) {
        List<WeiXinImageDTO> list = weixinImageService.selectWeixinImageList(weixinImageDTO);
        ExcelUtil<WeiXinImageDTO> util = new ExcelUtil<WeiXinImageDTO>(WeiXinImageDTO.class);
        return util.exportExcel(list, "image");
    }

    /**
     * 获取微信公众号图片素材详细信息
     */
    @PreAuthorize("@ss.hasPermi('weixin:image:query')")
    @GetMapping(value = "/{imageId}")
    public AjaxResult getInfo(@PathVariable("imageId") Long imageId) {
        return AjaxResult.success(weixinImageService.selectWeixinImageById(imageId));
    }

    /**
     * 新增微信公众号图片素材
     */
    @PreAuthorize("@ss.hasPermi('weixin:image:add')")
    @Log(title = "微信公众号图片素材", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WeiXinImageDTO weixinImageDTO) {
        return toAjax(weixinImageService.insertWeixinImage(weixinImageDTO));
    }

    /**
     * 修改微信公众号图片素材
     */
    @PreAuthorize("@ss.hasPermi('weixin:image:edit')")
    @Log(title = "微信公众号图片素材", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeiXinImageDTO weixinImageDTO) {
        return toAjax(weixinImageService.updateWeixinImage(weixinImageDTO));
    }

    /**
     * 删除微信公众号图片素材
     */
    @PreAuthorize("@ss.hasPermi('weixin:image:remove')")
    @Log(title = "微信公众号图片素材", businessType = BusinessType.DELETE)
    @DeleteMapping("/{imageIds}")
    public AjaxResult remove(@PathVariable Long[] imageIds) {
        return toAjax(weixinImageService.deleteWeixinImageByIds(imageIds));
    }
}
