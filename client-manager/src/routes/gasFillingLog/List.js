import React, {PropTypes} from 'react'
import {Table, Modal} from 'antd'
import {DropOption} from '../../components'
import {mediumFormat} from '../../utils/format'

const confirm = Modal.confirm

function list({loading, dataSource, pagination, onPageChange, onDeleteItem, location}) {
  const handleMenuClick = (record, e) => {
    if (e.key === '2') {
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
      title: '序号',
      dataIndex: 'id',
      key: 'id'
    }, {
      title: '气瓶登记码',
      dataIndex: 'code',
      key: 'code'
    }, {
      title: '充装时间',
      dataIndex: 'fillingTime',
      key: 'fillingTime'
    }, {
      title: '充装工',
      dataIndex: 'fillingerName',
      key: 'fillingerName'
    }, {
      title: '充装前后检查员',
      dataIndex: 'inspector',
      key: 'inspector'
    }, {
      title: '操作',
      key: 'operation',
      width: 80,
      render: (text, record) => {
        return <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={[{ key: '2', name: '删除' }]}/>
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
  location: PropTypes.object
}

export default list
