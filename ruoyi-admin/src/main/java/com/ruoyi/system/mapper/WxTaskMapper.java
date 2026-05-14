package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.stock.domain.StockZtPoolDtgcEm;
import com.ruoyi.system.domain.WxTask;
import org.springframework.stereotype.Repository;

/**
 * 企微消息推送任务Mapper接口
 *
 * @author DXR
 * @date 2025-06-30
 */
@Repository
public interface WxTaskMapper extends BaseMapper<WxTask> {
    /**
     * 查询企微消息推送任务
     *
     * @param wxTaskId 企微消息推送任务主键
     * @return 企微消息推送任务
     */
    public WxTask selectWxTaskByWxTaskId(Long wxTaskId);

    /**
     * 查询企微消息推送任务列表
     *
     * @param wxTask 企微消息推送任务
     * @return 企微消息推送任务集合
     */
    public List<WxTask> selectWxTaskList(WxTask wxTask);

    /**
     * 新增企微消息推送任务
     *
     * @param wxTask 企微消息推送任务
     * @return 结果
     */
    public int insertWxTask(WxTask wxTask);

    /**
     * 修改企微消息推送任务
     *
     * @param wxTask 企微消息推送任务
     * @return 结果
     */
    public int updateWxTask(WxTask wxTask);

    /**
     * 删除企微消息推送任务
     *
     * @param wxTaskId 企微消息推送任务主键
     * @return 结果
     */
    public int deleteWxTaskByWxTaskId(Long wxTaskId);

    /**
     * 批量删除企微消息推送任务
     *
     * @param wxTaskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxTaskByWxTaskIds(Long[] wxTaskIds);

    /**
     * 查询所有任务
     *
     * @return
     */
    List<WxTask> selectAll();
}
