import React, {useState} from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {deleteGasMedium, queryGasMediumList} from "./service";
import {Button, Divider} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import GasMediumForm from "@/pages/gasMedium/components/GasMediumForm";
import {GasMediumInfo} from "@/pages/gasMedium/data";
import {ProCoreActionType} from "@ant-design/pro-utils";
import FetchPopconfirm from "@/components/FetchPopconfirm";

let actionRef: ProCoreActionType;

const GasMedium: React.FC = () => {
  const [editModalVisible, setEditModalVisible] = useState<boolean>(false);
  const [record, setRecord] = useState<GasMediumInfo>();

  const columns: ProColumns<GasMediumInfo>[] = [{
    title: '标识',
    dataIndex: 'code',
  }, {
    title: '名称',
    dataIndex: 'name',
  }, {
    title: '创建时间',
    dataIndex: 'createTime',
  }, {
    title: '操作',
    dataIndex: 'option',
    valueType: 'option',
    fixed: 'right',
    width: 150,
    render: (_, record) => (
      <>
        <a onClick={() => {
          setRecord(record);
          setEditModalVisible(true);
        }}>编辑</a>
        <Divider type="vertical"/>
        <FetchPopconfirm title="确认删除？"
                         request={deleteGasMedium}
                         params={record.id}
                         onConfirm={() => actionRef?.reload()}>
          <a>删除</a>
        </FetchPopconfirm>
      </>
    ),
  }];

  return (
    <PageContainer>
      <ProTable<GasMediumInfo>
        actionRef={(a) => actionRef = a}
        rowKey="id"
        columns={columns}
        pagination={false}
        search={false}
        request={() => queryGasMediumList()}
        toolBarRender={() => [
          <Button key="add" type="primary" onClick={() => setEditModalVisible(true)}>
            <PlusOutlined/> 新建
          </Button>
        ]}
      />
      {
        editModalVisible && <GasMediumForm record={record}
                                      onClose={(refresh) => {
                                        setEditModalVisible(false);
                                        setRecord(undefined);
                                        if (refresh) actionRef?.reload();
                                      }}/>
      }
    </PageContainer>
  );
}

export default GasMedium;
