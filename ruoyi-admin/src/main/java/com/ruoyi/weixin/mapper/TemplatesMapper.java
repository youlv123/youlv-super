package com.ruoyi.weixin.mapper;

import com.ruoyi.weixin.domain.TemplatesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 模板Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-27
 */
@Mapper
public interface TemplatesMapper {
    /**
     * 查询模板
     *
     * @param templateId 模板主键
     * @return 模板
     */
    public TemplatesDTO selectTemplatesByTemplateId(Long templateId);

    /**
     * 查询模板列表
     *
     * @param templatesDTO 模板
     * @return 模板集合
     */
    public List<TemplatesDTO> selectTemplatesList(TemplatesDTO templatesDTO);

    /**
     * 新增模板
     *
     * @param templatesDTO 模板
     * @return 结果
     */
    public int insertTemplates(TemplatesDTO templatesDTO);

    /**
     * 修改模板
     *
     * @param templatesDTO 模板
     * @return 结果
     */
    public int updateTemplates(TemplatesDTO templatesDTO);

    /**
     * 删除模板
     *
     * @param templateId 模板主键
     * @return 结果
     */
    public int deleteTemplatesByTemplateId(Long templateId);

    /**
     * 批量删除模板
     *
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplatesByTemplateIds(Long[] templateIds);
}
