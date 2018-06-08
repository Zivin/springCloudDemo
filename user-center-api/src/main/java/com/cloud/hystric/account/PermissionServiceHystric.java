/**
 * @Project user-center-api
 * @Package com.cloud.hystric.account
 * @FileName PermissionServicezHystric.java
 */

package com.cloud.hystric.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cloud.entity.system.permission.PermissionVo;
import com.cloud.service.account.PermissionServiceApi;

/**
 * 权限接口异常实现
 * @Author zivin
 * @Date 2017年12月24日
 */
@Component
public class PermissionServiceHystric implements PermissionServiceApi {

    /*
     * (non-Javadoc)
     * @see com.cloud.service.account.PermissionServiceApi#getPermissionList()
     */
    @Override
    public List<PermissionVo> getPermissionList() {
        return new ArrayList<>();
    }

}
