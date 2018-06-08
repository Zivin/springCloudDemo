import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [
  {
    name: '登录',
    path: '/login',
    component: resolve => require(['pages/Login'], resolve),
    meta: {
      noRequireAuth: true
    }
  },
  {
    name: '首页',
    path: '/',
    component: resolve => require(['pages/Home'], resolve),
    children: [
      {
        name: 'main',
        path: '/main',
        component: resolve => require(['pages/home/Main'], resolve)
      },
      {
        name: 'discovery',
        path: '/discovery',
        component: resolve => require(['pages/home/Discovery'], resolve)
      },
      {
        name: 'mine',
        path: '/mine',
        component: resolve => require(['pages/home/Mine'], resolve)
      },
    ]
  },
  {
    name: '我的消息',
    path: '/message',
    component: resolve => require(['pages/Message'], resolve)
  },
  {
    name: '404',
    path: '*',
    component:
      resolve => require(['pages/NotFound'], resolve)
  }
]

const router = new Router({
  routes: routes
})

router.beforeEach((to, from, next) => {
  next();
})

router.afterEach(transition => {
})

export default router
