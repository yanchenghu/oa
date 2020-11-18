import request from '@/utils/request'

// 查询合作公司列表
export function listCompany(query) {
  return request({
    url: '/customer/company/list',
    method: 'get',
    params: query
  })
}

// 查询合作公司详细
export function getCompany(corpCode) {
  return request({
    url: '/customer/company/' + corpCode,
    method: 'get'
  })
}

// 新增合作公司
export function addCompany(data) {
  return request({
    url: '/customer/company',
    method: 'post',
    data: data
  })
}

// 修改合作公司
export function updateCompany(data) {
  return request({
    url: '/customer/company',
    method: 'put',
    data: data
  })
}

// 删除合作公司
export function delCompany(corpCode) {
  return request({
    url: '/customer/company/' + corpCode,
    method: 'delete'
  })
}

// 导出合作公司
export function exportCompany(query) {
  return request({
    url: '/customer/company/export',
    method: 'get',
    params: query
  })
}