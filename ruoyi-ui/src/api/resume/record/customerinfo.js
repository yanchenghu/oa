import request from '@/utils/request'

// 查询简历列表
export function listRecord(query) {
  return request({
    url: '/resume/record/list',
    method: 'get',
    params: query
  })
}

// 查询简历详细
export function getRecord(customerCode) {
  return request({
    url: '/resume/record/' + customerCode,
    method: 'get'
  })
}

// 新增简历
export function addRecord(data) {
  return request({
    url: '/resume/record',
    method: 'post',
    data: data
  })
}

// 修改简历
export function updateRecord(data) {
  return request({
    url: '/resume/record',
    method: 'put',
    data: data
  })
}

// 删除简历
export function delRecord(customerCode) {
  return request({
    url: '/resume/record/' + customerCode,
    method: 'delete'
  })
}

// 导出简历
export function exportRecord(query) {
  return request({
    url: '/resume/record/export',
    method: 'get',
    params: query
  })
}
