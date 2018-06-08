/**
 * @Project base-common
 * @Package com.cloud.config
 * @FileName UsernamePasswordUsertypeToken.java
 */

package com.cloud.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录token
 * @Author zivin
 * @Date 2017年10月11日
 */
public class AccountToken extends UsernamePasswordToken {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2478747960292953336L;

    private String accountType;

    /**
     * @return the accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public AccountToken(String loginName, String password, String accountType) {
        super(loginName, password);
        this.accountType = accountType;
    }

}
