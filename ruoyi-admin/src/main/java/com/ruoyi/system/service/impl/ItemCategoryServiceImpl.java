package com.ruoyi.system.service.impl;

import java.util.Date;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.enums.DelFlagEnum;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.ItemCategoryRelationMapper;
import com.ruoyi.system.service.IItemCategoryRelationService;
import com.ruoyi.system.service.IItemImageService;
import com.ruoyi.system.service.IItemInformationService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ItemCategoryMapper;
import com.ruoyi.system.service.IItemCategoryService;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 物品分类Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-29
 */
@Service
public class ItemCategoryServiceImpl implements IItemCategoryService {
    @Autowired
    private ItemCategoryMapper itemCategoryMapper;
    @Autowired
    private IItemCategoryRelationService iItemCategoryRelationService;
    @Autowired
    private IItemInformationService itemInformationService;
    @Autowired
    private IItemImageService iItemImageService;
    @Autowired
    private ItemCategoryRelationMapper itemCategoryRelationMapper;

    /**
     * 查询物品分类
     *
     * @param categoryId 物品分类主键
     * @return 物品分类
     */
    @Override
    public ItemCategoryDTO selectItemCategoryByCategoryId(Long categoryId) {
        return itemCategoryMapper.selectItemCategoryByCategoryId(categoryId);
    }

    /**
     * 查询物品分类列表
     *
     * @param itemCategoryDTO 物品分类
     * @return 物品分类
     */
    @Override
    public List<ItemCategoryDTO> selectItemCategoryList(ItemCategoryDTO itemCategoryDTO) {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        itemCategoryDTO.setUserId(userid);
        itemCategoryDTO.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        List<ItemCategoryDTO> itemCategoryDTOS = itemCategoryMapper.selectItemCategoryList(itemCategoryDTO);
        //是否需求查询数量的标志不为空并且不为false并且查询出来的分类不为空则进行分类下面数量查询
        if (null != itemCategoryDTO.getCheckFlag() && itemCategoryDTO.getCheckFlag() && CollectionUtils.isNotEmpty(itemCategoryDTOS)) {
            for (ItemCategoryDTO categoryDTO : itemCategoryDTOS) {
                Long count = iItemCategoryRelationService.selectCountBycategoryId(categoryDTO.getCategoryId());
                categoryDTO.setCount(count);
            }
        }
        return itemCategoryDTOS;
    }

