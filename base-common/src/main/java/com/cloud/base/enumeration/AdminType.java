/**
 * @Project base-common
 * @Package com.cloud.base.enumeration
 * @FileName Status.java
 */

package com.cloud.base.enumeration;

/**
 * 管理员类型
 * @Author zivin
 * @Date 2017年10月8日
 */
public enum AdminType {

    /**
     * SUPER 超级管理员
     */
    SUPER(0, "超级管理员"),

    /**
     * NORMAL 普通管理员
     */
    NORMAL(1, "普通管理员");

    AdminType(Integer code, String msg) {
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
}
