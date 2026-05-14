package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.ItemImageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 物品图片Mapper接口
 *
 * @author ruoyi
 * @date 2023-04-20
 */
@Repository
public interface ItemImageMapper {
    /**
     * 查询物品图片
     *
     * @param imageId 物品图片主键
     * @return 物品图片
     */
    public ItemImageDTO selectItemImageByImageId(Long imageId);

    /**
     * 查询物品图片列表
     *
     * @param itemImageDTO 物品图片
     * @return 物品图片集合
     */
    public List<ItemImageDTO> selectItemImageList(ItemImageDTO itemImageDTO);

    /**
     * 新增物品图片
     *
     * @param itemImageDTO 物品图片
     * @return 结果
     */
    public int insertItemImage(ItemImageDTO itemImageDTO);

    /**
     * 修改物品图片
     *
     * @param itemImageDTO 物品图片
     * @return 结果
     */
    public int updateItemImage(ItemImageDTO itemImageDTO);

    /**
     * 根据物品id修改物品图片
     *
     * @param itemIds 物品图片
     * @return 结果
     */
    public int updateItemImageByItemId(Long[] itemIds);

    /**
     * 根据物品id删除物品图片
     *
     * @param itemIds 物品图片
     * @return 结果
     */
    public int deleteItemImageByItemId(@Param("itemIds") Long[] itemIds);

    /**
     * 删除物品图片
     *
     * @param imageId 物品图片主键
     * @return 结果
     */
    public int deleteItemImageByImageId(Long imageId);

    /**
     * 批量删除物品图片
     *
     * @param imageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemImageByImageIds(Long[] imageIds);

    /**
     * 查询物品图片根据物品主键
     *
     * @param itemId 物品主键
     * @return 物品图片
     */
    List<ItemImageDTO> selectItemImageByItemId(Long itemId);

    /**
     * 批量查询查询物品图片根据物品主键
     * @param itemIds
     * @return
     */
    List<ItemImageDTO> selectBatchByItemId(@Param("itemIds") Long[] itemIds);
}
