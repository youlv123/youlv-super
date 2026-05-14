package com.ruoyi.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.stock.domain.StockZhAHist;
import com.ruoyi.stock.mapper.StockZhAHistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PushWxMessageRecordMapper;
import com.ruoyi.system.domain.PushWxMessageRecord;
import com.ruoyi.system.service.IPushWxMessageRecordService;

/**
 * 企业微信推送消息记录Service业务层处理
 *
 * @author DXR
 * @date 2025-06-21
 */
@Service
public class PushWxMessageRecordServiceImpl extends ServiceImpl<PushWxMessageRecordMapper, PushWxMessageRecord> implements IPushWxMessageRecordService {
    @Autowired
    private PushWxMessageRecordMapper pushWxMessageRecordMapper;

    /**
     * 查询企业微信推送消息记录
     *
     * @param pushId 企业微信推送消息记录主键
     * @return 企业微信推送消息记录
     */
    @Override
    public PushWxMessageRecord selectPushWxMessageRecordByPushId(Long pushId) {
        return pushWxMessageRecordMapper.selectPushWxMessageRecordByPushId(pushId);
    }

    /**
     * 查询企业微信推送消息记录列表
     *
     * @param pushWxMessageRecord 企业微信推送消息记录
     * @return 企业微信推送消息记录
     */
    @Override
    public List<PushWxMessageRecord> selectPushWxMessageRecordList(PushWxMessageRecord pushWxMessageRecord) {
        return pushWxMessageRecordMapper.selectPushWxMessageRecordList(pushWxMessageRecord);
    }

    /**
     * 新增企业微信推送消息记录
     *
     * @param pushWxMessageRecord 企业微信推送消息记录
     * @return 结果
     */
    @Override
    public int insertPushWxMessageRecord(PushWxMessageRecord pushWxMessageRecord) {
        return pushWxMessageRecordMapper.insertPushWxMessageRecord(pushWxMessageRecord);
    }

    /**
     * 修改企业微信推送消息记录
     *
     * @param pushWxMessageRecord 企业微信推送消息记录
     * @return 结果
     */
    @Override
    public int updatePushWxMessageRecord(PushWxMessageRecord pushWxMessageRecord) {
        return pushWxMessageRecordMapper.updatePushWxMessageRecord(pushWxMessageRecord);
    }

    /**
     * 批量删除企业微信推送消息记录
     *
     * @param pushIds 需要删除的企业微信推送消息记录主键
     * @return 结果
     */
    @Override
    public int deletePushWxMessageRecordByPushIds(Long[] pushIds) {
        return pushWxMessageRecordMapper.deletePushWxMessageRecordByPushIds(pushIds);
    }

    /**
     * 删除企业微信推送消息记录信息
     *
     * @param pushId 企业微信推送消息记录主键
     * @return 结果
     */
    @Override
    public int deletePushWxMessageRecordByPushId(Long pushId) {
        return pushWxMessageRecordMapper.deletePushWxMessageRecordByPushId(pushId);
    }
}
