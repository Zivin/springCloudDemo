import {USER_CENTER} from 'utils/constant'
// 系统主页API

// 用户中心路径前缀
const userCenter = '/userCenter';

// 获取登录验证码
export const GET_VERIFICATION_CODE = '/public/getVerificationCode';
// 登录获取有效token
export const LOGIN = '/public/login';
// 登录用户获取用户信息
export const ADMIN_GET_ADMIN = USER_CENTER + '/admin/getAdmin';
// 登录用户获取菜单信息
export const PERMISSION_GET_PERMISSION = USER_CENTER + '/permission/getPermission';
// 编辑保存管理员信息
export const ADMIN_UPDATE_ADMIN = USER_CENTER + '/admin/updateAdmin';
// 登录用户修改登录密码
export const ADMIN_UPDATE_PASSWORD = USER_CENTER + '/admin/updatePassword';
