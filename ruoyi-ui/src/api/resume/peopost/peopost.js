import request from '@/utils/request'

// 查询简历列表
export function listRecord(query) {
  return request({
    url: '/resume/peopost/list',
    method: 'get',
    params: query
  })
}

// 查询简历详细
export function getRecord(customerCode) {
  return request({
    url: '/resume/peopost/' + customerCode,
    method: 'get'
  })
}
// 查询需求列表
export function listFollow(query) {
  return request({
    url: '/demand/binding/list1',
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
// 抢占
export function addRecord(data) {
  return request({
    url: '/resume/record/rob',
    method: 'post',
    data: data
  })
}

// 修改简历
export function updateRecord(data) {
  return request({
    url: '/resume/peopost',
    method: 'put',
    data: data
  })
}

// 删除简历
export function delRecord(customerCode) {
  return request({
    url: '/resume/peopost/' + customerCode,
    method: 'delete'
  })
}

// 导出简历
export function exportRecord(query) {
  return request({
    url: '/resume/peopost/export',
    method: 'get',
    params: query
  })
}
