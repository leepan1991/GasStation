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
