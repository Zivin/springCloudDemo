/**
 * @Project base-common
 * @Package com.cloud.base.service
 * @FileName BaseFormService.java
 */

package com.cloud.base.service;

import com.cloud.base.entity.BaseEntity;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.entity.PageParam;
import com.github.pagehelper.PageInfo;

/**
 * service基类
 * @Author zivin
 * @Date 2017年10月8日
 */
public interface BaseFormService<T extends BaseEntity, Q extends T, V extends T> extends BaseService {

    /**
     * 查询列表
     * @param pageParam
     * @return
     */
    PageInfo<V> getList(PageParam<Q> pageParam);

    /**
     * 查询对象
     * @param id
     * @return
     */
    V get(String id);

    /**
     * 保存对象
     * @param param
     * @return
     */
    BaseResult save(V param);

    /**
     * 更新对象
     * @param param
     * @return
     */
    BaseResult update(V param);

    /**
     * 删除对象
     * @param param
     * @return
     */
    BaseResult delete(V param);

}
