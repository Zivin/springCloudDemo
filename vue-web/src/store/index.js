import Vue from 'vue'
import Vuex from 'vuex'

import user from 'store/modules/user'
import menu from 'store/modules/menu'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    user: user,
    menu: menu
  }
})
