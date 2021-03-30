import request from '@/utils/request'

// 查询审核编辑后的面试题列表
export function listAuditeditor(query) {
  return request({
    url: '/demand/auditeditor/list',
    method: 'get',
    params: query
  })
}
// 查询审核编辑后的面试题列表
export function listExtopiclist(query) {
  return request({
    url: '/demand/auditeditor/extopiclist',
    method: 'get',
    params: query
  })
}
// 查询审核编辑后的面试题详细
export function getAuditeditor(data) {
  return request({
    url: '/demand/auditeditor/querydemand',
    method: 'post',
    data:data,
  })
}
// 查询审核编辑后的面试题详细
export function afterbinding(data) {
  return request({
    url: '/demand/auditeditor/afterbinding',
    method: 'post',
    data:data,
  })
}

// 查询公司名称
export function corpName() {
  return request({
    url: '/customer/company/listcom',
    method: 'get',
  })
}
// 新增审核编辑后的面试题
export function addAuditeditor(data) {
  return request({
    url: '/demand/auditeditor/addauditeditor',
    method: 'post',
    data: data
  })
}
export function addaudbindingdemand(data) {
  return request({
    url: '/demand/auditeditor/addaudbindingdemand',
    method: 'post',
    data: data
  })
}

// 修改审核编辑后的面试题
export function updateAuditeditor(data) {
  return request({
    url: '/demand/auditeditor/edittopic',
    method: 'put',
    data: data
  })
}

// 删除审核编辑后的面试题
export function delAuditeditor(auditedId) {
  return request({
    url: '/demand/auditeditor/' + auditedId,
    method: 'delete'
  })
}

// 导出审核编辑后的面试题
export function exportAuditeditor(query) {
  return request({
    url: '/demand/auditeditor/export',
    method: 'get',
    params: query
  })
}
