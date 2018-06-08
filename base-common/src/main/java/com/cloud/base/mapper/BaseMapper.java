
package com.cloud.base.mapper;

import java.util.List;

import com.cloud.base.entity.BaseEntity;

/**
 * 管理员管理mapper
 * @Author zivin
 * @Date 2017年10月15日
 */
public interface BaseMapper<T extends BaseEntity, Q extends T, V extends T> {

    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    V selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<V> getList(Q qo);
}
