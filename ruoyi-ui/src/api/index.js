import request from '@/utils/request'

// 获取用户详细信息
export function getList() {
  return request({
    url: '/statistc/homepage/datadisplay',
    method: 'get'
  })
}
