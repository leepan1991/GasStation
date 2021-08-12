import React, {useState} from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {deleteRole, queryRoleList} from "./service";
import {Button, Divider} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import RoleForm from "@/pages/system/role/components/RoleForm";
import GrantResourceForm from "@/pages/system/role/components/GrantResourceForm";
import {RoleInfo} from "@/pages/system/role/data";
import {ProCoreActionType} from "@ant-design/pro-utils";
import FetchPopconfirm from "@/components/FetchPopconfirm";

let actionRef: ProCoreActionType;

const Role: React.FC = () => {
  const [editModalVisible, setEditModalVisible] = useState<boolean>(false);
  const [grantModalVisible, setGrantModalVisible] = useState<boolean>(false);
  const [record, setRecord] = useState<RoleInfo>();

  const columns: ProColumns<RoleInfo>[] = [{
    title: 'ID',
    dataIndex: 'id',
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
        <a onClick={() => {
          setRecord(record);
          setGrantModalVisible(true);
        }}>授权</a>
        <Divider type="vertical"/>
        <FetchPopconfirm title="确认删除？"
                         request={deleteRole}
                         params={record.id}
                         onConfirm={() => actionRef?.reload()}>
          <a>删除</a>
        </FetchPopconfirm>
      </>
    ),
  }];

  return (
    <PageContainer>
      <ProTable<RoleInfo>
        actionRef={(a) => actionRef = a}
        rowKey="id"
        columns={columns}
        pagination={false}
        search={false}
        request={() => queryRoleList()}
        toolBarRender={() => [
          <Button key="add" type="primary" onClick={() => setEditModalVisible(true)}>
            <PlusOutlined/> 新建
          </Button>
        ]}
      />
      {
        editModalVisible && <RoleForm record={record}
                                      onClose={(refresh) => {
                                        setEditModalVisible(false);
                                        setRecord(undefined);
                                        if (refresh) actionRef?.reload();
                                      }}/>
      }
      {
        (grantModalVisible && record) && <GrantResourceForm roleInfo={record}
                                                            onClose={() => {
                                                              setGrantModalVisible(false);
                                                              setRecord(undefined);
                                                            }}/>
      }
    </PageContainer>
  );
}

export default Role;
