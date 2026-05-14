package com.ruoyi.system.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.io.FileOutputStream;
import java.util.*;

import java.io.File;
import java.io.IOException;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BooleanEnum;
import com.ruoyi.common.enums.DelFlagEnum;
import com.ruoyi.common.enums.UploadPlatformEnum;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.system.domain.ItemCategoryRelationDTO;
import com.ruoyi.system.domain.ItemImageDTO;
import com.ruoyi.system.domain.ItemImageVO;
import com.ruoyi.system.mapper.ItemCategoryRelationMapper;
import com.ruoyi.system.service.IItemCategoryRelationService;
import com.ruoyi.system.service.IItemCategoryService;
import com.ruoyi.system.service.IItemImageService;
import com.ruoyi.util.ImageUtil;
import com.ruoyi.util.TencentCosUtil;
import com.ruoyi.system.mapper.ItemImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ItemInformationMapper;
import com.ruoyi.system.domain.ItemInformationDTO;
import com.ruoyi.system.service.IItemInformationService;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.common.utils.PageUtils.startPage;
import static org.apache.commons.lang3.ArrayUtils.swap;

/**
 * 物品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-05
 */
@Service
public class ItemInformationServiceImpl implements IItemInformationService {
    @Autowired
    private ItemInformationMapper itemInformationMapper;
    @Autowired
    private ItemImageMapper itemImageMapper;
    @Autowired
    private IItemImageService iItemImageService;

    @Autowired
    private ItemCategoryRelationMapper itemCategoryRelationMapper;
    @Autowired
    private ServerConfig serverConfig;

    @Value("${app.temp}")
    private String temp;
    /**
     * 是否开启物理删除
     */
    @Value("${flag.deleteFlag}")
    private boolean deleteFlag;

    /**
     * 查询物品信息，，点击修改按钮的时候触发的查询
     *
     * @param itemId 物品信息主键
     * @return 物品信息
     */
    @Override
    public ItemInformationDTO selectItemInformationByItemId(Long itemId) {
        //应该查询出图片的信息，和图片的id，决定要删除哪张图片
        //select * from item_image;--物品图片表
        return itemInformationMapper.selectItemInformationByItemId(itemId);
    }

    /**
     * 查询物品信息列表
     *
     * @param itemInformationDTO 物品信息
     * @return 物品信息
     */
    @Override
    public List<ItemInformationDTO> selectItemInformationList(ItemInformationDTO itemInformationDTO) {
//        List<ItemInformationDTO> itemInformationDTOList = new ArrayList<>();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        itemInformationDTO.setUserId(userid);
        //查询有效的，非逻辑删除的
        itemInformationDTO.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
        //查询物品信息表拿到物品信息
        List<ItemInformationDTO> itemInformationDTOList = itemInformationMapper.selectItemInformationList(itemInformationDTO);
   /*     if (null != itemInformationDTO.getCategoryId()) {
//            itemInformationDTOList = itemCategoryService.selectItemInformationByItemCategoryId(itemInformationDTO.getCategoryId());
            //1、拿到分类id，判空
            //2、去查分类关系表，即分类id绑定的商品id
            ItemCategoryRelationDTO dto = new ItemCategoryRelationDTO();
            //条件1：当前登录人的
            dto.setUserId(userid);
            //条件2：前端传来的分类id
            dto.setCategoryId(itemInformationDTO.getCategoryId());
            dto.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
            //只能查询当前登录人的
            itemInformationDTO.setUserId(userid);
            //查询有效的，非逻辑删除的
            itemInformationDTO.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
            //查询item_category_relation物品与分类关联表，根据分类id，查出这个id下面所绑定的所有物品的id
//            itemInformationDTOList = itemInformationMapper.selectItemInformation(dto);
            itemInformationDTOList = itemInformationMapper.selectItemInformationList(itemInformationDTO);
        } else {
            // 获取当前的用户信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            //只能查询当前登录人的
            itemInformationDTO.setUserId(userid);
            //查询有效的，非逻辑删除的
            itemInformationDTO.setDelFlag(DelFlagEnum.BOOLEAN_0.getCode());
            //查询物品信息表拿到物品信息
            itemInformationDTOList = itemInformationMapper.selectItemInformationList(itemInformationDTO);
        }*/
        //拿出id，查询物品的图片信息
        for (ItemInformationDTO informationDTO : itemInformationDTOList) {
            Long itemId = informationDTO.getItemId();
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
                try {
                    //生产地址，原图
//                    String pathFileName = serverConfig.getUrl() + "/prod-api" + FileUploadUtils.getPathFileName(RuoYiConfig.getTencentPath() + "/" + userid, itemImageDTO.getObjectKey());

                    //生产地址，压缩图片
                    String pathFileName = serverConfig.getUrl() + "/prod-api" + FileUploadUtils.getPathFileName(RuoYiConfig.getTencentPath() + "/" + userid+temp, itemImageDTO.getObjectKey());
                    //本地测试放开，原图
//                    String pathFileName = serverConfig.getUrl() + FileUploadUtils.getPathFileName(RuoYiConfig.getTencentPath() + "/" + userid, itemImageDTO.getObjectKey());
                    //本地测试放开，压缩图片
//                    String pathFileName = serverConfig.getUrl() + FileUploadUtils.getPathFileName(RuoYiConfig.getTencentPath() + "/" + userid+temp, itemImageDTO.getObjectKey());
                    itemImageVO.setImageUrl(pathFileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //腾讯云cos地址，目前不用，注释
//                itemImageVO.setImageUrl(itemImageDTO.getImageUrl());
                itemImageVO.setCreatedBy(itemImageDTO.getCreatedBy());
                itemImageVO.setCreatedDate(itemImageDTO.getCreatedDate());
                itemImageVOList.add(itemImageVO);
            }

            informationDTO.setItemImageVOList(itemImageVOList);
        }
        return itemInformationDTOList;

    }

    /**
     * 新增物品信息，不带文件
     *
     * @param itemInformationDTO 物品信息
     * @return 结果
     */
    @Override
    public int insertItemInformation(ItemInformationDTO itemInformationDTO) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        // 获取当前的用户信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        itemInformationDTO.setCreatedBy(username);
        itemInformationDTO.setUpdateBy(username);
        itemInformationDTO.setUserId(userid);
        itemInformationDTO.setUserName(username);
        int i = itemInformationMapper.insertItemInformation(itemInformationDTO);
        return i;
    }

