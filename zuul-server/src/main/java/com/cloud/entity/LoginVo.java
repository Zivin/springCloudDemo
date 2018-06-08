/**
 * @Project zuul-server
 * @Package com.cloud.entity
 * @FileName LoginVo.java
 */

package com.cloud.entity;

import java.io.Serializable;

/**
 * 登录vo
 * @Author zivin
 * @Date 2017年10月27日
 */
public class LoginVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2216299763826160269L;

    /**
     * account 账号
     */
    private String account;

    /**
     * password 密码
     */
    private String password;

    /**
     * verificationCode 验证码
     */
    private String verificationCode;

    /**
     * verificationId 验证码id
     */
    private String verificationId;

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
     * @return the verificationCode
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * @param verificationCode the verificationCode to set
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    /**
     * @return the verificationId
     */
    public String getVerificationId() {
        return verificationId;
    }

    /**
     * @param verificationId the verificationId to set
     */
    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LoginVo [account=" + account + ", password=" + password + ", verificationCode=" + verificationCode
                + ", verificationId=" + verificationId + "]";
    }

}
