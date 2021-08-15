import ProTable, {ProColumns} from "@ant-design/pro-table";
import React from "react";

export interface LogTableProps {

}

const LogTable: React.FC<LogTableProps> = () => {

  const columns: ProColumns[] = [{
    title: '序号/项目',
    dataIndex: 'a',
    width: 100,
    search: false
  }, {
    title: '充装时间',
    dataIndex: 'fillTime',
    width: 100
  }, {
    title: '钢瓶编号',
    dataIndex: 'no1',
    width: 100
  }, {
    title: '充装前检查',
    search: false,
    children: [{
      title: '气瓶原始编号',
      dataIndex: 'no2',
      search: false
    }, {
      title: '外观检查',
      dataIndex: 'no3',
      search: false
    }, {
      title: '剩余压力Mpa',
      dataIndex: 'no4',
      search: false
    }, {
      title: '气瓶容积L',
      dataIndex: 'no5',
      search: false
    }, {
      title: '公称压力Mpa',
      dataIndex: 'no6',
      search: false
    }, {
      title: '最大充装量kg',
      dataIndex: 'no7',
      search: false
    }, {
      title: '钢瓶皮重kg',
      dataIndex: 'no8',
      search: false
    }, {
      title: '空瓶现重kg',
      dataIndex: 'no9',
      search: false
    }, {
      title: '剩余介质重量kg',
      dataIndex: 'no10',
      search: false
    }]
  }, {
    title: '充装检查',
    search: false,
    children: [{
      title: '气瓶抽真空',
      dataIndex: 'no11',
      search: false
    }, {
      title: '充装压力Mpa',
      dataIndex: 'no12',
      search: false
    }, {
      title: '异常情况',
      dataIndex: 'no13',
      search: false
    }, {
      title: '处理措施',
      dataIndex: 'no14',
      search: false
    }]
  }, {
    title: '实瓶重量kg',
    dataIndex: 'no15',
    search: false
  }, {
    title: '介质重量kg',
    dataIndex: 'no16',
    search: false
  }, {
    title: '瓶内压力Mpa',
    dataIndex: 'no17',
    search: false
  }, {
    title: '充装后检查',
    search: false,
    children: [{
      title: '质量检验',
      dataIndex: 'no18',
      search: false
    }, {
      title: '异常情况',
      dataIndex: 'no19',
      search: false
    }, {
      title: '处理措施',
      dataIndex: 'no20',
      search: false
    }, {
      title: '充装人员',
      dataIndex: 'no21',
      search: false
    }, {
      title: '复核介质重量kg',
      dataIndex: 'no22',
      search: false
    }, {
      title: '复核人',
      dataIndex: 'no23',
      search: false
    }]
  }];

  return (
    <ProTable rowKey="id"
              bordered
              scroll={{x: 2248}}
              columns={columns}/>
  );
}

export default LogTable;
