package com.ruoyi.weixin.mapper;

import java.util.List;
import com.ruoyi.weixin.domain.WeiXinSendDTO;

/**
 * 微信公众号文章发送Mapper接口
 *
 * @author DXR
 * @date 2024-04-27
 */
public interface WeiXinSendMapper
{
    /**
     * 查询微信公众号文章发送
     *
     * @param sendId 微信公众号文章发送主键
     * @return 微信公众号文章发送
     */
    public WeiXinSendDTO selectWeixinSendBySendId(Long sendId);

    /**
     * 查询微信公众号文章发送列表
     *
     * @param weixinSendDTO 微信公众号文章发送
     * @return 微信公众号文章发送集合
     */
    public List<WeiXinSendDTO> selectWeixinSendList(WeiXinSendDTO weixinSendDTO);

    /**
     * 新增微信公众号文章发送
     *
     * @param weixinSendDTO 微信公众号文章发送
     * @return 结果
     */
    public int insertWeixinSend(WeiXinSendDTO weixinSendDTO);

    /**
     * 修改微信公众号文章发送
     *
     * @param weixinSendDTO 微信公众号文章发送
     * @return 结果
     */
    public int updateWeixinSend(WeiXinSendDTO weixinSendDTO);

    /**
     * 删除微信公众号文章发送
     *
     * @param sendId 微信公众号文章发送主键
     * @return 结果
     */
    public int deleteWeixinSendBySendId(Long sendId);

    /**
     * 批量删除微信公众号文章发送
     *
     * @param sendIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWeixinSendBySendIds(Long[] sendIds);
}
