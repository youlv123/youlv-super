package com.ruoyi.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.system.domain.PushWxMessageRecord;

/**
 * 企业微信推送消息记录Service接口
 *
 * @author DXR
 * @date 2025-06-21
 */
public interface IPushWxMessageRecordService extends IService<PushWxMessageRecord> {
    /**
     * 查询企业微信推送消息记录
     *
     * @param pushId 企业微信推送消息记录主键
     * @return 企业微信推送消息记录
     */
    public PushWxMessageRecord selectPushWxMessageRecordByPushId(Long pushId);

    /**
     * 查询企业微信推送消息记录列表
     *
     * @param pushWxMessageRecord 企业微信推送消息记录
     * @return 企业微信推送消息记录集合
     */
    public List<PushWxMessageRecord> selectPushWxMessageRecordList(PushWxMessageRecord pushWxMessageRecord);

    /**
     * 新增企业微信推送消息记录
     *
     * @param pushWxMessageRecord 企业微信推送消息记录
     * @return 结果
     */
    public int insertPushWxMessageRecord(PushWxMessageRecord pushWxMessageRecord);

    /**
     * 修改企业微信推送消息记录
     *
     * @param pushWxMessageRecord 企业微信推送消息记录
     * @return 结果
     */
    public int updatePushWxMessageRecord(PushWxMessageRecord pushWxMessageRecord);

    /**
     * 批量删除企业微信推送消息记录
     *
     * @param pushIds 需要删除的企业微信推送消息记录主键集合
     * @return 结果
     */
    public int deletePushWxMessageRecordByPushIds(Long[] pushIds);

    /**
     * 删除企业微信推送消息记录信息
     *
     * @param pushId 企业微信推送消息记录主键
     * @return 结果
     */
    public int deletePushWxMessageRecordByPushId(Long pushId);
}
