import request from '@/utils/request'

// 查询面试题列表
export function listTopic(query) {
  return request({
    url: '/demand/topic/list',
    method: 'get',
    params: query
  })
}

// 查询面试题详细
export function getTopic(id) {
  return request({
    url: '/demand/topic/' + id,
    method: 'get'
  })
}

// 新增面试题
export function addTopic(data) {
  return request({
    url: '/demand/topic',
    method: 'post',
    data: data
  })
}

// 修改面试题
export function updateTopic(data) {
  return request({
    url: '/demand/topic',
    method: 'put',
    data: data
  })
}

// 删除面试题
export function delTopic(id) {
  return request({
    url: '/demand/topic/' + id,
    method: 'delete'
  })
}

// 导出面试题
export function exportTopic(query) {
  return request({
    url: '/demand/topic/export',
    method: 'get',
    params: query
  })
}