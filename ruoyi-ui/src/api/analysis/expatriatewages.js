import request from '@/utils/request'

// 查询简历列表
export function listexpatriatewages(query) {
  return request({
    url: '/analysis/expatriatewages/list',
    method: 'get',
    params: query
  })
}

// 查询简历详细
export function getexpatriatewages(data) {
  return request({
    url: '/analysis/expatriatewages/samemonthProfitlist',
    method: 'get',
    params:data,
  })
}
// 查询需求列表
export function listFollow(query) {
  return request({
    url: '/analysis/expatriatewages/comlist',
    method: 'get',
    params: query
  })
}
// 导出简历
export function exportexpatriatewages(query) {
  return request({
    url: '/resume/peopost/export',
    method: 'get',
    params: query
  })
}
