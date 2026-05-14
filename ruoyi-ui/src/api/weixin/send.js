import request from '@/utils/request'

// 查询微信公众号文章发送列表
export function listSend(query) {
  return request({
    url: '/weixin/send/list',
    method: 'get',
    params: query
  })
}

// 查询微信公众号文章发送详细
export function getSend(sendId) {
  return request({
    url: '/weixin/send/' + sendId,
    method: 'get'
  })
}

// 新增微信公众号文章发送
export function addSend(data) {
  return request({
    url: '/weixin/send',
    method: 'post',
    data: data
  })
}

// 修改微信公众号文章发送
export function updateSend(data) {
  return request({
    url: '/weixin/send',
    method: 'put',
    data: data
  })
}

// 删除微信公众号文章发送
export function delSend(sendId) {
  return request({
    url: '/weixin/send/' + sendId,
    method: 'delete'
  })
}
