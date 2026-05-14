package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockBoardConsEm;
import com.ruoyi.system.domain.PushWxMessageRecord;
import org.springframework.stereotype.Repository;

/**
 * 企业微信推送消息记录Mapper接口
 *
 * @author DXR
 * @date 2025-06-21
 */
@Repository
public interface PushWxMessageRecordMapper extends BaseMapper<PushWxMessageRecord> {
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
     * 删除企业微信推送消息记录
     *
     * @param pushId 企业微信推送消息记录主键
     * @return 结果
     */
    public int deletePushWxMessageRecordByPushId(Long pushId);

    /**
     * 批量删除企业微信推送消息记录
     *
     * @param pushIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePushWxMessageRecordByPushIds(Long[] pushIds);

}
