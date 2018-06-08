/**
 * @Project user-center-api
 * @Package com.cloud.service
 * @FileName AdminService.java
 */

package com.cloud.service.account;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.entity.account.admin.AdminVo;
import com.cloud.hystric.account.AdminServiceHystric;

/**
 * 管理员接口
 * @Author zivin
 * @Date 2017年10月11日
 */
@FeignClient(value = "user-center", fallback = AdminServiceHystric.class)
public interface AdminServiceApi {

    @RequestMapping(value = "/admin/getAdminByAccount", method = RequestMethod.GET)
    AdminVo getAdminByAccount(@RequestParam("account") String account);

}
