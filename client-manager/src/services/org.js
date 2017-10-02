import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/org/listPaged',
    method: 'get',
    data: params
  })
}

export async function listAll (params) {
  return request({
    url: '/manager/org/listAll',
    method: 'get',
    data: params
  })
}

export async function create (params) {
  return request({
    url: '/manager/org/add',
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/org/delete',
    method: 'get',
    data: params
  })
}

export async function update (params) {
  return request({
    url: '/manager/org/update',
    method: 'put',
    data: params
  })
}
