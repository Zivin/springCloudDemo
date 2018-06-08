/**
 * @Project user-center
 * @Package com.cloud.service.user
 * @FileName UserService.java
 */

package com.cloud.service.account;

import com.cloud.base.service.BaseFormService;
import com.cloud.entity.account.user.User;
import com.cloud.entity.account.user.UserQo;
import com.cloud.entity.account.user.UserVo;

/**
 * 用户管理service接口
 * @Author zivin
 * @Date 2017年10月8日
 */
public interface UserService extends BaseFormService<User, UserQo, UserVo> {

}
