/**
 * @Project user-center
 * @Package com.cloud.service.impl
 * @FileName ModuleServiceImpl.java
 */

package com.cloud.service.impl.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.base.service.BaseFormServiceImpl;
import com.cloud.entity.system.module.Module;
import com.cloud.entity.system.module.ModuleQo;
import com.cloud.entity.system.module.ModuleVo;
import com.cloud.mapper.system.ModuleMapper;
import com.cloud.service.system.ModuleService;

/**
 * 模块管理service实现
 * @Author zivin
 * @Date 2017年11月12日
 */
@Service
@Transactional
public class ModuleServiceImpl extends BaseFormServiceImpl<Module, ModuleQo, ModuleVo> implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseServiceImpl#getMapper()
     */
    @Override
    protected ModuleMapper getMapper() {
        return moduleMapper;
    }

}
