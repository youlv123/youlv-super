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
import com.ruoyi.system.domain.QrcodePermissionDTO;
import com.ruoyi.system.service.IQrcodePermissionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 二维码查看权限Controller
 *
 * @author ruoyi
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/system/permission")
public class QrcodePermissionController extends BaseController {
    @Autowired
    private IQrcodePermissionService qrcodePermissionService;

    /**
     * 查询二维码查看权限列表
     */
    @PreAuthorize("@ss.hasPermi('system:permission:list')")
    @GetMapping("/list")
    public TableDataInfo list(QrcodePermissionDTO qrcodePermissionDTO) {
        startPage();
        List<QrcodePermissionDTO> list = qrcodePermissionService.selectQrcodePermissionList(qrcodePermissionDTO);
        return getDataTable(list);
    }

    /**
     * 导出二维码查看权限列表
     */
    @PreAuthorize("@ss.hasPermi('system:permission:export')")
    @Log(title = "二维码查看权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QrcodePermissionDTO qrcodePermissionDTO) {
        List<QrcodePermissionDTO> list = qrcodePermissionService.selectQrcodePermissionList(qrcodePermissionDTO);
        ExcelUtil<QrcodePermissionDTO> util = new ExcelUtil<QrcodePermissionDTO>(QrcodePermissionDTO.class);
        util.exportExcel(response, list, "二维码查看权限数据");
    }

    /**
     * 获取二维码查看权限详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:permission:query')")
    @GetMapping(value = "/{permissionId}")
    public AjaxResult getInfo(@PathVariable("permissionId") Long permissionId) {
        return success(qrcodePermissionService.selectQrcodePermissionByPermissionId(permissionId));
    }

    /**
     * 新增二维码查看权限
     */
    @PreAuthorize("@ss.hasPermi('system:permission:add')")
    @Log(title = "二维码查看权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QrcodePermissionDTO qrcodePermissionDTO) {
        return toAjax(qrcodePermissionService.insertQrcodePermission(qrcodePermissionDTO));
    }

    /**
     * 修改二维码查看权限
     */
    @PreAuthorize("@ss.hasPermi('system:permission:edit')")
    @Log(title = "二维码查看权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QrcodePermissionDTO qrcodePermissionDTO) {
        return toAjax(qrcodePermissionService.updateQrcodePermission(qrcodePermissionDTO));
    }

    /**
     * 删除二维码查看权限
     */
    @PreAuthorize("@ss.hasPermi('system:permission:remove')")
    @Log(title = "二维码查看权限", businessType = BusinessType.DELETE)
    @DeleteMapping("/{permissionIds}")
    public AjaxResult remove(@PathVariable Long[] permissionIds) {
        return toAjax(qrcodePermissionService.deleteQrcodePermissionByPermissionIds(permissionIds));
    }
}
