import request from '@/utils/request'

// 获取用户详细信息
export function getlist() {
  return request({
    url: '/statistc/homepage/datadisplay',
    method: 'get'
  })
}

export function getdata(data) {
  return request({
    url: 'work/statistic/workresultlist',
    method: 'post',
    data:data
  })
}

export function partjob() {
  return request({
    url: '/statistc/homepage/partjob',
    method: 'get'
  })
}
export function getmsg(data) {
  return request({
    url: '/statistc/homepage/viewdetailsplay',
    method: 'post',
    data:data
  })
}
export function getbusmsg(data) {
  return request({
    url: '/statistc/homepage/businessdetailsplay',
    method: 'post',
    data:data
  })
}

export function getlists(data) {
  return request({
    url: '/statistc/homepage/reservedisplay',
    method: 'get',
  })
}

export function getbusinessData(data) {
  return request({
    url: 'statistc/homepage/businessData',
    method: 'get',
  })
}
