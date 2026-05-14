package com.ruoyi.weixin.service.impl;


import com.ruoyi.weixin.domain.WeiXinImageDTO;
import com.ruoyi.weixin.mapper.WeiXinImageMapper;
import com.ruoyi.weixin.service.ImageAutoHandle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * 1、图片自动审核处理类
 */
@Slf4j
@AllArgsConstructor
@Service
public class ImageAutoHandleImpl implements ImageAutoHandle {


    @Autowired
    private WeiXinImageMapper weixinImageMapper;

    private final WxMpService wxService;

    @Override
    public int autoHandle() {
        //1、拿到逻辑删除为0正常，以及自动审核标识为0的图片数据

        //2、将图片提交阿里云或者腾讯云进行审核
        //3、审核通过自动审核标识改为1自动审核通过
        //4、不通过的数据改为2，自动审核不通过，同时将人工审核状态改成1，需要人工审核
        //5、

        //1、拿到所有weixin_image表的图片，自动审核标识为1已通过，人工审核标识为2审核通过的，逻辑删除为0正常的未上传微信素材库的图片
        List<WeiXinImageDTO> orgrilist = weixinImageMapper.selectWeixinAll();
        for (WeiXinImageDTO weixinImageDTO : orgrilist) {
            //从本地拿到文件
            File file = new File(weixinImageDTO.getImageUrl());
            if (!file.exists()) {
                System.out.println("文件不存在");
                continue;
            }
            //上传测试类型是图片
            String mediaType = "image";
            //2、图片上传到微信公众平台拿到url和media_id,存入数据库

            try {
                WxMpMaterial material = new WxMpMaterial();
                //文件名
                material.setName(weixinImageDTO.getImageName());

                material.setFile(file);
                //调用微信上传文件
                WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
                WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpMaterialService.materialFileUpload(mediaType, material);
                //新增的永久素材的media_id
                weixinImageDTO.setMediaId(wxMpMaterialUploadResult.getMediaId());
                //其中url就是上传图片的URL，可放置图文消息中使用。
                weixinImageDTO.setWeixinUrl(wxMpMaterialUploadResult.getUrl());
                //已上传
                weixinImageDTO.setWeixinFlag("1");
                //更新weixin_image表
                weixinImageMapper.updateWeixinImage(weixinImageDTO);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("上传非图文微信素材失败" + e);
            }
        }
        return 0;
    }
}
