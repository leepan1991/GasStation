import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/gasBottle/listPaged',
    method: 'get',
    data: params
  })
}
export async function findLocation (params) {
  return request({
    url: '/manager/gasBottle/findLocation',
    method: 'get',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/gasBottle/delete',
    method: 'get',
    data: params
  })
}

export async function updateNextCheckTime (params) {
  return request({
    url: '/manager/gasBottle/updateNextCheckTime',
    method: 'post',
    data: params
  })
}
