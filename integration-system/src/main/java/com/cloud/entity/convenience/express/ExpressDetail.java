/**
 * @Project integration-system
 * @Package com.cloud.entity.convenience.express
 * @FileName ExpressDetail.java
 */

package com.cloud.entity.convenience.express;

import java.io.Serializable;
import java.util.Date;

/**
 * 快递信息实体
 * @Author zivin
 * @Date 2018年1月3日
 */
public class ExpressDetail implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3498958954785268848L;

    /**
     * context 信息内容
     */
    private String context;

    /**
     * time 信息时间
     */
    private Date time;

    /**
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Express [context=" + context + ", time=" + time + "]";
    }

}
