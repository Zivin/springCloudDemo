/**
 * @Project base-common
 * @Package com.cloud.base.interaction
 * @FileName BaseController.java
 */

package com.cloud.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cloud.base.constant.WebConstant;
import com.cloud.base.util.BaseRedisUtils;

/**
 * controller基类
 * @Author zivin
 * @Date 2017年10月7日
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseRedisUtils baseRedisUtils;

    protected String getToken() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getHeader(WebConstant.X_TOKEN);
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    protected Object getCurrentAdmin() {
        return baseRedisUtils.getAdmin(getToken());
    }

    /**
     * 设置当前登录用户信息
     * @param admin
     */
    protected void setCurrentAdmin(Object admin) {
        baseRedisUtils.setAdmin(getToken(), admin);
    }

    /**
     * 获取当前登录用户权限信息
     * @return
     */
    protected Object getMenu() {
        return baseRedisUtils.getMenu(getToken());
    }

    /**
     * 设置当前登录用户权限信息
     * @param list
     */
    protected void setMenu(Object list) {
        baseRedisUtils.setMenu(getToken(), list);
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    protected Object getCurrentUser() {
        return baseRedisUtils.getUser(getToken());
    }

    /**
     * 设置当前登录用户信息
     * @param user
     */
    protected void setCurrentUser(Object user) {
        baseRedisUtils.setUser(getToken(), user);
    }

}
