/**
 * @Project user-center
 * @Package com.cloud.service.menu
 * @FileName MenuService.java
 */

package com.cloud.service.system;

import java.util.List;

import com.cloud.base.service.BaseFormService;
import com.cloud.entity.system.menu.Menu;
import com.cloud.entity.system.menu.MenuPermission;
import com.cloud.entity.system.menu.MenuQo;
import com.cloud.entity.system.menu.MenuVo;

/**
 * 菜单管理service接口
 * @Author zivin
 * @Date 2017年10月8日
 */
public interface MenuService extends BaseFormService<Menu, MenuQo, MenuVo> {

    /**
     * 获取菜单权限列表
     * @return
     */
    List<MenuPermission> queryMenuPermissionList();
}
