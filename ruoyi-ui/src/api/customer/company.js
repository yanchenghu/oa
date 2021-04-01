import request from '@/utils/request'

// 查询合作公司列表
export function listCompany(query) {
  return request({
    url: '/customer/company/booslist',
    method: 'get',
    params: query
  })
}
export function listCompanys(query) {
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
// 新增合同
export function addCompany(data) {
  return request({
    url: '/customer/company/save',
    method: 'post',
    data: data
  })
}

// 新增合作公司
export function addcontract(data) {
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

// 合同查询
export function contractCompany(query) {
  return request({
    url: '/customer/company/contract',
    method: 'get',
    params: query
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
