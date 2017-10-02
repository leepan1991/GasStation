package com.volunteer.pojo.po;

import com.volunteer.pojo.AbstractEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class Permission extends AbstractEntity {
    private String name;
    private String code;
    private String icon;
    private int status;
    private int type;
    private int parentId;
    private int order;

    private boolean selected;
    private List<Permission> children;
    private List<Permission> permissionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public enum Type {
        MENU(0),
        SOURCE(1);

        private int value;

        Type(int value) {
            this.value = value;
        }

        public int intValue() {
            return this.value;
        }
    }
}
