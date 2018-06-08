/**
 * @Project message-center
 * @Package com.cloud.service.impl.basic
 * @FileName RegionServiceImpl.java
 */

package com.cloud.service.impl.basic;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.base.entity.BaseResult;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.service.BaseFormServiceImpl;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.entity.basic.region.Region;
import com.cloud.entity.basic.region.RegionQo;
import com.cloud.entity.basic.region.RegionVo;
import com.cloud.mapper.basic.RegionMapper;
import com.cloud.service.basic.RegionService;

/**
 * 区域管理service实现
 * @Author zivin
 * @Date 2017年12月16日
 */
@Service
@Transactional
public class RegionServiceImpl extends BaseFormServiceImpl<Region, RegionQo, RegionVo> implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseServiceImpl#getMapper()
     */
    @Override
    protected RegionMapper getMapper() {
        return regionMapper;
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.service.basic.RegionService#getByCode(java.lang.String)
     */
    @Override
    public RegionVo getByCode(String regionCode) {
        if (BaseStringUtils.isBlank(regionCode)) {
            return null;
        }
        return regionMapper.getByCode(regionCode);
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#save(java.lang.Object)
     */
    @Override
    public BaseResult save(RegionVo param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }
        param.setId(BaseStringUtils.getUUID());

        // 计算区域等级
        param.setRegionLevel(RegionCrawler.getRegionLevel(param.getRegionCode()));

        // 查询父节点
        setParentId(param);

        int count = regionMapper.insertSelective(param);
        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#update(java.lang.Object)
     */
    @Override
    public BaseResult update(RegionVo param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }

        // 重新查询父节点
        setParentId(param);

        int count = regionMapper.updateByPrimaryKeySelective(param);
        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }

    /**
     * 查询区域的父级区域id
     * @param param
     * @return
     */
    private void setParentId(RegionVo param) {
        if (param.getRegionLevel() > 1) {
            RegionQo qo = new RegionQo();
            int parentLevel = param.getRegionLevel() - 1;
            switch (parentLevel) {
            case 1:
                qo.setRegionCode(param.getRegionCode().substring(0, 2) + "0000000000");
                break;
            case 2:
                qo.setRegionCode(param.getRegionCode().substring(0, 4) + "00000000");
                break;
            case 3:
                qo.setRegionCode(param.getRegionCode().substring(0, 6) + "000000");
                break;
            case 4:
                qo.setRegionCode(param.getRegionCode().substring(0, 9) + "000");
                break;
            default:
                qo.setRegionCode("000000000000");
                break;
            }
            List<RegionVo> parentList = regionMapper.getList(qo);
            if (CollectionUtils.isNotEmpty(parentList) && parentList.size() == 1) {
                param.setParentId(parentList.get(0).getId());
            }
        } else {
            param.setParentId("0");
        }
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#delete(com.cloud.base.entity.
     * BaseEntity)
     */
    @Override
    public BaseResult delete(RegionVo param) {
        if (null == param || CollectionUtils.isEmpty(param.getIds())) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }

        for (String id : param.getIds()) {
            // 校验区域是否存在下级
            RegionQo qo = new RegionQo();
            qo.setParentId(id);
            List<RegionVo> list = regionMapper.getList(qo);
            if (CollectionUtils.isNotEmpty(list)) {
                return BaseResult.failure("区域存在下级，不允许删除！");
            }
        }

        int count = regionMapper.updateByPrimaryKeySelective(param);
        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }

}
