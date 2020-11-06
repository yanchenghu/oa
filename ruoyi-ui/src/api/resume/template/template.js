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

// 新增简历模板
export function addTemplate(data) {
  return request({
    url: '/resume/template',
    method: 'post',
    data: data
  })
}

export function changeTemplateStatus(templateId, templateStatus) {
  const data = {
    templateId,
    templateStatus
  }
  return request({
    url: '/resume/template/changeStatus',
    method: 'put',
    data: data
  })
}

// 修改简历模板
export function updateTemplate(data) {
  return request({
    url: '/resume/template',
    method: 'put',
    data: data
  })
}

// 删除简历模板
export function delTemplate(templateId) {
  return request({
    url: '/resume/template/' + templateId,
    method: 'delete'
  })
}

// 导出简历模板
export function exportTemplate(query) {
  return request({
    url: '/resume/template/export',
    method: 'get',
    params: query
  })
}
