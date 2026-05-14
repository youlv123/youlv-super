package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.BackgroundMusicDTO;

/**
 * 背景音乐Service接口
 *
 * @author ruoyi
 * @date 2024-02-06
 */
public interface IBackgroundMusicService {
    /**
     * 查询背景音乐
     *
     * @param musicId 背景音乐主键
     * @return 背景音乐
     */
    public BackgroundMusicDTO selectBackgroundMusicByMusicId(Long musicId);

    /**
     * 查询背景音乐列表
     *
     * @param backgroundMusicDTO 背景音乐
     * @return 背景音乐集合
     */
    public List<BackgroundMusicDTO> selectBackgroundMusicList(BackgroundMusicDTO backgroundMusicDTO);

    /**
     * 新增背景音乐
     *
     * @param backgroundMusicDTO 背景音乐
     * @return 结果
     */
    public int insertBackgroundMusic(BackgroundMusicDTO backgroundMusicDTO);

    /**
     * 修改背景音乐
     *
     * @param backgroundMusicDTO 背景音乐
     * @return 结果
     */
    public int updateBackgroundMusic(BackgroundMusicDTO backgroundMusicDTO);

    /**
     * 批量删除背景音乐
     *
     * @param musicIds 需要删除的背景音乐主键集合
     * @return 结果
     */
    public int deleteBackgroundMusicByMusicIds(Long[] musicIds);

    /**
     * 删除背景音乐信息
     *
     * @param musicId 背景音乐主键
     * @return 结果
     */
    public int deleteBackgroundMusicByMusicId(Long musicId);
}
