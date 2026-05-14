package com.ruoyi.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weixin.mapper.WeiXinSendMapper;
import com.ruoyi.weixin.domain.WeiXinSendDTO;
import com.ruoyi.weixin.service.IWeiXinSendService;

/**
 * 微信公众号文章发送Service业务层处理
 *
 * @author DXR
 * @date 2024-04-27
 */
@Service
public class WeiXinSendServiceImpl implements IWeiXinSendService {
    @Autowired
    private WeiXinSendMapper weixinSendMapper;

    /**
     * 查询微信公众号文章发送
     *
     * @param sendId 微信公众号文章发送主键
     * @return 微信公众号文章发送
     */
    @Override
    public WeiXinSendDTO selectWeixinSendBySendId(Long sendId) {
        return weixinSendMapper.selectWeixinSendBySendId(sendId);
    }

    /**
     * 查询微信公众号文章发送列表
     *
     * @param weixinSendDTO 微信公众号文章发送
     * @return 微信公众号文章发送
     */
    @Override
    public List<WeiXinSendDTO> selectWeixinSendList(WeiXinSendDTO weixinSendDTO) {
        return weixinSendMapper.selectWeixinSendList(weixinSendDTO);
    }

    /**
     * 新增微信公众号文章发送
     *
     * @param weixinSendDTO 微信公众号文章发送
     * @return 结果
     */
    @Override
    public int insertWeixinSend(WeiXinSendDTO weixinSendDTO) {
        return weixinSendMapper.insertWeixinSend(weixinSendDTO);
    }

    /**
     * 修改微信公众号文章发送
     *
     * @param weixinSendDTO 微信公众号文章发送
     * @return 结果
     */
    @Override
    public int updateWeixinSend(WeiXinSendDTO weixinSendDTO) {
        return weixinSendMapper.updateWeixinSend(weixinSendDTO);
    }

    /**
     * 批量删除微信公众号文章发送
     *
     * @param sendIds 需要删除的微信公众号文章发送主键
     * @return 结果
     */
    @Override
    public int deleteWeixinSendBySendIds(Long[] sendIds) {
        return weixinSendMapper.deleteWeixinSendBySendIds(sendIds);
    }

    /**
     * 删除微信公众号文章发送信息
     *
     * @param sendId 微信公众号文章发送主键
     * @return 结果
     */
    @Override
    public int deleteWeixinSendBySendId(Long sendId) {
        return weixinSendMapper.deleteWeixinSendBySendId(sendId);
    }
}
