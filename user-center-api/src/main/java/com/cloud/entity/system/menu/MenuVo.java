/**
 * @Project user-center-api
 * @Package com.cloud.entity.menu
 * @FileName MenuVo.java
 */

package com.cloud.entity.system.menu;

import java.util.List;

/**
 * 菜单vo
 * @Author zivin
 * @Date 2017年10月15日
 */
public class MenuVo extends Menu {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8538368399099785802L;

    /**
     * menuPermissionList 菜单权限列表
     */
    private List<MenuPermission> menuPermissionList;

    /**
     * @return the menuPermissionList
     */
    public List<MenuPermission> getMenuPermissionList() {
        return menuPermissionList;
    }

    /**
     * @param menuPermissionList the menuPermissionList to set
     */
    public void setMenuPermissionList(List<MenuPermission> menuPermissionList) {
        this.menuPermissionList = menuPermissionList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MenuVo [menuPermissionList=" + menuPermissionList + ", toString()=" + super.toString() + "]";
    }

}
