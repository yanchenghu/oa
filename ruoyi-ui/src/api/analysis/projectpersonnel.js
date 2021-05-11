import request from '@/utils/request'

// 查询外派人员工资列表
export function listReturnedmoney(query) {
  return request({
    url: '/analysis/projectpersonnel/list',
    method: 'get',
    params: query
  })
}

export function getaccumulatedlist(data) {
  return request({
    url: '/analysis/returnedmoney/accumulatedProfitlist',
    method: 'get',
    params: data
  })
}
