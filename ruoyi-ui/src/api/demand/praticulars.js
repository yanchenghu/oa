import request from '@/utils/request'


export function gettemplist(data) {
  return request({
    url: '/demand/binding/demandDetailsList',
    method: 'post',
    data: data
  })
}
// 查询简历模板
export function template() {
  return request({
    url: '/resume/template/templaList',
    method: 'get',
  })
}

// 查询需求详细
export function getFollow(id) {
  return request({
    url: '/demand/follow/' + id,
    method: 'get'
  })
}

// 查询需求详细
export function submitstart(data) {
  return request({
    url: '/demand/binding/demandResumeTrack',
    method: 'post',
    data:data
  })
}

// 获取简历模板
export function addFollow() {
  return request({
    url: '/demand/binding/myRobresume',
    method: 'get',
  })
}
// 绑定简历
export function delFollow(id) {
  return request({
    url: '/demand/binding/resumeBingDemand',
    method: 'post',
    data:id
  })
}
// 查询入项信息
export function getInputInformation(id) {
  return request({
    url: '/demand/binding/getInputInformation',
    method: 'post',
    data:id
  })
}
// 入项
export function entryPersonnel(data) {
  return request({  
    url: '/demand/binding/entryPersonnel',
    method: 'post',
    data:data
  })
}
