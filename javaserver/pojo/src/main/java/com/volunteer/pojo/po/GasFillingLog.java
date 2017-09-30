package com.volunteer.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.volunteer.pojo.AbstractEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class GasFillingLog extends AbstractEntity {
    /**
     * 机构id
     */
    private int orgId;
    /**
     * 登记代码
     */
    private String code;
    /**
     * 充气时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date fillingTime;
    /**
     * 充气人
     */
    private int fillinger;
    /**
     * 检查员
     */
    private String inspector;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getFillingTime() {
        return fillingTime;
    }

    public void setFillingTime(Date fillingTime) {
        this.fillingTime = fillingTime;
    }

    public int getFillinger() {
        return fillinger;
    }

    public void setFillinger(int fillinger) {
        this.fillinger = fillinger;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

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
