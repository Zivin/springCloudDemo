
package com.cloud.mapper.account;

import org.springframework.stereotype.Repository;

import com.cloud.base.mapper.BaseMapper;
import com.cloud.entity.account.admin.Admin;
import com.cloud.entity.account.admin.AdminQo;
import com.cloud.entity.account.admin.AdminVo;

/**
 * 管理员管理mapper
 * @Author zivin
 * @Date 2017年10月15日
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin, AdminQo, AdminVo> {

    /**
     * 根据账号查询用户信息
     * @param account
     * @return
     */
    AdminVo getByAccount(String account);
}
