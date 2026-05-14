import request from '@/utils/request'

// 查询记录工作时长列表
export function listRecord(query) {
  return request({
    url: '/system/record/list',
    method: 'get',
    params: query
  })
}

// 查询记录工作时长详细
export function getRecord(recordId) {
  return request({
    url: '/system/record/' + recordId,
    method: 'get'
  })
}

// 新增记录工作时长
export function addRecord(data) {
  return request({
    url: '/system/record',
    method: 'post',
    data: data
  })
}

// 修改记录工作时长
export function updateRecord(data) {
  return request({
    url: '/system/record',
    method: 'put',
    data: data
  })
}

// 删除记录工作时长
export function delRecord(recordId) {
  return request({
    url: '/system/record/' + recordId,
    method: 'delete'
  })
}
