import React from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {queryRoleList} from "./service";

const Role: React.FC = () => {
  const columns: ProColumns<RoleInfo>[] = [{
    title: 'ID',
    dataIndex: 'id',
  }, {
    title: '名称',
    dataIndex: 'name',
  }, {
    title: '创建时间',
    dataIndex: 'createTime',
  }];
  return (
    <PageContainer>
      <ProTable<RoleInfo>
        rowKey="id"
        columns={columns}
        pagination={false}
        search={false}
        request={() => {
          return queryRoleList();
        }}
      />
    </PageContainer>
  );
}

export default Role;
