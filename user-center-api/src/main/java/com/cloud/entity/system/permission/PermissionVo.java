/**
 * @Project user-center-api
 * @Package com.cloud.entity.permission
 * @FileName PermissionVo.java
 */

package com.cloud.entity.system.permission;

/**
 * 权限vo
 * @Author zivin
 * @Date 2017年10月15日
 */
public class PermissionVo extends Permission {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7775033063338484524L;

    /**
     * system 所属系统
     */
    private Integer system;

    /**
     * moduleName 模块名称
     */
    private String moduleName;

    /**
     * moduleCode 模块编码
     */
    private String moduleCode;

    /**
     * moduleIcon 模块图标
     */
    private String moduleIcon;

    /**
     * modulePath 模块路径
     */
    private String modulePath;

    /**
     * menuName 菜单名称
     */
    private String menuName;

    /**
     * menuCode 菜单编码
     */
    private String menuCode;

    /**
     * menuIcon 菜单图标
     */
    private String menuIcon;

    /**
     * menuPath 菜单路径
     */
    private String menuPath;

    /**
     * @return the system
     */
    public Integer getSystem() {
        return system;
    }

    /**
     * @param system the system to set
     */
    public void setSystem(Integer system) {
        this.system = system;
    }

    /**
     * @return the moduleName
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName the moduleName to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return the moduleCode
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * @param moduleCode the moduleCode to set
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * @return the moduleIcon
     */
    public String getModuleIcon() {
        return moduleIcon;
    }

    /**
     * @param moduleIcon the moduleIcon to set
     */
    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
    }

    /**
     * @return the modulePath
     */
    public String getModulePath() {
        return modulePath;
    }

    /**
     * @param modulePath the modulePath to set
     */
    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
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
     * @return the menuIcon
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * @param menuIcon the menuIcon to set
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * @return the menuPath
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * @param menuPath the menuPath to set
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PermissionVo [system=" + system + ", moduleName=" + moduleName + ", moduleCode=" + moduleCode
                + ", moduleIcon=" + moduleIcon + ", modulePath=" + modulePath + ", menuName=" + menuName + ", menuCode="
                + menuCode + ", menuIcon=" + menuIcon + ", menuPath=" + menuPath + ", toString()=" + super.toString()
                + "]";
    }

}
