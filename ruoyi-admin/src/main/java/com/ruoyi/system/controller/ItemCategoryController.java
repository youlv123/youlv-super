package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.enums.ResponseStatusEnum;
import com.ruoyi.system.domain.ItemInformationDTO;
import com.ruoyi.system.domain.CategoryDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ItemCategoryDTO;
import com.ruoyi.system.service.IItemCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物品分类Controller
 *
 * @author ruoyi
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/system/category")
public class ItemCategoryController extends BaseController {
    @Autowired
    private IItemCategoryService itemCategoryService;

    /**
     * 查询物品分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(ItemCategoryDTO itemCategoryDTO) {
//        startPage();
        List<ItemCategoryDTO> list = itemCategoryService.selectItemCategoryList(itemCategoryDTO);
        return getDataTable(list);
    }

    /**
     * 导出物品分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "物品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ItemCategoryDTO itemCategoryDTO) {
        List<ItemCategoryDTO> list = itemCategoryService.selectItemCategoryList(itemCategoryDTO);
        ExcelUtil<ItemCategoryDTO> util = new ExcelUtil<ItemCategoryDTO>(ItemCategoryDTO.class);
        util.exportExcel(response, list, "物品分类数据");
    }

    /**
     * 获取物品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return success(itemCategoryService.selectItemCategoryByCategoryId(categoryId));
    }

    /**
     * 新增物品分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "物品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ItemCategoryDTO itemCategoryDTO) {
        return toAjax(itemCategoryService.insertItemCategory(itemCategoryDTO));
    }

    /**
     * 修改物品分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "物品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ItemCategoryDTO itemCategoryDTO) {
        return toAjax(itemCategoryService.updateItemCategory(itemCategoryDTO));
    }

    /**
     * 删除物品分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "物品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds) {
        return toAjax(itemCategoryService.deleteItemCategoryByCategoryIds(categoryIds));
    }

    /**
     * 将物品添加进分类
     * 这个其实和下面那个一样，写重复了，暂时都留着吧，目前没用这个
     */
//    @PreAuthorize("@ss.hasPermi('system:category:remove')")
//    @Log(title = "物品分类", businessType = BusinessType.DELETE)
    @GetMapping("/addGoodsToCategory")
    public AjaxResult addGoodsToCategory(@RequestParam("categoryId") Long categoryId, @RequestParam("idList") List<Long> idList) {
        itemCategoryService.addGoodsToCategory(categoryId, idList);
        return success();
    }

    /**
     * 根据id查询这个分类下面所有的商品信息
     *
     * @param categoryId
     * @return
     */

    @PostMapping("/serachIetmByCategoryId")
    public TableDataInfo serachIetmByCategoryId(@RequestParam("categoryId") Long categoryId) {
        startPage();
        List<ItemInformationDTO> list = itemCategoryService.selectItemInformationByItemCategoryId(categoryId);
        return getDataTable(list);
    }

    /**
     * 根据分类id删除这个分类下面所有的商品信息
     *
     * @param categoryId
     * @return
     */

    @PostMapping("/deleteIetmByCategoryId")
    public AjaxResult deleteIetmByCategoryId(@RequestParam("categoryId") Long categoryId, @RequestParam("itemIdList") List<Long> itemIdList) {
        if (null == categoryId || CollectionUtils.isEmpty(itemIdList)) {
            return AjaxResult.error(ResponseStatusEnum.RESPONSE_STATUS_201.getCode(), ResponseStatusEnum.RESPONSE_STATUS_201.getInfo());
        }
        itemCategoryService.deleteItemInformationByItemCategoryId(categoryId, itemIdList);
        return success();
    }

    /**
     * 通过分类id增加商品
     * 这个其实和上面那个一样，写重复了，暂时都留着吧，目前用的是这个
     *
     * @param categoryId
     * @return
     */

    @PostMapping("/addIetmByCategoryId")
    public AjaxResult addIetmByCategoryId(@RequestParam("categoryId") Long categoryId, @RequestParam("itemIdList") List<Long> itemIdList) {
        if (null == categoryId || CollectionUtils.isEmpty(itemIdList)) {
            return AjaxResult.error(ResponseStatusEnum.RESPONSE_STATUS_201.getCode(), ResponseStatusEnum.RESPONSE_STATUS_201.getInfo());
        }
        itemCategoryService.addIetmByCategoryId(categoryId, itemIdList);
        return success();
    }

    /**
     * 新增商品的时候，查询不在这个商品目录下的商品，让用户去挑选需要加入的商品
     * 先做成分页的吧
     *
     * @param categoryId
     * @return
     */
//    @GetMapping("/addSerachIetmByCategoryId")
//    @GetMapping(value = "/{categoryId}")
    @GetMapping("/addSerachIetmByCategoryId/{categoryId}")
    public TableDataInfo addSerachIetmByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<ItemInformationDTO> list = itemCategoryService.addSerachIetmByCategoryId(categoryId);
        return getDataTable(list);
    }

    /**
     * 根据物品id查询这个绑定了哪些分类
     *
     * @param itemId
     * @return
     */
    @GetMapping("/serachCategoryByIetmId/{itemId}")
    public TableDataInfo serachCategoryByIetmId(@PathVariable("itemId") Long itemId) {
        List<CategoryDTO> list = itemCategoryService.serachCategoryByIetmId(itemId);
        return getDataTable(list);
    }



}
