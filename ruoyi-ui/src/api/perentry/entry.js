import request from '@/utils/request'

// 查询入项列表
export function listEntry(query) {
  return request({
    url: '/perentry/entry/list',
    method: 'get',
    params: query
  })
}

// 查询入项详细
export function getEntry(id) {
  return request({
    url: '/perentry/entry/entryinfor/' + id,
    method: 'get'
  })
}

// 新增入项
export function addEntry(data) {
  return request({
    url: '/perentry/entry',
    method: 'post',
    data: data
  })
}

// 修改入项
export function updateEntry(data) {
  return request({
    url: '/perentry/entry',
    method: 'put',
    data: data
  })
}

// 删除入项
export function delEntry(id) {
  return request({
    url: '/perentry/entry/' + id,
    method: 'delete'
  })
}

// 导出入项
export function exportEntry(query) {
  return request({
    url: '/perentry/entry/export',
    method: 'get',
    params: query
  })
}
