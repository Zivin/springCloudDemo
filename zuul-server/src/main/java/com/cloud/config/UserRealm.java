/**
 * @Project base-common
 * @Package com.cloud.config
 * @FileName AdminRealm.java
 */

package com.cloud.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.base.enumeration.AccountType;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.entity.account.admin.AdminVo;
import com.cloud.service.account.AdminServiceApi;

/**
 * 用户登录校验授权
 * @Author zivin
 * @Date 2017年10月11日
 */
public class UserRealm extends AuthorizingRealm {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private AdminServiceApi adminService;

    public UserRealm(CacheManager cacheManager, HashedCredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }

    /**
     * 验证当前登录的Subject 本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        // 这个authcToken是从LoginController里面currentUser.login(token)传过来的
        AccountToken token = (AccountToken) authcToken;
        if (BaseStringUtils.isBlank(token.getUsername())) {
            throw new IncorrectCredentialsException("username is null!");
        } else if (BaseStringUtils.isBlank(token.getPassword().toString())) {
            throw new IncorrectCredentialsException("password is null!");
        }
        AdminVo adminVo = adminService.getAdminByAccount(token.getUsername());
        // Shiro完成对比逻辑,返回和令牌相关的正确的验证信息，第一个参数填登录用户名,第二个参数填合法的登录密码
        if (adminVo == null) {
            throw new UnknownAccountException("The user does not exist");
        }
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(adminVo.getAccount(), adminVo.getPassword(),
                getName());
        this.setSession(AccountType.USER.getCode(), adminVo);
        return authcInfo;
        // 没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
    }

    /**
     * 为当前登录的Subject授予角色和权限 ,该方法的调用时机为需授权资源被访问时
     * 并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AdminVo adminVo = (AdminVo) SecurityUtils.getSubject().getSession().getAttribute(AccountType.USER.getCode());
        if (adminVo != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 查询用户授权信息
            // TODO
            /*
             * List<Permission> perList = permissionService.getShiro("O");
             * if (perList != null && perList.size() != 0) {
             * for (Permission permission : perList) {
             * info.addStringPermission(permission.getPmsnCode());
             * }
             * return info;
             * }
             */
        }
        return null;
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            Session session = subject.getSession();
            LOGGER.debug("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

}
