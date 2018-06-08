
package com.cloud.entity.system.permission;

import com.cloud.base.entity.BaseEntity;

/**
 * 权限实体
 * @Author zivin
 * @Date 2017年10月15日
 */
public class Permission extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 227756465287027009L;

    /**
     * permissionName 权限名称
     */
    private String permissionName;

    /**
     * permissionCode 权限编码
     */
    private String permissionCode;

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

    /**
     * @return the permissionCode
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * @param permissionCode the permissionCode to set
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Permission [permissionName=" + permissionName + ", permissionCode=" + permissionCode + ", toString()="
                + super.toString() + "]";
    }

}
