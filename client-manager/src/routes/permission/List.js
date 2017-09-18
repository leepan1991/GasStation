import React, { PropTypes } from 'react'
import { Table, Modal, Icon } from 'antd'
import { DropOption } from '../../components'
import { statusFormat, permissionTypeFormat } from '../../utils/format'

const confirm = Modal.confirm

function list ({ loading, dataSource, pagination, onPageChange, onDeleteItem, onEditItem, location }) {
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
    }
  }

  const columns = [
    {
      title: '名称',
      dataIndex: 'name',
      key: 'name'
    }, {
      title: '编码',
      dataIndex: 'code',
      key: 'code'
    }, {
      title: '图标',
      dataIndex: 'icon',
      key: 'icon',
      render: (text) => {
        if (text) {
          return <Icon type={text}/>
        }
        return ''
      }
    }, {
      title: '状态',
      dataIndex: 'status',
      key: 'status',
      render: statusFormat
    }, {
      title: '类型',
      dataIndex: 'type',
      key: 'type',
      render: permissionTypeFormat
    }, {
      title: '操作',
      key: 'operation',
      width: 80,
      render: (text, record) => {
        return <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={[{ key: '1', name: '编辑' }, { key: '2', name: '删除' }]} />
      }
    }
  ]

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
