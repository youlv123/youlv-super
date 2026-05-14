import request from '@/utils/request'

// 查询微信公众号文章列表
export function listArticle(query) {
  return request({
    url: '/weixin/article/list',
    method: 'get',
    params: query
  })
}

// 查询微信公众号文章详细
export function getArticle(articleId) {
  return request({
    url: '/weixin/article/' + articleId,
    method: 'get'
  })
}

// 新增微信公众号文章
export function addArticle(data) {
  return request({
    url: '/weixin/article',
    method: 'post',
    data: data
  })
}

// 修改微信公众号文章
export function updateArticle(data) {
  return request({
    url: '/weixin/article',
    method: 'put',
    data: data
  })
}

// 删除微信公众号文章
export function delArticle(articleId) {
  return request({
    url: '/weixin/article/' + articleId,
    method: 'delete'
  })
}
