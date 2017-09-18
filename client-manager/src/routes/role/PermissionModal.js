import React, { PropTypes } from 'react'
import { Modal, Table, Checkbox } from 'antd'

class PermissionModal extends React.Component {
  static propTypes = {
    permissions: PropTypes.array,
    visible: PropTypes.bool,
    onCancel: PropTypes.func,
    onOk: PropTypes.func
  }

  constructor (props) {
    super(props)
    let permissions = props.permissions;
    function loopSelectCode(list) {
      let ids = []
      list.forEach(item => {
        if (item.selected) {
          ids.push(item.id)
        }
        if (item.permissionList && item.permissionList.length > 0) {
          item.permissionList.forEach(permission => {
            if (permission.selected) {
              ids.push(permission.id)
            }
          })
        }
        if (item.children && item.children.length > 0) {
          let childIds = loopSelectCode(item.children)
          if (childIds && childIds.length > 0) {
            ids = ids.concat(childIds)
          }
        }
      })
      return ids
    }
    this.state = {
      permissions: props.permissions,
      selectedCodeList: loopSelectCode(permissions)
    }
  }

  selectPermission(selectedCodeList) {
    const loopSelect = function(item) {
      selectedCodeList.forEach(id => {
        if (item.id === id) {
          item.selected = true
        }
        if (item.children && item.children.length > 0) {
          item.children.forEach(child => {
            loopSelect(child)
          })
        }
        if (item.permissionList && item.permissionList.length > 0) {
          item.permissionList.forEach(permission => {
            if (permission.id === id) {
              permission.selected = true
            }
          })
        }
      })
    }
    const loopUnselect = function (item) {
      item.selected = false
      if (item.permissionList && item.permissionList.length > 0) {
        item.permissionList.forEach(permission => {
          permission.selected = false
        })
      }
      if (item.children && item.children.length > 0) {
        item.children.forEach(child => {
          loopUnselect(child)
        })
      }
    }
    let { permissions } = this.state
    permissions.forEach(item => {
      loopUnselect(item)
      loopSelect(item)
    })
    this.setState({permissions, selectedCodeList})
  }

  onPermissionChange(value, options) {
    let { selectedCodeList } = this.state
    selectedCodeList = selectedCodeList.concat(value)
    selectedCodeList = Array.from(new Set(selectedCodeList))
    let values = options.map(item => {
      return item.value
    })
    values.forEach(item => {
      if (value.indexOf(item) < 0) {
        let index = selectedCodeList.indexOf(item)
        if (index > -1) {
          selectedCodeList.splice(index, 1);
        }
      }
    })
    this.selectPermission(selectedCodeList)
  }

  onTableChecked(selectedRows) {
    let { selectedCodeList } = this.state
    if (selectedRows && selectedRows.length > 0) {
      selectedRows.forEach(item => {
        selectedCodeList.push(item.id)
        if (item.permissionList && item.permissionList.length > 0) {
          item.permissionList.forEach(permission => {
            selectedCodeList.push(permission.id)
          })
        }
        if (item.children && item.children.length > 0) {
          item.children.forEach(child => {
            selectedCodeList.push(child.id)
          })
        }
      })
    }
    selectedCodeList = Array.from(new Set(selectedCodeList))
    this.selectPermission(selectedCodeList)
  }

  render() {
    let { permissions, selectedCodeList } = this.state
    let {
      visible,
      onOk,
      onCancel
    } = this.props
    function handleOk () {
      onOk(selectedCodeList)
    }

    const modalOpts = {
      title: `分配权限`,
      width: 800,
      visible,
      onOk: handleOk,
      onCancel,
      wrapClassName: 'vertical-center-modal',
    }

    const tableProps = {
      pagination: false,
      size: 'middle',
      bordered: true,
      columns: [{
        title: '名称',
        dataIndex: 'name',
        key: 'name',
        width: 200
      }, {
        title: '权限项',
        dataIndex: 'permissionList',
        key: 'permissionList',
        render: (text, record) => {
          let values = []
          let options = [{
            label: '访问',
            value: record.id
          }]
          if (record.selected) {
            values.push(record.id)
          }
          if (text && text.length > 0) {
            text.forEach(item => {
              options.push({
                label: item.name,
                value: item.id
              })
              if (item.selected) {
                values.push(item.id)
              }
            })
          }
          return <Checkbox.Group options={options} value={values} onChange={(value) => this.onPermissionChange(value, options)}/>
        }
      }],
      dataSource: permissions,
      rowKey: record => record.id,
      rowSelection: {
        onChange: (selectedRowKeys, selectedRows) => {
        },
        onSelect: (record, selected, selectedRows) => {
          this.onTableChecked(selectedRows)
        },
        onSelectAll: (selected, selectedRows) => {
          this.onTableChecked(selectedRows)
        }
      }
    }

    return (
      <Modal {...modalOpts}>
        <Table simple {...tableProps}/>
      </Modal>
    )
  }
}

export default PermissionModal
