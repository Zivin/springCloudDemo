
package com.cloud.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cloud.base.mapper.BaseMapper;
import com.cloud.entity.system.menu.Menu;
import com.cloud.entity.system.menu.MenuPermission;
import com.cloud.entity.system.menu.MenuQo;
import com.cloud.entity.system.menu.MenuVo;

/**
 * 菜单管理mapper
 * @Author zivin
 * @Date 2017年10月15日
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu, MenuQo, MenuVo> {

    /**
     * 获取菜单权限关联
     * @param menuId 菜单id
     * @return
     */
    List<MenuPermission> getMenuPermissionList(@Param("menuId") String menuId);

    /**
     * 禁用菜单权限
     * @param menuVo
     * @return
     */
    int disableMenuPermission(MenuVo menuVo);

    /**
     * 更新菜单权限关联
     * @param menuVo
     * @return
     */
    int enableMenuPermission(MenuVo menuVo);
}
