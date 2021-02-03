import request from '@/utils/request'

// 查询外派人员工资列表
export function listInsidesalary(query) {
  return request({
    url: '/finance/insidesalary/list',
    method: 'get',
    params: query
  })
}

// 查询外派人员工资详细
export function getInsidesalary(id) {
  return request({
    url: '/finance/insidesalary/' + id,
    method: 'get'
  })
}

// 新增外派人员工资
export function addInsidesalary(data) {
  return request({
    url: '/finance/insidesalary',
    method: 'post',
    data: data
  })
}

// 修改外派人员工资
export function updateInsidesalary(data) {
  return request({
    url: '/finance/insidesalary',
    method: 'put',
    data: data
  })
}

// 删除外派人员工资
export function delInsidesalary(id) {
  return request({
    url: '/finance/insidesalary/' + id,
    method: 'delete'
  })
}

// 导出外派人员工资
export function exportInsidesalary(query) {
  return request({
    url: '/finance/insidesalary/export',
    method: 'get',
    params: query
  })
}