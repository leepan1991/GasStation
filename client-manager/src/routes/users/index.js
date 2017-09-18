import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'
import PwdModal from './PwdModal'
import RoleModal from './RoleModal'

function Users ({ location, dispatch, users, loading }) {
  const { list, assignRoleList, pagination, currentItem, modalVisible, pwdModalVisible, roleModalVisible, modalType } = users
  const { field, keyword } = location.query

  const modalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `users/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'users/hideModal'
      })
    }
  }

  const pwdModalProps = {
    visible: pwdModalVisible,
    onOk (data) {
      dispatch({
        type: `users/updatePwd`,
        payload: data
      })
    },
    onCancel () {
      dispatch({
        type: 'users/hidePwdModal'
      })
    }
  }

  const roleModalProps = {
    assignRoleList,
    item: currentItem,
    visible: roleModalVisible,
    onOk (data) {
      console.log(data)
      dispatch({
        type: `users/assignRole`,
        payload: data
      })
    },
    onCancel () {
      dispatch({
        type: 'users/hideRoleModal'
      })
    }
  }

  const listProps = {
    dataSource: list,
    loading,
    pagination,
    location,
    onPageChange (page) {
      const { query, pathname } = location
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
        type: 'users/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'users/showModal',
        payload: {
          modalType: 'update',
          currentItem: item
        }
      })
    },
    onEditPwd (item) {
      dispatch({
        type: 'users/showPwdModal',
        payload: {
          modalType: 'updatePwd',
          currentItem: item
        }
      })
    },
    onAssignRole (item) {
      dispatch({
        type: 'users/selectWhenAssignRole',
        payload: {
          currentItem: item
        }
      })
    }
  }

  const filterProps = {
    field,
    keyword,
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/system/users',
        query: {
          [fieldsValue.field]: fieldsValue.keyword
        },
      })) : dispatch(routerRedux.push({
        pathname: '/system/users'
      }))
    },
    onAdd () {
      dispatch({
        type: 'users/showModal',
        payload: {
          modalType: 'create',
        }
      })
    }
  }

  const ModalGen = () => <Modal {...modalProps} />

  const PwdModalGen = () => <PwdModal {...pwdModalProps} />

  const RoleModalGen = () => <RoleModal {...roleModalProps} />

  return (
    <div className="content-inner">
      <Filter {...filterProps} />
      <List {...listProps} />
      <ModalGen />
      <PwdModalGen />
      <RoleModalGen />
    </div>
  )
}

Users.propTypes = {
  users: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({ users, loading }) => ({ users, loading: loading.models.users }))(Users)
