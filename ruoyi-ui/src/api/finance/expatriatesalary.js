import request from '@/utils/request'

// 查询外派人员工资列表
export function listExpatriatesalary(query) {
  return request({
    url: '/finance/expatriatesalary/list',
    method: 'get',
    params: query
  })
}

// 查询外派人员工资详细
export function getExpatriatesalary(id) {
  return request({
    url: '/finance/expatriatesalary/' + id,
    method: 'get'
  })
}

// 新增外派人员工资
export function addExpatriatesalary(data) {
  return request({
    url: '/finance/expatriatesalary',
    method: 'post',
    data: data
  })
}

// 修改外派人员工资
export function updateExpatriatesalary(data) {
  return request({
    url: '/finance/expatriatesalary',
    method: 'put',
    data: data
  })
}

// 删除外派人员工资
export function delExpatriatesalary(id) {
  return request({
    url: '/finance/expatriatesalary/' + id,
    method: 'delete'
  })
}

// 导出外派人员工资
export function exportExpatriatesalary(query) {
  return request({
    url: '/finance/expatriatesalary/export',
    method: 'get',
    params: query
  })
}