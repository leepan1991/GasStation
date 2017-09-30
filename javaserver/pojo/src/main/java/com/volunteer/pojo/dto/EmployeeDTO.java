package com.volunteer.pojo.dto;

import com.volunteer.pojo.po.Employee;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class EmployeeDTO extends Employee {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
