/**
 * @Project user-center
 * @Package com.cloud.service.impl
 * @FileName RoleServiceImpl.java
 */

package com.cloud.service.impl.system;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.base.constant.CommonConstant;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.service.BaseFormServiceImpl;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.entity.account.admin.AdminQo;
import com.cloud.entity.account.admin.AdminVo;
import com.cloud.entity.system.role.Role;
import com.cloud.entity.system.role.RoleMenuPermission;
import com.cloud.entity.system.role.RoleQo;
import com.cloud.entity.system.role.RoleVo;
import com.cloud.mapper.account.AdminMapper;
import com.cloud.mapper.system.RoleMapper;
import com.cloud.service.system.RoleService;

/**
 * 角色管理service实现
 * @Author zivin
 * @Date 2017年11月15日
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseFormServiceImpl<Role, RoleQo, RoleVo> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminMapper adminMapper;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseServiceImpl#getMapper()
     */
    @Override
    protected RoleMapper getMapper() {
        return roleMapper;
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#get(java.lang.String)
     */
    @Override
    public RoleVo get(String id) {
        if (BaseStringUtils.isBlank(id)) {
            return null;
        }
        RoleVo roleVo = roleMapper.selectByPrimaryKey(id);
        if (roleVo == null) {
            return null;
        }
        List<RoleMenuPermission> menuPermissionList = roleMapper.getRoleMenuPermissionList(roleVo.getId());
        roleVo.setRoleMenuPermissionList(menuPermissionList);
        return roleVo;
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#update(java.lang.Object)
     */
    @Override
    public BaseResult update(RoleVo param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }
        int count = roleMapper.updateByPrimaryKeySelective(param);

        // 更新角色菜单权限关联关联
        roleMapper.disableRoleMenuPermission(param);

        if (CollectionUtils.isNotEmpty(param.getRoleMenuPermissionList())) {
            roleMapper.enableRoleMenuPermission(param);
        }

        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#save(java.lang.Object)
     */
    @Override
    public BaseResult save(RoleVo param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }

        // 查询最大的角色编码
        String maxCode = roleMapper.getMaxRoleCode();

        param.setId(BaseStringUtils.getUUID());
        param.setRoleCode(BaseStringUtils.plusOne(maxCode, CommonConstant.ROLE_CODE_LENGTH));
        int count = roleMapper.insertSelective(param);

        // 保存角色的菜单权限
        if (CollectionUtils.isNotEmpty(param.getRoleMenuPermissionList())) {
            roleMapper.saveRoleMenuPermissionList(param);
        }

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
    public BaseResult delete(RoleVo param) {
        if (null == param || CollectionUtils.isEmpty(param.getIds())) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }

        for (String id : param.getIds()) {
            // 检验超级管理员角色id
            if (CommonConstant.SUPER_ID.equals(id)) {
                return BaseResult.failure("超级管理员角色不允许删除！");
            }
            // 校验角色是否已经有账号使用
            AdminQo qo = new AdminQo();
            qo.setRoleId(id);
            List<AdminVo> list = adminMapper.getList(qo);
            if (CollectionUtils.isNotEmpty(list)) {
                return BaseResult.failure("已被账号引用的角色不允许删除！");
            }
        }

        int count = roleMapper.updateByPrimaryKeySelective(param);
        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }
}
