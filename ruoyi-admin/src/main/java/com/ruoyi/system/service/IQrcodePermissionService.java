package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.QrcodePermissionDTO;

/**
 * 二维码查看权限Service接口
 *
 * @author ruoyi
 * @date 2023-06-07
 */
public interface IQrcodePermissionService {
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
     * 批量删除二维码查看权限
     *
     * @param permissionIds 需要删除的二维码查看权限主键集合
     * @return 结果
     */
    public int deleteQrcodePermissionByPermissionIds(Long[] permissionIds);

    /**
     * 删除二维码查看权限信息
     *
     * @param permissionId 二维码查看权限主键
     * @return 结果
     */
    public int deleteQrcodePermissionByPermissionId(Long permissionId);
}
