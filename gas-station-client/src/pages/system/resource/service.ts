import request from '@/utils/request';
import {ResourceInfo} from "@/pages/system/resource/data";

export async function queryResourceList(): Promise<any> {
  return request<ResourceInfo[]>('/mgr/system/resource', {
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
