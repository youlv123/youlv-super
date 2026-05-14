package com.ruoyi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 微信配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx-config")
public class WxConfig {
    private Map<String, WxConfigDTO> wxConfigMap;

    @Data
    public static class WxConfigDTO {
        //应用名
        private String name;
        //企业id
        private String corpId;
        //应用秘钥
        private String corpSecret;
    }
//初始化测试
//    @PostConstruct
//    public void init() {
//        System.out.println("Loaded wxConfigMap: " + wxConfigMap);
//    }
}
