/**
 * @Project user-center
 * @Package com.cloud.service
 * @FileName AdminService.java
 */

package com.cloud.service.account;

import com.cloud.base.entity.BaseResult;
import com.cloud.base.service.BaseFormService;
import com.cloud.entity.account.PasswordVo;
import com.cloud.entity.account.admin.Admin;
import com.cloud.entity.account.admin.AdminQo;
import com.cloud.entity.account.admin.AdminVo;

/**
 * 管理员管理service接口
 * @Author zivin
 * @Date 2017年10月8日
 */
public interface AdminService extends BaseFormService<Admin, AdminQo, AdminVo> {

    /**
     * 根据账号查询用户信息
     * @param account
     * @return
     */
    AdminVo getByAccount(String account);

    /**
     * 修改密码
     * @param passwordVo
     * @return
     */
    BaseResult updatePassword(PasswordVo passwordVo);
}
