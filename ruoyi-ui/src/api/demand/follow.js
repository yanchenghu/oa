import request from '@/utils/request'



// 查询待入项
export function listdata(query) {
  return request({
    url: '/demand/waitingentry/list',
    method: 'get',
    params:query
  })
}
// 查询简历模板
export function template() {
  return request({
    url: '/resume/template/templaList',
    method: 'get',
  })
}

export function listdatas(query) {
  return request({
    url: '/demand/waitinginterview/list',
    method: 'get',
    params:query
  })
}

// 查询公司名称
export function corpName() {
  return request({
    url: '/customer/company/listcom',
    method: 'get',
  })
}
// 查询公司名称
export function corpNames() {
  return request({
    url: '/customer/company/alllistcom',
    method: 'get',
  })
}
// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: '/system/dept/treeselect',
    method: 'get'
  })
}

// 查询需求列表
export function listFollow(query) {
  return request({
    url: '/demand/follow/list',
    method: 'get',
    params: query
  })
}
export function listFollows(query) {
  return request({
    url: '/demand/alldemand/list',
    method: 'get',
    params: query
  })
}
// 需求关闭
export function changeUserStatus(query) {
  return request({
    url: '/demand/follow/demandClosure',
    method: 'post',
    data: query
  })
}
// 需求开启
export function changeopenStatus(query) {
  return request({
    url: '/demand/follow/demandOpen',
    method: 'post',
    data: query
  })
}

// 查询需求详细
export function getFollow(id) {
  return request({
    url: '/demand/follow/' + id,
    method: 'get'
  })
}

// 新增需求
export function addFollow(data) {
  return request({
    url: '/demand/follow',
    method: 'post',
    data: data
  })
}

// 修改需求
export function updateFollow(data) {
  return request({
    url: '/demand/follow',
    method: 'put',
    data: data
  })
}
export function findnames(data) {
  return request({
    url: '/demand/follow/demandDuplicate',
    method: 'post',
    data: data
  })
}

// 删除需求
export function delFollow(id) {
  return request({
    url: '/demand/follow/' + id,
    method: 'delete'
  })
}

// 导出需求
export function exportFollow(query) {
  return request({
    url: '/demand/follow/export',
    method: 'get',
    params: query
  })
}
