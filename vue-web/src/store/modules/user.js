import * as types from 'store/types'
import {removeToken} from 'api/auth'

export default {
  state: {
    userId: '',
    userName: '欢迎您',
    userAvatar: ''
  },
  actions: {
    [types.BUILD_USER]({commit}, userInfo) {
      commit(types.BUILD_USER, userInfo);
    },
    [types.CLEAR_USER]({commit}) {
      commit(types.CLEAR_USER);
      // 清楚用户信息后清除菜单信息
      commit(types.CLEAR_MENU);
    },
  },
  mutations: {
    [types.BUILD_USER](state, userInfo) {
      state.userId = userInfo.id;
      state.userName = userInfo.userName;
      if (userInfo.avatar != null && userInfo.avatar != '') {
        state.userAvatar = userInfo.avatar;
      }
    },
    [types.CLEAR_USER](state) {
      state.userId = '';
      state.userName = '欢迎您';
      state.userAvatar = '';
      removeToken();
    },
  },
  getters: {
    [types.USERINFO]: (state) => {
      return {userId: state.userId, userName: state.userName, userAvatar: state.userAvatar};
    }
  }
};
