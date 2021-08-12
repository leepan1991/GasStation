import request, {Result} from '@/utils/request';
import {EmployeeInfo, EmployeeListParams} from "@/pages/employee/data";
import {PageInfo} from "@/define/data";

export async function queryEmployeeList(params: EmployeeListParams): Promise<any> {
  return request<PageInfo<EmployeeInfo>>('/mgr/biz/employee', {
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

export async function saveEmployee(params: EmployeeInfo): Promise<Result<undefined>> {
  return request<undefined>('/mgr/biz/employee', {
    method: 'POST',
    data: params
  });
}

export async function updateEmployee(id: number, params: EmployeeInfo): Promise<Result<undefined>> {
  return request<undefined>(`/mgr/biz/employee/${id}`, {
    method: 'PUT',
    data: params
  });
}

export async function deleteEmployee(id: number): Promise<Result<undefined>> {
  return request<undefined>(`/mgr/biz/employee/${id}`, {
    method: 'DELETE'
  });
}
