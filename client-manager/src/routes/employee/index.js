import React, {PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'

function Employee({location, dispatch, employee, loading}) {
  const {list, pagination, currentItem, modalVisible, modalType} = employee
  const modalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `employee/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'employee/hideModal'
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
        type: 'employee/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'employee/showModal',
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
        pathname: '/employee',
        query: {
          [fieldsValue.field]: fieldsValue.keyword
        }
      })) : dispatch(routerRedux.push({
        pathname: '/employee'
      }))
    },
    onAdd () {
      dispatch({
        type: 'employee/showModal',
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

Employee.propTypes = {
  employee: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool
}

export default connect(({employee, loading}) => ({employee, loading: loading.models.employee}))(Employee)
