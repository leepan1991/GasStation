import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/user/listPaged',
    method: 'get',
    data: params
  })
}

export async function create (params) {
  return request({
    url: '/manager/user/add',
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/user/delete',
    method: 'get',
    data: params
  })
}

export async function update (params) {
  return request({
    url: '/manager/user/update',
    method: 'put',
    data: params
  })
}

export async function updatePwd (params) {
  return request({
    url: '/manager/user/updatePwd',
    method: 'put',
    data: params
  })
}

export async function assignRole (params) {
  return request({
    url: '/manager/user/assignRole',
    method: 'put',
    data: params
  })
}
