package com.ruoyi.util;

/**
 * @Description
 * @Author 三文鱼先生
 * @Data 2023/7/5 14:38
 */
public class Test {
    public static void main(String[] args) {
        String inputImagePath = "A:/Users/123/Desktop/图片测试/222.jpg";
        String thumbnailPath = "A:/Users/123/Desktop/图片测试/风景11.jpg";
        String compressPath = "A:/Users/123/Desktop/图片测试/风景12.jpeg";

        ImageUtil.compressWithJPEG(inputImagePath , compressPath);

        ImageUtil.storeThumbnailWithImage(inputImagePath , thumbnailPath);
    }
}
