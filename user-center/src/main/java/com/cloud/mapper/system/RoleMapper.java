
package com.cloud.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cloud.base.mapper.BaseMapper;
import com.cloud.entity.system.role.Role;
import com.cloud.entity.system.role.RoleMenuPermission;
import com.cloud.entity.system.role.RoleQo;
import com.cloud.entity.system.role.RoleVo;

/**
 * 角色管理mapper
 * @Author zivin
 * @Date 2017年11月15日
 */
@Repository
public interface RoleMapper extends BaseMapper<Role, RoleQo, RoleVo> {

    /**
     * 获取角色菜单权限关联
     * @param roleId 角色 id
     * @return
     */
    List<RoleMenuPermission> getRoleMenuPermissionList(@Param("roleId") String roleId);

    /**
     * 禁用角色菜单权限
     * @param menuVo
     * @return
     */
    int disableRoleMenuPermission(RoleVo roleVo);

    /**
     * 更新角色菜单权限关联
     * @param menuVo
     * @return
     */
    int enableRoleMenuPermission(RoleVo roleVo);

    /**
     * 获取最大的角色编码
     * @return
     */
    String getMaxRoleCode();

    /**
     * 保存角色的菜单权限
     * @param roleVo
     * @return
     */
    int saveRoleMenuPermissionList(RoleVo roleVo);
}
