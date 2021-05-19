import request from '@/utils/request'


export function gettemplist(data) {
  return request({
    url: '/demand/binding/demandDetailsList',
    method: 'post',
    data: data
  })
}

export function updateMarDemandresume(data) {
  return request({
    url: '/demand/binding/updateMarDemandresume',
    method: 'post',
    data:data
  })
}
// 查询简历模板
export function template(data) {
  return request({
    url: '/resume/template/templaList/',
    method: 'get',
  })
}

export function resource(data) {
  return request({
    url: '/common/download/resource',
    method: 'get',
    params:data,
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
export function submitstarts(data) {
  return request({
    url: '/demand/binding/batchOperation',
    method: 'post',
    data:data
  })
}
// 重置状态
export function chongzhizhuang(data) {
  return request({
    url: '/demand/binding/batchResumeStatus',
    method: 'post',
    data:data
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
export function addFollow(data) {
  return request({
    url: '/demand/binding/myRobresume',
    method: 'post',
    data:data
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
