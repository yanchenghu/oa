import request from '@/utils/request'

// 查询需求列表
export function listFollow(query) {
  return request({
    url: '/demand/follow/list',
    method: 'get',
    params: query
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