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
      dataIndex: 'gasBottleRegCode',
      key: 'gasBottleRegCode'
    }, {
      title: '瓶身码',
      dataIndex: 'gasBottleCode',
      key: 'gasBottleCode'
    }, {
      title: '充装时间',
      dataIndex: 'fillingTime',
      key: 'fillingTime'
    }, {
      title: '充装工',
      dataIndex: 'fillingCustomerName',
      key: 'fillingCustomerName'
    }, {
      title: '充装前后检查员',
      dataIndex: 'inspector',
      key: 'inspector'
    }, {
      title: '充装介质',
      dataIndex: 'medium',
      key: 'medium',
      render: (text) => {
        return mediumFormat(text + '')
      }
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
      dataIndex: 'gasBottleVolume',
      key: 'gasBottleVolume'
    }, {
      title: '瓶重',
      dataIndex: 'gasBottleWeight',
      key: 'gasBottleWeight'
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
