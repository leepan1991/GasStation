import React, {PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'
import PermissionModal from './PermissionModal'

function Role({location, dispatch, role, loading}) {
  const {list, permissions, pagination, currentItem, modalVisible, perModalVisible, modalType} = role
  const modalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `role/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'role/hideModal'
      })
    }
  }

  const permissionModalProps = {
    permissions,
    visible: perModalVisible,
    onOk (data) {
      dispatch({
        type: `role/assignPermission`,
        payload: {
          permissionIds: data
        }
      })
    },
    onCancel () {
      dispatch({
        type: 'role/hidePermissionModal'
      })
    }
  }

  const listProps = {
    dataSource: list,
    loading,
    pagination,
    location,
    onPageChange (page) {
      const {query, pathname} = location
      dispatch(routerRedux.push({
        pathname,
        query: {
          ...query,
          page: page.current,
          pageSize: page.pageSize
        }
      }))
    },
    onDeleteItem (id) {
      dispatch({
        type: 'role/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'role/showModal',
        payload: {
          modalType: 'update',
          currentItem: item
        }
      })
    },
    onAssignPermission (item) {
      dispatch({
        type: 'role/selectInfoByUserId',
        payload: item
      })
    }
  }

  const filterProps = {
    location,
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/system/role',
        query: {
          [fieldsValue.field]: fieldsValue.keyword
        }
      })) : dispatch(routerRedux.push({
        pathname: '/system/role'
      }))
    },
    onAdd () {
      dispatch({
        type: 'role/showModal',
        payload: {
          modalType: 'create',
        }
      })
    }
  }

  const ModalGen = () => <Modal {...modalProps} />
  const PermissionModalGen = () => <PermissionModal {...permissionModalProps} />

  return (
    <div className="content-inner">
      <Filter {...filterProps} />
      <List {...listProps} />
      <ModalGen />
      <PermissionModalGen />
    </div>
  )
}

Role.propTypes = {
  role: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({role, loading}) => ({role, loading: loading.models.role}))(Role)
