// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Mint from 'mint-ui'
import 'mint-ui/lib/style.css'
//import http from 'api/http'
import util from 'utils/util'
import * as constant from 'utils/constant'

Vue.use(Mint)

Vue.config.productionTip = false
//Vue.prototype.$http = http
Vue.prototype.$util = util

// 全局设置文件上传地址和token
Vue.prototype.fileUploadUrl = constant.FILE_UPLOAD_URL
Vue.prototype.fileUploadHeader = constant.FILE_UPLOAD_HEADER

Vue.component("HorizontalDivider", {template: "<div class='horizontal-divider'></div>"})
Vue.component("VerticalDivider", {template: "<div class='vertical-divider'></div>"})
Vue.component("SemitransparentTextBox", resolve => require(['components/common/SemitransparentTextBox'], resolve))

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
