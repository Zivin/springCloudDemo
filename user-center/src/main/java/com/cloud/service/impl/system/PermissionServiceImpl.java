/**
 * @Project user-center
 * @Package com.cloud.service.impl
 * @FileName PermissionServiceImpl.java
 */

package com.cloud.service.impl.system;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.base.service.BaseFormServiceImpl;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.entity.system.permission.Permission;
import com.cloud.entity.system.permission.PermissionQo;
import com.cloud.entity.system.permission.PermissionVo;
import com.cloud.mapper.system.PermissionMapper;
import com.cloud.service.system.PermissionService;

/**
 * 权限管理service实现
 * @Author zivin
 * @Date 2017年10月15日
 */
@Service
@Transactional
public class PermissionServiceImpl extends BaseFormServiceImpl<Permission, PermissionQo, PermissionVo>
        implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseServiceImpl#getMapper()
     */
    @Override
    protected PermissionMapper getMapper() {
        return permissionMapper;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.cloud.service.PermissionService#getPermissionByRoleId(java.lang.
     * String)
     */
    @Override
    public List<PermissionVo> getPermissionByRoleId(String roleId) {
        if (BaseStringUtils.isBlank(roleId)) {
            return Collections.emptyList();
        }
        return permissionMapper.getPermissionByRoleId(roleId);
    }

}
