import React, {PropTypes} from 'react'
import {Table, Modal} from 'antd'
import {DropOption} from '../../components'

const confirm = Modal.confirm

function list({loading, dataSource, pagination, onPageChange, onDeleteItem, onEditCheckTime, onShowLocation, location}) {
  const handleMenuClick = (record, e) => {
    if (e.key === '1') {
      onShowLocation(record)
    } else if (e.key === '2') {
      confirm({
        title: '您确定要删除这条记录吗?',
        onOk () {
          onDeleteItem(record.id)
        }
      })
    } else if (e.key === '3') {
      onEditCheckTime(record)
    }
  }

  const columns = [{
    title: '气瓶登记码',
    dataIndex: 'code',
    key: 'code'
  }, {
    title: '瓶身码',
    dataIndex: 'bottleCode',
    key: 'bottleCode'
  }, {
    title: '充装介质',
    dataIndex: 'mediumName',
    key: 'mediumName'
  }, {
    title: '制造单位',
    dataIndex: 'manufacturer',
    key: 'manufacturer'
  }, {
    title: '制造年月',
    dataIndex: 'manufactureDate',
    key: 'manufactureDate'
  }, {
    title: '公称工作压力(Mpa)',
    dataIndex: 'workPressure',
    key: 'workPressure'
  }, {
    title: '容积(L)',
    dataIndex: 'volume',
    key: 'volume'
  }, {
    title: '瓶重',
    dataIndex: 'weight',
    key: 'weight'
  }, {
    title: '设计壁厚(mm)',
    dataIndex: 'wallThickness',
    key: 'wallThickness'
  }, {
    title: '最近一次检验日期',
    dataIndex: 'lastCheckTime',
    key: 'lastCheckTime'
  }, {
    title: '下次检验日期',
    dataIndex: 'nextCheckTime',
    key: 'nextCheckTime'
  }, {
    title: '报废日期',
    dataIndex: 'invalidatedDate',
    key: 'invalidatedDate'
  }, {
    title: '操作',
    key: 'operation',
    width: 80,
    render: (text, record) => {
      return <DropOption onMenuClick={e => handleMenuClick(record, e)}
                         menuOptions={[{key: '1', name: '位置'}, {key: '2', name: '删除'}, {key: '3', name: '更新下次检验时间'}]}/>
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
        rowKey={record => record.code}
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
