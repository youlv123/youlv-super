package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 根据物品id查询这个绑定了哪些分类,返回前端对象
 *
 * @author ruoyi
 * @date 2023-10-12
 */
@Data
public class CategoryDTO  {

    /**
     * 主键ID
     */
    private Long itemId;

    /**
     * 主键ID
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    @Excel(name = "分类名称")
    private String categoryName;

    /**
     * 物品是否和这个分类绑定的标识，ture绑定，false没绑定
     */
    private Boolean flag;

}
