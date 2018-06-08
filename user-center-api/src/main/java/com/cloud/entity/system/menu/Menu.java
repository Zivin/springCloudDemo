
package com.cloud.entity.system.menu;

import com.cloud.base.entity.BaseEntity;

/**
 * 菜单实体
 * @Author zivin
 * @Date 2017年10月15日
 */
public class Menu extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2202651740498299672L;

    /**
     * menuName 菜单名称
     */
    private String menuName;

    /**
     * menuCode 菜单编码
     */
    private String menuCode;

    /**
     * icon 菜单图标
     */
    private String icon;

    /**
     * path 菜单路径
     */
    private String path;

    /**
     * moduleId 模块ID
     */
    private String moduleId;

    /**
     * sort 排序
     */
    private Integer sort;

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
     * @return the menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * @param menuCode the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the moduleId
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * @param moduleId the moduleId to set
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * @return the sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Menu [menuName=" + menuName + ", menuCode=" + menuCode + ", icon=" + icon + ", path=" + path
                + ", moduleId=" + moduleId + ", sort=" + sort + ", toString()=" + super.toString() + "]";
    }

}
