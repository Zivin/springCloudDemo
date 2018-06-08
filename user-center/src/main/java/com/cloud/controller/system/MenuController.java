/**
 * @Project user-center
 * @Package com.cloud.controller
 * @FileName MenuController.java
 */

package com.cloud.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.base.controller.BaseFormController;
import com.cloud.base.entity.BaseResult;
import com.cloud.entity.system.menu.Menu;
import com.cloud.entity.system.menu.MenuQo;
import com.cloud.entity.system.menu.MenuVo;
import com.cloud.service.system.MenuService;

/**
 * 菜单管理controller
 * @Author zivin
 * @Date 2017年10月15日
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController extends BaseFormController<Menu, MenuQo, MenuVo> {

    @Autowired
    private MenuService menuService;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.controller.BaseFormController#getService()
     */
    @Override
    protected MenuService getService() {
        return menuService;
    }

    /**
     * 获取菜单权限列表
     * @return
     */
    @RequestMapping(value = "/queryPermission", method = RequestMethod.GET)
    @ResponseBody
    protected BaseResult queryPermission() {
        try {
            return BaseResult.success(menuService.queryMenuPermissionList());
        } catch (Exception e) {
            logger.error("获取菜单权限列表失败", e);
            return BaseResult.failure("获取菜单权限列表失败");
        }
    }

}
