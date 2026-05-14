import request from '@/utils/request'

// 查询理财汇总列表
export function listFinancialTotal(query) {
  return request({
    url: '/system/financialTotal/list',
    method: 'get',
    params: query
  })
}

// 查询理财汇总详细
export function getFinancialTotal(financialTotalId) {
  return request({
    url: '/system/financialTotal/' + financialTotalId,
    method: 'get'
  })
}

// 新增理财汇总
export function addFinancialTotal(data) {
  return request({
    url: '/system/financialTotal',
    method: 'post',
    data: data
  })
}

// 修改理财汇总
export function updateFinancialTotal(data) {
  return request({
    url: '/system/financialTotal',
    method: 'put',
    data: data
  })
}

// 删除理财汇总
export function delFinancialTotal(financialTotalId) {
  return request({
    url: '/system/financialTotal/' + financialTotalId,
    method: 'delete'
  })
}
