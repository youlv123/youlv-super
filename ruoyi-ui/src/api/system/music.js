import request from '@/utils/request'

// 查询背景音乐列表
export function listMusic(query) {
  return request({
    url: '/system/music/list',
    method: 'get',
    params: query
  })
}

// 查询背景音乐详细
export function getMusic(musicId) {
  return request({
    url: '/system/music/' + musicId,
    method: 'get'
  })
}

// 新增背景音乐
export function addMusic(data) {
  return request({
    url: '/system/music',
    method: 'post',
    data: data
  })
}

// 修改背景音乐
export function updateMusic(data) {
  return request({
    url: '/system/music',
    method: 'put',
    data: data
  })
}

// 删除背景音乐
export function delMusic(musicId) {
  return request({
    url: '/system/music/' + musicId,
    method: 'delete'
  })
}
