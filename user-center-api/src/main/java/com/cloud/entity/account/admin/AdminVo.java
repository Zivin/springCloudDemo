/**
 * @Project user-center
 * @Package com.cloud.entity.admin
 * @FileName AdminVo.java
 */

package com.cloud.entity.account.admin;

import java.util.List;

/**
 * 管理员vo
 * @Author zivin
 * @Date 2017年10月8日
 */
public class AdminVo extends Admin {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3063469381064137386L;

    /**
     * ids id列表
     */
    private List<String> ids;

    /**
     * roleName 角色名称
     */
    private String roleName;

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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AdminVo [ids=" + ids + ", roleName=" + roleName + ", toString()=" + super.toString() + "]";
    }

}
