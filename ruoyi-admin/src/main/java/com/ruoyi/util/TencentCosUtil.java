package com.ruoyi.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.ruoyi.common.utils.uuid.UUID;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TencentCosUtil {
    // 存储桶名称
    private  static final String BUCKET_NAME = "";
    // 存储桶所在地域
    private  static final String REGION_NAME = "";
    // 腾讯云账号的 SecretId
    private  static final String SECRET_ID = "";
    // 腾讯云账号的 SecretKey
    private  static final String SECRET_KEY = "";

    /**
     * 上传文件到腾讯云 COS 存储桶
     *
     * @param file 要上传的文件
     * @return 包含上传成功后的对象键和 URL 的 Map
     * @throws IOException 如果上传失败，将抛出此异常
     */
    public static Map<String, String> qcloudUploadFile(File file,String fileName) throws IOException {

        // 初始化身份信息（secretId, secretKey）
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);

        // 设置客户端配置信息
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(REGION_NAME));

        // 初始化 COS 客户端
        COSClient cosClient = null;
        String key;
        try {
            cosClient = new COSClient(cred, clientConfig);
            // 生成随机的对象键，并以文件的后缀名结尾，不然上传的时候腾讯云不知道文件类型，就不能浏览
            key = UUID.randomUUID().toString().substring(0,19) + fileName.substring(0,fileName.lastIndexOf("."))+fileName.substring(fileName.lastIndexOf("."));
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, file);
            putObjectRequest.setStorageClass(StorageClass.Standard); // 设置存储类型
            // 进行上传
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            // 关流相当于
        } catch (Exception e) {
            throw new IOException("上传文件失败：" + e.getMessage(), e);
        } finally {
            if (cosClient != null) {
                cosClient.shutdown();
            }
        }

        // 构造 URL
        String url = "https://" + BUCKET_NAME + ".cos." + REGION_NAME + ".myqcloud.com/" + key;
        // 创建一个包含上传成功后的对象键和 URL 的 Map 并返回
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("key", key);
        resultMap.put("URL", url);
        return resultMap;
    }

    /**
     * 删除文件
     * @param key 要删除文件的对象键
     * @throws IOException 如果上传失败，将抛出此异常
     */
    public  static void qcloudDeleteFile(String key) throws IOException {

        // 初始化身份信息（secretId, secretKey）
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);

        // 设置客户端配置信息
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(REGION_NAME));

        // 初始化 COS 客户端
        COSClient cosClient = null;

        try {
            cosClient = new COSClient(cred, clientConfig);
            // 创建删除请求
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(BUCKET_NAME, key);
            // 执行删除操作
            cosClient.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new IOException("删除文件失败：" + e.getMessage(), e);
        } finally {
            if (cosClient != null) {
                cosClient.shutdown();
            }
        }
    }

    /**
     * 删除本地文件
     * @param filePath
     */

    public static void deleteFile(String filePath) {
        // 创建File对象
        File file = new File(filePath);

        // 检查文件是否存在
        if (file.exists()) {
            // 尝试删除文件
            boolean isDeleted = file.delete();

            // 根据删除结果输出信息
            if (isDeleted) {
                System.out.println("文件删除成功！");
            } else {
                System.out.println("文件删除失败！");
            }
        } else {
            System.out.println("文件不存在！");
        }
    }



}
