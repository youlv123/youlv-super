package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BackgroundMusicMapper;
import com.ruoyi.system.domain.BackgroundMusicDTO;
import com.ruoyi.system.service.IBackgroundMusicService;

/**
 * 背景音乐Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-06
 */
@Service
public class BackgroundMusicServiceImpl implements IBackgroundMusicService {
    @Autowired
    private BackgroundMusicMapper backgroundMusicMapper;

    /**
     * 查询背景音乐
     *
     * @param musicId 背景音乐主键
     * @return 背景音乐
     */
    @Override
    public BackgroundMusicDTO selectBackgroundMusicByMusicId(Long musicId) {
        return backgroundMusicMapper.selectBackgroundMusicByMusicId(musicId);
    }

    /**
     * 查询背景音乐列表
     *
     * @param backgroundMusicDTO 背景音乐
     * @return 背景音乐
     */
    @Override
    public List<BackgroundMusicDTO> selectBackgroundMusicList(BackgroundMusicDTO backgroundMusicDTO) {
        return backgroundMusicMapper.selectBackgroundMusicList(backgroundMusicDTO);
    }

    /**
     * 新增背景音乐
     *
     * @param backgroundMusicDTO 背景音乐
     * @return 结果
     */
    @Override
    public int insertBackgroundMusic(BackgroundMusicDTO backgroundMusicDTO) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        backgroundMusicDTO.setCreatedBy(username);
        backgroundMusicDTO.setUpdatedBy(username);
        backgroundMusicDTO.setUserId(userid);
        backgroundMusicDTO.setUserName(username);
        return backgroundMusicMapper.insertBackgroundMusic(backgroundMusicDTO);
    }

    /**
     * 修改背景音乐
     *
     * @param backgroundMusicDTO 背景音乐
     * @return 结果
     */
    @Override
    public int updateBackgroundMusic(BackgroundMusicDTO backgroundMusicDTO) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        backgroundMusicDTO.setCreatedBy(username);
        backgroundMusicDTO.setUpdatedBy(username);
        backgroundMusicDTO.setUserId(userid);
        backgroundMusicDTO.setUserName(username);
        return backgroundMusicMapper.updateBackgroundMusic(backgroundMusicDTO);
    }

    /**
     * 批量删除背景音乐
     *
     * @param musicIds 需要删除的背景音乐主键
     * @return 结果
     */
    @Override
    public int deleteBackgroundMusicByMusicIds(Long[] musicIds) {
        return backgroundMusicMapper.deleteBackgroundMusicByMusicIds(musicIds);
    }

    /**
     * 删除背景音乐信息
     *
     * @param musicId 背景音乐主键
     * @return 结果
     */
    @Override
    public int deleteBackgroundMusicByMusicId(Long musicId) {
        return backgroundMusicMapper.deleteBackgroundMusicByMusicId(musicId);
    }
}
