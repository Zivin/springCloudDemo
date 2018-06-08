/**
 * @Project user-center
 * @Package com.cloud.controller
 * @FileName UserController.java
 */

package com.cloud.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.base.controller.BaseFormController;
import com.cloud.entity.account.user.User;
import com.cloud.entity.account.user.UserQo;
import com.cloud.entity.account.user.UserVo;
import com.cloud.service.account.UserService;

/**
 * 用户管理controller
 * @Author zivin
 * @Date 2017年10月7日
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseFormController<User, UserQo, UserVo> {

    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.controller.BaseFormController#getService()
     */
    @Override
    protected UserService getService() {
        return userService;
    }

}
