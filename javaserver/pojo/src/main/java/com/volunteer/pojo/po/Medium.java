package com.volunteer.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.volunteer.pojo.AbstractEntity;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
public class Medium extends AbstractEntity {
    /**
     * 介质名称
     */
    private String name;
    /**
     * 机构id
     */
    @JSONField(serialize = false, deserialize = false)
    private int orgId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
