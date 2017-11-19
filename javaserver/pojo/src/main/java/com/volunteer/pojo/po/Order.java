package com.volunteer.pojo.po;

import com.volunteer.pojo.AbstractStringEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/1 0001.
 */
public class Order extends AbstractStringEntity {
    private String unitName;
    private String unitAddress;
    private String linkPhone;
    private String linkUser;
    private int bottleNum;
    /**
     * 状态 0等待发货 10已发货 20已到货 30已收回
     */
    private short status;
    private int orgId;
    private Date createTime;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public int getBottleNum() {
        return bottleNum;
    }

    public void setBottleNum(int bottleNum) {
        this.bottleNum = bottleNum;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkUser() {
        return linkUser;
    }

    public void setLinkUser(String linkUser) {
        this.linkUser = linkUser;
    }
}
