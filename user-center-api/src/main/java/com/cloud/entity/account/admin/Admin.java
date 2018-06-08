/**
 * @Project user-center
 * @Package com.cloud.entity.admin
 * @FileName Admin.java
 */

package com.cloud.entity.account.admin;

import java.util.Date;

import com.cloud.base.entity.BaseEntity;

/**
 * 管理员实体
 * @Author zivin
 * @Date 2017年10月8日
 */
public class Admin extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1333350818721621137L;

    /**
     * account 账号
     */
    private String account;

    /**
     * userName 用户名
     */
    private String userName;

    /**
     * password 密码
     */
    private String password;

    /**
     * salt 密码盐值
     */
    private String salt;

    /**
     * avatar 用户头像
     */
    private String avatar;

    /**
     * roleId 角色ID
     */
    private String roleId;

    /**
     * realName 真实姓名
     */
    private String realName;

    /**
     * phone 手机号码
     */
    private String phone;

    /**
     * email 邮箱
     */
    private String email;

    /**
     * gender 性别：0未知，1男性，2女性
     */
    private Integer gender;

    /**
     * adminType 用户类型：0超级管理员，1普通管理员
     */
    private Integer adminType;

    /**
     * lastLoginTime 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

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
     * @return the realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName the realName to set
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return the adminType
     */
    public Integer getAdminType() {
        return adminType;
    }

    /**
     * @param adminType the adminType to set
     */
    public void setAdminType(Integer adminType) {
        this.adminType = adminType;
    }

    /**
     * @return the lastLoginTime
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime the lastLoginTime to set
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Admin [account=" + account + ", userName=" + userName + ", password=" + password + ", salt=" + salt
                + ", avatar=" + avatar + ", roleId=" + roleId + ", realName=" + realName + ", phone=" + phone
                + ", email=" + email + ", gender=" + gender + ", adminType=" + adminType + ", lastLoginTime="
                + lastLoginTime + ", toString()=" + super.toString() + "]";
    }

}
