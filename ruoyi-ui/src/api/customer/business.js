import request from '@/utils/request'

// 查询营销录入公司列表
export function listbusiness(query) {
  return request({
    url: '/customer/business/list',
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
export function exportYxdemand(data) {
  return request({
    url: '/customer/business/turnCustomers',
    method: 'post',
    data: data
  })
}


