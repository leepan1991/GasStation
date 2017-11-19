import React, {PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'
import LocationModal from './LocationModal'

function GasBottle({location, dispatch, gasBottle, loading}) {
  const {list, locationList, pagination, currentItem, modalVisible, locationModalVisible} = gasBottle

  const modalProps = {
    item: currentItem,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `gasBottle/updateNextCheckTime`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'gasBottle/hideModal'
      })
    }
  }

  const localtionModalProps = {
    locationList,
    item: currentItem,
    visible: locationModalVisible,
    onOk (data) {
      dispatch({
        type: 'gasBottle/hideLocationModal'
      })
    },
    onCancel () {
      dispatch({
        type: 'gasBottle/hideLocationModal'
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
        type: 'gasBottle/delete',
        payload: id
      })
    },
    onEditCheckTime (item) {
      dispatch({
        type: 'gasBottle/showModal',
        payload: {
          currentItem: item
        }
      })
    },
    onShowLocation (item) {
      if (location.query.orderId) {
        dispatch({
          type: 'gasBottle/findLocation',
          payload: {
            currentItem: item,
            orderId: location.query.orderId
          }
        })
      } else {
        dispatch({
          type: 'gasBottle/showLocationModal',
          payload: {
            currentItem: item
          }
        })
      }
    }
  }

  const filterProps = {
    location,
    onSearch (params) {
      dispatch(routerRedux.push({
        pathname: '/gasBottle',
        query: params
      }))
    },
    onExport: function(params) {
      let paramStr = ''
      Object.keys(params).forEach((item, index)=> {
        if (index !== 0) {
          paramStr += '&'
        }
        if (params[item]) {
          paramStr = paramStr + item + '=' + params[item]
        }
      })
      window.open('/manager/gasBottle/export?' + paramStr)
    }
  }

  const ModalGen = () => <Modal {...modalProps} />
  const LocationModalGen = () => <LocationModal {...localtionModalProps} />

  return (
    <div className="content-inner">
      <Filter {...filterProps} />
      <List {...listProps} />
      <ModalGen />
      <LocationModalGen />
    </div>
  )
}

GasBottle.propTypes = {
  gasBottle: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({gasBottle, loading}) => ({gasBottle, loading: loading.models.gasBottle}))(GasBottle)
