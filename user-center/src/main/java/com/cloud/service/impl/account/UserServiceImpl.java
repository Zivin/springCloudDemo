/**
 * @Project user-center
 * @Package com.cloud.service.user.impl
 * @FileName UserServiceImpl.java
 */

package com.cloud.service.impl.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.base.service.BaseFormServiceImpl;
import com.cloud.entity.account.user.User;
import com.cloud.entity.account.user.UserQo;
import com.cloud.entity.account.user.UserVo;
import com.cloud.mapper.account.UserMapper;
import com.cloud.service.account.UserService;

/**
 * 用户管理service实现
 * @Author zivin
 * @Date 2017年10月8日
 */
@Service
@Transactional
public class UserServiceImpl extends BaseFormServiceImpl<User, UserQo, UserVo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseServiceImpl#getMapper()
     */
    @Override
    protected UserMapper getMapper() {
        return userMapper;
    }

}
