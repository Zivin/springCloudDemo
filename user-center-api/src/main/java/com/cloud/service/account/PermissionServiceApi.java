/**
 * @Project user-center-api
 * @Package com.cloud.service.account
 * @FileName PermissionServiceApi.java
 */

package com.cloud.service.account;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloud.entity.system.permission.PermissionVo;
import com.cloud.hystric.account.PermissionServiceHystric;

/**
 * 权限接口
 * @Author zivin
 * @Date 2017年12月24日
 */
@FeignClient(value = "user-center", fallback = PermissionServiceHystric.class)
public interface PermissionServiceApi {

    @RequestMapping(value = "/permission/getPermissionList", method = RequestMethod.GET)
    List<PermissionVo> getPermissionList();

}
