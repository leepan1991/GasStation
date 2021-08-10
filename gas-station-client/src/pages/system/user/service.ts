import request from '@/utils/request';
import {PageInfo} from "@/define/data";
import {UserInfo, UserListParams} from "@/pages/system/user/data";

export async function queryUserList(params: UserListParams): Promise<any> {
  return request<PageInfo<UserInfo>>('/mgr/system/user', {
    method: 'GET',
    params
  }).then((res) => {
    if (res.code !== 0) {
      return {
        data: [],
        success: false,
      };
    }
    return {
      data: res.data.records,
      total: res.data.total,
      success: true,
      pageSize: res.data.pageSize,
      current: res.data.current
    };
  });
}

export async function saveUser(params: UserInfo): Promise<any> {
  return request<PageInfo<UserInfo>>('/mgr/system/user', {
    method: 'POST',
    data: params
  });
}

export async function updateUser(id:number, params: UserInfo): Promise<any> {
  return request<PageInfo<UserInfo>>(`/mgr/system/user/${id}`, {
    method: 'PUT',
    data: params
  });
}
