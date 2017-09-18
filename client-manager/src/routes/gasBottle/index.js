import React, {PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import List from './List'
import Filter from './Filter'
import Modal from './Modal'

function GasBottle({location, dispatch, gasBottle, loading}) {
  const {list, pagination, currentItem, modalVisible} = gasBottle

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

  return (
    <div className="content-inner">
      <Filter {...filterProps} />
      <List {...listProps} />
      <ModalGen />
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
