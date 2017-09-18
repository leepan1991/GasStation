import React, {PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import List from './List'
import Filter from './Filter'

function GasFillingLog({location, dispatch, gasFillingLog, loading}) {
  const {list, pagination, currentItem, modalVisible, modalType} = gasFillingLog

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
        type: 'gasFillingLog/delete',
        payload: id
      })
    }
  }

  const filterProps = {
    location,
    onSearch (params) {
      dispatch(routerRedux.push({
        pathname: '/gasFillingLog',
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
      window.open('/manager/gasFillingLog/export?' + paramStr)
    }
  }

  return (
    <div className="content-inner">
      <Filter {...filterProps} />
      <List {...listProps} />
    </div>
  )
}

GasFillingLog.propTypes = {
  gasFillingLog: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.bool,
}

export default connect(({gasFillingLog, loading}) => ({gasFillingLog, loading: loading.models.gasFillingLog}))(GasFillingLog)
