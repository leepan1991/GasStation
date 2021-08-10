import request, {Result} from '@/utils/request';
import {PageInfo} from "@/define/data";
import {GasBottleInfo, GasBottleInfoListParams} from "@/pages/gasBottle/data";
import {useEffect, useState} from "react";

export async function queryGasBottleList(params: GasBottleInfoListParams): Promise<any> {
  return request<PageInfo<GasBottleInfo>>('/mgr/biz/gasBottle', {
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

export async function queryById(id: number): Promise<Result<GasBottleInfo>> {
  return request<GasBottleInfo>(`/mgr/biz/gasBottle/${id}`, {
    method: 'GET',
  });
}

export async function updateById(id: number, record: GasBottleInfo): Promise<Result<any>> {
  return request<GasBottleInfo>(`/mgr/biz/gasBottle/${id}`, {
    method: 'PUT',
    data: record
  });
}

export async function deleteById(id: number): Promise<Result<any>> {
  return request<GasBottleInfo>(`/mgr/biz/gasBottle/${id}`, {
    method: 'DELETE'
  });
}

export const useGasBottleInfo = (gasBottleId: number): [GasBottleInfo | undefined, boolean] => {
  const [loading, setLoading] = useState(true);
  const [gasBottleInfo, setGasBottleInfo] = useState<GasBottleInfo>();
  useEffect(() => {
    setLoading(true);
    (async () => {
      const result = await queryById(gasBottleId);
      if (result.code == 0) {
        setGasBottleInfo(result.data);
      }
      setLoading(false);
    })();
  }, [gasBottleId]);
  return [gasBottleInfo, loading];
};
