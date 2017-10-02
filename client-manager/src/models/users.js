import {create, remove, update, updatePwd, query, assignRole} from '../services/users'
import {selectWhenAssignRole} from '../services/role'
import {listAll as listAllOrg} from '../services/org'
import {parse} from 'qs'

export default {

  namespace: 'users',

  state: {
    list: [],
    orgList: [],
    assignRoleList: [],
    currentItem: {},
    modalVisible: false,
    pwdModalVisible: false,
    roleModalVisible: false,
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
        if (location.pathname === '/system/users') {
          dispatch({
            type: 'query',
            payload: location.query
          })
          dispatch({
            type: 'listAllOrg'
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
    *listAllOrg ({payload}, {call, put}) {
      const data = yield call(listAllOrg, payload)
      if (data && data.success) {
        yield put({
          type: 'stateChange',
          payload: {
            orgList: data.data
          }
        })
      }
    },
    *'delete' ({payload}, {call, put}) {
      const data = yield call(remove, {id: payload})
      if (data && data.success) {
        yield put({type: 'query'})
      }
    },
    *create ({payload}, {call, put}) {
      yield put({type: 'hideModal'})
      const data = yield call(create, payload)
      if (data && data.success) {
        yield put({type: 'query'})
      }
    },
    *update ({payload}, {select, call, put}) {
      yield put({type: 'hideModal'})
      const id = yield select(({users}) => users.currentItem.id)
      const newUser = {...payload, id}
      const data = yield call(update, newUser)
      if (data && data.success) {
        yield put({type: 'query'})
      }
    },
    *updatePwd ({payload}, {select, call, put}) {
      yield put({type: 'hidePwdModal'})
      const id = yield select(({users}) => users.currentItem.id)
      const newUser = {...payload, id}
      const data = yield call(updatePwd, newUser)
      if (data && data.success) {
      }
    },
    *selectWhenAssignRole ({payload}, {call, put}) {
      const data = yield call(selectWhenAssignRole, {userId: payload.currentItem.id})
      if (data && data.success) {
        yield put({type: 'showRoleModal', payload: {currentItem: payload.currentItem, assignRoleList: data.data}})
      }
    },
    *assignRole ({payload}, {select, call, put}) {
      yield put({type: 'hideRoleModal'})
      const id = yield select(({users}) => users.currentItem.id)
      const newUser = {...payload, id}
      const data = yield call(assignRole, newUser)
      if (data && data.success) {
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
    stateChange (state, action) {
      return {...state, ...action.payload}
    },
    showModal (state, action) {
      return {...state, ...action.payload, modalVisible: true}
    },
    hideModal (state) {
      return {...state, modalVisible: false}
    },
    showPwdModal (state, action) {
      return {...state, ...action.payload, pwdModalVisible: true}
    },
    hidePwdModal (state) {
      return {...state, pwdModalVisible: false}
    },
    showRoleModal (state, action) {
      return {...state, ...action.payload, roleModalVisible: true}
    },
    hideRoleModal (state) {
      return {...state, roleModalVisible: false}
    }
  },

}
