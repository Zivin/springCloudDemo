/**
 * @Project user-center
 * @Package com.cloud.dao.user
 * @FileName UserMapper.java
 */

package com.cloud.mapper.account;

import org.springframework.stereotype.Repository;

import com.cloud.base.mapper.BaseMapper;
import com.cloud.entity.account.user.User;
import com.cloud.entity.account.user.UserQo;
import com.cloud.entity.account.user.UserVo;

/**
 * 用户管理mapper
 * @Author zivin
 * @Date 2017年10月8日
 */
@Repository
public interface UserMapper extends BaseMapper<User, UserQo, UserVo> {

}
