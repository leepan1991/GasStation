import request from '@/utils/request';

export async function queryRoleList(): Promise<any> {
  return request<RoleInfo[]>('/mgr/system/role', {
    method: 'GET',
  }).then((res) => {
    if (res.code !== 0) {
      return {
        data: [],
        success: false,
      };
    }
    return {
      data: res.data,
      total: res.data.length,
      success: true,
    };
  });
}
