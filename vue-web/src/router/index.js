import Vue from 'vue'
import Router from 'vue-router'
import NProgress from 'nprogress'
import {getToken} from 'api/auth'
import store from 'store/index'

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
        name: '个人设置',
        path: '/setting',
        component: resolve => require(['pages/Setting'], resolve),
      },
      {
        name: '我的消息',
        path: '/message',
        component: resolve => require(['pages/Message'], resolve),
      }
    ]
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

// 判断是否已经登录
router.beforeEach((to, from, next) => {
  NProgress.start();
  let token = getToken();
  if (to.meta.noRequireAuth || (token && token !== null)) {
    // 刷新后丢失菜单，重新进入主页
    if (to.matched[0].name == '404' && !store.state.menu.loadMenu) {
      next({path: '/'});
    } else {
      next();
    }
  } else {
    next({
      path: '/login',
      query: {redirect: to.fullPath}
    });
  }
})

router.afterEach(transition => {
  NProgress.done();
})

export default router
