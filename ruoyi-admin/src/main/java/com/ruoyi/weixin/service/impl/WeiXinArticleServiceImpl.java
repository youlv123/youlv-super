package com.ruoyi.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weixin.mapper.WeiXinArticleMapper;
import com.ruoyi.weixin.domain.WeiXinArticleDTO;
import com.ruoyi.weixin.service.IWeiXinArticleService;

/**
 * 微信公众号文章Service业务层处理
 *
 * @author DXR
 * @date 2024-04-28
 */
@Service
public class WeiXinArticleServiceImpl implements IWeiXinArticleService {
    @Autowired
    private WeiXinArticleMapper weixinArticleMapper;

    /**
     * 查询微信公众号文章
     *
     * @param articleId 微信公众号文章主键
     * @return 微信公众号文章
     */
    @Override
    public WeiXinArticleDTO selectWeixinArticleByArticleId(Long articleId) {
        return weixinArticleMapper.selectWeixinArticleByArticleId(articleId);
    }

    /**
     * 查询微信公众号文章列表
     *
     * @param weixinArticleDTO 微信公众号文章
     * @return 微信公众号文章
     */
    @Override
    public List<WeiXinArticleDTO> selectWeixinArticleList(WeiXinArticleDTO weixinArticleDTO) {
        return weixinArticleMapper.selectWeixinArticleList(weixinArticleDTO);
    }

    /**
     * 新增微信公众号文章
     *
     * @param weixinArticleDTO 微信公众号文章
     * @return 结果
     */
    @Override
    public int insertWeixinArticle(WeiXinArticleDTO weixinArticleDTO) {
        return weixinArticleMapper.insertWeixinArticle(weixinArticleDTO);
    }

    /**
     * 修改微信公众号文章
     *
     * @param weixinArticleDTO 微信公众号文章
     * @return 结果
     */
    @Override
    public int updateWeixinArticle(WeiXinArticleDTO weixinArticleDTO) {
        return weixinArticleMapper.updateWeixinArticle(weixinArticleDTO);
    }

    /**
     * 批量删除微信公众号文章
     *
     * @param articleIds 需要删除的微信公众号文章主键
     * @return 结果
     */
    @Override
    public int deleteWeixinArticleByArticleIds(Long[] articleIds) {
        return weixinArticleMapper.deleteWeixinArticleByArticleIds(articleIds);
    }

    /**
     * 删除微信公众号文章信息
     *
     * @param articleId 微信公众号文章主键
     * @return 结果
     */
    @Override
    public int deleteWeixinArticleByArticleId(Long articleId) {
        return weixinArticleMapper.deleteWeixinArticleByArticleId(articleId);
    }
}
