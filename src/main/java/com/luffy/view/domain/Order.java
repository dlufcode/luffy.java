package com.luffy.view.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * order_1
 * @author 
 */
public class Order implements Serializable {
    private Integer id;

    /**
     * 订单Id
     */
    private Integer orderId;

    /**
     * sku_id
     */
    private Integer skuId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date mtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getMtime() == null) ? 0 : getMtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", skuId=").append(skuId);
        sb.append(", status=").append(status);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}