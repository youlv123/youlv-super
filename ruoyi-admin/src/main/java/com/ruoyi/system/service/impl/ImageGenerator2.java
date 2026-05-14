package com.ruoyi.system.service.impl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;  
import javax.imageio.ImageIO;  
import javax.swing.ImageIcon;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
  
public class ImageGenerator2 {  
    public static void main(String[] args) {  
        try {  
            int width = 1080; // 可更改的尺寸变量  
            int height = 1920; // 可更改的尺寸变量  
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            Graphics g = image.getGraphics();  
  
            // 生成背景为白色  
            g.setColor(Color.WHITE);  
            g.fillRect(0, 0, width, height);  
  
            // 上部为风景图片  
            File[] imageFiles = {new File("A:/Users/123/Desktop/图片测试/风景3.jpg")}; // 替换为您的风景图片路径
            BufferedImage[] images = {ImageIO.read(imageFiles[0])}; // 将图片加载到BufferedImage中  
            int numImages = images.length;  
            int imageWidth = images[0].getWidth();  
            int imageHeight = images[0].getHeight();  
            float totalWidth = (float) imageWidth * numImages; // 计算所有图片的总宽度  
            float totalHeight = (float) imageHeight; // 计算所有图片的总高度  
            int x = (int) ((width - totalWidth) / 2); // 计算第一张图片的起始x坐标  
            int y = (int) ((height - totalHeight) / 2); // 计算第一张图片的起始y坐标  
            for (int i = 0; i < numImages; i++) {  
                g.drawImage(images[i], x, y, null); // 绘制风景图片  
                x += imageWidth; // 更新下一张图片的起始x坐标  
                if (i < numImages - 1) { // 不是最后一张图片，需要绘制间隔线  
                    g.setColor(Color.BLACK);  
                    g.drawLine(x, y + imageHeight / 2, x + 1, y + imageHeight / 2);  
                    x += 1; // 更新下一张图片的起始x坐标  
                }  
            }  
            images[0].getGraphics().dispose(); // 释放风景图片占用的资源  
  
            // 下部为文字  
            String text = "这是从数据库查出来的字段A"; // 替换为您的文字内容  
            Font font = new Font("Arial", Font.PLAIN, 16); // 可更改的字体和大小变量  
            g.setFont(font);  
            FontMetrics metrics = g.getFontMetrics(); // 获取字体的字距和行高信息
            int lineHeight = metrics.getHeight(); // 计算单行文本的高度  
            int startX = (width - metrics.stringWidth(text)) / 2; // 计算文本的起始x坐标  
            int startY = height - lineHeight * 3 - metrics.getDescent(); // 计算文本的起始y坐标，这里留出三行空白作为文字区域的上边界  
            g.setColor(Color.BLACK); // 设置文本颜色为黑色  
            g.drawString(text, startX, startY); // 绘制文本内容
            // 显示二维码  
            File[] qrCodeFiles = {new File("A:/Users/123/Desktop/图片测试/二维码.jpg")}; // 替换为您的二维码图片路径
            BufferedImage[] qrCodeImages = {ImageIO.read(qrCodeFiles[0])}; // 将二维码图片加载到BufferedImage中  
            int qrCodeWidth = qrCodeImages[0].getWidth(); // 计算二维码图片的宽度  
            int qrCodeHeight = qrCodeImages[0].getHeight(); // 计算二维码图片的高度  
            int qrCodeX = (width - qrCodeWidth) / 2; // 计算二维码图片的起始x坐标  
            int qrCodeY = height - qrCodeHeight - lineHeight; // 计算二维码图片的起始y坐标，这里放在文字区域的下边界  
            g.drawImage(qrCodeImages[0], qrCodeX, qrCodeY, null); // 绘制二维码图片  
  
            // 释放资源  
            g.dispose();  
            image.flush();  
  
            // 显示生成的图片  
            JFrame frame = new JFrame();  
            frame.getContentPane().add(new JLabel(new ImageIcon(image)));  
            frame.pack();  
            frame.setVisible(true);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}