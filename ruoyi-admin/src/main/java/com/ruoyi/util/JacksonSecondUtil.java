package com.ruoyi.util;
 
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 

 
public class JacksonSecondUtil {
 
    /**
     * CODE_WIDTH：二维码宽度，单位像素
     * CODE_HEIGHT：二维码高度，单位像素
     * FRONT_COLOR：二维码前景色，0x000000 表示黑色
     * BACKGROUND_COLOR：二维码背景色，0xFFFFFF 表示白色
     * 演示用 16 进制表示，和前端页面 CSS 的取色是一样的，注意前后景颜色应该对比明显，如常见的黑白
     */
    private static final int CODE_WIDTH = 450;
    private static final int CODE_HEIGHT = 450;
    private static final int FRONT_COLOR = 0x000000;
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    public static BufferedImage createQRCode(String content, BufferedImage bufferedImage, int topFontWidth, int topFontHeight, int centerFontWidth, int centerFontHeight, int bottomFontWidth, int bottomFontHeight) throws WriterException {
        //检验参数
        if (content == null || "".equals(content)) {
            return null;
        }
        if (bufferedImage == null) {
            return null;
        }
        content = content.trim();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int startBottomY = height + topFontHeight;
        height = startBottomY + bottomFontHeight;
        //计算中文文字空白区域
        int centerFontStartX = (width - centerFontWidth) / 2 + 5;   // x轴起始坐标
        int centerFontEndX = centerFontStartX + centerFontWidth + 5;    // x轴终点坐标
        int centerFontStartY = (height - centerFontHeight) / 2 + 5; // y轴起始坐标
        int centerFontEndY = centerFontStartY + centerFontHeight + 5;   // y轴终点坐标
 
        /**com.google.zxing.EncodeHintType：编码提示类型,枚举类型
         * EncodeHintType.CHARACTER_SET：设置字符编码类型
         * EncodeHintType.ERROR_CORRECTION：设置误差校正
         * ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
         * 不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
         * EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
         * */
        Map<EncodeHintType, Object> hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);
 
        /**
         * MultiFormatWriter:多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码
         * encode(String contents,BarcodeFormat format,int width, int height,Map<EncodeHintType,?> hints)
         *  contents:条形码/二维码内容
         *  format：编码类型，如 条形码，二维码 等
         *  width：码的宽度
         *  height：码的高度
         *  hints：码内容的编码类型
         * BarcodeFormat：枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
         * BitMatrix：位(比特)矩阵或叫2D矩阵，也就是需要的二维码
         */
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
 
        /**java.awt.image.BufferedImage：具有图像数据的可访问缓冲图像，实现了 RenderedImage 接口
         * BitMatrix 的 get(int x, int y) 获取比特矩阵内容，指定位置有值，则返回true，将其设置为前景色，否则设置为背景色
         * BufferedImage 的 setRGB(int x, int y, int rgb) 方法设置图像像素
         * x：像素位置的横坐标，即列
         * y：像素位置的纵坐标，即行
         * rgb：像素的值，采用 16 进制,如 0xFFFFFF 白色
         */
        BufferedImage newBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //顶部文字填充区域设置为空白
                if (y <= topFontHeight) {
                    newBufferedImage.setRGB(x, y, BACKGROUND_COLOR);
                }
                //中心文字填充区域设置为空白
                else if (x > centerFontStartX && x < centerFontEndX && y > centerFontStartY && y < centerFontEndY) {
                    newBufferedImage.setRGB(x, y, BACKGROUND_COLOR);
                }
                //底部文字填充区域设置为空白
                else if (y > startBottomY) {
                    newBufferedImage.setRGB(x, y, BACKGROUND_COLOR);
                } else {
                    newBufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
                }
 
            }
        }
        bufferedImage = null; //主动释放
        return newBufferedImage;
    }

    /**
     * @param content  二维码内容
     * @param topFont 二维码最上面文字内容。最大17字，不影响扫描
     * @param centerFont 二维码中间文字内容，最大7个字，建议6个字，不然扫描识别不行
     * @param bottomFont 二维码下面文字内容，最大17字，不影响扫描
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static File createBase64ImgStr(String content, String topFont, String centerFont, String bottomFont) throws WriterException, IOException {
        BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        if (content == null || "".equals(content)) {
            return  null;
        }
        topFont = topFont == null ? "" : topFont;
        centerFont = centerFont == null ? "" : centerFont;
        bottomFont = bottomFont == null ? "" : bottomFont;
        bufferedImage = pressTextInImage(bufferedImage, content, topFont, centerFont, bottomFont);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 将图片写入输出流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        // 创建临时文件
        File file = File.createTempFile("qrcode-", "." + "png");
        // 将输出流中的数据写入文件
        FileUtils.writeByteArrayToFile(file, os.toByteArray());
        // 关闭输出流
        os.close();
        return file;
    }
 
    public static BufferedImage pressTextInImage(BufferedImage bufferedImage, String content, String topFont, String centerFont, String bottomFont) throws WriterException {
        if (bufferedImage == null) {
            return null;
        }
        //图片绘制对象
        Graphics graphics = bufferedImage.createGraphics();
        Font font = new Font("宋体", 5, 40);
        Font font1 = new Font("宋体", 5, 30);
        FontMetrics metrics = graphics.getFontMetrics(font);
        FontMetrics metrics1 = graphics.getFontMetrics(font1);
        //获取中心字体的宽和高
        int centerFontWidth = metrics.stringWidth(centerFont);
        int centerFontHeight = metrics.getHeight();
        //获取底部字体的宽和高
        int bottomFontWidth = metrics1.stringWidth(bottomFont);
        int bottomFontHeight = metrics1.getHeight();
        //获取顶部字体的宽和高
        int topFontWidth = metrics1.stringWidth(topFont);
        int topFontHeight = metrics1.getHeight();
        //将image生成二维码图片对象
        bufferedImage = createQRCode(content, bufferedImage, topFontWidth, topFontHeight, centerFontWidth, centerFontHeight, bottomFontWidth, bottomFontHeight);
        //获取二维码图片的宽和高
        int imageW = bufferedImage.getWidth();
        int imageH = bufferedImage.getHeight();
        //计算顶部文字填充位置
        int topStartX = (imageW - topFontWidth) / 2; //居中显示
        int topStartY = topFontHeight;
        //计算中心文字填充位置
        int centerStartX = (imageW - centerFontWidth) / 2 + 10;  //居中显示
        int centerStartY = imageH / 2 + centerFontHeight / 2 - (centerFontHeight / 4) + 10;
        //计算底部文字填充位置
        int bottomStartX = (imageW - bottomFontWidth) / 2; //居中显示
        int bottomStartY = (imageH - bottomFontHeight) + 15;
 
        //文字图片对象
        BufferedImage textImag = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
        Graphics textGraphics = textImag.createGraphics();
        //画图
        textGraphics.drawImage(bufferedImage, 0, 0, imageW, imageH, null);
        //
        //设置中心画笔的颜色
        textGraphics.setColor(Color.BLACK);
        textGraphics.setFont(font);
        //  写中心字体
        textGraphics.drawString(centerFont, centerStartX, centerStartY);
        // 写底部文字
        textGraphics.setColor(Color.BLACK);
        textGraphics.setFont(font1);
        textGraphics.drawString(bottomFont, bottomStartX, bottomStartY);
        //写顶部字体
        textGraphics.setColor(Color.BLACK);
        textGraphics.setFont(font1);
        textGraphics.drawString(topFont, topStartX, topStartY);
        graphics.dispose();
 
        return textImag;
    }

}
