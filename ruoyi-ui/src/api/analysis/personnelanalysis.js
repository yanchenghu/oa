import request from '@/utils/request'

// 查询简历列表
export function listmingxi(query) {
  return request({
    url: '/analysis/personneldata/personnellist',
    method: 'get',
    params: query
  })
}

export function listbusinessllist(query) {
  return request({
    url: '/analysis/businessdata/businessllist',
    method: 'get',
    params: query
  })
}
// 查询每日详细
export function listdata(query) {
  return request({
    url: '/analysis/personneldata/everydaylist',
    method: 'get',
    params: query
  })
}

export function getbusinessData() {
  return request({
    url: '/work/statistic/rankinglist',
    method: 'get',
  })
}
export function yuangonglist() {
  return request({
    url: '/analysis/personneldata/userlist',
    method: 'get',
  })
}
