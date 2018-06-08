/**
 * @Project integration-system
 * @Package com.cloud.entity.convenience.express
 * @FileName ExpressCompany.java
 */

package com.cloud.entity.convenience.express;

import java.io.Serializable;

/**
 * 快递公司实体
 * @Author zivin
 * @Date 2018年1月5日
 */
public class ExpressCompany implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1863362865918301721L;

    /**
     * name 名称
     */
    private String name;

    /**
     * exName 代号
     */
    private String exName;

    /**
     * ico 图标
     */
    private String ico;

    /**
     * phone 电话
     */
    private String phone;

    /**
     * url 网站
     */
    private String url;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the exName
     */
    public String getExName() {
        return exName;
    }

    /**
     * @param exName the exName to set
     */
    public void setExName(String exName) {
        this.exName = exName;
    }

    /**
     * @return the ico
     */
    public String getIco() {
        return ico;
    }

    /**
     * @param ico the ico to set
     */
    public void setIco(String ico) {
        this.ico = ico;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ExpressCompany [name=" + name + ", exName=" + exName + ", ico=" + ico + ", phone=" + phone + ", url="
                + url + "]";
    }

}
