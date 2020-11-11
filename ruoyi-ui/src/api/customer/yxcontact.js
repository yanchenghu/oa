import request from '@/utils/request'

// 查询跟进联系内容列表
export function listYxcontact(query) {
  return request({
    url: '/customer/yxcontact/list',
    method: 'get',
    params: query
  })
}

// 查询跟进联系内容详细
export function getYxcontact(contactId) {
  return request({
    url: '/customer/yxcontact/' + contactId,
    method: 'get'
  })
}

// 新增跟进联系内容
export function addYxcontact(data) {
  return request({
    url: '/customer/yxcontact',
    method: 'post',
    data: data
  })
}

// 修改跟进联系内容
export function updateYxcontact(data) {
  return request({
    url: '/customer/yxcontact',
    method: 'put',
    data: data
  })
}

// 删除跟进联系内容
export function delYxcontact(contactId) {
  return request({
    url: '/customer/yxcontact/' + contactId,
    method: 'delete'
  })
}

// 导出跟进联系内容
export function exportYxcontact(query) {
  return request({
    url: '/customer/yxcontact/export',
    method: 'get',
    params: query
  })
}
