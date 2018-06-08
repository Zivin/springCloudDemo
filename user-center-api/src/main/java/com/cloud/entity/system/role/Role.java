
package com.cloud.entity.system.role;

import com.cloud.base.entity.BaseEntity;

/**
 * 管理员角色实体
 * @Author zivin
 * @Date 2017年11月15日
 */
public class Role extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8199101853244737692L;

    /**
     * roleName 角色名称
     */
    private String roleName;

    /**
     * roleCode 角色编码
     */
    private String roleCode;

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
     * @return the roleCode
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * @param roleCode the roleCode to set
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Role [roleName=" + roleName + ", roleCode=" + roleCode + ", toString()=" + super.toString() + "]";
    }

}
