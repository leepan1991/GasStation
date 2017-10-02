import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/order/listPaged',
    method: 'get',
    data: params
  })
}

export async function create (params) {
  return request({
    url: '/manager/order/add',
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/order/delete',
    method: 'get',
    data: params
  })
}

export async function update (params) {
  return request({
    url: '/manager/order/update',
    method: 'put',
    data: params
  })
}
