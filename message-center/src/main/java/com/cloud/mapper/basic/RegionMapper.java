
package com.cloud.mapper.basic;

import org.springframework.stereotype.Repository;

import com.cloud.base.mapper.BaseMapper;
import com.cloud.entity.basic.region.Region;
import com.cloud.entity.basic.region.RegionQo;
import com.cloud.entity.basic.region.RegionVo;

/**
 * 区域管理mapper
 * @Author zivin
 * @Date 2017年12月16日
 */
@Repository
public interface RegionMapper extends BaseMapper<Region, RegionQo, RegionVo> {

    /**
     * 根据区域编码获取区域信息
     * @param regionCode
     * @return
     */
    RegionVo getByCode(String regionCode);
}
