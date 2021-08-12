import request, {Result} from '@/utils/request';
import {PageInfo} from "@/define/data";
import {GrantedRoleInfo, UserInfo, UserListParams} from "@/pages/system/user/data";
import React, {useEffect, useState} from "react";

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

export async function saveUser(params: UserInfo): Promise<Result<UserInfo>> {
  return request<UserInfo>('/mgr/system/user', {
    method: 'POST',
    data: params
  });
}

export async function updateUser(id: number, params: UserInfo): Promise<Result<UserInfo>> {
  return request<UserInfo>(`/mgr/system/user/${id}`, {
    method: 'PUT',
    data: params
  });
}

export async function deleteUser(id: number): Promise<Result<UserInfo>> {
  return request<UserInfo>(`/mgr/system/user/${id}`, {
    method: 'DELETE'
  });
}

export async function changePassword(id: number, password: string): Promise<Result<UserInfo>> {
  return request<UserInfo>(`/mgr/system/user/${id}/password`, {
    method: 'PUT',
    data: {password}
  });
}

export async function grantRole(id: number, roleIdList: string[]): Promise<Result<undefined>> {
  return request<undefined>(`/mgr/system/user/${id}/grant`, {
    method: 'PUT',
    data: {roleIdList}
  });
}

export async function grantInfo(id: number): Promise<Result<GrantedRoleInfo[]>> {
  return request<GrantedRoleInfo[]>(`/mgr/system/user/${id}/grantInfo`, {
    method: 'GET'
  });
}

export const useUserGrantInfo = (userId: number): [GrantedRoleInfo[], string[], React.Dispatch<React.SetStateAction<string[]>>, boolean] => {
  const [loading, setLoading] = useState<boolean>(true);
  const [targetKeys, setTargetKeys] = useState<string[]>([]);
  const [roleList, setRoleList] = useState<GrantedRoleInfo[]>([]);
  useEffect(() => {
    setLoading(true);
    (async () => {
      const result = await grantInfo(userId);
      if (result.code == 0) {
        setRoleList(result.data);
        setTargetKeys(result.data.filter(item => item.selected).map(item => item.id + ""));
      }
      setLoading(false);
    })();
  }, [userId]);
  return [roleList, targetKeys, setTargetKeys, loading];
};
