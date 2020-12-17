import request from '@/utils/request'

// 查询入项列表
export function getlist(query) {
  return request({
    url: '/perentry/perflow/list',
    method: 'get',
    params: query
  })
}
export function getoutlist(query) {
  return request({
    url: '/perentry/perflow/outItemlist',
    method: 'get',
    params: query
  })
}
export function getendlist(query) {
  return request({
    url: '/perentry/perflow/entryDetail',
    method: 'get',
    params: query
  })
}