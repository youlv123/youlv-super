package com.ruoyi.system.controller;


import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.ItemInformationDTO;
import com.ruoyi.system.service.IItemInformationService;
import com.ruoyi.system.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 各种数据初始化专用
 */
@RestController
@RequestMapping("/system/init")
public class InitController {


    @Autowired
    private InitService initService;
    /**
     * 初始化压缩图片
     */
    @GetMapping("/compressPicture")
    public int compressPicture() {
        initService.compressPicture();
        return 1;
    }
}
