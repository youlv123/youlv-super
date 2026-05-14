package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.ItemCategoryDTO;
import org.springframework.stereotype.Repository;

/**
 * 物品分类Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-29
 */
@Repository
public interface ItemCategoryMapper {
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
     * 删除物品分类
     *
     * @param categoryId 物品分类主键
     * @return 结果
     */
    public int deleteItemCategoryByCategoryId(Long categoryId);

    /**
     * 批量删除物品分类
     *
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemCategoryByCategoryIds(Long[] categoryIds);
}
