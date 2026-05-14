import request from '@/utils/request'

// 查询理财持仓明细（每笔买入独立记录，FIFO计算核心）列表
export function listFundPositionDetail(query) {
  return request({
    url: '/system/fundPositionDetail/list',
    method: 'get',
    params: query
  })
}

// 查询理财持仓明细（每笔买入独立记录，FIFO计算核心）详细
export function getFundPositionDetail(fundDetailId) {
  return request({
    url: '/system/fundPositionDetail/' + fundDetailId,
    method: 'get'
  })
}

// 新增理财持仓明细（每笔买入独立记录，FIFO计算核心）
export function addFundPositionDetail(data) {
  return request({
    url: '/system/fundPositionDetail',
    method: 'post',
    data: data
  })
}

// 修改理财持仓明细（每笔买入独立记录，FIFO计算核心）
export function updateFundPositionDetail(data) {
  return request({
    url: '/system/fundPositionDetail',
    method: 'put',
    data: data
  })
}

// 删除理财持仓明细（每笔买入独立记录，FIFO计算核心）
export function delFundPositionDetail(fundDetailId) {
  return request({
    url: '/system/fundPositionDetail/' + fundDetailId,
    method: 'delete'
  })
}
