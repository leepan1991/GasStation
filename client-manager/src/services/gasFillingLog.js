import { request } from '../utils'

export async function query (params) {
  return request({
    url: '/manager/gasFillingLog/listPaged',
    method: 'get',
    data: params
  })
}

export async function remove (params) {
  return request({
    url: '/manager/gasFillingLog/delete',
    method: 'get',
    data: params
  })
}
