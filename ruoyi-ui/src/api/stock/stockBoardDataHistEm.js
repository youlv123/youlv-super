import request from '@/utils/request'

// 查询板块数据历史列表
export function listStockBoardDataHistEm(query) {
  return request({
    url: '/system/stockBoardDataHistEm/list',
    method: 'get',
    params: query
  })
}

// 查询板块数据历史详细
export function getStockBoardDataHistEm(boardDataHistId) {
  return request({
    url: '/system/stockBoardDataHistEm/' + boardDataHistId,
    method: 'get'
  })
}

// 新增板块数据历史
export function addStockBoardDataHistEm(data) {
  return request({
    url: '/system/stockBoardDataHistEm',
    method: 'post',
    data: data
  })
}

// 修改板块数据历史
export function updateStockBoardDataHistEm(data) {
  return request({
    url: '/system/stockBoardDataHistEm',
    method: 'put',
    data: data
  })
}

// 删除板块数据历史
export function delStockBoardDataHistEm(boardDataHistId) {
  return request({
    url: '/system/stockBoardDataHistEm/' + boardDataHistId,
    method: 'delete'
  })
}
