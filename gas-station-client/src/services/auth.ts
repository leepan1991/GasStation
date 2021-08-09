import request, {Result} from '@/utils/request';
import {LoginInfo, LoginParams} from "@/define/data";

export async function login(params: LoginParams): Promise<Result<LoginInfo>> {
  return request<LoginInfo>('/mgr/auth/login', {
    method: 'POST',
    data: params,
  });
}

export async function logout(): Promise<any> {
  return request<LoginInfo>('/mgr/auth/logout', {
    method: 'POST',
  });
}

export async function loginInfo(): Promise<Result<LoginInfo>> {
  return request<LoginInfo>('/mgr/auth/loginInfo', {
    method: 'GET'
  });
}
