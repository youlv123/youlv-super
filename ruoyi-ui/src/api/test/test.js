import request from '@/utils/request'



// 查询
export function singleQuery(data) {
  return request({
    url: '/system/test/singleQuery' ,
    method: 'post',
    data: data
  })
}


// 导出
export function batchQuery(query) {
  return request({
    url: '/system/test/batchQuery',
    method: 'get',
    params: query
  })
}






