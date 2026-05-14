import request from '@/utils/request'

// 查询企微消息推送任务列表
export function listWxTask(query) {
  return request({
    url: '/system/wxTask/list',
    method: 'get',
    params: query
  })
}

// 查询企微消息推送任务详细
export function getWxTask(wxTaskId) {
  return request({
    url: '/system/wxTask/' + wxTaskId,
    method: 'get'
  })
}

// 新增企微消息推送任务
export function addWxTask(data) {
  return request({
    url: '/system/wxTask',
    method: 'post',
    data: data
  })
}

// 修改企微消息推送任务
export function updateWxTask(data) {
  return request({
    url: '/system/wxTask',
    method: 'put',
    data: data
  })
}

// 删除企微消息推送任务
export function delWxTask(wxTaskId) {
  return request({
    url: '/system/wxTask/' + wxTaskId,
    method: 'delete'
  })
}
