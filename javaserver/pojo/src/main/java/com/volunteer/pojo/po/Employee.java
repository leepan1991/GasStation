package com.volunteer.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.volunteer.pojo.AbstractEntity;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class Employee extends AbstractEntity {
    private String name;
    private String phone;
    @JSONField(serialize = false)
    private String password;
    private int orgId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
