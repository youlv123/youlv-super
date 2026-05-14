package com.ruoyi.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 批量查询入参类
 */
@Data
public class BatchQueryDTO {
    /**
     * 查询开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 查询结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String option;

}
