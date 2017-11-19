package com.volunteer.pojo.dto;

import com.volunteer.pojo.po.OrderBottle;

/**
 * Created by Administrator on 2017/10/1 0001.
 */
public class OrderBottleDTO extends OrderBottle {

    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
