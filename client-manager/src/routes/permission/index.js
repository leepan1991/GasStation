import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Row, Col } from 'antd'
import ParentTree from './ParentTree'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'

function Permission ({ location, dispatch, permission, loading }) {
  const { list, treeData, pagination, currentItem, modalVisible, modalType } = permission

  const modalProps = {
    item: modalType === 'create' ? {} : currentItem,
    treeData,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `permission/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'permission/hideModal'
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
        type: 'permission/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'permission/showModal',
        payload: {
          modalType: 'update',
          currentItem: item
        }
      })
    }
  }

  const filterProps = {
    location,
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/system/permission',
        query: {
          [fieldsValue.field]: fieldsValue.keyword
        }
      })) : dispatch(routerRedux.push({
        pathname: '/system/permission'
      }))
    },
    onAdd () {
      dispatch({
        type: 'permission/showModal',
        payload: {
          modalType: 'create',
        }
      })
    }
  }

  const parentTreeProps = {
    location,
    treeData,
    onParentSelect (data) {
      dispatch(routerRedux.push({
        pathname: '/system/permission',
        query: data
      }))
    }
  }

  const ModalGen = () =>
    <Modal {...modalProps} />

  return (
    <div className="content-inner">
      <Row gutter={16}>
        <Col span={4}>
          <ParentTree {...parentTreeProps} />
        </Col>
        <Col span={20}>
          <Filter {...filterProps} />
          <List {...listProps} />
        </Col>
      </Row>
      <ModalGen />
    </div>
  )
}

Permission.propTypes = {
  permission: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({ permission, loading }) => ({ permission, loading: loading.models.permission }))(Permission)
