/**
 * @Project base-common
 * @Package com.cloud.base.entity
 * @FileName BaseEntity.java
 */

package com.cloud.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体基类
 * @Author zivin
 * @Date 2017年10月8日
 */
public class BaseEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3090844600606082548L;

    /**
     * id 主键ID
     */
    private String id;

    /**
     * status 状态：0启用，1禁用
     */
    private Integer status;

    /**
     * remark 备注
     */
    private String remark;

    /**
     * createUserId 创建人ID
     */
    private String createUserId;

    /**
     * createTime 创建时间
     */
    private Date createTime;

    /**
     * updateUserId 修改人ID
     */
    private String updateUserId;

    /**
     * updateTime 修改时间
     */
    private Date updateTime;

    /**
     * disabled 删除状态：0未删除，1已删除
     */
    private Integer disabled;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the createUserId
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId the createUserId to set
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateUserId
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * @param updateUserId the updateUserId to set
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the disabled
     */
    public Integer getDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BaseEntity [id=" + id + ", status=" + status + ", remark=" + remark + ", createUserId=" + createUserId
                + ", createTime=" + createTime + ", updateUserId=" + updateUserId + ", updateTime=" + updateTime
                + ", disabled=" + disabled + "]";
    }

}
