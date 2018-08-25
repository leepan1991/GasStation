import React, { PropTypes } from 'react'
import { Router } from 'dva/router'
// import pathToRegexp from 'path-to-regexp'
import App from './routes/app'

const cached = {}
const registerModel = (app, model) => {
  if (!cached[model.namespace]) {
    app.model(model)
    cached[model.namespace] = 1
  }
}

const Routers = function ({ history, app }) {
  const handleChildRoute = (nextState, replace) => {
    let {location} = nextState
    if (location.pathname === '/') {
      replace({ pathname: '/gasFillingLog' })
    }
  }

  const routes = [
    {
      path: '/',
      component: App,
      getIndexRoute (nextState, cb) {
        require.ensure([], require => {
          registerModel(app, require('./models/gasFillingLog'))
          cb(null, { component: require('./routes/gasFillingLog/') })
        }, 'gasFillingLog')
      },
      childRoutes: [{
        path: 'system/org',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            registerModel(app, require('./models/org'))
            cb(null, require('./routes/org/'))
          }, 'org')
        }
      }, {
        path: 'system/users',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            registerModel(app, require('./models/users'))
            cb(null, require('./routes/users/'))
          }, 'users')
        }
      }, {
        path: 'system/role',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            registerModel(app, require('./models/role'))
            cb(null, require('./routes/role/'))
          }, 'role')
        }
      }, {
        path: 'system/permission',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            registerModel(app, require('./models/permission'))
            cb(null, require('./routes/permission/'))
          }, 'permission')
        }
      }, {
        path: 'gasBottle',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            registerModel(app, require('./models/gasBottle'))
            cb(null, require('./routes/gasBottle/'))
          }, 'gasBottle')
        }
      }, {
        path: 'gasFillingLog',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            registerModel(app, require('./models/gasFillingLog'))
            cb(null, require('./routes/gasFillingLog/'))
          }, 'gasFillingLog')
        }
      }, {
        path: 'customer',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            registerModel(app, require('./models/employee'))
            cb(null, require('./routes/employee/'))
          }, 'customer')
        }
      }, {
        path: 'order',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            registerModel(app, require('./models/order'))
            cb(null, require('./routes/order/'))
          }, 'order')
        }
      }, {
        path: '*',
        getComponent (nextState, cb) {
          require.ensure([], require => {
            cb(null, require('./routes/error/'))
          }, 'error')
        }
      }]
    }
  ]

  routes[0].onEnter = handleChildRoute
  routes[0].childRoutes.map(item => {
    item.onEnter = handleChildRoute
    return item
  })

  return <Router history={history} routes={routes} />
}

Routers.propTypes = {
  history: PropTypes.object,
  app: PropTypes.object,
}

export default Routers
