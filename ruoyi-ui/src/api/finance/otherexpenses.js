import request from '@/utils/request'

// 查询每月公司其他住处费用列表
export function listOtherexpenses(query) {
  return request({
    url: '/finance/otherexpenses/list',
    method: 'get',
    params: query
  })
}

// 查询每月公司其他住处费用详细
export function getOtherexpenses(id) {
  return request({
    url: '/finance/otherexpenses/' + id,
    method: 'get'
  })
}

// 新增每月公司其他住处费用
export function addOtherexpenses(data) {
  return request({
    url: '/finance/otherexpenses',
    method: 'post',
    data: data
  })
}


// 修改每月公司其他住处费用
export function updateOtherexpenses(data) {
  return request({
    url: '/finance/otherexpenses',
    method: 'put',
    data: data
  })
}

// 删除每月公司其他住处费用
export function delOtherexpenses(id) {
  return request({
    url: '/finance/otherexpenses/' + id,
    method: 'delete'
  })
}

// 导出每月公司其他住处费用
export function exportOtherexpenses(query) {
  return request({
    url: '/finance/otherexpenses/export',
    method: 'get',
    params: query
  })
}
