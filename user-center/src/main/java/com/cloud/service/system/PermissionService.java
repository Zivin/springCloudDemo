/**
 * @Project user-center
 * @Package com.cloud.service.menu
 * @FileName MenuService.java
 */

package com.cloud.service.system;

import java.util.List;

import com.cloud.base.service.BaseFormService;
import com.cloud.entity.system.permission.Permission;
import com.cloud.entity.system.permission.PermissionQo;
import com.cloud.entity.system.permission.PermissionVo;

/**
 * 权限管理service接口
 * @Author zivin
 * @Date 2017年10月8日
 */
public interface PermissionService extends BaseFormService<Permission, PermissionQo, PermissionVo> {

    /**
     * 根据角色id查询菜单权限
     * @param roleId
     * @return
     */
    List<PermissionVo> getPermissionByRoleId(String roleId);

}
