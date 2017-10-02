import React, {PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'

function Org({location, dispatch, org, loading}) {
  const {list, pagination, currentItem, modalVisible, modalType} = org
  const modalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `org/${modalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'org/hideModal'
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
        type: 'org/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'org/showModal',
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
        pathname: '/system/org',
        query: {
          [fieldsValue.field]: fieldsValue.keyword
        }
      })) : dispatch(routerRedux.push({
        pathname: '/system/org'
      }))
    },
    onAdd () {
      dispatch({
        type: 'org/showModal',
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

Org.propTypes = {
  org: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({org, loading}) => ({org, loading: loading.models.org}))(Org)
