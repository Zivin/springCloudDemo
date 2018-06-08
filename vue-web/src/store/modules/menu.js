import * as types from 'store/types'
import util from 'utils/util'

export default {
  state: {
    // 系统菜单名称
    systemName: types.INTEGRATE_SYSTEM,
    // 菜单列表
    menuList: {},
    // 记录是否已经加载菜单
    loadMenu: false
  },
  actions: {
    [types.BUILD_MENU]({commit}, menu) {
      commit(types.BUILD_MENU, menu);
      // 动态加载路由
      util.addRoutes(menu.menuList[types.INTEGRATE_SYSTEM], '集成系统');
      util.addRoutes(menu.menuList[types.USER_CENTER], '用户中心');
      util.addRoutes(menu.menuList[types.MESSAGE_CENTER], '信息中心');
    },
    [types.CHOOSE_SYSTEM]({commit}, system) {
      commit(types.CHOOSE_SYSTEM, system);
    },
    [types.CLEAR_MENU]({commit}) {
      commit(types.CLEAR_MENU);
    }
  },
  mutations: {
    // 登录后构建菜单
    [types.BUILD_MENU](state, menu) {
      state.systemName = menu.systemName;
      state.menuList = menu.menuList;
      state.loadMenu = true;
    },
    // 选择系统后记录系统菜单名称
    [types.CHOOSE_SYSTEM](state, system) {
      state.systemName = system;
    },
    // 清空菜单
    [types.CLEAR_MENU](state) {
      state.systemName = types.INTEGRATE_SYSTEM;
      state.menuList = {};
      state.loadMenu = false;
    },
  },
  getters: {
    // 根据所选系统名称获取相应菜单
    [types.MENUS]: (state) => {
      return state.menuList[state.systemName];
    },
    // 系统权限列表
    [types.PERMISSIONS]: (state) => {
      let permissions = {};
      // 集成系统
      buildPermission(state.menuList[types.INTEGRATE_SYSTEM], permissions);
      // 用户中心
      buildPermission(state.menuList[types.USER_CENTER], permissions);
      // 信息中心
      buildPermission(state.menuList[types.MESSAGE_CENTER], permissions);
      return permissions;
    }
  }
}

// 构建权限
function buildPermission(menuList, permissions) {
  if (menuList) {
    menuList.forEach((module, index) => {
      module.children.forEach((menu, index) => {
        menu.children.forEach((permission, index) => {
          permissions[menu.code + ':' + permission.code] = permission.name;
        })
      })
    });
  }
}
