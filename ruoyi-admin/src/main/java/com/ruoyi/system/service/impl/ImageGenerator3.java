package com.ruoyi.system.service.impl;

import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

public class ImageGenerator3 {

    // 定义图片的宽度和高度，可以更改
    public static final int IMAGE_WIDTH = 1080;
    public static final int IMAGE_HEIGHT = 1920;
//    public static final int IMAGE_WIDTH = 2160;
//    public static final int IMAGE_HEIGHT = 3840;

    // 定义风景图片的路径，占比，坐标和尺寸，可以更改
    public static final String LANDSCAPE_IMAGE_PATH = "A:/Users/123/Desktop/图片测试/风景3.jpg";
    public static final double LANDSCAPE_IMAGE_RATIO = 0.33; // 风景图片高度占总图片高度的比例
    public static final int LANDSCAPE_IMAGE_X = 0; // 风景图片的x坐标
    public static final int LANDSCAPE_IMAGE_Y = 0; // 风景图片的y坐标
    public static final int LANDSCAPE_IMAGE_WIDTH = IMAGE_WIDTH; // 风景图片的宽度
    public static final int LANDSCAPE_IMAGE_HEIGHT = (int) (IMAGE_HEIGHT * LANDSCAPE_IMAGE_RATIO); // 风景图片的高度

    // 定义文字的字体，大小，颜色，坐标，区域，行间距和缩进，可以更改
    public static final String TEXT_FONT_FAMILY = "华文楷体"; // 文字的字体
    public static final int TEXT_FONT_SIZE = 48; // 文字的大小
    public static final String TEXT_COLOR = "#000000"; // 文字的颜色
    public static final int TEXT_X = 100; // 文字区域的x坐标
    public static final int TEXT_Y = LANDSCAPE_IMAGE_HEIGHT + 100; // 文字区域的y坐标
    public static final int TEXT_WIDTH = IMAGE_WIDTH - 200; // 文字区域的宽度
    public static final int TEXT_HEIGHT = IMAGE_HEIGHT - LANDSCAPE_IMAGE_HEIGHT - 400; // 文字区域的高度
    public static final int TEXT_LINE_SPACING = 10; // 文字的行间距
    public static final int TEXT_INDENTATION = 72; // 每段文字第一行的缩进

