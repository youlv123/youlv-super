package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.QrcodePermissionDTO;
import org.springframework.stereotype.Repository;

/**
 * 二维码查看权限Mapper接口
 *
 * @author ruoyi
 * @date 2023-06-07
 */
@Repository
public interface QrcodePermissionMapper {
    /**
     * 查询二维码查看权限
     *
     * @param permissionId 二维码查看权限主键
     * @return 二维码查看权限
     */
    public QrcodePermissionDTO selectQrcodePermissionByPermissionId(Long permissionId);

    /**
     * 查询二维码查看权限列表
     *
     * @param qrcodePermissionDTO 二维码查看权限
     * @return 二维码查看权限集合
     */
    public List<QrcodePermissionDTO> selectQrcodePermissionList(QrcodePermissionDTO qrcodePermissionDTO);

    /**
     * 新增二维码查看权限
     *
     * @param qrcodePermissionDTO 二维码查看权限
     * @return 结果
     */
    public int insertQrcodePermission(QrcodePermissionDTO qrcodePermissionDTO);

    /**
     * 修改二维码查看权限
     *
     * @param qrcodePermissionDTO 二维码查看权限
     * @return 结果
     */
    public int updateQrcodePermission(QrcodePermissionDTO qrcodePermissionDTO);

    /**
     * 删除二维码查看权限
     *
     * @param permissionId 二维码查看权限主键
     * @return 结果
     */
    public int deleteQrcodePermissionByPermissionId(Long permissionId);

    /**
     * 批量删除二维码查看权限
     *
     * @param permissionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQrcodePermissionByPermissionIds(Long[] permissionIds);
}
