package com.volunteer.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.volunteer.pojo.AbstractEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class Role extends AbstractEntity {
    private String name;
    private int status;
    private String description;

    private int userId;
    @JSONField(serialize = false)
    private List<Integer> permissionIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
