package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.QrcodeDTO;
import org.springframework.stereotype.Repository;

/**
 * 二维码Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-28
 */
@Repository
public interface QrcodeMapper {
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
     * 删除二维码
     *
     * @param qrcodeId 二维码主键
     * @return 结果
     */
    public int deleteQrcodeByQrcodeId(Long qrcodeId);

    /**
     * 批量删除二维码
     *
     * @param qrcodeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQrcodeByQrcodeIds(Long[] qrcodeIds);
}
