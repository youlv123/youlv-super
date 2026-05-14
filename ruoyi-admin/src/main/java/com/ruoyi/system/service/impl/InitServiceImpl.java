package com.ruoyi.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.ItemImageDTO;
import com.ruoyi.system.mapper.InitMapper;
import com.ruoyi.system.mapper.ItemInformationMapper;
import com.ruoyi.system.service.InitService;
import com.ruoyi.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class InitServiceImpl implements InitService {


    @Autowired
    private InitMapper initMapper;

    @Value("${app.temp}")
    private String temp;

    /**
     * 初始化压缩图片
     */
    @Override
    public void compressPicture() {

        Long maxId = null;
        List<ItemImageDTO> oraclePos = this.findNextBatch(maxId);
        log.info("found oraclePos [{}]", oraclePos.size());

        maxId = Long.MIN_VALUE;
        // 获取当前用户ID
        while (CollectionUtils.isNotEmpty(oraclePos)) {
            for (ItemImageDTO itemImageDTO : oraclePos) {
                String key = itemImageDTO.getObjectKey();
                String userid = itemImageDTO.getCreatedBy();
                //获取文件保存路径
                String absPath = FileUploadUtils.getAbsoluteFile(RuoYiConfig.getTencentPath() + "/" + userid, key).getAbsolutePath();
                //获取服务器缩略图保存路径
                String absPathTemp = FileUploadUtils.getAbsoluteFile(RuoYiConfig.getTencentPath() + "/" + userid + temp, key).getAbsolutePath();
                //压缩图片并保存在指定路径
                ImageUtil.storeThumbnailWithImage(absPath, absPathTemp);
                Long id = itemImageDTO.getImageId();
                if (id > maxId) {
                    // find max id
                    maxId = id;
                }
            }
            oraclePos = this.findNextBatch(maxId);
            log.info("found oraclePos [{}]", oraclePos.size());

        }

    }

    private List<ItemImageDTO> findNextBatch(Long maxId) {
        PageHelper.startPage(1, 200);
        List<ItemImageDTO> selecttest = initMapper.selectItem(maxId);
        PageInfo<ItemImageDTO> pageInfo = new PageInfo<>(selecttest);
        return pageInfo.getList();
    }
}
