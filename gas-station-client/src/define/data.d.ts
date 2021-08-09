
export interface LoginParams {
  username: string;
  password: string;
}

export interface LoginInfo {
  id: number;
  name: string;
  username: string;
}

export interface PageInfo<T> {
  current: number,
  pageSize: number,
  total: number,
  records: T[]
}

export interface BaseListParams {
  filter?: { [key: string]: any };
  sorter?: { [key: string]: any };
}
