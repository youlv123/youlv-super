package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.ItemCategoryRelationDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 物品与分类关联Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-29
 */
@Repository
public interface ItemCategoryRelationMapper {
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
     * 删除物品与分类关联
     *
     * @param relationId 物品与分类关联主键
     * @return 结果
     */
    public int deleteItemCategoryRelationByRelationId(Long relationId);

    /**
     * 批量删除物品与分类关联
     *
     * @param relationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemCategoryRelationByRelationIds(Long[] relationIds);
    /**
     * 当物品被删除时，这里也要同步关联关系逻辑删除
     *
     * @param itemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int updateItemCategoryRelationByitemIds(Long[] itemIds);

    /**
     * 当物品被删除时，这里也要同步关联关系物理删除
     *
     * @param itemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemCategoryRelationByitemIds(Long[] itemIds);

    /**
     * 批量关联物品与分类
     *
     * @param list
     * @return
     */
    public int batchInsertItemCategoryRelation(@Param("list") List<ItemCategoryRelationDTO> list);

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
    public Long selectCountBycategoryId(Long categoryId);
}
