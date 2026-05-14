package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import com.ruoyi.system.domain.QrcodeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IQrcodeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 二维码Controller
 *
 * @author ruoyi
 * @date 2023-05-28
 */
@RestController
@RequestMapping("/system/qrcode")
public class QrcodeController extends BaseController {
    @Autowired
    private IQrcodeService qrcodeService;

    /**
     * 查询二维码列表
     */
    @PreAuthorize("@ss.hasPermi('system:qrcode:list')")
    @GetMapping("/list")
    public TableDataInfo list(QrcodeDTO qrcodeDTO) {
        startPage();
        List<QrcodeDTO> list = qrcodeService.selectQrcodeList(qrcodeDTO);
        return getDataTable(list);
    }

    /**
     * 导出二维码列表
     */
    @PreAuthorize("@ss.hasPermi('system:qrcode:export')")
    @Log(title = "二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QrcodeDTO qrcodeDTO) {
        List<QrcodeDTO> list = qrcodeService.selectQrcodeList(qrcodeDTO);
        ExcelUtil<QrcodeDTO> util = new ExcelUtil<QrcodeDTO>(QrcodeDTO.class);
        util.exportExcel(response, list, "二维码数据");
    }

    /**
     * 获取二维码详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:qrcode:query')")
    @GetMapping(value = "/{qrcodeId}")
    public AjaxResult getInfo(@PathVariable("qrcodeId") Long qrcodeId) {
        return success(qrcodeService.selectQrcodeByQrcodeId(qrcodeId));
    }

    /**
     * 新增二维码
     */
    @PreAuthorize("@ss.hasPermi('system:qrcode:add')")
    @Log(title = "二维码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QrcodeDTO qrcodeDTO) {
        return toAjax(qrcodeService.insertQrcode(qrcodeDTO));
    }

    /**
     * 修改二维码
     */
    @PreAuthorize("@ss.hasPermi('system:qrcode:edit')")
    @Log(title = "二维码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QrcodeDTO qrcodeDTO) {
        return toAjax(qrcodeService.updateQrcode(qrcodeDTO));
    }

    /**
     * 删除二维码
     */
    @PreAuthorize("@ss.hasPermi('system:qrcode:remove')")
    @Log(title = "二维码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{qrcodeIds}")
    public AjaxResult remove(@PathVariable Long[] qrcodeIds) {
        return toAjax(qrcodeService.deleteQrcodeByQrcodeIds(qrcodeIds));
    }

    /**
     * 根据前端传来的数量，批量生成二维码
     *
     * @return
     * @throws IOException
     * @throws WriterException
     */

    //    @PreAuthorize("@ss.hasPermi('system:information:ddd')")
    @PostMapping("/createBatch")
    public ResponseEntity<byte[]> createBatch(@RequestParam("num") int num) throws IOException, WriterException {
        return qrcodeService.createBatch(num);
    }


}
