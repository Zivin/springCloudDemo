
package com.cloud.entity.basic.region;

import com.cloud.base.entity.BaseEntity;

/**
 * 区域实体
 * @Author zivin
 * @Date 2017年12月16日
 */
public class Region extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4118398692610710507L;

    /**
     * regionName 区域名称
     */
    private String regionName;

    /**
     * regionCode 区域编码
     */
    private String regionCode;

    /**
     * regionLevel 区域等级：1省，2市，3县/区，4乡/镇，5村
     */
    private Integer regionLevel;

    /**
     * parentId 父级区域ID
     */
    private String parentId;

    /**
     * @return the regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName the regionName to set
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return the regionCode
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * @param regionCode the regionCode to set
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * @return the regionLevel
     */
    public Integer getRegionLevel() {
        return regionLevel;
    }

    /**
     * @param regionLevel the regionLevel to set
     */
    public void setRegionLevel(Integer regionLevel) {
        this.regionLevel = regionLevel;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Region [regionName=" + regionName + ", regionCode=" + regionCode + ", regionLevel=" + regionLevel
                + ", parentId=" + parentId + ", toString()=" + super.toString() + "]";
    }

}
