import axios from 'axios'
import {Message, MessageBox} from 'element-ui'
import store from 'src/store'
import {getToken} from 'api/auth'
import * as resultCode from 'api/resultCode'
import * as types from 'store/types'
import NProgress from 'nprogress'

// 创建axios实例
const http = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 15000                 // 请求超时时间
})

// get请求
http.get = function (url, params, callback, error) {
  http({
    url: url,
    method: 'get',
    params: params
  }).then(callback).catch(error);
}

// post请求
http.post = function (url, data, callback, error) {
  http({
    url: url,
    method: 'post',
    data: data
  }).then(callback).catch(error);
}

// request拦截器
http.interceptors.request.use(
  config => {
    if (getToken()) {
      // 让每个请求携带自定义token 请根据实际情况自行修改
      config.headers['X-Token'] = getToken();
    }
    NProgress.start();
    return config;
  },
  error => {
    NProgress.done();
    Message.error('加载超时');
    Promise.reject(error);
  })

// response拦截器
http.interceptors.response.use(
  response => {
    NProgress.done();
    const res = response.data
    // 11:非法的token; 12:Token 过期;
    if (res.code === resultCode.ILLEGAL_TOKEN || res.code === resultCode.OVERDUE_TOKEN) {
      MessageBox.confirm('你已被登出，请重新登录', '确认登出', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        store.dispatch(types.CLEAR_USER).then(() => {
          location.reload()// 为了重新实例化vue-router对象 避免bug
        });
      })
      return Promise.reject('error')
    }

    // code为非0是业务逻辑错误
    if (res.code !== 0) {
      if (res.code > 100) {
        Message.error(res.msg);
      } else if (res.code === 1 && res.msg) {
        Message.error(res.msg);
      } else {
        Message.error('网络请求失败，请重试');
      }
    }
    return response.data
  },
  (error) => {
    NProgress.done();
    if (error.response) {
      switch (error.response.status) {
        case 401:
          MessageBox.confirm('授权失效，请重新登录', '授权失效', {
            confirmButtonText: '重新登录',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            store.dispatch(types.CLEAR_USER).then(() => {
              location.reload()// 为了重新实例化vue-router对象 避免bug
            });
          });
          break;
        case 403:
          Message.error(error.response.data);
          break;
        default:
          Message.error(error.response.data.message);
          break;
      }
    } else {
      Message.error(error.message);
    }

    return Promise.reject(error);
  }
)

export default http
