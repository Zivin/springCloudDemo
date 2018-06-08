// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'styles/icon/iconfont.css'
import 'nprogress/nprogress.css'
import http from 'api/http'
import util from 'utils/util'
import validator from 'utils/validator'
import * as constant from 'utils/constant'

Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.$http = http
Vue.prototype.$util = util
Vue.prototype.$validator = validator

// 全局设置文件上传地址和token
Vue.prototype.fileUploadUrl = constant.FILE_UPLOAD_URL
Vue.prototype.fileUploadHeader = constant.FILE_UPLOAD_HEADER

Vue.component("ButtonGroup", resolve => require(['components/common/ButtonGroup'], resolve))
Vue.component("PaginationPage", resolve => require(['components/common/PaginationPage'], resolve))
Vue.component("ItemPage", resolve => require(['components/common/ItemPage'], resolve))
Vue.component("DefaultImage", resolve => require(['components/common/DefaultImage'], resolve))
Vue.component("RegionSelector", resolve => require(['components/common/RegionSelector'], resolve))

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
