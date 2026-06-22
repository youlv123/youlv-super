package com.ruoyi.aia.main.java.com.ruoyi.system.service;

import com.ruoyi.aia.main.java.com.ruoyi.system.domain.Attachment;

import java.util.List;


/**
 * 附件信息Service接口
 *
 * @author DXR
 * @date 2026-06-22
 */
public interface IAttachmentService
{
    /**
     * 查询附件信息
     *
     * @param id 附件信息主键
     * @return 附件信息
     */
    public Attachment selectAttachmentById(Long id);

    /**
     * 查询附件信息列表
     *
     * @param attachment 附件信息
     * @return 附件信息集合
     */
    public List<Attachment> selectAttachmentList(Attachment attachment);

    /**
     * 新增附件信息
     *
     * @param attachment 附件信息
     * @return 结果
     */
    public int insertAttachment(Attachment attachment);

    /**
     * 修改附件信息
     *
     * @param attachment 附件信息
     * @return 结果
     */
    public int updateAttachment(Attachment attachment);

    /**
     * 批量删除附件信息
     *
     * @param ids 需要删除的附件信息主键集合
     * @return 结果
     */
    public int deleteAttachmentByIds(Long[] ids);

    /**
     * 删除附件信息信息
     *
     * @param id 附件信息主键
     * @return 结果
     */
    public int deleteAttachmentById(Long id);
}
