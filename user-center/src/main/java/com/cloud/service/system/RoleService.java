/**
 * @Project user-center
 * @Package com.cloud.service
 * @FileName RoleService.java
 */

package com.cloud.service.system;

import com.cloud.base.service.BaseFormService;
import com.cloud.entity.system.role.Role;
import com.cloud.entity.system.role.RoleQo;
import com.cloud.entity.system.role.RoleVo;

/**
 * 角色管理service接口
 * @Author zivin
 * @Date 2017年11月15日
 */
public interface RoleService extends BaseFormService<Role, RoleQo, RoleVo> {

}
