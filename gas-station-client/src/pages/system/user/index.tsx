import React, {useState} from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {queryUserList} from "./service";
import {UserInfo} from "@/pages/system/user/data";
import {Button, Divider, Popconfirm} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import UserForm from "@/pages/system/user/components/UserForm";
import {ProCoreActionType} from "@ant-design/pro-utils";

let actionRef: ProCoreActionType;

const User: React.FC = () => {

  const [modalVisible, setModalVisible] = useState<boolean>(false);
  const [record, setRecord] = useState<UserInfo>();

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
  }, {
    title: '操作',
    dataIndex: 'option',
    valueType: 'option',
    fixed: 'right',
    width: 110,
    render: (_, record) => (
      <>
        <a onClick={() => {
          setRecord(record);
          setModalVisible(true);
        }}>编辑</a>
        <Divider type="vertical"/>
        <Popconfirm title="确认删除？">
          <a>删除</a>
        </Popconfirm>
      </>
    ),
  }];
  return (
    <PageContainer>
      <ProTable<UserInfo>
        rowKey="id"
        actionRef={(a) => actionRef = a}
        columns={columns}
        request={(params, sorter, filter) => {
          return queryUserList({...params, sorter, filter});
        }}
        toolBarRender={() => [
          <Button key="add" type="primary" onClick={() => setModalVisible(true)}>
            <PlusOutlined/> 新建
          </Button>
        ]}/>
      {
        modalVisible && <UserForm record={record}
                                  onClose={(refresh) => {
                                    if (refresh) actionRef?.reload();
                                    setModalVisible(false);
                                  }}/>
      }
    </PageContainer>
  );
}

export default User;
