
package com.cloud.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cloud.base.mapper.BaseMapper;
import com.cloud.entity.system.permission.Permission;
import com.cloud.entity.system.permission.PermissionQo;
import com.cloud.entity.system.permission.PermissionVo;

/**
 * 权限管理mapper
 * @Author zivin
 * @Date 2017年10月15日
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission, PermissionQo, PermissionVo> {

    /**
     * 根据角色id查询菜单权限
     * @param roleId
     * @return
     */
    List<PermissionVo> getPermissionByRoleId(@Param("roleId") String roleId);
}
