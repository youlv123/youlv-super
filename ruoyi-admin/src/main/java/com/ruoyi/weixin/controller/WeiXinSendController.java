package com.ruoyi.weixin.controller;

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
import com.ruoyi.weixin.domain.WeiXinSendDTO;
import com.ruoyi.weixin.service.IWeiXinSendService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 微信公众号文章发送Controller
 *
 * @author DXR
 * @date 2024-04-27
 */
@RestController
@RequestMapping("/weixin/send")
public class WeiXinSendController extends BaseController {
    @Autowired
    private IWeiXinSendService weixinSendService;

    /**
     * 查询微信公众号文章发送列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:send:list')")
    @GetMapping("/list")
    public TableDataInfo list(WeiXinSendDTO weixinSendDTO) {
        startPage();
        List<WeiXinSendDTO> list = weixinSendService.selectWeixinSendList(weixinSendDTO);
        return getDataTable(list);
    }

    /**
     * 导出微信公众号文章发送列表
     */
    @PreAuthorize("@ss.hasPermi('weixin:send:export')")
    @Log(title = "微信公众号文章发送", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WeiXinSendDTO weixinSendDTO) {
        List<WeiXinSendDTO> list = weixinSendService.selectWeixinSendList(weixinSendDTO);
        ExcelUtil<WeiXinSendDTO> util = new ExcelUtil<WeiXinSendDTO>(WeiXinSendDTO.class);
        util.exportExcel(response, list, "微信公众号文章发送数据");
    }

    /**
     * 获取微信公众号文章发送详细信息
     */
    @PreAuthorize("@ss.hasPermi('weixin:send:query')")
    @GetMapping(value = "/{sendId}")
    public AjaxResult getInfo(@PathVariable("sendId") Long sendId) {
        return success(weixinSendService.selectWeixinSendBySendId(sendId));
    }

    /**
     * 新增微信公众号文章发送
     */
    @PreAuthorize("@ss.hasPermi('weixin:send:add')")
    @Log(title = "微信公众号文章发送", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WeiXinSendDTO weixinSendDTO) {
        return toAjax(weixinSendService.insertWeixinSend(weixinSendDTO));
    }

    /**
     * 修改微信公众号文章发送
     */
    @PreAuthorize("@ss.hasPermi('weixin:send:edit')")
    @Log(title = "微信公众号文章发送", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeiXinSendDTO weixinSendDTO) {
        return toAjax(weixinSendService.updateWeixinSend(weixinSendDTO));
    }

    /**
     * 删除微信公众号文章发送
     */
    @PreAuthorize("@ss.hasPermi('weixin:send:remove')")
    @Log(title = "微信公众号文章发送", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sendIds}")
    public AjaxResult remove(@PathVariable Long[] sendIds) {
        return toAjax(weixinSendService.deleteWeixinSendBySendIds(sendIds));
    }
}
