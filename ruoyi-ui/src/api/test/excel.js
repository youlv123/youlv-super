import request from '@/utils/request'

export function uploadAndDownloadFile(file) {
  const formData = new FormData()
  formData.append('file', file)

  return request({
    url: '/system/vip/importOTAeasy',
    method: 'post',
    data: formData,
    responseType: 'blob',// ⚠️ 必须加上这一行，告诉 axios 这是二进制流

    // 添加以下配置以获取完整的响应头信息
    transformResponse: (data, headers) => {
      return {
        data: data,
        headers: headers
      }
    }
  })
}



export function exportOTAeasyTemplate() {
  return request({
    url: '/system/vip/exportOTAeasyTemplate', // 后端GET接口地址
    method: 'get',
    responseType: 'blob', // 关键：告诉axios这是二进制流
    transformResponse: (data, headers) => {
      // 保留原始响应头和二进制数据
      return {
        data: data,
        headers: headers
      }
    }
  })
}
