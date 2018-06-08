
package com.cloud.mapper.system;

import org.springframework.stereotype.Repository;

import com.cloud.base.mapper.BaseMapper;
import com.cloud.entity.system.module.Module;
import com.cloud.entity.system.module.ModuleQo;
import com.cloud.entity.system.module.ModuleVo;

/**
 * 模块管理mapper
 * @Author zivin
 * @Date 2017年11月12日
 */
@Repository
public interface ModuleMapper extends BaseMapper<Module, ModuleQo, ModuleVo> {
}
