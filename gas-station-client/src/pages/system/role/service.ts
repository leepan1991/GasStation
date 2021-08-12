import request, {Result} from '@/utils/request';
import {RoleInfo} from "@/pages/system/role/data";
import React, {useEffect, useState} from "react";
import {ResourceInfo} from "@/pages/system/resource/data";

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

export async function saveRole(params: RoleInfo): Promise<Result<undefined>> {
  return request<undefined>('/mgr/system/role', {
    method: 'POST',
    data: params
  });
}

export async function updateRole(id: number, params: RoleInfo): Promise<Result<undefined>> {
  return request<undefined>(`/mgr/system/role/${id}`, {
    method: 'PUT',
    data: params
  });
}

export async function deleteRole(id: number): Promise<Result<undefined>> {
  return request<undefined>(`/mgr/system/role/${id}`, {
    method: 'DELETE'
  });
}

export async function grantResource(id: number, resourceIdList: string[]): Promise<Result<undefined>> {
  return request<undefined>(`/mgr/system/role/${id}/grant`, {
    method: 'PUT',
    data: {resourceIdList}
  });
}

export async function grantInfo(id: number): Promise<Result<ResourceInfo[]>> {
  return request<ResourceInfo[]>(`/mgr/system/role/${id}/grantInfo`, {
    method: 'GET'
  });
}

function loopResourceId(list: ResourceInfo[]) {
  let selectedIdList: number[] = [];
  list.forEach(item => {
    if (item.selected) {
      selectedIdList.push(item.id);
      if (item.children && item.children.length > 0) {
        const childIdList = loopResourceId(item.children);
        if (childIdList && childIdList.length > 0) {
          selectedIdList = selectedIdList.concat(childIdList);
        }
      }
    }
  });
  return selectedIdList;
}

export const useRoleGrantInfo = (roleId: number): [ResourceInfo[], React.Key[], React.Dispatch<React.SetStateAction<React.Key[]>>, boolean] => {
  const [loading, setLoading] = useState<boolean>(true);
  const [selectedKeys, setSelectedKeys] = useState<React.Key[]>([]);
  const [resourceList, setResourceList] = useState<ResourceInfo[]>([]);
  useEffect(() => {
    setLoading(true);
    (async () => {
      const result = await grantInfo(roleId);
      if (result.code == 0) {
        setResourceList(result.data);
        setSelectedKeys(loopResourceId(result.data));
      }
      setLoading(false);
    })();
  }, [roleId]);
  return [resourceList, selectedKeys, setSelectedKeys, loading];
};
