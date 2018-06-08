/**
 * @Project base-common
 * @Package com.cloud.base.enumeration
 * @FileName BaseResultCode.java
 */

package com.cloud.base.enumeration;

/**
 * 返回结果编码
 * @Author zivin
 * @Date 2017年9月30日
 */
public enum BaseResultCode {

    /**
     * SUCCESS 成功
     */
    SUCCESS(0, "成功"),

    /**
     * FAILURE 失败
     */
    FAILURE(1, "操作失败"),

    /**
     * ILLEGAL_TOKEN 非法token
     */
    ILLEGAL_TOKEN(11, "非法token"),

    /**
     * OVERDUE_TOKEN token过期
     */
    OVERDUE_TOKEN(12, "token过期"),

    /**
     * EMPTY_PARAM 参数为空
     */
    EMPTY_PARAM(13, "参数为空"),

    /**
     * INVALID_PARAM 无效参数
     */
    INVALID_PARAM(14, "无效参数"),

    /**
     * LOGIN_ERROR 账号或密码错误
     */
    LOGIN_ERROR(101, "账号或密码错误"),

    /**
     * ACCOUNT_UNUSABLE 账号不可用
     */
    ACCOUNT_UNUSABLE(102, "账号不可用"),

    /**
     * VALIDATION_EXPIRED 验证码过期
     */
    VALIDATION_EXPIRED(103, "验证码过期"),

    /**
     * INVALID_VALIDATION 无效验证码
     */
    INVALID_VALIDATION(104, "无效验证码"),
    
    /**
     * INVALID_RESULT 返回无效数据
     */
    INVALID_RESULT(105, "返回无效数据");

    BaseResultCode(Integer code, String msg) {
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
