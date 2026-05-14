package com.ruoyi.system.service.impl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
public class ImageGenerator4 {

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
            // 设置文字抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            String text = getTextFromDatabase(g2d); // 从数据库中获取文字，根据id
            String[] paragraphs = text.split("\n"); // 按照换行符将文字分割成段落
            int currentY = TEXT_Y; // 当前的y坐标


// -----------------------------------------------------------------------------------------------

// 设置文字区域和边距
            int marginX = 50;  // 左右边距
            int marginY = 50;  // 上下边距
            int textAreaWidth = 2160 - 2 * marginX;    // 文字区域宽度
            int textAreaHeight = 2176 - 2 * marginY;   // 文字区域高度

            // 设置文本内容
            Font font = new Font("Arial", Font.PLAIN, 36);  // 字体设置
            Color textColor = Color.BLACK;  // 文字颜色

            // 设置段落格式
            float lineSpacing = 1.2f;  // 行间距，可以根据需要调整
            float wordSpacing = 0.5f;  // 字间距，可以根据需要调整

            // 设置作者和出处
            String author = "作者：张三";
            String source = "出处：某某杂志";
            Font authorFont = new Font("Arial", Font.BOLD, 24);             // 作者字体设置
            Color authorColor = Color.GRAY;                                 // 作者颜色
            Font sourceFont = new Font("Arial", Font.PLAIN, 18);            // 出处字体设置
            Color sourceColor = Color.GRAY;                                 // 出处颜色
            int authorSourceMargin = 10;                                    // 作者和出处之间的间距
            int authorTopMargin = 20;                                       // 顶部首行文字和作者之间的间距
            int sourceBottomMargin = 20;                                    // 底部最后一行文字和出处之间的间距

            // 设置文本区域
            Rectangle textRect = new Rectangle(
                    marginX,
                    marginY + authorTopMargin + font.getSize(),
                    textAreaWidth,
                    textAreaHeight - authorTopMargin - font.getSize() - sourceBottomMargin - authorFont.getSize());

            // 设置字体绘制上下文
            FontRenderContext frc = g2d.getFontRenderContext();

            // 设置文本属性
            AttributedString attributedString = new AttributedString(text);
            attributedString.addAttribute(TextAttribute.FONT, font);
            attributedString.addAttribute(TextAttribute.FOREGROUND, textColor);

            // 创建段落和作者/出处的文本布局列表
            List<TextLayout> layoutList = new ArrayList<>();

            // 计算段落的布局
            LineBreakMeasurer measurer = new LineBreakMeasurer(attributedString.getIterator(), frc);
            float wrappingWidth = textRect.width;
            while (measurer.getPosition() < text.length()) {
                TextLayout layout = measurer.nextLayout(wrappingWidth);
                layoutList.add(layout);
            }

            // 计算作者和出处的布局
            TextLayout authorLayout = new TextLayout(author, authorFont, frc);
            TextLayout sourceLayout = new TextLayout(source, sourceFont, frc);

            // 绘制文字
            g2d.setColor(textColor);
            float y = textRect.y;
            for (TextLayout layout : layoutList) {
                y += layout.getAscent();
                layout.draw(g2d, textRect.x, y);
                y += layout.getDescent() + layout.getLeading();
            }

            // 绘制作者和出处
            g2d.setColor(authorColor);
            float authorY = textRect.y - authorTopMargin;
            authorLayout.draw(g2d, textRect.x, authorY);

            g2d.setColor(sourceColor);
            float sourceY = textRect.y + textRect.height + sourceBottomMargin;
            sourceLayout.draw(g2d, textRect.x, sourceY);

            // 显示最大可生成字数
//            int maxCharCount = getMaxCharCount(layoutList, font, wrappingWidth);
//            System.out.println("最大可生成字数：" + maxCharCount);
// -----------------------------------------------------------------------------------------------



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

    // 获取最大可生成字数
//    private static int getMaxCharCount(List<TextLayout> layoutList, Font font, float wrappingWidth) {
//        int maxCharCount = 0;
//        for (TextLayout layout : layoutList) {
//            int lineCharCount = layout.getLineBreakIndex(0, wrappingWidth);
//            if (lineCharCount > maxCharCount) {
//                maxCharCount = lineCharCount;
//            }
//        }
//
//        return maxCharCount;
//    }

//    private static int getMaxCharCount(List<TextLayout> layoutList, Font font, float wrappingWidth) {
//        int maxCharCount = 0;
//        for (TextLayout layout : layoutList) {
//            int charCount = getCharCount(layout, font, wrappingWidth);
//            if (charCount > maxCharCount) {
//                maxCharCount = charCount;
//            }
//        }
//        return maxCharCount;
//    }




    // 创建 Graphics2D 对象
    private static Graphics2D createGraphics2D() {
        int imageWidth = 2160;
        int imageHeight = 3840;

        Image image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        return g2d;
    }
}