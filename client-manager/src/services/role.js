import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/role/listPaged',
    method: 'get',
    data: params
  })
}
export async function selectWhenAssignRole (params) {
  return request({
    url: '/manager/role/selectWhenAssignRole',
    method: 'get',
    data: params
  })
}

export async function create (params) {
  return request({
    url: '/manager/role/add',
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/role/delete',
    method: 'get',
    data: params
  })
}

export async function update (params) {
  return request({
    url: '/manager/role/update',
    method: 'put',
    data: params
  })
}

export async function assignPermission (params) {
  return request({
    url: '/manager/role/assignPermission',
    method: 'post',
    data: params
  })
}
