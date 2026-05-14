/*
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class VideoGenerator1 {

    public static void generateVideo(String text, String fontPath, String musicPath, String outputVideoPath) throws Exception {
        // 设置视频参数
        int videoWidth = 1280;
        int videoHeight = 720;
        int videoFrameRate = 30;
        int videoBitrate = 5000000;

        // 加载字体
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(48f);

        // 创建视频录制器
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideoPath, videoWidth, videoHeight);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setFormat("mp4");
        recorder.setFrameRate(videoFrameRate);
        recorder.setVideoBitrate(videoBitrate);
        recorder.start();

        // 创建图像转换器
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

        // 创建图形上下文
        Graphics2D g2d;

        // 创建音频抓取器
        FFmpegFrameGrabber audioGrabber = new FFmpegFrameGrabber(musicPath);
        audioGrabber.start();

        // 创建音频录制器
        FFmpegFrameRecorder audioRecorder = new FFmpegFrameRecorder(outputVideoPath, audioGrabber.getAudioChannels());
        audioRecorder.setAudioCodec(audioGrabber.getAudioCodec());
        audioRecorder.setSampleRate(audioGrabber.getSampleRate());
        audioRecorder.setAudioBitrate(audioGrabber.getAudioBitrate());
        audioRecorder.start();

        // 创建音频帧和视频帧
        Frame audioFrame, videoFrame;
        while ((audioFrame = audioGrabber.grabFrame()) != null && (videoFrame = recorder.grab()) != null) {
            // 将音频帧和视频帧复制到同一个帧中
            Frame mergedFrame = new Frame(videoFrame.imageWidth, videoFrame.imageHeight, videoFrame.imageDepth, videoFrame.imageChannels);
            avutil.av_frame_copy(mergedFrame, videoFrame);
            avutil.av_frame_copy(mergedFrame, audioFrame);

            // 写入合并的帧
            recorder.record(mergedFrame);
            audioRecorder.record(mergedFrame);
        }

        // 释放音频资源
        audioGrabber.stop();
        audioGrabber.release();
        audioRecorder.stop();
        audioRecorder.release();

        // 绘制文字和视频帧
        int totalDuration = 10; // 视频总时长（秒）
        int fadeInDuration = 2; // 淡入时长（秒）
        int fadeOutDuration = 2; // 淡出时长（秒）
        int fadeInFrames = fadeInDuration * videoFrameRate;
        int fadeOutFrames = fadeOutDuration * videoFrameRate;
        int textDurationFrames = (totalDuration - fadeInDuration - fadeOutDuration) * videoFrameRate;

        for (int i = 0; i < fadeInFrames; i++) {
            g2d.setBackground(new Color(0, 0, 0, 0));
            g2d.clearRect(0, 0, videoWidth, videoHeight);

            float alpha = (float) i / fadeInFrames;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            int textX = (videoWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
            int textY = videoHeight / 2 + g2d.getFontMetrics().getHeight() / 2;
            g2d.drawString(text, textX, textY);

            Frame frame = converter.convert(image);
            recorder.record(frame);
        }

        for (int i = 0; i < textDurationFrames; i++) {
            g2d.setBackground(new Color(0, 0, 0, 0));
            g2d.clearRect(0, 0, videoWidth, videoHeight);

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

            int textX = (videoWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
            int textY = videoHeight / 2 + g2d.getFontMetrics().getHeight() / 2;
            g2d.drawString(text, textX, textY);

            Frame frame = converter.convert(image);
            recorder.record(frame);
        }

        for (int i = 0; i < fadeOutFrames; i++) {
            g2d.setBackground(new Color(0, 0, 0, 0));
            g2d.clearRect(0, 0, videoWidth, videoHeight);

            float alpha = 1.0f - (float) i / fadeOutFrames;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            int textX = (videoWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
            int textY = videoHeight / 2 + g2d.getFontMetrics().getHeight() / 2;
            g2d.drawString(text, textX, textY);

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
        String fontPath = "path/to/font.ttf";
        String musicPath = "path/to/music.mp3";
        String outputVideoPath = "output.mp4";

        try {
            generateVideo(text, fontPath, musicPath, outputVideoPath);
            System.out.println("视频生成成功！");
        } catch (Exception e) {
            System.out.println("视频生成失败：" + e.getMessage());
        }
    }
}*/
