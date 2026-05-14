package com.ruoyi.system.service.impl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;

public class ImageGenerator1 {
    public static void main(String[] args) {
        int width = 1080;  // 图片宽度
        int height = 1920; // 图片高度
        int landscapeHeight = height / 3; // 风景图片占据的高度

        // 加载风景图片
        String landscapeImagePath = "A:/Users/123/Desktop/图片测试/风景3.jpg";
        BufferedImage landscapeImage = loadImage(landscapeImagePath);

        // 获取文本内容
        String text = "从数据库获取的字段A"; // 文字内容
        String source = "从数据库获取的字段B"; // 这句话的出处
        String author = "从数据库获取的字段C"; // 作者

        // 设置字体
        String fontName = "Arial"; // 字体名称
        int fontSize = 24; // 字体大小

        // 设置二维码图片
        String qrCodeImagePath = "A:/Users/123/Desktop/图片测试/二维码.jpg";
        BufferedImage qrCodeImage = loadImage(qrCodeImagePath);
        int qrCodeWidth = 200; // 二维码图片宽度
        int qrCodeHeight = 200; // 二维码图片高度

        // 创建一个DOM实现
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // 创建一个文档对象
        String svgNamespaceURI = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNamespaceURI, "svg", null);

        // 创建一个SVGGraphics2D对象，用于绘制图形
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 绘制风景图片
        svgGenerator.drawImage(landscapeImage, 0, 0, width, landscapeHeight,null);

        // 绘制文本
        svgGenerator.setFont(new Font(fontName, Font.PLAIN, fontSize));
        svgGenerator.drawString(text, 50, landscapeHeight + 50); // 调整文本位置

        // 绘制出处和作者
        svgGenerator.setFont(new Font(fontName, Font.PLAIN, 16)); // 调整出处和作者文本大小
        svgGenerator.drawString(source, 50, landscapeHeight + 100); // 调整出处位置
        svgGenerator.drawString("作者：" + author, 50, landscapeHeight + 120); // 调整作者位置

        // 绘制二维码图片
        int qrCodeX = (width - qrCodeWidth) / 2; // 居中位置
        int qrCodeY = height - qrCodeHeight - 50; // 距底部距离为50
        svgGenerator.drawImage(qrCodeImage, qrCodeX, qrCodeY, qrCodeWidth, qrCodeHeight, null);

        try {
            // 将SVG文件保存为PNG图像
            File outputFile = new File("A:/Users/123/Desktop/图片测试/path_to_output.png");
            OutputStream outputStream = new FileOutputStream(outputFile);
            svgGenerator.stream(String.valueOf(outputStream), true);
            outputStream.flush();
            outputStream.close();
            System.out.println("PNG图像已生成。");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}