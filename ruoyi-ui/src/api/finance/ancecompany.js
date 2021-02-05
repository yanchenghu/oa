import request from '@/utils/request'

// 查询每月公司其他住处费用列表
export function listAncecompany(query) {
  return request({
    url: '/finance/ancecompany/list',
    method: 'get',
    params: query
  })
}
export function listMonitoring(query) {
  return request({
    url: '/finance/ancecompany/listMonitoring',
    method: 'get',
    params: query
  })
}
export function gethistory(data) {
  return request({
    url: '/finance/ancecompany/selAncecontact',
    method: 'post',
    data: data
  })
}
// 查询每月公司其他住处费用详细
export function getAncecompany(id) {
  return request({
    url: '/finance/ancecompany/' + id,
    method: 'get'
  })
}
export function getMonitoring(id) {
  return request({
    url: '/finance/ancecompany/gettMon/' + id,
    method: 'get'
  })
}

// 新增每月公司其他住处费用
export function addAncecompany(data) {
  return request({
    url: '/finance/ancecompany',
    method: 'post',
    data: data
  })
}
// 新增每月公司其他住处费用
export function addMonitoring(data) {
  return request({
    url: '/finance/ancecompany/addMonitoring',
    method: 'post',
    data: data
  })
}
// 修改每月公司其他住处费用
export function updateAncecompany(data) {
  return request({
    url: 'finance/ancecompany/addAncecontact',
    method: 'post',
    data: data
  })
}
export function updateMonitoring(data) {
  return request({
    url: 'finance/ancecompany/editMonitoring',
    method: 'put',
    data: data
  })
}

// 删除每月公司其他住处费用
export function delAncecompany(id) {
  return request({
    url: '/finance/ancecompany/' + id,
    method: 'delete'
  })
}

export function delMonitorid(id) {
  return request({
    url: '/finance/ancecompany/reMonitorid/' + id,
    method: 'delete'
  })
}

// 导出每月公司其他住处费用
export function exportAncecompany(query) {
  return request({
    url: '/finance/ancecompany/export',
    method: 'get',
    params: query
  })
}
