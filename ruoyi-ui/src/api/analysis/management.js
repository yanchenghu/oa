import request from '@/utils/request'

// 查询简历列表
export function listmanagement(query) {
  return request({
    url: '/analysis/management/managementlist',
    method: 'get',
    params: query
  })
}