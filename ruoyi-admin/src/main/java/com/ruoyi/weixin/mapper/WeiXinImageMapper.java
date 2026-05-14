package com.ruoyi.weixin.mapper;



import com.ruoyi.weixin.domain.WeiXinImageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 微信公众号图片素材Mapper接口
 *
 * @author www.joolun.com
 * @date 2023-11-27
 */
@Repository
public interface WeiXinImageMapper {
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
     * 删除微信公众号图片素材
     *
     * @param imageId 微信公众号图片素材ID
     * @return 结果
     */
    public int deleteWeixinImageById(Long imageId);

    /**
     * 批量删除微信公众号图片素材
     *
     * @param imageIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinImageByIds(Long[] imageIds);

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<WeiXinImageDTO> selectWeixinAll();

    /**
     * 查询图片数据根据时间和id
     *
     * @return
     */
    public List<WeiXinImageDTO> selectByUpdateTime(@Param("updatedDate") Date updatedDate, @Param("imageId") Long imageId);
}
