/**
 * @Project zuul-service
 * @Package com.cloud.constant
 * @FileName ZuulServiceConstant.java
 */

package com.cloud.constant;

/**
 * zuul常量 
 * @Author zivin
 * @Date 2017年9月19日
 */
public class ZuulServiceConstant {

    /**
     * PRE 过滤器类型:路由之前
     */
    public static final String PRE = "pre";

    /**
     * routing 过滤器类型:路由之时
     */
    public static final String ROUTING = "routing";

    /**
     * POST 过滤器类型:路由之后
     */
    public static final String POST = "post";

    /**
     * ERROR 过滤器类型:发送错误调用
     */
    public static final String ERROR = "error";
}
