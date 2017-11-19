package com.volunteer.pojo.po;

import com.volunteer.pojo.AbstractEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/16 0016.
 */
public class BottleLocation extends AbstractEntity {

    private double lng;
    private double lat;
    private String orderId;
    private String bottleCode;
    private Date createTime;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBottleCode() {
        return bottleCode;
    }

    public void setBottleCode(String bottleCode) {
        this.bottleCode = bottleCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
