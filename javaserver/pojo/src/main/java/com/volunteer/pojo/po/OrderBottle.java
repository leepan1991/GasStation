package com.volunteer.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/1 0001.
 */
public class OrderBottle {
    private String bottleCode;
    private String orderId;
    private Date storageTime;
    private Date deliveryTime;

    @JSONField(serialize = false)
    private int orgId;

    public String getBottleCode() {
        return bottleCode;
    }

    public void setBottleCode(String bottleCode) {
        this.bottleCode = bottleCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
