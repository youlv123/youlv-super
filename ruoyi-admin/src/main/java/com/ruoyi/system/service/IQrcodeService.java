package com.ruoyi.system.service;

import java.io.IOException;
import java.util.List;

import com.google.zxing.WriterException;
import com.ruoyi.system.domain.QrcodeDTO;
import org.springframework.http.ResponseEntity;

/**
 * 二维码Service接口
 * 
 * @author ruoyi
 * @date 2023-05-28
 */
public interface IQrcodeService 
{
    /**
     * 查询二维码
     * 
     * @param qrcodeId 二维码主键
     * @return 二维码
     */
    public QrcodeDTO selectQrcodeByQrcodeId(Long qrcodeId);

    /**
     * 查询二维码列表
     * 
     * @param qrcodeDTO 二维码
     * @return 二维码集合
     */
    public List<QrcodeDTO> selectQrcodeList(QrcodeDTO qrcodeDTO);

    /**
     * 新增二维码
     * 
     * @param qrcodeDTO 二维码
     * @return 结果
     */
    public int insertQrcode(QrcodeDTO qrcodeDTO);

    /**
     * 修改二维码
     * 
     * @param qrcodeDTO 二维码
     * @return 结果
     */
    public int updateQrcode(QrcodeDTO qrcodeDTO);

    /**
     * 批量删除二维码
     * 
     * @param qrcodeIds 需要删除的二维码主键集合
     * @return 结果
     */
    public int deleteQrcodeByQrcodeIds(Long[] qrcodeIds);

    /**
     * 删除二维码信息
     * 
     * @param qrcodeId 二维码主键
     * @return 结果
     */
    public int deleteQrcodeByQrcodeId(Long qrcodeId);

    /**
     * 根据前端传来的数量，批量生成二维码
     * @param num
     * @return
     */
    ResponseEntity<byte[]> createBatch(int num) throws IOException, WriterException;
}