    // 定义二维码图片的路径，占比，坐标和尺寸，可以更改
    public static final String QR_CODE_IMAGE_PATH = "A:/Users/123/Desktop/图片测试/二维码.jpg";
    public static final double QR_CODE_IMAGE_RATIO = 0.1; // 二维码图片宽度占总图片宽度的比例
    public static final int QR_CODE_IMAGE_WIDTH = (int) (IMAGE_WIDTH * QR_CODE_IMAGE_RATIO); // 二维码图片的宽度
    public static final int QR_CODE_IMAGE_HEIGHT = QR_CODE_IMAGE_WIDTH; // 二维码图片的高度
    public static final int QR_CODE_IMAGE_X = (IMAGE_WIDTH - QR_CODE_IMAGE_WIDTH) / 2; // 二维码图片的x坐标
    public static final int QR_CODE_IMAGE_Y = IMAGE_HEIGHT - QR_CODE_IMAGE_HEIGHT - 100; // 二维码图片的y坐标
    // 一个方法，用于计算文字区域的实际高度
    public static int getTextHeight() {
        return IMAGE_HEIGHT - LANDSCAPE_IMAGE_HEIGHT - QR_CODE_IMAGE_HEIGHT - 200; // 文字区域的高度等于总图片高度减去风景图片高度，二维码图片高度和上下边距
    }
    // 定义每行显示的古诗数量，可以更改
    public static final int POEM_LINE_COUNT = 1; // 每行显示两句古诗
    public static void main(String[] args) {
        try {
            // 创建一个空白图片和一个图形对象
            BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            // 设置图片的背景颜色为白色
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

            // 在图片上绘制风景图片
            Image landscapeImage = ImageIO.read(new File(LANDSCAPE_IMAGE_PATH));
            g2d.drawImage(landscapeImage, LANDSCAPE_IMAGE_X, LANDSCAPE_IMAGE_Y, LANDSCAPE_IMAGE_WIDTH, LANDSCAPE_IMAGE_HEIGHT, null);

            // 在图片上绘制文字
            g2d.setFont(new Font(TEXT_FONT_FAMILY, Font.PLAIN, TEXT_FONT_SIZE));
            g2d.setColor(Color.decode(TEXT_COLOR));
            String text = getTextFromDatabase(g2d); // 从数据库中获取文字，根据id
            String[] paragraphs = text.split("\n"); // 按照换行符将文字分割成段落
            int currentY = TEXT_Y; // 当前的y坐标



            for (String paragraph : paragraphs) {
                String[] units = paragraph.split("[\\s。]"); // 按照空格或者逗号将段落分割成单元
                boolean firstLine = true; // 一个标志，表示是否是段落的第一行
                int lineCount = 0; // 一个计数器，表示当前行已经显示了多少个单元
                for (String unit : units) {
                    int currentX = firstLine ? TEXT_X + TEXT_INDENTATION : TEXT_X; // 当前的x坐标，如果是第一行，就缩进两个字符，否则就从左边开始
                    int unitWidth = g2d.getFontMetrics().stringWidth(unit); // 单元的宽度
                    if (currentX + unitWidth > TEXT_X + TEXT_WIDTH) {
                        // 如果单元不能在一行内显示，就换行到下一行
                        currentY += TEXT_FONT_SIZE + TEXT_LINE_SPACING;
                        currentX = TEXT_X;
                        lineCount = 0; // 重置计数器
                    }
                    if (currentY < TEXT_Y + getTextHeight()) {
                        // 如果当前的y坐标没有超出文字区域的范围，就在图片上绘制单元
                        g2d.drawString(unit, currentX, currentY);
                        currentX += unitWidth; // 更新x坐标
                        lineCount++; // 更新计数器
                        if (currentX + TEXT_FONT_SIZE < TEXT_X + TEXT_WIDTH) {
                            // 如果有足够的空间放一个标点符号或者空格，就在图片上绘制标点符号或者空格
                            if (paragraph.indexOf(unit) + unit.length() < paragraph.length()) {
                                String punctuationOrSpace = paragraph.substring(paragraph.indexOf(unit) + unit.length(), paragraph.indexOf(unit) + unit.length() + 1);
                                g2d.drawString(punctuationOrSpace, currentX, currentY);
                                currentX += g2d.getFontMetrics().stringWidth(punctuationOrSpace);
                            }else {
                                int sourceWidth = g2d.getFontMetrics().stringWidth(unit); // 来源信息的宽度
                                int spaceCount = (TEXT_WIDTH - sourceWidth) / g2d.getFontMetrics().stringWidth(" "); // 需要的空格数量，用于将来源信息对齐到右边
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < spaceCount; i++) {
                                    sb.append(" "); // 添加空格，用于填充空白
                                }
                                sb.append(unit);

                            }
                        }
                        if (lineCount == POEM_LINE_COUNT) {
                            // 如果当前行已经显示了POEM_LINE_COUNT个单元，就换行到下一行
                            currentY += TEXT_FONT_SIZE + TEXT_LINE_SPACING;
                            currentX = TEXT_X;
                            lineCount = 0; // 重置计数器
                        }
                    } else {
                        // 如果当前的y坐标超出了文字区域的范围，就跳出循环，不再绘制文字
                        break;
                    }
                    firstLine = false; // 设置标志为false，表示已经绘制了段落的第一行
                }
                currentY += TEXT_FONT_SIZE + TEXT_LINE_SPACING; // 更新y坐标，绘制下一个段落
            }

            // 在图片上绘制二维码图片
            Image qrCodeImage = ImageIO.read(new File(QR_CODE_IMAGE_PATH));
            g2d.drawImage(qrCodeImage, QR_CODE_IMAGE_X, QR_CODE_IMAGE_Y, QR_CODE_IMAGE_WIDTH, QR_CODE_IMAGE_HEIGHT, null);

            // 释放图形对象的资源
            g2d.dispose();

            // 将图片写入一个文件中
            File file = new File("A:/Users/123/Desktop/图片测试/output3.png");
            ImageIO.write(image, "png", file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 一个方法，用于从数据库中获取文字，根据id
    public static String getTextFromDatabase(Graphics2D g2d) {

        StringBuilder sb = new StringBuilder();


//                String fieldA = rs.getString("fieldA"); // 文字内容
//                String fieldB = rs.getString("fieldB"); // 来源标题
//                String fieldC = rs.getString("fieldC"); // 来源作者
        String fieldA = "浔阳江头夜送客，枫叶荻花秋瑟瑟。主人下马客在船，举酒欲饮无管弦。醉不成欢惨将别，别时茫茫江浸月。忽闻水上琵琶声，主人忘归客不发。寻声暗问弹者谁？琵琶声停欲语迟。移船相近邀相见，添酒回灯重开宴。千呼万唤始出来，犹抱琵琶半遮面。转轴拨弦三两声，未成曲调先有情。";
        String fieldB = "《琵琶行》";
        String fieldC = "白居易";
        String sourceAndAuthor = "——"+fieldC + fieldB;
        sb.append(fieldA); // 将文字内容添加到字符串构建器中
//        sb.append("\n\n"); // 添加两个换行符，用于分隔文字内容和来源信息

        int sourceWidth = g2d.getFontMetrics().stringWidth(fieldB + " " + fieldC); // 来源信息的宽度

//                if (sourceWidth > TEXT_WIDTH) {
//                    // 如果来源信息不能在一行内显示，就换行到两行
//                    sb.append(fieldB); // 将来源标题添加到字符串构建器中
//                    sb.append("\n"); // 添加一个换行符，用于分隔来源标题和作者
//
//                    int spaceCount = (TEXT_WIDTH - g2d.getFontMetrics().stringWidth(fieldC)) / g2d.getFontMetrics().stringWidth(" "); // 需要的空格数量，用于将来源作者对齐到右边
//
//                    for (int i = 0; i < spaceCount; i++) {
//                        sb.append(" "); // 添加空格，用于填充空白
//                    }
//
//                    sb.append(fieldC); // 将来源作者添加到字符串构建器中
//
//                } else {
        // 如果来源信息能在一行内显示，就对齐到右边
        int spaceCount = (TEXT_WIDTH - sourceWidth) / g2d.getFontMetrics().stringWidth(" "); // 需要的空格数量，用于将来源信息对齐到右边

//        for (int i = 0; i < spaceCount; i++) {
//            sb.append(" "); // 添加空格，用于填充空白
//        }

//                    sb.append(fieldB); // 将来源标题添加到字符串构建器中
//                    sb.append(" "); // 添加一个空格，用于分隔来源标题和作者
//                    sb.append(fieldC); // 将来源作者添加到字符串构建器中
        sb.append(sourceAndAuthor);
//                }


        return sb.toString(); // 返回文字内容作为一个字符串
    }
}