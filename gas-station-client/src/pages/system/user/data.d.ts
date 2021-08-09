import {BaseListParams} from "@/define/data";

interface UserInfo {
  id: number,
  name: string
  username: string
  createTime: string
}

interface UserListParams extends BaseListParams{
  name?: string;
  username?: string;
}
