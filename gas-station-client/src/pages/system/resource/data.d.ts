export interface ResourceInfo {
  id: number,
  name: string
  createTime: string,
  selected: boolean,
  children: ResourceInfo[]
}
