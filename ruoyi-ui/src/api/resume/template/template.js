import request from '@/utils/request'

// 查询简历模板列表
export function listTemplate(query) {
  return request({
    url: '/resume/template/list',
    method: 'get',
    params: query
  })
}



// 查询简历模板详细
export function getTemplate(templateId) {
  return request({
    url: '/resume/template/' + templateId,
    method: 'get'
  })
}




// 预览
export function preview(templateId) {
  return request({
    url: '/resume/template/preview/' + templateId,
    method: 'get'
  })
}






