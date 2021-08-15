import request, {Result} from '@/utils/request';
import {PortalConfigInfo} from "@/pages/system/portal/data";
import {useEffect, useState} from "react";

export async function getPortalConfig(): Promise<Result<PortalConfigInfo>> {
  return request<PortalConfigInfo>('/mgr/biz/portalConfig', {
    method: 'GET',
  });
}

export const usePortalConfig = (flag: boolean): [PortalConfigInfo | undefined, boolean] => {
  const [loading, setLoading] = useState(true);
  const [portalConfigInfo, setPortalConfigInfo] = useState<PortalConfigInfo>();
  useEffect(() => {
    setLoading(true);
    (async () => {
      const result = await getPortalConfig();
      if (result.code == 0) {
        setPortalConfigInfo(result.data);
      }
      setLoading(false);
    })();
  }, [flag]);
  return [portalConfigInfo, loading];
};

export async function savePortalConfig(params: PortalConfigInfo): Promise<Result<any>> {
  return request<PortalConfigInfo>('/mgr/biz/portalConfig', {
    method: 'POST',
    data: params
  });
}
