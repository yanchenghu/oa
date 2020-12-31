import request from '@/utils/request'

// 获取用户详细信息
export function getlist() {
  return request({
    url: '/statistc/homepage/datadisplay',
    method: 'get'
  })
}

export function getdata() {
  return request({
    url: 'work/statistic/workresultlist',
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
