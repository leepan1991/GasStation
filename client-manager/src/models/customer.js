import {create, remove, update, query} from '../services/customer'
import {parse} from 'qs'
import {message} from 'antd'

export default {

  namespace: 'customer',

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
        if (location.pathname === '/customer') {
          dispatch({
            type: 'query',
            payload: location.query,
          })
        }
      })
    },
  },

  effects: {
    *query ({payload}, {call, put}) {
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
    *create ({payload}, {call, put}) {
      yield put({type: 'hideModal'})
      const data = yield call(create, payload)
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
    *update ({payload}, {select, call, put}) {
      yield put({type: 'hideModal'})
      const id = yield select(({customer}) => customer.currentItem.id)
      const newOne = {...payload, id}
      const data = yield call(update, newOne)
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
  },

}
