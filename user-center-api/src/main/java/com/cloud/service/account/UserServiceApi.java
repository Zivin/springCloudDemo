/**
 * @Project user-center-api
 * @Package com.cloud.service
 * @FileName LoginService.java
 */

package com.cloud.service.account;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.cloud.hystric.account.UserServiceHystric;

/**
 * 用户接口
 * @Author zivin
 * @Date 2017年9月19日
 */
@FeignClient(value = "user-center", fallback = UserServiceHystric.class)
public interface UserServiceApi {

}
