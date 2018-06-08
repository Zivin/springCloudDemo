/**
 * @Project base-common
 * @Package com.cloud.base.enumeration
 * @FileName Status.java
 */

package com.cloud.base.enumeration;

/**
 * 删除标志
 * @Author zivin
 * @Date 2017年10月8日
 */
public enum Disabled {

    /**
     * NO 未删除
     */
    NO(0, "未删除"),

    /**
     * YES 已删除
     */
    YES(1, "已删除");

    Disabled(Integer code, String msg) {
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
