import React from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {queryUserList} from "./service";
import {UserInfo} from "@/pages/system/user/data";

const User: React.FC = () => {
  const columns: ProColumns<UserInfo>[] = [{
    title: 'ID',
    dataIndex: 'id',
    search: false
  }, {
    title: '姓名',
    dataIndex: 'name',
  }, {
    title: '账号',
    dataIndex: 'username',
  }, {
    title: '创建时间',
    dataIndex: 'createTime',
    search: false,
  }];
  return (
    <PageContainer>
      <ProTable<UserInfo>
        rowKey="id"
        columns={columns}
        request={(params, sorter, filter) => {
          return queryUserList({...params, sorter, filter});
        }}
      />
    </PageContainer>
  );
}

export default User;
