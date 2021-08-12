import request, {Result} from '@/utils/request';
import {GasMediumInfo} from "@/pages/gasMedium/data";

export async function queryGasMediumList(): Promise<any> {
  return request<GasMediumInfo[]>('/mgr/biz/gasMedium', {
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

export async function saveGasMedium(params: GasMediumInfo): Promise<Result<undefined>> {
  return request<undefined>('/mgr/biz/gasMedium', {
    method: 'POST',
    data: params
  });
}

export async function updateGasMedium(id: number, params: GasMediumInfo): Promise<Result<undefined>> {
  return request<undefined>(`/mgr/biz/gasMedium/${id}`, {
    method: 'PUT',
    data: params
  });
}

export async function deleteGasMedium(id: number): Promise<Result<undefined>> {
  return request<undefined>(`/mgr/biz/gasMedium/${id}`, {
    method: 'DELETE'
  });
}
