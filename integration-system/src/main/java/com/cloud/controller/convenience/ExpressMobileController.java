/**
 * @Project integration-system
 * @Package com.cloud.controller.convenience
 * @FileName ExpressMobileController.java
 */

package com.cloud.controller.convenience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.base.controller.BaseController;
import com.cloud.base.entity.BaseResult;
import com.cloud.entity.convenience.express.ExpressQo;
import com.cloud.service.convenience.ExpressService;

/**
 * 快递controller
 * @Author zivin
 * @Date 2018年1月3日
 */
@RestController
@RequestMapping(value = "/mobile/express")
public class ExpressMobileController extends BaseController {

    @Autowired
    private ExpressService expressService;

    /**
     * 根据运单号查询快递公司信息
     * @return
     */
    @RequestMapping(value = "/queryExpress", method = RequestMethod.POST)
    @ResponseBody
    protected BaseResult queryExpress(@RequestBody ExpressQo qo) {
        try {
            return expressService.queryExpress(qo);
        } catch (Exception e) {
            logger.error("根据运单号查询快递公司信息失败", e);
            return BaseResult.failure("根据运单号查询快递公司信息");
        }
    }

    /**
     * 查询快递信息
     * @return
     */
    @RequestMapping(value = "/getExpress", method = RequestMethod.POST)
    @ResponseBody
    protected BaseResult getExpress(@RequestBody ExpressQo qo) {
        try {
            return expressService.getExpress(qo);
        } catch (Exception e) {
            logger.error("查询快递信息失败", e);
            return BaseResult.failure("查询快递信息失败");
        }
    }
}
