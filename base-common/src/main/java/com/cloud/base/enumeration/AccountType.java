/**
 * @Project base-common
 * @Package com.cloud.base.enumeration
 * @FileName Status.java
 */

package com.cloud.base.enumeration;

import com.cloud.base.util.BaseStringUtils;

/**
 * 账号类型
 * @Author zivin
 * @Date 2017年10月8日
 */
public enum AccountType {

    /**
     * ADMIN 管理员
     */
    ADMIN("admin", "adminRealm", "管理员"),

    /**
     * USER 用户
     */
    USER("user", "userRealm", "用户");

    AccountType(String code, String realm, String msg) {
        this.code = code;
        this.realm = realm;
        this.msg = msg;
    }

    private String code;

    private String realm;

    private String msg;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the realm
     */
    public String getRealm() {
        return realm;
    }

    /**
     * @param realm the realm to set
     */
    public void setRealm(String realm) {
        this.realm = realm;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getRealm(String code) {
        if (BaseStringUtils.isBlank(code)) {
            return null;
        }
        for (AccountType accountType : AccountType.values()) {
            if (accountType.getCode().equals(code)) {
                return accountType.getRealm();
            }
        }
        return null;
    }
}
