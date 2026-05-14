import request from '@/utils/request'

// 查询二维码列表
export function listQrcode(query) {
  return request({
    url: '/system/qrcode/list',
    method: 'get',
    params: query
  })
}

// 查询二维码详细
export function getQrcode(qrcodeId) {
  return request({
    url: '/system/qrcode/' + qrcodeId,
    method: 'get'
  })
}

// 新增二维码
export function addQrcode(data) {
  return request({
    url: '/system/qrcode',
    method: 'post',
    data: data
  })
}

// 修改二维码
export function updateQrcode(data) {
  return request({
    url: '/system/qrcode',
    method: 'put',
    data: data
  })
}

// 删除二维码
export function delQrcode(qrcodeId) {
  return request({
    url: '/system/qrcode/' + qrcodeId,
    method: 'delete'
  })
}
