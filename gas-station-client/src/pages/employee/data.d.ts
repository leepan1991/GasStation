import {BaseListParams} from "@/define/data";

export interface EmployeeInfo {
  id: number,
  name: string
  phone: string
  password: string
  createTime: string
}

export interface EmployeeListParams extends BaseListParams {
  name?: string
  phone?: string
}
