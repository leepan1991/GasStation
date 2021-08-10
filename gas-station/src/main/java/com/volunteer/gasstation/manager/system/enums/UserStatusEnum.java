package com.volunteer.gasstation.manager.system.enums;

/**
 * @author by huoyao on 2021/8/10.
 */
public enum UserStatusEnum {

    /**
     * 正常
     */
    NORMAL(1),
    /**
     * 删除
     */
    DELETED(2),
    ;

    private int status;

    UserStatusEnum(int status) {
        this.status = status;
    }

    public int intValue() {
        return this.status;
    }
}
