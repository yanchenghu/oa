import request from '@/utils/request'

// 查询简历模板列表
export function gettemplist(query) {
  return request({
    url: '/resume/mytrackresume/list',
    method: 'get',
    params: query
  })
}

export function genzongbut(data) {
  return request({
    url: '/resume/mytrackresume/resumeTracking',
    method: 'post',
    data: data
  })
}

