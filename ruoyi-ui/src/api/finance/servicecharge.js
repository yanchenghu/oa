import request from '@/utils/request'

// 查询服务费核算请求书列表
export function listServicecharge(query) {
  return request({
    url: '/finance/servicecharge/list',
    method: 'get',
    params: query
  })
}

// 查询服务费核算请求书详细
export function getServicecharge(id) {
  return request({
    url: '/finance/servicecharge/' + id,
    method: 'get'
  })
}

// 新增服务费核算请求书
export function addServicecharge(data) {
  return request({
    url: '/finance/servicecharge',
    method: 'post',
    data: data
  })
}

// 开票
export function kaipServicecharge(data) {
  return request({
    url: '/finance/servicecharge/batchPayment',
    method: 'post',
    data: data
  })
}

// 修改服务费核算请求书
export function updateServicecharge(data) {
  return request({
    url: '/finance/servicecharge',
    method: 'put',
    data: data
  })
}

// 删除服务费核算请求书
export function delServicecharge(data) {
  return request({
    url: '/finance/servicecharge/batchBilling',
    method: 'post',
    data:data,
  })
}

// 导出服务费核算请求书
export function exportServicecharge(query) {
  return request({
    url: '/finance/servicecharge/export',
    method: 'get',
    params: query
  })
}
