import request from '@/utils/request'

// 查询物品信息列表
export function listInformation(query) {
  return request({
    url: '/system/information/list',
    method: 'get',
    params: query
  })
}

// 查询物品信息详细
export function getInformation(itemId) {
  return request({
    url: '/system/information/' + itemId,
    method: 'get'
  })
}

// 新增物品信息
export function addInformation(data) {
  return request({
    url: '/system/information',
    method: 'post',
    data: data
  })
}

// 修改物品信息
export function updateInformation(data) {
  return request({
    url: '/system/information',
    method: 'put',
    data: data
  })
}

// 删除物品信息
export function delInformation(itemId) {
  return request({
    url: '/system/information/' + itemId,
    method: 'delete'
  })
}
// 上传图片
export function upload(data) {
  return request({
    url: '/system/information/upload',
    method: 'post',
    contentType: false,
    headers: {
      'Content-Type': 'multipart/form-data',
      credentials: 'same-origin'
    },
    data: data
  })
}
