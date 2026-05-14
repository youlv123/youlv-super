package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.ItemCategoryRelationDTO;
import com.ruoyi.system.domain.ItemInformationDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 物品信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-04-05
 */
@Repository
public interface ItemInformationMapper {
    /**
     * 查询物品信息
     *
     * @param itemId 物品信息主键
     * @return 物品信息
     */
    public ItemInformationDTO selectItemInformationByItemId(Long itemId);

    /**
     * 查询物品信息列表
     *
     * @param itemInformationDTO 物品信息
     * @return 物品信息集合
     */
    public List<ItemInformationDTO> selectItemInformationList(ItemInformationDTO itemInformationDTO);

    /**
     * 新增物品信息
     *
     * @param itemInformationDTO 物品信息
     * @return 结果
     */
    public int insertItemInformation(ItemInformationDTO itemInformationDTO);

    /**
     * 修改物品信息
     *
     * @param itemInformationDTO 物品信息
     * @return 结果
     */
    public int updateItemInformation(ItemInformationDTO itemInformationDTO);

    /**
     * 删除物品信息
     *
     * @param itemId 物品信息主键
     * @return 结果
     */
    public int deleteItemInformationByItemId(Long itemId);

    /**
     * 批量删除物品信息
     *
     * @param itemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemInformationByItemIds(Long[] itemIds);

    /**
     * 批量删除物品信息,逻辑删除
     *
     * @param itemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int updateItemInformationByItemIds(Long[] itemIds);

    /**
     * 查询不在list列表id里的数据
     *
     * @param itemInformationDto
     * @param list
     * @return
     */
    List<ItemInformationDTO> selectItemInfo(@Param("itemInformationDto") ItemInformationDTO itemInformationDto, @Param("list") List<ItemCategoryRelationDTO> list);

    List<ItemInformationDTO> selectItemInformation(ItemCategoryRelationDTO itemCategoryRelationDTO);
}
