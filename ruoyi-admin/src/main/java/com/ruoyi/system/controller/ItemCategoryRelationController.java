package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.enums.DelFlagEnum;
import com.ruoyi.common.enums.ResponseStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ItemCategoryRelationDTO;
import com.ruoyi.system.service.IItemCategoryRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物品与分类关联Controller
 *
 * @author ruoyi
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/system/relation")
public class ItemCategoryRelationController extends BaseController {
    @Autowired
    private IItemCategoryRelationService itemCategoryRelationService;

    /**
     * 查询物品与分类关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(ItemCategoryRelationDTO itemCategoryRelationDTO) {
        startPage();
        itemCategoryRelationDTO.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        List<ItemCategoryRelationDTO> list = itemCategoryRelationService.selectItemCategoryRelationList(itemCategoryRelationDTO);
        return getDataTable(list);
    }

    /**
     * 导出物品与分类关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "物品与分类关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ItemCategoryRelationDTO itemCategoryRelationDTO) {
        itemCategoryRelationDTO.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        List<ItemCategoryRelationDTO> list = itemCategoryRelationService.selectItemCategoryRelationList(itemCategoryRelationDTO);
        ExcelUtil<ItemCategoryRelationDTO> util = new ExcelUtil<ItemCategoryRelationDTO>(ItemCategoryRelationDTO.class);
        util.exportExcel(response, list, "物品与分类关联数据");
    }

    /**
     * 获取物品与分类关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{relationId}")
    public AjaxResult getInfo(@PathVariable("relationId") Long relationId) {
        return success(itemCategoryRelationService.selectItemCategoryRelationByRelationId(relationId));
    }

    /**
     * 新增物品与分类关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "物品与分类关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ItemCategoryRelationDTO itemCategoryRelationDTO) {
        return toAjax(itemCategoryRelationService.insertItemCategoryRelation(itemCategoryRelationDTO));
    }

    /**
     * 修改物品与分类关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "物品与分类关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ItemCategoryRelationDTO itemCategoryRelationDTO) {
        return toAjax(itemCategoryRelationService.updateItemCategoryRelation(itemCategoryRelationDTO));
    }

    /**
     * 删除物品与分类关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "物品与分类关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{relationIds}")
    public AjaxResult remove(@PathVariable Long[] relationIds) {
        return toAjax(itemCategoryRelationService.deleteItemCategoryRelationByRelationIds(relationIds));
    }

    /**
     * 通过物品id增加物品与分类绑定，先删除这个物品绑定的所有分类，再根据前端传来的新增
     *
     * @param itemId
     * @return
     */

    @PostMapping("/addCategoryAfterDelete")
    public AjaxResult addCategoryAfterDelete(@RequestParam("itemId") Long itemId, @RequestParam("categoryIdList") List<Long> categoryIdList) {
        if (null == itemId ) {
            return AjaxResult.error(ResponseStatusEnum.RESPONSE_STATUS_201.getCode(), ResponseStatusEnum.RESPONSE_STATUS_201.getInfo());
        }
        itemCategoryRelationService.addCategoryAfterDelete(itemId, categoryIdList);
        return success();
    }
}
