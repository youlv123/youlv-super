package com.ruoyi.test.dto;

import lombok.Data;

import java.util.List;

/**
 * singleQuery返回值
 */
@Data
public class SingleQueryResponse {
    /**
     * 出错返回码，为0表示成功，非0表示调用失败
     */
    private  BasicInfoEntity basicInfo;
    private List<ModelDisplay> modelDisplay;



}
