package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.ItemImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ItemImageMapper;
import com.ruoyi.system.service.IItemImageService;

/**
 * 物品图片Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-20
 */
@Service
public class ItemImageServiceImpl implements IItemImageService {
    @Autowired
    private ItemImageMapper itemImageMapper;

    /**
     * 查询物品图片
     *
     * @param imageId 物品图片主键
     * @return 物品图片
     */
    @Override
    public ItemImageDTO selectItemImageByImageId(Long imageId) {
        return itemImageMapper.selectItemImageByImageId(imageId);
    }

    /**
     * 查询物品图片根据物品主键
     *
     * @param itemId 物品主键
     * @return 物品图片
     */
    @Override
    public List<ItemImageDTO> selectItemImageByItemId(Long itemId) {
        return itemImageMapper.selectItemImageByItemId(itemId);
    }

    /**
     * 批量查询查询物品图片根据物品主键
     *
     * @param itemIds 物品主键
     * @return 物品图片
     */
    @Override
    public List<ItemImageDTO> selectBatchByItemId(Long[] itemIds) {
        return itemImageMapper.selectBatchByItemId(itemIds);
    }

    /**
     * 查询物品图片列表
     *
     * @param itemImageDTO 物品图片
     * @return 物品图片
     */
    @Override
    public List<ItemImageDTO> selectItemImageList(ItemImageDTO itemImageDTO) {
        return itemImageMapper.selectItemImageList(itemImageDTO);
    }

    /**
     * 新增物品图片
     *
     * @param itemImageDTO 物品图片
     * @return 结果
     */
    @Override
    public int insertItemImage(ItemImageDTO itemImageDTO) {
        return itemImageMapper.insertItemImage(itemImageDTO);
    }

    /**
     * 修改物品图片
     *
     * @param itemImageDTO 物品图片
     * @return 结果
     */
    @Override
    public int updateItemImage(ItemImageDTO itemImageDTO) {
        return itemImageMapper.updateItemImage(itemImageDTO);
    }

    /**
     * 根据物品id修改物品图片
     *
     * @param itemIds 物品图片
     * @return 结果
     */
    @Override
    public int updateItemImageByItemId(Long[] itemIds) {
        return itemImageMapper.updateItemImageByItemId(itemIds);
    }

    /**
     * 根据物品id删除物品图片
     *
     * @param itemIds 物品图片
     * @return 结果
     */
    @Override
    public int deleteItemImageByItemId(Long[] itemIds) {
        return itemImageMapper.deleteItemImageByItemId(itemIds);
    }

    /**
     * 批量删除物品图片
     *
     * @param imageIds 需要删除的物品图片主键
     * @return 结果
     */
    @Override
    public int deleteItemImageByImageIds(Long[] imageIds) {
        return itemImageMapper.deleteItemImageByImageIds(imageIds);
    }

    /**
     * 删除物品图片信息
     *
     * @param imageId 物品图片主键
     * @return 结果
     */
    @Override
    public int deleteItemImageByImageId(Long imageId) {
        return itemImageMapper.deleteItemImageByImageId(imageId);
    }
}
