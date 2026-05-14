package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.ItemCategoryDTO;
import com.ruoyi.system.domain.ItemInformationDTO;
import com.ruoyi.system.domain.CategoryDTO;

/**
 * 物品分类Service接口
 *
 * @author ruoyi
 * @date 2023-05-29
 */
public interface IItemCategoryService {
    /**
     * 查询物品分类
     *
     * @param categoryId 物品分类主键
     * @return 物品分类
     */
    public ItemCategoryDTO selectItemCategoryByCategoryId(Long categoryId);

    /**
     * 查询物品分类列表
     *
     * @param itemCategoryDTO 物品分类
     * @return 物品分类集合
     */
    public List<ItemCategoryDTO> selectItemCategoryList(ItemCategoryDTO itemCategoryDTO);

    /**
     * 新增物品分类
     *
     * @param itemCategoryDTO 物品分类
     * @return 结果
     */
    public int insertItemCategory(ItemCategoryDTO itemCategoryDTO);

    /**
     * 修改物品分类
     *
     * @param itemCategoryDTO 物品分类
     * @return 结果
     */
    public int updateItemCategory(ItemCategoryDTO itemCategoryDTO);

    /**
     * 批量删除物品分类
     *
     * @param categoryIds 需要删除的物品分类主键集合
     * @return 结果
     */
    public int deleteItemCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除物品分类信息
     *
     * @param categoryId 物品分类主键
     * @return 结果
     */
    public int deleteItemCategoryByCategoryId(Long categoryId);

    /**
     * 将物品添加进分类
     */
    void addGoodsToCategory(Long categoryId, List<Long> idList);

    /**
     * 根据id查询这个分类下面所有的商品信息
     *
     * @param categoryId
     * @return
     */
    List<ItemInformationDTO> selectItemInformationByItemCategoryId(Long categoryId);

    /**
     * 根据id删除这个分类下面所有的商品信息
     *
     * @param categoryId
     * @return
     */
    void deleteItemInformationByItemCategoryId(Long categoryId, List<Long> itemIdList);

    /**
     * 通过分类id增加商品
     *
     * @param categoryId
     * @return
     */
    void addIetmByCategoryId(Long categoryId, List<Long> itemIdList);

    /**
     * 新增商品的时候，查询不在这个商品目录下的商品，让用户去挑选需要加入的商品
     *
     * @param categoryId
     * @return
     */
    List<ItemInformationDTO> addSerachIetmByCategoryId(Long categoryId);

    /**
     * 根据物品id查询这个绑定了哪些分类
     *
     * @param itemId
     * @return
     */
    List<CategoryDTO> serachCategoryByIetmId(Long itemId);


}
