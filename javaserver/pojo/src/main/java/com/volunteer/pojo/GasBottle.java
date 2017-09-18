package com.volunteer.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class GasBottle extends AbstractEntity {
    private String gasBottleRegCode;
    private String gasBottleId;
    private String gasBottleCode;
    private String siteName;
    private String deviceName;
    private String medium;
    private String manufacturer;
    private String manufactureDate;
    private String workPressure;
    private String gasBottleVolume;
    private String gasBottleWeight;
    private String wallThickness;
    private String lastCheckTime;
    private String nextCheckTime;
    private String invalidatedDate;
    private Date createTime;

    private int startNum;
    private int endNum;

    public String getGasBottleRegCode() {
        return gasBottleRegCode;
    }

    public void setGasBottleRegCode(String gasBottleRegCode) {
        this.gasBottleRegCode = gasBottleRegCode;
    }

    public String getGasBottleId() {
        return gasBottleId;
    }

    public void setGasBottleId(String gasBottleId) {
        this.gasBottleId = gasBottleId;
    }

    public String getGasBottleCode() {
        return gasBottleCode;
    }

    public void setGasBottleCode(String gasBottleCode) {
        this.gasBottleCode = gasBottleCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getWorkPressure() {
        return workPressure;
    }

    public void setWorkPressure(String workPressure) {
        this.workPressure = workPressure;
    }

    public String getGasBottleVolume() {
        return gasBottleVolume;
    }

    public void setGasBottleVolume(String gasBottleVolume) {
        this.gasBottleVolume = gasBottleVolume;
    }

    public String getGasBottleWeight() {
        return gasBottleWeight;
    }

    public void setGasBottleWeight(String gasBottleWeight) {
        this.gasBottleWeight = gasBottleWeight;
    }

    public String getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(String wallThickness) {
        this.wallThickness = wallThickness;
    }

    public String getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(String lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public String getNextCheckTime() {
        return nextCheckTime;
    }

    public void setNextCheckTime(String nextCheckTime) {
        this.nextCheckTime = nextCheckTime;
    }

    public String getInvalidatedDate() {
        return invalidatedDate;
    }

    public void setInvalidatedDate(String invalidatedDate) {
        this.invalidatedDate = invalidatedDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }
}
