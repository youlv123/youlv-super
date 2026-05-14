package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.ImageGeneratorDTO;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageGenerator {
    public static void main(String[] args) {
/*
绘制图片的时候坐标
        (0,0)原点
        ——————————————————————————————————————————————————————————>X
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        v
        Y



        字体的大小分为宽和高
        字体的宽和字体类型，比如宋体和楷体，就不一样，和字体的大小size，和字体的粗细三点有关系
        字体的高和字体的类型，比如宋体和楷体，和字体的大小size有关
        所以字体区域是动态的
        */
        ImageGeneratorDTO dto = new ImageGeneratorDTO();

        //1、图片4k分辨率
        int widthX = 2160; // 图片宽度
        int heightY = 3840; // 图片高度
        //8K分辨率
//        int width = 4320; // 图片宽度
//        int height = 7680; // 图片高度
        double ratio = 0.2;
        //2、风景相关参数.0.382黄金等分线
        double v = heightY * ratio;
        int Y1 = (int) Math.round(v); // 风景图片高度
        //3、二维码相关参数
        //3.1 二维码的宽
        int qrCodeWidthZ = widthX / 6;
        //3.2 二维码的长
        int qrCodeHeight = qrCodeWidthZ; // 二维码高度等于宽度
        //计算二维码左边，即二维码左上角的位置
        //3.3 二维码X坐标
        int qrCodeX = (widthX - qrCodeWidthZ) / 2; // 计算二维码x坐标
        //3.4 二维码Y坐标
        int qrCodeY = heightY - qrCodeHeight - 50; // 计算二维码y坐标
        //5、文字区域到照片区域的距离
        int Y4 = 200;
        //6、二维码区域到底部距离
        int Y3 = 100;
        //7、二维码区域到文字区域的距离
        int Y5 = 100;
        //8、文字区域距离边距离
        int X4 = 250;
        //9、作者区域距离文字距离
        int X6 = 50;
        //10、作者区域距离顶部图片距离
        int Y6 = 200;
        //4、文字相关参数
        //文字
        //4.1字体名称
        String fontName = "方正楷体";
        //4.2字体大小
        float fontSize = 80;


        //A1                                A2
        //------------------------------------
        //|                                  |
        //|                                  |
        //|                                  |
        //|                                  |
        //|                                  |
        //|                                  |
        //------------------------------------
        //A3                                A4

        //4.3 A1的坐标为
        //文字区域X坐标
        int A1X = X4;
        //文字区域Y坐标
//        int A1Y = heightY - landscapeHeightY1-Y4;
        int A1Y = Y1 + Y4;

        //4.4 A2的坐标为
        //文字区域X坐标
        int A2X = widthX - X4;
        //文字区域Y坐标
//        int A2Y = heightY - landscapeHeightY1;
        int A2Y = Y1 + Y4;

        //4.5 A3的坐标为
        //文字区域X坐标
        int A3X = X4;
        //文字区域Y坐标
//        int A3Y = heightY - landscapeHeightY1 - qrCodeWidth;
        int A3Y = heightY - Y3 - Y5 - qrCodeWidthZ;

        //4.6 A4的坐标为
        //文字区域X坐标
        int A4X = widthX - X4;
        //文字区域Y坐标
//        int A4Y = heightY - landscapeHeightY1 - qrCodeWidthZ;
        int A4Y = heightY - Y3 - Y5 - qrCodeWidthZ;

        //4.7 绘制文字
        String text = "小时候觉得忘带作业是天大的事，高中的时候觉得考不上大学是天大的事，恋爱的时候觉得和喜欢的人分开是天大的事。\n"+"但现在回头看看，那些难以跨过的山，其实都已经跨过了。\n"+"以为不能接受的，也都接受了。\n生活充满了选择，遗憾也不过是常态。\n其实人通常就是无论做什么选择都会后悔，大家总是习惯去美化自己当时没有选择的那条路。\n可是大家都心知肚明，就算时间重来一次，以当时的心智和阅历还是会做出同样的选择。\n那么故事的结局还重要吗？" +
 "我想人生就是一场享受过程的修行。“失之东隅，收之桑榆”\n回头看，轻舟已过万重山；\n向前看，前路漫漫亦灿灿。";
//        String text = "浔阳江头夜送客，枫叶荻花秋瑟瑟。主人下马客在船，举酒欲饮无管弦。醉不成欢惨将别，别时茫茫江浸月。忽闻水上琵琶声，主人忘归客不发。寻声暗问弹者谁？琵琶声停欲语迟。移船相近邀相见，添酒回灯重开宴。千呼万唤始出来，犹抱琵琶半遮面。转轴拨弦三两声，未成曲调先有情。弦弦掩抑声声思，似述平生不得志。";
        //4.8 出处
        String source = "";
        //4.9 作者
        String author = "智勇手书体";
        //4.10 出处和作者进行拼接
        String sourceAndAuthor = "——" + author + source;
        //4.11 文字区域X2长度
        int X2 = widthX - 2 * X4;
        //4.12 文字区域高度Y2
        int Y2 = heightY - Y1 - Y3 - Y4 - Y5 - qrCodeWidthZ;
        //4.13 字体的行间距
        int Y7 = 121;
        dto.setWidthX(widthX);
        dto.setHeightY(heightY);
        dto.setRatio(ratio);
        dto.setY1(Y1);
        dto.setQrCodeWidthZ(qrCodeWidthZ);
        dto.setQrCodeHeight(qrCodeHeight);
        dto.setQrCodeX(qrCodeX);
        dto.setQrCodeY(qrCodeY);
        dto.setY4(Y4);
        dto.setY3(Y3);
        dto.setY5(Y5);
        dto.setX4(X4);
        dto.setX6(X6);
        dto.setY6(Y6);
        dto.setFontName(fontName);
        dto.setFontSize(fontSize);
        dto.setA1X(A1X);
        dto.setA1Y(A1Y);
        dto.setA2X(A2X);
        dto.setA2Y(A2Y);
        dto.setA3X(A3X);
        dto.setA3Y(A3Y);
        dto.setA4X(A4X);
        dto.setA4Y(A4Y);
        dto.setText(text);
        dto.setSource(source);
        dto.setAuthor(author);
        dto.setSourceAndAuthor(sourceAndAuthor);
        dto.setX2(X2);
        dto.setY2(Y2);
        dto.setY7(Y7);

//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/鸿雷板书简体-正式版.ttf";//逗号<句号=一个字宽
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/slideyouran-Regular.ttf";//逗号=句号>一个字宽
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/鸿雷行书简体.otf";//逗号<句号<一个字宽
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/庞门正道真贵楷体.ttf";//逗号=句号=一个字宽
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/千图纤墨体.ttf";//逗号=句号=一个字宽
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/瑞美加张清平硬笔楷书.ttf";//不错,逗号<句号<一个字宽
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/瑞美加张清平硬笔行书.ttf";//逗号=句号<一个字宽
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/演示夏行楷.ttf";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/杨任东竹石体-Light.ttf";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/杨任东竹石体-Regular.ttf";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/云峰寒蝉体.ttf";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/云峰静龙行书.ttf";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/智勇手书体.ttf";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/1/GenSenRounded-L.ttc";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/2/judou-sans-common-hans-extralight.ttf";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/2/judou-sans-common-hans-light.ttf";
        String ttfPath = "A:/夸克下载/免费商用字体/字体/2/judou-sans-common-hans-semilight.ttf";
//        String ttfPath = "A:/夸克下载/免费商用字体/字体/2/milky-mono-cn-extralight.ttf";
        //5、绘制整个目标图片
        BufferedImage image = new BufferedImage(widthX, heightY, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // 设置背景颜色为白色
        g2d.setBackground(Color.WHITE);
        //在 g2d 所代表的图形上下文中填充一个宽度为 width，高度为 height 的矩形，该矩形的左上角位于坐标 (0, 0)。
        g2d.fillRect(0, 0, widthX, heightY);
        // 绘制风景图片
        try {
            BufferedImage landscapeImage = ImageIO.read(new File("A:/Users/123/Desktop/图片测试/风景.jpg"));
            //在 g2d 所代表的图形上下文中填充一个宽度为 width，高度为 landscapeHeight 的图片，该矩形的左上角位于坐标 (0, 0)。
            g2d.drawImage(landscapeImage, 0, 0, widthX, Y1, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font font = null;
//         font = new Font("宋体", Font.PLAIN, 59); // 可更改的字体和大小变量
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(ttfPath));
            font = font.deriveFont(fontSize);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建一个FontDesignMetrics对象，用于获取字体的宽度
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        // 调用makeLineFeed方法，将文本内容按照最大宽度分成多行，并用换行符分隔
        String[] rows = makeLineFeed(text, metrics, X2).split("\n");
        //这地方加30考虑可能最后
        A1X = caculateA1X(text, metrics, X2, widthX) ;
        //设置颜色为黑色
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        // 设置文字抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        FontMetrics fm = g2d.getFontMetrics(font);

        for (int i = 0; i < rows.length; i++) {
            //设置每个行文字的Y坐标
            if (i > 0) {
                A1Y += Y7;
            }
            System.out.println("A1Y-----"+A1Y);
            g2d.drawString(rows[i], A1X, A1Y); // 绘制文字
        }

        int textWidth = fm.stringWidth(text); // 获取文字宽度
        int oneWidth = fm.stringWidth("邓"); // 获取文字宽度
        System.out.println("一个文字的宽---"+oneWidth);
        int textHeight = fm.getHeight();
        int ascent = fm.getAscent(); // 上升高度
        dto.setAscent(ascent);
        System.out.println("上升高度"+ascent);
        int descent = fm.getDescent(); // 下降高度
        int harf = descent / 2;
        int realFontHeight = ascent - harf;
        System.out.println("实际高度"+realFontHeight);
        System.out.println("下降高度"+descent);

        //字体真正的高度应该是上升高度减去二分之一下降高度
        int leading = fm.getLeading(); // 行间距
        System.out.println("行间距"+leading);
        int textHeight1 = ascent + descent + leading;
        System.out.println("高度"+textHeight1);
        int oneAutorWidth3 = fm.stringWidth("，"); // 获取文字宽度
        int oneAutorWidth4 = fm.stringWidth("。"); // 获取文字宽度
        System.out.println("逗号宽度---"+oneAutorWidth3);
        System.out.println("句号宽度---"+oneAutorWidth4);
        System.out.println(textHeight);
        System.out.println(textWidth);
        // 绘制出处和作者
        g2d.setFont(new Font(fontName, Font.PLAIN, (int) fontSize - 5));
        FontMetrics autorFm = g2d.getFontMetrics();
        int autorWidth = autorFm.stringWidth(text); // 获取文字宽度
        int oneAutorWidth = autorFm.stringWidth("邓"); // 获取文字宽度
        int periodWidth = autorFm.stringWidth("。"); // 获取句号宽度
        int commaWidth = autorFm.stringWidth("，"); // 获取逗号宽度
        int autorHeight = autorFm.getHeight();//作者所有的高
        System.out.println(autorHeight);
        System.out.println("作者一个文字的宽---"+oneAutorWidth);
        dto.setTextWidth(textWidth);
        dto.setOneWidth(oneWidth);
        dto.setHeight(textHeight);
        dto.setAutorWidth(autorWidth);
        dto.setAutorHeight(autorHeight);
        dto.setOneAutorWidth(oneAutorWidth);
        caculate(dto);

        int lastIndex = ttfPath.lastIndexOf("/"); // 获取最后一个斜杠的索引
        String content =null;
        if (lastIndex != -1) { // 如果找到了斜杠
            content= ttfPath.substring(lastIndex + 1); // 截取最后一个斜杠后面的内容
            System.out.println("截取的内容：" + content);
        } else {
            System.out.println("未找到斜杠");
        }
        int sourceAndAuthorWidth = autorFm.stringWidth(content); // 获取出处和作者宽度
//        int sourceAndAuthorWidth = autorFm.stringWidth(sourceAndAuthor); // 获取出处和作者宽度
        int XX = widthX - sourceAndAuthorWidth - X6; // 计算出处和作者x坐标
        System.out.println("作者"+(A1Y + Y6));
//        g2d.drawString(sourceAndAuthor, XX, A1Y + Y6); // 绘制出处和作者

        g2d.drawString(content, XX, A1Y + Y6); // 绘制出处和作者

        // 绘制二维码
        try {
            BufferedImage qrCodeImage = ImageIO.read(new File("A:/Users/123/Desktop/图片测试/二维码.jpg"));
            g2d.drawImage(qrCodeImage, qrCodeX, qrCodeY, qrCodeWidthZ, qrCodeHeight, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2d.dispose(); // 释放资源

        try {
            ImageIO.write(image, "png", new File("A:/Users/123/Desktop/图片测试/output1.png")); // 输出图片到文件
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // 定义一个方法，用于将一个字符串按照指定的字体和最大宽度分成多行，并用换行符分隔
    public static String makeLineFeed(String text, FontDesignMetrics metrics, int max_width) {
        // 创建一个StringBuilder对象，用于拼接多行文本
        StringBuilder sb = new StringBuilder();
        // 初始化当前行的宽度为0
        int line_width = 0;
        int hang = 0;
        // 遍历字符串中的每一个字符
        for (int i = 0; i < text.length(); i++) {
            // 获取当前字符
            char c = text.charAt(i);
            // 如果是首行，则在添加字符之前先添加两个空格
//            if (hang == 0) {
//                sb.append("");
//                line_width += metrics.charWidth(' ') * 4;
//                hang += 1;
//            }
            // 将当前字符添加到StringBuilder对象中
            sb.append(c);
            // 如果主动换行则跳过
            if (sb.toString().endsWith("\n")) {
                line_width = 0;
                continue;
            }
            // FontDesignMetrics 的 charWidth() 方法可以计算字符的宽度
            int char_width = metrics.charWidth(c);
            // 将当前字符的宽度累加到当前行的宽度中
            line_width += char_width;
            // 如果当前字符的宽度加上之前字符串的已有宽度超出了最大宽度，则换行
            if (line_width >= max_width - char_width) {
                line_width = 0;
                sb.append("\n");
            }
        }
        // 返回拼接好的多行文本字符串
        return sb.toString();
    }

    /**
     * 计算实际情况下，每行的字体占据情况，得出每行字体的宽度，再用行宽减去后再除以2就是文字开头距离边上多远，这样做目的是为了让文字居中对齐
     * @param text
     * @param metrics
     * @param max_width
     * @param widthX
     * @return
     */
    public static int caculateA1X(String text, FontDesignMetrics metrics, int max_width, int widthX) {
        // 创建一个StringBuilder对象，用于拼接多行文本
        StringBuilder sb = new StringBuilder();
        // 初始化当前行的宽度为0
        int line_width = 0;
        int A1X = 0;
        // 遍历字符串中的每一个字符
        for (int i = 0; i < text.length(); i++) {
            // 获取当前字符
            char c = text.charAt(i);
            // 将当前字符添加到StringBuilder对象中
            sb.append(c);
            // 如果主动换行则跳过
            if (sb.toString().endsWith("\n")) {
                line_width = 0;
                continue;
            }
            // FontDesignMetrics 的 charWidth() 方法可以计算字符的宽度
            int char_width = metrics.charWidth(c);
            // 将当前字符的宽度累加到当前行的宽度中
            line_width += char_width;
            // 如果当前字符的宽度加上之前字符串的已有宽度超出了最大宽度，则换行
            if (line_width >= max_width - char_width) {
                A1X = (widthX - line_width) / 2;
                return A1X;
            }
        }
        // 返回拼接好的多行文本字符串
        return A1X;
    }

    /**
     * 定义一个方法，计算文字区域最多可以容纳多少个字
     */
    public static void caculate(ImageGeneratorDTO dto) {
        //图片的宽
        int widthX = dto.getWidthX();
        //图片高度
        int heightY = dto.getHeightY();
        //风景相关参数.0.382黄金等分线,图片占据整个图片上部的比例
        final double ratio = dto.getRatio();
        //风景图片高度
        int y1 = dto.getY1();
        //二维码的宽
        int qrCodeWidthZ = dto.getQrCodeWidthZ();
        //二维码的高
        int qrCodeHeight = dto.getQrCodeHeight();
        //二维码X坐标
        int qrCodeX = dto.getQrCodeX();
        //二维码Y坐标
        int qrCodeY = dto.getQrCodeY();
        //文字区域到照片区域的距离
        int y4 = dto.getY4();
        //二维码区域到底部距离
        int y3 = dto.getY3();
        //二维码区域到文字区域的距离
        int y5 = dto.getY5();
        //文字区域距离边距离
        int x4 = dto.getX4();
        //作者区域距离文字距离
        int x6 = dto.getX6();
        //作者区域距离顶部图片距离
        int y6 = dto.getY6();
        //字体名称
        String fontName = dto.getFontName();
        //字体大小
        float fontSize = dto.getFontSize();
        //文字区域A1的X坐标
        int a1X1 = dto.getA1X();
        //文字区域A1的Y坐标
        int a1Y = dto.getA1Y();
        //文字区域A2的X坐标
        int a2X = dto.getA2X();
        //文字区域A2的Y坐标
        int a2Y = dto.getA2Y();
        //文字区域A3的X坐标
        int a3X = dto.getA3X();
        //文字区域A3的Y坐标
        int a3Y = dto.getA3Y();
        //文字区域A4的X坐标
        int a4X = dto.getA4X();
        //文字区域A4的Y坐标
        int a4Y = dto.getA4Y();
        //主体文字
        String text = dto.getText();
        //出处
        String source = dto.getSource();
        //作者
        String author = dto.getAuthor();
        //拼接后作者和出处
        String sourceAndAuthor = dto.getSourceAndAuthor();
        //文字区域X2长度
        int x2 = dto.getX2();
        //文字区域高度Y2
        int y2 = dto.getY2();
        //字体的行间距
        int y7 = dto.getY7();
        //所有文字加起来的宽
        int textWidth = dto.getTextWidth();
        //文字的高
        int height = dto.getHeight();
        //主体文本一个字的宽
        int oneWidth = dto.getOneWidth();
        //作者的宽
        int autorWidth = dto.getAutorWidth();
        //作者的高
        int autorHeight = dto.getAutorHeight();
        //作者一个字的宽
        int oneAutorWidth = dto.getOneAutorWidth();
        //句号的宽
        int periodWidth = dto.getPeriodWidth();
        //逗号的宽
        int commaWidth = dto.getCommaWidth();
        //上升高度
        int ascent = dto.getAscent();
        //1、排版计算，会导致有逗号在最后面，影响美观，或者某一行没有标点，在已有的宽度下，会导致有些行和其它行不一致，可能会超出，很难看
        //2、标点的宽度和字体有关，句号和逗号还不一致，和字体占据的宽不一致，得特殊计算
        //3、计算最后一行的时候，究竟要容纳多少文字，多少标点进行计算
        //4、要动态调解字体大小，间距等，要在一个范围内，如果超过范围，取消这条的生成，状态改变
        //X2÷oneWidth就是一行的最大文字，有以下几种情况，1、刚刚好除整，2、多几个
        System.out.println("Y1----"+y1);
        System.out.println("Y2----"+y2);
        System.out.println("Y3----"+y3);
        System.out.println("Y4----"+y4);
        System.out.println("Y5----"+y5);
        System.out.println("Y6----"+y6);
        System.out.println("Z----"+qrCodeWidthZ);

        int xNum = x2 / oneWidth;
        System.out.println("xNum___"+xNum);
        int i = y2 - y6;
        System.out.println("i___"+i);
        System.out.println("height___"+height);
        System.out.println("y7___"+y7);
        int yNum = i / y7;
        System.out.println("yNum___"+yNum);
        int total=xNum*yNum;
        System.out.println("总共可以装"+total);
        /**
         * 刚好除整的情况很少，我们考虑不能整除的，多几个的情况
         * 当句号和逗号相等的情况下
         * 当句号和逗号不相等的情况下
         */
/**
 * 在普通文字，在首行缩进的情况下
 * 1、在给定的参数条件下，最大可以容纳多少行文字，最多多少个字
 * 计算规则：拿到X2的值和Y2的值，拿到每个字的宽和每个字的高以及
 * 2、在给定的文字是否超过这个字数限制
 * 3、计算规则以第一行有一个逗号，第二号有一个逗号一个句号为标准计算
 * 4、文字的高仿佛有问题，看看文字的Y坐标到底从哪个地方开始的
 */
//拿到句号逗号文字的宽，拿到最大限度，计算是否有句号在最后超过了最大限度，或者一行有一个逗号超过最大限度，超过了在超过一定范围内，不管，超过太多进入下一行
        //当超过最大限度，自己调整字体大小，重新计算，字体范围在多少
        //则可以计算出最大多少字，最少多少字
        //作者区要小于文字区域的字体大小
        //在普通文字，在首行不缩进的情况下

        //在是古诗的情况下
        //古诗直接看最大古诗字数和最小古诗字数，直接限定，按照模板，绝不会出错
        //古诗等可以调节图片的长宽设置，
        //根据字数多少调节图片和风景占用，使得图片更好看
    }

}
