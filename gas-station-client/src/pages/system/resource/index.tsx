import React from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {queryResourceList} from "./service";
import {ResourceInfo} from "@/pages/system/resource/data";

const Resource: React.FC = () => {
  const columns: ProColumns<ResourceInfo>[] = [{
    title: '名称',
    dataIndex: 'name',
  }, {
    title: '标识',
    dataIndex: 'code',
  }];
  return (
    <PageContainer>
      <ProTable<ResourceInfo>
        rowKey="id"
        columns={columns}
        pagination={false}
        search={false}
        request={() => {
          return queryResourceList();
        }}
      />
    </PageContainer>
  );
}

export default Resource;
