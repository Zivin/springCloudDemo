/**
 * @Project base-common
 * @Package com.cloud.base.interaction
 * @FileName BaseResult.java
 */

package com.cloud.base.entity;

import java.io.Serializable;

import com.cloud.base.enumeration.BaseResultCode;

/**
 * 接口统一返回结果
 * @Author zivin
 * @Date 2017年9月30日
 */
public class BaseResult implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 302541276693581431L;

    /**
     * code 返回码
     */
    private Integer code;

    /**
     * msg 返回信息
     */
    private String msg;

    /**
     * data 返回数据
     */
    private transient Object data;

    /**
     * @param code
     * @param msg
     * @param data
     */
    private BaseResult(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 请求成功
     * @return
     */
    public static BaseResult success() {
        return success(null);
    }

    /**
     * 请求成功
     * @param data
     * @return
     */
    public static BaseResult success(Object data) {
        return new BaseResult(BaseResultCode.SUCCESS.getCode(), BaseResultCode.SUCCESS.getMsg(), data);
    }

    /**
     * 请求失败
     * @return
     */
    public static BaseResult failure() {
        return failure(BaseResultCode.FAILURE.getMsg());
    }

    /**
     * 请求失败
     * @param msg
     * @return
     */
    public static BaseResult failure(String msg) {
        return new BaseResult(BaseResultCode.FAILURE.getCode(), msg, null);
    }

    /**
     * 请求失败
     * @param failureCode
     * @return
     */
    public static BaseResult failure(BaseResultCode failureCode) {
        return new BaseResult(failureCode.getCode(), failureCode.getMsg(), null);
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
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
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

}
