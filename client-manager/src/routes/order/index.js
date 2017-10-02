import React, {PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'

function Order({location, dispatch, order, loading}) {
  const {list, pagination, currentItem, modalVisible, modalType} = order
  const modalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `order/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'order/hideModal'
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
        type: 'order/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'order/showModal',
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
        pathname: '/order',
        query: {
          [fieldsValue.field]: fieldsValue.keyword
        }
      })) : dispatch(routerRedux.push({
        pathname: '/order'
      }))
    },
    onAdd () {
      dispatch({
        type: 'order/showModal',
        payload: {
          modalType: 'create',
        }
      })
    }
  }

  const ModalGen = () => <Modal {...modalProps} />

  return (
    <div className="content-inner">
      <Filter {...filterProps} />
      <List {...listProps} />
      <ModalGen />
    </div>
  )
}

Order.propTypes = {
  order: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({order, loading}) => ({order, loading: loading.models.order}))(Order)
