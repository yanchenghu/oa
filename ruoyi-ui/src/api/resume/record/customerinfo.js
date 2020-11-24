import request from '@/utils/request'


// 删除工作经历
export function workDel(query) {
  return request({
    url: '/resume/record/workDel',
    method: 'get',
    params: query
  })
}
export function projdeDel(query) {
  return request({
    url: '/resume/record/projdeDel',
    method: 'get',
    params: query
  })
}
export function educaDel(query) {
  return request({
    url: '/resume/record/educaDel',
    method: 'get',
    params: query
  })
}


// 修改信息
export function handupdata(data) {
  return request({
    url: '/resume/record/resumeUpdate',
    method: 'post',
    data:data
  })
}

// 手动添加简历
export function handInsert(data) {
  return request({
    url: 'resume/record/resumeInsert',
    method: 'post',
    data:data
  })
}

// 查询简历列表
export function listRecord(query) {
  return request({
    url: '/resume/record/listbynatel',
    method: 'get',
    params: query
  })
}

//解析简历
export function jiexi(file) {
  return request({
    url: 'resume/record/analysisResume',
    method: 'post',
    data:file
  })
}

// 查询简历详细
export function getRecord(customerCode) {
  return request({
    url: '/resume/record/query',
    method: 'post',
    data:customerCode
  })
}

// 新增简历
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
