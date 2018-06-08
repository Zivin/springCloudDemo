/**
 * @Project zuul-service
 * @Package com.cloud.filter
 * @FileName AuthorizationFilter.java
 */

package com.cloud.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloud.base.constant.WebConstant;
import com.cloud.base.util.BaseRedisUtils;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.constant.ZuulServiceConstant;
import com.cloud.entity.account.admin.AdminVo;
import com.cloud.entity.account.user.UserVo;
import com.cloud.entity.system.permission.PermissionVo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 权限过滤器
 * @Author zivin
 * @Date 2017年9月19日
 */
@Component
public class AuthorizationFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Autowired
    private BaseRedisUtils baseRedisUtils;

    /*
     * (non-Javadoc)
     * @see com.netflix.zuul.IZuulFilter#run()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader(WebConstant.X_TOKEN);
        String url = request.getRequestURL().toString();

        // mobile接口只需判断登录权限
        if (url.contains("/mobile/")) {
            UserVo userVo = (UserVo) baseRedisUtils.getUser(token);
            if (userVo != null) {
                baseRedisUtils.setUser(token, userVo);
            }
            return null;
        }

        // 如果有登录信息，每次请求将登录信息时间延长
        AdminVo adminVo = (AdminVo) baseRedisUtils.getAdmin(token);
        if (adminVo != null) {
            baseRedisUtils.setAdmin(token, adminVo);
        }

        // 校验权限
        // 直接通过不需要登录权限的请求
        if (url.contains("/public/")) {
            return null;
        }

        // 最后两段路径包含大写字母的为只需要校验登录的接口，不判断用户权限
        String[] codes = url.split("/");
        String permission = codes[codes.length - 1];
        String menu = codes[codes.length - 2];
        if ((BaseStringUtils.containsUpperCase(permission) || BaseStringUtils.containsUpperCase(menu))
                && adminVo != null) {
            return null;
        }

        // 校验用户权限
        List<PermissionVo> list = (List<PermissionVo>) baseRedisUtils.getMenu(token);
        if (CollectionUtils.isNotEmpty(list)) {
            for (PermissionVo permissionVo : list) {
                if (permissionVo.getMenuCode().equalsIgnoreCase(menu)
                        && permissionVo.getPermissionCode().equalsIgnoreCase(permission)) {
                    // 包含该权限，允许用户访问
                    return null;
                }
            }
        }

        // 校验后，登录用户没有该访问权限，返回错误信息
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(403);
        ctx.getResponse().setCharacterEncoding("UTF-8");
        ctx.setResponseBody("您没有访问权限，如有需要请请联系管理员");

        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.netflix.zuul.IZuulFilter#shouldFilter()
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.netflix.zuul.ZuulFilter#filterOrder()
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see com.netflix.zuul.ZuulFilter#filterType()
     */
    @Override
    public String filterType() {
        return ZuulServiceConstant.PRE;
    }

}
