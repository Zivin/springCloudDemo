/**
 * @Project user-center-api
 * @Package com.cloud.hystric
 * @FileName AdminServiceHystric.java
 */

package com.cloud.hystric.account;

import org.springframework.stereotype.Component;

import com.cloud.entity.account.admin.AdminVo;
import com.cloud.service.account.AdminServiceApi;

/**
 * 管理员接口异常实现
 * @Author zivin
 * @Date 2017年10月11日
 */
@Component
public class AdminServiceHystric implements AdminServiceApi {

    /*
     * (non-Javadoc)
     * @see com.cloud.service.AdminService#getAdminByAccount(java.lang.String)
     */
    @Override
    public AdminVo getAdminByAccount(String account) {
        return null;
    }

}
