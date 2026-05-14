package com.ruoyi.system.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.zxing.WriterException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.util.JacksonSecondUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.QrcodeMapper;
import com.ruoyi.system.domain.QrcodeDTO;
import com.ruoyi.system.service.IQrcodeService;

/**
 * 二维码Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-28
 */
@Service
public class QrcodeServiceImpl implements IQrcodeService {
    @Autowired
    private QrcodeMapper qrcodeMapper;

    /**
     * 查询二维码
     *
     * @param qrcodeId 二维码主键
     * @return 二维码
     */
    @Override
    public QrcodeDTO selectQrcodeByQrcodeId(Long qrcodeId) {
        return qrcodeMapper.selectQrcodeByQrcodeId(qrcodeId);
    }

    /**
     * 查询二维码列表
     *
     * @param qrcodeDTO 二维码
     * @return 二维码
     */
    @Override
    public List<QrcodeDTO> selectQrcodeList(QrcodeDTO qrcodeDTO) {
        return qrcodeMapper.selectQrcodeList(qrcodeDTO);
    }

    /**
     * 新增二维码
     *
     * @param qrcodeDTO 二维码
     * @return 结果
     */
    @Override
    public int insertQrcode(QrcodeDTO qrcodeDTO) {
        return qrcodeMapper.insertQrcode(qrcodeDTO);
    }

    /**
     * 修改二维码
     *
     * @param qrcodeDTO 二维码
     * @return 结果
     */
    @Override
    public int updateQrcode(QrcodeDTO qrcodeDTO) {
        return qrcodeMapper.updateQrcode(qrcodeDTO);
    }

    /**
     * 批量删除二维码
     *
     * @param qrcodeIds 需要删除的二维码主键
     * @return 结果
     */
    @Override
    public int deleteQrcodeByQrcodeIds(Long[] qrcodeIds) {
        return qrcodeMapper.deleteQrcodeByQrcodeIds(qrcodeIds);
    }

    /**
     * 删除二维码信息
     *
     * @param qrcodeId 二维码主键
     * @return 结果
     */
    @Override
    public int deleteQrcodeByQrcodeId(Long qrcodeId) {
        return qrcodeMapper.deleteQrcodeByQrcodeId(qrcodeId);
    }

    /**
     * 根据前端传来的数量，批量生成二维码
     *
     * @param num
     * @return
     */
    @Override
    public ResponseEntity<byte[]> createBatch(int num) throws IOException, WriterException {
        //1、数量大于1000，直接结束
        if (num > 1000) {
            return null;
        }
        //2、获取用户信息
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        //3、新建返回给前端的包含所有二维码的集合
        List<File> filesList = new ArrayList<>();
        //4、创建num个二维码
        for (int i = 0; i < num; i++) {
            QrcodeDTO qrcodeDTO = new QrcodeDTO();
            qrcodeDTO.setIsBatchBinding("N");
            qrcodeDTO.setStatus("N");
            qrcodeDTO.setContent("");
            qrcodeDTO.setCreatedBy(username);
            qrcodeDTO.setUpdatedBy(username);
            qrcodeDTO.setDelFlag("0");
            qrcodeMapper.insertQrcode(qrcodeDTO);
            Long qrcodeId = qrcodeDTO.getQrcodeId();
            //4.1生成二维码
            //二维码内容
            String content = "目前不能跳转没有信息，敬请期待..." + qrcodeId;
            //二维码最顶层内容
            String topFont = "夜深忽梦少年事";
            //二维码中间内容
            String centerFont = "这就是二维码";
            //二维码最下面内容
            String bottomFont = "梦啼妆泪红阑干";
            //调用工具生成
            File base64ImgStr = JacksonSecondUtil.createBase64ImgStr(content, topFont, centerFont, bottomFont);
            // 将文件添加到文件列表中
            filesList.add(base64ImgStr);
        }
        //5、二维码打包成压缩包返回给前端
        //5.1创建一个字节输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //5.2创建字节数组缓冲区
        byte[] buffer = new byte[1024];
        //5.3创建ZipOutputStream对象，使用try-with-resources语句
        try (ZipOutputStream zipOut = new ZipOutputStream(out)) {
            //5.4遍历文件列表
            for (File file : filesList) {
                // 创建文件输入流
                FileInputStream in = new FileInputStream(file);
                // 创建压缩流ZipEntry
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                int len;
                // 读取文件的数据到缓冲区
                while ((len = in.read(buffer)) > 0) {
                    // 将数据写入到压缩流中
                    zipOut.write(buffer, 0, len);
                }
                zipOut.closeEntry(); // 关闭当前ZipEntry
                in.close(); // 关闭文件输入流
            }
        }
        //5.5获取压缩后的字节数组
        byte[] bytes = out.toByteArray();
        //5.6将压缩后的字节数组作为Http响应的内容返回客户端
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=qrcode.zip") // 设置响应头信息
                .body(bytes);
    }
}
