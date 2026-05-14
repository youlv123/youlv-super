package com.ruoyi.weixin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.weixin.service.IWeiXinArticleService;
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
import com.ruoyi.weixin.domain.WeiXinArticleDTO;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 微信公众号文章Controller
 *
 * @author DXR
 * @date 2024-04-28
 */
@RestController
@RequestMapping("/weixin/article")
public class WeiXinArticleController extends BaseController {
    @Autowired
    private IWeiXinArticleService weixinArticleService;

    /**
     * 查询微信公众号文章列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(WeiXinArticleDTO weixinArticleDTO) {
        startPage();
        List<WeiXinArticleDTO> list = weixinArticleService.selectWeixinArticleList(weixinArticleDTO);
        return getDataTable(list);
    }

    /**
     * 导出微信公众号文章列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:article:export')")
    @Log(title = "微信公众号文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WeiXinArticleDTO weixinArticleDTO) {
        List<WeiXinArticleDTO> list = weixinArticleService.selectWeixinArticleList(weixinArticleDTO);
        ExcelUtil<WeiXinArticleDTO> util = new ExcelUtil<WeiXinArticleDTO>(WeiXinArticleDTO.class);
        util.exportExcel(response, list, "微信公众号文章数据");
    }

    /**
     * 获取微信公众号文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('weixin:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId) {
        return success(weixinArticleService.selectWeixinArticleByArticleId(articleId));
    }

    /**
     * 新增微信公众号文章
     */
    @PreAuthorize("@ss.hasPermi('weixin:article:add')")
    @Log(title = "微信公众号文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WeiXinArticleDTO weixinArticleDTO) {
        return toAjax(weixinArticleService.insertWeixinArticle(weixinArticleDTO));
    }

    /**
     * 修改微信公众号文章
     */
    @PreAuthorize("@ss.hasPermi('weixin:article:edit')")
    @Log(title = "微信公众号文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeiXinArticleDTO weixinArticleDTO) {
        return toAjax(weixinArticleService.updateWeixinArticle(weixinArticleDTO));
    }

    /**
     * 删除微信公众号文章
     */
    @PreAuthorize("@ss.hasPermi('weixin:article:remove')")
    @Log(title = "微信公众号文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds) {
        return toAjax(weixinArticleService.deleteWeixinArticleByArticleIds(articleIds));
    }
}
