package com.ruoyi.system.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.system.domain.ItemCategoryRelationDTO;
import com.ruoyi.system.domain.ItemInformationDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物品信息Service接口
 *
 * @author ruoyi
 * @date 2023-04-05
 */
public interface IItemInformationService {
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
    public int insertItemInformation(ItemInformationDTO itemInformationDTO) throws IOException;
    /**
     * 新增物品信息，带文件的
     */
    public int insertItemInformationFiles(ItemInformationDTO itemInformationDTO, MultipartFile[] files) throws IOException;

    /**
     * 修改物品信息
     *
     * @param itemInformationDTO 物品信息
     * @return 结果
     */
    public int updateItemInformation(ItemInformationDTO itemInformationDTO);

    /**
     * 批量删除物品信息
     *
     * @param itemIds 需要删除的物品信息主键集合
     * @return 结果
     */
    public int deleteItemInformationByItemIds(Long[] itemIds) throws IOException;

    /**
     * 删除物品信息信息
     *
     * @param itemId 物品信息主键
     * @return 结果
     */
    public int deleteItemInformationByItemId(Long itemId) throws IOException;

    /**
     * 上传图片接口
     *
     * @param id
     * @param files
     * @return
     */
    int uploadPicture(Long id, MultipartFile[] files) throws Exception;

    /**
     * 查询不在list列表id里的数据
     *
     * @param itemInformationDto
     * @param list
     * @return
     */
    List<ItemInformationDTO> selectItemInfo(ItemInformationDTO itemInformationDto, List<ItemCategoryRelationDTO> list);
}
