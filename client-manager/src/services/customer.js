import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/customer/listPaged',
    method: 'get',
    data: params
  })
}

export async function create (params) {
  return request({
    url: '/manager/customer/add',
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/customer/delete',
    method: 'get',
    data: params
  })
}

export async function update (params) {
  return request({
    url: '/manager/customer/update',
    method: 'put',
    data: params
  })
}
