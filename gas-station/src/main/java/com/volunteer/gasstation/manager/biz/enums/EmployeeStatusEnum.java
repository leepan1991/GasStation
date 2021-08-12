package com.volunteer.gasstation.manager.biz.enums;

/**
 * @author by huoyao on 2021/8/12.
 */
public enum EmployeeStatusEnum {

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

    EmployeeStatusEnum(int status) {
        this.status = status;
    }

    public int intValue() {
        return this.status;
    }
}
