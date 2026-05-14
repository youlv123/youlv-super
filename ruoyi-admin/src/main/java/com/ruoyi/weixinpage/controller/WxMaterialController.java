/*
MIT License

Copyright (c) 2020 www.joolun.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.ruoyi.weixinpage.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.FileUtils;
import com.ruoyi.weixinpage.domain.ImageManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialFileBatchGetResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信素材
 *
 * @author www.joolun.com
 * @date 2019-03-23 21:26:35
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxmaterial")
public class WxMaterialController extends BaseController {
    private final WxMpService wxService;

    /**
     * 上传非图文微信素材
     * 新增永久素材接口-新增其他类型永久素材  POST，需使用https https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE
     * dxr 2023-11-19
     *
     * @param mulFile
     * @param mediaType
     * @return
     */
    @PostMapping("/materialFileUpload")
    //	@PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:add')")
    public AjaxResult materialFileUpload(@RequestParam("file") MultipartFile mulFile,
                                         @RequestParam("title") String title,
                                         @RequestParam("introduction") String introduction,
                                         @RequestParam("mediaType") String mediaType) {
        try {
            WxMpMaterial material = new WxMpMaterial();
            material.setName(mulFile.getOriginalFilename());
            if (WxConsts.MediaFileType.VIDEO.equals(mediaType)) {
                material.setVideoTitle(title);
                material.setVideoIntroduction(introduction);
            }
            File file = FileUtils.multipartFileToFile(mulFile);
            material.setFile(file);
            WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
            WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpMaterialService.materialFileUpload(mediaType, material);
            WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem wxMpMaterialFileBatchGetResult = new WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem();
            wxMpMaterialFileBatchGetResult.setName(file.getName());
            wxMpMaterialFileBatchGetResult.setMediaId(wxMpMaterialUploadResult.getMediaId());
            wxMpMaterialFileBatchGetResult.setUrl(wxMpMaterialUploadResult.getUrl());
            return AjaxResult.success(wxMpMaterialFileBatchGetResult);
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("上传非图文微信素材失败" + e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败", e);
            return AjaxResult.error(e.getLocalizedMessage());
        }
    }

    /**
     * 上传图文消息内的图片获取URL
     * 没认证的公众号没有权限，http请求方式: POST https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN
     * dxr 2023-11-19
     *
     * @param mulFile
     * @return
     */
    @PostMapping("/newsImgUpload")
    //	@PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:add')")
    public String newsImgUpload(@RequestParam("file") MultipartFile mulFile) throws Exception {
        File file = FileUtils.multipartFileToFile(mulFile);
        WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
        WxMediaImgUploadResult wxMediaImgUploadResult = wxMpMaterialService.mediaImgUpload(file);
        Map<Object, Object> responseData = new HashMap<>();
        responseData.put("link", wxMediaImgUploadResult.getUrl());
        return JSONUtil.toJsonStr(responseData);
    }

    /**
     * 通过id删除微信素材
     * 删除永久素材接口，http请求方式: POST https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN
     * 入参应该是media_id
     * dxr 2023-11-19
     *
     * @param
     * @return R
     */
    @DeleteMapping
    @PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:del')")
    public AjaxResult materialDel(String id) {
        WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
        try {
            return AjaxResult.success(wxMpMaterialService.materialDelete(id));
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("删除微信素材失败", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 分页查询
     * 获取素材列表接口  http请求方式: POST https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN
     * dxr 2023-11-19
     *
     * @param page 分页对象
     * @param type 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news），图文这个类型好像现在没有了
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:index')")
    public AjaxResult getWxMaterialPage(Page page, String type) {
        try {
            WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
            int count = (int) page.getSize();//分页数量，返回素材的数量，取值在1到20之间
            int offset = (int) page.getCurrent() * count - count;//从全部素材的该偏移位置开始返回，0表示从第一个素材 返回，第二页就要写从第10个开始
            if (WxConsts.MaterialType.NEWS.equals(type)) {
                return AjaxResult.success(wxMpMaterialService.materialNewsBatchGet(offset, count));
            } else {
                return AjaxResult.success(wxMpMaterialService.materialFileBatchGet(type, offset, count));
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("查询素材失败", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 分页查询2
     * 也是获取素材列表接口，请求地址一样  http请求方式: POST https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN
     * 这个接口只会返回图片地址和名字，上面的会返回名字、地址、更新时间、mediaId，两个接口入参不一样罢了
     * dxr 2023-11-19
     *
     * @param type
     * @return
     */
    @GetMapping("/page-manager")
//	@PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:index')")
    public String getWxMaterialPageManager(Integer count, Integer offset, String type) throws WxErrorException {
        List<ImageManager> listImageManager = new ArrayList<>();
        WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
        List<WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem> list = wxMpMaterialService.materialFileBatchGet(type, offset, count).getItems();
        list.forEach(wxMaterialFileBatchGetNewsItem -> {
            ImageManager imageManager = new ImageManager();
            imageManager.setName(wxMaterialFileBatchGetNewsItem.getMediaId());
            imageManager.setUrl(wxMaterialFileBatchGetNewsItem.getUrl());
            imageManager.setThumb(wxMaterialFileBatchGetNewsItem.getUrl());
            listImageManager.add(imageManager);
        });
        return JSONUtil.toJsonStr(listImageManager);
    }

    /**
     * 获取微信视频素材
     * 也是获取永久素材的接口，接口地址一样  http请求方式: POST,https协议 https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN
     * dxr 2023-11-19
     *
     * @param
     * @return R
     */
    @GetMapping("/materialVideo")
    @PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:get')")
    public AjaxResult getMaterialVideo(String mediaId) {
        WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
        try {
            return AjaxResult.success(wxMpMaterialService.materialVideoInfo(mediaId));
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("获取微信视频素材失败", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 获取微信素材直接文件
     * <p>
     * 获取永久素材的接口  http请求方式: POST,https协议 https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN
     * dxr 2023-11-19
     *
     * @param
     * @return R
     */
    @GetMapping("/materialOther")
    @PreAuthorize("@ss.hasPermi('wxmp:wxmaterial:get')")
    public ResponseEntity<byte[]> getMaterialOther(String mediaId, String fileName) throws Exception {
        try {
            WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
            //获取文件
            InputStream is = wxMpMaterialService.materialImageOrVoiceDownload(mediaId);
            byte[] body = new byte[is.available()];
            is.read(body);
            HttpHeaders headers = new HttpHeaders();
            //设置文件类型
            headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            headers.add("Content-Type", "application/octet-stream");
            HttpStatus statusCode = HttpStatus.OK;
            //返回数据
            ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
            return entity;
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("获取微信素材直接文件失败", e);
            return null;
        }
    }

    /**
     * 获取微信临时素材直接文件
     * 微信里面获取临时素材的请求接口  https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
     * dxr  2023-11-19
     *
     * @param
     * @return R
     */
    @GetMapping("/tempMaterialOther")
    @PreAuthorize("@ss.hasPermi('wxmp:wxmsg:index')")
    public ResponseEntity<byte[]> getTempMaterialOther(String mediaId, String fileName) throws Exception {
        try {
            WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
            //获取文件
            InputStream is = new FileInputStream(wxMpMaterialService.mediaDownload(mediaId));
            byte[] body = new byte[is.available()];
            is.read(body);
            HttpHeaders headers = new HttpHeaders();
            //设置文件类型
            headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            headers.add("Content-Type", "application/octet-stream");
            HttpStatus statusCode = HttpStatus.OK;
            //返回数据
            ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
            return entity;
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("获取微信素材直接文件失败", e);
            return null;
        }
    }
}
