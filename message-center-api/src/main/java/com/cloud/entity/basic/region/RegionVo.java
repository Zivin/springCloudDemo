/**
 * @Project message-center-api
 * @Package com.cloud.entity.region
 * @FileName RegionVo.java
 */

package com.cloud.entity.basic.region;

import java.util.List;

/**
 * 区域vo
 * @Author zivin
 * @Date 2017年12月16日
 */
public class RegionVo extends Region {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7580947647996426955L;

    /**
     * parentRegionName 父级区域名称
     */
    private String parentRegionName;

    /**
     * parentRegionCode 父级区域编码
     */
    private String parentRegionCode;

    /**
     * ids id列表
     */
    private List<String> ids;

    /**
     * @return the parentRegionName
     */
    public String getParentRegionName() {
        return parentRegionName;
    }

    /**
     * @param parentRegionName the parentRegionName to set
     */
    public void setParentRegionName(String parentRegionName) {
        this.parentRegionName = parentRegionName;
    }

    /**
     * @return the parentRegionCode
     */
    public String getParentRegionCode() {
        return parentRegionCode;
    }

    /**
     * @param parentRegionCode the parentRegionCode to set
     */
    public void setParentRegionCode(String parentRegionCode) {
        this.parentRegionCode = parentRegionCode;
    }

    /**
     * @return the ids
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * @param ids the ids to set
     */
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RegionVo [parentRegionName=" + parentRegionName + ", parentRegionCode=" + parentRegionCode + ", ids="
                + ids + ", toString()=" + super.toString() + "]";
    }

}
