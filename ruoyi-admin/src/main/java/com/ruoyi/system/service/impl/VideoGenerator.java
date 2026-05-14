package com.ruoyi.system.service.impl;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

import java.io.File;

public class VideoGenerator {
    public static void generateVideo(String imagePath, String audioPath, String outputVideoPath) throws FrameGrabber.Exception, FrameRecorder.Exception {
        // 读取图片
        FFmpegFrameGrabber imageGrabber = new FFmpegFrameGrabber(new File(imagePath));
        imageGrabber.start();
        Frame imageFrame = imageGrabber.grab();

        // 创建视频帧录制器
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideoPath, imageFrame.imageWidth, imageFrame.imageHeight);
//        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 设置视频编码器，MP4格式
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4); // 设置视频编码器
//        recorder.setFormat("mp4"); // 设置输出格式
        recorder.setFormat("avi"); // 设置输出格式
        recorder.setFrameRate(30); // 设置帧率

        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        recorder.setAudioChannels(2); // 设置为双声道
        recorder.setAudioBitrate(128000); // 设置音频比特率
        recorder.setVideoOption("preset", "ultrafast"); // 设置视频编码速度
        recorder.setVideoOption("crf", "28"); // 设置视频质量
        recorder.setVideoBitrate(20000); // 设置视频比特率
// 设置日志回调
        FFmpegLogCallback.set();
        recorder.start();

        // 创建音频帧抓取器
        FFmpegFrameGrabber audioGrabber = new FFmpegFrameGrabber(new File(audioPath));
        audioGrabber.start();

        // 将图片写入视频帧录制器
        recorder.record(imageFrame);

        // 读取音频
        Frame audioFrame;
        while ((audioFrame = audioGrabber.grabFrame()) != null) {
            // 将音频写入视频帧录制器
            recorder.record(audioFrame);
        }

        // 停止录制器并释放资源
        recorder.stop();
        recorder.release();
        audioGrabber.stop();
        audioGrabber.release();
    }

    public static void main(String[] args) {
        String imagePath = "A:/Users/123/Desktop/图片测试/2K.png";
        String audioPath = "A:/Users/123/Desktop/图片测试/1.mp3";
//        String outputVideoPath = "A:/Users/123/Desktop/图片测试/2K.mp4";
        String outputVideoPath = "A:/Users/123/Desktop/图片测试/2K.avi";

        try {
            generateVideo(imagePath, audioPath, outputVideoPath);
            System.out.println("视频生成成功！");
        } catch (Exception e) {
            System.out.println("视频生成失败：" + e.getMessage());
        }
    }
}