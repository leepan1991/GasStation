import React, {useState} from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {deleteEmployee, queryEmployeeList} from "./service";
import {Button, Divider} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import EmployeeForm from "@/pages/employee/components/EmployeeForm";
import {EmployeeInfo} from "@/pages/employee/data";
import {ProCoreActionType} from "@ant-design/pro-utils";
import FetchPopconfirm from "@/components/FetchPopconfirm";

let actionRef: ProCoreActionType;

const Employee: React.FC = () => {
  const [editModalVisible, setEditModalVisible] = useState<boolean>(false);
  const [record, setRecord] = useState<EmployeeInfo>();

  const columns: ProColumns<EmployeeInfo>[] = [{
    title: '名称',
    dataIndex: 'name',
  }, {
    title: '电话',
    dataIndex: 'phone',
  }, {
    title: '创建时间',
    dataIndex: 'createTime',
    search: false
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
                         request={deleteEmployee}
                         params={record.id}
                         onConfirm={() => actionRef?.reload()}>
          <a>删除</a>
        </FetchPopconfirm>
      </>
    ),
  }];

  return (
    <PageContainer>
      <ProTable<EmployeeInfo>
        actionRef={(a) => actionRef = a}
        rowKey="id"
        columns={columns}
        request={(params, sorter, filter) => {
          return queryEmployeeList({...params, sorter, filter})
        }}
        toolBarRender={() => [
          <Button key="add" type="primary" onClick={() => setEditModalVisible(true)}>
            <PlusOutlined/> 新建
          </Button>
        ]}
      />
      {
        editModalVisible && <EmployeeForm record={record}
                                      onClose={(refresh) => {
                                        setEditModalVisible(false);
                                        setRecord(undefined);
                                        if (refresh) actionRef?.reload();
                                      }}/>
      }
    </PageContainer>
  );
}

export default Employee;
