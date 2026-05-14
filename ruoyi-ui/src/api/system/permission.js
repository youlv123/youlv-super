import request from '@/utils/request'

// 查询二维码查看权限列表
export function listPermission(query) {
  return request({
    url: '/system/permission/list',
    method: 'get',
    params: query
  })
}

// 查询二维码查看权限详细
export function getPermission(permissionId) {
  return request({
    url: '/system/permission/' + permissionId,
    method: 'get'
  })
}

// 新增二维码查看权限
export function addPermission(data) {
  return request({
    url: '/system/permission',
    method: 'post',
    data: data
  })
}

// 修改二维码查看权限
export function updatePermission(data) {
  return request({
    url: '/system/permission',
    method: 'put',
    data: data
  })
}

// 删除二维码查看权限
export function delPermission(permissionId) {
  return request({
    url: '/system/permission/' + permissionId,
    method: 'delete'
  })
}
