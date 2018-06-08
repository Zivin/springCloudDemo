/**
 * @Project message-center
 * @Package com.cloud.controller.basic
 * @FileName RegionController.java
 */

package com.cloud.controller.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.base.controller.BaseFormController;
import com.cloud.base.entity.BaseResult;
import com.cloud.entity.basic.region.Region;
import com.cloud.entity.basic.region.RegionQo;
import com.cloud.entity.basic.region.RegionVo;
import com.cloud.service.basic.RegionService;
import com.cloud.service.impl.basic.RegionCrawler;

/**
 * 区域管理controller
 * @Author zivin
 * @Date 2017年12月16日
 */
@RestController
@RequestMapping(value = "/region")
public class RegionController extends BaseFormController<Region, RegionQo, RegionVo> {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionCrawler regionCrawler;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.controller.BaseFormController#getService()
     */
    @Override
    protected RegionService getService() {
        return regionService;
    }

    /**
     * 刷新区域数据
     * @return
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    @ResponseBody
    protected BaseResult refresh() {
        try {
            new Thread(regionCrawler).start();
            return BaseResult.success();
        } catch (Exception e) {
            logger.error("刷新区域数据失败", e);
            return BaseResult.failure("刷新区域数据失败");
        }
    }
}
