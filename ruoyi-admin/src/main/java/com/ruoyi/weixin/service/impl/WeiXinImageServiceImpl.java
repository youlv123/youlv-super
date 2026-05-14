package com.ruoyi.weixin.service.impl;

import java.util.List;


import com.ruoyi.weixin.domain.WeiXinImageDTO;
import com.ruoyi.weixin.mapper.WeiXinImageMapper;
import com.ruoyi.weixin.service.IWeiXinImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 微信公众号图片素材Service业务层处理
 *
 * @author www.joolun.com
 * @date 2023-11-27
 */
@Service
public class WeiXinImageServiceImpl implements IWeiXinImageService {
    @Autowired
    private WeiXinImageMapper weixinImageMapper;

    /**
     * 查询微信公众号图片素材
     *
     * @param imageId 微信公众号图片素材ID
     * @return 微信公众号图片素材
     */
    @Override
    public WeiXinImageDTO selectWeixinImageById(Long imageId) {
        return weixinImageMapper.selectWeixinImageById(imageId);
    }

    /**
     * 查询微信公众号图片素材列表
     *
     * @param weixinImageDTO 微信公众号图片素材
     * @return 微信公众号图片素材
     */
    @Override
    public List<WeiXinImageDTO> selectWeixinImageList(WeiXinImageDTO weixinImageDTO) {
        return weixinImageMapper.selectWeixinImageList(weixinImageDTO);
    }

    /**
     * 新增微信公众号图片素材
     *
     * @param weixinImageDTO 微信公众号图片素材
     * @return 结果
     */
    @Override
    public int insertWeixinImage(WeiXinImageDTO weixinImageDTO) {
        return weixinImageMapper.insertWeixinImage(weixinImageDTO);
    }

    /**
     * 修改微信公众号图片素材
     *
     * @param weixinImageDTO 微信公众号图片素材
     * @return 结果
     */
    @Override
    public int updateWeixinImage(WeiXinImageDTO weixinImageDTO) {
        return weixinImageMapper.updateWeixinImage(weixinImageDTO);
    }

    /**
     * 批量删除微信公众号图片素材
     *
     * @param imageIds 需要删除的微信公众号图片素材ID
     * @return 结果
     */
    @Override
    public int deleteWeixinImageByIds(Long[] imageIds) {
        return weixinImageMapper.deleteWeixinImageByIds(imageIds);
    }

    /**
     * 删除微信公众号图片素材信息
     *
     * @param imageId 微信公众号图片素材ID
     * @return 结果
     */
    @Override
    public int deleteWeixinImageById(Long imageId) {
        return weixinImageMapper.deleteWeixinImageById(imageId);
    }


}
