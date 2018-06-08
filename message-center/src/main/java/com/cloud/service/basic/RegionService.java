/**
 * @Project message-center
 * @Package com.cloud.service.basic.region
 * @FileName RegionService.java
 */

package com.cloud.service.basic;

import com.cloud.base.service.BaseFormService;
import com.cloud.entity.basic.region.Region;
import com.cloud.entity.basic.region.RegionQo;
import com.cloud.entity.basic.region.RegionVo;

/**
 * 区域管理service接口
 * @Author zivin
 * @Date 2017年12月16日
 */
public interface RegionService extends BaseFormService<Region, RegionQo, RegionVo> {

    /**
     * 根据区域编码获取区域信息
     * @param regionCode
     * @return
     */
    RegionVo getByCode(String regionCode);
}
