import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/permission/listPaged',
    method: 'get',
    data: params
  })
}

export async function selectLoopMenu (params) {
  return request({
    url: '/manager/permission/selectLoopMenu',
    method: 'get',
    data: params
  })
}

export async function selectInfoByUserId (params) {
  return request({
    url: '/manager/permission/selectInfoByUserId',
    method: 'get',
    data: params
  })
}

export async function create (params) {
  return request({
    url: '/manager/permission/add',
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/permission/delete',
    method: 'get',
    data: params
  })
}

export async function update (params) {
  return request({
    url: '/manager/permission/update',
    method: 'put',
    data: params
  })
}
