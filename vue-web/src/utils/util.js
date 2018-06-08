import router from 'src/router/index'
import store from 'store/index'
import * as types from 'store/types'

export default {
  // 动态加载路由
  addRoutes: function (moduleList, systemName) {
    if (moduleList.length > 0) {
      let routeList = [];
      // 每个模块
      moduleList.forEach((module, index) => {
        let route = {
          name: module.name,
          path: '/',
          meta: {system: systemName},
          component: resolve => require(['pages/Home'], resolve),
          children: []
        };
        // 每个菜单
        module.children.forEach((menu, index) => {
          route.children.push({
            name: menu.name,
            path: module.path + menu.path,
            component: resolve => require(['pages/' + module.code + '/' + menu.code], resolve)
          });
        });
        routeList.push(route);
      });
      router.addRoutes(routeList);
    }
  },

  // 生成UUID
  getUuid: function () {
    function S4() {
      return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }

    return (S4() + S4() + S4() + S4() + S4() + S4() + S4() + S4());
  },

  // 判断是否拥有权限，并返回权限名称
  hasPermission: function (menuCode, permissionCode) {
    return store.getters[types.PERMISSIONS][menuCode + ':' + permissionCode];
  }
}
