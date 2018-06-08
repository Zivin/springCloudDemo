/**
 * @Project integration-system
 * @Package com.cloud.service.convenience
 * @FileName ExpressService.java
 */

package com.cloud.service.convenience;

import com.cloud.base.entity.BaseResult;
import com.cloud.base.service.BaseService;
import com.cloud.entity.convenience.express.ExpressQo;

/**
 * 快递service接口
 * @Author zivin
 * @Date 2018年1月4日
 */
public interface ExpressService extends BaseService {

    /**
     * 根据运单号查询快递公司信息
     * @param qo
     * @return
     */
    BaseResult queryExpress(ExpressQo qo);

    /**
     * 查询快递信息
     * @param qo
     * @return
     */
    BaseResult getExpress(ExpressQo qo);
}
