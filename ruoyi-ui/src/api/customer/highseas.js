import request from '@/utils/request'

// 查询营销录入公司列表
export function listYxdemand(query) {
  return request({
    url: '/customer/highseas/list',
    method: 'get',
    params: query
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
    url: '/customer/highseas',
    method: 'post',
    data: data
  })
}

// 修改营销录入公司
export function see(data) {
  return request({
    url: '/customer/highseas',
    method: 'put',
    data: data
  })
}

// 删除营销录入公司
export function delYxdemand(entryId) {
  return request({
    url: '/customer/highseas/' + entryId,
    method: 'delete'
  })
}

// 导出营销录入公司
export function exportYxdemand(query) {
  return request({
    url: '/customer/highseas/export',
    method: 'get',
    params: query
  })
}

// 营销抢占
export function yxhandrob(data) {
  return request({
    url: '/customer/yxdemand/rebMarByEnId',
    method: 'post',
    data: data
  })
}
// 商务抢占
export function swhandrob(data) {
  return request({
    url: '/customer/business/rebBusByEnId ',
    method: 'post',
    data: data
  })
}
