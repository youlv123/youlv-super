package com.ruoyi.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.io.FileUtils;



public class QRCodeGenerator {

    /**
     * 默认二维码大小
     */
    private static final int DEFAULT_SIZE = 300;

    /**
     * 默认二维码格式
     */
    private static final String DEFAULT_FORMAT = "png";

    /**
     * 生成二维码
     * 
     * @param content     二维码内容
     * @param size        二维码大小
     * @param format      二维码格式
     * @param color       二维码颜色
     * @param borderColor 二维码边框颜色
     * @param borderWidth 二维码边框大小
     * @param text        二维码中的文字
     * @param font        二维码中文字的字体
     * @param textColor   二维码中文字的颜色
     * @return 生成的二维码文件
     * @throws WriterException
     * @throws IOException
     */
    public static File generateQRCode(String content, int size, String format, Color color, Color borderColor,
            int borderWidth, String text, Font font, Color textColor) throws WriterException, IOException {
        // 创建二维码写入器
        QRCodeWriter writer = new QRCodeWriter();
        // 设置二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置容错率,其中key 值就是EncodeHintType的属性
        //容错,级从大到小的顺序:L>N>Q>H,当等级越高，所斋要的扫描时问就越长，但是准确率越高
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //外边距
        hints.put(EncodeHintType.MARGIN,2);
        // 生成二维码矩阵
        BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        // 创建二维码图片
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
        // 绘制颜色
        if (color != null) {
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(color);
            g2d.fillRect(0, 0, size, size);
            g2d.dispose();
        }
        // 绘制边框
        if (borderColor != null && borderWidth > 0) {
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(borderWidth));
            g2d.drawRect(0, 0, size, size);
            g2d.dispose();
        }
        // 绘制文字
        if (text != null && font != null && textColor != null) {
            Graphics2D g2d = image.createGraphics();
            g2d.setFont(font);
            g2d.setColor(textColor);
            int textWidth = g2d.getFontMetrics().stringWidth(text);
            int textHeight = g2d.getFontMetrics().getHeight();
            g2d.drawString(text, (size - textWidth) / 2, size + textHeight);
            g2d.dispose();
        }
        // 将图片写入输出流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, format, os);
        // 创建临时文件
        File file = File.createTempFile("qrcode-", "." + format);
        // 将输出流中的数据写入文件
        FileUtils.writeByteArrayToFile(file, os.toByteArray());
        // 关闭输出流
        os.close();
        return file;
    }

}
