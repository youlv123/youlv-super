package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.enums.DelFlagEnum;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ItemCategoryRelationMapper;
import com.ruoyi.system.domain.ItemCategoryRelationDTO;
import com.ruoyi.system.service.IItemCategoryRelationService;

/**
 * 物品与分类关联Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-29
 */
@Service
public class ItemCategoryRelationServiceImpl implements IItemCategoryRelationService {
    @Autowired
    private ItemCategoryRelationMapper itemCategoryRelationMapper;

    /**
     * 查询物品与分类关联
     *
     * @param relationId 物品与分类关联主键
     * @return 物品与分类关联
     */
    @Override
    public ItemCategoryRelationDTO selectItemCategoryRelationByRelationId(Long relationId) {
        return itemCategoryRelationMapper.selectItemCategoryRelationByRelationId(relationId);
    }

    /**
     * 查询物品与分类关联列表
     *
     * @param itemCategoryRelationDTO 物品与分类关联
     * @return 物品与分类关联
     */
    @Override
    public List<ItemCategoryRelationDTO> selectItemCategoryRelationList(ItemCategoryRelationDTO itemCategoryRelationDTO) {
        return itemCategoryRelationMapper.selectItemCategoryRelationList(itemCategoryRelationDTO);
    }

    /**
     * 新增物品与分类关联
     *
     * @param itemCategoryRelationDTO 物品与分类关联
     * @return 结果
     */
    @Override
    public int insertItemCategoryRelation(ItemCategoryRelationDTO itemCategoryRelationDTO) {
        return itemCategoryRelationMapper.insertItemCategoryRelation(itemCategoryRelationDTO);
    }

    /**
     * 修改物品与分类关联
     *
     * @param itemCategoryRelationDTO 物品与分类关联
     * @return 结果
     */
    @Override
    public int updateItemCategoryRelation(ItemCategoryRelationDTO itemCategoryRelationDTO) {
        return itemCategoryRelationMapper.updateItemCategoryRelation(itemCategoryRelationDTO);
    }

    /**
     * 批量删除物品与分类关联
     *
     * @param relationIds 需要删除的物品与分类关联主键
     * @return 结果
     */
    @Override
    public int deleteItemCategoryRelationByRelationIds(Long[] relationIds) {
        return itemCategoryRelationMapper.deleteItemCategoryRelationByRelationIds(relationIds);
    }

    /**
     * 删除物品与分类关联信息
     *
     * @param relationId 物品与分类关联主键
     * @return 结果
     */
    @Override
    public int deleteItemCategoryRelationByRelationId(Long relationId) {
        return itemCategoryRelationMapper.deleteItemCategoryRelationByRelationId(relationId);
    }

    /**
     * 批量关联物品与分类
     *
     * @param list
     * @return
     */
    @Override
    public int batchInsertItemCategoryRelation(List<ItemCategoryRelationDTO> list) {
        return itemCategoryRelationMapper.batchInsertItemCategoryRelation(list);
    }

    /**
     * 根据id删除这个分类下面所有的商品信息
     *
     * @param dto
     * @return
     */
    @Override
    public void deleteItemInformationByItemCategoryId(ItemCategoryRelationDTO dto) {
        itemCategoryRelationMapper.deleteItemInformationByItemCategoryId(dto);
    }

    /**
     * 根据分类id查询分类下面有多少个物品
     *
     * @param categoryId
     * @return
     */
    @Override
    public Long selectCountBycategoryId(Long categoryId) {
        return itemCategoryRelationMapper.selectCountBycategoryId(categoryId);
    }

    /**
     * 通过物品id增加物品与分类绑定，先删除这个物品绑定的所有分类，再根据前端传来的新增
     *
     * @param itemId
     * @return
     */
    @Override
    public void addCategoryAfterDelete(Long itemId, List<Long> categoryIdList) {
        //1、删除这个物品绑定的所有分类，先做成逻辑删除吧
        ItemCategoryRelationDTO dto = new ItemCategoryRelationDTO();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        dto.setUserId(userid);
        dto.setUpdatedBy(username);
        dto.setUpdatedDate(new Date());
        dto.setItemId(itemId);
        //逻辑删除
        dto.setDelFlag(DelFlagEnum.BOOLEAN_1.getCode());
        itemCategoryRelationMapper.deleteItemInformationByItemCategoryId(dto);
        //2、再根据前端传来的新增
        ArrayList<ItemCategoryRelationDTO> list = new ArrayList<>();
        for (Long categoryId : categoryIdList) {
            ItemCategoryRelationDTO itemCategoryRelationDTO = new ItemCategoryRelationDTO();
            itemCategoryRelationDTO.setItemId(itemId);
            itemCategoryRelationDTO.setCategoryId(categoryId);
            itemCategoryRelationDTO.setUserId(userid);
            itemCategoryRelationDTO.setUserName(username);
            itemCategoryRelationDTO.setCreatedBy(username);
            itemCategoryRelationDTO.setCreatedDate(new Date());
            itemCategoryRelationDTO.setUpdatedBy(username);
            itemCategoryRelationDTO.setUpdatedDate(new Date());
            itemCategoryRelationDTO.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
            list.add(itemCategoryRelationDTO);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            itemCategoryRelationMapper.batchInsertItemCategoryRelation(list);
        }
    }
}
