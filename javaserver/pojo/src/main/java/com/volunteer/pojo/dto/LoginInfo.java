package com.volunteer.pojo.dto;

import com.volunteer.pojo.po.Employee;
import com.volunteer.pojo.po.Medium;
import com.volunteer.pojo.po.Org;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class LoginInfo {

    private String token;
    private Employee employee;
    private Org org;
    private List<Medium> mediumList;

    public LoginInfo() {
        super();
    }

    public LoginInfo(Employee employee, Org org) {
        this.employee = employee;
        this.org = org;
    }

    public LoginInfo(String token, Employee employee, Org org, List<Medium> mediumList) {
        this.token = token;
        this.employee = employee;
        this.org = org;
        this.mediumList = mediumList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public List<Medium> getMediumList() {
        return mediumList;
    }

    public void setMediumList(List<Medium> mediumList) {
        this.mediumList = mediumList;
    }
}
