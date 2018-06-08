/**
 * @Project user-center
 * @Package com.cloud.entity
 * @FileName PasswordVo.java
 */

package com.cloud.entity.account;

import java.io.Serializable;

/**
 * 修改密码vo
 * @Author zivin
 * @Date 2017年10月23日
 */
public class PasswordVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8101523497987405168L;

    /**
     * userId 用户id
     */
    private String userId;

    /**
     * oldPassword 旧密码
     */
    private String oldPassword;

    /**
     * newPassword 新密码
     */
    private String newPassword;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PasswordVo [userId=" + userId + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
    }

}
