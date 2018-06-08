/**
 * @Project base-common
 * @Package com.cloud.base.controller
 * @FileName BaseFormController.java
 */

package com.cloud.base.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.base.entity.BaseEntity;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.entity.PageParam;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.enumeration.Disabled;
import com.cloud.base.service.BaseFormService;

/**
 * controller基类
 * @Author zivin
 * @Date 2017年10月31日
 */
@RestController
public abstract class BaseFormController<T extends BaseEntity, Q extends T, V extends T> extends BaseController {

    protected abstract BaseFormService<T, Q, V> getService();

    /**
     * 获取列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    protected BaseResult query(@RequestBody PageParam<Q> pageParam) {
        try {
            return BaseResult.success(getService().getList(pageParam));
        } catch (Exception e) {
            logger.error("获取列表失败", e);
            return BaseResult.failure("获取列表失败");
        }
    }

    /**
     * 获取数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    protected BaseResult get(@RequestParam("id") String id) {
        try {
            V vo = getService().get(id);
            if (null == vo) {
                return BaseResult.failure(BaseResultCode.INVALID_RESULT);
            }
            return BaseResult.success(vo);
        } catch (Exception e) {
            logger.error("获取数据失败", e);
            return BaseResult.failure("获取数据失败");
        }
    }

    /**
     * 保存数据
     * @param param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    protected BaseResult add(@RequestBody V param) {
        try {
            BaseEntity admin = (BaseEntity) getCurrentAdmin();
            param.setCreateTime(new Date());
            param.setCreateUserId(admin.getId());
            param.setDisabled(Disabled.NO.getCode());
            return getService().save(param);
        } catch (Exception e) {
            logger.error("保存数据失败", e);
            return BaseResult.failure("保存数据失败");
        }
    }

    /**
     * 更新数据
     * @param param
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    protected BaseResult edit(@RequestBody V param) {
        try {
            BaseEntity admin = (BaseEntity) getCurrentAdmin();
            param.setUpdateTime(new Date());
            param.setUpdateUserId(admin.getId());
            return getService().update(param);
        } catch (Exception e) {
            logger.error("更新数据失败", e);
            return BaseResult.failure("更新数据失败");
        }
    }

    /**
     * 删除数据
     * @param param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    protected BaseResult delete(@RequestBody V param) {
        try {
            BaseEntity admin = (BaseEntity) getCurrentAdmin();
            param.setUpdateTime(new Date());
            param.setUpdateUserId(admin.getId());
            param.setDisabled(Disabled.YES.getCode());
            return getService().delete(param);
        } catch (Exception e) {
            logger.error("删除数据失败", e);
            return BaseResult.failure("删除数据失败");
        }
    }

}
