import React from "react";
import {PageContainer} from "@ant-design/pro-layout";
import ProTable, {ProColumns} from '@ant-design/pro-table';
import {ProCoreActionType} from "@ant-design/pro-utils";
import {queryGasBottleList, deleteById} from "../service";
import {GasBottleInfo} from "@/pages/gasBottle/data";
import {Divider, Popconfirm} from "antd";
import {Link} from "@umijs/preset-dumi/lib/theme";

let actionRef: ProCoreActionType;

function handleDelete(record: GasBottleInfo) {
  deleteById(record.id).then(res => {
    if (res.code === 0) {
      actionRef?.reload();
    }
    return true;
  });
}

const GasBottleList: React.FC = () => {
  const columns: ProColumns<GasBottleInfo>[] = [{
    title: '气瓶编号',
    dataIndex: 'qpno',
    fixed: 'left',
    width: 100
  }, {
    title: '气瓶品种',
    dataIndex: 'qppz',
    search: false,
    fixed: 'left',
    width: 100
  }, {
    title: '充装介质',
    dataIndex: 'ccjz',
    fixed: 'left',
    width: 100
  }, {
    title: '充装容积',
    dataIndex: 'qprj',
    search: false
  }, {
    title: '公称工作压力',
    dataIndex: 'gcyl',
    search: false
  }, {
    title: '水压试验压力',
    dataIndex: 'syyl',
    search: false
  }, {
    title: '制造单位',
    dataIndex: 'zzdw',
    search: false
  }, {
    title: '出厂日期',
    dataIndex: 'ccrq',
    search: false
  }, {
    title: '有效年限',
    dataIndex: 'yxqx',
    search: false
  }, {
    title: '制造编号',
    dataIndex: 'ccno',
    search: false
  }, {
    title: '充装标志',
    dataIndex: 'dwno',
    search: false
  }, {
    title: '使用单位',
    dataIndex: 'sydw',
    search: false
  }, {
    title: '产权性质',
    dataIndex: 'cqxz',
    search: false
  }, {
    title: '上次检验日期',
    dataIndex: 'jyrqf',
    search: false
  }, {
    title: '下次检验日期',
    dataIndex: 'jyrqn',
    search: false
  }, {
    title: '气瓶名称',
    dataIndex: 'qpmc',
    search: false
  }, {
    title: '气瓶型号',
    dataIndex: 'qpxh',
    search: false
  }, {
    title: '状态',
    dataIndex: 'qpStatus',
    search: false
  }, {
    title: '操作',
    dataIndex: 'option',
    valueType: 'option',
    fixed: 'right',
    width: 110,
    render: (_, record) => (
      <>
        <Link to={`/gasBottle/edit/${record.id}`}>编辑</Link>
        <Divider type="vertical"/>
        <Popconfirm title="确认删除？" onConfirm={() => handleDelete(record)}>
          <a>删除</a>
        </Popconfirm>
      </>
    ),
  }];
  return (
    <PageContainer>
      <ProTable<GasBottleInfo>
        rowKey="id"
        actionRef={(a) => actionRef = a}
        columns={columns}
        scroll={{x: 2048}}
        request={(params, sorter, filter) => {
          return queryGasBottleList({...params, sorter, filter});
        }}
      />
    </PageContainer>
  );
}

export default GasBottleList;
