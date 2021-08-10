export default [{
  name: 'system',
  icon: 'smile',
  path: '/system',
  routes: [{
    name: 'user',
    path: '/system/user',
    component: './system/user'
  }, {
    name: 'role',
    path: '/system/role',
    component: './system/role'
  }]
}, {
  name: 'gasBottle',
  icon: 'smile',
  path: '/gasBottle/list',
  component: './gasBottle/list'
}, {
  name: 'gasBottleEdit',
  icon: 'smile',
  hideInMenu: true,
  path: '/gasBottle/edit/:id',
  component: './gasBottle/edit'
}, {
  path: '/user',
  layout: false,
  routes: [{
    path: '/user',
    routes: [{
      name: 'login',
      path: '/user/login',
      component: './user/Login',
    }],
  }, {
    component: './404',
  }],
}, {
  path: '/welcome',
  name: 'welcome',
  icon: 'smile',
  component: './Welcome',
  hideInMenu: true
}, {
  path: '/',
  redirect: '/welcome',
}, {
  component: './404',
},
];
