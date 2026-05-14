package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.QrcodePermissionMapper;
import com.ruoyi.system.domain.QrcodePermissionDTO;
import com.ruoyi.system.service.IQrcodePermissionService;

/**
 * 二维码查看权限Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-07
 */
@Service
public class QrcodePermissionServiceImpl implements IQrcodePermissionService {
    @Autowired
    private QrcodePermissionMapper qrcodePermissionMapper;

    /**
     * 查询二维码查看权限
     *
     * @param permissionId 二维码查看权限主键
     * @return 二维码查看权限
     */
    @Override
    public QrcodePermissionDTO selectQrcodePermissionByPermissionId(Long permissionId) {
        return qrcodePermissionMapper.selectQrcodePermissionByPermissionId(permissionId);
    }

    /**
     * 查询二维码查看权限列表
     *
     * @param qrcodePermissionDTO 二维码查看权限
     * @return 二维码查看权限
     */
    @Override
    public List<QrcodePermissionDTO> selectQrcodePermissionList(QrcodePermissionDTO qrcodePermissionDTO) {
        return qrcodePermissionMapper.selectQrcodePermissionList(qrcodePermissionDTO);
    }

    /**
     * 新增二维码查看权限
     *
     * @param qrcodePermissionDTO 二维码查看权限
     * @return 结果
     */
    @Override
    public int insertQrcodePermission(QrcodePermissionDTO qrcodePermissionDTO) {
        return qrcodePermissionMapper.insertQrcodePermission(qrcodePermissionDTO);
    }

    /**
     * 修改二维码查看权限
     *
     * @param qrcodePermissionDTO 二维码查看权限
     * @return 结果
     */
    @Override
    public int updateQrcodePermission(QrcodePermissionDTO qrcodePermissionDTO) {
        return qrcodePermissionMapper.updateQrcodePermission(qrcodePermissionDTO);
    }

    /**
     * 批量删除二维码查看权限
     *
     * @param permissionIds 需要删除的二维码查看权限主键
     * @return 结果
     */
    @Override
    public int deleteQrcodePermissionByPermissionIds(Long[] permissionIds) {
        return qrcodePermissionMapper.deleteQrcodePermissionByPermissionIds(permissionIds);
    }

    /**
     * 删除二维码查看权限信息
     *
     * @param permissionId 二维码查看权限主键
     * @return 结果
     */
    @Override
    public int deleteQrcodePermissionByPermissionId(Long permissionId) {
        return qrcodePermissionMapper.deleteQrcodePermissionByPermissionId(permissionId);
    }
}
