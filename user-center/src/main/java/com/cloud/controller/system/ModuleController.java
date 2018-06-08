/**
 * @Project user-center
 * @Package com.cloud.controller
 * @FileName ModuleController.java
 */

package com.cloud.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.base.controller.BaseFormController;
import com.cloud.entity.system.module.Module;
import com.cloud.entity.system.module.ModuleQo;
import com.cloud.entity.system.module.ModuleVo;
import com.cloud.service.system.ModuleService;

/**
 * 模块管理controller
 * @Author zivin
 * @Date 2017年11月12日
 */
@RestController
@RequestMapping(value = "/module")
public class ModuleController extends BaseFormController<Module, ModuleQo, ModuleVo> {

    @Autowired
    private ModuleService moduleService;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.controller.BaseFormController#getService()
     */
    @Override
    protected ModuleService getService() {
        return moduleService;
    }

}
