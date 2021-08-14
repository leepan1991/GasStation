import request, {Result} from '@/utils/request';
import {GasMediumInfo} from "@/pages/gasMedium/data";
import {useEffect, useState} from "react";

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

export const useGasMediumInfoList = (gasBottleId: number): [GasMediumInfo | undefined, boolean] => {
  const [loading, setLoading] = useState(true);
  const [gasMediumList, setGasMediumList] = useState<GasMediumInfo>();
  useEffect(() => {
    setLoading(true);
    (async () => {
      const result = await queryGasMediumList();
      setGasMediumList(result.data);
      setLoading(false);
    })();
  }, [gasBottleId]);
  return [gasMediumList, loading];
};
