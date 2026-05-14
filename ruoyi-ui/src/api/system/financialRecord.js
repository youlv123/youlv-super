import request from '@/utils/request'

// 查询理财记录列表
export function listFinancialRecord(query) {
  return request({
    url: '/system/financialRecord/list',
    method: 'get',
    params: query
  })
}

// 查询理财记录详细
export function getFinancialRecord(financialRecordId) {
  return request({
    url: '/system/financialRecord/' + financialRecordId,
    method: 'get'
  })
}

// 新增理财记录
export function addFinancialRecord(data) {
  return request({
    url: '/system/financialRecord',
    method: 'post',
    data: data
  })
}

// 修改理财记录
export function updateFinancialRecord(data) {
  return request({
    url: '/system/financialRecord',
    method: 'put',
    data: data
  })
}

// 删除理财记录
export function delFinancialRecord(financialRecordId) {
  return request({
    url: '/system/financialRecord/' + financialRecordId,
    method: 'delete'
  })
}
