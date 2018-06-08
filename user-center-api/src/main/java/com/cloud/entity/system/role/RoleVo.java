/**
 * @Project user-center-api
 * @Package com.cloud.entity.role
 * @FileName RoleVo.java
 */

package com.cloud.entity.system.role;

import java.util.List;

/**
 * 管理员角色vo
 * @Author zivin
 * @Date 2017年11月15日
 */
public class RoleVo extends Role {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2585896928957634189L;

    /**
     * ids id列表
     */
    private List<String> ids;

    /**
     * roleMenuPermissionList 角色菜单权限列表
     */
    private List<RoleMenuPermission> roleMenuPermissionList;

    /**
     * @return the ids
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * @param ids the ids to set
     */
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    /**
     * @return the roleMenuPermissionList
     */
    public List<RoleMenuPermission> getRoleMenuPermissionList() {
        return roleMenuPermissionList;
    }

    /**
     * @param roleMenuPermissionList the roleMenuPermissionList to set
     */
    public void setRoleMenuPermissionList(List<RoleMenuPermission> roleMenuPermissionList) {
        this.roleMenuPermissionList = roleMenuPermissionList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleVo [ids=" + ids + ", roleMenuPermissionList=" + roleMenuPermissionList + ", toString()="
                + super.toString() + "]";
    }

}
