import request from '@/utils/request'

// 查询理财交易记录（记录所有买入/卖出操作，关联持仓明细）列表
export function listFinancialTransaction(query) {
  return request({
    url: '/system/financialTransaction/list',
    method: 'get',
    params: query
  })
}

// 查询理财交易记录（记录所有买入/卖出操作，关联持仓明细）详细
export function getFinancialTransaction(transactionId) {
  return request({
    url: '/system/financialTransaction/' + transactionId,
    method: 'get'
  })
}

// 新增理财交易记录（记录所有买入/卖出操作，关联持仓明细）
export function addFinancialTransaction(data) {
  return request({
    url: '/system/financialTransaction',
    method: 'post',
    data: data
  })
}

// 修改理财交易记录（记录所有买入/卖出操作，关联持仓明细）
export function updateFinancialTransaction(data) {
  return request({
    url: '/system/financialTransaction',
    method: 'put',
    data: data
  })
}

// 删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）
export function delFinancialTransaction(transactionId) {
  return request({
    url: '/system/financialTransaction/' + transactionId,
    method: 'delete'
  })
}
