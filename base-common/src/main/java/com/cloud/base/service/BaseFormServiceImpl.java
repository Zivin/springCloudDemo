/**
 * @Project base-common
 * @Package com.cloud.base.service
 * @FileName BaseFormServiceImpl.java
 */

package com.cloud.base.service;

import java.util.List;

import com.cloud.base.entity.BaseEntity;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.entity.PageParam;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.mapper.BaseMapper;
import com.cloud.base.util.BaseStringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * service实现基类
 * @Author zivin
 * @Date 2017年10月8日
 */
public abstract class BaseFormServiceImpl<T extends BaseEntity, Q extends T, V extends T> extends BaseServiceImpl
        implements BaseFormService<T, Q, V> {

    protected abstract BaseMapper<T, Q, V> getMapper();

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#getList(com.cloud.base.entity.
     * PageParam)
     */
    @Override
    public PageInfo<V> getList(PageParam<Q> pageParam) {
        if (null == pageParam) {
            return new PageInfo<>();
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);
        List<V> list = getMapper().getList(pageParam.getParam());
        return new PageInfo<>(list);
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#get(java.lang.String)
     */
    @Override
    public V get(String id) {
        if (BaseStringUtils.isBlank(id)) {
            return null;
        }
        return getMapper().selectByPrimaryKey(id);
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#save(java.lang.Object)
     */
    @Override
    public BaseResult save(V param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }
        param.setId(BaseStringUtils.getUUID());
        int count = getMapper().insertSelective(param);
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
    public BaseResult update(V param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }
        int count = getMapper().updateByPrimaryKeySelective(param);
        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.base.service.BaseService#delete(com.cloud.base.entity.
     * BaseEntity)
     */
    @Override
    public BaseResult delete(V param) {
        if (null == param) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }
        int count = getMapper().updateByPrimaryKeySelective(param);
        if (count > 0) {
            return BaseResult.success();
        }
        return BaseResult.failure();
    }

}
