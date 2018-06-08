/**
 * @Project user-center-api
 * @Package com.cloud.entity.menu
 * @FileName MenuPermission.java
 */

package com.cloud.entity.system.role;

import com.cloud.base.entity.BaseEntity;

/**
 * 角色菜单权限关联实体
 * @Author zivin
 * @Date 2017年11月11日
 */
public class RoleMenuPermission extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5033500926012641160L;

    /**
     * roleId 角色id
     */
    private String roleId;

    /**
     * roleName 角色名称
     */
    private String roleName;

    /**
     * menuId 菜单ID
     */
    private String menuId;

    /**
     * menuName 菜单名称
     */
    private String menuName;

    /**
     * permissionId 权限ID
     */
    private String permissionId;

    /**
     * permissionName 权限名称
     */
    private String permissionName;

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the menuId
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return the permissionId
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId the permissionId to set
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * @return the permissionName
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * @param permissionName the permissionName to set
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RoleMenuPermission [roleId=" + roleId + ", roleName=" + roleName + ", menuId=" + menuId + ", menuName="
                + menuName + ", permissionId=" + permissionId + ", permissionName=" + permissionName + ", toString()="
                + super.toString() + "]";
    }

}
