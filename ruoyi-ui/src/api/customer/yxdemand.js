import request from '@/utils/request'

// 查询营销录入公司列表
export function listYxdemand(query) {
  return request({
    url: '/customer/yxdemand/list',
    method: 'get',
    params: query
  })
}
export function findnames(data) {
  return request({
    url: '/customer/yxdemand/yxdemByName',
    method: 'post',
    data: data
  })
}
// 移交
export function turnover(data) {
  return request({
    url: '/customer/yxdemand/transfer',
    method: 'post',
    data: data
  })
}
// 发布跟踪记录
export function release(data) {
  return request({
    url: '/customer/yxdemand/release',
    method: 'post',
    data: data
  })
}
// 查询营销录入公司详细
export function getYxdemand(entryId) {
  return request({
    url: '/customer/yxdemand/' + entryId,
    method: 'get'
  })
}

// 新增营销录入公司
export function addYxdemand(data) {
  return request({
    url: '/customer/yxdemand',
    method: 'post',
    data: data
  })
}

// 修改营销录入公司
export function see(data) {
  return request({
    url: '/customer/yxdemand',
    method: 'put',
    data: data
  })
}

// 删除营销录入公司
export function delYxdemand(entryId) {
  return request({
    url: '/customer/yxdemand/' + entryId,
    method: 'delete'
  })
}

// 导出营销录入公司
export function exportYxdemand(query) {
  return request({
    url: '/customer/yxdemand/export',
    method: 'get',
    params: query
  })
}



