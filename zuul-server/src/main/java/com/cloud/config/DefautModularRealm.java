/**
 * @Project zuul-server
 * @Package com.cloud.config
 * @FileName DefautModularRealm.java
 */

package com.cloud.config;

import java.util.Map;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

import com.cloud.base.enumeration.AccountType;

/**
 * 管理员和用户登录校验判断入口
 * @Author zivin
 * @Date 2017年10月11日
 */
public class DefautModularRealm extends ModularRealmAuthenticator {

    private Map<String, Realm> definedRealms;

    public DefautModularRealm(Map<String, Realm> definedRealms) {
        super();
        this.definedRealms = definedRealms;
    }

    /**
     * 调用单个realm执行操作
     */
    @Override
    protected AuthenticationInfo doSingleRealmAuthentication(Realm realm, AuthenticationToken token) {

        // 如果该realms不支持(不能验证)当前token
        if (!realm.supports(token)) {
            throw new ShiroException("token错误!");
        }
        AuthenticationInfo info = null;
        try {
            info = realm.getAuthenticationInfo(token);

            if (info == null) {
                throw new ShiroException("token不存在!");
            }
        } catch (Exception e) {
            throw new ShiroException("用户名或者密码错误!");
        }
        return info;
    }

    /**
     * 判断登录类型执行操作
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) {
        this.assertRealmsConfigured();
        AccountToken token = (AccountToken) authenticationToken;
        Realm realm = this.definedRealms.get(AccountType.getRealm(token.getAccountType()));
        return this.doSingleRealmAuthentication(realm, authenticationToken);
    }

    /**
     * 判断realm是否为空
     */
    @Override
    protected void assertRealmsConfigured() {
        this.definedRealms = this.getDefinedRealms();
        if (CollectionUtils.isEmpty(this.definedRealms)) {
            throw new ShiroException("值传递错误!");
        }
    }

    public Map<String, Realm> getDefinedRealms() {
        return this.definedRealms;
    }

    public void setDefinedRealms(Map<String, Realm> definedRealms) {
        this.definedRealms = definedRealms;
    }

}
