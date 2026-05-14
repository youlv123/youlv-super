package com.ruoyi.system.service.impl;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacpp.Loader;
//import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TextAnimationGenerator {
    public static void generateVideo(String text, String fontPath, String outputVideoPath) throws FrameRecorder.Exception {
        int videoWidth = 1280;
        int videoHeight = 720;
        int fontSize = 60;
        int fadeInDuration = 3; // 文字淡入的持续时间（秒）
        int fadeOutDuration = 3; // 文字淡出的持续时间（秒）
        int totalDuration = 10; // 视频总时长（秒）

        // 创建一个临时目录用于保存生成的图片帧
        String tempDir = "temp";
        File tempDirFile = new File(tempDir);
        if (!tempDirFile.exists()) {
            tempDirFile.mkdirs();
        }

        // 加载字体文件
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            font = font.deriveFont(Font.BOLD, fontSize);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // 创建一个Java2DFrameConverter用于将Java2D图像转换为Frame对象
        Java2DFrameConverter converter = new Java2DFrameConverter();

        // 创建一个FFmpegFrameRecorder用于录制视频
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideoPath, videoWidth, videoHeight);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setFormat("mp4");
        recorder.setFrameRate(30);
        recorder.setVideoBitrate(8000000);
        recorder.setVideoQuality(0);

        // 创建一个OpenCVFrameConverter用于将OpenCV的Mat对象转换为Frame对象
        OpenCVFrameConverter.ToMat toMat = new OpenCVFrameConverter.ToMat();

        // 创建一个OpenCVFrameConverter用于将Frame对象转换为Java2D图像
        Java2DFrameConverter toImage = new Java2DFrameConverter();

        // 初始化视频录制器
        recorder.start();

        // 创建一个Graphics2D对象来绘制文字
        BufferedImage image = new BufferedImage(videoWidth, videoHeight, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);

        // 计算文字淡入和淡出的帧数
        int fadeInFrames = fadeInDuration * (int)recorder.getFrameRate();
        int fadeOutFrames = fadeOutDuration * (int)recorder.getFrameRate();

        // 计算文字持续显示的帧数
        int textDurationFrames = (totalDuration - fadeInDuration - fadeOutDuration) * (int)recorder.getFrameRate();

        // 绘制文字逐渐淡入的帧
        for (int i = 0; i < fadeInFrames; i++) {
            // 清空画布
            g2d.setBackground(new Color(0, 0, 0, 0));
            g2d.clearRect(0, 0, videoWidth, videoHeight);

            // 计算当前帧的透明度
            float alpha = (float) i / fadeInFrames;

            // 设置文字透明度
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            // 绘制文字
            int textX = (videoWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
            int textY = videoHeight / 2 + g2d.getFontMetrics().getHeight() / 2;
            g2d.drawString(text, textX, textY);

            // 将Java2D图像转换为Frame对象并写入视频录制器
            Frame frame = converter.convert(image);
            recorder.record(frame);
        }

        // 绘制文字持续显示的帧
        for (int i = 0; i < textDurationFrames; i++) {
            // 清空画布
            g2d.setBackground(new Color(0, 0, 0, 0));
            g2d.clearRect(0, 0, videoWidth, videoHeight);

            // 设置文字透明度为1
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

            // 绘制文字
            int textX = (videoWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
            int textY = videoHeight / 2 + g2d.getFontMetrics().getHeight() / 2;
            g2d.drawString(text, textX, textY);

            // 将Java2D图像转换为Frame对象并写入视频录制器
            Frame frame = converter.convert(image);
            recorder.record(frame);
        }

        // 绘制文字逐渐淡出的帧
        for (int i = 0; i < fadeOutFrames; i++) {
            // 清空画布
            g2d.setBackground(new Color(0, 0, 0, 0));
            g2d.clearRect(0, 0, videoWidth, videoHeight);

            // 计算当前帧的透明度
            float alpha = 1.0f - (float) i / fadeOutFrames;

            // 设置文字透明度
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            // 绘制文字
            int textX = (videoWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
            int textY = videoHeight / 2 + g2d.getFontMetrics().getHeight() / 2;
            g2d.drawString(text, textX, textY);

            // 将Java2D图像转换为Frame对象并写入视频录制器
            Frame frame = converter.convert(image);
            recorder.record(frame);
        }

        // 释放资源
        g2d.dispose();
        recorder.stop();
        recorder.release();
    }

    public static void main(String[] args) {
        String text = "Hello, World!";
        String fontPath = "A:/夸克下载/免费商用字体/字体/2/judou-sans-common-hans-semilight.ttf";
        String outputVideoPath = "A:/Users/123/Desktop/图片测试/11.mp4";
        try {
            generateVideo(text, fontPath, outputVideoPath);
            System.out.println("视频生成成功！");
        } catch (Exception e) {
            System.out.println("视频生成失败：" + e.getMessage());
        }
    }
}