package com.volunteer.pojo.po;

import com.volunteer.pojo.AbstractEntity;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
public class Acl extends AbstractEntity {

    private int roleId;
    private int permissionId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
