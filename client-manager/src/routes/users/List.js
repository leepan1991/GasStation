import React, {PropTypes} from 'react'
import {Table, Modal} from 'antd'
import {DropOption} from '../../components'
const confirm = Modal.confirm

function list({loading, dataSource, pagination, onPageChange, onDeleteItem, onEditItem, onEditPwd, onAssignRole}) {
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
      onEditPwd(record)
    } else if (e.key === '4') {
      onAssignRole(record)
    }
  }

  const columns = [{
    title: '用户名',
    dataIndex: 'username',
    key: 'username'
  }, {
    title: '邮箱',
    dataIndex: 'email',
    key: 'email'
  }, {
    title: '联系电话',
    dataIndex: 'phone',
    key: 'phone'
  }, {
    title: '状态',
    dataIndex: 'status',
    key: 'status',
    render: (text) => <span>{text == 0 ? '正常' : '禁用'}</span>
  }, {
    title: '操作',
    key: 'operation',
    width: 80,
    render: (text, record) => {
      return <DropOption onMenuClick={e => handleMenuClick(record, e)}
                         menuOptions={[{key: '1', name: '编辑'}, {key: '2', name: '删除'}, {
                           key: '3',
                           name: '修改密码'
                         }, {key: '4', name: '分配角色'}]}/>
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
  onAssignRole: PropTypes.func,
  onEditPwd: PropTypes.func
}

export default list
