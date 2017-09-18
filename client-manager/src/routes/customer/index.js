import React, {PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'

function Customer({location, dispatch, customer, loading}) {
  const {list, pagination, currentItem, modalVisible, modalType} = customer
  const modalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `customer/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'customer/hideModal'
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
        type: 'customer/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'customer/showModal',
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
        pathname: '/customer',
        query: {
          [fieldsValue.field]: fieldsValue.keyword
        }
      })) : dispatch(routerRedux.push({
        pathname: '/customer'
      }))
    },
    onAdd () {
      dispatch({
        type: 'customer/showModal',
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

Customer.propTypes = {
  customer: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({customer, loading}) => ({customer, loading: loading.models.customer}))(Customer)