    /**
     * 新增物品信息，带文件
     *
     * @param itemInformationDTO 物品信息
     * @return 结果
     */
    @Override
    public int insertItemInformationFiles(ItemInformationDTO itemInformationDTO, MultipartFile[] files) throws IOException {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        // 获取当前的用户信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        itemInformationDTO.setCreatedBy(username);
        itemInformationDTO.setUpdateBy(username);
        itemInformationDTO.setUserId(userid);
        itemInformationDTO.setUserName(username);
        //不为空是否上传图片设置为Y
        if (files != null && files.length > 0) {
            itemInformationDTO.setHasImage(BooleanEnum.BOOLEAN_Y.getCode());
        }

        int i = itemInformationMapper.insertItemInformation(itemInformationDTO);
        Long itemId = itemInformationDTO.getItemId();//拿到主键id
        //调用腾讯云或者阿里云上传图片
        for (MultipartFile file : files) {
            // 将 MultipartFile 转换为 File
            File localFile = convertMultipartFileToFile(file);
            String originalFilename = file.getOriginalFilename();
            //上传腾讯云OS
            Map<String, String> stringStringMap = TencentCosUtil.qcloudUploadFile(localFile, originalFilename);
            String key = stringStringMap.get("key");//拿到对象键
            String url = stringStringMap.get("URL");//拿到图片路径
            //写进图片表
            ItemImageDTO itemImageDTO = new ItemImageDTO();
            itemImageDTO.setItemId(itemId);//物品表主键
            itemImageDTO.setImageName(originalFilename);//文件名
            itemImageDTO.setImageSize(file.getSize());//文件大小
            itemImageDTO.setImageUrl(url);//文件路径
            itemImageDTO.setObjectKey(key);//对象键
            itemImageDTO.setUploadPlatform(UploadPlatformEnum.UPLOAD_OSS.getCode());//上传平台
            itemImageDTO.setCreatedBy(username);//创建人
            itemImageDTO.setCreatedDate(new Date());//创建时间
            itemImageDTO.setUpdatedBy(username);//更新人
            itemImageDTO.setUpdatedDate(new Date());//更新时间
            //插入图片表
            itemImageMapper.insertItemImage(itemImageDTO);
        }

        return i;
    }

    /**
     * 将multipartFile转换成file文件
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        File file = File.createTempFile("temp", fileExtension);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }


    /**
     * 修改物品信息
     *
     * @param itemInformationDTO 物品信息
     * @return 结果
     */
    @Override
    public int updateItemInformation(ItemInformationDTO itemInformationDTO) {
        return itemInformationMapper.updateItemInformation(itemInformationDTO);
    }

    /**
     * 批量删除物品信息
     *
     * @param itemIds 需要删除的物品信息主键
     * @return 结果
     */
    @Override
    public int deleteItemInformationByItemIds(Long[] itemIds) throws IOException{
        if (deleteFlag) {//为了区别是物理删除还是逻辑删除
            //删除物品信息
            itemInformationMapper.deleteItemInformationByItemIds(itemIds);
            //删除关联关系表
            itemCategoryRelationMapper.deleteItemCategoryRelationByitemIds(itemIds);
            //删除图片信息表数据
            // 获取当前用户ID
            Long userid = SecurityUtils.getUserId();
            //查询所有的图片信息
            List<ItemImageDTO> list = iItemImageService.selectBatchByItemId(itemIds);
            //循环删除
            for (ItemImageDTO itemImageDTO : list) {
                String key = itemImageDTO.getObjectKey();
                //删除腾讯云的图片
                TencentCosUtil.qcloudDeleteFile(key);
                //拿到原图路径
                String absPath = FileUploadUtils.getAbsoluteFile(RuoYiConfig.getTencentPath() + "/" + userid, key).getAbsolutePath();
                //拿到压缩图片路径
                String tempPath = FileUploadUtils.getAbsoluteFile(RuoYiConfig.getTencentPath() + "/" + userid+temp, key).getAbsolutePath();
                //删除服务器本地原文件
                FileUtils.deleteFile(absPath);
                //删除服务器本地压缩图片
                FileUtils.deleteFile(tempPath);
            }

            return iItemImageService.deleteItemImageByItemId(itemIds);
        }
        //对关联关系表进行逻辑删除
        itemCategoryRelationMapper.updateItemCategoryRelationByitemIds(itemIds);
        // 对图片的状态进行更改
        iItemImageService.updateItemImageByItemId(itemIds);
        //改成逻辑删除
        return itemInformationMapper.updateItemInformationByItemIds(itemIds);
    }

