/**
 * @Project integration-system
 * @Package com.cloud.entity.express
 * @FileName Express.java
 */

package com.cloud.entity.convenience.express;

import java.io.Serializable;

/**
 * 快递实体
 * @Author zivin
 * @Date 2018年1月3日
 */
public class Express implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5164875914136161358L;

    /**
     * no 运单号
     */
    private String no;

    /**
     * company 快递公司
     */
    private ExpressCompany company;

    /**
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return the company
     */
    public ExpressCompany getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(ExpressCompany company) {
        this.company = company;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Express [no=" + no + ", company=" + company + "]";
    }

}
