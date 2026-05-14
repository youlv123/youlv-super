package com.ruoyi.system.controller;

import java.io.*;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ItemInformationDTO;
import com.ruoyi.system.service.IItemInformationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物品信息Controller
 *
 * @author ruoyi
 * @date 2023-04-05
 */
@RestController
@RequestMapping("/system/information")
public class ItemInformationController extends BaseController {
    @Autowired
    private IItemInformationService itemInformationService;
    //生成二维码访问前缀
    public static final String url ="";
    /**
     * 查询物品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(ItemInformationDTO itemInformationDTO) {
        startPage();
        List<ItemInformationDTO> list = itemInformationService.selectItemInformationList(itemInformationDTO);
        return getDataTable(list);
    }

    /**
     * 导出物品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:export')")
    @Log(title = "物品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ItemInformationDTO itemInformationDTO) {
        List<ItemInformationDTO> list = itemInformationService.selectItemInformationList(itemInformationDTO);
        ExcelUtil<ItemInformationDTO> util = new ExcelUtil<ItemInformationDTO>(ItemInformationDTO.class);
        util.exportExcel(response, list, "物品信息数据");
    }

    /**
     * 获取物品信息详细信息，点击修改按钮的时候触发的查询
     */
    @PreAuthorize("@ss.hasPermi('system:information:query')")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable("itemId") Long itemId) {
        return success(itemInformationService.selectItemInformationByItemId(itemId));
    }

    /**
     * 新增物品信息，不带文件的
     */
    @PreAuthorize("@ss.hasPermi('system:information:add')")
    @Log(title = "物品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ItemInformationDTO itemInformationDTO) throws IOException {
        return toAjax(itemInformationService.insertItemInformation(itemInformationDTO));
    }

    /**
     * 新增物品信息，带文件的
     */
//    @PreAuthorize("@ss.hasPermi('system:information:add')")
//    @Log(title = "物品信息", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult addFiles(@Valid ItemInformationDTO itemInformationDTO, @RequestParam("files") MultipartFile[] files) throws IOException {
//        return toAjax(itemInformationService.insertItemInformationFiles(itemInformationDTO, files));
//    }

    /**
     * 修改物品信息
     */
    @PreAuthorize("@ss.hasPermi('system:information:edit')")
    @Log(title = "物品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ItemInformationDTO itemInformationDTO) {
        return toAjax(itemInformationService.updateItemInformation(itemInformationDTO));
    }

    /**
     * 删除物品信息
     */
    @PreAuthorize("@ss.hasPermi('system:information:remove')")
    @Log(title = "物品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{itemIds}")
    public AjaxResult remove(@PathVariable Long[] itemIds) throws IOException{
        return toAjax(itemInformationService.deleteItemInformationByItemIds(itemIds));
    }

    /**
     * 上传图片接口
     */
//    @PreAuthorize("@ss.hasPermi('system:information:upload')")
    @Log(title = "上传图片", businessType = BusinessType.OTHER)
    @PostMapping("/upload")
    public AjaxResult handleFileUpload(@RequestParam("id") Long id, @RequestParam("files") MultipartFile[] files)throws Exception {
        return toAjax(itemInformationService.uploadPicture(id, files));
    }

}
