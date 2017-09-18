import { request } from '../utils'

export async function login (params) {
  return request({
    url: '/manager/user/login',
    method: 'post',
    data: params
  })
}

export async function logout (params) {
  return request({
    url: '/manager/user/logout',
    method: 'post',
    data: params
  })
}

export async function userInfo (params) {
  return request({
    url: '/manager/user/userInfo',
    method: 'get',
    data: params
  })
}
