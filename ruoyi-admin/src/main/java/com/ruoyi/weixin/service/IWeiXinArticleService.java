package com.ruoyi.weixin.service;

import java.util.List;

import com.ruoyi.weixin.domain.WeiXinArticleDTO;

/**
 * 微信公众号文章Service接口
 *
 * @author DXR
 * @date 2024-04-28
 */
public interface IWeiXinArticleService {
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
     * 批量删除微信公众号文章
     *
     * @param articleIds 需要删除的微信公众号文章主键集合
     * @return 结果
     */
    public int deleteWeixinArticleByArticleIds(Long[] articleIds);

    /**
     * 删除微信公众号文章信息
     *
     * @param articleId 微信公众号文章主键
     * @return 结果
     */
    public int deleteWeixinArticleByArticleId(Long articleId);
}
