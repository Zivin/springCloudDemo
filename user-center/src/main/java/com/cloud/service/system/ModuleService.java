/**
 * @Project user-center
 * @Package com.cloud.service
 * @FileName ModuleService.java
 */

package com.cloud.service.system;

import com.cloud.base.service.BaseFormService;
import com.cloud.entity.system.module.Module;
import com.cloud.entity.system.module.ModuleQo;
import com.cloud.entity.system.module.ModuleVo;

/**
 * 模块管理service接口
 * @Author zivin
 * @Date 2017年11月12日
 */
public interface ModuleService extends BaseFormService<Module, ModuleQo, ModuleVo> {

}
