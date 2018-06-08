/**
 * @Project base-common
 * @Package com.cloud.base.enumeration
 * @FileName Status.java
 */

package com.cloud.base.enumeration;

/**
 * 实体类状态
 * @Author zivin
 * @Date 2017年10月8日
 */
public enum Status {

    /**
     * ENABLE 启用
     */
    ENABLE(0, "启用"),

    /**
     * DISABLE 禁用
     */
    DISABLE(1, "禁用");

    Status(Integer code, String msg) {
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
