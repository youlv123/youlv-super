package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.ItemCategoryRelationDTO;

/**
 * 物品与分类关联Service接口
 *
 * @author ruoyi
 * @date 2023-05-29
 */
public interface IItemCategoryRelationService {
    /**
     * 查询物品与分类关联
     *
     * @param relationId 物品与分类关联主键
     * @return 物品与分类关联
     */
    public ItemCategoryRelationDTO selectItemCategoryRelationByRelationId(Long relationId);

    /**
     * 查询物品与分类关联列表
     *
     * @param itemCategoryRelationDTO 物品与分类关联
     * @return 物品与分类关联集合
     */
    public List<ItemCategoryRelationDTO> selectItemCategoryRelationList(ItemCategoryRelationDTO itemCategoryRelationDTO);

    /**
     * 新增物品与分类关联
     *
     * @param itemCategoryRelationDTO 物品与分类关联
     * @return 结果
     */
    public int insertItemCategoryRelation(ItemCategoryRelationDTO itemCategoryRelationDTO);

    /**
     * 修改物品与分类关联
     *
     * @param itemCategoryRelationDTO 物品与分类关联
     * @return 结果
     */
    public int updateItemCategoryRelation(ItemCategoryRelationDTO itemCategoryRelationDTO);

    /**
     * 批量删除物品与分类关联
     *
     * @param relationIds 需要删除的物品与分类关联主键集合
     * @return 结果
     */
    public int deleteItemCategoryRelationByRelationIds(Long[] relationIds);

    /**
     * 删除物品与分类关联信息
     *
     * @param relationId 物品与分类关联主键
     * @return 结果
     */
    public int deleteItemCategoryRelationByRelationId(Long relationId);

    /**
     * 批量关联物品与分类
     *
     * @param list
     * @return
     */
    public int batchInsertItemCategoryRelation(List<ItemCategoryRelationDTO> list);

    /**
     * 根据id删除这个分类下面所有的商品信息
     *
     * @param dto
     * @return
     */
    void deleteItemInformationByItemCategoryId(ItemCategoryRelationDTO dto);

    /**
     * 根据分类id查询分类下面有多少个物品
     *
     * @param categoryId
     * @return
     */
    Long selectCountBycategoryId(Long categoryId);

    /**
     * 通过物品id增加物品与分类绑定，先删除这个物品绑定的所有分类，再根据前端传来的新增
     *
     * @param itemId
     * @return
     */
    void addCategoryAfterDelete(Long itemId, List<Long> categoryIdList);
}
