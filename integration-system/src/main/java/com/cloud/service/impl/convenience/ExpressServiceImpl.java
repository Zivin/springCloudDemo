/**
 * @Project integration-system
 * @Package com.cloud.service.impl.convenience
 * @FileName ExpressServiceImpl.java
 */

package com.cloud.service.impl.convenience;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.service.BaseServiceImpl;
import com.cloud.base.util.BaseDateUtils;
import com.cloud.base.util.BaseHttpUtils;
import com.cloud.entity.convenience.express.ExpressCompany;
import com.cloud.entity.convenience.express.ExpressDetail;
import com.cloud.entity.convenience.express.ExpressQo;
import com.cloud.entity.convenience.express.ExpressVo;
import com.cloud.service.convenience.ExpressService;

/**
 * 快递service实现
 * @Author zivin
 * @Date 2018年1月4日
 */
@Service
@Transactional
public class ExpressServiceImpl extends BaseServiceImpl implements ExpressService {

    /*
     * (non-Javadoc)
     * @see com.cloud.service.convenience.ExpressService#queryExpress(com.cloud.
     * entity.convenience.express.ExpressQo)
     */
    @Override
    public BaseResult queryExpress(ExpressQo qo) {
        if (null == qo) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }

        String result = BaseHttpUtils.get("http://www.kuaidi.com/index-ajaxselectinfo-" + qo.getNo() + ".html", null);

        JSONArray jsonArray = JSON.parseArray(result);
        if (jsonArray == null || jsonArray.isEmpty()) {
            return BaseResult.failure("查询不到订单信息");
        }

        // 组件返回的快递公司列表
        List<ExpressCompany> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ExpressCompany company = new ExpressCompany();
            company.setName(jsonObject.getString("name"));
            company.setExName(jsonObject.getString("exname"));
            company.setIco("http://www.kuaidi.com" + jsonObject.getString("ico"));
            company.setPhone(jsonObject.getString("phone"));
            company.setUrl(jsonObject.getString("url"));
            list.add(company);
        }
        return BaseResult.success(list);
    }

    /*
     * (non-Javadoc)
     * @see com.cloud.service.convenience.ExpressService#getExpress(com.cloud.
     * entity.convenience.express.ExpressQo)
     */
    @Override
    public BaseResult getExpress(ExpressQo qo) {
        if (null == qo) {
            return BaseResult.failure(BaseResultCode.EMPTY_PARAM);
        }

        String result = BaseHttpUtils.get("http://www.kuaidi.com/index-ajaxselectcourierinfo-" + qo.getNo() + "-"
                + qo.getCompany().getExName() + ".html", null);

        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject == null || !jsonObject.getBooleanValue("success")) {
            return BaseResult.failure("查询不到订单信息");
        }

        ExpressVo expressVo = new ExpressVo();
        expressVo.setCompany(qo.getCompany());
        expressVo.setNo(jsonObject.getString("nu"));
        JSONArray data = jsonObject.getJSONArray("data");
        if (data != null && !data.isEmpty()) {
            List<ExpressDetail> list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                ExpressDetail detail = new ExpressDetail();
                detail.setTime(BaseDateUtils.parseDate(data.getJSONObject(i).getString("time")));
                detail.setContext(data.getJSONObject(i).getString("context"));
                list.add(detail);
            }
            expressVo.setDetails(list);
        }
        return BaseResult.success(expressVo);
    }

}
