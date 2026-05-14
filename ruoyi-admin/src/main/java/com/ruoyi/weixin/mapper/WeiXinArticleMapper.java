package com.ruoyi.weixin.mapper;

import java.util.List;

import com.ruoyi.weixin.domain.WeiXinArticleDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 微信公众号文章Mapper接口
 *
 * @author DXR
 * @date 2024-04-28
 */
@Mapper
public interface WeiXinArticleMapper {
    /**
     * 查询微信公众号文章
     *
     * @param articleId 微信公众号文章主键
     * @return 微信公众号文章
     */
    public WeiXinArticleDTO selectWeixinArticleByArticleId(Long articleId);

    /**
     * 查询微信公众号文章列表
     *
     * @param weixinArticleDTO 微信公众号文章
     * @return 微信公众号文章集合
     */
    public List<WeiXinArticleDTO> selectWeixinArticleList(WeiXinArticleDTO weixinArticleDTO);

    /**
     * 新增微信公众号文章
     *
     * @param weixinArticleDTO 微信公众号文章
     * @return 结果
     */
    public int insertWeixinArticle(WeiXinArticleDTO weixinArticleDTO);

    /**
     * 修改微信公众号文章
     *
     * @param weixinArticleDTO 微信公众号文章
     * @return 结果
     */
    public int updateWeixinArticle(WeiXinArticleDTO weixinArticleDTO);

    /**
     * 删除微信公众号文章
     *
     * @param articleId 微信公众号文章主键
     * @return 结果
     */
    public int deleteWeixinArticleByArticleId(Long articleId);

    /**
     * 批量删除微信公众号文章
     *
     * @param articleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWeixinArticleByArticleIds(Long[] articleIds);

    /**
     * 查询待上传草稿箱的微信文章
     *
     * @return
     */
    public List<WeiXinArticleDTO> selectWeixinArticleAll();
}
