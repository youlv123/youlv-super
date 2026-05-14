import request from '@/utils/request'

// 查询股票交割单列表
export function listStock(query) {
  return request({
    url: '/stock/stock/list',
    method: 'get',
    params: query
  })
}

// 查询股票交割单详细
export function getStock(stockId) {
  return request({
    url: '/stock/stock/' + stockId,
    method: 'get'
  })
}

// 新增股票交割单
export function addStock(data) {
  return request({
    url: '/stock/stock',
    method: 'post',
    data: data
  })
}

// 修改股票交割单
export function updateStock(data) {
  return request({
    url: '/stock/stock',
    method: 'put',
    data: data
  })
}

// 删除股票交割单
export function delStock(stockId) {
  return request({
    url: '/stock/stock/' + stockId,
    method: 'delete'
  })
}
