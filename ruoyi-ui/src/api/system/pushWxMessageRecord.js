import request from '@/utils/request'

// 查询企业微信推送消息记录列表
export function listPushWxMessageRecord(query) {
  return request({
    url: '/system/pushWxMessageRecord/list',
    method: 'get',
    params: query
  })
}

// 查询企业微信推送消息记录详细
export function getPushWxMessageRecord(pushId) {
  return request({
    url: '/system/pushWxMessageRecord/' + pushId,
    method: 'get'
  })
}

// 新增企业微信推送消息记录
export function addPushWxMessageRecord(data) {
  return request({
    url: '/system/pushWxMessageRecord',
    method: 'post',
    data: data
  })
}

// 修改企业微信推送消息记录
export function updatePushWxMessageRecord(data) {
  return request({
    url: '/system/pushWxMessageRecord',
    method: 'put',
    data: data
  })
}

// 删除企业微信推送消息记录
export function delPushWxMessageRecord(pushId) {
  return request({
    url: '/system/pushWxMessageRecord/' + pushId,
    method: 'delete'
  })
}
