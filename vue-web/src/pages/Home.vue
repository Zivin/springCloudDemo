<!--首页-->
<template>
  <div class="container">
    <!--头部-->
    <el-row class="header">
      <el-col style="width: auto">
        <div class="logo"><img :src="this.logo"/></div>
      </el-col>
      <el-col style="height: 100%; width: auto;">
        <el-menu :default-active="this.types.INTEGRATE_SYSTEM" mode="horizontal"
                 @select="this.chooseSystem">
          <el-menu-item :index="this.types.INTEGRATE_SYSTEM">集成系统</el-menu-item>
          <el-menu-item :index="this.types.USER_CENTER">用户中心</el-menu-item>
          <el-menu-item :index="this.types.MESSAGE_CENTER">信息中心</el-menu-item>
        </el-menu>
      </el-col>
      <el-col class="userinfo">
        <el-dropdown trigger="hover">
          <span class="el-dropdown-link userinfo-inner">{{this.userName}}<default-image :src="this.userAvatar"/></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="myMessage">我的消息</el-dropdown-item>
            <el-dropdown-item @click.native="personalSetting">个人设置</el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
    <el-row class="divider"></el-row>
    <!--主页面-->
    <el-row class="main">
      <!--导航菜单-->
      <el-col style="height: 100%; width: auto;">
        <el-menu class="el-menu-vertical-demo" @open="handleopen" @close="handleclose"
                 @select="handleselect" unique-opened router :collapse="collapsed">
          <div class="tools" @click.prevent="collapse">
            <i class="el-icon-minus" style="padding:10px 45%"></i>
          </div>
          <template v-for="(item,index) in this.menus">
            <!--子菜单-->
            <el-submenu :index="item.path">
              <template slot="title">
                <i :class="item.icon?item.icon:'el-icon-plus'"></i>
                <span slot="title">{{item.name}}</span>
              </template>
              <el-menu-item v-for="(child,i) in item.children" :index="item.path + child.path" :key="child.path">
                <i :class="child.icon?child.icon:'el-icon-menu'"></i>
                <span slot="title">{{child.name}}</span>
              </el-menu-item>
            </el-submenu>
          </template>
        </el-menu>
      </el-col>
      <el-col class="whole-container">
        <el-row class="breadcrumb-container">
          <div class="breadcrumb-title">
            {{this.$route.name}}
          </div>
          <div class="breadcrumb-title-refresh">
            <el-button type="text" size="mini" @click="refresh">刷新</el-button>
          </div>
          <el-breadcrumb separator="/" class="breadcrumb-inner">
            <el-breadcrumb-item :to="{path:'/'}" v-if="this.$route.matched[0].name!='首页'">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{path:'/'}" v-if="this.$route.matched[0].name!='首页'">
              {{this.$route.matched[0].meta.system}}
            </el-breadcrumb-item>
            <el-breadcrumb-item v-for="item in this.$route.matched" :key="item.path"
                                :to="{path:item.path?item.path:'/'}">
              {{ item.name }}
            </el-breadcrumb-item>
          </el-breadcrumb>
        </el-row>
        <el-row class="content-container">
          <section class="content-wrapper">
            <transition name="fade" mode="out-in">
              <router-view :key="this.contentKey"></router-view>
            </transition>
          </section>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import {mapGetters} from 'vuex'
  import {mapActions} from 'vuex'
  import {ADMIN_GET_ADMIN, PERMISSION_GET_PERMISSION} from 'api/modules/login'
  import * as types from 'store/types'

  export default {
    data() {
      return {
        logo: require('../assets/logo.png'),
        collapsed: false,
        types: types,
        contentKey: 1
      };
    },
    computed: {
      ...mapState({
        userName: state => state.user.userName,
        userAvatar: state => state.user.userAvatar
      }),
      ...mapGetters({
        menus: types.MENUS
      })
    },
    methods: {
      ...mapActions({
        buildUser: types.BUILD_USER,
        clearUser: types.CLEAR_USER,
        chooseSystem: types.CHOOSE_SYSTEM,
        buildMenu: types.BUILD_MENU
      }),
      refresh() {
        this.contentKey = 1 - this.contentKey;
      },
      handleopen() {
      },
      handleclose() {
      },
      handleselect(index, indexPath) {
      },
      // 我的消息
      myMessage: function () {
        this.$router.push({path: '/message'});
      },
      // 个人设置
      personalSetting: function () {
        this.$router.push({path: '/setting'});
      },
      //退出登录
      logout: function () {
        this.$confirm('确认退出吗?', '提示', {
          //type: 'warning'
        }).then(() => {
          this.clearUser().then(() => {
            location.reload()// 为了重新实例化vue-router对象 避免bug
          })
        }).catch(() => {
        });
      },
      //折叠导航栏
      collapse: function () {
        this.collapsed = !this.collapsed;
      },
    },
    mounted() {
      this.$http.get(ADMIN_GET_ADMIN, {}, response => {
        if (response.code == 0) {
          this.buildUser(response.data);
        }
      }, error => {
      });
      this.$http.get(PERMISSION_GET_PERMISSION, {}, response => {
        if (response.code == 0) {
          this.buildMenu(response.data);
          // 如果存在重定向参数，则跳转到重定向页面
          if (this.$route.params.redirect) {
            this.$router.push({path: this.$route.params.redirect});
          }
        }
      }, error => {
      });
    }
  }
</script>

<style scoped lang="scss">
  @import '../styles/vars';

  .container {
    .header {
      height: 60px;
      width: 100%;
      line-height: 60px;
      background: $color-primary;
      color: $color-main;
      .userinfo {
        height: 60px;
        width: auto;
        float: right;
        .userinfo-inner {
          cursor: pointer;
          color: $color-main;
          img {
            width: 40px;
            height: 40px;
            border-radius: 5px;
            margin: 10px 10px 10px 10px;
            float: right;
          }
        }
      }
      .logo {
        img {
          height: 60px;
          float: left;
        }
      }
    }
    .divider {
      background: $color-main;
      height: 1px;
    }
    .main {
      // height: 100%;
      min-height: 500px;
      width: 100%;
      display: flex;
      // background: #324057;
      position: absolute;
      top: 61px;
      bottom: 0px;
      //overflow: hidden;
      .tools {
        height: 30px;
        cursor: pointer;
      }
      .el-menu-vertical-demo {
        height: 100%;
      }
      .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
      }
      .whole-container {
        flex: 1;
        height: 100%;
        width: 100%;
        flex-flow: column;
        display: flex;
        .breadcrumb-container {
          width: 100%;
          height: 40px;
          background: #f1f2f7;
          .breadcrumb-title {
            width: auto;
            float: left;
            margin: 10px;
          }
          .breadcrumb-title-refresh {
            width: auto;
            float: left;
            margin: 8px;
          }
          .breadcrumb-inner {
            width: auto;
            float: right;
            margin: 15px;
          }
        }
        .content-container {
          height: 100%;
          flex: 1;
          overflow: auto;
          padding: 20px 20px 20px 20px;
          width: 100%;
          .content-wrapper {
            position: relative;
          }
        }
      }
    }
  }
</style>

<style>
  .search-param-style {
    width: 200px;
  }
</style>
