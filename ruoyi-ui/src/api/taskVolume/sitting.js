import request from '@/utils/request'

export function getList() {
  return request({
    url: '/taskvolume/list',
    method: 'get',
  })
}
export function setdata(data) {
  return request({
    url: '/taskvolume',
    method: 'post',
    data:data
  })
}
