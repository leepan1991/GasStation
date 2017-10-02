package com.volunteer.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.volunteer.pojo.AbstractEntity;
import com.volunteer.pojo.AbstractStringEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class GasBottle {
    /**
     * 机构id
     */
    private int orgId;
    /**
     * 登记代码
     */
    private String code;
    /**
     * 瓶身码
     */
    private String bottleCode;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 充装介质
     */
    private int mediumId;
    /**
     * 充装介质
     */
    private String mediumName;
    /**
     * 制造单位
     */
    private String manufacturer;
    /**
     * 制造日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date manufactureDate;
    /**
     * 公称工作压力(Mpa)
     */
    private String workPressure;
    /**
     * 容积
     */
    private String volume;
    /**
     * 瓶重
     */
    private String weight;
    /**
     * 壁厚
     */
    private String wallThickness;
    /**
     * 最近一次检验日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date lastCheckTime;
    /**
     * 下次检验日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date nextCheckTime;
    /**
     * 报废日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date invalidatedDate;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 状态 0在库 1已出库
     */
    private int status;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /**
     * 订单id
     */
    private String orderId;

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

    public String getBottleCode() {
        return bottleCode;
    }

    public void setBottleCode(String bottleCode) {
        this.bottleCode = bottleCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getMediumId() {
        return mediumId;
    }

    public void setMediumId(int mediumId) {
        this.mediumId = mediumId;
    }

    public String getMediumName() {
        return mediumName;
    }

    public void setMediumName(String mediumName) {
        this.mediumName = mediumName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getWorkPressure() {
        return workPressure;
    }

    public void setWorkPressure(String workPressure) {
        this.workPressure = workPressure;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(String wallThickness) {
        this.wallThickness = wallThickness;
    }

    public Date getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(Date lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public Date getNextCheckTime() {
        return nextCheckTime;
    }

    public void setNextCheckTime(Date nextCheckTime) {
        this.nextCheckTime = nextCheckTime;
    }

    public Date getInvalidatedDate() {
        return invalidatedDate;
    }

    public void setInvalidatedDate(Date invalidatedDate) {
        this.invalidatedDate = invalidatedDate;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
