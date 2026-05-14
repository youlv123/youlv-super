import request from '@/utils/request'

// 查询物品分类列表
export function listCategory(query) {
  return request({
    url: '/system/category/list',
    method: 'get',
    params: query
  })
}

// 查询物品分类详细
export function getCategory(categoryId) {
  return request({
    url: '/system/category/' + categoryId,
    method: 'get'
  })
}

// 新增物品分类
export function addCategory(data) {
  return request({
    url: '/system/category',
    method: 'post',
    data: data
  })
}

// 修改物品分类
export function updateCategory(data) {
  return request({
    url: '/system/category',
    method: 'put',
    data: data
  })
}

// 删除物品分类
export function delCategory(categoryId) {
  return request({
    url: '/system/category/' + categoryId,
    method: 'delete'
  })
}

/**
 * 新增商品的时候，查询不在这个商品目录下的商品，让用户去挑选需要加入的商品
 * 先做成分页的吧
 *
 * @param categoryId
 * @return
 */
export function addSerachIetmByCategoryId(categoryId) {
  return request({
    url: '/system/category/addSerachIetmByCategoryId/'+ categoryId ,
    method: 'get'
  })
}

/**
 * 将物品添加进分类
 */

export function addIetmByCategoryId(data) {
  return request({
    url: '/system/category/addIetmByCategoryId' ,
    method: 'post',
    data: data
  })
}
/**
 * 根据id删除这个分类下面所有的商品信息
 *
 */
export function deleteIetmByCategoryId(data) {
  return request({
    url: '/system/category/deleteIetmByCategoryId' ,
    method: 'post',
    data: data
  })
}
/**
 * 根据id查询这个分类下面所有的商品信息
 */
export function serachIetmByCategoryId(data) {
  return request({
    url: '/system/category/serachIetmByCategoryId' ,
    method: 'post',
    data: data
  })
}
/**
 * 根据物品id查询这个绑定了哪些分类
 *
 * @param itemId
 * @return
 */
export function serachCategoryByIetmId(itemId) {
  return request({
    url: '/system/category/serachCategoryByIetmId/'+ itemId ,
    method: 'get'
  })
}
