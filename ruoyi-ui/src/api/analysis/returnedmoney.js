import request from '@/utils/request'

// 查询外派人员工资列表
export function listReturnedmoney(query) {
  return request({
    url: '/analysis/returnedmoney/list',
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
export function getsamemonthlist(data) {
  return request({
    url: '/analysis/returnedmoney/samemonthProfitlist',
    method: 'get',
    params: data
  })
}
// 累计回款列表
export function getReturnedmoney(data) {
  return request({
    url: '/analysis/returnedmoney/accumulatedCollectionlist',
    method: 'get',
    params: data
  })
}

// 累计支出列表
export function getExpenditurelist(data) {
  return request({
    url: '/analysis/returnedmoney/accumulatedExpenditurelist',
    method: 'get',
    params: data
  })
}

// 新增外派人员工资
export function addReturnedmoney(data) {
  return request({
    url: '/analysis/returnedmoney',
    method: 'post',
    data: data
  })
}

// 修改外派人员工资
export function updateReturnedmoney(data) {
  return request({
    url: '/analysis/returnedmoney',
    method: 'put',
    data: data
  })
}

// 删除外派人员工资
export function delReturnedmoney(id) {
  return request({
    url: '/analysis/returnedmoney/' + id,
    method: 'delete'
  })
}

// 导出外派人员工资
export function exportReturnedmoney(query) {
  return request({
    url: '/analysis/returnedmoney/export',
    method: 'get',
    params: query
  })
}
