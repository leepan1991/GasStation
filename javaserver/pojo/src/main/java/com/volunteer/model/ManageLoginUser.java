package com.volunteer.model;

import com.volunteer.pojo.Permission;
import com.volunteer.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25 0025.
 */
public class ManageLoginUser {

    public ManageLoginUser(User user, List<Permission> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    private User user;
    private List<Permission> permissionList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
