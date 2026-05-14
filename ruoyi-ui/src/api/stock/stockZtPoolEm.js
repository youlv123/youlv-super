import request from '@/utils/request'

// 查询涨停板行情列表
export function listStockZtPoolEm(query) {
  return request({
    url: '/stock/stockZtPoolEm/list',
    method: 'get',
    params: query
  })
}

// 查询涨停板行情详细
export function getStockZtPoolEm(ztId) {
  return request({
    url: '/stock/stockZtPoolEm/' + ztId,
    method: 'get'
  })
}

// 新增涨停板行情
export function addStockZtPoolEm(data) {
  return request({
    url: '/stock/stockZtPoolEm',
    method: 'post',
    data: data
  })
}

// 修改涨停板行情
export function updateStockZtPoolEm(data) {
  return request({
    url: '/stock/stockZtPoolEm',
    method: 'put',
    data: data
  })
}

// 删除涨停板行情
export function delStockZtPoolEm(ztId) {
  return request({
    url: '/stock/stockZtPoolEm/' + ztId,
    method: 'delete'
  })
}
