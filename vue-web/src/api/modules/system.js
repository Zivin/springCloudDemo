import {USER_CENTER} from 'utils/constant'
// 系统管理API

// 权限管理
// 获取权限列表
export const PERMISSION_QUERY = USER_CENTER + '/permission/query';
// 获取权限信息
export const PERMISSION_GET = USER_CENTER + '/permission/get';
// 修改权限信息
export const PERMISSION_EDIT = USER_CENTER + '/permission/edit';

// 菜单管理
// 获取菜单列表
export const MENU_QUERY = USER_CENTER + '/menu/query';
// 获取菜单信息
export const MENU_GET = USER_CENTER + '/menu/get';
// 修改菜单信息
export const MENU_EDIT = USER_CENTER + '/menu/edit';
// 获取菜单权限列表
export const MENU_QUERY_PERMISSION = USER_CENTER + '/menu/queryPermission';

// 模块管理
// 获取模块列表
export const MODULE_QUERY = USER_CENTER + '/module/query';
// 获取模块信息
export const MODULE_GET = USER_CENTER + '/module/get';
// 修改模块信息
export const MODULE_EDIT = USER_CENTER + '/module/edit';

// 角色管理
// 获取角色列表
export const ROLE_QUERY = USER_CENTER + '/role/query';
// 获取角色信息
export const ROLE_GET = USER_CENTER + '/role/get';
// 修改角色信息
export const ROLE_EDIT = USER_CENTER + '/role/edit';
// 新增角色信息
export const ROLE_ADD = USER_CENTER + '/role/add';
// 删除角色信息
export const ROLE_DELETE = USER_CENTER + '/role/delete';
