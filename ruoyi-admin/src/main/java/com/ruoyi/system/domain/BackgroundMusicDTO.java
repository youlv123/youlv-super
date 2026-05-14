package com.ruoyi.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 背景音乐对象 background_music
 *
 * @author ruoyi
 * @date 2024-02-06
 */
@Data
public class BackgroundMusicDTO {
    /**
     * 主键ID
     */
    private Long musicId;

    /**
     * 音乐名称
     */
    @Excel(name = "音乐名称")
    private String musicName;

    /**
     * 歌手
     */
    @Excel(name = "歌手")
    private String singer;

    /**
     * 音乐类型
     */
    @Excel(name = "音乐类型")
    private String musicType;

    /**
     * 网易云音乐链接
     */
    @Excel(name = "网易云音乐链接")
    private String cloudmusicUrl;

    /**
     * qq音乐链接
     */
    @Excel(name = "qq音乐链接")
    private String qqmusicUrl;

    /**
     * 云盘链接
     */
    @Excel(name = "云盘链接")
    private String cloudUrl;

    /**
     * 是否使用
     */
    @Excel(name = "是否使用")
    private String useFlag;

    /**
     * 使用视频链接
     */
    @Excel(name = "使用视频链接")
    private String useVideoUrl;

    /**
     * 使用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useDate;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新人
     */
    @Excel(name = "灵感备注信息")
    private String remark;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

    /**
     * 逻辑删除
     */
    private String delFlag;

}
