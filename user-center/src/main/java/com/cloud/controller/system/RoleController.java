/**
 * @Project user-center
 * @Package com.cloud.controller
 * @FileName RoleController.java
 */

package com.cloud.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.base.controller.BaseFormController;
import com.cloud.entity.system.role.Role;
import com.cloud.entity.system.role.RoleQo;
import com.cloud.entity.system.role.RoleVo;
import com.cloud.service.system.RoleService;

/**
 * @Author zivin
 * @Date 2017年11月15日
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseFormController<Role, RoleQo, RoleVo> {

    @Autowired
    private RoleService roleService;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.controller.BaseFormController#getService()
     */
    @Override
    protected RoleService getService() {
        return roleService;
    }

}
