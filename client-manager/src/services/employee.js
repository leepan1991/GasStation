import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/employee/listPaged',
    method: 'get',
    data: params
  })
}

export async function create (params) {
  return request({
    url: '/manager/employee/add',
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/employee/delete',
    method: 'get',
    data: params
  })
}

export async function update (params) {
  return request({
    url: '/manager/employee/update',
    method: 'put',
    data: params
  })
}
