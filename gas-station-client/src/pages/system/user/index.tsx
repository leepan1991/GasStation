import React, {useState} from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {deleteUser, queryUserList} from "./service";
import {UserInfo} from "@/pages/system/user/data";
import {Button, Divider} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import UserForm from "@/pages/system/user/components/UserForm";
import PwdForm from "@/pages/system/user/components/PwdForm";
import GrantForm from "@/pages/system/user/components/GrantForm";
import {ProCoreActionType} from "@ant-design/pro-utils";
import FetchPopconfirm from "@/components/FetchPopconfirm";

let actionRef: ProCoreActionType;

const User: React.FC = () => {

  const [editModalVisible, setEditModalVisible] = useState<boolean>(false);
  const [grantModalVisible, setGrantModalVisible] = useState<boolean>(false);
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
    title: '手机号码',
    dataIndex: 'phone',
  }, {
    title: '创建时间',
    dataIndex: 'createTime',
    search: false,
  }, {
    title: '操作',
    dataIndex: 'option',
    valueType: 'option',
    fixed: 'right',
    width: 210,
    render: (_, record) => (
      <>
        <a onClick={() => {
          setRecord(record);
          setEditModalVisible(true);
        }}>编辑</a>
        <Divider type="vertical"/>
        <PwdForm userId={record.id}>
          <a>修改密码</a>
        </PwdForm>
        <Divider type="vertical"/>
        <a onClick={() => {
          setRecord(record);
          setGrantModalVisible(true);
        }}>角色</a>
        <Divider type="vertical"/>
        <FetchPopconfirm title="确认删除？"
                         request={deleteUser}
                         params={record.id}
                         onConfirm={() => actionRef?.reload()}>
          <a>删除</a>
        </FetchPopconfirm>
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
          <Button key="add" type="primary" onClick={() => setEditModalVisible(true)}>
            <PlusOutlined/> 新建
          </Button>
        ]}/>
      {
        editModalVisible && <UserForm record={record}
                                      onClose={(refresh) => {
                                        if (refresh) actionRef?.reload();
                                        setEditModalVisible(false);
                                        setRecord(undefined);
                                      }}/>
      }
      {
        (grantModalVisible && record) && <GrantForm userInfo={record}
                                                    onClose={() => {
                                                      setGrantModalVisible(false);
                                                      setRecord(undefined);
                                                    }}/>
      }
    </PageContainer>
  );
}

export default User;
