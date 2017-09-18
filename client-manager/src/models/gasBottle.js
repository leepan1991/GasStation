import {query, remove, updateNextCheckTime} from '../services/gasBottle'
import {parse} from 'qs'

export default {

  namespace: 'gasBottle',

  state: {
    list: [],
    currentItem: {},
    modalVisible: false,
    modalType: 'create',
    pagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null
    }
  },

  subscriptions: {
    setup ({dispatch, history}) {
      history.listen(location => {
        if (location.pathname === '/gasBottle' || location.pathname === '/') {
          dispatch({
            type: 'query',
            payload: location.query
          })
        }
      })
    }
  },

  effects: {
    *query ({payload}, {call, put, select}) {
      const location = yield select(state => state.routing.locationBeforeTransitions)
      if (!payload || Object.keys(payload).length == 0) {
        payload = location.query;
      }
      const data = yield call(query, parse(payload))
      if (data) {
        yield put({
          type: 'querySuccess',
          payload: {
            list: data.data.data,
            pagination: data.data.page
          }
        })
      }
    },
    *'delete' ({payload}, {call, put}) {
      const data = yield call(remove, {id: payload})
      if (data && data.success) {
        const queryData = yield call(query, {})
        if (queryData && queryData.success) {
          yield put({
            type: 'querySuccess',
            payload: {
              list: queryData.data.data,
              pagination: queryData.data.page
            }
          })
        }
      }
    },
    *updateNextCheckTime ({payload}, {call, put}) {
      yield put({type: 'hideModal'})
      const data = yield call(updateNextCheckTime, payload)
      if (data && data.success) {
        yield put({type: 'query'})
      }
    }
  },

  reducers: {
    querySuccess (state, action) {
      const {list, pagination} = action.payload
      return {
        ...state,
        list,
        pagination: {
          ...state.pagination,
          ...pagination,
        }
      }
    },
    showModal (state, action) {
      return {...state, ...action.payload, modalVisible: true}
    },
    hideModal (state) {
      return {...state, modalVisible: false}
    }
  }
}
