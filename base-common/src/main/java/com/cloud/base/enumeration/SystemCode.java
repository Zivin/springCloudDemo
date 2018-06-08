/**
 * @Project base-common
 * @Package com.cloud.base.enumeration
 * @FileName Status.java
 */

package com.cloud.base.enumeration;

/**
 * 系统类型
 * @Author zivin
 * @Date 2017年10月8日
 */
public enum SystemCode {

    /**
     * INTEGRATE_SYSTEM 集成系统
     */
    INTEGRATE_SYSTEM(0, "integrateSystem"),

    /**
     * USER_CENTER 用户中心
     */
    USER_CENTER(1, "userCenter"),

    /**
     * MESSAGE_CENTER 信息中心
     */
    MESSAGE_CENTER(2, "messageCenter");

    SystemCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    void setCode(Integer code) {
        this.code = code;
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

    public static SystemCode getByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (SystemCode systemCode : SystemCode.values()) {
            if (code.equals(systemCode.getCode())) {
                return systemCode;
            }
        }
        return null;
    }
}
