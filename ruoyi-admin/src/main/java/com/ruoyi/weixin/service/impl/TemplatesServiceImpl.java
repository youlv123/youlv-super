package com.ruoyi.weixin.service.impl;

import java.util.List;

import com.ruoyi.weixin.domain.TemplatesDTO;
import com.ruoyi.weixin.mapper.TemplatesMapper;
import com.ruoyi.weixin.service.ITemplatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 模板Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-27
 */
@Service
public class TemplatesServiceImpl implements ITemplatesService {
    @Autowired
    private TemplatesMapper templatesMapper;

    /**
     * 查询模板
     *
     * @param templateId 模板主键
     * @return 模板
     */
    @Override
    public TemplatesDTO selectTemplatesByTemplateId(Long templateId) {
        return templatesMapper.selectTemplatesByTemplateId(templateId);
    }

    /**
     * 查询模板列表
     *
     * @param templatesDTO 模板
     * @return 模板
     */
    @Override
    public List<TemplatesDTO> selectTemplatesList(TemplatesDTO templatesDTO) {
        return templatesMapper.selectTemplatesList(templatesDTO);
    }

    /**
     * 新增模板
     *
     * @param templatesDTO 模板
     * @return 结果
     */
    @Override
    public int insertTemplates(TemplatesDTO templatesDTO) {
        return templatesMapper.insertTemplates(templatesDTO);
    }

    /**
     * 修改模板
     *
     * @param templatesDTO 模板
     * @return 结果
     */
    @Override
    public int updateTemplates(TemplatesDTO templatesDTO) {
        return templatesMapper.updateTemplates(templatesDTO);
    }

    /**
     * 批量删除模板
     *
     * @param templateIds 需要删除的模板主键
     * @return 结果
     */
    @Override
    public int deleteTemplatesByTemplateIds(Long[] templateIds) {
        return templatesMapper.deleteTemplatesByTemplateIds(templateIds);
    }

    /**
     * 删除模板信息
     *
     * @param templateId 模板主键
     * @return 结果
     */
    @Override
    public int deleteTemplatesByTemplateId(Long templateId) {
        return templatesMapper.deleteTemplatesByTemplateId(templateId);
    }
}
