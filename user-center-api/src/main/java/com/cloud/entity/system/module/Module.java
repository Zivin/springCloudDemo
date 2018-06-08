
package com.cloud.entity.system.module;

import com.cloud.base.entity.BaseEntity;

/**
 * 模块实体
 * @Author zivin
 * @Date 2017年11月12日
 */
public class Module extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3102011928989000290L;

    /**
     * moduleName 模块名称
     */
    private String moduleName;

    /**
     * moduleCode 模块编码
     */
    private String moduleCode;

    /**
     * icon 模块图标
     */
    private String icon;

    /**
     * path 模块路径
     */
    private String path;

    /**
     * system 所属系统：0集成系统，1用户中心，2消息中心
     */
    private Integer system;

    /**
     * sort 排序
     */
    private Integer sort;

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
        return "Module [moduleName=" + moduleName + ", moduleCode=" + moduleCode + ", icon=" + icon + ", path=" + path
                + ", system=" + system + ", sort=" + sort + ", toString()=" + super.toString() + "]";
    }

}
