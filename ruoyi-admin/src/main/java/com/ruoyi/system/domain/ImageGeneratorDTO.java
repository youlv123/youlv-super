package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class ImageGeneratorDTO {
    /**
     * 图片的宽
     */
    private int widthX;

    /**
     * 图片高度
     */
    private int heightY;

    /**
     * 风景相关参数.0.382黄金等分线,图片占据整个图片上部的比例
     */
    private double ratio;

    /**
     * 风景图片高度
     */
    private int Y1;

    /**
     * 二维码的宽
     */
    private int qrCodeWidthZ;

    /**
     * 二维码的高
     */
    private int qrCodeHeight;

    /**
     * 二维码X坐标
     */
    private int qrCodeX;

    /**
     * 二维码Y坐标
     */
    private int qrCodeY;

    /**
     * 文字区域到照片区域的距离
     */
    private int Y4;

    /**
     * 二维码区域到底部距离
     */
    private int Y3;

    /**
     * 二维码区域到文字区域的距离
     */
    private int Y5;

    /**
     * 文字区域距离边距离
     */
    private int X4;

    /**
     * 作者区域距离文字距离
     */
    private int X6;

    /**
     * 作者区域距离顶部图片距离
     */
    private int Y6;

    /**
     * 字体名称
     */
    private String fontName;

    /**
     * 字体大小
     */
    private float fontSize;

    /**
     * 文字区域A1的X坐标
     */
    private int A1X;

    /**
     * 文字区域A1的Y坐标
     */
    private int A1Y;

    /**
     * 文字区域A2的X坐标
     */
    private int A2X;

    /**
     * 文字区域A2的Y坐标
     */
    private int A2Y;

    /**
     * 文字区域A3的X坐标
     */
    private int A3X;

    /**
     * 文字区域A3的Y坐标
     */
    private int A3Y;

    /**
     * 文字区域A4的X坐标
     */
    private int A4X;

    /**
     * 文字区域A4的Y坐标
     */
    private int A4Y;

    /**
     * 主体文字
     */
    private String text;

    /**
     * 出处
     */
    private String source;

    /**
     * 作者
     */
    private String author;

    /**
     * 拼接后作者和出处
     */
    private String sourceAndAuthor;

    /**
     * 文字区域X2长度
     */
    private int X2;

    /**
     * 文字区域高度Y2
     */
    private int Y2;

    /**
     * 字体的行间距
     */
    private int Y7;

    /**
     * 所有文字加起来的宽
     */
    private int textWidth;

    /**
     * 文字的高
     */
    private int height;

    /**
     * 主体文本一个字的宽
     */
    private int oneWidth;

    /**
     * 作者的宽
     */
    private int autorWidth;

    /**
     * 作者的高
     */
    private int autorHeight;

    /**
     * 作者一个字的宽
     */
    private int oneAutorWidth;

    /**
     * 句号的宽
     */
    private int periodWidth;

    /**
     * 逗号的宽
     */
    private int commaWidth;
    /**
     * 上升高度
     */
    private int ascent;



}
