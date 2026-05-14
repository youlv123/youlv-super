package com.ruoyi.weixin.service;



import com.ruoyi.weixin.domain.WeiXinImageDTO;

import java.util.List;


/**
 * 微信公众号图片素材Service接口
 *
 * @author www.joolun.com
 * @date 2023-11-27
 */
public interface IWeiXinImageService {
    /**
     * 查询微信公众号图片素材
     *
     * @param imageId 微信公众号图片素材ID
     * @return 微信公众号图片素材
     */
    public WeiXinImageDTO selectWeixinImageById(Long imageId);

    /**
     * 查询微信公众号图片素材列表
     *
     * @param weixinImageDTO 微信公众号图片素材
     * @return 微信公众号图片素材集合
     */
    public List<WeiXinImageDTO> selectWeixinImageList(WeiXinImageDTO weixinImageDTO);

    /**
     * 新增微信公众号图片素材
     *
     * @param weixinImageDTO 微信公众号图片素材
     * @return 结果
     */
    public int insertWeixinImage(WeiXinImageDTO weixinImageDTO);

    /**
     * 修改微信公众号图片素材
     *
     * @param weixinImageDTO 微信公众号图片素材
     * @return 结果
     */
    public int updateWeixinImage(WeiXinImageDTO weixinImageDTO);

    /**
     * 批量删除微信公众号图片素材
     *
     * @param imageIds 需要删除的微信公众号图片素材ID
     * @return 结果
     */
    public int deleteWeixinImageByIds(Long[] imageIds);

    /**
     * 删除微信公众号图片素材信息
     *
     * @param imageId 微信公众号图片素材ID
     * @return 结果
     */
    public int deleteWeixinImageById(Long imageId);

}
