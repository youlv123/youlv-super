package com.ruoyi.aia.main.java.com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.aia.main.java.com.ruoyi.system.domain.Attachment;
import com.ruoyi.aia.main.java.com.ruoyi.system.mapper.AttachmentMapper;
import com.ruoyi.aia.main.java.com.ruoyi.system.service.IAttachmentService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 附件信息Service业务层处理
 *
 * @author DXR
 * @date 2026-06-22
 */
@Service
public class AttachmentServiceImpl implements IAttachmentService
{
    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 查询附件信息
     *
     * @param id 附件信息主键
     * @return 附件信息
     */
    @Override
    public Attachment selectAttachmentById(Long id)
    {
        return attachmentMapper.selectAttachmentById(id);
    }

    /**
     * 查询附件信息列表
     *
     * @param attachment 附件信息
     * @return 附件信息
     */
    @Override
    public List<Attachment> selectAttachmentList(Attachment attachment)
    {
        return attachmentMapper.selectAttachmentList(attachment);
    }

    /**
     * 新增附件信息
     *
     * @param attachment 附件信息
     * @return 结果
     */
    @Override
    public int insertAttachment(Attachment attachment)
    {
        attachment.setCreateTime(DateUtils.getNowDate());
        return attachmentMapper.insertAttachment(attachment);
    }

    /**
     * 修改附件信息
     *
     * @param attachment 附件信息
     * @return 结果
     */
    @Override
    public int updateAttachment(Attachment attachment)
    {
        attachment.setUpdateTime(DateUtils.getNowDate());
        return attachmentMapper.updateAttachment(attachment);
    }

    /**
     * 批量删除附件信息
     *
     * @param ids 需要删除的附件信息主键
     * @return 结果
     */
    @Override
    public int deleteAttachmentByIds(Long[] ids)
    {
        return attachmentMapper.deleteAttachmentByIds(ids);
    }

    /**
     * 删除附件信息信息
     *
     * @param id 附件信息主键
     * @return 结果
     */
    @Override
    public int deleteAttachmentById(Long id)
    {
        return attachmentMapper.deleteAttachmentById(id);
    }
}
