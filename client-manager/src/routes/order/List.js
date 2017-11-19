import React, {PropTypes} from 'react'
import {Table, Modal} from 'antd'
import {DropOption} from '../../components'
import {orderStatusFormat, timeFormat} from '../../utils/format'

const confirm = Modal.confirm

function list({loading, dataSource, pagination, onPageChange, onDeleteItem, onEditItem, onShowBottle, location}) {
  const handleMenuClick = (record, e) => {
    if (e.key === '1') {
      onEditItem(record)
    } else if (e.key === '2') {
      confirm({
        title: '您确定要删除这条记录吗?',
        onOk () {
          onDeleteItem(record.id)
        }
      })
    } else if (e.key === '3') {
      onShowBottle(record)
    }
  }

  const columns = [{
    title: '客户单位',
    dataIndex: 'unitName',
    key: 'unitName'
  }, {
    title: '联系地址',
    dataIndex: 'unitAddress',
    key: 'unitAddress'
  }, {
    title: '联系电话',
    dataIndex: 'linkPhone',
    key: 'linkPhone'
  }, {
    title: '联系人',
    dataIndex: 'linkUser',
    key: 'linkUser'
  }, {
    title: '气瓶数量',
    dataIndex: 'bottleNum',
    key: 'bottleNum'
  }, {
    title: '状态',
    dataIndex: 'status',
    key: 'status',
    render: orderStatusFormat
  }, {
    title: '创建时间',
    dataIndex: 'createTime',
    key: 'createTime',
    render: timeFormat
  }, {
    title: '操作',
    key: 'operation',
    width: 80,
    render: (text, record) => {
      return <DropOption onMenuClick={e => handleMenuClick(record, e)}
                         menuOptions={[{key: '1', name: '编辑'}, {key: '3', name: '气瓶'}, {key: '2', name: '删除'}]}/>
    }
  }]

  return (
    <div>
      <Table
        size="middle"
        bordered
        columns={columns}
        dataSource={dataSource}
        loading={loading}
        onChange={onPageChange}
        pagination={pagination}
        simple
        rowKey={record => record.id}
      />
    </div>
  )
}

list.propTypes = {
  loading: PropTypes.bool,
  dataSource: PropTypes.array,
  pagination: PropTypes.object,
  onPageChange: PropTypes.func,
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  location: PropTypes.object
}

export default list
