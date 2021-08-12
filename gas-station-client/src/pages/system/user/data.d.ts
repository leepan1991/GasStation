import {BaseListParams} from "@/define/data";

export interface UserInfo {
  id: number,
  name: string
  username: string
  createTime: string
}

export interface UserListParams extends BaseListParams {
  name?: string;
  username?: string;
}

export interface GrantedRoleInfo {
  id: number;
  name: string;
  selected: boolean;
}
