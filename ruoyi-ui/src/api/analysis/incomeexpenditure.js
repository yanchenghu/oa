import request from '@/utils/request'

// 查询简历列表
export function listincomeexpenditure(query) {
  return request({
    url: '/analysis/incomeexpenditure/list',
    method: 'get',
    params: query
  })
}