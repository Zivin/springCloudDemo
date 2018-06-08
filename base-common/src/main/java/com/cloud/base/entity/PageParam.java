/**
 * @Project base-common
 * @Package com.cloud.base.entity
 * @FileName PageParam.java
 */

package com.cloud.base.entity;

import java.io.Serializable;

/**
 * 分页列表查询参数
 * @Author zivin
 * @Date 2017年11月2日
 */
public class PageParam<Q extends BaseEntity> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1461335313862122293L;

    /**
     * param 查询参数
     */
    private Q param;

    /**
     * pageNum 页码
     */
    private int pageNum;

    /**
     * pageSize 每页记录数
     */
    private int pageSize;

    /**
     * @return the param
     */
    public Q getParam() {
        return param;
    }

    /**
     * @param param the param to set
     */
    public void setParam(Q param) {
        this.param = param;
    }

    /**
     * @return the pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum the pageNum to set
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PageParam [param=" + param + ", pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
    }

}
