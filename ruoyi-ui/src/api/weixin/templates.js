import request from '@/utils/request'

// 查询模板列表
export function listTemplates(query) {
  return request({
    url: '/weixin/templates/list',
    method: 'get',
    params: query
  })
}

// 查询模板详细
export function getTemplates(templateId) {
  return request({
    url: '/weixin/templates/' + templateId,
    method: 'get'
  })
}

// 新增模板
export function addTemplates(data) {
  return request({
    url: '/weixin/templates',
    method: 'post',
    data: data
  })
}

// 修改模板
export function updateTemplates(data) {
  return request({
    url: '/weixin/templates',
    method: 'put',
    data: data
  })
}

// 删除模板
export function delTemplates(templateId) {
  return request({
    url: '/weixin/templates/' + templateId,
    method: 'delete'
  })
}
