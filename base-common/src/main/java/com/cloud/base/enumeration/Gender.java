/**
 * @Project base-common
 * @Package com.cloud.base.enumeration
 * @FileName Status.java
 */

package com.cloud.base.enumeration;

/**
 * 性别
 * @Author zivin
 * @Date 2017年10月8日
 */
public enum Gender {

    /**
     * UNKNOW 未知
     */
    UNKNOW(0, "未知"),

    /**
     * MALE 男性
     */
    MALE(1, "男性"),

    /**
     * FEMALE 女性
     */
    FEMALE(2, "女性");

    Gender(Integer code, String msg) {
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
