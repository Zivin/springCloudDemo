/**
 * @Project user-center
 * @Package com.cloud.service.impl
 * @FileName MenuServiceImpl.java
 */

package com.cloud.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.base.entity.BaseResult;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.service.BaseFormServiceImpl;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.entity.system.menu.Menu;
import com.cloud.entity.system.menu.MenuPermission;
import com.cloud.entity.system.menu.MenuQo;
import com.cloud.entity.system.menu.MenuVo;
import com.cloud.mapper.system.MenuMapper;
import com.cloud.service.system.MenuService;

/**
 * 菜单管理service实现
 * @Author zivin
 * @Date 2017年10月15日
 */
@Service
@Transactional
public class MenuServiceImpl extends BaseFormServiceImpl<Menu, MenuQo, MenuVo> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseServiceImpl#getMapper()
     */
    @Override
    protected MenuMapper getMapper() {
        return menuMapper;
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#get(java.lang.String)
     */
    @Override
    public MenuVo get(String id) {
        if (BaseStringUtils.isBlank(id)) {
            return null;
        }
        MenuVo menuVo = menuMapper.selectByPrimaryKey(id);
        if (menuVo == null) {
            return null;
        }
        List<MenuPermission> menuPermissionList = menuMapper.getMenuPermissionList(menuVo.getId());
        menuVo.setMenuPermissionList(menuPermissionList);
        return menuVo;
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#update(java.lang.Object)
     */
    @Override
    public BaseResult update(MenuVo param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }
        int count = menuMapper.updateByPrimaryKeySelective(param);

        // 更新菜单权限关联关联
        menuMapper.disableMenuPermission(param);
        menuMapper.enableMenuPermission(param);

        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.service.system.MenuService#queryMenuPermissionList()
     */
    @Override
    public List<MenuPermission> queryMenuPermissionList() {
        return menuMapper.getMenuPermissionList(null);
    }

}