    /**
     * 新增物品分类
     *
     * @param itemCategoryDTO 物品分类
     * @return 结果
     */
    @Override
    public int insertItemCategory(ItemCategoryDTO itemCategoryDTO) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        itemCategoryDTO.setUserId(userid);
        itemCategoryDTO.setUserName(username);
        itemCategoryDTO.setCreatedBy(username);
        itemCategoryDTO.setUpdatedBy(username);
        return itemCategoryMapper.insertItemCategory(itemCategoryDTO);
    }

    /**
     * 修改物品分类
     *
     * @param itemCategoryDTO 物品分类
     * @return 结果
     */
    @Override
    public int updateItemCategory(ItemCategoryDTO itemCategoryDTO) {
        return itemCategoryMapper.updateItemCategory(itemCategoryDTO);
    }

    /**
     * 批量删除物品分类
     *
     * @param categoryIds 需要删除的物品分类主键
     * @return 结果
     */
    @Override
    public int deleteItemCategoryByCategoryIds(Long[] categoryIds) {
        return itemCategoryMapper.deleteItemCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除物品分类信息
     *
     * @param categoryId 物品分类主键
     * @return 结果
     */
    @Override
    public int deleteItemCategoryByCategoryId(Long categoryId) {
        return itemCategoryMapper.deleteItemCategoryByCategoryId(categoryId);
    }

    /**
     * 将物品添加进分类
     *
     * @param categoryId
     * @param idList
     */
    @Override
    public void addGoodsToCategory(Long categoryId, List<Long> idList) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        //新建落入所需要数据的list
        List<ItemCategoryRelationDTO> list = new ArrayList<>();
        for (Long itemId : idList) {
            ItemCategoryRelationDTO dto = new ItemCategoryRelationDTO();
            dto.setItemId(itemId);
            dto.setCategoryId(categoryId);
            dto.setUserId(userid);
            dto.setUserName(username);
            dto.setCreatedBy(username);
            dto.setCreatedDate(new Date());
            dto.setUpdatedBy(username);
            dto.setUpdatedDate(new Date());
            dto.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
            list.add(dto);
        }
        iItemCategoryRelationService.batchInsertItemCategoryRelation(list);
    }

    /**
     * 根据分类id查询这个分类下面所有的商品信息
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<ItemInformationDTO> selectItemInformationByItemCategoryId(Long categoryId) {
        //1、拿到分类id，判空
        if (null == categoryId) {
            return null;
        }
        //2、拿到登录人信息
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        //3、去查分类关系表，即分类id绑定的商品id
        ItemCategoryRelationDTO dto = new ItemCategoryRelationDTO();
        //条件1：当前登录人的
        dto.setUserId(userid);
        //条件2：前端传来的分类id
        dto.setCategoryId(categoryId);
        dto.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        //查询item_category_relation物品与分类关联表，根据分类id，查出这个id下面所绑定的所有物品的id
        List<ItemCategoryRelationDTO> list = iItemCategoryRelationService.selectItemCategoryRelationList(dto);
        //4、拿到商品id，查询商品表，拿到商品信息
        List<ItemInformationDTO> returnList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ItemCategoryRelationDTO itemDto : list) {
                Long itemId = itemDto.getItemId();
                //查询item_information物品信息表，根据id去查询
                ItemInformationDTO itemInformationDTO = itemInformationService.selectItemInformationByItemId(itemId);
                //有可能该物品被逻辑删除了，但是分类关联关系表还没有被删除
                if (null != itemInformationDTO) {
                    //根据物品id查询该物品的图片信息
                    List<ItemImageDTO> itemImageList = iItemImageService.selectItemImageByItemId(itemId);
                    //进行转换返回前端
                    List<ItemImageVO> itemImageVOList = new ArrayList<>();
                    for (ItemImageDTO itemImageDTO : itemImageList) {
                        ItemImageVO itemImageVO = new ItemImageVO();
                        itemImageVO.setImageId(itemImageDTO.getImageId());
                        itemImageVO.setItemId(itemImageDTO.getItemId());
                        itemImageVO.setImageName(itemImageDTO.getImageName());
                        itemImageVO.setImageSize(itemImageDTO.getImageSize());
                        itemImageVO.setImageUrl(itemImageDTO.getImageUrl());
                        itemImageVO.setCreatedBy(itemImageDTO.getCreatedBy());
                        itemImageVO.setCreatedDate(itemImageDTO.getCreatedDate());
                        itemImageVOList.add(itemImageVO);
                    }

                    itemInformationDTO.setItemImageVOList(itemImageVOList);

                    returnList.add(itemInformationDTO);
                }
            }
        }
        //5、返回前端
        return returnList;
    }

    /**
     * 根据id删除这个分类下面所有的商品信息
     *
     * @param categoryId
     * @return
     */
    @Override
    public void deleteItemInformationByItemCategoryId(Long categoryId, List<Long> itemIdList) {
        //1、拿到登录人信息
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        //2、更新分类商品关系表，改状态为逻辑删除
        for (Long id : itemIdList) {
            ItemCategoryRelationDTO dto = new ItemCategoryRelationDTO();
            dto.setUserId(userid);
            dto.setUpdatedBy(username);
            dto.setUpdatedDate(new Date());
            dto.setItemId(id);
            dto.setCategoryId(categoryId);
            //逻辑删除
            dto.setDelFlag(DelFlagEnum.BOOLEAN_1.getCode());
            iItemCategoryRelationService.deleteItemInformationByItemCategoryId(dto);
        }
    }

    /**
     * 通过分类id增加商品
     *
     * @param categoryId
     * @return
     */
    @Override
    public void addIetmByCategoryId(Long categoryId, List<Long> itemIdList) {
        //1、拿到登录人信息
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        //在物品与分类关联表，新建多条数据
        for (Long itemId : itemIdList) {
            ItemCategoryRelationDTO dto = new ItemCategoryRelationDTO();
            dto.setItemId(itemId);
            dto.setCategoryId(categoryId);
            dto.setUserId(userid);
            dto.setUserName(username);
            dto.setCreatedBy(username);
            dto.setCreatedDate(new Date());
            dto.setUpdatedBy(username);
            dto.setUpdatedDate(new Date());
            dto.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
            iItemCategoryRelationService.insertItemCategoryRelation(dto);
        }


    }

    /**
     * 新增商品的时候，查询不在这个商品目录下的商品，让用户去挑选需要加入的商品
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<ItemInformationDTO> addSerachIetmByCategoryId(Long categoryId) {
        //1、拿到登录人信息
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        //2、去查分类关系表，即分类id绑定的商品id
        ItemCategoryRelationDTO dto = new ItemCategoryRelationDTO();
        //条件1：当前登录人的
        dto.setUserId(userid);
        //条件2：前端传来的分类id
        dto.setCategoryId(categoryId);
        dto.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        //查询item_category_relation物品与分类关联表，根据分类id，查出这个id下面所绑定的所有物品的id
        List<ItemCategoryRelationDTO> list = iItemCategoryRelationService.selectItemCategoryRelationList(dto);
        //3、查询item_information物品信息表，id不在已经绑定的id里面的
        ItemInformationDTO itemInformationDto = new ItemInformationDTO();
        itemInformationDto.setUserId(userid);
        itemInformationDto.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        //先做成分页的吧
//        startPage();
        List<ItemInformationDTO> returnList = itemInformationService.selectItemInfo(itemInformationDto, list);

        for (ItemInformationDTO information : returnList) {
            Long itemId = information.getItemId();
            //根据物品id查询该物品的图片信息
            List<ItemImageDTO> itemImageList = iItemImageService.selectItemImageByItemId(itemId);
            //进行转换返回前端
            List<ItemImageVO> itemImageVOList = new ArrayList<>();
            for (ItemImageDTO itemImageDTO : itemImageList) {
                ItemImageVO itemImageVO = new ItemImageVO();
                itemImageVO.setImageId(itemImageDTO.getImageId());
                itemImageVO.setItemId(itemImageDTO.getItemId());
                itemImageVO.setImageName(itemImageDTO.getImageName());
                itemImageVO.setImageSize(itemImageDTO.getImageSize());
                itemImageVO.setImageUrl(itemImageDTO.getImageUrl());
                itemImageVO.setCreatedBy(itemImageDTO.getCreatedBy());
                itemImageVO.setCreatedDate(itemImageDTO.getCreatedDate());
                itemImageVOList.add(itemImageVO);
            }

            information.setItemImageVOList(itemImageVOList);
        }

        return returnList;
    }

    /**
     * 根据物品id查询这个绑定了哪些分类
     *
     * @param itemId
     * @return
     */
    @Override
    public List<CategoryDTO> serachCategoryByIetmId(Long itemId) {

        //1、查询该用户名下有多少个分类，根据用户id和逻辑删除
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
        //条件1：当前登录人的
        itemCategoryDTO.setUserId(userid);
        //条件2：前端传来的物品id
        itemCategoryDTO.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        List<ItemCategoryDTO> categoryList = itemCategoryMapper.selectItemCategoryList(itemCategoryDTO);
        //2、拿物品id去查分类与物品中间表，根据当前用户id，逻辑删除查询
        ItemCategoryRelationDTO dto = new ItemCategoryRelationDTO();
        //条件1：当前登录人的
        dto.setUserId(userid);
        //条件2：前端传来的物品id
        dto.setItemId(itemId);
        //条件3：逻辑删除有效
        dto.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        //查询item_category_relation物品与分类关联表，根据物品id，查出这个id下面所绑定的所有分类的id
        List<ItemCategoryRelationDTO> list = iItemCategoryRelationService.selectItemCategoryRelationList(dto);
        Map<Long, Integer> map = list.stream()
                .collect(Collectors.toMap(ItemCategoryRelationDTO::getCategoryId, e -> 1));

        //3、循环分类list，然后看查出来的绑定的分类flag就设置成ture，否则设置成false
        List<CategoryDTO> returnList = new ArrayList<>();
        for (ItemCategoryDTO cDTO : categoryList) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setItemId(itemId);
            categoryDTO.setCategoryId(cDTO.getCategoryId());
            categoryDTO.setCategoryName(cDTO.getCategoryName());
            if (null != map.get(cDTO.getCategoryId())) {
                categoryDTO.setFlag(true);
            } else {
                categoryDTO.setFlag(false);
            }
            returnList.add(categoryDTO);


        }
        //对象返回前端
        return returnList;
    }




}
