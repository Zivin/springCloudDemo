/**
 * @Project integration-system
 * @Package com.cloud.entity.convenience.express
 * @FileName ExpressVo.java
 */

package com.cloud.entity.convenience.express;

import java.util.List;

/**
 * 快递vo
 * @Author zivin
 * @Date 2018年1月4日
 */
public class ExpressVo extends Express {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2205697954129024019L;

    /**
     * details 详情；列表
     */
    private List<ExpressDetail> details;

    /**
     * @return the details
     */
    public List<ExpressDetail> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<ExpressDetail> details) {
        this.details = details;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ExpressVo [details=" + details + ", toString()=" + super.toString() + "]";
    }

}
