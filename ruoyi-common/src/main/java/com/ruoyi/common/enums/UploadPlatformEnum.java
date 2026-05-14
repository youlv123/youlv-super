package com.ruoyi.common.enums;

/**
 * 图片上传平台枚举
 *
 * @author dengxinrui
 */
public enum UploadPlatformEnum {
    UPLOAD_OSS("COS", "腾讯云COS"),
    BOOLEAN_N("OSS", "阿里云OSS"),
    ;

    private final String code;
    private final String info;

    UploadPlatformEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