    /**
     * 删除物品信息信息
     *
     * @param itemId 物品信息主键
     * @return 结果
     */
    @Override
    public int deleteItemInformationByItemId(Long itemId) throws IOException{
        Long[] itemIds = new Long[1];
        itemIds[0] = itemId;


        return deleteItemInformationByItemIds(itemIds);
//        return itemInformationMapper.deleteItemInformationByItemId(itemId);
    }

    /**
     * 上传图片接口
     *
     * @param id
     * @param files
     * @return
     */
    @Override
    public int uploadPicture(Long id, MultipartFile[] files) throws Exception {
        if (null == files || files.length == 0) {
            throw new Exception("文件不可为空");
        }
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        String userid = SecurityUtils.getUserId().toString();
        // 获取当前的用户信息
        ItemInformationDTO itemInformationDTO = new ItemInformationDTO();
        itemInformationDTO.setItemId(id);
        itemInformationDTO.setUpdateBy(username);
        itemInformationDTO.setUserId(Long.valueOf(userid));
        //不为空  是否上传图片设置为Y
        itemInformationDTO.setHasImage(BooleanEnum.BOOLEAN_Y.getCode());
        int i = itemInformationMapper.updateItemInformation(itemInformationDTO);
        //调用腾讯云或者阿里云上传图片
        for (MultipartFile file : files) {
            // 将 MultipartFile 转换为 File
            File localFile = convertMultipartFileToFile(file);
            String originalFilename = file.getOriginalFilename();
            //上传腾讯云OS
            Map<String, String> stringStringMap = TencentCosUtil.qcloudUploadFile(localFile, originalFilename);
            String key = stringStringMap.get("key");//拿到对象键
            String url = stringStringMap.get("URL");//拿到图片路径
            //写进图片表
            ItemImageDTO itemImageDTO = new ItemImageDTO();
            itemImageDTO.setItemId(id);//物品表主键
            itemImageDTO.setImageName(originalFilename);//文件名
            itemImageDTO.setImageSize(file.getSize());//文件大小
            itemImageDTO.setImageUrl(url);//文件路径
            itemImageDTO.setObjectKey(key);//对象键
            itemImageDTO.setUploadPlatform(UploadPlatformEnum.UPLOAD_OSS.getCode());//上传平台
            itemImageDTO.setCreatedBy(userid);//创建人
            itemImageDTO.setCreatedDate(new Date());//创建时间
            itemImageDTO.setUpdatedBy(userid);//更新人
            itemImageDTO.setUpdatedDate(new Date());//更新时间
            //插入图片表
            itemImageMapper.insertItemImage(itemImageDTO);
            //服务器文件存了两份，一份是缩略图，一份是原图
            //获取文件保存路径
            String absPath = FileUploadUtils.getAbsoluteFile(RuoYiConfig.getTencentPath() + "/" + userid, key).getAbsolutePath();
            //获取服务器缩略图保存路径
            String absPathTemp = FileUploadUtils.getAbsoluteFile(RuoYiConfig.getTencentPath() + "/" + userid+temp, key).getAbsolutePath();

            try {
                // 使用 transferTo 方法保存文件,将文件保存到服务器本地
                file.transferTo(Paths.get(absPath));
                System.out.println("文件 " + key + " 已保存至 " + absPath);
            } catch (IOException e) {
                e.printStackTrace();
                // 处理异常，例如记录日志或返回错误信息给调用者
            }
            //文件小于1M不压缩
            if (file.getSize() < 1024 * 1024) {
                org.apache.commons.io.FileUtils.copyFile(new File(absPath), new File(absPathTemp));
            }else {
                //压缩图片并保存在指定路径
                ImageUtil.storeThumbnail(localFile,absPathTemp);
            }
        }

        return i;
    }

    /**
     * 查询不在list列表id里的数据
     *
     * @param itemInformationDto
     * @param list
     * @return
     */
    @Override
    public List<ItemInformationDTO> selectItemInfo(ItemInformationDTO itemInformationDto, List<ItemCategoryRelationDTO> list) {
        return itemInformationMapper.selectItemInfo(itemInformationDto, list);
    }


}
