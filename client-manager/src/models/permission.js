import { create, remove, update, query, selectLoopMenu } from '../services/permission'
import { parse } from 'qs'

export default {

  namespace: 'permission',

  state: {
    list: [],
    treeData: [],
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
    setup ({ dispatch, history }) {
      history.listen(location => {
        if (location.pathname === '/system/permission') {
          dispatch({
            type: 'selectLoopMenu',
            payload: {},
          })
          dispatch({
            type: 'query',
            payload: location.query,
          })
        }
      })
    }
  },

  effects: {
    *query ({ payload }, { call, put, select }) {
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
    *selectLoopMenu ({ payload }, { call, put }) {
      const data = yield call(selectLoopMenu, payload)
      if (data && data.success) {
        yield put({
          type: 'selectLoopMenuSuccess',
          payload: {
            treeData: data.data
          }
        })
      }
    },
    *'delete' ({ payload }, { call, put }) {
      const data = yield call(remove, { id: payload })
      if (data && data.success) {
        yield put({ type: 'query' })
      }
    },
    *create ({ payload }, { call, put }) {
      yield put({ type: 'hideModal' })
      const data = yield call(create, payload)
      if (data && data.success) {
        yield put({ type: 'query' })
      }
    },
    *update ({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' })
      const id = yield select(({ permission }) => permission.currentItem.id)
      const newOne = { ...payload, id }
      const data = yield call(update, newOne)
      if (data && data.success) {
        yield put({ type: 'query' })
      }
    }
  },

  reducers: {
    querySuccess (state, action) {
      const { list, pagination } = action.payload
      return { ...state,
        list,
        pagination: {
          ...state.pagination,
          ...pagination,
        } }
    },
    selectLoopMenuSuccess (state, action) {
      const { treeData } = action.payload
      return { ...state, treeData }
    },
    showModal (state, action) {
      return { ...state, ...action.payload, modalVisible: true }
    },
    hideModal (state) {
      return { ...state, modalVisible: false }
    }
  }
}
