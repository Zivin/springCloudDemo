/**
 * @Project user-center
 * @Package com.cloud.service.impl
 * @FileName AdminServiceImpl.java
 */

package com.cloud.service.impl.account;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.base.constant.CommonConstant;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.service.BaseFormServiceImpl;
import com.cloud.base.util.BaseEncryptionUtils;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.entity.account.PasswordVo;
import com.cloud.entity.account.admin.Admin;
import com.cloud.entity.account.admin.AdminQo;
import com.cloud.entity.account.admin.AdminVo;
import com.cloud.mapper.account.AdminMapper;
import com.cloud.service.account.AdminService;

/**
 * 管理员管理service实现
 * @Author zivin
 * @Date 2017年10月8日
 */
@Service
@Transactional
public class AdminServiceImpl extends BaseFormServiceImpl<Admin, AdminQo, AdminVo> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseServiceImpl#getMapper()
     */
    @Override
    protected AdminMapper getMapper() {
        return adminMapper;
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.service.AdminService#getByAccount(java.lang.String)
     */
    @Override
    public AdminVo getByAccount(String account) {
        if (BaseStringUtils.isBlank(account)) {
            return null;
        }
        return adminMapper.getByAccount(account);
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.service.AdminService#updatePassword(com.cloud.entity.
     * PasswordVo)
     */
    @Override
    public BaseResult updatePassword(PasswordVo passwordVo) {
        AdminVo adminVo = get(passwordVo.getUserId());
        if (null == adminVo) {
            return BaseResult.failure(BaseResultCode.INVALID_PARAM);
        }
        // 旧密码不一致
        if (!BaseEncryptionUtils.encryptByMd5(passwordVo.getOldPassword(), adminVo.getSalt())
                .equals(adminVo.getPassword())) {
            return BaseResult.failure("密码输入错误，请重新输入");
        }

        // 验证通过，修改密码
        adminVo.setSalt(BaseStringUtils.getUUID());
        adminVo.setPassword(BaseEncryptionUtils.encryptByMd5(passwordVo.getNewPassword(), adminVo.getSalt()));
        adminVo.setUpdateTime(new Date());
        adminVo.setUpdateUserId(passwordVo.getUserId());
        return update(adminVo);
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#save(java.lang.Object)
     */
    @Override
    public BaseResult save(AdminVo param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }
        param.setId(BaseStringUtils.getUUID());
        param.setSalt(BaseStringUtils.getUUID());
        param.setPassword(BaseEncryptionUtils
                .encryptByMd5(BaseEncryptionUtils.encryptByMd5(CommonConstant.DEFAULT_PASSWORD, ""), param.getSalt()));
        int count = adminMapper.insertSelective(param);
        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#delete(com.cloud.base.entity.
     * BaseEntity)
     */
    @Override
    public BaseResult delete(AdminVo param) {
        if (null == param || CollectionUtils.isEmpty(param.getIds())) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }

        for (String id : param.getIds()) {
            // 检验超级管理员id
            if (CommonConstant.SUPER_ID.equals(id)) {
                return BaseResult.failure("总管理员账号不允许删除！");
            }
        }

        int count = adminMapper.updateByPrimaryKeySelective(param);
        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }
}
